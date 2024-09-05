import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class HW1_Q2_99102156 {
    static class Ticket {
        int ticket;
        int num_paid;

        Ticket(int c) {
            num_paid = 0;
            ticket = c;
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        ArrayList<Integer> x = new ArrayList<>(n);

        Ticket[] a = new Ticket[n];

        for (int i = 0; i < n; i++) {
            x.add(scan.nextInt());
            a[i] = new Ticket(x.get(i));
        }
        int first[] = new int[n];
        for (int i = 0; i < n; i++) {
            first[i]=x.get(i);
        }

        boolean chaotic = false;
        int num_changes = 0;

        for (int i = 0; i < n && chaotic == false; i++) {
            int index = x.indexOf(i+1);
//            System.out.println("index for i = "+i+": "+index);

            while (index > i ) {
                int tmp = a[index].ticket;
//                System.out.println("tmp :"+tmp);

                a[index].ticket = a[index - 1].ticket;
                x.set(index , x.get(index-1));

                a[index - 1].ticket = tmp;
                x.set(index-1 , tmp);

                a[a[index].ticket-1].num_paid++;
//                System.out.println("num_paids :");
//                for (int j = 0; j < n; j++) {
//                    System.out.print("a["+j+"] : "+a[j].num_paid+" ");
//                }
                if (a[a[index].ticket-1].num_paid > 2)
                    chaotic = true;

                index--;
//                System.out.println("index : "+index);
                num_changes++;
            }
            while (index < i ) {
                int tmp = a[index].ticket;
//                System.out.println("tmp :"+tmp);


                a[index].ticket = a[index + 1].ticket;
                x.set(index , x.get(index+1));

                a[index + 1].ticket = tmp;
                x.set(index+1 , tmp);

                a[a[index+1].ticket-1].num_paid++;
//                System.out.println("num_paids :");
//                for (int j = 0; j < n; j++) {
//                    System.out.print("a["+j+"] : "+a[j].num_paid+" ");
//                }

                if (a[a[index+1].ticket-1].num_paid > 2)
                    chaotic = true;

                index++;
//                System.out.println("index : "+index);
                num_changes++;
            }
//            for (int j = 0; j < n; j++) {
//                System.out.print("x["+j+"] : "+x[j]+" ");
//            }
//            System.out.println();
        }
        if (chaotic == true) {
            System.out.print("Too chaotic");
        } else {
            System.out.print(num_changes);
        }
    }
}
