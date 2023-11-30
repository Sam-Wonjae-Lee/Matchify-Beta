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
}
