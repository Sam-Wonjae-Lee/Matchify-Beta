package data_access;

import entity.*;
import use_case.accept_invite.AcceptUserDataAccessInterface;
import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.home_page.HomePageUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.match.MatchUserAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;
import use_case.send_invite.SendInviteUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SendInviteUserDataAccessInterface, DeclineUserDataAccessInterface,
        AcceptUserDataAccessInterface, LoginUserDataAccessInterface,
        MatchUserAccessInterface, OpenInboxUserDataAccessInterface,
        HomePageUserDataAccessInterface {
    private final String friends_csvFile_path = "src/csv_files/user_friends.csv";
    private final String inbox_csvFile_path = "src/csv_files/user_inbox.csv";

    private final String genre_csvFile_path = "src/csv_files/user_genre.csv";

    private final String username_csvFile_path = "src/csv_files/user_username.csv";

    private final Map<String, HashSet<String>> friend_data_saved = new HashMap<>();
    private final Map<String, HashSet<String>> inbox_data_saved = new HashMap<>();

    private final HashMap<String, HashMap<String, Integer>> genre_data_saved = new HashMap<>();

    private final HashMap<String, String> username_data_saved = new HashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private final String sample = ",";

    private UserFactory userFactory;
    public FileUserDataAccessObject(CommonUserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        HashMap<String, HashSet<String>> friend_data = this.read_friend();
        HashMap<String, HashSet<String>> inbox_data = this.read_inbox();
        HashMap<String, HashMap<String, Integer>> genre_data = this.read_user_genre();
        HashMap<String, String> username_data = this.read_username();
        for(String key : friend_data.keySet()){
            FriendsList friendsList = new FriendsList();
            for(String user_id : friend_data.get(key)){
                friendsList.add_friend(user_id);
            }

            Inbox inbox = new Inbox();
            for(String user_id : inbox_data.get(key)){
                inbox.add_invite(user_id);
            }

            Genre obj = new Genre();
            obj.setGenreMap(genre_data.get(key));

            String username = username_data.get(key);

            User user = this.userFactory.create(key, friendsList, inbox, obj, username);
            this.accounts.put(key, user);
            this.inbox_data_saved.put(key,inbox_data.get(key));
            this.friend_data_saved.put(key,friend_data.get(key));
            this.genre_data_saved.put(key,genre_data.get(key));
            this.username_data_saved.put(key, username_data.get(key));
        }
        //checks that the DAO is storing users in hashmap
        System.out.println("friend: " + friend_data_saved.keySet());
        System.out.println("inbox: " + inbox_data_saved.keySet());
        System.out.println("accounts: " + accounts.keySet());
        System.out.println("username: " + username_data_saved.keySet());
    }

    private HashMap<String, String> read_username(){
        String mystring;
        HashMap<String, String> ans = new HashMap<>();
        try {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.username_csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                ans.put(users[0], users[1]);
            }
            return ans;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return ans;
    }

    private HashMap<String, HashMap<String, Integer>> read_user_genre(){
        String mystring;
        HashMap<String, HashMap<String, Integer>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.genre_csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                HashMap<String, Integer> new_arr = new HashMap<>();
                for(int i = 1; i < users.length; i++){
                    String[] genres = users[i].split("_");
                    System.out.println(genres[0]);
                    System.out.println(genres[1]);
                    new_arr.put(genres[0],Integer.parseInt(genres[1]));
                }
                ans.put(users[0], new_arr);
            }
            return ans;
        }
        catch (IOException e)//catches exception in the try block
        {
            e.printStackTrace();//Prints this throwable and its backtrace
        }
        return ans;
    }

    public void add_username(String user_id, String username){
        this.username_data_saved.put(user_id, username);
        this.write_username();
    }

    private void write_username(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.username_csvFile_path))) {
            for (String key : this.username_data_saved.keySet()) {
                String str = key + "," + username_data_saved.get(key);
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add_user_genre(String user_id, HashMap<String, Integer> genres){
        // adds a user's genre into the database, if the user already have previous entries inside of the genre, then update it
        this.genre_data_saved.put(user_id,genres);
        this.write_genre();
    }

    private void write_genre(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.genre_csvFile_path))) {
            for (String key : this.genre_data_saved.keySet()) {
                String new_str = key;
                HashMap<String, Integer> genre = this.genre_data_saved.get(key);
                for(String key1 : this.genre_data_saved.get(key).keySet()){
                    new_str = new_str.concat(","+key1+"_"+ genre.get(key1));
                }
                writer.write(new_str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                HashSet<String> new_arr = new HashSet<>();
                for(int i = 1; i < users.length; i++){
                    new_arr.add(users[i]);
                }
                ans.put(users[0], new_arr);
            }
            return ans;
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

    public void add_to_friendList(String user_id, String friend_id){
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
                HashSet<String> new_arr = new HashSet<>();
                for(int i = 1; i < users.length; i++){
                    new_arr.add(users[i]);
                }
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

    public void add_friend_request(String user_id, String friend_id){
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

    public void remove_friend_request(String user_id, String friend_id){
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
        User user = this.accounts.get(user_id);
        User friend = this.accounts.get(friend_id);
        this.add_to_friendList(user_id, friend_id);
        this.add_to_friendList(friend_id, user_id);
    }

    @Override
    public void deleteInvite(String user_id, String friend_id) {
        User user = this.accounts.get(user_id);
        user.getInbox().remove_invite(friend_id);
        this.remove_friend_request(user_id,friend_id);
        System.out.println("delete invite DAO ran");
        System.out.println("succesfully deleted = " + !user.getInbox().get_invites().contains(friend_id));
    }

    @Override
    public boolean userExists(String user_id) {
        return this.accounts.containsKey(user_id);
    }

    @Override
    public void save(User user) {
        String user_id = user.getUserID();
        this.accounts.put(user_id,user);
        HashSet<String> empty = new HashSet<>();
        this.friend_data_saved.put(user_id, empty);
        this.inbox_data_saved.put(user_id, empty);
        this.username_data_saved.put(user_id, user.getUsername());
        this.write_inbox();
        this.write_friend();
        this.write_username();
    }

    @Override
    public void addToInbox(String inviter_id, String receiver_id) {
        System.out.println("add to inbox is called");
        if(!accounts.get(receiver_id).getInbox().get_invites().contains(inviter_id)) {
            this.add_friend_request(receiver_id, inviter_id);
        }
    }

    @Override
    public Collection<User> get_all_users() {
        return accounts.values();
    }

    @Override
    public User getUser(String userID) {
        return this.accounts.get(userID);
    }

    public void clear_all_files(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inbox_csvFile_path))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(genre_csvFile_path))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(friends_csvFile_path))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}