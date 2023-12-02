package data_access;

import entity.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.match.MatchDataAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FileUserDataAccessObject implements SendInviteUserDataAccessInterface, DeclineUserDataAccessInterface,
        AcceptUserDataAccessInterface, LoginUserDataAccessInterface,
        MatchDataAccessInterface, OpenInboxUserDataAccessInterface {
    private final String friends_csvFile_path = "src/csv_files/user_friends.csv";
    private final String inbox_csvFile_path = "src/csv_files/user_inbox.csv";

    private final Map<String, HashSet<String>> friend_data_saved = new HashMap<>();
    private final Map<String, HashSet<String>> inbox_data_saved = new HashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private final String sample = ",";

    private UserFactory userFactory;

    public FileUserDataAccessObject(CommonUserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        HashMap<String, HashSet<String>> friend_data = this.read_friend();
        HashMap<String, HashSet<String>> inbox_data = this.read_inbox();
        for(String key : friend_data.keySet()){
            FriendsList friendsList = new FriendsList();
            for(String user_id : friend_data.get(key)){
                friendsList.add_friend(user_id);
            }
            Inbox inbox = new Inbox();
            for(String user_id : inbox_data.get(key)){
                inbox.add_invite(user_id);
            }
            User user = this.userFactory.create(key, friendsList, inbox);
            this.accounts.put(key, user);
        }
    }

    // read method is used for initializing the database
    private HashMap<String, HashSet<String>> read_friend(){
        // reads from the csv file, and returns it
        String mystring;
        HashMap<String, HashSet<String>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.friends_csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                HashSet<String> new_arr = new HashSet<>(Arrays.asList(users));
                ans.put(users[0], new_arr);
            }
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
        return ans;
    }

    private void write_friend(){
        // writes the friend_data_saved back into the csv file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.friends_csvFile_path))) {
            for (String key : this.friend_data_saved.keySet()) {
                String new_str = key;
                System.out.println(this.friend_data_saved);
                for(String val : this.friend_data_saved.get(key)){
                    new_str = new_str.concat(","+val);
                }
                writer.write(new_str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add_to_friendList(String user_id, String friend_id){
        // adds the friend into user_id's friend within the database.
        if(this.friend_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.friend_data_saved.get(user_id);
            this.accounts.get(user_id).getFriendList().add_friend(friend_id);
            new_arr.add(friend_id);
            this.friend_data_saved.replace(user_id,new_arr);
        }
        else {
            HashSet<String> new_arr = new HashSet<>();
            new_arr.add(friend_id);
            this.friend_data_saved.put(user_id,new_arr);
        }
        this.write_friend();
    }

    private HashMap<String, HashSet<String>> read_inbox(){
        String mystring;
        HashMap<String, HashSet<String>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.inbox_csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                HashSet<String> new_arr = new HashSet<String>(Arrays.asList(users));
                ans.put(users[0], new_arr);
            }
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
        return ans;
    }

    private void write_inbox(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inbox_csvFile_path))) {
            for (String key : this.inbox_data_saved.keySet()) {
                String new_str = String.valueOf(key);
                System.out.println(this.inbox_data_saved);
                for(String val : this.inbox_data_saved.get(key)){
                    new_str = new_str.concat(","+val);
                }
                writer.write(new_str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add_friend_request(String user_id, String friend_id){
        // adds the user into the database, if user_id already exists in the database, update it's values instead
        if(this.inbox_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.inbox_data_saved.get(user_id);
            new_arr.add(friend_id);
            this.inbox_data_saved.replace(user_id,new_arr);
            this.accounts.get(user_id).getInbox().add_invite(friend_id);
        }
        else {
            HashSet<String> new_arr = new HashSet<>();
            new_arr.add(friend_id);
            this.inbox_data_saved.put(user_id,new_arr);
        }
        this.write_inbox();
    }

    private void remove_friend_request(String user_id, String friend_id){
        // removes the friend from the database, if user_id already exists in the database.
        if(this.inbox_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.inbox_data_saved.get(user_id);
            this.accounts.get(user_id).getInbox().remove_invite(friend_id);
            new_arr.remove(friend_id);
            this.inbox_data_saved.replace(user_id,new_arr);
        }
        this.write_inbox();
    }

    @Override
    public void add_friend(String user_id, String friend_id) {

    }

    @Override
    public void deleteInvite(String username) {

    }

    @Override
    public boolean userExists(String userId) {
        return false;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public List<String> getUserPlaylistID(String user) throws IOException, ExecutionException, InterruptedException, SpotifyWebApiException {
        return null;
    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public void addToInbox(String inviteID, String userID) {

    }

    @Override
    public User getUser(String userID) {
        return null;
    }
}
