import java.util.Scanner;

public class QUIZ1_99102156 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        short n = scan.nextShort();
        short k = scan.nextShort();
        long a[] = new long[30];
        for (short i = 0; i < n; i++) {
            a[i] = scan.nextLong();
        }
        System.out.print(isPowerSeries(n,k,a));
    }
    static String isPowerSeries ( short n , short k , long a[])
    {
        boolean exit = false ;
        int num_one_remains = 0;
        long [][] what_k_powers = new long[n][100];

        long[] overalPowers = new long[n];
        for (short i = 0; i < n; i++) {
            overalPowers [i] = 0;
        }
        for (short i = 0; i < n; i++) {
            if (a[i] % k == 1)
            {
                num_one_remains++;
                a[i]--;
                if ( num_one_remains ==2)
                    return "NO";
            }
            if (a[i] % k != 0)
                return "NO";
            else
            {
                int j=0;
                while(a[i] != 0)
                {
                    while (a[i] % k == 0 && exit == false ){
                        if (a[i]!=0)
                        {
                            a[i]/=k;
                            overalPowers[i]++;
                        }
                        else {
                            exit = true;
                        }
                    }
                    what_k_powers[i][j]=overalPowers[i];
                    for (int l = 0; l < i; l++) {
                        for (int m = 0; m < what_k_powers[l].length; m++) {
                            if (what_k_powers[i][j] == what_k_powers[l][m])
                                return "NO";
                        }
                    }
                    a[i]--;
                    j++;
                    if (a[i] % k !=0 && a[i] != 0)
                        return "NO";
                }
            }
        }
        return "YES";
    }
}
