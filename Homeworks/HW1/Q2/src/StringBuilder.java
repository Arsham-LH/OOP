import java.util.HashMap;
import java.util.Scanner;

class StringBuilderClass {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        StringBuilder stringbuilder = new StringBuilder("I am a doctor");
//        int PointerIndex = stringbuilder.indexOf("a doctor");
//        stringbuilder.ins
        HashMap<String, Integer> hashmap= new HashMap<>();
        hashmap.put("Ali" , 20);
        hashmap.put("Mamad" , 19);
        hashmap.put("Reza" , 15);
        hashmap.put("Gholi" , 35);

        System.out.println(hashmap.values());
    }
}
