import java.util.*;


public class H1Q3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String code = scan.nextLine();

        String text = scan.nextLine();

        System.out.println(ChangeText(code , text));


    }

    static String  ChangeText(String code , String text) {
        String result = new String("");
        int wordStart=0;
        int wordEnd = 0;
        while (wordEnd<text.length()) {
            while (text.charAt(wordStart)<'a' || text.charAt(wordStart)>'z')
            {
                result+=text.charAt(wordStart);
                wordStart++;
            }

            wordEnd=wordStart+1;

//            System.out.println("wordStart : "+wordStart);
//            System.out.println("wordEnd : "+wordEnd);

            while (wordEnd<text.length() && text.charAt(wordEnd) >= 97 && text.charAt(wordEnd) <= 122)
            {
                wordEnd++;
//                System.out.println("wordEnd : " + wordEnd);
            }

//            System.out.println("wordEnd 2 : "+wordEnd);

            int i;
            for (i = wordStart; i <wordEnd ; i++) {
                int number_of_char = i-wordStart+1;
//                System.out.println("number_of_char : "+number_of_char);

                int number_of_related_code;
                if (number_of_char % code.length()!=0)
                    number_of_related_code = number_of_char % code.length();
                else
                    number_of_related_code = code.length();


//                System.out.println("number_of_related_code : "+number_of_related_code);

//                if (text.charAt(i)>='a' && text.charAt(i)<='z') {
                int new_char;
                new_char = text.charAt(i) + code.charAt(number_of_related_code - 1) - 'a';
//                    System.out.println("new char : " + new_char);

                if ('z' >= new_char) {
                    result += (char)new_char /*Character.toString(new_char)*/;
//                        System.out.println("result : " + result);
                } else {
                    new_char += 'a' - 'z' - 1;
                    result += (char)new_char /*Character.toString(new_char)*/;
//                        System.out.println("result : " + result);

                }
//                }
//                else
//                {
//                    result+=text.charAt(i);
//                }
            }
            if (i<text.length())
                result+=text.charAt(i);

            wordEnd++;
            wordStart = wordEnd;
//            System.out.println("wordEnd : "+wordEnd);
//            System.out.println("wordStart : "+wordStart);
        }
        return result;
    }

}
