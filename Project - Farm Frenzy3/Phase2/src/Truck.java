import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Truck {
    private final ArrayList<Good> goods;
    private final int capacity;
    private int occupiedCapacity;
    private String[] animals = {"", "", ""};
    private int travel;
    private int sellMoney;
    private User user;

    public Truck(int capacity, User user) {
        this.user = user;
        this.capacity = capacity;
        occupiedCapacity = 0;
        goods = new ArrayList<>();
        travel = 0;
        goods.add(new Good("egg", 15, 0));
        goods.add(new Good("feather", 20, 0));
        goods.add(new Good("milk", 25, 0));
        goods.add(new Good("farina", 40, 0));
        goods.add(new Good("cloth", 50, 0));
        goods.add(new Good("milk2", 60, 0));
        goods.add(new Good("bread", 80, 0));
        goods.add(new Good("shirt", 100, 0));
        goods.add(new Good("iceCream", 120, 0));
        goods.add(new Good("lion", 300, 0));
        goods.add(new Good("bear", 400, 0));
        goods.add(new Good("tiger", 500, 0));
        animals[1] += "turkey" + "@" + "0";
        animals[2] = "buffalo" + "@" + "0";
        animals[0] += "chicken" + "@" + "0";
    }

    public int getAnimalsV() {
        if (travel == 0) {
            return getAnimalV("chicken") + getAnimalV("turkey") + getAnimalV("buffalo");
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
            return -1;
        }
    }

    public int getAnimalV(String name) {
        if (travel == 0) {
            if (name.equals("buffalo")) {
                String[] split = animals[2].split("@");
                int number = Integer.parseInt(split[1]);
                return number * 5;
            } else if (name.equals("chicken")) {
                String[] split = animals[0].split("@");
                int number = Integer.parseInt(split[1]);
                return number * 2;
            } else if (name.equals("turkey")) {
                String[] split = animals[1].split("@");
                int number = Integer.parseInt(split[1]);
                return number * 3;
            }
            writeLogFile("Error", "animal is not exist", user);
            return 0;
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
            return -1;
        }
    }

    public void setAnimalNumber(String name, int num) {
        if (travel == 0) {
            if (name.equals("buffalo")) {
                String[] split = animals[2].split("@");
                split[1] = String.valueOf(num);
                animals[2] = split[0] + "@" + split[1];
            } else if (name.equals("chicken")) {
                String[] split = animals[0].split("@");
                split[1] = String.valueOf(num);
                animals[0] = split[0] + "@" + split[1];
            } else if (name.equals("turkey")) {
                String[] split = animals[1].split("@");
                split[1] = String.valueOf(num);
                animals[1] = split[0] + "@" + split[1];
            }
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }

    public void addAnimal(String animalName) {
        if (travel == 0) {
            if (travel == 0) {
                if (animalName.equals("chicken")) {
                    if (2 + occupiedCapacity <= capacity) {
                        setAnimalNumber("chicken", getAnimalV("chicken") / 2 + 1);
                        occupiedCapacity += 2;
                    } else {
                        writeLogFile("Error", "not enough space in truck", user);
                        System.out.println("The truck does not have the required space");
                    }
                } else if (animalName.equals("buffalo")) {
                    if (5 + occupiedCapacity <= capacity) {
                        setAnimalNumber("buffalo", getAnimalsV() / 5 + 1);
                        occupiedCapacity += 5;
                    } else {
                        writeLogFile("Error", "not enough space in truck", user);
                        System.out.println("The truck does not have the required space");
                    }
                } else if (animalName.equals("turkey")) {
                    if (3 + occupiedCapacity <= capacity) {
                        setAnimalNumber("turkey", getAnimalsV() / 3 + 1);
                        occupiedCapacity += 3;
                    } else {
                        writeLogFile("Error", "not enough space in truck", user);
                        System.out.println("The truck does not have the required space");
                    }
                } else {
                    System.out.println("animal is not exist");
                    writeLogFile("Error", "animal is not exist", user);
                }
            }
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }

    public void addGood(Good good) {
        if (travel == 0) {
            for (Good good0 : goods) {
                if (good.getName().equals(good0.getName())) {
                    if (capacity < good.getCount() * good.getSpaceRequired() + occupiedCapacity)
                        System.out.println("cellar is full");
                    else {
                        good0.setCount(good0.getCount() + good.getCount());
                        occupiedCapacity += good.getCount() * good.getSpaceRequired();
                    }
                }
            }
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }

    public void removeAnimal(String name) {
        if (travel == 0) {
            if (name.equals("chicken")) {
                if (getAnimalV("chicken") / 2 > 0) {
                    setAnimalNumber("chicken", getAnimalV("chicken") / 2 - 1);
                    occupiedCapacity -= 2;
                }
            } else if (name.equals("buffalo")) {
                if (getAnimalV("buffalo") / 5 > 0) {
                    setAnimalNumber("buffalo", getAnimalV("buffalo") / 5 - 1);
                    occupiedCapacity -= 5;
                }
            } else if (name.equals("turkey")) {
                if (getAnimalV("turkey") / 3 > 0) {
                    setAnimalNumber("turkey", getAnimalV("turkey") / 3 - 1);
                    occupiedCapacity -= 3;
                }
            } else {
                writeLogFile("Error", "animal not found", user);
            }
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }
    public void truckToCellar(String nameOfGood, Cellar cellar) {
        if (travel == 0) {
            Good good = goodSearcher(nameOfGood);
            if (good == null) {
                writeLogFile("Error", "good not found", user);
                System.out.println("good not found");
            } else {
                if (good.getCount() == 0)
                    System.out.println("you have not enough good");
                else {
                    cellar.addGood(good);
                    good.setCount(0);
                }
            }
        }
        else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }

    public Good goodSearcher(String name) {
        if (travel == 0) {
            for (int i = 0; i < goods.size(); i++) {
                if (goods.get(i).getName().equals(name))
                    return goods.get(i);
            }
            return null;
        }else{
            writeLogFile("Error", "Truck in travel", user);
            return null;
        }
    }

    private int sellPriceOfTypeOfAnimal(String name) {
        if (travel == 0) {
            if (name.equals("chicken")) {
                if (getAnimalV("chicken") / 2 > 0) {
                    int num = getAnimalV("chicken") / 2;
                    setAnimalNumber("chicken", 0);
                    occupiedCapacity -= 2;
                    return num * 100;
                }
            } else if (name.equals("buffalo")) {
                int num = getAnimalV("buffalo") / 5;
                setAnimalNumber("buffalo", 0);
                occupiedCapacity -= 5;
                return num * 400;
            } else if (name.equals("turkey")) {
                int num = getAnimalV("chicken") / 3;
                setAnimalNumber("chicken", 0);
                occupiedCapacity -= 3;
                return num * 300;
            }
            return 0;
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
            return 0;
        }
    }

    public void sell() {
        if (travel == 0) {
            sellMoney = 0;
            for (int i = 0; i < goods.size(); i++) {
                sellMoney += goods.get(i).getSellPrice() * goods.get(i).getCount();
                goods.get(i).setCount(0);
            }
            sellMoney += (sellPriceOfTypeOfAnimal("chicken") + sellPriceOfTypeOfAnimal("buffalo") + sellPriceOfTypeOfAnimal("turkey"));
            occupiedCapacity = 0;
        }else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
        }
    }

    public int sellPrice() {
        if (travel == 0)
        {
            travel=10;
            return sellMoney;
        }
        else{
            System.out.println("Truck is in travel!");
            writeLogFile("Error", "Truck in travel", user);
            return 0;
        }
    }
    public void writeLogFile(String eventType, String event, User user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        File help = new File("");
        File logFile = new File (help.getAbsolutePath()+"\\Information\\log.txt");
        try {
            String newLogText = new String("");
            if (logFile.exists()){
                FileReader logFileReader = new FileReader(logFile);
                BufferedReader logBufferedReader = new BufferedReader(logFileReader);
                String line1 = logBufferedReader.readLine();
                String line2 = logBufferedReader.readLine();
                String line3 = logBufferedReader.readLine();

                String oldBody = new String("");
                String linesReader = logBufferedReader.readLine();
                while (linesReader != null){
                    oldBody += linesReader + "\n";
                    linesReader = logBufferedReader.readLine();
                }
                newLogText += line1;
                newLogText += "\nUserName: "+((user == null)?"null":user.getUserName());
                newLogText += "\nLast edit date & time: "+dtf.format(now);
                newLogText += "\n" + oldBody;
            }else{
                logFile.createNewFile();
                newLogText += "Created file date: "+dtf.format(now);
                newLogText += "\nUserName: "+((user == null)?"null":user.getUserName());
                newLogText += "\nLast edit date & time: "+dtf.format(now);
            }
            newLogText += "\n[" + eventType + "], " + dtf.format(now) + ", " + event + "\n";

            FileWriter logFileWriter = new FileWriter(logFile);
            logFileWriter.write(newLogText);
            logFileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public int getTravel() {
        return travel;
    }

    public void setTravel(int travel) {
        this.travel = travel;
    }

    public int getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(int sellMoney) {
        this.sellMoney = sellMoney;
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
}