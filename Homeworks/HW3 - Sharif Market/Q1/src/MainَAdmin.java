import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainَAdmin {
    public static void main(String[] args) {
        AdminManager manager = new AdminManager();
        AdminInputProcessor inputProcessor = new AdminInputProcessor(manager);
        inputProcessor.run();
//        String string = new String(scan.nextLine());
//        String [] strings = string.split(" -");
//        for (int i = 0; i < strings.length; i++) {
//            System.out.println(strings[i]);
//        }
    }
}
