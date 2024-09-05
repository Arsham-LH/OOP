import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private int levelNumber;
    private int initialCoin;
    private ArrayList<Good> taskGoods;
    private ArrayList<DomesticAnimal> taskAnimals;
    private ArrayList<Integer> eachTaskAnimalCount;
    private int taskCoins;
    private int maxTime;
    private int extraCoins;
    private HashMap<Integer , BadWildAnimal> wildAnimals = new HashMap<>();

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        if (levelNumber == 1){
            initialCoin = 100;
            Good taskGood1 = new Good("egg", 5);
            Good taskGood2 = new Good("feather", 3);
            taskGoods = new ArrayList<>();
            taskGoods.add(taskGood1);
            taskGoods.add(taskGood2);
            taskAnimals = new ArrayList<>();
            eachTaskAnimalCount = new ArrayList<>();
            wildAnimals.put(12, new BadWildAnimal("lion", 12));
            wildAnimals.put(18, new BadWildAnimal("bear" , 18));
            taskCoins = 250;
            maxTime = 25;
            extraCoins = 150;
        }else if (levelNumber == 2){
            initialCoin = 300;
            Good taskGood1 = new Good("egg", 7);
            Good taskGood2 = new Good("feather", 3);
            Good taskGood3 = new Good("flour", 3);
            taskGoods = new ArrayList<>();
            taskGoods.add(taskGood1);
            taskGoods.add(taskGood2);
            taskGoods.add(taskGood3);
            DomesticAnimal taskAnimal1 = new DomesticAnimal("turkey");
            taskAnimals = new ArrayList<>();
            eachTaskAnimalCount = new ArrayList<>();
            wildAnimals.put(5, new BadWildAnimal("bear", 5));
            wildAnimals.put(45, new BadWildAnimal("lion", 45));
            wildAnimals.put(55, new BadWildAnimal("bear", 55));
            eachTaskAnimalCount.add(2);
            taskAnimals.add(taskAnimal1);
            taskCoins = 300;
            maxTime = 65;
            extraCoins = 300;
        }else if (levelNumber == 3){
            initialCoin = 500;
            Good taskGood1 = new Good("feather", 10);
            Good taskGood2 = new Good("milk", 5);
            Good taskGood3 = new Good("cloth", 3);
            taskGoods = new ArrayList<>();
            taskGoods.add(taskGood1);
            taskGoods.add(taskGood2);
            taskGoods.add(taskGood3);
            taskAnimals = new ArrayList<>();
            eachTaskAnimalCount = new ArrayList<>();
            DomesticAnimal taskAnimal1 = new DomesticAnimal("turkey");
            DomesticAnimal taskAnimal2 = new DomesticAnimal("buffalo");
            eachTaskAnimalCount.add(3);
            eachTaskAnimalCount.add(1);
            wildAnimals.put(10, new BadWildAnimal("bear", 10));
            wildAnimals.put(20, new BadWildAnimal("lion", 20));
            wildAnimals.put(25,new BadWildAnimal("bear", 25));
            wildAnimals.put(40, new BadWildAnimal("lion", 40));
            wildAnimals.put(70, new BadWildAnimal("tiger", 70));
            taskAnimals.add(taskAnimal1);
            taskAnimals.add(taskAnimal2);
            taskCoins = 300;
            maxTime = 80;
            extraCoins = 350;
        }else if (levelNumber == 4){
            initialCoin = 1000;
            Good taskGood1 = new Good("p_milk", 10);
            Good taskGood2 = new Good("bread", 7);
            Good taskGood3 = new Good("egg", 12);
            taskGoods = new ArrayList<>();
            taskGoods.add(taskGood1);
            taskGoods.add(taskGood2);
            taskGoods.add(taskGood3);
            taskAnimals = new ArrayList<>();
            eachTaskAnimalCount = new ArrayList<>();
            DomesticAnimal taskAnimal1 = new DomesticAnimal("chicken");
            DomesticAnimal taskAnimal2 = new DomesticAnimal("buffalo");
            eachTaskAnimalCount.add(6);
            eachTaskAnimalCount.add(2);
            wildAnimals.put(15, new BadWildAnimal("bear", 15));
            wildAnimals.put(25, new BadWildAnimal("lion", 25));
            wildAnimals.put(35,new BadWildAnimal("bear", 35));
            wildAnimals.put(40, new BadWildAnimal("lion", 40));
            wildAnimals.put(70, new BadWildAnimal("tiger", 70));
            wildAnimals.put(100, new BadWildAnimal("tiger", 100));
            taskAnimals.add(taskAnimal1);
            taskAnimals.add(taskAnimal2);
            taskCoins = 500;
            maxTime = 115;
            extraCoins = 550;
        }else if (levelNumber == 5){
            initialCoin = 2500;
            Good taskGood1 = new Good("feather", 10);
            Good taskGood2 = new Good("shirt", 7);
            Good taskGood3 = new Good("iceCream", 5);
            taskGoods = new ArrayList<>();
            taskGoods.add(taskGood1);
            taskGoods.add(taskGood2);
            taskGoods.add(taskGood3);
            taskAnimals = new ArrayList<>();
            eachTaskAnimalCount = new ArrayList<>();
            wildAnimals.put(7, new BadWildAnimal("lion", 7));
            wildAnimals.put(15, new BadWildAnimal("bear", 15));
            wildAnimals.put(25,new BadWildAnimal("bear", 25));
            wildAnimals.put(30, new BadWildAnimal("tiger" , 30));
            wildAnimals.put(45, new BadWildAnimal("lion", 45));
            wildAnimals.put(75, new BadWildAnimal("tiger", 75));
            wildAnimals.put(120, new BadWildAnimal("bear", 120));
            wildAnimals.put(140, new BadWildAnimal("tiger", 140));

            DomesticAnimal taskAnimal1 = new DomesticAnimal("turkey");
            DomesticAnimal taskAnimal2 = new DomesticAnimal("buffalo");
            DomesticAnimal taskAnimal3 = new DomesticAnimal("chicken");
            eachTaskAnimalCount.add(5);
            eachTaskAnimalCount.add(3);
            eachTaskAnimalCount.add(5);
            taskAnimals.add(taskAnimal1);
            taskAnimals.add(taskAnimal2);
            taskAnimals.add(taskAnimal3);
            taskCoins = 1000;
            maxTime = 175;
            extraCoins = 1000;
        }
    }

    public void setEachTaskAnimalCount(ArrayList<Integer> eachTaskAnimalCount) {
        this.eachTaskAnimalCount = eachTaskAnimalCount;
    }

    public ArrayList<Integer> getEachTaskAnimalCount() {
        return eachTaskAnimalCount;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getInitialCoin() {
        return initialCoin;
    }

    public ArrayList<Good> getTaskGoods() {
        return taskGoods;
    }

    public ArrayList<DomesticAnimal> getTaskAnimals() {
        return taskAnimals;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getExtraCoins() {
        return extraCoins;
    }

    public int getTaskCoins() {
        return taskCoins;
    }

    public HashMap<Integer, BadWildAnimal> getWildAnimals() {
        return wildAnimals;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setInitialCoin(int initialCoin) {
        this.initialCoin = initialCoin;
    }

    public void setTaskGoods(ArrayList<Good> taskGoods) {
        this.taskGoods = taskGoods;
    }

    public void setTaskAnimals(ArrayList<DomesticAnimal> taskAnimals) {
        this.taskAnimals = taskAnimals;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void setExtraCoins(int extraCoins) {
        this.extraCoins = extraCoins;
    }

    public void setTaskCoins(int taskCoins) {
        this.taskCoins = taskCoins;
    }

    public void setWildAnimals(HashMap<Integer, BadWildAnimal> wildAnimals) {
        this.wildAnimals = wildAnimals;
    }
}
