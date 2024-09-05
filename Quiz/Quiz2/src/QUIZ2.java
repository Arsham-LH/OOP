import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
    public class QUIZ2 {
        static class Zombie {
            private static final int MAX_HEALTH = 120;
            private int health;
            private int row;
            private int col;

            public Zombie(int row, int col) {
                this.row = row;
                this.col = col;
                Random rand = new Random();
                health = rand.nextInt(MAX_HEALTH) + 1;
            }

            void tackDamage(int damage) {
                health -= damage;
            }
        }

        static class Bomb {
            String[] types = {"common", "rare", "super"};
            String type;
            private int price;
            private int damage;
            private int row;
            private int col;

            public Bomb(int row, int col) {
                this.row = row;
                this.col = col;
                type = new String("common");
                price = 25;
                damage = 25;
            }

            public Bomb(int row, int col, String type) {
                this.type = type;
                this.row = row;
                this.col = col;
                switch (type) {
                    case "common":
                        price = 25;
                        damage = 25;
                        break;
                    case "rare":
                        price = 75;
                        damage = 75;
                        break;
                    case "super":
                        price = 150;
                        damage = 150;
                        break;
                }
            }
        }

        static class InputProcessor {
            Manager manager = new Manager();
            int remainingZombies = manager.MAX_ZOMBIES;

            String showMainDisplay(int level, Scanner scanner) {
                System.out.printf("Enter your command : ");
                String command = new String(scanner.nextLine());
                return command;
            }

            void showDisplay(Manager manager, int level) {
                Random zombieRand = new Random();
                boolean addNewZombie = false;
                if (zombieRand.nextInt(10) <= 7 && remainingZombies > 0) {
                    remainingZombies--;
                    addNewZombie = true;
                    Zombie newZombie = new Zombie(zombieRand.nextInt(5), 29);
                    manager.zombies.add(newZombie);
                }

                System.out.println("----------------------------------");
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 34; j++) {
                        if (j == 0 || j == 33)
                            System.out.printf("%x", i + 1);
                        else if (j == 1 || j == 32)
                            System.out.printf(":");
                        else {
                            int zombieIndex = -1;
                            boolean isHereZombie = false;
                            for (int k = 0; k < manager.zombies.size(); k++) {
                                if (manager.zombies.get(k).row == i && manager.zombies.get(k).col+2 == j) {
                                    isHereZombie = true;
                                    zombieIndex = k;
                                }
                            }
                            int bombIndex = -1;
                            boolean isHereBomb = false;
                            for (int k2 = 0; k2 < manager.bombs.size(); k2++) {
                                if (manager.bombs.get(k2).row == i && manager.bombs.get(k2).col+2 == j) {
                                    isHereBomb = true;
                                    bombIndex = k2;
                                }
                            }

                            if (isHereZombie && isHereBomb) {
                                if (manager.bombs.get(bombIndex).damage >= manager.zombies.get(zombieIndex).health) {
                                    System.out.print(" ");
                                    manager.zombies.remove(zombieIndex);
                                    manager.playerCoins += 15;
                                } else {
                                    System.out.print("<");
                                    manager.zombies.get(zombieIndex).health -= manager.bombs.get(bombIndex).damage;
                                }
                                manager.bombs.remove(bombIndex);
                            } else if (isHereBomb && !isHereZombie)
                                System.out.print(manager.bombs.get(bombIndex).type.charAt(0));
                            else if (isHereZombie && !isHereBomb)
                                System.out.print("<");
                            else
                                System.out.print(" ");


                        }
                    }
                    System.out.println();
                }
                System.out.println("----------------------------------");
                System.out.println("step " + level + " , you have " + manager.playerCoins + " coins\nRemaining zombies = " + remainingZombies);
            }

            private void run() {
                int level = 1;
                showDisplay(manager, level);
                Scanner scanner = new Scanner(System.in);
                String command = new String(showMainDisplay(level, scanner));
                boolean gameLost = false;
                boolean gameWon = false;
                while (!command.equals("exit") && (gameLost == false) && (gameWon == false)) {
                    boolean errorFounded = false;

                    if (command.matches("put bomb [1-5] [0-9]{1,2}"))
                        manager.putBomb(command.charAt(9)-'0', (command.charAt(11)-'0') * (command.length() <=12 ? 1 : 10) + (command.length() <= 12 ? 0 : (command.charAt(12)-'0')), "common");
                    else if (command.matches("put bomb [1-5] [0-9]{1,2} common"))
                        manager.putBomb(command.charAt(9)-'0', (command.charAt(11)-'0') *
                                (command.charAt(12)<='9' && command.charAt(12)>='0' ? 10 : 1) + (command.charAt(12)>'9' || command.charAt(12)<'0' ? 0 : (command.charAt(12)-'0')),"common");
                    else if (command.matches("put bomb [1-5] [0-9]{1,2} rare"))
                        manager.putBomb(command.charAt(9)-'0', (command.charAt(11)-'0') *
                                (command.charAt(12)<='9' && command.charAt(12)>='0' ? 10 : 1) + (command.charAt(12)>'9' || command.charAt(12)<'0' ? 0 : (command.charAt(12)-'0')),"rare");
                    else if (command.matches("put bomb [1-5] [0-9]{1,2} super"))
                        manager.putBomb(command.charAt(9)-'0', (command.charAt(11)-'0') *
                                (command.charAt(12)<='9' && command.charAt(12)>='0' ? 10 : 1) + (command.charAt(12)>'9' || command.charAt(12)<'0' ? 0 : (command.charAt(12)-'0')),"super");

                    else if (command.matches("check zombie [1-5] [0-9]{1,2}")) {
                        if (manager.readZombieLife(command.charAt(13) - '0'
                                , (command.charAt(15) - '0') * (command.length() <=16 ? 1 : 10) +
                                        (command.length() <= 16 ? 0 : command.charAt(16) - '0'), errorFounded) >= 0)
                            System.out.println("selected zombie life : "+manager.readZombieLife(command.charAt(13) - '0'
                                    , (command.charAt(15) - '0') * (command.length() <=16 ? 1 : 10) +
                                            (command.length() <= 16 ? 0 : command.charAt(16) - '0'), errorFounded));
                        else {
                            System.out.println("No zombies in that point . Please enter another command");
                            errorFounded = true;
                        }
                    }
                    else if (command.equals("request coin"))
                        manager.requestCoin();
                    else {
                        System.out.println("invalid command . Please enter another one :");
                        errorFounded = true;
                    }
                    if (!errorFounded) {
                        level++;
                        for (int i = 0; i < manager.zombies.size(); i++) {
                            manager.zombies.get(i).col--;
                        }
                        showDisplay(manager, level);
                        for (int i = 0; i < manager.zombies.size(); i++) {
                            if (manager.zombies.get(i).col < 2)
                                gameLost = true;
                        }
                        if (manager.zombies.size() == 0 && remainingZombies == 0)
                            gameWon = true;

                        System.out.print("Enter your command : ");
                    }
                    command = scanner.nextLine();
                }
                if (gameLost)
                    System.out.println("You Lose!");
                else if (gameWon)
                    System.out.println("You won!");
            }
        }


        static class Manager {
            private String playerName;
            private int playerCoins = 500;
            private ArrayList<Zombie> zombies = new ArrayList<>(20);
            private ArrayList<Bomb> bombs = new ArrayList<>(100);
            private int[][] map;
            private int MAX_ROW = 5;
            private int MAX_COLUMN = 30;
            private String status;
            private Random random;
            private int MAX_ZOMBIES = 20;
            private int zombiesCounter;

            private void putBomb(int row, int col, String type) {
                Bomb newBomb = new Bomb(row-1, col-1, type);
                bombs.add(newBomb);
                playerCoins-=newBomb.price;
            }

            private int readZombieLife(int row, int col, boolean errorFounded) {
                for (int i = 0; i < zombies.size() /*&& isThereZombie == false*/; i++) {
                    if (zombies.get(i).row == row-1 && zombies.get(i).col == col-1) {
                        return zombies.get(i).health;
                    }
                }
                return -1;
            }

            private void requestCoin() {
                Random coinRand = new Random();
                if (coinRand.nextInt(10) <= 5)
                    playerCoins += 50;
                else
                    System.out.println("Request for coins was rejected .");
            }


        }

        public static void main(String[] args) {
            InputProcessor inputProcessor = new InputProcessor();
            inputProcessor.run();
        }

    }

