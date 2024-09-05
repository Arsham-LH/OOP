import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Scanner;

public class AdminInputProcessor {
    private AdminManager adminManager = new AdminManager();
    private Scanner scanner = new Scanner(System.in);

    public AdminInputProcessor(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    private void processAddGood(String[] split) {

        if (split.length != 5)
            System.out.println("Error adding good . Please check your input.");

        else if (Integer.parseInt(split[4]) > Integer.parseInt(split[3]))
            System.out.println("Error . Sell price is less than buy price !");

        else {
            boolean isNewGood = true;
            for (Good good : adminManager.getGoods()) {
                if (split[1].equals(good.getName())) {
                    System.out.println("Error . This item has already exists in the market !");
                    isNewGood = false;
                    break;
                }
            }
            if (isNewGood) {
                Random random = new Random();
                int randomID = random.nextInt(90000) + 10000;
                boolean existsID = true;
                while (existsID){
                    existsID = false;
                    for (Good good : adminManager.getGoods()) {
                        if (good.getID() == randomID) {
                            existsID = true;
                            randomID = random.nextInt(90000) + 10000;
                        }
                    }
                }
                Good result = new Good(split[1], randomID, Integer.parseInt(split[4]),
                        Integer.parseInt(split[3]), Integer.parseInt(split[2]) , 0);
                adminManager.addGood(result);
                System.out.println("Add was successful -> good_ID = " + result.getID());
            }
        }
    }

    private void processRemoveGood(String goodID) {
        boolean existsGood = false;
        for (Good good : adminManager.getGoods()) {
            if (good.getID() == Integer.parseInt(goodID)) {
                adminManager.removeGood(good);
                existsGood = true;
                System.out.println("Remove was successful .");
                break;
            }
        }
        if (!existsGood)
            System.out.println("Error . There is no good with this name in the market .");
    }

    private void processEditGood(String[] split) {
        boolean isAvailable = false;
        boolean existsGood = false;
        boolean buySellError = false;
        for (Good good : adminManager.getGoods()) {
            if (good.getID() == Integer.parseInt(split[0])) {
                if (adminManager.editGood(good, split)) {
                    System.out.println("Error . Sell price is less than buy price !");
                    buySellError = true;
                }
                isAvailable = true;
                existsGood = true;
                break;
            }
        }
        if (!isAvailable){
            for (Good unavailableGood : adminManager.getUnavailableGoods()) {
                if (unavailableGood.getID() == Integer.parseInt(split[0])) {
                    if (adminManager.editGood(unavailableGood, split)) {
                        System.out.println("Error . Sell price is less than buy price !");
                        buySellError = true;
                    }
                    existsGood = true;
                    break;
                }
            }
        }
        if (!existsGood)
            System.out.println("Error . There is no good with this ID in the market .");
        if (existsGood && !buySellError)
            System.out.println("Edit was successful .");
    }

    private void processGoodProfitCal(int goodID) {
        long result = adminManager.goodProfitCal(goodID);
        if (result == -1)
            System.out.println("Error . There is no good with this ID in the market .");
        else
            System.out.println(result + " IRR");
    }

    private void processTotalProfitCal(){
        System.out.println(adminManager.totalProfitCal() + " IRR");
    }

    private void processShowGoods(ArrayList<Good> goods){
        final int minNameSpace = 20;
        final int minCountSpace = 20;
        final int minPriceSpace = 20;
        int nameSpace = minNameSpace;
        int countSpace = minCountSpace;
        int priceSpace = minPriceSpace;
        ArrayList<Good> result = new ArrayList<>(adminManager.sortedGoods(goods));
        if (adminManager.maxGoodsNameSize(goods) > minNameSpace)
            nameSpace = adminManager.maxGoodsNameSize(goods) + 2;

        if (adminManager.maxGoodsCountDigitsSize(goods) > minCountSpace)
            countSpace = adminManager.maxGoodsCountDigitsSize(goods) + 2;

        if (adminManager.maxGoodsSellPriceDigitsSize(goods) > minPriceSpace)
            priceSpace = adminManager.maxGoodsSellPriceDigitsSize(goods) + 2;

        
        
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

    private void processCheckoutOrder(String orderID){
//        boolean lackError = false;
//        for (Good good : adminManager.getGoods()) {
//            if (good.getID() == Integer.parseInt(commands[0])){
//                if (good.getRemainingCount() < Integer.parseInt(commands[1])){
//                    lackError = true;
//                    System.out.println("Error: Order not successful (There is not enough item counts in th market)");
//                }
//            }
//        }
        try {
            boolean existsUnchecked = false;
            boolean lackError = false;
            for (Order order : adminManager.getOrders()) {
                if (order.orderID.equals(orderID)) {
                    existsUnchecked = true;
                    if (adminManager.checkoutOrder(order)) {
                        lackError = true;
                    }
                    break;
                }
            }
            if (lackError)
                System.out.println("Error: Order not successful (There is not enough item counts in the market)");
            else {
                if (!existsUnchecked)
                    System.out.println("Error . there is no unchecked order with this ID .");
                else
                System.out.println("Order checked out successfully !");
            }
        } catch (ConcurrentModificationException e){}
    }

    private void processLoadObjects(String [] objectsNames){
        for (int i = 0; i < objectsNames.length; i++) {
            adminManager.loadObjects(objectsNames[i]);
        }
    }

    private void processShowOrders() {
        System.out.println(adminManager.printString(adminManager.getOrders()));
    }

    private void processShowCheckedOrders() {
        System.out.println(adminManager.printString(adminManager.getCheckedOrders()));
    }

    private void processShowGoodID(String substring) {
        boolean isAvailable = false;
        boolean existsGood = false;
        for (Good good : adminManager.getGoods()) {
            if (good.getName().equals(substring)) {
                System.out.println("This good's ID is " + good.getID());
                existsGood = true;
                isAvailable = true;
                break;
            }
        }
        if (!isAvailable){
            for (Good unavailableGood : adminManager.getUnavailableGoods()) {
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

    private void processShowSellGoodDetails(String goodID) {
        for ( int i=0 ; i < goodID.length() ; i++){
            if (goodID.charAt(i) > '9' || goodID.charAt(i) < '0'){
                System.out.println("Error . invalid good ID .");
                return;
            }
        }
        boolean isAvailable = false;
        boolean existsGood = false;
        for (Good good : adminManager.getGoods()) {
            if (good.getID() == Integer.parseInt(goodID)){
                existsGood = true;
                isAvailable = true;
                processPrintSellDetails(good);
                break;
            }
        }
        if (!isAvailable){
            for (Good unavailableGood : adminManager.getUnavailableGoods()) {
                if (unavailableGood.getID() == Integer.parseInt(goodID)){
                    existsGood = true;
                    processPrintSellDetails(unavailableGood);
                    break;
                }
            }
        }
        if (!existsGood)
            System.out.println("Error. There is no good with this name in the market.");
    }

    private void processPrintSellDetails(Good good) {
        int totalOrders = adminManager.thisGoodOrdersCount(good);
        int totalSellCount = good.getSoldCount();
        String goodName = good.getName();
        long totalSell = good.getSoldPrice();
        long totalProfit = good.getProfit();
        System.out.println(totalOrders + " order(s), " + totalSellCount + " " + goodName + "(s), " + totalSell + " IRR sell, "
                + totalProfit + " IRR profit");
    }

    private void processShowGoodName(String substring) {
        boolean exists = false;
        for (Good good : adminManager.getGoods()) {
            if (good.getID() == Integer.parseInt(substring)) {
                exists = true;
                System.out.println("This good's name is " + good.getName());
                break;
            }
        }
        if (!exists)
            System.out.println("There is no good with this ID .");
    }

    private void processShowTotalSellDetails(){
        int totalOrders = adminManager.getCheckedOrders().size();

        String namesAndSellsCount = "";
        long totalSell = 0;
        long totalProfit =0;
        for (Good good : adminManager.getGoods()) {
            namesAndSellsCount += ((good.getSoldCount() != 0)?(good.getSoldCount() + " " + good.getName() + "(s), "):"");
            totalSell += good.getSoldPrice();
            totalProfit += good.getProfit();
        }
        for (Good unavailableGood : adminManager.getUnavailableGoods()) {
            namesAndSellsCount += ((unavailableGood.getSoldCount()!=0)?(unavailableGood.getSoldCount() + " " + unavailableGood.getName() + "(s), "):"");
            totalSell += unavailableGood.getSoldPrice();
            totalProfit += unavailableGood.getProfit();
        }
        System.out.println(totalOrders + " order(s), " +
                namesAndSellsCount + totalSell + " IRR sell, " + totalProfit + " IRR profit");
    }


//    private void processSaveObjects(String [] objectsNames){
//        for (int i = 0; i < objectsNames.length; i++) {
//            adminManager.saveObjects(objectsNames[i]);
//        }
//    }

//    private void processShowCheckedOrders(){
////        adminManager.printString(adminManager.checkedOrders());
//    }


    public void run() {
        processLoadObjects(new String[]{"goods" , "unavailableGoods" , "orders" , "checkedOrders"});
        String input;
        System.out.println("Enter a command :\n");
        input = scanner.nextLine();
        while (!input.equals("end")) {
            if (input.startsWith("add ")) {
                processAddGood(input.split(" -[a-z][a-z]? "));
            } else if (input.startsWith("remove -c ")) {
                processRemoveGood(input.substring(10));
            } else if (input.startsWith("edit ")) {
                processEditGood(input.substring(5).split(" -"));
            }else if (input.startsWith("calc -p -c ")){
                processGoodProfitCal(Integer.parseInt(input.substring(11)));
            }else if (input.equals("calc -p")){
                processTotalProfitCal();
            }else if (input.equals("ls -r")){
                ArrayList<Good> result = (ArrayList<Good>) adminManager.getGoods().clone();
                for (Good unavailableGood : adminManager.getUnavailableGoods()) {
                    result.add(unavailableGood);
                }
                processShowGoods(result);
            }else if (input.equals("ls -i")){
                processShowGoods(adminManager.getGoods());
            }else if (input.equals("ls -n")){
                processShowGoods(adminManager.getUnavailableGoods());
            }else if (input.startsWith("checkout ")){
                processCheckoutOrder(input.substring(9));
            }else if (input.startsWith("goodID ")){
                processShowGoodID(input.substring(7));
            }else if(input.startsWith("goodName ")){
                processShowGoodName(input.substring(9));
            }else if(input.equals("ls -ho")){
                processShowCheckedOrders();
            }else if(input.equals("ls -o")){
                processShowOrders();
            }else if (input.startsWith("calc -s -c ")){
                processShowSellGoodDetails(input.substring(11));
            }else if (input.equals("calc -s")){
                processShowTotalSellDetails();
            }else if (input.startsWith("show stats of ")) {
                boolean existsGood = false;
                String result = new String(input.substring(14));
                for (Good good : adminManager.getGoods()) {
                    if (result.equals(good.getName())) {
                        adminManager.showGoodStats(good);
                        existsGood = true;
                        break;
                    }
                }
                if (existsGood == false)
                    System.out.println("There is no such good in the market !");
            }
            else{
                System.out.println("Error . Invalid command.");
            }



            System.out.println("Enter a command :");
            input = scanner.nextLine();
        }
    }

}
