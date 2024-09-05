import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

public class AdminManager {
    private ArrayList<Good> goods;
    private ArrayList<Good> unavailableGoods;
    private ArrayList<Order> orders;
    private ArrayList<Order> checkedOrders;
    private ArrayList<User> users;
    private int totalSalesCount = 0;
    private long totalSalePrice = 0;
    private long totalProfit = 0;


    public AdminManager() {
        this.goods = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.checkedOrders = new ArrayList<>();
        this.unavailableGoods = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public long getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(long totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public int getTotalSalesCount() {
        return totalSalesCount;
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Order> getCheckedOrders() {
        return checkedOrders;
    }

    public void setCheckedOrders(ArrayList<Order> checkedOrders) {
        this.checkedOrders = checkedOrders;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setTotalSalesCount(int totalSalesCount) {
        this.totalSalesCount = totalSalesCount;
    }

    public void setTotalProfit(long totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Good> getUnavailableGoods() { return unavailableGoods; }

    public void setUnavailableGoods(ArrayList<Good> unavailableGoods) { this.unavailableGoods = unavailableGoods; }

    public void addGood(Good good){
        boolean isNewGood = true;
        for (Good unavailableGood : unavailableGoods) {
            if (unavailableGood.getName().equals(good.getName())){
                goods.add(new Good(good.getName() , unavailableGood.getID() ,
                        good.getBuyPrice() , good.getSellPrice() , good.getRemainingCount() , good.getSoldCount()));
                unavailableGoods.remove(unavailableGood);
                isNewGood = false;
                break;
            }
        }
        if (isNewGood)
            goods.add(good);
        saveObjects("goods");
        saveObjects("unavailableGoods");
    }

    public void removeGood(Good good){
        for (Good good1 : goods) {
            if (good1.getName().equals(good.getName())){
                goods.remove(good1);
                break;
            }
        }
        for (Good good1 : unavailableGoods) {
            if (good1.getName().equals(good.getName())){
                unavailableGoods.remove(good1);
                break;
            }
        }

        saveObjects("goods");
        saveObjects("unavailableGoods");
    }

    public void editName(Good good, String newName){
        for (Good good1 : goods) {
            if(good1.getName().equals(good.getName())){
                good1.setName(newName);
                saveObjects("goods");
                return;
            }
        }
        for (Good good1 : unavailableGoods) {
            if(good1.getName().equals(good.getName())){
                good1.setName(newName);
                saveObjects("unavailableGoods");
                return;
            }
        }
    }

    public void editSellPrice(Good good, int newSellPrice){
        for (Good good1 : goods) {
            if(good1.getName().equals(good.getName())){
                good1.setSellPrice(newSellPrice);
                saveObjects("goods");
                return;
            }
        }
        for (Good good1 : unavailableGoods) {
            if(good1.getName().equals(good.getName())){
                good1.setSellPrice(newSellPrice);
                saveObjects("unavailableGoods");
                return;
            }
        }
    }

    public void editBuyPrice(Good good, int newBuyPrice){
        for (Good good1 : goods) {
            if(good1.getName().equals(good.getName())){
                good1.setBuyPrice(newBuyPrice);
                saveObjects("goods");
                return;
            }
        }
        for (Good good1 : unavailableGoods) {
            if(good1.getName().equals(good.getName())){
                good1.setBuyPrice(newBuyPrice);
                saveObjects("unavailableGoods");
                return;
            }
        }
    }

    public void editCount(Good good, int newCount){
        for (Good good1 : goods) {
            if(good1.getName().equals(good.getName())){
                if (newCount == 0) {
                    Good unavailabledGood = new Good(good1.getName(), good1.getID(), good1.getBuyPrice(),
                            good1.getSellPrice(), newCount, good1.getSoldCount());
                    goods.remove(good1);
                    unavailableGoods.add(unavailabledGood);
                }else {
                    good1.setRemainingCount(newCount);
                }
                return;
            }
        }
        for (Good good1 : unavailableGoods) {
            if(good1.getName().equals(good.getName())){
                if (newCount > 0) {
                    Good availabledGood = new Good(good1.getName(), good1.getID(), good1.getBuyPrice(),
                            good1.getSellPrice(), newCount, good1.getSoldCount());
                    unavailableGoods.remove(good1);
                    goods.add(availabledGood);
                }else {
                    good1.setRemainingCount(newCount);
                }
                return;
            }
        }
        saveObjects("goods");
        saveObjects("unavailableGoods");
    }

    public boolean editGood(Good good , String [] split){

        boolean error = false;
        String previousName = good.getName();
        int previousCount = good.getRemainingCount();
        int previousSellPrice = good.getSellPrice();
        int previousBuyPrice = good.getBuyPrice();

        for (int i = 0; i < split.length; i++) {
            if (split[i].startsWith("n "))
                good.setName(split[i].substring(2));
            else if (split[i].startsWith("c "))
                good.setRemainingCount(Integer.parseInt(split[i].substring(2)));
            else if (split[i].startsWith("sp ")) {
                good.setSellPrice(Integer.parseInt(split[i].substring(3)));
            }
            else if (split[i].startsWith("bp ")) {
                good.setBuyPrice(Integer.parseInt(split[i].substring(3)));
            }
        }
        for (Good unavailableGood : unavailableGoods) {
            if (unavailableGood.getID() == good.getID()){
                if (good.getRemainingCount() > 0 ){
                    goods.add(good);
                    unavailableGoods.remove(good);
                    break;
                }
            }
        }
        if (good.getSellPrice() < good.getBuyPrice()) {
            error = true;
            good.setName(previousName);
            good.setRemainingCount(previousCount);
            good.setBuyPrice(previousBuyPrice);
            good.setSellPrice(previousSellPrice);
        }else {
            saveObjects("goods");
            saveObjects("unavailableGoods");
        }

        return error;
    }

    public long goodProfitCal(int goodID) {
        for (Good good : goods) {
            if (good.getID() == goodID)
                return (good.getProfit());
        }
        for (Good unavailableGood : unavailableGoods) {
            if (unavailableGood.getID() == goodID)
                return (unavailableGood.getProfit());
        }
        return -1;
    }

    public long totalProfitCal(){
        long result = 0;
        for (Good good : goods) {
            result += good.getProfit();
        }
        for (Good unavailableGood : unavailableGoods) {
            result += (unavailableGood.getProfit());
        }
        return result;
    }


    public ArrayList<Good> sortedGoods(ArrayList<Good> unsortedGoods){
        ArrayList<String> sortedNames = new ArrayList<>();
        for (Good unsortedGood : unsortedGoods) {
            sortedNames.add(unsortedGood.getName());
        }
        Collections.sort(sortedNames);
        ArrayList<Good> result = new ArrayList<>();
        int i = 0;
        while (i < sortedNames.size()) {
            for (Good unsortedGood : unsortedGoods) {
                if (sortedNames.get(i) == unsortedGood.getName())
                    result.add(unsortedGood);
            }
            i++;
        }
        return result;
    }

    public int maxGoodsNameSize(ArrayList<Good> goods){
        int result = 0;
        for (Good good : goods)
            if (good.getName().length() > result)
                result = good.getName().length();
        return result;
    }

    public  int maxGoodsCountDigitsSize(ArrayList<Good> goods){
        int counter = 0 ;
        for (Good good : goods) {
            if (good.getRemainingCount() > counter)
                counter = good.getRemainingCount();
        }
        if (counter == 0)
            return 1;
        int result = 0;
        while (counter > 0){
            counter/=10;
            result++;
        }
        return result;
    }

    public int maxGoodsSellPriceDigitsSize(ArrayList<Good> goods){
        int counter = 0;
        for (Good good : goods) {
            if (good.getSellPrice() > counter)
                counter = good.getSellPrice();
        }
        if (counter == 0)
            return 1;
        int result = 0;
        while (counter > 0){
            counter/=10;
            result++;
        }
        return result;
    }

    public void saveObjects(String objectName){

        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        File help = new File("");
        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\"+objectName+".json");

        try {
            if (! jsonFile.exists())
                jsonFile.createNewFile();
            FileWriter fileWriter = new FileWriter(jsonFile);
            String jsonText = "";
            switch (objectName) {
                case "goods" :
                    if (goods != null)
                        jsonText = gson.toJson(goods);
                    break;
                case "unavailableGoods" :
                    if (unavailableGoods != null)
                        jsonText = gson.toJson(unavailableGoods);
                    break;
                case "orders":
                    if (orders != null)
                        jsonText = gson.toJson(orders);
                    break;
                case "checkedOrders" :
                    if (checkedOrders != null)
                        jsonText = gson.toJson(checkedOrders);
                    break;
                case "sales count":
                    jsonText = gson.toJson(totalSalesCount);
                    break;
                case "sales price":
                    jsonText = gson.toJson(totalSalePrice);
                    break;
                case "profit":
                    jsonText = gson.toJson(totalProfit);
                    break;
            }
            if (!jsonText.equals(""))
                fileWriter.write(jsonText);

            fileWriter.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }


    public void loadObjects(String objectName){
        File help = new File("");
        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\"+objectName+".json");

        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        try {
            FileReader fileReader = new FileReader(jsonFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            String jsonString = "";
            while (line != null){
                jsonString += line;
                line = bufferedReader.readLine();
            }
            Type arrayListOfGoodObject;

            switch (objectName){
                case "goods":
                    arrayListOfGoodObject = new TypeToken<ArrayList<Good>>(){}.getType();
                    goods = gson.fromJson(jsonString , arrayListOfGoodObject);
                    break;
                case "unavailableGoods":
                    arrayListOfGoodObject = new TypeToken<ArrayList<Good>>(){}.getType();
                    unavailableGoods = gson.fromJson(jsonString , arrayListOfGoodObject);
                    break;
                case "orders" :
                    arrayListOfGoodObject = new TypeToken<ArrayList<Order>>(){}.getType();
                    orders = gson.fromJson(jsonString , arrayListOfGoodObject);
                    break;
                case "checkedOrders" :
                    arrayListOfGoodObject = new TypeToken<ArrayList<Order>>(){}.getType();
                    checkedOrders = gson.fromJson(jsonString , arrayListOfGoodObject);
                    break;
                case "sales count":
                    totalSalesCount = gson.fromJson(jsonString, Integer.class);
                    break;
                case "sales price":
                    totalSalePrice = gson.fromJson(jsonString, Long.class);
                    break;
                case "profit":
                    totalProfit = gson.fromJson(jsonString, Long.class);
                    break;
                default:
                    arrayListOfGoodObject = new TypeToken<ArrayList<Good>>(){}.getType();
                    goods = gson.fromJson(jsonString , arrayListOfGoodObject);
                    break;
            }


            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {    }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }


    public void checkoutOrder(Order order){
        try {
            for (Good good : goods) {
                if (good.getID() == order.goodID) {
                    good.setSoldCount(good.getSoldCount()+order.goodCount);
                    good.setSoldPrice(good.getSoldPrice() + order.goodCount * order.orderTimeSellPrice);
                    good.setProfit(good.getProfit() + order.goodCount * (order.orderTimeSellPrice - order.orderTimeBuyPrice));
                    totalSalesCount += order.goodCount;
                    totalSalePrice += order.goodCount * order.orderTimeSellPrice;
                    totalProfit += order.goodCount * (order.orderTimeSellPrice - order.orderTimeBuyPrice);
                    if (good.getRemainingCount() == 0)
                        removeGood(good);
                    checkedOrders.add(order);
                    orders.remove(order);
                    break;
                }
            }
            saveObjects("goods");
            saveObjects("unavailableGoods");
            saveObjects("orders");
            saveObjects("checkedOrders");
            saveObjects("sales count");
            saveObjects("sales price");
            saveObjects("profit");
        } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
    }

    public <T> String printString(ArrayList<T> arrayList){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        String result = gson.toJson(arrayList);
        return result;
    }

    public int thisGoodOrdersCount(Good good) {
        int result = 0;
        try {
            for (Order checkedOrder : checkedOrders) {
                if (checkedOrder.goodID == good.getID()) {
                    result++;
                }
            }
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        return result;

    }

    public Good getGoodByID(int goodID){
        for (Good good : goods) {
            if (good.getID() == goodID){
                return good;
            }
        }
        for (Good unavailableGood : unavailableGoods) {
            if (unavailableGood.getID() == goodID){
                return unavailableGood;
            }
        }
        return null;
    }

}
