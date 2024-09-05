import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class MainCustomer {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
        CustomerManager customerManager = new CustomerManager();
        CustomerInputProcessor customerInputProcessor = new CustomerInputProcessor(customerManager);
        customerInputProcessor.run();
//        File file = new File("d:\\arsham\\TERM 2\\OOP CODES\\HOMEWORKS\\outTest.txt");
//        try {
//            FileWriter fileWriter = new FileWriter(file);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    bufferedWriter.write((i+1)*(j+1)+"\t");
//                }
//                bufferedWriter.write("\n");
//            }
//            bufferedWriter.close();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
