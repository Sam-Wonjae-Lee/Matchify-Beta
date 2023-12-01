package data_access;

import use_case.decline_invite.DeclineUserDataAccessInterface;
import use_case.open_inbox.OpenInboxUserDataAccessInterface;

import java.io.*;
import java.util.*;

// do I need to implement the SignUpUserDataAccessInterface or LoginUserDataAccessInterface?
public class FileInboxDataAccessObject implements DeclineUserDataAccessInterface, OpenInboxUserDataAccessInterface {

    private final String csvFile_path = "src/csv_files/user_friends.csv";

    private HashMap<String, HashSet<String>> data_saved = new HashMap<>();

    private final String sample = ",";


    public FileInboxDataAccessObject() throws IOException {
        this.data_saved = this.read();
    }

    // read method is used for initializing the database
    private HashMap<String, HashSet<String>> read(){
        String mystring;
        HashMap<String, HashSet<String>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.csvFile_path));
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

    private void write(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile_path))) {
            for (String key : this.data_saved.keySet()) {
                String new_str = String.valueOf(key);
                System.out.println(this.data_saved);
                for(String val : this.data_saved.get(key)){
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
        if(this.data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.data_saved.get(user_id);
            if(!new_arr.contains(friend_id)){
                // it does not contain duplicate
                new_arr.add(friend_id);
                this.data_saved.replace(user_id,new_arr);
            }
        }
        else {
            HashSet<String> new_arr = new HashSet<String>();
            new_arr.add(friend_id);
            this.data_saved.put(user_id,new_arr);
        }
        this.write();
    }

    public void remove_friend_request(String user_id, String friend_id){
        // removes the friend from the database, if user_id already exists in the database.
        if(this.data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.data_saved.get(user_id);
            if(new_arr.contains(friend_id)){
                // if it's in user_id's friends
                new_arr.remove(friend_id);
                this.data_saved.replace(user_id,new_arr);
            }
        }
        this.write();
    }

    public HashSet<String> get_user_inbox(String user_id){
        // returns the values of the user_id, if user_id doesn't exist, return null instead.
        return this.data_saved.get(user_id);
    }

    public Set<String> get_all_users(){
        return this.data_saved.keySet();
    }
}