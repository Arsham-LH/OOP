import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;

public class CustomerManager {
    private ArrayList<Good> goods;
    private ArrayList<Good> unavailableGoods;
    private ArrayList<Order> orders;
    private ArrayList<Order> checkedOrders;
    private ArrayList<User> users;
    private User user;

    public CustomerManager() {
        this.goods = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.unavailableGoods = new ArrayList<>();
        this.users = new ArrayList<>();
        this.checkedOrders = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        goods.add(good);
    }

    public void addUser(String userName, String password) {
        User result = new User(userName, password, 1000000);
        users.add(result);
        saveObjects("users");
    }

    public void setUser(String userName, String password) {
        for (User user1 : users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
                user = user1;
                break;
            }
        }
    }

    public void removeGood(Good good){
        good.setRemainingCount(0);
        unavailableGoods.add(good);
        goods.remove(good);
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
    public void addOrder(int goodID, int count){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for (Good good : goods) {
            if(good.getID() == goodID) {
                Order newOrder = new Order(good.getID(), count,
                        user.getUserName(), dateTimeFormatter.format(now), false, good.getBuyPrice(), good.getSellPrice());
                orders.add(newOrder);
                user.setMoney(user.getMoney()-(getGoodByID(newOrder.goodID).getSellPrice()*newOrder.goodCount));
                good.setRemainingCount(good.getRemainingCount()-newOrder.goodCount);
                if (good.getRemainingCount()<=0){
                    removeGood(good);
                }
                break;
            }
        }
        saveObjects("orders");
        saveObjects("users");
        saveObjects("goods");
        saveObjects("unavailableGoods");
    }

    public void cancelOrder(int goodID){
        for (Order order : orders) {
            if (order.orderID.equals(user.getUserName()) && order.goodID==goodID){
                user.setMoney(user.getMoney() + (getGoodByID(order.goodID).getSellPrice()*order.goodCount));
                Good canceledOrderGood = getGoodByID(goodID);
                if (canceledOrderGood.getRemainingCount() == 0){
                    Good availabledGood = new Good(canceledOrderGood.getName(),canceledOrderGood.getID(),
                            canceledOrderGood.getBuyPrice(),canceledOrderGood.getSellPrice(),
                            canceledOrderGood.getRemainingCount()+order.goodCount,canceledOrderGood.getSoldCount());
                    goods.add(availabledGood);
                    unavailableGoods.remove(canceledOrderGood);
                    saveObjects("unavailableGoods");
                }else {
                    canceledOrderGood.setRemainingCount(canceledOrderGood.getRemainingCount() + order.goodCount);
                }
                orders.remove(order);
                break;
            }
        }
        saveObjects("orders");
        saveObjects("users");
        saveObjects("goods");
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
                case "users" :
                    if (users != null)
                        jsonText = gson.toJson(users);
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
            String jsonString = new String("");
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
                case "users" :
                    arrayListOfGoodObject = new TypeToken<ArrayList<User>>(){}.getType();
                    users = gson.fromJson(jsonString, arrayListOfGoodObject);
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

    public boolean existsUserName(String userName){
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectPass(String userName , String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName)){
                if(user.getPassword().equals(password))
                    return true;
                return false;
            }
        }
        return false;
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

    public Order firstMatchedOrderWithGood(Good good){
        if (orders.size() == 0){
            System.out.println(orders.size());
            return null;
        }
        for (Order order : orders) {
            if (order.orderID.equals(user.getUserName()) && order.goodID == good.getID()){
                return order;
            }
        }
        return null;
    }

    public void showGoodStats(Good good){
        System.out.println("Good name : "+good.getName()+"\nGood ID : "+good.getID()+"\nGood count : "+good.getRemainingCount()
                +"\nGood buy price : "+good.getBuyPrice()+"\nGood sell price : "+good.getSellPrice()+"\nGood profit per count : "+good.profitPerCount());
    }
}
