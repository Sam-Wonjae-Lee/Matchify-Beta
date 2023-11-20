package data_access;

import entity.User;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.*;

public class FileUserDataAccessObject {

    private File csvFile;

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

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


}
