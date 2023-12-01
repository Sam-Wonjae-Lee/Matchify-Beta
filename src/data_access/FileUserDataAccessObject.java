package data_access;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FileUserDataAccessObject {
    private final String friends_csvFile_path = "src/csv_files/user_friends.csv";
    private final String inbox_csvFile_path = "src/csv_files/user_inbox.csv";

    private HashMap<String, HashSet<String>> friend_data_saved = new HashMap<>();
    private HashMap<String, HashSet<String>> inbox_data_saved = new HashMap<>();

    private final String sample = ",";

    public FileUserDataAccessObject() throws IOException {
        this.friend_data_saved = this.read_friend();
        this.inbox_data_saved = this.read_inbox();
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile_path))) {
            for (String key : this.friend_data_saved.keySet()) {
                String new_str = String.valueOf(key);
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

    public void add_friend(String user_id, String friend_id){
        // adds the friend into user_id's friend within the database.
        if(this.friend_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.friend_data_saved.get(user_id);
            if(!new_arr.contains(friend_id)){
                // it does not contain duplicate
                new_arr.add(friend_id);
                this.friend_data_saved.replace(user_id,new_arr);
            }
        }
        else {
            HashSet<String> new_arr = new HashSet<>();
            new_arr.add(friend_id);
            this.friend_data_saved.put(user_id,new_arr);
        }
        this.write_friend();
    }

    public HashSet<String> get_friends(String user_id){
        // returns a HashSet<String> of all the friends that user has.
        return this.friend_data_saved.get(user_id);
    }

    public Set<String> get_all_users(){
        return this.friend_data_saved.keySet();
    }

    private HashMap<String, HashSet<String>> read_inbox(){
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

    public void add_friend_request(String user_id, String friend_id){
        // adds the user into the database, if user_id already exists in the database, update it's values instead
        if(this.inbox_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.inbox_data_saved.get(user_id);
            if(!new_arr.contains(friend_id)){
                // it does not contain duplicate
                new_arr.add(friend_id);
                this.inbox_data_saved.replace(user_id,new_arr);
            }
        }
        else {
            HashSet<String> new_arr = new HashSet<String>();
            new_arr.add(friend_id);
            this.inbox_data_saved.put(user_id,new_arr);
        }
        this.write_inbox();
    }

    public void remove_friend_request(String user_id, String friend_id){
        // removes the friend from the database, if user_id already exists in the database.
        if(this.inbox_data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.inbox_data_saved.get(user_id);
            if(new_arr.contains(friend_id)){
                // if it's in user_id's friends
                new_arr.remove(friend_id);
                this.inbox_data_saved.replace(user_id,new_arr);
            }
        }
        this.write_inbox();
    }

    public HashSet<String> get_user_inbox(String user_id){
        // returns the values of the user_id, if user_id doesn't exist, return null instead.
        return this.inbox_data_saved.get(user_id);
    }

    public boolean has_user(String user_id) {
        return this.friend_data_saved.containsKey(user_id);
    }
}
