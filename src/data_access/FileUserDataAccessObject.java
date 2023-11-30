package data_access;

import entity.CommonUser;
import use_case.decline_invite.DeclineUserDataAccessInterface;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.*;

public class FileUserDataAccessObject implements DeclineUserDataAccessInterface {

    private File csvFile;

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, CommonUser> accounts = new HashMap<>();

    // Add userfactory

    public FileUserDataAccessObject(String csvPath) throws IOException {

        //csvFile = new File(csvpath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            //save();
        }
    }

    @Override
    public String delete(){
        String s = String.join("\n", accounts.keySet());
        accounts.clear();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
