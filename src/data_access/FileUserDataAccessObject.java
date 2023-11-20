package data_access;

import entity.CommonUser;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.*;

public class FileUserDataAccessObject {

    private File csvFile;

    private Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, CommonUser> accounts = new HashMap<>();

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Add userfactory

    public FileUserDataAccessObject(String csvPath) throws IOException {

        //csvFile = new File(csvpath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        }
    }


}
