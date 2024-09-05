import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class InputProcessor/* implements Runnable*/{
    Manager manager;
    Game game;
    public InputProcessor(Manager manager) {
        this.manager = manager;
    }
    private void ProcessWork(String factoryName){
        boolean existsFactory = false;
        for (Factory factory : manager.factories) {
            if (factory.getName().equalsIgnoreCase(factoryName)){
                if (factory.getWorkingRemainingTime() > 0){
                    manager.writeLogFile("Error", "Work command for working factory");
                    return;
                }
                if (manager.cellar.getGoods().size() != 0) {
                    for (Good good : manager.cellar.getGoods()) {
                        if (good.getName().equals(factory.getInputGoodName())) {
                            if (good.getCount() >= 1 /*factory.level*/) {
                                manager.startWork(factory);
                                manager.writeLogFile("Info", "Factory started working");
                                return;
                            } else {
                                manager.writeLogFile("Error", "Good lack for working factory");
                                return;
                            }
                        }
                    }
                }else{
                    System.out.println("Error . There is not enough good.");
                    manager.writeLogFile("Error", "Good lack for working factory");

                    return;
                }
                existsFactory = true;
                break;
            }
        }
        if (!existsFactory){
            System.out.println("Error . There is no built factory with this name.");
            manager.writeLogFile("Error", "Not existing factory name");
            return;
        }
    }

    private void processPlantGrass(int x, int y){
        if (x<1 || x>6 || y<1 || y>6){
            System.out.println("Error. Invalid point");
            manager.writeLogFile("Error" , "Invalid point");
            return;
        }
        if (manager.wellWater >= 1) {
            manager.plantGrass(x, y);
            System.out.println("Planted grass on x=" + x + " , y=" + y);
            manager.writeLogFile("Info", "Planted grass on " + x + "," + y);
        }
        else{
            System.out.println("Error. There is not enough water.");
            manager.writeLogFile("Error", "Water lack for planting");
        }
    }

    private void fillWell(){
        if (manager.fillingWellTimeCounter != 0){
            System.out.println("Well is already filling!");
            manager.writeLogFile("Error", "Repeated filling well");
            return;
        }
        if (manager.wellWater != 0) {
            System.out.println("Error. Well must be empty.");
            manager.writeLogFile("Error", "Error filling well");
            return;
        }
        else {
            manager.startFillingWell();
            System.out.println("Filling well...");
            manager.writeLogFile("Info", "Filled well");
            return;
        }
    }

    private void processBuildFactory(String factoryName){

        for (Factory factory : manager.factories) {
            if (factory.getName().equals(factoryName)){
                System.out.println("Error. This factory already exists.");
                manager.writeLogFile("Error", "Building an existing factory");
                return;
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
            default: {
                System.out.println("There is no factory with this name in the game!");
                manager.writeLogFile("Error", "Invalid inout for building factory");
                return;
            }
        }
        if (coinsRequired > manager.coins){
            System.out.println("Error. You don't have enough coins.");
            manager.writeLogFile("Error", "Coins lack for building factory");
            return;
        }else {
            manager.buildFactory(factoryName);
            System.out.println("Factory was built successfully!");
            manager.writeLogFile("Info", "Factory was built successfully");
        }
    }


    void runGame() {
        manager.addLevels();
        manager.createMissionsFile();
        manager.loadUsers();
        createWelcomeFrame();
//        boolean exit = false;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome !" +
//                " If you have an account enter 'LOG IN' , else enter 'SIGNUP' to create a new one ." +
//                "Enter 'EXIT' to quit game.");
//        String command = new String("");
//        command = scanner.nextLine();
//        while (!isValidCommand(command, "signup", "log in", "exit")){
//            System.out.println("Invalid command. Please enter another one:");
//            manager.writeLogFile("Error", "Invalid command");
//            command = scanner.nextLine();
//        }
//        if (command.equalsIgnoreCase("signup")){
//            processSignUp(scanner);
//        }else if (command.equalsIgnoreCase("log in")){
//            processLogin(scanner);
//        }else if (command.equalsIgnoreCase("exit")){
//            manager.saveUsers();
//            return;
//        }
    }

    //    private void processLogin(Scanner scanner, FrameCreator frame) {
//        System.out.println("Please enter your username:");
//
//
//        //GRAPHICS
//        JLabel enterUserName = new JLabel("Please enter your username:");
//        final int TEXT_HEIGHT = 25;
//        enterUserName.setBounds(frame.getX(), frame.getY(), frame.getWidth(), TEXT_HEIGHT);
//        enterUserName.setFont(new Font("MV Boli", Font.PLAIN, 35));
//
//        final int TEXT_AND_FIELD_GAP = 25;
//        final int TEXT_FIELD_WIDTH = 500;
//        final int TEXT_FIELD_HEIGHT = 50;
//
//        JTextField textField = new JTextField("username",25);
//        textField.setBounds(frame.getFrameCenter().x - TEXT_FIELD_WIDTH/2, enterUserName.getY() + TEXT_AND_FIELD_GAP,
//                TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT );
//        textField.setFont(new Font("MV Boli", Font.PLAIN, 35));
//
//        ArrayList<Component> components = new ArrayList<>();
//        components.add(enterUserName);
//        components.add(textField);
//
//        frame.setComponents("farm.jpg", components);
//
//        System.out.println("testing...");
//
//
//
//
//
//
//
//
//        String userName = scanner.nextLine();
//        while (!manager.existsUserName(userName) && (!userName.equalsIgnoreCase("exit"))){
//            System.out.println("This username does not exist. Please enter another one:");
//            manager.writeLogFile("Error", "Not existing username for login");
//            userName = scanner.nextLine();
//        }
//        if (userName.equalsIgnoreCase("exit")){
//            return;
//        }
//
//        System.out.println("Please enter your password:");
//        String password = scanner.nextLine();
//        while (!manager.isCorrectPass(userName, password) && (!password.equalsIgnoreCase("exit"))){
//            System.out.println("Password is not correct. Enter the password again:");
//            manager.writeLogFile("Error", "Incorrect password");
//            password = scanner.nextLine();
//        }
//        if (password.equalsIgnoreCase("exit")){
//            return;
//        }
//
//        manager.setUser(userName, password);
//        System.out.println("login successful!");
//        manager.writeLogFile("Info", "Logged in successfully");
//        processMenu(userName, password, scanner);
//
//    }
    private void createTextFieldFrame(FrameCreator frame, JLabel label,
                                      String labelText, JTextField textField,
                                      String fieldInitialText, String buttonText,
                                      ActionListener listener, String backgroundName){
        label.setText(labelText);
        label.setForeground(new Color(4, 88, 15));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLayout(null);
        final int TEXT_HEIGHT = 50;
        label.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        label.setFont(new Font("MV Boli", Font.PLAIN, 35));

        final int TEXT_AND_FIELD_GAP = 100;
        final int TEXT_FIELD_WIDTH = 500;
        final int TEXT_FIELD_HEIGHT = 50;

        textField.setText(fieldInitialText);
        textField.setBounds(frame.getFrameCenter().x - TEXT_FIELD_WIDTH/2,
                label.getY() + TEXT_AND_FIELD_GAP, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT );
        textField.setFont(new Font("MV Boli", Font.PLAIN, 35));
        textField.setHorizontalAlignment(JTextField.LEFT);


        final int BUTTON_AND_FIELD_GAP = 20;
        final int BUTTON_WIDTH = 150;
        final int BUTTON_HEIGHT = 60;

        JButton button = new JButton(buttonText);
        button.setBounds(textField.getX() - BUTTON_AND_FIELD_GAP - BUTTON_WIDTH,
                textField.getY(), BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBackground(Color.BLUE);
        button.setForeground(Color.YELLOW);
        button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        button.setFocusable(false);
        button.setVerticalAlignment(label.CENTER);
        button.setFont(new Font("MV Boli", Font.PLAIN, 20));
        button.addActionListener(listener);

        final int EXIT_AND_BOTTOM_GAP = 20;
        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(frame.getFrameCenter().x - BUTTON_WIDTH/2,
                label.getY() + label.getHeight() - BUTTON_HEIGHT - BUTTON_HEIGHT - EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.YELLOW);
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setFocusable(false);
        exitButton.setHorizontalAlignment(label.CENTER);
        exitButton.setVerticalAlignment(label.CENTER);
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton){
                    System.exit(0);
                }
            }
        });



        label.add(textField);
        label.add(button);
        label.add(exitButton);
        ArrayList<Component> components = new ArrayList<>();
        components.add(label);
        frame.setComponents(backgroundName, components);


    }

    private void processLogin(Scanner scanner) {
//        System.out.println("Please enter your username:");


        //GRAPHICS
        FrameCreator userNameFrame = new FrameCreator("userName", 1600, 800, Color.black,
                null, 3, false);

        JLabel enterUserName = new JLabel();
        JTextField userNameField = new JTextField();
        ActionListener userNameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                if (!manager.existsUserName(userName)) {
                    manager.printErrorOrInfo (game,"ERROR",
                            "This username does not exist. Please enter another one",
                            "Computer Error Alert-SoundBible.com-783113881.wav",true,"error.png");
//                    JOptionPane.showMessageDialog(enterUserName,
//                            "This username does not exist. Please enter another one",
//                            "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                }else{
                    userNameFrame.dispose();
                    FrameCreator passFrame = new FrameCreator("password",
                            1600,800, Color.black, null, 3, false);
                    JLabel enterPass = new JLabel();
                    JTextField passField = new JTextField();
                    ActionListener passListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            String password = passField.getText();
                            if (!manager.isCorrectPass(userName, password)){
                                manager.printErrorOrInfo (game,"ERROR",
                                        "Password is not correct. Enter the password again",
                                        "Computer Error Alert-SoundBible.com-783113881.wav",
                                        true,"error.png");

//                                JOptionPane.showMessageDialog(enterPass,
//                                        "Password is not correct. Enter the password again",
//                                        "ERROR",JOptionPane.ERROR_MESSAGE,
//                                        new ImageIcon("error.png"));
                            }else{
                                manager.setUser(userName, password);
                                manager.writeLogFile("Info", "Logged in successfully");
                                passFrame.dispose();
                                processMenu(userName, password, scanner);
                            }
                        }
                    };
                    createTextFieldFrame(passFrame, enterPass, "Please enter your password",passField,
                            "password","submit", passListener, "farm.jpg");
                }

            }
        };

        createTextFieldFrame(userNameFrame, enterUserName,"Please enter your username",userNameField,
                "username","submit", userNameListener, "farm.jpg");




        //CMD

//            System.out.println("Please enter your username:");
        //        String userName = scanner.nextLine();
        //        while (!manager.existsUserName(userName) && (!userName.equalsIgnoreCase("exit"))){
        //            System.out.println("This username does not exist. Please enter another one:");
        //            manager.writeLogFile("Error", "Not existing username for login");
        //            userName = scanner.nextLine();
        //        }
        //        if (userName.equalsIgnoreCase("exit")){
        //            return;
        //        }
        //
        //        System.out.println("Please enter your password:");
        //        String password = scanner.nextLine();
        //        while (!manager.isCorrectPass(userName, password) && (!password.equalsIgnoreCase("exit"))){
        //            System.out.println("Password is not correct. Enter the password again:");
        //            manager.writeLogFile("Error", "Incorrect password");
        //            password = scanner.nextLine();
        //        }
        //        if (password.equalsIgnoreCase("exit")){
        //            return;
        //        }
        //
        //        manager.setUser(userName, password);
        //        System.out.println("login successful!");
        //        manager.writeLogFile("Info", "Logged in successfully");
        //        processMenu(userName, password, scanner);
        //

    }

    private void processSignUp(Scanner scanner) {

        FrameCreator userNameFrame = new FrameCreator("userName", 1600, 800, Color.black,
                null, 3, false);

        JLabel enterUserName = new JLabel();
        JTextField userNameField = new JTextField();

        ActionListener userNameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                if (manager.existsUserName(userName)){
                    manager.printErrorOrInfo (game,"ERROR",
                            "This username already exists. Please enter another one",
                            "Computer Error Alert-SoundBible.com-783113881.wav",
                            true,"error.png");
//                    JOptionPane.showMessageDialog(userNameFrame,
//                            "This username already exists. Please enter another one",
//                            "ERROR",JOptionPane.ERROR_MESSAGE,
//                            new ImageIcon("error.png"));
                }
                else {
                    userNameFrame.dispose();
                    FrameCreator passFrame = new FrameCreator("password", 1600,
                            800, Color.black,
                            null, 3, false);
                    JLabel enterPass = new JLabel();
                    JTextField passField = new JTextField();
                    ActionListener passListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String password = passField.getText();
                            manager.addUser(userName , password);
                            manager.setUser(userName, password);
                            manager.writeLogFile("Info", "Signed up successfully");
                            processMenu(userName, password, scanner);
                        }
                    };

                    createTextFieldFrame(passFrame,enterPass,
                            "Please enter a password for your account",passField,
                            "password","submit",passListener,"farm.jpg");
                }
            }
        };

        createTextFieldFrame(userNameFrame,enterUserName,"Enter a username",userNameField,
                "username","submit",userNameListener,"farm.jpg");


        //CMD

//        System.out.println("Enter a username:");
//        String userName = new String(scanner.nextLine());
//        while (manager.existsUserName(userName)){
//            manager.writeLogFile("Error", "Existing userName for sign up");
//            System.out.println("This username already exists. Please enter another one:");
//            userName = scanner.nextLine();
//        }
//        System.out.println("Please enter a password for your account:");
//        String password = new String(scanner.nextLine());
//        manager.addUser(userName , password);
//        manager.setUser(userName, password);
//        System.out.println("Signup successful! You're now in menu section.");
//        manager.writeLogFile("Info", "Signed up successfully");
//        processMenu(userName, password, scanner);

    }

    private void processMenu(String userName, String password, Scanner scanner) {
//        User user = new User("", "", 0, 0);
//        for (User user1 : manager.users) {
//            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)) {
//                user = user1;
//                break;
//            }
//        }

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
        JButton logoutButton = new JButton("LOG OUT");
        JButton exitButton = new JButton("EXIT");

        startLevelButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                menuFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        settingsButton.setBounds(menuFrame.getFrameCenter().x-BUTTON_WIDTH/2,
                startLevelButton.getY()+startLevelButton.getHeight()+BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        logoutButton.setBounds(menuFrame.getFrameCenter().x-BUTTON_WIDTH/2,
                settingsButton.getY()+settingsButton.getHeight()+BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        exitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                menuFrame.getY()+menuFrame.getHeight()-BUTTON_HEIGHT-EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        startLevelButton.setBackground(Color.BLUE);
        settingsButton.setBackground(Color.BLUE);
        logoutButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        startLevelButton.setForeground(Color.YELLOW);
        settingsButton.setForeground(Color.YELLOW);
        logoutButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        startLevelButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        settingsButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        logoutButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        startLevelButton.setFocusable(false);
        settingsButton.setFocusable(false);
        logoutButton.setFocusable(false);
        exitButton.setFocusable(false);

        startLevelButton.setVerticalAlignment(mainLabel.CENTER);
        settingsButton.setVerticalAlignment(mainLabel.CENTER);
        logoutButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        startLevelButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        settingsButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        logoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
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
                manager.writeLogFile("Info", "Settings opened");
            }
        };
        ActionListener logoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processLogout(userName, password);
                manager.writeLogFile("Info", "Logged out");
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
        logoutButton.addActionListener(logoutListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(startLevelButton);
        mainLabel.add(settingsButton);
        mainLabel.add(logoutButton);
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

    private void createSelectLevelFrame(String userName, String password, Scanner scanner){
        User user = new User("", "", 0, 0);
        for (User user1 : manager.users) {
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
                manager.writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 1, scanner);
            }
        };
        ActionListener listener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                manager.writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 2, scanner);
            }
        };
        ActionListener listener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                manager.writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 3, scanner);
            }
        };
        ActionListener listener4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                manager.writeLogFile("Info", "Level selected");
                processStartLevel(userName, password, 4, scanner);
            }
        };
        ActionListener listener5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsFrame.dispose();
                manager.writeLogFile("Info", "Level selected");
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

    private void processStartLevel(String userName, String password, int levelNum, Scanner scanner) {
        User user = new User("", "", 0, 0);
        for (User user1 : manager.users) {
            if (user1.getUserName().equals(userName) && user1.getPassword().equals(password)) {
                user = user1;
                break;
            }
        }

        manager.startLevel(levelNum);
        manager.initializeFactories();
        game = new Game(manager);

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

    private void processBuy (String name)
    {
        if (name.equalsIgnoreCase("dog")){
            if(manager.coins >= 100){
                manager.coins -= 100;
                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("dog");
                manager.animalInMap.add(goodWildAnimal);
                manager.designMapForAnimals(goodWildAnimal, true);
                System.out.println("Dog is bought!");
                manager.writeLogFile("Info", "Bought dog");
            }else{
                System.out.println("Your coins is not enough");
                manager.writeLogFile("Error", "Coins lack for buying " + name);
            }
        }else if (name.equalsIgnoreCase("cat")){
            if(manager.coins >= 150){
                manager.coins -= 150;
                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("cat");
                manager.animalInMap.add(goodWildAnimal);
                manager.designMapForAnimals(goodWildAnimal, true);
                System.out.println("Cat is bought!");
                manager.writeLogFile("Info", "Bought cat");
            }else{
                System.out.println("Your coins is not enough");
                manager.writeLogFile("Error", "Coins lack for buying " + name);
            }
        } else if (name.equalsIgnoreCase("chicken"))
        {
            if (manager.coins>=100)
            {
                manager.coins-=100;
                DomesticAnimal domesticAnimal = new DomesticAnimal("chicken");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);
                System.out.println("Chicken is bought!");
                manager.writeLogFile("Info", "Bought chicken");
            }
            else
            {
                System.out.println("Your coins is not enough");
                manager.writeLogFile("Error", "Coins lack for buying " + name);
            }
        }
        else if (name.equalsIgnoreCase("turkey"))
        {
            if (manager.coins>=200)
            {
                manager.coins-=200;
                DomesticAnimal domesticAnimal = new DomesticAnimal("turkey");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);
                System.out.println("Turkey is bought!");
                manager.writeLogFile("Info", "Bought turkey");
            }
            else
            {
                System.out.println("Your coins is not enough");
                manager.writeLogFile("Error", "Coins lack for buying " + name);
            }
        }
        else if (name.equalsIgnoreCase("buffalo"))
        {
            if (manager.coins>=400)
            {
                manager.coins-=400;
                DomesticAnimal domesticAnimal = new DomesticAnimal("buffalo");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);
                System.out.println("Buffalo is bought!");
                manager.writeLogFile("Info", "Bought buffalo");
            }
            else
            {
                System.out.println("Your coins is not enough");
                manager.writeLogFile("Error", "Coins lack for buying " + name);
            }
        }
    }

    private void processCage (int x,int y)
    {
        manager.cager(x,y);
    }
    private void processSell()
    {
        manager.truck.sell();
        manager.coins+=manager.truck.sellPrice();
        System.out.println("Truck went");
        manager.writeLogFile("Info", "Truck went (processSell)");
    }
    private void processUnload(String name)
    {
        if (manager.truck.getTravel()==0)
        {
            if (name.equalsIgnoreCase("chicken")||name.equalsIgnoreCase("turkey")||name.equalsIgnoreCase("buffalo"))
            {
                manager.truck.removeAnimal(name);
                manager.animalInMap.add(new DomesticAnimal(name));
                manager.designMapForAnimals(manager.animalInMap.get(manager.animalInMap.size()-1),true);
                manager.writeLogFile("Info","Unloaded animal : " + name);
            }
            else if (name.equalsIgnoreCase("icecream")||name.equalsIgnoreCase("milk")||name.equalsIgnoreCase("egg")||name.equalsIgnoreCase("feather")||name.equalsIgnoreCase("shirt")||name.equalsIgnoreCase("p_milk")||name.equalsIgnoreCase("cloth")||name.equalsIgnoreCase("flour"))
            {
                System.out.println("Unloaded good : " + name);
                manager.truck.truckToCellar(name, manager.cellar);
                manager.writeLogFile("Info", "Unloaded good : " + name);
            }
            else
            {
                System.out.println("This item does not exist");
                manager.writeLogFile("Error", "Not existing item unload");
            }
        }
        else {
            System.out.println("Truck is in travel...");
            manager.writeLogFile("Error", "Unloading Truck in travel");
        }
    }
    private void processLoad(String name)
    {
        if (name.equals("chicken")||name.equals("turkey")||name.equals("buffalo")) {
            for (int i = 0; i < manager.animalInMap.size(); i++)
            {
                if (manager.animalInMap.get(i).getName().equals(name))
                {
                    manager.designMapForAnimals(manager.animalInMap.get(i),false);
                    manager.truck.addAnimal(name);
                    manager.animalInMap.remove(i);
                    System.out.println("Truck loaded!");
                    manager.writeLogFile("Info", "Truck loaded with " + name);
                    break;
                }
            }
        }
        else
        {
            manager.cellar.cellarToTruck(name, manager.truck);
        }

    }

    private void processGetGameCommands(User user, Scanner scanner) {
//        Thread thread = new Thread(r);
//        thread.start();
//        thread.run();


//        new Game(manager);


//        System.out.println("Enter a command:");
//        String command = new String(scanner.nextLine());
//        while (!command.equalsIgnoreCase("exit")){
//            if (command.matches("(b|B)(u|U)(y|Y) .+")){
//                processBuy(command.substring(4));
//            }else if (command.matches("(p|P)(i|I)(c|C)(k|K)(u|U)(p|P) \\d \\d")){
//                processCatch(Character.getNumericValue(command.charAt(7)), Character.getNumericValue(command.charAt(9)));
//            }else if (command.equalsIgnoreCase("well")){
//                fillWell();
//            }else if (command.matches("(p|P)(l|L)(a|A)(n|N)(t|T) \\d \\d")){
//                processPlantGrass(Character.getNumericValue(command.charAt(6)), Character.getNumericValue(command.charAt(8)));
//            }else if (command.matches("(w|W)(o|O)(r|R)(k|K) \\w+")){
//                ProcessWork(command.substring(5));
//            }else if (command.matches("(b|B)(u|U)(i|I)(l|L)(d|D) \\w+")){
//                processBuildFactory(command.substring(6));
//            }else if (command.matches("(u|U)(p|P)(g|G)(r|R)(a|A)(d|D)(e|E) \\w+")){
//                processUpgradeFactory(command.substring(8));
//            }else if (command.matches("(c|C)(a|A)(g|G)(e|E) \\d \\d")){
//                processCage(Character.getNumericValue(command.charAt(5)),Character.getNumericValue(command.charAt(7)));
//            }else if (command.matches("(t|T)(u|U)(r|R)(n|N) \\d+")){
//                manager.turn(Integer.parseInt(command.substring(5)));
//                processShowDetails();
//                manager.writeLogFile("Info", "Turned time " + command.substring(5)
//                        + "times and details showed");
//            }else if (command.matches("(t|T)(r|R)(u|U)(c|C)(k|K) (l|L)(o|O)(a|A)(d|D) \\w+")){
//                processLoad(command.substring(11));
//            }else if (command.matches("(t|T)(r|R)(u|U)(c|C)(k|K) (u|U)(n|N)(l|L)(o|O)(a|A)(d|D) \\w+")){
//                processUnload(command.substring(13));
//            }else if (command.matches("(t|T)(r|R)(u|U)(c|C)(k|K) (g|G)(o|O)")){
//                processSell();
//            }else if (command.equalsIgnoreCase("inquiry")){
//                processShowDetails();
//                manager.writeLogFile("Info", "Details showed");
//            }else{
//                System.out.println("Invalid command. Please enter another one:");
//                manager.writeLogFile("Error", "Invalid command");
//            }
//
//            if (manager.isLevelCompleted()){
//                manager.finishLevel();
//                System.out.println("Level is completed!");
//                manager.writeLogFile("Info", "Level accomplished");
//                System.out.println("Enter 'menu' to get back to the menu. If you want to exit the game, enter 'exit'");
//                String finalCommand = scanner.nextLine();
//                while (!finalCommand.equalsIgnoreCase("menu") && !finalCommand.equalsIgnoreCase("exit")){
//                    System.out.println("Invalid command. Please enter another one:");
//                    manager.writeLogFile("Error", "Invalid command");
//                }
//                if (finalCommand.equalsIgnoreCase("exit")){
//                    return;
//                }else{
//                    processMenu(user.getUserName(), user.getPassword(), scanner);
//                }
//            }
//            command = scanner.nextLine();
//        }
//        manager.saveUsers();
    }

    private void processShowDetails() {
        System.out.println("GoodInMapSize: " + manager.goodInMap.size());
        System.out.println("Time : "+manager.time);

//        System.out.println("Grasses count:");
//        int[][] grasses = manager.processGetGrasses();
//        for (int i = 0; i < grasses.length; i++) {
//            for (int i1 = 0; i1 < grasses[i].length; i1++) {
//                System.out.print(grasses[i][i1] + "  ");
//            }
//            System.out.println();
//        }
//        HashMap<String, ArrayList<Good>> goodsHashMap = new HashMap<>();
//        HashMap<String, ArrayList<Animal>> animalsHashMap = new HashMap<>();
        int[][] grasses = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grasses[i][j] = manager.getGrassCount(i, j);

//                ArrayList<Good> goodsHere = manager.getGoodsHere(i, j);
//                if (goodsHere != null) {
//                    goodsHashMap.put("[" + i + " " + j + "]", goodsHere);
//                }
//                ArrayList<Animal> animalsHere = manager.getAnimalsHere(i, j);
//                if (animalsHere != null) {
//                    animalsHashMap.put("[" + i + " " + j + "]", animalsHere);
//                }
            }
        }
//        System.out.println("animalsHashMapSize: "+animalsHashMap.size());
//        System.out.println("keyset: "+animalsHashMap.keySet());
//        System.out.println("values: "+animalsHashMap.values());
        System.out.println("Grasses count:");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(grasses[j][i] + "  ");
            }
            System.out.println();
        }
        if (manager.goodInMap.size() != 0) {
            System.out.println("Goods on map:");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    ArrayList<String> usedGoodNames = new ArrayList<>();
                    for (Good good : manager.goodInMap) {
                        if (good.getX()-1 == i && good.getY()-1 == j){
                            if (!usedGoodNames.contains(good.getName())){
                                System.out.println(good.getName() + " *" + manager.goodsCountHereByName(good.getName(), i , j)
                                        + " [" + good.getX() + " " + good.getY() + "]");
                                usedGoodNames.add(good.getName());
                            }
                        }
                    }
//                    if (goodsHashMap.get("[" + i + " " + j + "]") != null) {
//                        for (Good good : goodsHashMap.get("[" + i + " " + j + "]")) {
//                            System.out.println(good.getName() + " [" + (i+1) + " " + (j+1) + "]" + " * " + good.getCount());
//                        }
//                    }
                }
            }
        }
        if (manager.animalInMap.size() != 0) {
            System.out.println("Animals on map:");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
//                    if (animalsHashMap.get("[" + i + " " + j + "]") != null) {
//                        for (Animal animal : animalsHashMap.get("[" + i + " " + j + "]")) {
//                            String animalName = animal.getName();
//                            System.out.print(animalName + " ");
//                            if (animalName.equals("lion") || animalName.equals("bear") || animalName.equals("tiger")) {
//                                System.out.print(((BadWildAnimal) animal).getCagesRequiredCount() - ((BadWildAnimal) animal).getCagesCount());
//                            } else if (animalName.equals("chicken") || animalName.equals("turkey") || animalName.equals("buffalo")) {
//                                System.out.print(((DomesticAnimal) animal).getLife());
//                            }
//
//                            System.out.println(" [" + (i+1) + " " + (j+1) + "]");
//                        }
//                    }
                    for (Animal animal : manager.animalInMap) {
                        if (animal.getX()-1 == i && animal.getY()-1 == j){
                            String animalName = animal.getName();
                            if (animalName.equals("lion") || animalName.equals("bear") || animalName.equals("tiger")) {
                                System.out.println(animalName + " "
                                        + (((BadWildAnimal) animal).getCagesRequiredCount() - ((BadWildAnimal) animal).getCagesCount())
                                        + " " + "[" + animal.getX() + " " + animal.getY() + "]");
                            } else if (animalName.equals("chicken") || animalName.equals("turkey") || animalName.equals("buffalo")) {
                                System.out.println(animalName + " "
                                        + ((DomesticAnimal) animal).getLife()
                                        + " " + "[" + animal.getX() + " " + animal.getY() + "]");
                            } else{
                                System.out.println(animalName + " " + "[" + animal.getX() + " " + animal.getY() + "]");
                            }
                        }
                    }
                }
            }
        }


        System.out.println("Your coins: "+manager.coins +
                (manager.level.getTaskCoins() == 0 ? "":"/" + manager.level.getTaskCoins()));

        if (manager.level.getTaskGoods().size() != 0){
            System.out.println("Good(s) to collect:");
            for (Good taskGood : manager.level.getTaskGoods()) {
                System.out.println(taskGood.getName() + " : "
                        + manager.goodCountInCellarByName(taskGood.getName()) + "/" + taskGood.getCount());
            }
        }

        if (manager.level.getTaskAnimals().size() != 0){
            System.out.println("Animal(s) to collect:");
            for (int i = 0; i < manager.level.getTaskAnimals().size(); i++) {
                System.out.println(manager.level.getTaskAnimals().get(i).getName() + " : "
                        + manager.animalCountOnMap(manager.level.getTaskAnimals().get(i).getName())
                        + "/" + manager.level.getEachTaskAnimalCount().get(i));
            }
        }



    }

    private void processUpgradeFactory(String factoryName) {
        boolean existsFactory = false;
        for (Factory factory : manager.factories) {
            if (factory.getName().equals(factoryName)){
                existsFactory = true;
                break;
            }
        }
        if (!existsFactory){
            System.out.println("Error. This factory does not exist.");
            manager.writeLogFile("Error", "Not existing factory upgrade");
        }
        manager.upgradeFactory(factoryName);
        System.out.println(factoryName + " upgraded successfully.");
        manager.writeLogFile("Info", "Upgraded factory successfully");
    }

    private void processCatch(int x, int y) {
        if (manager.map[x-1][y-1] == null){
            System.out.println("There is nothing here to pickup!");
            manager.writeLogFile("Error", "Picked up nothing");
            return;
        }
        boolean existsAnyGood = false;
        for (int i=0; i<manager.map[x-1][y-1].length(); i++){
            if (manager.map[x-1][y-1].charAt(i) == 'G'){
                existsAnyGood = true;
                manager.Catch(x, y);
                System.out.println("Goods were picked up.");
                break;
            }
        }
        if (!existsAnyGood){
            manager.writeLogFile("Error", "No good in the point for catch");
            System.out.println("There is no good in this point!");
        }
    }

    private void processShowSettings() {
    }

    private void processLogout(String userName, String password) {
        manager.user = null;
        manager.saveUsers();
        runGame();
    }

    private boolean isValidCommand(String command , String... validCommands) {
        for (String validCommand : validCommands) {
            if (command.equalsIgnoreCase(validCommand))
                return true;
        }
        return false;
    }













    //GRAPHICS






    public void createWelcomeFrame(){
        FrameCreator frame = new FrameCreator("Welcome frame", 1600, 800, Color.BLACK,
                null, 3, false);
//        JLabel backgroundLabel = new JLabel();
//        System.out.println("....");

        JLabel initialText = new JLabel("Welcome ! If you have an account press 'LOG IN' , else press 'SIGNUP' to create a new one .Press 'EXIT' to quit the game.");
        initialText.setForeground(new Color(78, 65, 163));
        initialText.setFont(new Font("MV Boli", Font.PLAIN,25));
        initialText.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        initialText.setHorizontalAlignment(JLabel.CENTER);
        initialText.setVerticalAlignment(JLabel.TOP);
        initialText.setLayout(null);


        JButton loginButton = new JButton("LOG IN");
        JButton signupButton = new JButton("SIGN UP");
        JButton exitButton = new JButton("EXIT");

        final int TEXT_AND_BUTTON1_GAP = 150;
        final int BUTTONS_WIDTH = 350;
        final int BUTTONS_HEIGHT = 100;
        final int BUTTONS_GAP = 20;
        final int EXIT_AND_BOTTOM_GAP = 50;

        loginButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, frame.getY() + TEXT_AND_BUTTON1_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        signupButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, loginButton.getY() + loginButton.getHeight() + BUTTONS_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        exitButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, frame.getY()+frame.getHeight()-BUTTONS_HEIGHT-EXIT_AND_BOTTOM_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);

        loginButton.setFocusable(false);
        signupButton.setFocusable(false);
        exitButton.setFocusable(false);

        loginButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        signupButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20));

        loginButton.setForeground(Color.YELLOW);
        signupButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        loginButton.setBackground(Color.blue);
        signupButton.setBackground(Color.blue);
        exitButton.setBackground(Color.blue);

        loginButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        signupButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        loginButton.setVerticalAlignment(initialText.CENTER);
        signupButton.setVerticalAlignment(initialText.CENTER);
        exitButton.setVerticalAlignment(initialText.CENTER);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton){
//                    frame.setComponents("farm.jpg", null, true);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
//                    System.exit(0);
                    frame.dispose();
                    processLogin(new Scanner(System.in));
//                    loginButton.removeAll();
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processSignUp(new Scanner(System.in));
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton){
                    manager.saveUsers();
                    System.exit(0);
                }
            }
        });

//        JLayeredPane pane = new JLayeredPane();
//        pane.add(loginButton);
//        pane.add(signupButton);
//        pane.add(exitButton);
//        pane.add(initialText);
//        frame.add(pane);

//        frame.add(initialText, JLayeredPane.DRAG_LAYER);

        initialText.add(loginButton);
        initialText.add(signupButton);
        initialText.add(exitButton);

//        System.out.println("adding components...");
        ArrayList<Component> components = new ArrayList<>();
        components.add(initialText);
//        components.add(loginButton);
//        components.add(signupButton);
//        components.add(exitButton);
//        System.out.println("components added!");

        frame.setComponents("farm.jpg", components);

//        frame.add(initialText);
//        frame.add(loginButton/*, JLayeredPane.DRAG_LAYER*/);
//        frame.add(signupButton/*, JLayeredPane.DRAG_LAYER*/);
//        frame.add(exitButton/*, JLayeredPane.DRAG_LAYER*/);
//        frame.add(initialText/*, JLayeredPane.DRAG_LAYER*/);
//        System.out.println("center point:\n" + frame.getFrameCenter().x +" "+ frame.getFrameCenter().y);
//        System.out.println("login button point:");
//        System.out.println(loginButton.getX() + " " + loginButton.getY());


    }

//    @Override
//    public void run(){
////        while(true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("turning (main run)...");
//            manager.turn(1);
//            game.turn();
//            SwingUtilities.updateComponentTreeUI(game);
//            processShowDetails();
////        }
//    }
//    public void paint(Graphics g){
//        Graphics2D graphics2D = (Graphics2D) g;
//        Image background = new ImageIcon("farm.htm").getImage();
//        graphics2D.drawImage(background,0,0,null);
//    }

}
