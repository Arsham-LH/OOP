import java.util.Scanner;
class words {
    StringBuilder wordS;
    int repeat;
    words() {
        wordS = new StringBuilder();
    }
}
public class HW1_Q6_99102156 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        words [] word = new words[3];
        for (int i = 0; i < 3; i++) {
            word[i] = new words();
        }
        generateAllWords(3 , 0 , word , '.');
        System.out.println(word[0].wordS);
//        String dna_s=new String(scan.nextLine());
//        StringBuilder dna = new StringBuilder();
//        dna.append(dna_s);
//        StringBuilder test = new StringBuilder();
//        test.append(dna);
//        test.append("hi hello");
//        System.out.println(test);
//        short k = scan.nextShort();
//        int d = scan.nextInt();

//        FindMaxRepeatWords( dna , k , d);
    }

    static void FindMaxRepeatWords(StringBuilder dna, int k, int d)
    {
        words[] word = new words[k*k*k*k];

    }
    static void generateAllWords (int k , int startChar , words [] word , char c)
    {
        if(startChar<k-1)
        {
            if (c!='.')
                word[0].wordS.append(c);

            generateAllWords(k , startChar+1 , word , 'A');

            if (c!='.')
                word[0].wordS.append(c);

            generateAllWords(k , startChar+1 , word , 'C');

            if (c!='.')
                word[0].wordS.append(c);

            generateAllWords(k , startChar+1 , word , 'G');

            if (c!='.')
                word[0].wordS.append(c);

            generateAllWords(k , startChar+1 , word , 'T');

        }
        else
        {
            if (c!='.')
                word[0].wordS.append(c);
            word[0].wordS.append(" ");
        }
//        if (c !='.') {
//            if (c != 'A')
//                word[0].wordS.append("A");
//            if (c != 'C')
//                word[0].wordS.append("C");
//            if (c != 'G')
//                word[0].wordS.append("G");
//            if (c != 'T')
//                word[0].wordS.append("T");
//            word[0].wordS.append(c);
        }








    public static void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }
}
