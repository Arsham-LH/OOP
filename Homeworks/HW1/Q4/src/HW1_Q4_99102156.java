import java.util.ArrayList;
import java.util.Scanner;

public class HW1_Q4_99102156 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        ArrayList < Integer > b = new ArrayList<>(n);
        System.out.print(Hybrid(0 , n , b));
    }
    static int Hybrid (int start , int n, ArrayList<Integer> b){
        ArrayList<Integer> a = new ArrayList<>(n);
        ArrayList<StringBuilder> string = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            return Hybrid(i , n , b);
        }

    }
}
