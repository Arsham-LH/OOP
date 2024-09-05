import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cellar {
    private final ArrayList<Good> goods ;
    private ArrayList<Animal> animals;
    private final int capacity ;
    private int occupiedCapacity;
    private User user;

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public Cellar(int capacity,User user) {
        this.capacity = capacity;
        this.user = user;
        occupiedCapacity=0;
        goods= new ArrayList<>();
        goods.add(new Good("egg",15,0));
        goods.add(new Good("feather",25,0));
        goods.add(new Good("milk",25,0));
        goods.add(new Good("flour",40,0));
        goods.add(new Good("cloth",50,0));
        goods.add(new Good("p_milk",60,0));
        goods.add(new Good("bread",80,0));
        goods.add(new Good("shirt",100,0));
        goods.add(new Good("iceCream",120,0));
        goods.add(new Good("lion",300,0));
        goods.add(new Good("bear",400,0));
        goods.add(new Good("tiger",500,0));
        animals= new ArrayList<>();
    }
    public void addGood (Good good)
    {
        for (Good good0: goods) {
            if (good.getName().equals(good0.getName()))
            {
                if (capacity<good.getCount()*good.getSpaceRequired()+occupiedCapacity)
                {
                    System.out.println("cellar is full");
                    writeLogFile("Error","cellar is full",user);
                }
                else
                {
                    good0.setCount(good0.getCount()+good.getCount());
                    occupiedCapacity+=good.getCount()*good.getSpaceRequired();
                }
            }
        }
    }
    public boolean addTrue (Good good)
    {
        for (Good good0: goods) {
            if (good.getName().equals(good0.getName()))
            {
                if (capacity<good.getCount()*good.getSpaceRequired()+occupiedCapacity)
                    return false;
                else
                {
                    return true;
                }
            }
        }
        return false;
    }
    public Good goodSearcher (String name)
    {
        for (int i = 0 ; i < goods.size() ; i++)
        {
            if (goods.get(i).getName().equals(name))
                return goods.get(i);
        }
        return null;
    }
    public void cellarToTruck(String nameOfGood,Truck truck)
    {
        Good good = goodSearcher(nameOfGood);
        if (good==null)
        {
            System.out.println("Error. Good does not exist.");
            writeLogFile("Error","good does not exist",user);
        }
        else if (truck.getTravel()==0)
        {
            {
                if (good.getCount() == 0){
                    System.out.println("There is no good with this name in cellar!");
                    writeLogFile("Error", "Loading unavailable good" , user);
                    return;
                }
                System.out.println("Truck loaded successfully!");
                truck.addGood(good);
                good.setCount(0);
            }
        }
        else
        {
            System.out.println("Truck is in travel...");
        }
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }
    public void writeLogFile(String eventType , String event,User user){
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}