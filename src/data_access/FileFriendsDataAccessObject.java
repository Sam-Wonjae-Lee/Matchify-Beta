import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// TODO: add the proper implements
public class FileFriendsDataAccessObject implements {

    private final String csvFile_path = "src/csv_files/user_friends.csv";

    private HashMap<String, HashSet<String>> data_saved = new HashMap<>();

    private final String sample = ",";


    public FileFriendsDataAccessObject() throws IOException {
        this.data_saved = this.read();
    }

    // read method is used for initializing the database
    private HashMap<String, HashSet<String>> read(){
        // reads from the csv file, and returns it
        String mystring;
        HashMap<String, HashSet<String>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.csvFile_path));
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

    private void write(){
        // writes the data_saved back into the csv file.
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

    public void add_friend(String user_id, String friend_id){
        // adds the friend into user_id's friend within the database.
        if(this.data_saved.containsKey(user_id)){
            HashSet<String> new_arr = this.data_saved.get(user_id);
            if(!new_arr.contains(friend_id)){
                // it does not contain duplicate
                new_arr.add(friend_id);
                this.data_saved.replace(user_id,new_arr);
            }
        }
        else {
            HashSet<String> new_arr = new HashSet<>();
            new_arr.add(friend_id);
            this.data_saved.put(user_id,new_arr);
        }
        this.write();
    }

    public HashSet<String> get_user_friends(String user_id){
        // returns a HashSet<String> of all the friends that user has.
        return this.data_saved.get(user_id);
    }

    public Set<String> get_all_users(){
        return this.data_saved.keySet();
    }
}