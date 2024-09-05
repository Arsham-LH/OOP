import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;




public class MainCustomer {
    public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager();
        CustomerInputProcessor customerInputProcessor = new CustomerInputProcessor(customerManager);
        customerInputProcessor.run();
    }
}
