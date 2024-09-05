//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.geom.RectangularShape;
//import java.io.*;
//import java.lang.reflect.Type;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//
//public class Manager {
//    public ArrayList<User> users = new ArrayList<>();
//    public ArrayList<Good> goods = new ArrayList<>();
//    public ArrayList<Factory> factories = new ArrayList<>();
//    public ArrayList<Good> goodInMap = new ArrayList<>();
//    public ArrayList<Animal> animalInMap = new ArrayList<>();
//    public ArrayList<Level> levels = new ArrayList<>();
//    public User user;
//    public Level level ;
//    Cellar cellar = new Cellar(30, user);
//    Truck truck = new Truck(15, user);
//    public String[][] map = new String[6][6];
//    public int coins = 0 ;
//    public int time = 0 ;
//    public int wellWater = 5;
//    public int fillingWellTimeCounter = 0;
//    public final byte maxLevel = 5;
//
//
//    //    public void wildAnimalConstructCheck(){
////        BadWildAnimal badWildAnimal = new BadWildAnimal("bear", 1);
////        System.out.println("name: " + badWildAnimal.getName());
////        System.out.println("getCagesRequiredCount: " + badWildAnimal.getCagesRequiredCount());
////        System.out.println("speed: " + badWildAnimal.getSpeed());
////    }
//
//    public boolean checkGood (int x,int y)
//    {
//        if (map[x-1][y-1] == null){
//            System.out.println("There is nothing here to pickup!");
//            writeLogFile("Error", "Picked up nothing");
//            return false;
//        }
//        boolean existsAnyGood = false;
//        for (int i=0; i<map[x-1][y-1].length(); i++){
//            if (map[x-1][y-1].charAt(i) == 'G'){
//                existsAnyGood = true;
//                break;
//            }
//        }
//        return existsAnyGood;
//    }
//    public void initializeMap(){
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                map[i][j] = new String("");
//            }
//        }
//    }
//    void addLevels(){
//        levels.add(new Level(1));
//        levels.add(new Level(2));
//        levels.add(new Level(3));
//        levels.add(new Level(4));
//        levels.add(new Level(5));
//    }
//    public void startLevel(int levelNum){
//        this.level = levels.get(levelNum-1);
//        coins = level.getInitialCoin() + user.getExtraCoins();
//    }
//
//    public void birthOfWildAnimal()
//    {
//        for (Integer i :level.getWildAnimals().keySet()) {
//            if (i==time)
//            {
//                //taghire khodam
//                BadWildAnimal newBadWildAnimal = new BadWildAnimal(level.getWildAnimals().get(i).getName(),time);
//                designMapForAnimals(newBadWildAnimal,true);
//                animalInMap.add(newBadWildAnimal);
//                //
//                writeLogFile("Info", "new wild animal");
//            }
//        }
//    }
//
//    public void removeAnimal(ArrayList<Animal> animals)
//    {
//        for (int i = 0 ;i < animals.size() ; i++ )
//        {
//            animalRemoveFromArray(animals.get(i));
//        }
//    }
//
//    public void removeGood(ArrayList<Good> goods)
//    {
//        for (int i = 0 ;i < goods.size() ; i++ )
//        {
//            goodRemoverFromArray(goods.get(i));
//        }
//    }
//
//    public void animalRemoveFromArray (Animal animal)
//    {
//        for (int i = 0; i < /*goodInMap   ehtemelan in ghalate va bayad benevisim:*/animalInMap.size() ; i++)
//        {
//            if (animal.getName().equals(animalInMap.get(i).getName())&&(animal.getX()==animalInMap.get(i).getX())&&(animal.getY()==animalInMap.get(i).getY()))
//            {
////                goodInMap.remove(i);
//                //ehtemalan khate bala bayad ba khate zir jabeja she
//                animalInMap.remove(i);
//                break;
//            }
//        }
//    }
//
//    public void goodRemoverFromArray (Good good)
//    {
//        for (int i = 0; i < goodInMap.size() ; i++)
//        {
//            if (good.getName().equals(goodInMap.get(i).getName())&&(good.getX()==goodInMap.get(i).getX())&&(good.getPickupTime()==goodInMap.get(i).getPickupTime())&&(good.getY()==goodInMap.get(i).getY()))
//            {
//                goodInMap.remove(i);
//                break;
//            }
//        }
//    }
//
//    public boolean badWildAnimalCheck (int x , int y)
//    {
//        boolean cageTrue = false ;
//        for (int i = 0 ;  i < animalInMap.size() ; i++)
//        {
//            if (animalInMap.get(i) instanceof BadWildAnimal)
//            {
//                if (animalInMap.get(i).getX()==x&&animalInMap.get(i).getY()==y)
//                {
//                    ((BadWildAnimal) animalInMap.get(i)).cager(time);
//                    cageTrue=true;
//                    writeLogFile("Info","cage for animal");
//                }
//            }
//        }
//        return cageTrue;
//    }
//
//    public void cager (int x,int y)
//    {
//        boolean cageTrue = false;
//        for (int i = 0 ;  i < animalInMap.size() ; i++)
//        {
//            if (animalInMap.get(i) instanceof BadWildAnimal)
//            {
//                if (animalInMap.get(i).getPrevX()/*getX()*/==x&&animalInMap.get(i).getPrevY()/*getY()*/==y)
//                {
//                    ((BadWildAnimal) animalInMap.get(i)).cager(time);
//                    cageTrue=true;
//                    System.out.println("Cage added for " + animalInMap.get(i).getName());
//                    writeLogFile("Info","cage for animal");
//                }
//            }
//        }
//        if (!cageTrue)
//        {
//            System.out.println("There is no wild animal to cage!");
//            writeLogFile("Error","no cage for animal");
//        }
//    }
//    public void animalTime()
//    {
//        birthOfWildAnimal();
//        ArrayList<Animal> removeAnimal = new ArrayList<>();
//        for (int i = 0 ; i < animalInMap.size() ; i ++)
//        {
//            if (animalInMap.get(i) instanceof GoodWildAnimal)
//            {
//                if (animalInMap.get(i).getName().equals("dog"))
//                {
//                    if (killTrueElseFalse((GoodWildAnimal) animalInMap.get(i)))
//                    {
//                        kill((GoodWildAnimal) animalInMap.get(i),removeAnimal);
//                        removeAnimal.add( animalInMap.get(i));
//                    }
//                    designMapForAnimals(animalInMap.get(i),false);
//                    animalInMap.get(i).randomMovement();
//                    designMapForAnimals(animalInMap.get(i),true);
//                }
//                else if ( animalInMap.get(i).getName().equals("cat"))
//                {
//                    goodMovementOfCat((GoodWildAnimal) animalInMap.get(i));
//                }
//            }
//            else if ( animalInMap.get(i) instanceof BadWildAnimal)
//            {
//                ((BadWildAnimal) animalInMap.get(i)).cageChecker(time);
//                //shak daram
//                int numberMovement = ((BadWildAnimal) animalInMap.get(i)).getSpeed();
//                while (numberMovement!=0)
//                {
//                    designMapForAnimals(animalInMap.get(i),false);
//                    animalInMap.get(i).randomMovement();
//                    designMapForAnimals(animalInMap.get(i),true);
//                    if (numberMovement == 1){
//                        if (eatAnimalChecker((BadWildAnimal)  animalInMap.get(i))>=0)
//                        {
//                            int z=eatAnimalChecker((BadWildAnimal) animalInMap.get(i));
//                            eatAnimal((BadWildAnimal)  animalInMap.get(i));
//                            removeAllGoodInOnePlace(animalInMap.get(i).getX(), animalInMap.get(i).getY());
//                            removeAnimal.add(animalInMap.get(z));
//                        }
//                    }
//                    numberMovement--;
//                }
//                //
//                if (((BadWildAnimal) animalInMap.get(i)).getCagesCount()== ((BadWildAnimal) animalInMap.get(i)).getCagesRequiredCount())
//                {
//                    Good wildAnimalGood = new Good(animalInMap.get(i).getName(),1,animalInMap.get(i).getX(),animalInMap.get(i).getY());
//                    //goodInMap.add(new Good(animalInMap.get(i).getName(),1,animalInMap.get(i).getX(),animalInMap.get(i).getY()));
//                    designMapForGoods(wildAnimalGood,true, false);
//                    removeAnimal.add(animalInMap.get(i));
//                }
//            }
//            else if (animalInMap.get(i) instanceof DomesticAnimal)
//            {
//                ((DomesticAnimal) animalInMap.get(i)).setLife(((DomesticAnimal) animalInMap.get(i)).getLife()-10);
//                goodMovementForDomesticAnimal((DomesticAnimal) animalInMap.get(i));
//                if (((DomesticAnimal) animalInMap.get(i)).checkGoodTime())
//                {
//                    //taghire khodam
//                    Good producedGood = new Good(((DomesticAnimal) animalInMap.get(i)).getProduct().getName(),1,((DomesticAnimal) animalInMap.get(i)).getX(),((DomesticAnimal) animalInMap.get(i)).getY());
//
////                    goodInMap.add(new Good(((DomesticAnimal) animalInMap.get(i)).getProduct().getName(),1,((DomesticAnimal) animalInMap.get(i)).getX(),((DomesticAnimal) animalInMap.get(i)).getY()));
//                    designMapForGoods(producedGood,true, false);
//                }
//                if (((DomesticAnimal) animalInMap.get(i)).getLife()==0) {
//                    removeAnimal.add(((DomesticAnimal) animalInMap.get(i)));
//                    //az dorost budane in motmaen nistam
////                    animalInMap.remove(i);
//                }
//            }
//        }
//        removeAnimal(removeAnimal);
//    }
//
//    public int animalFinder (int x,int y,String name)
//    {
//        for (int i = 0 ; i<animalInMap.size();i++)
//        {
//            if ((animalInMap.get(i).getX()==x)&&(animalInMap.get(i).getY()==y)&&(animalInMap.get(i).getName().equals(name)))
//                return i;
//        }
//        writeLogFile("Error","animal in this part not found");
//        return -1;
//    }
//    public void eat (DomesticAnimal domesticAnimal)
//    {
//        if (domesticAnimal.getLife()<50)
//        {
//            String[] split = map[domesticAnimal.getX()-1][domesticAnimal.getY()-1].split("#");
//            int i = nameFinder("grass",split);
//            StringBuilder sr= new StringBuilder();
//            if (i>=0)
//            {
//                domesticAnimal.setLife(domesticAnimal.getLife()+50);
//                for (int j = 0 ; j <split.length;j++)
//                {
//                    if (j!=i)
//                    {
//                        sr.append("#").append(split[j]);
//                    }
//                }
//                map[domesticAnimal.getX()-1][domesticAnimal.getY()-1]= sr.toString();
//            }
//            else
//            {
//                writeLogFile("Error","eat not happen");
//            }
//        }
//    }
//    public void kill (GoodWildAnimal goodWildAnimal,ArrayList<Animal> animals)
//    {
//        String partOfMap = map[goodWildAnimal.getX()-1][goodWildAnimal.getY()-1];
//        String[] split = partOfMap.split("#");
//        int q = nameFinder("Abear",split);
//        int w = nameFinder("Alion",split);
//        int e = nameFinder("Atiger",split);
//        int[] Array = {q,w,e};
//        Arrays.sort(Array);
//        int answer = Array[2];
//        int i;
//        i = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "dog");
//        if (i>=0&&answer>=0)
//        {{
//            designMapForAnimals(goodWildAnimal,false);
//        }
//            int j=-1;
//            if (answer==q)
//            {
//                j = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "bear");
//                animals.add(animalInMap.get(j));
//                designMapForAnimals(animalInMap.get(j),false);
//            }
//            else if (answer==w)
//            {
//                j = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "lion");
//                animals.add(animalInMap.get(j));
//                designMapForAnimals(animalInMap.get(j),false);
//            }
//            else if (answer==e)
//            {
//                j = animalFinder(goodWildAnimal.getX(),goodWildAnimal.getY(),"tiger");
//                animals.add(animalInMap.get(j));
//                designMapForAnimals(animalInMap.get(j),false);
//            }
//            if (j>0)
//            {
//                animalInMap.remove(j);
//            }
//        }
//        else
//        {
//            writeLogFile("Error","kill not happen");
//        }
//    }
//    public int eatAnimalChecker (BadWildAnimal badWildAnimal)
//    {
//        if (map[badWildAnimal.getX()-1][badWildAnimal.getY()-1] == null) return -1;
//        String partOfMap = map[badWildAnimal.getX()-1][badWildAnimal.getY()-1];
//        String[] split = partOfMap.split("#");
//        int q = nameFinder("Aturkey",split);
//        int w = nameFinder("Abuffalo",split);
//        int e = nameFinder("Achicken",split);
//        int[] Array = {q,w,e};
//        Arrays.sort(Array);
//        int answer = Array[2];
//        int j=-1;
//        if (answer>0&&answer==q)
//        {
//            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "turkey");
//        }
//        else if (answer>0&&answer==w)
//        {
//            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "buffalo");
//        }
//        else if (answer>0&&answer==e)
//        {
//            j = animalFinder(badWildAnimal.getX(),badWildAnimal.getY(),"chicken");
//        }
//        if (j<0)
//            writeLogFile("Error","domestic animal not found in this place");
//        return j;
//    }
//    public void eatAnimal(BadWildAnimal badWildAnimal)
//    {
//        String partOfMap = map[badWildAnimal.getX()-1][badWildAnimal.getY()-1];
//        String[] split = partOfMap.split("#");
//        int q = nameFinder("Aturkey",split);
//        int w = nameFinder("Abuffalo",split);
//        int e = nameFinder("Achicken",split);
//        int[] Array = {q,w,e};
//        Arrays.sort(Array);
//        int answer = Array[2];
//        int j=-1;
//        if (answer>=0&&answer==q)
//        {
//            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "turkey");
//            designMapForAnimals(animalInMap.get(j),false);
//        }
//        else if (answer>=0&&answer==w)
//        {
//            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "buffalo");
//            designMapForAnimals(animalInMap.get(j),false);
//        }
//        else if (answer>=0&&answer==e)
//        {
//            j = animalFinder(badWildAnimal.getX(),badWildAnimal.getY(),"chicken");
//            designMapForAnimals(animalInMap.get(j),false);
//        }
//        if (answer<0&&j<0)
//        {
//            writeLogFile("Info","eat not happen");
//        }
//        else
//        {
//            writeLogFile("Info","eat happen");
//        }
//    }
//    public boolean killTrueElseFalse (GoodWildAnimal goodWildAnimal)
//    {
//        String partOfMap = map[goodWildAnimal.getX()-1][goodWildAnimal.getY()-1];
//        String[] split = partOfMap.split("#");
//        int q = nameFinder("Abear",split);
//        int w = nameFinder("Alion",split);
//        int e = nameFinder("Atiger",split);
//        int[] Array = {q,w,e};
//        Arrays.sort(Array);
//        int answer = Array[2];
//        if (answer>0)
//            return true;
//        else
//            return false;
//    }
//    public boolean findStartWith (String startWith, String[] split)
//    {
//        for (int i=0 ; i <split.length ; i++)
//        {
//            if (split[i].startsWith(startWith))
//                return true;
//        }
//        return false;
//    }
//    public int findBestPathForCat (int x,int y)
//    {
//        double length= 8;
//        int xy=(x-1)*10+(y-1);
//        x--;
//        y--;
//        for (int i = 0 ;i<6;i++)
//        {
//            for (int j=0;j<6;j++)
//            {
//                if (map[i][j]!=null)
//                {
//                    String[] split =map[i][j].split("#");
//                    if (findStartWith("G",split))
//                    {
//                        if (Math.sqrt((x-i)*(x-i)+(y-j)*(y-j))<length)
//                        {
//                            xy=i*10+j;
//                            length=Math.sqrt((x-i)*(x-i)+(y-j)*(y-j));
//                        }
//                    }
//                }
//            }
//        }
//        return xy;
//    }
//    public int  findBestPath (int x,int y)
//    {
//        double length= 8;
//        int xy=(x-1)*10+(y-1);
//        x--;
//        y--;
//        for (int i = 0 ;i<6;i++)
//        {
//            for (int j=0;j<6;j++)
//            {
//                String[] split = new String[30];
//                if(map[i][j] != null)
//                    split =map[i][j].split("#");
//                if (nameFinder("grass",split)>-1)
//                {
//                    if (Math.sqrt((x-i)*(x-i)+(y-j)*(y-j))<length)
//                    {
//                        xy=i*10+j;
//                        length=Math.sqrt((x-i)*(x-i)+(y-j)*(y-j));
//                    }
//                }
//            }
//        }
//        return xy;
//    }
//    public void removeAllGoodInOnePlace(int x, int y){
//        ArrayList<Good> goods = new ArrayList<>();
//        for (Good good : goodInMap) {
//            if (good.getX() == x && good.getY() == y){
//                goods.add(good);
//                designMapForGoods(good, false, false);
//            }
//        }
//        removeGood(goods);
//    }
//    public void goodProduceForAnimal (DomesticAnimal domesticAnimal)
//    {
//        designMapForGoods(new Good(domesticAnimal.getProduct().getName(),domesticAnimal.getProduct().getSellPrice(),1),domesticAnimal.getX(), domesticAnimal.getY());
//    }
//    public void goodMovementForDomesticAnimal (DomesticAnimal domesticAnimal)
//    {
//        int xy=findBestPath(domesticAnimal.getX(),domesticAnimal.getY());
//        int x= xy/10;
//        int y=xy%10;
//        if ((x== domesticAnimal.getX()-1)&&(y== domesticAnimal.getY()-1))
//            eat(domesticAnimal);
//        else
//        {
//            if (x> domesticAnimal.getX()-1)
//            {
//                designMapForAnimals(domesticAnimal,false);
//                domesticAnimal.setPrevX(domesticAnimal.getX());
//                domesticAnimal.setPrevY(domesticAnimal.getY());
//                domesticAnimal.setX(domesticAnimal.getX()+1);
//                designMapForAnimals(domesticAnimal,true);
//            }
//            else if (x< domesticAnimal.getX()-1)
//            {
//                designMapForAnimals(domesticAnimal,false);
//                domesticAnimal.setPrevX(domesticAnimal.getX());
//                domesticAnimal.setPrevY(domesticAnimal.getY());
//                domesticAnimal.setX(domesticAnimal.getX()-1);
//                designMapForAnimals(domesticAnimal,true);
//            }
//            else
//            {
//                if (y> domesticAnimal.getY()-1)
//                {
//                    designMapForAnimals(domesticAnimal,false);
//                    domesticAnimal.setPrevX(domesticAnimal.getX());
//                    domesticAnimal.setPrevY(domesticAnimal.getY());
//                    domesticAnimal.setY(domesticAnimal.getY()+1);
//                    designMapForAnimals(domesticAnimal,true);
//                }
//                else if (y< domesticAnimal.getY()-1)
//                {
//                    designMapForAnimals(domesticAnimal,false);
//                    domesticAnimal.setPrevX(domesticAnimal.getX());
//                    domesticAnimal.setPrevY(domesticAnimal.getY());
//                    domesticAnimal.setY(domesticAnimal.getY()-1);
//                    designMapForAnimals(domesticAnimal,true);
//                }
//            }
//        }
//
//    }
//    public void Catch (int x,int y)
//    {
//        try {
//            String sr = map[x - 1][y - 1];
//            String[] split = sr.split("#");
//            String answer = "";
//            for (int i = 0; i < split.length; i++) {
//                if (!split[i].startsWith("G")) {
//                    answer = "#" + split[i];
//                }
//            }
//            ArrayList<Good> goods = new ArrayList<>();
//            map[x - 1][y - 1] = answer;
//            for (int i = 0; i < goodInMap.size(); i++) {
//                if (goodInMap.get(i).getX() == x && goodInMap.get(i).getY() == y) {
//                    if (cellar.addTrue(goodInMap.get(i))) {
//                        cellar.addGood(goodInMap.get(i));
//                        goods.add(goodInMap.get(i));
//                    } else {
//                        goodRemoverFromArray(goodInMap.get(i));
//                        designMapForGoods(goodInMap.get(i), true, false);
//                    }
//                }
//            }
//            removeGood(goods);
//        } catch (IndexOutOfBoundsException e){ }
//    }
//    public void goodMovementOfCat (GoodWildAnimal goodWildAnimal)
//    {
//        int xy = findBestPathForCat(goodWildAnimal.getX(),goodWildAnimal.getY());
//        int x = xy/10;
//        int y=xy%10;
//        if ((x== goodWildAnimal.getX()-1) && (y== goodWildAnimal.getY()-1))
//            Catch(x+1,y+1);
//        else
//        {
//            if (x> goodWildAnimal.getX()-1)
//            {
//                designMapForAnimals(goodWildAnimal,false);
//                goodWildAnimal.setPrevX(goodWildAnimal.getX());
//                goodWildAnimal.setPrevY(goodWildAnimal.getY());
//                goodWildAnimal.setX(goodWildAnimal.getX()+1);
//                designMapForAnimals(goodWildAnimal,true);
//            }
//            else if (x< goodWildAnimal.getX()-1)
//            {
//                designMapForAnimals(goodWildAnimal,false);
//                goodWildAnimal.setPrevX(goodWildAnimal.getX());
//                goodWildAnimal.setPrevY(goodWildAnimal.getY());
//                goodWildAnimal.setX(goodWildAnimal.getX()-1);
//                designMapForAnimals(goodWildAnimal,true);
//            }
//            else
//            {
//                if (y> goodWildAnimal.getY()-1)
//                {
//                    designMapForAnimals(goodWildAnimal,false);
//                    goodWildAnimal.setPrevX(goodWildAnimal.getX());
//                    goodWildAnimal.setPrevY(goodWildAnimal.getY());
//                    goodWildAnimal.setY(goodWildAnimal.getY()+1);
//                    designMapForAnimals(goodWildAnimal,true);
//                }
//                else if (y< goodWildAnimal.getY()-1)
//                {
//                    designMapForAnimals(goodWildAnimal,false);
//                    goodWildAnimal.setPrevX(goodWildAnimal.getX());
//                    goodWildAnimal.setPrevY(goodWildAnimal.getY());
//                    goodWildAnimal.setY(goodWildAnimal.getY()-1);
//                    designMapForAnimals(goodWildAnimal,true);
//                }
//            }
//        }
//    }
//
//    public void designMapForGoods (Good good,int x,int y)
//    {
//        map[x-1][y-1]=map[x-1][y-1]+"#G"+good.getName();
//        good.setTimeOfOut(good.getPickupTime());
//        goodInMap.add(good);
//        writeLogFile("Info","Good designed");
//    }
//    public int nameFinder (String sr,String[] split) {
//        if (split != null) {
//            if ((split.length > 0) &&(split[0] == null))
//                return -1;
//            for (int i = 0; i < split.length; i++) {
//                if (split[i].equals(sr)) {
//                    return i;
//                }
//            }
//            return -1;
//        }
//        return -1;
//
//    }
//    public int goodFinder (String sr,String[] split)
//    {
//        for (int i = 0 ; i < split.length ; i++)
//        {
//            if (split[i].startsWith(sr))
//            {
//                return i ;
//            }
//        }
//        return -1;
//    }
//    public void designMapForAnimals (Animal animal,boolean addTrueRemoveFalse)
//    {
//        if (addTrueRemoveFalse)
//        {
//            map[animal.getX()-1][animal.getY()-1]=map[animal.getX()-1][animal.getY()-1]+"#A"+animal.getName();
//            writeLogFile("Info","animal add to map");
//        }
//        else
//        {
//            String[] objects = map[animal.getX()-1][animal.getY()-1].split("#");
//            String sr="";
//            for (int j = 0 ; j <objects.length;j++)
//            {
//                if (!objects[j].equals("A" + animal.getName()))
//                {
//                    sr=sr+"#"+objects[j];
//                }
//            }
//            map[animal.getX()-1][animal.getY()-1]=sr;
//            writeLogFile("Info","animal remove from map");
//        }
//    }
//
//    public int animalCountOnMap(String animalName){
//        int count = 0 ;
//        for (int i = 0 ; i <animalInMap.size() ; i++)
//        {
//            if (animalInMap.get(i).getName().equals(animalName))
//                count++;
//        }
//        return count;
//    }
//    public void designMapForGoods (Good good,boolean addTrueRemoveFalse, boolean removeForAll)
//    {
//        if (addTrueRemoveFalse)
//        {
//            map[good.getX()-1][good.getY()-1]=map[good.getX()-1][good.getY()-1]+"#G"+good.getName();
//            good.setTimeOfOut(good.getPickupTime());
//            goodInMap.add(good);
//        }
//        else
//        {
//            //taghire khodam
//            if(map[good.getX()-1][good.getY()-1] == null)
//                return;
//            String[] objects = map[good.getX()-1][good.getY()-1].split("#");
//            String sr="";
//            if (!removeForAll) {
//                int deletedCount = 0;
//                for (int j = 0; j < objects.length; j++) {
//                    if (deletedCount == 0) {
//                        if (!objects[j].equals("G" + good.getName())) {
//                            sr = sr + "#" + objects[j];
//                        } else {
//                            deletedCount = 1;
//                        }
//                    } else {
//                        sr = sr + "#" + objects[j];
//                    }
//                }
//            }else{
//                for (int i = 0; i < objects.length; i++) {
//                    if (!objects[i].equals("G" + good.getName())) {
//                        sr = sr + "#" + objects[i];
//                    }
//                }
//            }
//            map[good.getX()-1][good.getY()-1]=sr;
//
//        }
//    }
//    public boolean existsUserName(String userName){
//        for (User user : users) {
//            if (user.getUserName().equals(userName)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean isCorrectPass(String userName , String password) {
//        for (User user : users) {
//            if (user.getUserName().equals(userName)){
//                if(user.getPassword().equals(password))
//                    return true;
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public String processBuildFactory(String factoryName){
//
//        for (Factory factory : factories) {
//            if (factory.getName().equals(factoryName)){
//                if(factory.isBuilt()) {
//                    writeLogFile("Error", "Building an existing factory");
//                    return "Error. This factory already exists.";
//                }
//
//            }
//        }
//        int coinsRequired = 0;
//        switch (factoryName){
//            case "mill":
//                coinsRequired = 150;
//                break;
//            case "weavingF":
//                coinsRequired = 250;
//                break;
//            case "packingMilk":
//                coinsRequired = 400;
//                break;
//            case "bakery":
//                coinsRequired = 250;
//                break;
//            case "sewingF":
//                coinsRequired = 400;
//                break;
//            case "iceCreamF":
//                coinsRequired = 550;
//                break;
//            default: {
//                writeLogFile("Error", "Invalid inout for building factory");
//                return "There is no factory with this name in the game!";
//            }
//        }
//        if (coinsRequired > coins){
//            writeLogFile("Error", "Coins lack for building factory");
//            return "Error. You don't have enough coins.";
//        }else {
//            buildFactory(factoryName);
//            writeLogFile("Info", "Factory was built successfully");
//            return "Factory was built successfully!";
//        }
//    }
//
//    public void initializeFactories(){
//        factories.add(new Factory(1 , "mill", false));
//        factories.add(new Factory(1 , "weavingF", false));
//        factories.add(new Factory(1 , "packingMilk", false));
//        factories.add(new Factory(1 , "bakery", false));
//        factories.add(new Factory(1 , "sewingF", false));
//        // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
//        factories.add(new Factory(1 , "iceCreamF", false));
//    }
//
//    public void buildFactory(String factoryName){
//        for (Factory factory : factories) {
//            if (factory.getName().equals(factoryName)){
//                factory.setBuilt(true);
//                coins -= factory.getBuildPrice();
//                return;
//            }
//        }
//    }
//
//    public void upgradeFactory(String factoryName){
//        for (Factory factory : factories) {
//            if (factory.getName().equals(factoryName)){
//                factory.setLevel(2);
//                int newTimeTakes = (factory.getTimeTakes() % 2 == 1) ?
//                        factory.getTimeTakes()/2+1 : factory.getTimeTakes()/2;
//                factory.setTimeTakes(newTimeTakes);
//            }
//        }
//    }
//
//    public void startWork(Factory factory){
//        factory.setWorking(true);
//        factory.setWorkingRemainingTime(factory.getTimeTakes());
//        for (Good good : cellar.getGoods()) {
//            if (good.getName().equals(factory.getInputGoodName()))
//            {
//                good.setCount(good.getCount() - 1);
//                break;
//            }
//        }
//    }
//    public void finishWork(Factory factory)
//    {
//        factory.setWorking(false);
//        factory.setWorkingRemainingTime(-1);
//        if (factory.getName().equals("Aviculture"))
//        {
//            designMapForAnimals(new DomesticAnimal("chicken"),true);
//        }
//        else
//            designMapForGoods(new Good(factory.getOutputGoodName() , 1 , (factory.getX() == 0) ? 1 : 6 , factory.getY() ) , (factory.getX() == 0) ? 1 : 6 , factory.getY() );
//    }
//
//    public String processWork (int x,int y){
//        boolean existsFactory = false;
//        for (Factory factory : factories) {
//            if (factory.getX()==x&&factory.getY()==y){
//                if (factory.getWorkingRemainingTime() > 0){
//                    writeLogFile("Error", "Work command for working factory");
//                    return "Factory is already working!";
//                }
//                if (cellar.getGoods().size() != 0) {
//                    for (Good good : cellar.getGoods()) {
//                        if (good.getName().equals(factory.getInputGoodName())) {
//                            if (good.getCount() >= 1 /*factory.level*/) {
//                                startWork(factory);
//                                writeLogFile("Info", "Factory started working");
//                                return "Factory started working...";
//                            } else {
//                                writeLogFile("Error", "Good lack for working factory");
//                                return "There is not enough required good!";
//                            }
//                        }
//                    }
//                }else{
//                    System.out.println("Error . There is not enough good.");
//                    writeLogFile("Error", "Good lack for working factory");
//
//                    return "There is not enough required good!";
//                }
//                existsFactory = true;
//                break;
//            }
//        }
//        if (!existsFactory){
//            System.out.println("Error . There is no built factory with this name.");
//            writeLogFile("Error", "Not existing factory name");
//            return "Invalid name for factory";
//        }
//        return "An error occurred!";
//    }
//
//    public void plantGrass(int x, int y) {
//        wellWater--;
//        map[x-1][y-1] += "#grass";
//    }
//
//    public void startFillingWell() {
//        fillingWellTimeCounter = 3;
//    }
//
//    public int goodsCountHereByName(String name, int i, int j) {
//        int result = 0;
//        for (Good good : goodInMap) {
//            if (good.getName().equals(name) && good.getX()-1 == i && good.getY()-1 == j){
//                result += good.getCount();
//            }
//        }
//        return result;
//    }
//
//    public void turn(int timeCount){
//        int timeCounter = 0;
//        while (timeCounter < timeCount) {
//            time++;
//            if (time > level.getMaxTime()){
//                user.setExtraCoins(0);
//            }
//            if (fillingWellTimeCounter > 0){
//                fillingWellTimeCounter --;
//                if (fillingWellTimeCounter ==0){
//                    wellWater = 5;
//                }
//            }
//            for (Factory factory : factories) {
//                if (factory.isWorking()) {
//                    factory.setWorkingRemainingTime(factory.getWorkingRemainingTime() - /*1*/ factory.getLevel());
//                    if (factory.getWorkingRemainingTime() <=0){
//                        finishWork(factory);
//                    }
//                }
//            }
//
//            ArrayList<Good> removingGoods = new ArrayList<>();
//            for (int i=0; i<goodInMap.size(); i++) {
//                if (goodInMap.get(i).getTimeOfOut() > 0){
//                    goodInMap.get(i).setTimeOfOut(goodInMap.get(i).getTimeOfOut() - 1);
//                    if (goodInMap.get(i).getTimeOfOut() == 0){
//                        removingGoods.add(goodInMap.get(i));
////                        designMapForGoods(goodInMap.get(i) , false , false);
////                        goodInMap.remove(goodInMap.get(i));
//                    }
//                }
//            }
//            if (removingGoods.size()!=0)
//                removeGoodsFromGoodInMap(removingGoods);
//
//            timeCounter++;
//            if (isLevelCompleted()) {
//                finishLevel();
//                return;
//            }
//            animalTime();
//            if (truck.getTravel() > 0)
//                truck.setTravel(truck.getTravel() - 1);
//        }
//        writeLogFile("Info", "turn in size " + timeCount);
//    }
//
//    private void removeGoodsFromGoodInMap(ArrayList<Good> removingGoods) {
//        for (Good removingGood : removingGoods) {
//            for (Good good : goodInMap) {
//                if (removingGood.getName().equals(good.getName()) && removingGood.getX() == good.getX() && removingGood.getY() == good.getY()){
//                    designMapForGoods(good, false, false);
//                    goodInMap.remove(good);
//                    break;
//                }
//            }
//        }
//    }
//
//    public void addUser(String userName, String password) {
//        User result = new User(userName, password, 1, 0);
//        users.add(result);
//        saveUsers();
//    }
//
//    public void setUser(String userName, String password) {
//        for (User user1 : users) {
//            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
//                user = user1;
//                cellar.setUser(user1);
//                truck.setUser(user1);
//                break;
//            }
//        }
//    }
//
//    public boolean isLevelCompleted(){
//        if (level.getTaskCoins() > coins)
//            return false;
//        for (Good taskGood : level.getTaskGoods()) {
//            if (taskGood.getCount() > cellar.goodSearcher(taskGood.getName()).getCount()){
//                return false;
//            }
//        }
//        for (int i = 0; i < level.getTaskAnimals().size(); i++) {
//            if (level.getEachTaskAnimalCount().get(i) > animalCountOnMap(level.getTaskAnimals().get(i).getName())){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void finishLevel(){
//        if (time <= level.getMaxTime()){
//            user.setExtraCoins(level.getExtraCoins());
//        }else {
//            user.setExtraCoins(0);
//        }
//        user.setUnlockedLevel(user.getUnlockedLevel() + 1);
//        saveUsers();
//        //feelan hamina be zehnam reside
//    }
//
//    public void createMissionsFile(){
//        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
//        Gson gson = builder.create();
//
//        File help = new File("");
//        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\missions.json");
//        try {
//            if (!jsonFile.exists())
//                jsonFile.createNewFile();
//            FileWriter fileWriter = new FileWriter(jsonFile);
//            String jsonText;
//            jsonText = ("total levels: "+maxLevel);
//            for (Level level1 : levels) {
//                jsonText += ("\nlevel "+level1.getLevelNumber() + ":");
//                jsonText += "\ninitial coins: " + level1.getInitialCoin();
//                if (level1.getTaskGoods().size() != 0) {
//                    jsonText += "\ntask goods:";
//                    jsonText += "\n" + gson.toJson(level1.getTaskGoods());
//                }
//                if (level1.getTaskAnimals().size() != 0) {
//                    jsonText += "\ntask animals:";
//                    for (int i = 0; i < level1.getTaskAnimals().size(); i++) {
//                        jsonText += ("\n" + level1.getTaskAnimals().get(i).getName() + " *" + level1.getEachTaskAnimalCount().get(i));
//                    }
//                }
//                if (level1.getWildAnimals().size() != 0) {
//                    jsonText += "\nwild animals:";
//                    ArrayList<String> valueNames = new ArrayList<>();
//                    ArrayList<Integer> keys = new ArrayList<>();
//                    for (BadWildAnimal value : level1.getWildAnimals().values()) {
//                        valueNames.add(value.getName());
//                    }
//                    for (Integer integer : level1.getWildAnimals().keySet()) {
//                        keys.add(integer);
//                    }
//                    for (int i = 0; i < level1.getWildAnimals().size(); i++) {
//                        jsonText += "\n" + valueNames.get(i) + "_" + keys.get(i);
//                    }
//                }
//                jsonText += ("\nlevel max time: " + level1.getMaxTime());
//                jsonText += ("\nlevel extra coins(prize): " + level1.getExtraCoins());
//            }
//            fileWriter.write(jsonText);
//            fileWriter.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void saveUsers(){
//        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
//        Gson gson = builder.create();
//
//        File help = new File("");
//        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\users.json");
//
//        try {
//            if (!jsonFile.exists()){
//                jsonFile.createNewFile();
//            }
//            FileWriter fileWriter = new FileWriter(jsonFile);
//            String jsonText = gson.toJson(users);
//            if (!jsonText.equals("")) {
//                fileWriter.write(jsonText);
//            }
//            fileWriter.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void loadUsers(){
//        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
//        Gson gson = builder.create();
//
//        File help = new File("");
//        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\users.json");
//        if (jsonFile.exists()) {
//            try {
//                FileReader fileReader = new FileReader(jsonFile);
//                BufferedReader bufferedReader = new BufferedReader(fileReader);
//                String line = bufferedReader.readLine();
//                String jsonString = new String("");
//                while (line != null) {
//                    jsonString += line;
//                    line = bufferedReader.readLine();
//                }
//                Type arrayListOfUserObject = new TypeToken<ArrayList<User>>() {
//                }.getType();
//                users = gson.fromJson(jsonString, arrayListOfUserObject);
//                bufferedReader.close();
//                fileReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public int getGrassCount(int i, int j) {
//        int result = 0;
//        String[] split;
//        if (map[i][j] != null)
//            split = map[i][j].split("#");
//        else
//            return 0;
//        for (String s : split) {
//            if (s.equals("grass")){
//                result ++;
//            }
//        }
//        return result;
//    }
//
//    public ArrayList<Good> getGoodsHere(int i, int j) {
//        ArrayList<Good> result = new ArrayList<>();
//        String[] split;
//        if (map[i][j] != null)
//            split = map[i][j].split("#");
//        else
//            return null;
//        for (String s : split) {
//            if (s.startsWith("G")){
//                int index = createdGoodIndex(result, s.substring(1));
//                if (index == -1){
//                    result.add(new Good(s.substring(1), 1));
//                }else{
//                    result.get(index).setCount(result.get(index).getCount() + 1);
//                }
//            }
//        }
//        return result;
//    }
//
//    public ArrayList<Animal> getAnimalsHere(int i, int j) {
//        ArrayList<Animal> result = new ArrayList<>();
//        String[] split;
//        if (map[i][j] == null)
//            return null;
////        for (String s : split) {
////            if (s.startsWith("A")){
////                String mainAnimalName = s.substring(1);
////                if (mainAnimalName.equals("lion") || mainAnimalName.equals("bear") || mainAnimalName.equals("tiger")){
////                    result.add(new BadWildAnimal(mainAnimalName, 1));
////                }else if (mainAnimalName.equals("cat") || mainAnimalName.equals("dog")){
////                    result.add(new GoodWildAnimal(mainAnimalName));
////                }else{
////                    result.add(new DomesticAnimal(mainAnimalName));
////                }
////            }
////        }
//        for (Animal animal : animalInMap) {
//            if (animal.getX() == i && animal.getY() == j){
//                result.add(animal);
//            }
//        }
//        return result;
//    }
//
//
//    public int createdGoodIndex(ArrayList<Good> goods, String goodName){
//        for (int i = 0; i < goods.size(); i++) {
//            if (goods.get(i).getName().equals(goodName)){
//                return i;
//            }
//        }
//        writeLogFile("Error","in this part good not found");
//        return -1;
//    }
//
//    public int goodCountInCellarByName(String goodName){
//        for (Good good : cellar.getGoods()) {
//            if (good.getName().equals(goodName))
//                return good.getCount();
//        }
//        writeLogFile("Error","Good in cellar not found");
//        return -1;
//    }
//
//    public void writeLogFile(String eventType , String event){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//
//        File help = new File("");
//        File logFile = new File (help.getAbsolutePath()+"\\Information\\log.txt");
//        try {
//            String newLogText = new String("");
//            if (logFile.exists()){
//                FileReader logFileReader = new FileReader(logFile);
//                BufferedReader logBufferedReader = new BufferedReader(logFileReader);
//                String line1 = logBufferedReader.readLine();
//                String line2 = logBufferedReader.readLine();
//                String line3 = logBufferedReader.readLine();
//
//                String oldBody = new String("");
//                String linesReader = logBufferedReader.readLine();
//                while (linesReader != null){
//                    oldBody += linesReader + "\n";
//                    linesReader = logBufferedReader.readLine();
//                }
//                newLogText += line1;
//                newLogText += "\nUserName: "+((user == null)?"null":user.getUserName());
//                newLogText += "\nLast edit date & time: "+dtf.format(now);
//                newLogText += "\n" + oldBody;
//            }else{
//                logFile.createNewFile();
//                newLogText += "Created file date: "+dtf.format(now);
//                newLogText += "\nUserName: "+((user == null)?"null":user.getUserName());
//                newLogText += "\nLast edit date & time: "+dtf.format(now);
//            }
//            newLogText += "\n[" + eventType + "], " + dtf.format(now) + ", " + event + "\n";
//
//            FileWriter logFileWriter = new FileWriter(logFile);
//            logFileWriter.write(newLogText);
//            logFileWriter.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//
//
//
//
//
//
//    //GRAPHICS
//
//
//
//
//
//
//}
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RectangularShape;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Good> goods = new ArrayList<>();
    public ArrayList<Factory> factories = new ArrayList<>();
    public ArrayList<Good> goodInMap = new ArrayList<>();
    public ArrayList<Animal> animalInMap = new ArrayList<>();
    public ArrayList<Level> levels = new ArrayList<>();
    public User user;
    public Level level ;
    Cellar cellar = new Cellar(30, user);
    Truck truck = new Truck(15, user);
    public String[][] map = new String[6][6];
    public int coins = 0 ;
    public int time = 0 ;
    public int wellWater = 5;
    public int fillingWellTimeCounter = 0;
    public final byte maxLevel = 5;
    public void processMenu(String userName, String password, Scanner scanner) {

        FrameCreator menuFrame = new FrameCreator("MENU", 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Select one");

        mainLabel.setForeground(new Color(4, 88, 15));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        final int TEXT_HEIGHT = 50;
        mainLabel.setBounds(menuFrame.getX(), menuFrame.getY(), menuFrame.getWidth(), menuFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35));



        final int BUTTON_WIDTH = 350;
        final int BUTTON_HEIGHT = 90;
        final int TOP_AND_BUTTONS_GAP = 200;
        final int BUTTONS_GAP = 20;
        final int EXIT_AND_BOTTOM_GAP = 50;

        JButton startLevelButton = new JButton("START");
        JButton settingsButton = new JButton("SETTINGS");
        JButton exitButton = new JButton("EXIT");

        startLevelButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                menuFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton.setBounds(menuFrame.getFrameCenter().x-BUTTON_WIDTH/2,
                startLevelButton.getY()+startLevelButton.getHeight()+BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                menuFrame.getY()+menuFrame.getHeight()-BUTTON_HEIGHT-EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        startLevelButton.setBackground(Color.BLUE);
        settingsButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        startLevelButton.setForeground(Color.YELLOW);
        settingsButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        startLevelButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        settingsButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        startLevelButton.setFocusable(false);
        settingsButton.setFocusable(false);
        exitButton.setFocusable(false);

        startLevelButton.setVerticalAlignment(mainLabel.CENTER);
        settingsButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        startLevelButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        settingsButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20));

        ActionListener startLevelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                createSelectLevelFrame(userName, password, scanner);
            }
        };
        ActionListener settingsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowSettings();
                writeLogFile("Info", "Settings opened");
            }
        };
        ActionListener logoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processLogout(userName, password);
                writeLogFile("Info", "Logged out");
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        startLevelButton.addActionListener(startLevelListener);
        settingsButton.addActionListener(settingsListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(startLevelButton);
        mainLabel.add(settingsButton);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        menuFrame.setComponents("farm.jpg", components);














        //CMD
//        User user = new User("", "", 0, 0);
//        for (User user1 : manager.users) {
//            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)) {
//                user = user1;
//                break;
//            }
//        }
//
//        System.out.println("Enter a command:");
//        String command = new String(scanner.nextLine());
//        while (!isValidCommand(command, "log out", "settings", "exit")
//                && !command.matches("(s|S)(t|T)(a|A)(r|R)(t|T) \\d+")){
//            manager.writeLogFile("Error", "Invalid command");
//            System.out.println("Invalid command. Please enter another one:");
//            command = scanner.nextLine();
//        }
//        if (command.equalsIgnoreCase("log out")){
//            processLogout(userName, password);
//            manager.writeLogFile("Info", "Logged out");
//        }else if (command.equalsIgnoreCase("settings")){
//            processShowSettings();
//            manager.writeLogFile("Info", "Settings opened");
//        }else if (command.equalsIgnoreCase("exit")){
//            return;
//        }else if (command.matches("(s|S)(t|T)(a|A)(r|R)(t|T) \\d+")){
//            int enteredLevel = Integer.parseInt(command.substring(6));
//            if (enteredLevel > user.getUnlockedLevel() || enteredLevel > manager.levels.size() || enteredLevel <= 0){
//                if (enteredLevel > user.getUnlockedLevel() && enteredLevel <= manager.levels.size()) {
//                    manager.writeLogFile("Error", "Entered an unlocked level");
//                    System.out.println("You haven't unlocked this level yet!");
//                }else{
//                    manager.writeLogFile("Error", "Entered Invalid level");
//                    System.out.println("This level does not exist in the game!");
//                }
//                processMenu(userName, password, scanner);
//                return;
//            }
//            manager.writeLogFile("Info", "Level selected");
//            processStartLevel(user, Integer.parseInt(command.substring(6)), scanner);
//        }
    }

    //    public void wildAnimalConstructCheck(){
//        BadWildAnimal badWildAnimal = new BadWildAnimal("bear", 1);
//        System.out.println("name: " + badWildAnimal.getName());
//        System.out.println("getCagesRequiredCount: " + badWildAnimal.getCagesRequiredCount());
//        System.out.println("speed: " + badWildAnimal.getSpeed());
//    }
    private void processShowSettings() {
    }
    private void createSelectLevelFrame(String userName, String password, Scanner scanner){
        User user = new User("", "", 0, 0);
        for (User user1 : users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)) {
                user = user1;
                break;
            }
        }

        FrameCreator levelsFrame = new FrameCreator("SELECT LEVEL", 1600, 800, Color.black,
                null, 3, false);
        JLabel mainLabel = new JLabel("Select the level you want to start");

        mainLabel.setForeground(new Color(6, 63, 238));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        final int TEXT_HEIGHT = 50;
        mainLabel.setBounds(levelsFrame.getX(), levelsFrame.getY(), levelsFrame.getWidth(), levelsFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35));


        final int BUTTON_WIDTH = 350;
        final int BUTTON_HEIGHT = 75;
        final int TOP_AND_BUTTONS_GAP = 140;
        final int BUTTONS_GAP = 17;
        final int EXIT_AND_BOTTOM_GAP = 50;

        JButton level1Button = new JButton("1");
        JButton level2Button = new JButton("2");
        JButton level3Button = new JButton("3");
        JButton level4Button = new JButton("4");
        JButton level5Button = new JButton("5");
        JButton exitButton = new JButton("EXIT");



        level1Button.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                levelsFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        level2Button.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                level1Button.getY()+level1Button.getHeight()+BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        level3Button.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                level2Button.getY()+level2Button.getHeight()+BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        level4Button.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                level3Button.getY()+level3Button.getHeight()+BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        level5Button.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                level4Button.getY()+level4Button.getHeight()+BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);

        exitButton.setBounds(levelsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                levelsFrame.getY()+levelsFrame.getHeight()-BUTTON_HEIGHT-EXIT_AND_BOTTOM_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);

        level1Button.setBackground(Color.BLUE);
        level2Button.setBackground(Color.BLUE);
        level3Button.setBackground(Color.BLUE);
        level4Button.setBackground(Color.BLUE);
        level5Button.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        level1Button.setForeground(Color.YELLOW);
        level2Button.setForeground(Color.YELLOW);
        level3Button.setForeground(Color.YELLOW);
        level4Button.setForeground(Color.YELLOW);
        level5Button.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        level1Button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        level2Button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        level3Button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        level4Button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        level5Button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        level1Button.setFocusable(false);
        level2Button.setFocusable(false);
        level3Button.setFocusable(false);
        level4Button.setFocusable(false);
        level5Button.setFocusable(false);
        exitButton.setFocusable(false);

        level1Button.setVerticalAlignment(mainLabel.CENTER);
        level2Button.setVerticalAlignment(mainLabel.CENTER);
        level3Button.setVerticalAlignment(mainLabel.CENTER);
        level4Button.setVerticalAlignment(mainLabel.CENTER);
        level5Button.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        level1Button.setFont(new Font("MV Boli", Font.PLAIN, 30));
        level2Button.setFont(new Font("MV Boli", Font.PLAIN, 30));
        level3Button.setFont(new Font("MV Boli", Font.PLAIN, 30));
        level4Button.setFont(new Font("MV Boli", Font.PLAIN, 30));
        level5Button.setFont(new Font("MV Boli", Font.PLAIN, 30));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 30));



        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 1, scanner);
            }
        };
        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 2, scanner);
            }
        };
        ActionListener listener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 3, scanner);
            }
        };
        ActionListener listener4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 4, scanner);
            }
        };
        ActionListener listener5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 5, scanner);
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        level1Button.addActionListener(listener1);
        level2Button.addActionListener(listener2);
        level3Button.addActionListener(listener3);
        level4Button.addActionListener(listener4);
        level5Button.addActionListener(listener5);
        exitButton.addActionListener(exitListener);

        if (user.getUnlockedLevel() == 1){
            level2Button.setEnabled(false);
            level3Button.setEnabled(false);
            level4Button.setEnabled(false);
            level5Button.setEnabled(false);
        }else if(user.getUnlockedLevel() == 2) {
            level3Button.setEnabled(false);
            level4Button.setEnabled(false);
            level5Button.setEnabled(false);
        }else if(user.getUnlockedLevel() == 3) {
            level4Button.setEnabled(false);
            level5Button.setEnabled(false);
        }else if(user.getUnlockedLevel() == 4) {
            level5Button.setEnabled(false);
        }

        mainLabel.add(level1Button);
        mainLabel.add(level2Button);
        mainLabel.add(level3Button);
        mainLabel.add(level4Button);
        mainLabel.add(level5Button);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        levelsFrame.setComponents("farm.jpg", components);





    }
    private void processLogout(String userName, String password) {
        user = null;
        saveUsers();
        runGame();
    }
    void runGame() {
        addLevels();
        createMissionsFile();
        loadUsers();
    }

    private void processStartLevel(String userName, String password, int levelNum, Scanner scanner) {
        User user = new User("", "", 0, 0);
        for (User user1 : users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)) {
                user = user1;
                break;
            }
        }

        startLevel(levelNum);
        initializeFactories();
        Game game = new Game(this);

//        Runnable r = new Runnable() {
//            @Override
//            public void run(){
//                while(true){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("turning (Runnable run)...");
//                    manager.turn(1);
//                    game.turn();
//                    processShowDetails();
//                }
//            }
//        };

//        while (true) {
//            run();
//        }

//        System.out.println("level "+manager.level.getLevelNumber());
//        System.out.println("Your coins: "+manager.coins);
//        if (manager.level.getTaskCoins()!= 0){
//            System.out.println("Coins to collect: " + manager.level.getTaskCoins());
//        }
//        if (manager.level.getTaskGoods().size() != 0){
//            System.out.println("Good(s) to collect:");
//            for (Good taskGood : manager.level.getTaskGoods()) {
//                System.out.println(taskGood.getName() + " *" + taskGood.getCount());
//            }
//        }
//        if (manager.level.getTaskAnimals().size() != 0){
//            System.out.println("Animal(s) to collect:");
//            for (int i = 0; i < manager.level.getTaskAnimals().size(); i++) {
//                System.out.println(manager.level.getTaskAnimals().get(i).getName() + " *" + manager.level.getEachTaskAnimalCount().get(i));
//            }
//        }


//        processGetGameCommands(user, scanner);
    }

    public void processUnload(String name)
    {
        if (truck.getTravel()==0)
        {
            if (name.equalsIgnoreCase("chicken")||name.equalsIgnoreCase("turkey")||name.equalsIgnoreCase("buffalo"))
            {
                truck.removeAnimal(name);
                animalInMap.add(new DomesticAnimal(name));
                designMapForAnimals(animalInMap.get(animalInMap.size()-1),true);
                writeLogFile("Info","Unloaded animal : " + name);
            }
            else if (name.equalsIgnoreCase("icecream")||name.equalsIgnoreCase("milk")||name.equalsIgnoreCase("egg")||name.equalsIgnoreCase("feather")||name.equalsIgnoreCase("shirt")||name.equalsIgnoreCase("p_milk")||name.equalsIgnoreCase("cloth")||name.equalsIgnoreCase("flour"))
            {
                System.out.println("Unloaded good : " + name);
                truck.truckToCellar(name, cellar);
                writeLogFile("Info", "Unloaded good : " + name);
            }
            else
            {
                System.out.println("This item does not exist");
                writeLogFile("Error", "Not existing item unload");
            }
        }
        else {
            System.out.println("Truck is in travel...");
            writeLogFile("Error", "Unloading Truck in travel");
        }
    }
    public void processLoad(String name)
    {
        if (truck.getTravel() == 0) {
            if (name.equals("chicken") || name.equals("turkey") || name.equals("buffalo")) {
                for (int i = 0; i < animalInMap.size(); i++) {
                    if (animalInMap.get(i).getName().equals(name)) {
                        designMapForAnimals(animalInMap.get(i), false);
                        truck.addAnimal(name);
                        animalInMap.remove(i);
                        System.out.println("Truck loaded!");
                        writeLogFile("Info", "Truck loaded with " + name);
                        break;
                    }
                }
            } else {
                cellar.cellarToTruck(name, truck);
            }
        }
        else{
            printErrorOrInfo(null,"ERROR","Truck is in travel!",
                    "Computer Error Alert-SoundBible.com-783113881.wav",
                    true,"error.png");
        }
    }
    public void processSell()
    {
        truck.sell();
        coins+=truck.sellPrice();
        System.out.println("Truck went");
        writeLogFile("Info", "Truck went (processSell)");
    }
    public int countAnimal(String name)
    {
        int num = 0 ;
        for (int i = 0 ; i < animalInMap.size() ; i++)
        {
            if (animalInMap.get(i).getName().equals(name))
                num++;
        }
        return num ;
    }
    public void printErrorOrInfo (Component component,String title,String message,String path,boolean errorTrue,String imagePath)
    {
        File musicPath = new File(path);
//        System.out.println("musicPath exists == "+musicPath.exists());
        if (musicPath.exists())
        {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                if (errorTrue)
                {
                    if (imagePath.equals(""))
                    {
                        JOptionPane.showMessageDialog(component,message,title,JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(component,message,title,JOptionPane.ERROR_MESSAGE,new ImageIcon(imagePath));
                    }
                }
                else
                {
                    if (imagePath.equals(""))
                    {
                        JOptionPane.showMessageDialog(component,message,title,JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(component,message,title,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(imagePath));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkGood (int x,int y)
    {
        if (map[x-1][y-1] == null){
//            System.out.println("There is nothing here to pickup!");
            writeLogFile("Error", "Picked up nothing");
            return false;
        }
        boolean existsAnyGood = false;
        for (int i=0; i<map[x-1][y-1].length(); i++){
            if (map[x-1][y-1].charAt(i) == 'G'){
                existsAnyGood = true;
                break;
            }
        }
        return existsAnyGood;
    }
    public void initializeMap(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                map[i][j] = new String("");
            }
        }
    }
    void addLevels(){
        levels.add(new Level(1));
        levels.add(new Level(2));
        levels.add(new Level(3));
        levels.add(new Level(4));
        levels.add(new Level(5));
    }
    public void startLevel(int levelNum){
        this.level = levels.get(levelNum-1);
        coins = level.getInitialCoin() + user.getExtraCoins();
    }

    public void birthOfWildAnimal()
    {
        for (Integer i :level.getWildAnimals().keySet()) {
            if (i==time)
            {
                //taghire khodam
                BadWildAnimal newBadWildAnimal = new BadWildAnimal(level.getWildAnimals().get(i).getName(),time);
                designMapForAnimals(newBadWildAnimal,true);
                animalInMap.add(newBadWildAnimal);
                //
                writeLogFile("Info", "new wild animal");
            }
        }
    }

    public void removeAnimal(ArrayList<Animal> animals)
    {
        for (int i = 0 ;i < animals.size() ; i++ )
        {
            animalRemoveFromArray(animals.get(i));
        }
    }

    public void removeGood(ArrayList<Good> goods)
    {
        for (int i = 0 ;i < goods.size() ; i++ )
        {
            goodRemoverFromArray(goods.get(i));
        }
    }

    public void animalRemoveFromArray (Animal animal)
    {
        for (int i = 0; i < /*goodInMap   ehtemelan in ghalate va bayad benevisim:*/animalInMap.size() ; i++)
        {
            if (animal.getName().equals(animalInMap.get(i).getName())&&(animal.getX()==animalInMap.get(i).getX())&&(animal.getY()==animalInMap.get(i).getY()))
            {
//                goodInMap.remove(i);
                //ehtemalan khate bala bayad ba khate zir jabeja she
                animalInMap.remove(i);
                break;
            }
        }
    }

    public void goodRemoverFromArray (Good good)
    {
        for (int i = 0; i < goodInMap.size() ; i++)
        {
            if (good.getName().equals(goodInMap.get(i).getName())&&(good.getX()==goodInMap.get(i).getX())&&(good.getPickupTime()==goodInMap.get(i).getPickupTime())&&(good.getY()==goodInMap.get(i).getY()))
            {
                goodInMap.remove(i);
                break;
            }
        }
    }

    public boolean badWildAnimalCheck (int x , int y)
    {
        boolean cageTrue = false ;
        for (int i = 0 ;  i < animalInMap.size() ; i++)
        {
            if (animalInMap.get(i) instanceof BadWildAnimal)
            {
                if (animalInMap.get(i).getX()==x&&animalInMap.get(i).getY()==y)
                {
                    ((BadWildAnimal) animalInMap.get(i)).cager(time);
                    cageTrue=true;
                    writeLogFile("Info","cage for animal");
                }
            }
        }
        return cageTrue;
    }

    public void cager (int x,int y)
    {
        boolean cageTrue = false;
        for (int i = 0 ;  i < animalInMap.size() ; i++)
        {
            if (animalInMap.get(i) instanceof BadWildAnimal)
            {
                if (animalInMap.get(i).getPrevX()/*getX()*/==x&&animalInMap.get(i).getPrevY()/*getY()*/==y)
                {
                    ((BadWildAnimal) animalInMap.get(i)).cager(time);
                    cageTrue=true;
//                    System.out.println("Cage added for " + animalInMap.get(i).getName());
                    writeLogFile("Info","cage for animal");
                }
            }
        }
        if (!cageTrue)
        {
//            System.out.println("There is no wild animal to cage!");
            writeLogFile("Error","no cage for animal");
        }
    }
    public void animalTime()
    {
        birthOfWildAnimal();
        ArrayList<Animal> removeAnimal = new ArrayList<>();
        for (int i = 0 ; i < animalInMap.size() ; i ++)
        {
            if (animalInMap.get(i) instanceof GoodWildAnimal)
            {
                if (animalInMap.get(i).getName().equals("dog"))
                {
                    if (killTrueElseFalse((GoodWildAnimal) animalInMap.get(i)))
                    {
                        kill((GoodWildAnimal) animalInMap.get(i),removeAnimal);
                        removeAnimal.add( animalInMap.get(i));
                    }
                    designMapForAnimals(animalInMap.get(i),false);
                    animalInMap.get(i).randomMovement();
                    designMapForAnimals(animalInMap.get(i),true);
                }
                else if ( animalInMap.get(i).getName().equals("cat"))
                {
                    goodMovementOfCat((GoodWildAnimal) animalInMap.get(i));
                }
            }
            else if ( animalInMap.get(i) instanceof BadWildAnimal)
            {
                ((BadWildAnimal) animalInMap.get(i)).cageChecker(time);
                //shak daram
                int numberMovement = ((BadWildAnimal) animalInMap.get(i)).getSpeed();
                while (numberMovement!=0)
                {
                    designMapForAnimals(animalInMap.get(i),false);
                    animalInMap.get(i).randomMovement();
                    designMapForAnimals(animalInMap.get(i),true);
                    if (numberMovement == 1){
                        if (eatAnimalChecker((BadWildAnimal)  animalInMap.get(i))>=0)
                        {
                            int z=eatAnimalChecker((BadWildAnimal) animalInMap.get(i));
                            eatAnimal((BadWildAnimal)  animalInMap.get(i));
                            removeAllGoodInOnePlace(animalInMap.get(i).getX(), animalInMap.get(i).getY());
                            removeAnimal.add(animalInMap.get(z));
                        }
                    }
                    numberMovement--;
                }
                //
                if (((BadWildAnimal) animalInMap.get(i)).getCagesCount()== ((BadWildAnimal) animalInMap.get(i)).getCagesRequiredCount())
                {
                    Good wildAnimalGood = new Good(animalInMap.get(i).getName(),1,animalInMap.get(i).getX(),animalInMap.get(i).getY());
                    //goodInMap.add(new Good(animalInMap.get(i).getName(),1,animalInMap.get(i).getX(),animalInMap.get(i).getY()));
                    designMapForGoods(wildAnimalGood,true, false);
                    removeAnimal.add(animalInMap.get(i));
                }
            }
            else if (animalInMap.get(i) instanceof DomesticAnimal)
            {
                ((DomesticAnimal) animalInMap.get(i)).setLife(((DomesticAnimal) animalInMap.get(i)).getLife()-10);
                goodMovementForDomesticAnimal((DomesticAnimal) animalInMap.get(i));
                if (((DomesticAnimal) animalInMap.get(i)).checkGoodTime())
                {
                    //taghire khodam
                    Good producedGood = new Good(((DomesticAnimal) animalInMap.get(i)).getProduct().getName(),1,((DomesticAnimal) animalInMap.get(i)).getX(),((DomesticAnimal) animalInMap.get(i)).getY());

//                    goodInMap.add(new Good(((DomesticAnimal) animalInMap.get(i)).getProduct().getName(),1,((DomesticAnimal) animalInMap.get(i)).getX(),((DomesticAnimal) animalInMap.get(i)).getY()));
                    designMapForGoods(producedGood,true, false);
                }
                if (((DomesticAnimal) animalInMap.get(i)).getLife()==0) {
                    removeAnimal.add(((DomesticAnimal) animalInMap.get(i)));
                    //az dorost budane in motmaen nistam
//                    animalInMap.remove(i);
                }
            }
        }
        removeAnimal(removeAnimal);
    }

    public int animalFinder (int x,int y,String name)
    {
        for (int i = 0 ; i<animalInMap.size();i++)
        {
            if ((animalInMap.get(i).getX()==x)&&(animalInMap.get(i).getY()==y)&&(animalInMap.get(i).getName().equals(name)))
                return i;
        }
        writeLogFile("Error","animal in this part not found");
        return -1;
    }
    public void eat (DomesticAnimal domesticAnimal)
    {
        if (domesticAnimal.getLife()<50)
        {
            String[] split = map[domesticAnimal.getX()-1][domesticAnimal.getY()-1].split("#");
            int i = nameFinder("grass",split);
            StringBuilder sr= new StringBuilder();
            if (i>=0)
            {
                domesticAnimal.setLife(domesticAnimal.getLife()+50);
                for (int j = 0 ; j <split.length;j++)
                {
                    if (j!=i)
                    {
                        sr.append("#").append(split[j]);
                    }
                }
                map[domesticAnimal.getX()-1][domesticAnimal.getY()-1]= sr.toString();
            }
            else
            {
                writeLogFile("Error","eat not happen");
            }
        }
    }
    public void kill (GoodWildAnimal goodWildAnimal,ArrayList<Animal> animals)
    {
        String partOfMap = map[goodWildAnimal.getX()-1][goodWildAnimal.getY()-1];
        String[] split = partOfMap.split("#");
        int q = nameFinder("Abear",split);
        int w = nameFinder("Alion",split);
        int e = nameFinder("Atiger",split);
        int[] Array = {q,w,e};
        Arrays.sort(Array);
        int answer = Array[2];
        int i;
        i = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "dog");
        if (i>=0&&answer>=0)
        {{
            designMapForAnimals(goodWildAnimal,false);
        }
            int j=-1;
            if (answer==q)
            {
                j = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "bear");
                animals.add(animalInMap.get(j));
                designMapForAnimals(animalInMap.get(j),false);
            }
            else if (answer==w)
            {
                j = animalFinder(goodWildAnimal.getX(), goodWildAnimal.getY(), "lion");
                animals.add(animalInMap.get(j));
                designMapForAnimals(animalInMap.get(j),false);
            }
            else if (answer==e)
            {
                j = animalFinder(goodWildAnimal.getX(),goodWildAnimal.getY(),"tiger");
                animals.add(animalInMap.get(j));
                designMapForAnimals(animalInMap.get(j),false);
            }
            if (j>0)
            {
                animalInMap.remove(j);
            }
        }
        else
        {
            writeLogFile("Error","kill not happen");
        }
    }
    public int eatAnimalChecker (BadWildAnimal badWildAnimal)
    {
        if (map[badWildAnimal.getX()-1][badWildAnimal.getY()-1] == null) return -1;
        String partOfMap = map[badWildAnimal.getX()-1][badWildAnimal.getY()-1];
        String[] split = partOfMap.split("#");
        int q = nameFinder("Aturkey",split);
        int w = nameFinder("Abuffalo",split);
        int e = nameFinder("Achicken",split);
        int[] Array = {q,w,e};
        Arrays.sort(Array);
        int answer = Array[2];
        int j=-1;
        if (answer>0&&answer==q)
        {
            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "turkey");
        }
        else if (answer>0&&answer==w)
        {
            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "buffalo");
        }
        else if (answer>0&&answer==e)
        {
            j = animalFinder(badWildAnimal.getX(),badWildAnimal.getY(),"chicken");
        }
        if (j<0)
            writeLogFile("Error","domestic animal not found in this place");
        return j;
    }
    public void eatAnimal(BadWildAnimal badWildAnimal)
    {
        String partOfMap = map[badWildAnimal.getX()-1][badWildAnimal.getY()-1];
        String[] split = partOfMap.split("#");
        int q = nameFinder("Aturkey",split);
        int w = nameFinder("Abuffalo",split);
        int e = nameFinder("Achicken",split);
        int[] Array = {q,w,e};
        Arrays.sort(Array);
        int answer = Array[2];
        int j=-1;
        if (answer>=0&&answer==q)
        {
            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "turkey");
            designMapForAnimals(animalInMap.get(j),false);
        }
        else if (answer>=0&&answer==w)
        {
            j = animalFinder(badWildAnimal.getX(), badWildAnimal.getY(), "buffalo");
            designMapForAnimals(animalInMap.get(j),false);
        }
        else if (answer>=0&&answer==e)
        {
            j = animalFinder(badWildAnimal.getX(),badWildAnimal.getY(),"chicken");
            designMapForAnimals(animalInMap.get(j),false);
        }
        if (answer<0&&j<0)
        {
            writeLogFile("Info","eat not happen");
        }
        else
        {
            writeLogFile("Info","eat happen");
        }
    }
    public boolean killTrueElseFalse (GoodWildAnimal goodWildAnimal)
    {
        String partOfMap = map[goodWildAnimal.getX()-1][goodWildAnimal.getY()-1];
        String[] split = partOfMap.split("#");
        int q = nameFinder("Abear",split);
        int w = nameFinder("Alion",split);
        int e = nameFinder("Atiger",split);
        int[] Array = {q,w,e};
        Arrays.sort(Array);
        int answer = Array[2];
        if (answer>0)
            return true;
        else
            return false;
    }
    public boolean findStartWith (String startWith, String[] split)
    {
        for (int i=0 ; i <split.length ; i++)
        {
            if (split[i].startsWith(startWith))
                return true;
        }
        return false;
    }
    public int findBestPathForCat (int x,int y)
    {
        double length= 8;
        int xy=(x-1)*10+(y-1);
        x--;
        y--;
        for (int i = 0 ;i<6;i++)
        {
            for (int j=0;j<6;j++)
            {
                if (map[i][j]!=null)
                {
                    String[] split =map[i][j].split("#");
                    if (findStartWith("G",split))
                    {
                        if (Math.sqrt((x-i)*(x-i)+(y-j)*(y-j))<length)
                        {
                            xy=i*10+j;
                            length=Math.sqrt((x-i)*(x-i)+(y-j)*(y-j));
                        }
                    }
                }
            }
        }
        return xy;
    }
    public int  findBestPath (int x,int y)
    {
        double length= 8;
        int xy=(x-1)*10+(y-1);
        x--;
        y--;
        for (int i = 0 ;i<6;i++)
        {
            for (int j=0;j<6;j++)
            {
                String[] split = new String[30];
                if(map[i][j] != null)
                    split =map[i][j].split("#");
                if (nameFinder("grass",split)>-1)
                {
                    if (Math.sqrt((x-i)*(x-i)+(y-j)*(y-j))<length)
                    {
                        xy=i*10+j;
                        length=Math.sqrt((x-i)*(x-i)+(y-j)*(y-j));
                    }
                }
            }
        }
        return xy;
    }
    public void removeAllGoodInOnePlace(int x, int y){
        ArrayList<Good> goods = new ArrayList<>();
        for (Good good : goodInMap) {
            if (good.getX() == x && good.getY() == y){
                goods.add(good);
                designMapForGoods(good, false, false);
            }
        }
        removeGood(goods);
    }
    public void goodProduceForAnimal (DomesticAnimal domesticAnimal)
    {
        designMapForGoods(new Good(domesticAnimal.getProduct().getName(),domesticAnimal.getProduct().getSellPrice(),1),domesticAnimal.getX(), domesticAnimal.getY());
    }
    public void goodMovementForDomesticAnimal (DomesticAnimal domesticAnimal)
    {
        int xy=findBestPath(domesticAnimal.getX(),domesticAnimal.getY());
        int x= xy/10;
        int y=xy%10;
        if ((x== domesticAnimal.getX()-1)&&(y== domesticAnimal.getY()-1))
            eat(domesticAnimal);
        else
        {
            if (x> domesticAnimal.getX()-1)
            {
                designMapForAnimals(domesticAnimal,false);
                domesticAnimal.setPrevX(domesticAnimal.getX());
                domesticAnimal.setPrevY(domesticAnimal.getY());
                domesticAnimal.setX(domesticAnimal.getX()+1);
                designMapForAnimals(domesticAnimal,true);
            }
            else if (x< domesticAnimal.getX()-1)
            {
                designMapForAnimals(domesticAnimal,false);
                domesticAnimal.setPrevX(domesticAnimal.getX());
                domesticAnimal.setPrevY(domesticAnimal.getY());
                domesticAnimal.setX(domesticAnimal.getX()-1);
                designMapForAnimals(domesticAnimal,true);
            }
            else
            {
                if (y> domesticAnimal.getY()-1)
                {
                    designMapForAnimals(domesticAnimal,false);
                    domesticAnimal.setPrevX(domesticAnimal.getX());
                    domesticAnimal.setPrevY(domesticAnimal.getY());
                    domesticAnimal.setY(domesticAnimal.getY()+1);
                    designMapForAnimals(domesticAnimal,true);
                }
                else if (y< domesticAnimal.getY()-1)
                {
                    designMapForAnimals(domesticAnimal,false);
                    domesticAnimal.setPrevX(domesticAnimal.getX());
                    domesticAnimal.setPrevY(domesticAnimal.getY());
                    domesticAnimal.setY(domesticAnimal.getY()-1);
                    designMapForAnimals(domesticAnimal,true);
                }
            }
        }

    }
    public void Catch (int x,int y)
    {
        try {
            String sr = map[x - 1][y - 1];
            String[] split = sr.split("#");
            String answer = "";
            for (int i = 0; i < split.length; i++) {
                if (!split[i].startsWith("G")) {
                    answer = "#" + split[i];
                }
            }
            ArrayList<Good> goods = new ArrayList<>();
            map[x - 1][y - 1] = answer;
            for (int i = 0; i < goodInMap.size(); i++) {
                if (goodInMap.get(i).getX() == x && goodInMap.get(i).getY() == y) {
                    if (cellar.addTrue(goodInMap.get(i))) {
                        cellar.addGood(goodInMap.get(i));
                        goods.add(goodInMap.get(i));
                    } else {
                        goodRemoverFromArray(goodInMap.get(i));
                        designMapForGoods(goodInMap.get(i), true, false);
                    }
                }
            }
            removeGood(goods);
        } catch (IndexOutOfBoundsException e){ }
    }
    public void goodMovementOfCat (GoodWildAnimal goodWildAnimal)
    {
        int xy = findBestPathForCat(goodWildAnimal.getX(),goodWildAnimal.getY());
        int x = xy/10;
        int y=xy%10;
        if ((x== goodWildAnimal.getX()-1) && (y== goodWildAnimal.getY()-1))
            Catch(x+1,y+1);
        else
        {
            if (x> goodWildAnimal.getX()-1)
            {
                designMapForAnimals(goodWildAnimal,false);
                goodWildAnimal.setPrevX(goodWildAnimal.getX());
                goodWildAnimal.setPrevY(goodWildAnimal.getY());
                goodWildAnimal.setX(goodWildAnimal.getX()+1);
                designMapForAnimals(goodWildAnimal,true);
            }
            else if (x< goodWildAnimal.getX()-1)
            {
                designMapForAnimals(goodWildAnimal,false);
                goodWildAnimal.setPrevX(goodWildAnimal.getX());
                goodWildAnimal.setPrevY(goodWildAnimal.getY());
                goodWildAnimal.setX(goodWildAnimal.getX()-1);
                designMapForAnimals(goodWildAnimal,true);
            }
            else
            {
                if (y> goodWildAnimal.getY()-1)
                {
                    designMapForAnimals(goodWildAnimal,false);
                    goodWildAnimal.setPrevX(goodWildAnimal.getX());
                    goodWildAnimal.setPrevY(goodWildAnimal.getY());
                    goodWildAnimal.setY(goodWildAnimal.getY()+1);
                    designMapForAnimals(goodWildAnimal,true);
                }
                else if (y< goodWildAnimal.getY()-1)
                {
                    designMapForAnimals(goodWildAnimal,false);
                    goodWildAnimal.setPrevX(goodWildAnimal.getX());
                    goodWildAnimal.setPrevY(goodWildAnimal.getY());
                    goodWildAnimal.setY(goodWildAnimal.getY()-1);
                    designMapForAnimals(goodWildAnimal,true);
                }
            }
        }
    }

    public void designMapForGoods (Good good,int x,int y)
    {
        map[x-1][y-1]=map[x-1][y-1]+"#G"+good.getName();
        good.setTimeOfOut(good.getPickupTime());
        goodInMap.add(good);
        writeLogFile("Info","Good designed");
    }
    public int nameFinder (String sr,String[] split) {
        if (split != null) {
            if ((split.length > 0) &&(split[0] == null))
                return -1;
            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(sr)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;

    }
    public int goodFinder (String sr,String[] split)
    {
        for (int i = 0 ; i < split.length ; i++)
        {
            if (split[i].startsWith(sr))
            {
                return i ;
            }
        }
        return -1;
    }
    public void designMapForAnimals (Animal animal,boolean addTrueRemoveFalse)
    {
        if (addTrueRemoveFalse)
        {
            map[animal.getX()-1][animal.getY()-1]=map[animal.getX()-1][animal.getY()-1]+"#A"+animal.getName();
            writeLogFile("Info","animal add to map");
        }
        else
        {
            String[] objects = map[animal.getX()-1][animal.getY()-1].split("#");
            String sr="";
            for (int j = 0 ; j <objects.length;j++)
            {
                if (!objects[j].equals("A" + animal.getName()))
                {
                    sr=sr+"#"+objects[j];
                }
            }
            map[animal.getX()-1][animal.getY()-1]=sr;
            writeLogFile("Info","animal remove from map");
        }
    }

    public int animalCountOnMap(String animalName){
        int count = 0 ;
        for (int i = 0 ; i <animalInMap.size() ; i++)
        {
            if (animalInMap.get(i).getName().equals(animalName))
                count++;
        }
        return count;
    }
    public void designMapForGoods (Good good,boolean addTrueRemoveFalse, boolean removeForAll)
    {
        if (addTrueRemoveFalse)
        {
            map[good.getX()-1][good.getY()-1]=map[good.getX()-1][good.getY()-1]+"#G"+good.getName();
            good.setTimeOfOut(good.getPickupTime());
            goodInMap.add(good);
        }
        else
        {
            //taghire khodam
            if(map[good.getX()-1][good.getY()-1] == null)
                return;
            String[] objects = map[good.getX()-1][good.getY()-1].split("#");
            String sr="";
            if (!removeForAll) {
                int deletedCount = 0;
                for (int j = 0; j < objects.length; j++) {
                    if (deletedCount == 0) {
                        if (!objects[j].equals("G" + good.getName())) {
                            sr = sr + "#" + objects[j];
                        } else {
                            deletedCount = 1;
                        }
                    } else {
                        sr = sr + "#" + objects[j];
                    }
                }
            }else{
                for (int i = 0; i < objects.length; i++) {
                    if (!objects[i].equals("G" + good.getName())) {
                        sr = sr + "#" + objects[i];
                    }
                }
            }
            map[good.getX()-1][good.getY()-1]=sr;

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

    public String processBuildFactory(String factoryName){

        for (Factory factory : factories) {
            if (factory.getName().equals(factoryName)){
                if(factory.isBuilt()) {
                    writeLogFile("Error", "Building an existing factory");
                    return "Error. This factory already exists.";
                }

            }
        }
        int coinsRequired = 0;
        switch (factoryName){
            case "mill":
                coinsRequired = 150;
                break;
            case "weavingF":
                coinsRequired = 250;
                break;
            case "packingMilk":
                coinsRequired = 400;
                break;
            case "bakery":
                coinsRequired = 250;
                break;
            case "sewingF":
                coinsRequired = 400;
                break;
            case "iceCreamF":
                coinsRequired = 550;
                break;
            case "aviculture":
                coinsRequired = 300;
                break;
            default: {
                writeLogFile("Error", "Invalid inout for building factory");
                return "There is no factory with this name in the game!";
            }
        }
        if (coinsRequired > coins){
            writeLogFile("Error", "Coins lack for building factory");
            return "Error. You don't have enough coins.";
        }else {
            buildFactory(factoryName);
            writeLogFile("Info", "Factory was built successfully");
            return "Factory was built successfully!";
        }
    }

    public void initializeFactories(){
        factories.add(new Factory(1 , "mill", false));
        factories.add(new Factory(1 , "weavingF", false));
        factories.add(new Factory(1 , "packingMilk", false));
        factories.add(new Factory(1 , "bakery", false));
        factories.add(new Factory(1 , "sewingF", false));
        // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
        if (level.getLevelNumber() == 4 || level.getLevelNumber() == 5)
            factories.add(new Factory(1 , "iceCreamF", false));
        else
            factories.add(new Factory(1 , "aviculture", false));
    }

    public void buildFactory(String factoryName){
        for (Factory factory : factories) {
            if (factory.getName().equals(factoryName)){
                factory.setBuilt(true);
                coins -= factory.getBuildPrice();
                return;
            }
        }
    }

    public void upgradeFactory(String factoryName){
        for (Factory factory : factories) {
            if (factory.getName().equals(factoryName)){
                factory.setLevel(2);
                int newTimeTakes = (factory.getTimeTakes() % 2 == 1) ?
                        factory.getTimeTakes()/2+1 : factory.getTimeTakes()/2;
                factory.setTimeTakes(newTimeTakes);
            }
        }
    }

    public void startWork(Factory factory){
        factory.setWorking(true);
        factory.setWorkingRemainingTime(factory.getTimeTakes());
        for (Good good : cellar.getGoods()) {
            if (good.getName().equals(factory.getInputGoodName()))
            {
                good.setCount(good.getCount() - 1);
                break;
            }
        }
    }
    public void finishWork(Factory factory)
    {
        factory.setWorking(false);
        factory.setWorkingRemainingTime(-1);
        if (factory.getName().equals("aviculture"))
        {
            DomesticAnimal domesticAnimal = new DomesticAnimal("chicken");
            domesticAnimal.setX(factory.getX()-1);
            domesticAnimal.setY(factory.getY());
            domesticAnimal.setPrevX(domesticAnimal.getX());
            domesticAnimal.setPrevY(domesticAnimal.getY());

            animalInMap.add(domesticAnimal);
            designMapForAnimals(new DomesticAnimal("chicken"),true);
        }
        else
            designMapForGoods(new Good(factory.getOutputGoodName() , 1 , (factory.getX() == 0) ? 1 : 6 , factory.getY() ) , (factory.getX() == 0) ? 1 : 6 , factory.getY() );
    }

    public String processWork (int x,int y){
        boolean existsFactory = false;
        for (Factory factory : factories) {
            if (factory.getX()==x&&factory.getY()==y){
                if (factory.getWorkingRemainingTime() > 0){
                    writeLogFile("Error", "Work command for working factory");
                    return "Factory is already working!";
                }
                if (cellar.getGoods().size() != 0) {
                    for (Good good : cellar.getGoods()) {
                        if (good.getName().equals(factory.getInputGoodName())) {
                            if (good.getCount() >= 1 /*factory.level*/) {
                                startWork(factory);
                                writeLogFile("Info", "Factory started working");
                                return "Factory started working...";
                            } else {
                                writeLogFile("Error", "Good lack for working factory");
                                return "There is not enough required good!";
                            }
                        }
                    }
                }else{
//                    System.out.println("Error . There is not enough good.");
                    writeLogFile("Error", "Good lack for working factory");

                    return "There is not enough required good!";
                }
                existsFactory = true;
                break;
            }
        }
        if (!existsFactory){
//            System.out.println("Error . There is no built factory with this name.");
            writeLogFile("Error", "Not existing factory name");
            return "Invalid name for factory";
        }
        return "An error occurred!";
    }

    public void plantGrass(int x, int y) {
        wellWater--;
        map[x-1][y-1] += "#grass";
    }

    public void startFillingWell() {
        fillingWellTimeCounter = 3;
    }

    public int goodsCountHereByName(String name, int i, int j) {
        int result = 0;
        for (Good good : goodInMap) {
            if (good.getName().equals(name) && good.getX()-1 == i && good.getY()-1 == j){
                result += good.getCount();
            }
        }
        return result;
    }

    public void turn(int timeCount){
        int timeCounter = 0;
        while (timeCounter < timeCount) {
            time++;
            if (time > level.getMaxTime()){
                user.setExtraCoins(0);
            }
            if (fillingWellTimeCounter > 0){
                fillingWellTimeCounter --;
                if (fillingWellTimeCounter ==0){
                    wellWater = 5;
                }
            }
            for (Factory factory : factories) {
                if (factory.isWorking()) {
                    factory.setWorkingRemainingTime(factory.getWorkingRemainingTime() - /*1*/ factory.getLevel());
                    if (factory.getWorkingRemainingTime() <=0){
                        finishWork(factory);
                    }
                }
            }

            ArrayList<Good> removingGoods = new ArrayList<>();
            for (int i=0; i<goodInMap.size(); i++) {
                if (goodInMap.get(i).getTimeOfOut() > 0){
                    goodInMap.get(i).setTimeOfOut(goodInMap.get(i).getTimeOfOut() - 1);
                    if (goodInMap.get(i).getTimeOfOut() == 0){
                        removingGoods.add(goodInMap.get(i));
//                        designMapForGoods(goodInMap.get(i) , false , false);
//                        goodInMap.remove(goodInMap.get(i));
                    }
                }
            }
            if (removingGoods.size()!=0)
                removeGoodsFromGoodInMap(removingGoods);

            timeCounter++;
            if (isLevelCompleted()) {
                finishLevel();
                return;
            }
            animalTime();
            if (truck.getTravel() > 0)
                truck.setTravel(truck.getTravel() - 1);
        }
        writeLogFile("Info", "turn in size " + timeCount);
    }

    private void removeGoodsFromGoodInMap(ArrayList<Good> removingGoods) {
        for (Good removingGood : removingGoods) {
            for (Good good : goodInMap) {
                if (removingGood.getName().equals(good.getName()) && removingGood.getX() == good.getX() && removingGood.getY() == good.getY()){
                    designMapForGoods(good, false, false);
                    goodInMap.remove(good);
                    break;
                }
            }
        }
    }

    public void addUser(String userName, String password) {
        User result = new User(userName, password, 1, 0);
        users.add(result);
        saveUsers();
    }

    public void setUser(String userName, String password) {
        for (User user1 : users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)){
                user = user1;
                cellar.setUser(user1);
                truck.setUser(user1);
                break;
            }
        }
    }

    public boolean isLevelCompleted(){
        if (level.getTaskCoins() > coins)
            return false;
        for (Good taskGood : level.getTaskGoods()) {
            if (taskGood.getCount() > cellar.goodSearcher(taskGood.getName()).getCount()){
                return false;
            }
        }
        for (int i = 0; i < level.getTaskAnimals().size(); i++) {
            if (level.getEachTaskAnimalCount().get(i) > animalCountOnMap(level.getTaskAnimals().get(i).getName())){
                return false;
            }
        }
        return true;
    }

    public void finishLevel(){
//        System.out.println("level finished!");
        if (time <= level.getMaxTime()){
            user.setExtraCoins(level.getExtraCoins());
        }else {
            user.setExtraCoins(0);
        }
        if (level.getLevelNumber() == user.getUnlockedLevel()) {
            user.setUnlockedLevel(user.getUnlockedLevel() + 1);
        }
        saveUsers();
        //feelan hamina be zehnam reside
    }

    public void createMissionsFile(){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        File help = new File("");
        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\missions.json");
        try {
            if (!jsonFile.exists())
                jsonFile.createNewFile();
            FileWriter fileWriter = new FileWriter(jsonFile);
            String jsonText;
            jsonText = ("total levels: "+maxLevel);
            for (Level level1 : levels) {
                jsonText += ("\nlevel "+level1.getLevelNumber() + ":");
                jsonText += "\ninitial coins: " + level1.getInitialCoin();
                if (level1.getTaskGoods().size() != 0) {
                    jsonText += "\ntask goods:";
                    jsonText += "\n" + gson.toJson(level1.getTaskGoods());
                }
                if (level1.getTaskAnimals().size() != 0) {
                    jsonText += "\ntask animals:";
                    for (int i = 0; i < level1.getTaskAnimals().size(); i++) {
                        jsonText += ("\n" + level1.getTaskAnimals().get(i).getName() + " *" + level1.getEachTaskAnimalCount().get(i));
                    }
                }
                if (level1.getWildAnimals().size() != 0) {
                    jsonText += "\nwild animals:";
                    ArrayList<String> valueNames = new ArrayList<>();
                    ArrayList<Integer> keys = new ArrayList<>();
                    for (BadWildAnimal value : level1.getWildAnimals().values()) {
                        valueNames.add(value.getName());
                    }
                    for (Integer integer : level1.getWildAnimals().keySet()) {
                        keys.add(integer);
                    }
                    for (int i = 0; i < level1.getWildAnimals().size(); i++) {
                        jsonText += "\n" + valueNames.get(i) + "_" + keys.get(i);
                    }
                }
                jsonText += ("\nlevel max time: " + level1.getMaxTime());
                jsonText += ("\nlevel extra coins(prize): " + level1.getExtraCoins());
            }
            fileWriter.write(jsonText);
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveUsers(){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        File help = new File("");
        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\users.json");

        try {
            if (!jsonFile.exists()){
                jsonFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(jsonFile);
            String jsonText = gson.toJson(users);
            if (!jsonText.equals("")) {
                fileWriter.write(jsonText);
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadUsers(){
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        File help = new File("");
        File jsonFile = new File (help.getAbsolutePath()+"\\Information\\users.json");
        if (jsonFile.exists()) {
            try {
                FileReader fileReader = new FileReader(jsonFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                String jsonString = new String("");
                while (line != null) {
                    jsonString += line;
                    line = bufferedReader.readLine();
                }
                Type arrayListOfUserObject = new TypeToken<ArrayList<User>>() {
                }.getType();
                users = gson.fromJson(jsonString, arrayListOfUserObject);
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getGrassCount(int i, int j) {
        int result = 0;
        String[] split;
        if (map[i][j] != null)
            split = map[i][j].split("#");
        else
            return 0;
        for (String s : split) {
            if (s.equals("grass")){
                result ++;
            }
        }
        return result;
    }

    public ArrayList<Good> getGoodsHere(int i, int j) {
        ArrayList<Good> result = new ArrayList<>();
        String[] split;
        if (map[i][j] != null)
            split = map[i][j].split("#");
        else
            return null;
        for (String s : split) {
            if (s.startsWith("G")){
                int index = createdGoodIndex(result, s.substring(1));
                if (index == -1){
                    result.add(new Good(s.substring(1), 1));
                }else{
                    result.get(index).setCount(result.get(index).getCount() + 1);
                }
            }
        }
        return result;
    }

    public ArrayList<Animal> getAnimalsHere(int i, int j) {
        ArrayList<Animal> result = new ArrayList<>();
        String[] split;
        if (map[i][j] == null)
            return null;
//        for (String s : split) {
//            if (s.startsWith("A")){
//                String mainAnimalName = s.substring(1);
//                if (mainAnimalName.equals("lion") || mainAnimalName.equals("bear") || mainAnimalName.equals("tiger")){
//                    result.add(new BadWildAnimal(mainAnimalName, 1));
//                }else if (mainAnimalName.equals("cat") || mainAnimalName.equals("dog")){
//                    result.add(new GoodWildAnimal(mainAnimalName));
//                }else{
//                    result.add(new DomesticAnimal(mainAnimalName));
//                }
//            }
//        }
        for (Animal animal : animalInMap) {
            if (animal.getX() == i && animal.getY() == j){
                result.add(animal);
            }
        }
        return result;
    }


    public int createdGoodIndex(ArrayList<Good> goods, String goodName){
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getName().equals(goodName)){
                return i;
            }
        }
        writeLogFile("Error","in this part good not found");
        return -1;
    }

    public int goodCountInCellarByName(String goodName){
        for (Good good : cellar.getGoods()) {
            if (good.getName().equals(goodName))
                return good.getCount();
        }
        writeLogFile("Error","Good in cellar not found");
        return -1;
    }

    public void writeLogFile(String eventType , String event){
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










    //GRAPHICS






}