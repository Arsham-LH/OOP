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
//    private ArrayList<String> usersID;

    public AdminManager() {
        this.goods = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.checkedOrders = new ArrayList<>();
        this.unavailableGoods = new ArrayList<>();
//        this.usersID = new ArrayList<>();
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
        good.setRemainingCount(0);
        unavailableGoods.add(good);
        goods.remove(good);
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

    public void saveGoods(){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String goodsText = gson.toJson(goods);
        System.out.println(goodsText);
        File help = new File("");
        File goodsFile = new File(help.getAbsolutePath()+"\\Information\\goods.json");
        try {
            if(!goodsFile.exists()) goodsFile.createNewFile();
            FileWriter goodsWriter = new FileWriter(goodsFile);
            goodsWriter.write(goodsText);
            goodsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            }
//            System.out.println("json text : "+jsonText);
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

//            if(objectName.equals("goods"))
//                arrayListOfGoodObject = new TypeToken<ArrayList<Good>>(){}.getType();
//            else if (objectName.equals("orders"))
//                arrayListOfGoodObject = new TypeToken<ArrayList<Order>>(){}.getType();
//            else if (objectName.equals("unavailableGoods"))
//                arrayListOfGoodObject = new TypeToken<ArrayList<Good>>(){}.getType();
//            else
//                arrayListOfGoodObject = new TypeToken<ArrayList<Order>>(){}.getType();
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


    public boolean checkoutOrder(Order order){
        boolean lackError = false;
        try {
            for (Good good : goods) {
                if (good.getID() == order.goodID) {
                    if (good.getRemainingCount() < order.goodCount) {
                        lackError = true;
                        return lackError;
                    }
                    good.setRemainingCount(good.getRemainingCount() - order.goodCount);
                    good.setSoldCount(good.getSoldCount()+order.goodCount);
                    good.setSoldPrice(good.getSoldPrice() + order.goodCount * good.getSellPrice());
                    good.setProfit(good.getProfit() + order.goodCount * good.profitPerCount());
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
        } catch (ConcurrentModificationException e){
            System.out.println("ConcurrentModificationException");
        }
        return lackError;
    }

    public <T> String printString(ArrayList<T> arrayList){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        String result = gson.toJson(arrayList);
        return result;
    }












    public void showGoodStats(Good good){
        System.out.println("Good name : "+good.getName()+"\nGood ID : "+good.getID()+"\nGood count : "+good.getRemainingCount()
        +"\nGood buy price : "+good.getBuyPrice()+"\nGood sell price : "+good.getSellPrice()+"\nGood profit per count : "+good.profitPerCount());
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
}
