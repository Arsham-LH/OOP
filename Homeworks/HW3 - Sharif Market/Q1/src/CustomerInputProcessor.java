import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CustomerInputProcessor {
    private CustomerManager customerManager = new CustomerManager();
    private Scanner scanner = new Scanner(System.in);

    public CustomerInputProcessor(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    private void processShowGoods(ArrayList<Good> goods){
        final int minNameSpace = 20;
        final int minCountSpace = 20;
        final int minPriceSpace = 20;
        int nameSpace = minNameSpace;
        int countSpace = minCountSpace;
        int priceSpace = minPriceSpace;
        ArrayList<Good> result = new ArrayList<>(customerManager.sortedGoods(goods));
        if (customerManager.maxGoodsNameSize(goods) > minNameSpace)
            nameSpace = customerManager.maxGoodsNameSize(goods) + 2;

        if (customerManager.maxGoodsCountDigitsSize(goods) > minCountSpace)
            countSpace = customerManager.maxGoodsCountDigitsSize(goods) + 2;

        if (customerManager.maxGoodsSellPriceDigitsSize(goods) > minPriceSpace)
            priceSpace = customerManager.maxGoodsSellPriceDigitsSize(goods) + 2;



        System.out.printf("+");
        for (int i = 0; i < nameSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < countSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < priceSpace; i++)
            System.out.printf("-");
        System.out.println("+");

        System.out.printf("| Good name");
        for (int i = 0; i < (nameSpace - 10); i++)
            System.out.printf(" ");
        System.out.printf("| Inventory");
        for (int i = 0; i < (countSpace - 10); i++)
            System.out.printf(" ");
        System.out.printf("| Price(IRR)");
        for (int i = 0; i < (priceSpace - 11); i++)
            System.out.printf(" ");
        System.out.println("|");

        System.out.printf("+");
        for (int i = 0; i < nameSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < countSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < priceSpace; i++)
            System.out.printf("-");
        System.out.println("+");

        for (Good good : result) {

            System.out.printf("| " + good.getName());
            for (int i = 0; i < (nameSpace - (good.getName().length() + 1)); i++)
                System.out.printf(" ");
            System.out.printf("| " + good.getRemainingCount() + " kg/item(s)");

            int countDigits = 0;
            int countTmp = good.getRemainingCount();
            if (countTmp == 0)
                countDigits = 1;
            while (countTmp > 0){
                countTmp/= 10;
                countDigits++;
            }

            for (int i = 0; i < (countSpace - (countDigits + 12)); i++)
                System.out.printf(" ");
            System.out.printf("| " + good.getSellPrice());

            int priceDigits = 0;
            int priceTmp = good.getSellPrice();
            if (priceTmp == 0)
                priceDigits = 1;
            while (priceTmp > 0){
                priceTmp/= 10;
                priceDigits++;
            }

            for (int i = 0; i < (priceSpace - (priceDigits + 1)); i++)
                System.out.printf(" ");
            System.out.println("|");

        }

        System.out.printf("+");
        for (int i = 0; i < nameSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < countSpace; i++)
            System.out.printf("-");
        System.out.printf("+");
        for (int i = 0; i < priceSpace; i++)
            System.out.printf("-");
        System.out.println("+");

    }
    private void processAddOrder(String input , String userID){
        String commands[] = input.split(" -c ");
        if (commands.length != 2 || commands[1].equals("0")) {
            System.out.println("ERROR: order not successful");
            return;
        }
        else{
            for (int i= 0 ; i<commands[1].length() ; i++){
                if(commands[1].charAt(i) >'9' || commands[1].charAt(i) < '0'){
                    System.out.println("ERROR: order not successful");
                    return;
                }
            }
        }

        if(customerManager.addOrder(commands, userID))
            System.out.println("Error : There is no good with this ID in the market.");
        else
            System.out.println("Your order id is : " + userID);
    }

    private void processCancelOrder(String input){
        boolean existingError = true;
        String orderID = input.split(" -d ")[1];
        boolean error = false;
        if (input.split(" -d ").length != 2){
            System.out.println("Error deleting order ! Please check your input.");
            error = true;
        }else{
            for (Order order : customerManager.getOrders()) {
                if (order.orderID.equals(orderID)) {
                    existingError = false;
                    break;
                }
            }
            if (existingError)
                System.out.println("Error deleting order . There is no order with this ID .");
        }
        if (!error && !existingError){
            customerManager.cancelOrder(orderID);
            System.out.println("order "+orderID+" was deleted successfully!");
        }
    }

    private void processLoadObjects(String [] objectsNames){
        for (int i = 0; i < objectsNames.length; i++) {
            customerManager.loadObjects(objectsNames[i]);
        }
    }

    private void processSaveObjects(String [] objectsNames){
        for (int i = 0; i < objectsNames.length; i++) {
            customerManager.saveObjects(objectsNames[i]);
        }
    }

    private void processShowGoodID(String substring) {
        boolean isAvailable = false;
        boolean existsGood = false;
        for (Good good : customerManager.getGoods()) {
            if (good.getName().equals(substring)) {
                System.out.println("This good's ID is " + good.getID());
                existsGood = true;
                isAvailable = true;
                break;
            }
        }
        if (!isAvailable){
            for (Good unavailableGood : customerManager.getUnavailableGoods()) {
                if (unavailableGood.getName().equals(substring)){
                    existsGood = true;
                    System.out.println("this good's ID is " + unavailableGood.getID());
                    break;
                }
            }
        }
        if (!existsGood)
            System.out.println("There is no good with this name");
    }

    private void processShowGoodName(String substring) {
        boolean exists = false;
        for (Good good : customerManager.getGoods()) {
            if (good.getID() == Integer.parseInt(substring)) {
                exists = true;
                System.out.println("This good's name is " + good.getName());
                break;
            }
        }
        if (!exists)
            System.out.println("There is no good with this ID .");
    }








    public void run() {
        processLoadObjects(new String[]{"goods" , "unavailableGoods" , "orders" , "checkedOrders"});
        String userID = new String("");
        String input;
        System.out.print("Please enter your userID :\n");
        userID = scanner.nextLine();
        System.out.print("Enter a command :\n");
        input = scanner.nextLine();
        while (!input.equals("end")) {

            if(input.startsWith("login ")){
                userID = input.substring(6);
            }
            else if (input.equals("ls -r")){
                ArrayList<Good> result = (ArrayList<Good>) customerManager.getGoods().clone();
                for (Good unavailableGood : customerManager.getUnavailableGoods()) {
                    result.add(unavailableGood);
                }
                processShowGoods(result);
            }else if (input.equals("ls -i")){
                processShowGoods(customerManager.getGoods());
            }else if (input.equals("ls -n")){
                processShowGoods(customerManager.getUnavailableGoods());
            }else if (input.startsWith("order ") && (input.charAt(6) != '-')){
                processAddOrder(input.substring(6) , userID);
            }else if (input.startsWith("order -d ")){
                processCancelOrder(input);
            }
            else if(input.equals("logout")){
                run();
            }else if (input.startsWith("goodID ")){
                processShowGoodID(input.substring(7));
            }else if(input.startsWith("goodName ")){
                processShowGoodName(input.substring(9));
            }
            System.out.print("Enter a command :");
            input = scanner.nextLine();
        }
    }

}
