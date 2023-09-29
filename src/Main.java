/*
A minimal example of calling the API from Java.
The output of the program should indicate the result of the request.
Tools like those above will generate code snippets for you, as will some API documentation pages.
You can also refer to the code from the Grade API lab activity for examples using OkHttp.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "frankisnothere");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
