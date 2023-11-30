package data_access;


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.*;

public class FileUserDataAccessObject {

    private File csvFile;

    private Map<String, Integer> headers = new LinkedHashMap<>();


    // read method is used for initializing the database
    private HashMap<Integer, ArrayList<String>> read(){
        // [user_id, user_name, photo, age, bio]
        String mystring;
        HashMap<Integer, ArrayList<String>> ans = new HashMap<>();
        try
        {
            BufferedReader brdrd = new BufferedReader(new FileReader(this.csvFile_path));
            while ((mystring = brdrd.readLine()) != null)  //Reads a line of text
            {
                String[] users = mystring.split(sample);
                ArrayList<String> new_arr = new ArrayList<>();
                for(int a = 1; a <= 4; a++){
                    new_arr.add(users[a]);
                }
                ans.put(Integer.parseInt(users[0]), new_arr);
                System.out.println("ID: " + users[0] + ", Name:" + users[1] + ", Photo: " + users[2] + ", Age:" + users[3] + ", Bio:  " + users[4]);
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
                ArrayList<String> val = this.data_saved.get(key);
                System.out.println(val);
                String new_str = String.valueOf(key) + "," +val.get(0) + "," + val.get(1) + ","  + val.get(2) + ","  + val.get(3);
                writer.write(new_str);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add_user(Integer user_id, String user_name, String photo, String age, String bio){
        // adds the user into the database, if user_id already exists in the database, update it's values instead
        ArrayList<String> new_arr = new ArrayList<>();
        new_arr.add(user_name);
        new_arr.add(photo);
        new_arr.add(age);
        new_arr.add(bio);
        if(this.data_saved.containsValue(user_id)){
            this.data_saved.replace(user_id,new_arr);
        }
        else {
            this.data_saved.put(user_id,new_arr);
        }
        this.write();
    }
}
