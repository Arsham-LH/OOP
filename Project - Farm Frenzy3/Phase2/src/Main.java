import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        InputProcessor inputProcessor = new InputProcessor(manager);
        inputProcessor.runGame();


//        JLabel testLabel= null;
//        System.out.println(testLabel == null);


//        try {
//            getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
//    public static Connection getConnection() throws Exception{
//        try{
//            String driver = "com.mysql.jdbc.Driver";
//            String url = "jdbc:mysql://24.196.52.166:3306/testdb";
//            String username = "username";
//            String password = "password";
//            Class.forName(driver);
//            Connection conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected!");
//            return conn;
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//
//
//
//        return null;
//    }
}
