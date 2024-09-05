import java.util.Scanner;

public class HW2_Q1_99102156 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StringBuilder username = new StringBuilder(scan.nextLine());
        String password = new String(scan.nextLine());
        System.out.print(isAppropriate(username, password));
    }

    static String isAppropriate(StringBuilder username, String password) {
        if (username.length() < 6 || username.length() > 30)
            return "Inappropriate username or password";
        if ((int) username.charAt(0) < 'A' || (int) username.charAt(0) > 'z')
            return "Inappropriate username or password";
        for (int i = 1; i < username.length(); i++)
            if (username.charAt(i) < 'A' && username.charAt(i) > 'z' && username.charAt(i) != '_')
                return "Inappropriate username or password";

        if (password.length() < 8 || password.length() > 20) {
            return "Inappropriate username or password";
        }
        if (!(password.matches("(.*)[0-9](.*)") && password.matches("(.*)[a-z](.*)") && password.matches("(.*)[A-Z](.*)"))) {
            return "Inappropriate username or password";
        }
        if (password.contains(" ")) {
            return "Inappropriate username or password";
        }
        if (password.contains("^") || password.contains("=") || password.contains("+") || password.contains("-") || password.contains("(") || password.contains(")") || password.contains("*") || password.contains("&") || password.contains("%") || password.contains("$") || password.contains("#") || password.contains("@") || password.contains("!"))
            return "Account created successfully";
        return "Inappropriate username or password";
    }
}

