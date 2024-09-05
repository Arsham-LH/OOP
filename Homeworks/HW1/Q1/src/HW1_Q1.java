import java.util.Scanner;

public class HW1_Q1 {
    public static void main(String[] args) {
    {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int t = scan.nextInt();
        ShowAdvanceLine(n, k, t);
    }
    static void ShowAdvanceLine(int n, int k, int t) {
        int total_number = t * k * n / 100;
        int number_of_filled_squares = total_number / k;
        int degree_of_nonfilled_square = total_number % k;
        int i;
        for (i = 1; i <= n; i++) {
            if (i<=number_of_filled_squares)
            {
                System.out.print(k+" ");
            }
            else if (i==number_of_filled_squares+1)
            {
                System.out.print(degree_of_nonfilled_square+" ");
            }
            else
            {
                System.out.print(0);
                if(i!=n)
                {
                    System.out.print(" ");
                }

            }
        }
    }

}
