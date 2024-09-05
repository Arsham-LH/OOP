
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class Player {
    boolean visited;
    int passes;
    int kitNumber;
    ArrayList<Integer> adjacents ;

    public Player(int kitNumber) {
        this.visited = false;
        this.passes = 0;
        this.kitNumber = kitNumber;
        this.adjacents = new ArrayList<>(12);
    }
}
public class HW1_Q5_99102156 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        int n = scan.nextInt();

        Player[] players = new Player[12];
        for (int i = 0; i < 11; i++) {
            players[i] = new Player(i + 1);
        }
            for (int i = 0; i < n; i++) {

            int a = scan.nextInt();
            int b = scan.nextInt();

            players[a-1].adjacents.add(b);
            players[b-1].adjacents.add(a);
        }
        for (int i = 0; i < 11; i++) {
            Collections.sort(players[i].adjacents);
        }
        if (FindMinPasses(players)!=0)
            System.out.print(2 * (45 / (t * FindMinPasses(players))));
        else
            System.out.println(0);
    }

    static int FindMinPasses(Player[] players) {
        ArrayList<Integer> kitNumsLine = new ArrayList<>(11);

        kitNumsLine.add(1);
        players[0].visited = true;
        int i = 0;
        while (kitNumsLine.size()!=0 && kitNumsLine.get(kitNumsLine.size() - 1) != 11 ) {
//            System.out.println("test");
//            for (int j = 0; j < kitNumsLine.size(); j++) {
//                System.out.print(kitNumsLine.get(j)+" ");
//            }
//            System.out.println();

            int j = 0;
//            System.out.println("player 0 adjecents : "+players[i].adjacents.size());
            while (j < players[kitNumsLine.get(i)-1].adjacents.size()) {
                if (!players[players[kitNumsLine.get(i)-1].adjacents.get(j) - 1].visited) {
                    players[players[kitNumsLine.get(i)-1].adjacents.get(j) - 1/*andise haryek az hamsaye haye bazikone i om */].visited = true;
                    players[players[kitNumsLine.get(i)-1].adjacents.get(j) - 1].passes = players[kitNumsLine.get(i)-1].passes + 1;
                    kitNumsLine.add(players[players[kitNumsLine.get(i)-1].adjacents.get(j) - 1].kitNumber);
                }
                j++;
            }
//            for (int k = 0; k < 11; k++) {
//                System.out.println("visited player "+(k+1)+" : "+players[k].visited);
//            }
            kitNumsLine.remove(i);
//            System.out.println("kitsNumLine size : "+kitNumsLine.size());
        }
        if (players[10].passes!=0)
            return players[10].passes;
        else
            return 0;
    }
}
