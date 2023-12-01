import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// do I need to implement the SignUpUserDataAccessInterface or LoginUserDataAccessInterface?
public class FileFriendsDataAccessObject {

    private final String csvFile_path = "src/csv_files/user_friends.csv";

    private HashMap<Integer, ArrayList<Integer>> data_saved = new HashMap<>();

    private final String sample = ",";


    public FileFriendsDataAccessObject() throws IOException {
        this.data_saved = this.read();
    }

    // read method is used for initializing the database
    private HashMap<Integer, ArrayList<Integer>> read(){
        // [user_id, user_name, photo, age, bio]
        String mystring;
        HashMap<Integer, ArrayList<Integer>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                ArrayList<Integer> new_arr = new ArrayList<>();
                for(int a = 0; a < users.length; a++){
                    new_arr.add(Integer.parseInt(users[a]));
                }
                ans.put(Integer.parseInt(users[0]), new_arr);
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
            for (Integer key : this.data_saved.keySet()) {
                String new_str = String.valueOf(key);
                System.out.println(this.data_saved);
                ArrayList<Integer> val = this.data_saved.get(key);
                for(int i = 0; i < val.size(); i++){
                    new_str = new_str.concat(","+String.valueOf(val.get(i)));
                }
                writer.write(new_str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add_friend(Integer user_id, Integer friend_id){
        // adds the user into the database, if user_id already exists in the database, update it's values instead
        if(this.data_saved.containsKey(user_id)){
            ArrayList<Integer> new_arr = this.data_saved.get(user_id);
            if(!new_arr.contains(friend_id)){
                // it does not contain duplicate
                new_arr.add(friend_id);
                this.data_saved.replace(user_id,new_arr);
            }
        }
        else {
            ArrayList<Integer> new_arr = new ArrayList<>();
            new_arr.add(friend_id);
            this.data_saved.put(user_id,new_arr);
        }
        this.write();
    }

    public ArrayList<Integer> get_user(Integer user_id){
        // returns the values of the user_id, if user_id doesn't exist, return null instead.
        return this.data_saved.get(user_id);
    }
}