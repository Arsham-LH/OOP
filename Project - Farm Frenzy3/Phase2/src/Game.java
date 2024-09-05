import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;
import java.util.Timer;

public class Game extends JFrame implements MouseListener,Runnable {
    JPanel panel = new JPanel();
    int wid;
    int height;
    JMenuBar menuBar;
    JMenu menu ;
    JMenu pause ;
    Clip clip ;

    private Manager manager;
    JLayeredPane layeredPane = new JLayeredPane();

    ImageIcon groundIcon = new ImageIcon("ground.png");
    ImageIcon grassIcon = new ImageIcon("flowerGrass_PS_R2.png");

    JLabel[][] gamePlant = new JLabel[6][6];
    JLabel[][] gameFactory = new JLabel[2][3];
    JLabel truckLabel = new JLabel();
    JLabel cellarLabel = new JLabel();
    JLabel wellLabel = new JLabel();

    ArrayList<JLabel> animalLabels = new ArrayList<>();
    ArrayList<JLabel> cageLabels = new ArrayList<>();
    ArrayList<JLabel> detailsLabels = new ArrayList<>();
    ArrayList<JProgressBar> livesBar = new ArrayList<>();
    ArrayList<JLabel> goodLabels = new ArrayList<>();

    JButton buyChickenButton = new JButton("100");
    JButton buyTurkeyButton = new JButton("200");
    JButton buyBuffaloButton = new JButton("400");
    JButton buyCatButton = new JButton("150");
    JButton buyDogButton = new JButton("100");

    JProgressBar wellBar = new JProgressBar(JProgressBar.HORIZONTAL,0,5);
    JProgressBar[][] factoryWorkingBars = new JProgressBar[2][3];
    public Game(Manager manager) throws HeadlessException {
        this.manager = manager;
        ImageIcon gameLogo = new ImageIcon("cow logo.png");
        this.setIconImage(gameLogo.getImage());
        this.setSize(1200, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        menu = new JMenu("menu");
        pause = new JMenu("pause");
        wid = 70 * this.getWidth() / 1000;
        height = 50 *this.getHeight()/600;
        File musicPath = new File("AnyConv_com_32_Bonus_''Glou_Glou''_Reprise.wav");
        if (musicPath.exists())
        {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.setFramePosition(0);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch (Exception e)
            {
//                System.out.println(1);
            }
        }



        this.setBackground(Color.BLACK);
        this.setVisible(true);
        layeredPane.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        panel.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        panel.setLayout(null);

        panel.add(layeredPane);

        ImageIcon backgroundIcon = new ImageIcon("back6_R.png");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight());
//        System.out.println(backgroundIcon == null);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        final int SHOP_BUTTONS_WIDTH = 80 * this.getWidth() / 1000;
        final int SHOP_BUTTONS_HEIGHT = 70 * this.getHeight() / 600;
        final int SHOP_AND_LEFT_GAP = 10 * this.getWidth() / 1000;
        final int SHOP_AND_TOP_GAP = 15 * this.getHeight() / 600;

        ImageIcon chickenIcon = new ImageIcon("chicken_shop_PS_R.png");
        ImageIcon turkeyIcon = new ImageIcon("turkey_shop_PS_R.png");
        ImageIcon buffaloIcon = new ImageIcon("buffalo_shop_PS_R.png");
        ImageIcon catIcon = new ImageIcon("cat_shop_PS_R.png");
        ImageIcon dogIcon = new ImageIcon("dog_shop_PS_R.png");

        buyChickenButton.setIcon(chickenIcon);
        buyTurkeyButton.setIcon(turkeyIcon);
        buyBuffaloButton.setIcon(buffaloIcon);
        buyCatButton.setIcon(catIcon);
        buyDogButton.setIcon(dogIcon);

        buyChickenButton.setBounds(this.getX()+SHOP_AND_LEFT_GAP,this.getY()+SHOP_AND_TOP_GAP,
                SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
        buyTurkeyButton.setBounds(buyChickenButton.getX()+ buyChickenButton.getWidth(),
                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
        buyBuffaloButton.setBounds(buyTurkeyButton.getX()+ buyTurkeyButton.getWidth(),
                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
        buyCatButton.setBounds(buyBuffaloButton.getX()+ buyBuffaloButton.getWidth(),
                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
        buyDogButton.setBounds(buyCatButton.getX()+ buyCatButton.getWidth(),
                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);

        buyChickenButton.setFont(new Font("MV Boli", Font.PLAIN,20));
        buyTurkeyButton.setFont(new Font("MV Boli", Font.PLAIN,20));
        buyBuffaloButton.setFont(new Font("MV Boli", Font.PLAIN,20));
        buyCatButton.setFont(new Font("MV Boli", Font.PLAIN,20));
        buyDogButton.setFont(new Font("MV Boli", Font.PLAIN,20));

        buyChickenButton.setVerticalTextPosition(JLabel.BOTTOM);
        buyTurkeyButton.setVerticalTextPosition(JLabel.BOTTOM);
        buyBuffaloButton.setVerticalTextPosition(JLabel.BOTTOM);
        buyCatButton.setVerticalTextPosition(JLabel.BOTTOM);
        buyDogButton.setVerticalTextPosition(JLabel.BOTTOM);

        buyChickenButton.setHorizontalTextPosition(JLabel.CENTER);
        buyTurkeyButton.setHorizontalTextPosition(JLabel.CENTER);
        buyBuffaloButton.setHorizontalTextPosition(JLabel.CENTER);
        buyCatButton.setHorizontalTextPosition(JLabel.CENTER);
        buyDogButton.setHorizontalTextPosition(JLabel.CENTER);

        buyChickenButton.setFocusable(false);
        buyTurkeyButton.setFocusable(false);
        buyBuffaloButton.setFocusable(false);
        buyCatButton.setFocusable(false);
        buyDogButton.setFocusable(false);

        buyChickenButton.setBackground(Color.blue);
        buyTurkeyButton.setBackground(Color.blue);
        buyBuffaloButton.setBackground(Color.blue);
        buyCatButton.setBackground(Color.blue);
        buyDogButton.setBackground(Color.blue);

        buyChickenButton.setForeground(Color.YELLOW);
        buyTurkeyButton.setForeground(Color.YELLOW);
        buyBuffaloButton.setForeground(Color.YELLOW);
        buyCatButton.setForeground(Color.YELLOW);
        buyDogButton.setForeground(Color.YELLOW);

        buyChickenButton.setBorder(BorderFactory.createEtchedBorder());
        buyTurkeyButton.setBorder(BorderFactory.createEtchedBorder());
        buyBuffaloButton.setBorder(BorderFactory.createEtchedBorder());
        buyCatButton.setBorder(BorderFactory.createEtchedBorder());
        buyDogButton.setBorder(BorderFactory.createEtchedBorder());

        enableAndDisableShopButtons();

        ActionListener buyChickenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.coins-=100;
                enableAndDisableShopButtons();

                DomesticAnimal domesticAnimal = new DomesticAnimal("chicken");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);

                manager.printErrorOrInfo (layeredPane,"BUY SUCCESSFUL",
                        "Chicken is bought!",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(layeredPane,
//                        "Chicken is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Bought chicken");
            }
        };
        ActionListener buyTurkeyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.coins-=200;
                enableAndDisableShopButtons();

                DomesticAnimal domesticAnimal = new DomesticAnimal("turkey");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);

                manager.printErrorOrInfo (layeredPane,"BUY SUCCESSFUL",
                        "Turkey is bought!",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(layeredPane,
//                        "Turkey is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Bought turkey");
            }
        };
        ActionListener buyBuffaloListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.coins-=400;
                enableAndDisableShopButtons();

                DomesticAnimal domesticAnimal = new DomesticAnimal("buffalo");
                manager.animalInMap.add(domesticAnimal);
                manager.designMapForAnimals(domesticAnimal,true);

                manager.printErrorOrInfo (layeredPane,"BUY SUCCESSFUL",
                        "Buffalo is bought!",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(layeredPane,
//                        "Buffalo is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Bought buffalo");
            }
        };
        ActionListener buyCatListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.coins-=150;
                enableAndDisableShopButtons();

                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("cat");
                manager.animalInMap.add(goodWildAnimal);
                manager.designMapForAnimals(goodWildAnimal, true);

                manager.printErrorOrInfo (layeredPane,"BUY SUCCESSFUL",
                        "Cat is bought!",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(layeredPane,
//                        "Cat is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Bought cat");
            }
        };
        ActionListener buyDogListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.coins-=100;
                enableAndDisableShopButtons();

                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("dog");
                manager.animalInMap.add(goodWildAnimal);
                manager.designMapForAnimals(goodWildAnimal, true);

                manager.printErrorOrInfo (layeredPane,"BUY SUCCESSFUL",
                        "Dog is bought!",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(layeredPane,
//                        "Dog is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Bought dog");

            }
        };
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("I;m here!");
                manager.processMenu(manager.user.getUserName(),manager.user.getPassword(),new Scanner(System.in));
                //TODO //pause
            }
        };
        ActionListener pauseListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO//pause
            }
        };


        buyChickenButton.addActionListener(buyChickenListener);
        buyTurkeyButton.addActionListener(buyTurkeyListener);
        buyBuffaloButton.addActionListener(buyBuffaloListener);
        buyCatButton.addActionListener(buyCatListener);
        buyDogButton.addActionListener(buyDogListener);

        menu.addActionListener(menuListener);
        pause.addActionListener(pauseListener);

        menuBar.add(menu);
        menuBar.add(pause);



        layeredPane.add(buyChickenButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buyTurkeyButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buyBuffaloButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buyCatButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(buyDogButton, JLayeredPane.PALETTE_LAYER);





















        final int TRUCK_HEIGHT = 60 * this.getHeight() / 600;
        final int TRUCK_WIDTH = 150 * this.getWidth() / 1000;
        final int TRUCK_AND_LEFT_GAP = 280 * this.getWidth() / 1000;
        final int TRUCK_AND_DOWN_GAP = 65 * this.getHeight() / 600;

        createPanelWithBackAndForImage(truckLabel,this.getX() + TRUCK_AND_LEFT_GAP,
                this.getHeight() - TRUCK_HEIGHT - TRUCK_AND_DOWN_GAP, TRUCK_WIDTH, TRUCK_HEIGHT,
                new Color(0,200,0,0),"truck_0_PS_R.png",this, true);

//instead of above paragraph
//        truckLabel.setBounds(this.getX() + TRUCK_AND_LEFT_GAP,
//                this.getHeight() - TRUCK_HEIGHT - TRUCK_AND_DOWN_GAP, TRUCK_WIDTH, TRUCK_HEIGHT);
//        truckLabel.setIcon(new ImageIcon("R_truck_0.jpg"));
//        truckLabel.setBackground(Color.blue);
//        truckLabel.addMouseListener(this);
//        truckLabel.setOpaque(true);
//        layeredPane.add(truckLabel, JLayeredPane.PALETTE_LAYER);




//        File file = new File("truck_0.jpg");
//            BufferedImage image = new BufferedImage(ImageIO.read(file));

        final int CELLAR_HEIGHT = 60 * this.getHeight() / 600;
        final int CELLAR_WIDTH = 130 * this.getWidth() / 1000;
        final int CELLAR_AND_RIGHT_GAP = 280 * this.getWidth() / 1000;
        final int CELLAR_AND_DOWN_GAP = TRUCK_AND_DOWN_GAP;

        createPanelWithBackAndForImage(cellarLabel,this.getX() + this.getWidth() - CELLAR_AND_RIGHT_GAP - CELLAR_WIDTH,
                this.getHeight() - CELLAR_HEIGHT - CELLAR_AND_DOWN_GAP, CELLAR_WIDTH, CELLAR_HEIGHT,
                new Color(0,200,0,0),"box_PS_R.png",this, true);

//instead of above paragraph
//        cellarLabel.setBounds(this.getX() + this.getWidth() - CELLAR_AND_RIGHT_GAP - CELLAR_WIDTH,
//                this.getHeight() - CELLAR_HEIGHT - TRUCK_AND_DOWN_GAP, CELLAR_WIDTH, CELLAR_HEIGHT);
//        cellarLabel.setBackground(Color.RED);
//        cellarLabel.addMouseListener(this);
//        cellarLabel.setOpaque(true);
//        layeredPane.add(cellarLabel, JLayeredPane.PALETTE_LAYER);

        final int WELL_WIDTH = 75 * this.getWidth() / 1000;
        final int WELL_HEIGHT = 60 * this.getHeight() / 600;
        final int WELL_AND_TOP_GAP = 70 * this.getHeight() / 600;

        createPanelWithBackAndForImage(wellLabel,this.getX() + this.getWidth() / 2 - WELL_WIDTH / 2,
    this.getY() + WELL_AND_TOP_GAP,
                WELL_WIDTH, WELL_HEIGHT,new Color(0,200,0,0),
                "well_0_Photoshop_R.png",this, true);

        final int WELL_AND_BAR_GAP = 0;
        final int BAR_WIDTH = wellLabel.getWidth();
        final int BAR_HEIGHT = 20;
        int barX = wellLabel.getX();
        int barY = wellLabel.getY()+ wellLabel.getHeight()+WELL_AND_BAR_GAP;

        wellBar.setBounds(barX,barY,BAR_WIDTH,BAR_HEIGHT);
        wellBar.setBackground(Color.black);
        wellBar.setForeground(Color.red);
        wellBar.setFont(new Font("MV Boli", Font.PLAIN,15));
        wellBar.setStringPainted(true);
        wellBar.setValue(manager.wellWater);

        layeredPane.add(wellBar, JLayeredPane.PALETTE_LAYER);





//        wellLabel = new JLabel();
//        wellLabel.setBounds(this.getX() + this.getWidth() / 2 - WELL_WIDTH / 2, this.getY() + WELL_AND_TOP_GAP,
//                WELL_WIDTH, WELL_HEIGHT);
//        wellLabel.addMouseListener(this);
//        wellLabel.setBackground(null);
//        wellLabel.setIcon(new ImageIcon("well_0_Photoshop_R.png"));
//        wellLabel.setOpaque(true);
//        layeredPane.add(wellLabel, JLayeredPane.PALETTE_LAYER);

        final int[] FACTORIES_WIDTH = {0};
        final int[] FACTORIES_HEIGHT = {0};
        int FACTORIES_AND_MARGIN_GAP = 50 * this.getWidth() / 1000;
        int SAME_COL_FACTORIES_GAP = 70 * this.getHeight() / 600;
        int FACTORIES_AND_TOP_GAP = 95 * this.getHeight() / 600;

        final int FACTORY_BUTTONS_WIDTH = 80 * this.getWidth() / 1000;
        final int FACTORY_BUTTONS_HEIGHT = 70 * this.getHeight() / 600;
        final int SAME_COL_FACTORY_BUTTONS_GAP = 100 * this.getHeight() / 600;
        final int LAST_ROW_FACTORIES_GAP = 30 * this.getHeight() / 600;

        JButton[][] buildFactoryButtons = new JButton[2][3];
        ArrayList<Factory> factories = manager.factories;
        final String[] foreGroundImageName = {new String()};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                Factory currentFactory = factories.get(3 * i + j);
                gameFactory[i][j] = new JLabel();
                factoryWorkingBars[i][j] = new JProgressBar(JProgressBar.VERTICAL,
                        0,currentFactory.getTimeTakes());

                if (i == 0 && j == 0) {
                    foreGroundImageName[0] = "mill_PS_R.png";

                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 120 * this.getHeight() / 600;

                } else if (i == 0 && j == 1) {
                    foreGroundImageName[0] = "weavingF_PS_R.png";

                    FACTORIES_WIDTH[0] = 110 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 70 * this.getHeight() / 600;

                } else if (i == 0 && j == 2) {
                    foreGroundImageName[0] = "packingMilk_PS_R.png";

                    FACTORIES_WIDTH[0] = 140 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 100 * this.getHeight() / 600;

                } else if (i == 1 && j == 0) {
                    foreGroundImageName[0] = "bakery_PS_R.png";

                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 90 * this.getHeight() / 600;

                } else if (i == 1 && j == 1) {
                    foreGroundImageName[0] = "sewingF_PS_R.png";

                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 100 * this.getHeight() / 600;

                } else if (i == 1 && j == 2) {
                    if (manager.level.getLevelNumber() == 4 || manager.level.getLevelNumber() == 5)
                        foreGroundImageName[0] = "iceCreamF_PS_R.png";
                    else
                        foreGroundImageName[0] = "aviculture_PS_R.png";

                    FACTORIES_WIDTH[0] = 150 * this.getWidth() / 1000;
                    FACTORIES_HEIGHT[0] = 130 * this.getHeight() / 600;
                    // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
//                    foreGroundImageName = "aviculture_PS_R.png";

//                    FACTORIES_WIDTH = 120 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT = 120 * this.getHeight() / 600;

                }

                int factoryX = FACTORIES_AND_MARGIN_GAP + i *
                        (this.getWidth() - FACTORIES_WIDTH[0] - 2 * FACTORIES_AND_MARGIN_GAP);
                int factoryY;
                if (j == 0) {
                    factoryY = FACTORIES_AND_TOP_GAP;
                }else if (j == 2){
                    factoryY = gameFactory[i][j - 1].getY() + gameFactory[i][j - 1].getHeight()
                            + LAST_ROW_FACTORIES_GAP;
                }else{
//                    System.out.println(("i= " + i + ", j= " + j));
                    factoryY = gameFactory[i][j - 1].getY() + gameFactory[i][j - 1].getHeight()
                            + SAME_COL_FACTORIES_GAP;
                }

                gameFactory[i][j].setBounds(factoryX,factoryY,FACTORIES_WIDTH[0],FACTORIES_HEIGHT[0]);

                int buttonX = FACTORIES_AND_MARGIN_GAP + i *
                        (this.getWidth() - FACTORY_BUTTONS_WIDTH - 2 * FACTORIES_AND_MARGIN_GAP);
                int buttonY;
                if (j == 0) {
                    buttonY = FACTORIES_AND_TOP_GAP;
                } else {
                    buttonY = buildFactoryButtons[i][j - 1].getY() + buildFactoryButtons[i][j - 1].getHeight()
                            + SAME_COL_FACTORY_BUTTONS_GAP;
                }

                buildFactoryButtons[i][j] = new JButton(currentFactory.getFullFactoryName());
                buildFactoryButtons[i][j].setBounds(buttonX, buttonY, FACTORY_BUTTONS_WIDTH, FACTORY_BUTTONS_HEIGHT);
                buildFactoryButtons[i][j].setFocusable(false);
                buildFactoryButtons[i][j].setFont(new Font("MV Boli", Font.PLAIN,15));
                buildFactoryButtons[i][j].setBackground(Color.BLUE);
                buildFactoryButtons[i][j].setForeground(Color.YELLOW);
                buildFactoryButtons[i][j].setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

                Game currentGame = this;
                MouseListener workFactoriesListener = this;
                ActionListener buildFactoryListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String errorExistingResult = manager.processBuildFactory(currentFactory.getName());

                        if (errorExistingResult.equals("Error. This factory already exists.") ||
                                errorExistingResult.equals("There is no factory with this name in the game!") ||
                                errorExistingResult.equals("Error. You don't have enough coins.")) {

                            manager.printErrorOrInfo (layeredPane,"ERROR",
                                    errorExistingResult,
                                    "Computer Error Alert-SoundBible.com-783113881.wav",
                                    true,"error.png");

//                            JOptionPane.showMessageDialog(layeredPane,
//                                    errorExistingResult,
//                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));

                        } else if (errorExistingResult.equals("Factory was built successfully!")) {
                            int index = factories.indexOf(currentFactory);
                            int i = 0;
                            int j = 0;

                            if (index >= 0 && index <= 2) {
                                i = 0;
                                j = index;
                            } else if (index > 2 && index <= 5) {
                                i = 1;
                                j = index - 3;
                            }

                            if (i == 0 && j == 0) {
                                foreGroundImageName[0] = "mill_PS_R.png";
                            } else if (i == 0 && j == 1) {
                                foreGroundImageName[0] = "weavingF_PS_R.png";
                            } else if (i == 0 && j == 2) {
                                foreGroundImageName[0] = "packingMilk_PS_R.png";
                            } else if (i == 1 && j == 0) {
                                foreGroundImageName[0] = "bakery_PS_R.png";
                            } else if (i == 1 && j == 1) {
                                foreGroundImageName[0] = "sewingF_PS_R.png";
                            } else if (i == 1 && j == 2) {
                                if (manager.level.getLevelNumber() == 4 || manager.level.getLevelNumber() == 5)
                                    foreGroundImageName[0] = "iceCreamF_PS_R.png";
                                else
                                    foreGroundImageName[0] = "aviculture_PS_R.png";

                                // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
                            }



//                gameFactory[i][j].setBackground(new Color(0, 150, 0));
//                gameFactory[i][j].setBounds(FACTORIES_AND_MARGIN_GAP + i *
//                                (this.getWidth() - FACTORIES_WIDTH - 2 * FACTORIES_AND_MARGIN_GAP),
//                        FACTORIES_AND_TOP_GAP + j * (FACTORIES_HEIGHT + SAME_COL_FACTORIES_GAP),
//                        FACTORIES_WIDTH, FACTORIES_HEIGHT);
//                gameFactory[i][j].addMouseListener(this);
//                gameFactory[i][j].setOpaque(true);
//                layeredPane.add(gameFactory[i][j], JLayeredPane.PALETTE_LAYER);

                            createPanelWithBackAndForImage(gameFactory[i][j], factoryX, factoryY, FACTORIES_WIDTH[0], FACTORIES_HEIGHT[0],
                                    new Color(0, 200, 0,0), foreGroundImageName[0], workFactoriesListener, false);
//                            manager.buildFactory(currentFactory.getName());

                            buildFactoryButtons[i][j].setEnabled(false);
                            layeredPane.remove(buildFactoryButtons[i][j]);

                            final int FACTORY_AND_BAR_GAP = 0;
                            final int BAR_WIDTH = 20;
                            final int BAR_HEIGHT = gameFactory[i][j].getHeight();
                            int barX;
                            int barY = gameFactory[i][j].getY()+gameFactory[i][j].getHeight()-BAR_HEIGHT;
                            if (i==0){
                                barX = gameFactory[i][j].getX()+gameFactory[i][j].getWidth()+FACTORY_AND_BAR_GAP;
                            }else {
                                barX = gameFactory[i][j].getX() - FACTORY_AND_BAR_GAP - BAR_WIDTH;
                            }
                            factoryWorkingBars[i][j].setBounds(barX,barY,BAR_WIDTH,BAR_HEIGHT);
                            factoryWorkingBars[i][j].setBackground(Color.black);
                            factoryWorkingBars[i][j].setForeground(Color.red);
                            factoryWorkingBars[i][j].setFont(new Font("MV Boli", Font.PLAIN,15));
                            factoryWorkingBars[i][j].setStringPainted(true);
                            factoryWorkingBars[i][j].setValue(0);

                            layeredPane.add(factoryWorkingBars[i][j], JLayeredPane.PALETTE_LAYER);

                            final int BAR_AND_UPGRADE_GAP = 0 * currentGame.getWidth()/1000;
                            final int UPGRADE_BUTTON_WIDTH = 70 * currentGame.getWidth()/1000;
                            final int UPGRADE_BUTTON_HEIGHT = 70 * currentGame.getHeight()/600;
                            int upgradeX = (i==0 ? factoryWorkingBars[i][j].getX()+
                                    factoryWorkingBars[i][j].getWidth()+BAR_AND_UPGRADE_GAP :
                                    factoryWorkingBars[i][j].getX()-UPGRADE_BUTTON_WIDTH-BAR_AND_UPGRADE_GAP);
                            int upgradeY = factoryWorkingBars[i][j].getY()+factoryWorkingBars[i][j].getHeight()/2
                                    -UPGRADE_BUTTON_HEIGHT/2;
                            JButton upgradeButton = new JButton("UPGRADE");
                            upgradeButton.setBounds(upgradeX,upgradeY,UPGRADE_BUTTON_WIDTH,UPGRADE_BUTTON_HEIGHT);
                            upgradeButton.setFocusable(false);
                            upgradeButton.setFont(new Font("MV Boli", Font.PLAIN, 12));
                            upgradeButton.setForeground(Color.YELLOW);
                            upgradeButton.setBackground(Color.BLUE);
                            upgradeButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
                            upgradeButton.setIcon(new ImageIcon("upgrade_R2.png"));
                            upgradeButton.setVerticalTextPosition(JButton.BOTTOM);
                            upgradeButton.setHorizontalTextPosition(JButton.CENTER);
                            ActionListener upgradeListener = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    manager.upgradeFactory(currentFactory.getName());
                                    upgradeButton.setEnabled(false);
                                    layeredPane.remove(upgradeButton);
                                    SwingUtilities.updateComponentTreeUI(currentGame);

                                    manager.printErrorOrInfo (layeredPane,"UPGRADE SUCCESSFUL",
                                            currentFactory.getFullFactoryName() + " upgraded successfully.",
                                            "mixkit-fairy-cartoon-success-voice-344.wav",
                                            false,"done.png");

//                                    JOptionPane.showMessageDialog(layeredPane,
//                                            currentFactory.getFullFactoryName() + " upgraded successfully.",
//                                            "UPGRADE SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE,
//                                            new ImageIcon("done.png"));

                                    manager.writeLogFile("Info", "Upgraded factory successfully");
                                }
                            };
                            upgradeButton.addActionListener(upgradeListener);

                            layeredPane.add(upgradeButton, JLayeredPane.PALETTE_LAYER);

                            layeredPane.remove(buildFactoryButtons[i][j]);
                            SwingUtilities.updateComponentTreeUI(currentGame);

                            manager.printErrorOrInfo (layeredPane,"BUILD SUCCESSFUL",
                                    errorExistingResult,
                                    "mixkit-fairy-cartoon-success-voice-344.wav",
                                    false,"done.png");

//                            JOptionPane.showMessageDialog(layeredPane,
//                                    errorExistingResult,
//                                    "BUILD SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE,
//                                    new ImageIcon("done.png"));
                        }
                    }
                };
                buildFactoryButtons[i][j].addActionListener(buildFactoryListener);

                layeredPane.add(buildFactoryButtons[i][j], JLayeredPane.PALETTE_LAYER);

            }








        }



        this.setVisible(true);
        this.setLayout(null);
//        for panel

        final int GAME_PLANTS_AND_MIDDLE_GAP = -40;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                gamePlant[i][j] = new JLabel();
                gamePlant[i][j].setBackground(new Color(0, 200, 0,0));
                gamePlant[i][j].setBounds(this.getX() + this.getWidth() / 2 + (i - 3) * wid,
                        this.getY() + this.getHeight() / 2 + (j - 3) * height - GAME_PLANTS_AND_MIDDLE_GAP,
                        wid, height);
                gamePlant[i][j].addMouseListener(this);
                gamePlant[i][j].setOpaque(true);
                gamePlant[i][j].setIcon(groundIcon);
//                groundLabels[i][j] = createGroundAndGrassLabel(i,j,groundIcon);
//                for panel
//                JLabel groundLabel = new JLabel(groundIcon);
//                groundLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
//                gamePlant[i][j].add(groundLabel);

                layeredPane.add(gamePlant[i][j], JLayeredPane.PALETTE_LAYER);
            }
        }

        showDetails();
        //setPos
        this.add(panel);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!manager.isLevelCompleted()) {
                    manager.turn(1);
                    turn();
                }else{
                    timer.cancel();
                }
            }
        },0,2000);
    }
//    public void run(){
////        while(true){
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        long counter = 0;
//        while (counter <100000000){
//            counter++;
//        }
//        System.out.println("turning (main run)...");
//        manager.turn(1);
//        this.turn();
//        SwingUtilities.updateComponentTreeUI(this);
////        processShowDetails();
////        }
//    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == wellLabel)
        {
            //only for test

//            layeredPane.remove(wellLabel);
//            SwingUtilities.updateComponentTreeUI(this);

            //only for test
            if (manager.fillingWellTimeCounter != 0){
                manager.printErrorOrInfo (layeredPane,"ERROR",
                        "Well is already filling!",
                        "Computer Error Alert-SoundBible.com-783113881.wav",
                        true,"error.png");

//                JOptionPane.showMessageDialog(this,
//                        "Well is already filling!",
//                        "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                manager.writeLogFile("Error", "Repeated filling well");
            }
            else if (manager.wellWater != 0) {
                manager.printErrorOrInfo (layeredPane,"ERROR",
                        "Error. Well must be empty.",
                        "Computer Error Alert-SoundBible.com-783113881.wav",
                        true,"error.png");

//                JOptionPane.showMessageDialog(this,
//                        "Error. Well must be empty.",
//                        "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                manager.writeLogFile("Error", "Error filling well");
            }
            else {
                manager.startFillingWell();

                manager.printErrorOrInfo (layeredPane,"Filling",
                        "Filling well...",
                        "mixkit-fairy-cartoon-success-voice-344.wav",
                        false,"done.png");

//                JOptionPane.showMessageDialog(this,
//                        "Filling well...",
//                        "Filling", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                manager.writeLogFile("Info", "Filled well");
            }
        }
else if (e.getSource()==truckLabel)
        {
        new TruckPage(manager);
        //TODO//pause
        }
        else if (e.getSource()==cellarLabel)
        {
        new GoodList(manager.cellar);
        //TODO//pause
        }
        for(int i = 0 ; i < 6 ; i++)
        {
            for (int j = 0 ; j < 6 ; j++)
            {
                if (e.getSource()==gamePlant[i][j])
                {
//                    if (manager.badWildAnimalCheck(i+1,j+1))
//                    {
////                        manager.cager(i+1,j+1);
////                        System.out.println("cager worked!(in this)");
//                    }
//                    /*else */if (manager.checkGood(i+1,j+1))
//                    {
//                        manager.Catch(i+1,j+1);
//                        // TODO: 7/12/2021
//                        System.out.println("Goods were picked up.");
//                    }
//                    else
                    {
                        if (manager.wellWater >= 1) {
                            manager.plantGrass(i+1, j+1);
                            gamePlant[i][j].setIcon(grassIcon);
                            SwingUtilities.updateComponentTreeUI(this);

                            manager.printErrorOrInfo (layeredPane,"Planted Grass",
                                    "Planted grass on x=" + (i+1) + " , y=" + (j+1),
                                    "mixkit-fairy-cartoon-success-voice-344.wav",
                                    false,"done.png");

//                            JOptionPane.showMessageDialog(this,
//                                    "Planted grass on x=" + (i+1) + " , y=" + (j+1),
//                                    "Planted Grass", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));


//                            ImageIcon bushIcon = new ImageIcon(/*freeTileset\\png\\Object\\Bush (1)*//*"labelsGrass.jpg"*/"flowerGrass_PS_R2.png");
////                            gamePlant[i][j].setIcon(bushIcon);
////                            for panel
//                            JLabel bushLabel = new JLabel(bushIcon);
//                            bushLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
//                            bushLabel.setOpaque(true);
//                            layeredPane.add(bushLabel, JLayeredPane.PALETTE_LAYER);

//                            ImageIcon grassIcon = new ImageIcon("flowerGrass_PS_R2.png");
//                            JLabel grassLabel = new JLabel(grassIcon);
//                            grassLabel.setBackground(new Color(116, 57, 28, 0));
//                            grassLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
//                            grassLabel.setOpaque(true);
//                            layeredPane.add(grassLabel, JLayeredPane.MODAL_LAYER);

                            //                            gamePlant[i][j].add(bushLabel);
//                            gamePlant[i][j].setOpaque(true);


//                            gamePlant[i][j].setIcon(new ImageIcon("freeTileset\\png\\Object\\Bush (1).png"));






//                            for (int i1 = 0; i1 < 6; i1++) {
//                                for (int j1 = 0; j1 < 6; j1++) {
//                                    if (existsPointInLabel(e.getX(), e.getY(), gamePlant[i1][j1])){
//                                    }
//                                }
//                            }
//                            System.out.println("Planted grass on x=" + (i+1) + " , y=" + (j+1));
                            manager.writeLogFile("Info", "Planted grass on " + (i+1) + "," + (j+1));
                            wellBar.setValue(wellBar.getValue()-1);
                        }
                        else{
                            manager.printErrorOrInfo (layeredPane,"ERROR",
                                    "Error. There is not enough water.",
                                    "Computer Error Alert-SoundBible.com-783113881.wav",
                                    true,"error.png");

//                            JOptionPane.showMessageDialog(this,
//                                    "Error. There is not enough water.",
//                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                            manager.writeLogFile("Error", "Water lack for planting");
                        }
                    }
                }
            }
        }
        for (int i = 0 ; i < 2 ; i++)
        {
            for (int j = 0 ; j < 3 ; j++)
            {
                if (e.getSource()==gameFactory[i][j])
                {

                    String errorExistingResult = manager.processWork(i*7,2*j+1);
//                    System.out.println("result = "+errorExistingResult);
                    if (errorExistingResult.equals("Factory is already working!") || errorExistingResult.equals("An error occurred!")
                    || errorExistingResult.equals("There is not enough required good!")){
                        manager.printErrorOrInfo (layeredPane,"ERROR",
                                errorExistingResult,
                                "Computer Error Alert-SoundBible.com-783113881.wav",
                                true,"error.png");

//                        JOptionPane.showMessageDialog(this,
//                                errorExistingResult,
//                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                    }else if (errorExistingResult.equals("Factory started working...")){



                        manager.printErrorOrInfo (layeredPane,"Work started",
                                errorExistingResult,
                                "mixkit-fairy-cartoon-success-voice-344.wav",
                                false,"done.png");

//                        JOptionPane.showMessageDialog(this,
//                                errorExistingResult,
//                                "Work started", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean existsPointInLabel(int x, int y, JLabel label){
        if (label.getX() <= x && label.getX()+label.getWidth() >= x && label.getY()<=y && label.getY()+label.getHeight()>= y)
            return true;
        return false;
    }
    public void createPanelWithBackAndForImage(JLabel label,
                                               int x, int y, int width, int height,
                                               Color backgroundColor,
                                               String foregroundImageName, MouseListener mouseListener,
                                               boolean setBounds){

//        JLayeredPane helpLayeredPane = new JLayeredPane();
//        helpLayeredPane.setBounds(x,y,width,height);

//        ImageIcon backIcon = new ImageIcon(backgroundImageName);
        ImageIcon foreIcon = new ImageIcon(foregroundImageName);

//        JLabel backLabel = new JLabel();

        label.setBackground(backgroundColor);

//        backLabel.setBounds(x,y,width,height);
        if (setBounds)
            label.setBounds(x,y,width,height);

//        backLabel.setIcon(backIcon);
        label.setIcon(foreIcon);

        label.addMouseListener(mouseListener);

//        backLabel.setOpaque(true);
        label.setOpaque(true);

//        layeredPane.add(backLabel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(label, JLayeredPane.MODAL_LAYER);


//        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);
    }

    public void turn() {

        if (manager.wellWater == 0) {
            if (manager.fillingWellTimeCounter == 2) {
                wellBar.setValue(2);
            } else if (manager.fillingWellTimeCounter == 1) {
                wellBar.setValue(4);
            }
        } else {
            wellBar.setValue(manager.wellWater);
        }

        for (int i = 0; i < manager.factories.size(); i++) {
            Factory currentFactory = manager.factories.get(i);

            if (currentFactory.isBuilt()) {
                int progressBarI = (i <= 2 ? 0 : 1);
                int progressBarJ = (i <= 2 ? i : i - 3);

                int newProgressValue = currentFactory.getTimeTakes() - currentFactory.getWorkingRemainingTime();

//                System.out.println("progressBarI= "+progressBarI);
//                System.out.println("progressBarJ= "+progressBarJ);

                if (!currentFactory.isWorking()) {
                    int previousBarValue = factoryWorkingBars[progressBarI][progressBarJ].getValue();
                    if (previousBarValue != 0) {
                        factoryWorkingBars[progressBarI][progressBarJ].setValue(0);
                    }
                } else {
                    factoryWorkingBars[progressBarI][progressBarJ].setValue(newProgressValue);
                }
            }
//                factory.setWorkingRemainingTime(factory.getWorkingRemainingTime() - /*1*/ factory.getLevel());
//                if (manager.factories.get(i).getWorkingRemainingTime() <=0){
//                    finishWork(factory);
//                }
//            }else{
//            }
        }

        for (JLabel animalLabel : animalLabels) {
            layeredPane.remove(animalLabel);
        }
        for (JLabel cageLabel : cageLabels) {
            layeredPane.remove(cageLabel);
        }
        for (JProgressBar bar : livesBar) {
            layeredPane.remove(bar);
        }
        for (JLabel goodLabel : goodLabels) {
            layeredPane.remove(goodLabel);
        }

        animalLabels = new ArrayList<>();
        cageLabels = new ArrayList<>();
        livesBar = new ArrayList<>();
        goodLabels = new ArrayList<>();

        for (Good good : manager.goodInMap) {
            int i = good.getX() - 1;
            int j = good.getY() - 1;


            ImageIcon goodIcon = new ImageIcon(good.getName()+"_PS_R.png");
            JLabel goodLabel = new JLabel(goodIcon);
            goodLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), wid - 1, height - 1);
            goodLabel.setBackground(new Color(0, 0, 0, 0));
            goodLabel.setOpaque(true);

            MouseListener catchListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (manager.checkGood(i+1,j+1))
                    {
                        manager.Catch(i+1,j+1);
//                        System.out.println("Goods were picked up.");
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            };

            goodLabel.addMouseListener(catchListener);

            goodLabels.add(goodLabel);
            layeredPane.add(goodLabel, JLayeredPane.POPUP_LAYER);
            SwingUtilities.updateComponentTreeUI(this);




        }

        try{
            for (Animal animal : manager.animalInMap) {
                int i = animal.getX() - 1;
                int j = animal.getY() - 1;

                ImageIcon animalIcon = new ImageIcon();

                if ((animal.getX() - animal.getPrevX() == 0 && animal.getY() - animal.getPrevY() == 0) || (animal.getX() - animal.getPrevX() == -1))
                    animalIcon = new ImageIcon(animal.getName() + "\\left_C.png");
                else if (animal.getX() - animal.getPrevX() == 1)
                    animalIcon = new ImageIcon(animal.getName() + "\\right_C.png");
                else if (animal.getY() - animal.getPrevY() == -1)
                    animalIcon = new ImageIcon(animal.getName() + "\\up_C.png");
                else/* if (animal.getY() - animal.getPrevY() == 1)*/
                    animalIcon = new ImageIcon(animal.getName() + "\\down_C.png");


                JLabel animalLabel = new JLabel(animalIcon);
                animalLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), wid - 1, height - 1);
                animalLabel.setBackground(new Color(0, 0, 0, 0));
                animalLabel.setOpaque(true);


                if (animal instanceof BadWildAnimal) {
                    String exactImageAddress = new String();
                    if (((BadWildAnimal) animal).getCagesCount() == ((BadWildAnimal) animal).getCagesRequiredCount()) {

                        ImageIcon cagedAnimalIcon = new ImageIcon(animal.getName() + "\\caged_C.png");
                        animalLabel.setIcon(cagedAnimalIcon);

                        exactImageAddress = "complete.png";
                    } else if (((BadWildAnimal) animal).getCagesCount() < ((BadWildAnimal) animal).getCagesRequiredCount()) {
                        exactImageAddress = "build" + ((BadWildAnimal) animal).getCagesCount() + ".png";
                    }

                    ImageIcon cageIcon = new ImageIcon(animal.getName() + "Cage\\" + exactImageAddress);

                    JLabel cageLabel = new JLabel(cageIcon);
                    cageLabel.setBounds(animalLabel.getX(), animalLabel.getY(),
                            animalLabel.getWidth() - 1, animalLabel.getHeight() - 1);
                    cageLabel.setBackground(new Color(0, 0, 0, 0));
                    cageLabel.setOpaque(true);

                    MouseListener cageListener = new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
    //                        if (manager.badWildAnimalCheck(i+1,j+1))
    //                        {
                            manager.cager(animal.getPrevX(), animal.getPrevY());
//                            System.out.println("cager worked!(in cageListener)\ncagesCount = " + ((BadWildAnimal) animal).getCagesCount());
    //                        }
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    };
                    cageLabel.addMouseListener(cageListener);
                    cageLabels.add(cageLabel);
                    layeredPane.add(cageLabel, JLayeredPane.POPUP_LAYER);
                }

                if (animal instanceof DomesticAnimal) {
                    JProgressBar lifeBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
                    final int LIFE_BAR_WIDTH = animalLabel.getWidth();
                    final int LIFE_BAR_HEIGHT = 10 * this.getHeight() / 800;

                    lifeBar.setBounds(animalLabel.getX(), animalLabel.getY() + animalLabel.getHeight() - LIFE_BAR_HEIGHT,
                            LIFE_BAR_WIDTH, LIFE_BAR_HEIGHT);
                    lifeBar.setBackground(Color.black);
                    lifeBar.setForeground(Color.red);
                    lifeBar.setFont(new Font("MV Boli", Font.PLAIN, 15));
                    lifeBar.setStringPainted(true);
                    lifeBar.setValue(((DomesticAnimal) animal).getLife());
                    lifeBar.setOpaque(true);

                    livesBar.add(lifeBar);
                    layeredPane.add(lifeBar, JLayeredPane.POPUP_LAYER);
                }

                animalLabels.add(animalLabel);
                layeredPane.add(animalLabel, JLayeredPane.MODAL_LAYER);
                SwingUtilities.updateComponentTreeUI(this);

            }
        }catch (ConcurrentModificationException e){

        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int grassCount = manager.getGrassCount(i,j);
                if (grassCount <= 0){
                    gamePlant[i][j].setIcon(groundIcon);
//                    if (grassLabel[i][j] != null) {
//                        layeredPane.remove(grassLabel[i][j]);
//                        grassLabel[i][j] = null;
//                    }
                }else{
                    gamePlant[i][j].setIcon(grassIcon);
//                    if (grassLabel[i][j] == null) {
//                        grassLabel[i][j] = new JLabel(new ImageIcon("flowerGrass_PS_R2.png"));
//                        grassLabel[i][j].setBackground(new Color(0, 0, 0, 0));
//                    }
//                        layeredPane.add(grassLabel[i][j], JLayeredPane.MODAL_LAYER);
                }
            }
        }
        showDetails();
        if (manager.isLevelCompleted()){
            manager.finishLevel();
            clip.close();
            this.dispose();
            createCongratsFrame();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void createCongratsFrame() {

        FrameCreator congratsFrame = new FrameCreator("END OF LEVEL", 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Level is completed! Select one");

        mainLabel.setForeground(new Color(4, 88, 15));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        final int TEXT_HEIGHT = 50;
        mainLabel.setBounds(congratsFrame.getX(), congratsFrame.getY(), congratsFrame.getWidth(), congratsFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35));



        final int BUTTON_WIDTH = 350;
        final int BUTTON_HEIGHT = 90;
        final int TOP_AND_BUTTONS_GAP = 200;
        final int BUTTONS_GAP = 20;
        final int EXIT_AND_BOTTOM_GAP = 50;

        JButton mainMenuButton = new JButton("MAIN MENU");
        JButton exitButton = new JButton("EXIT");

        mainMenuButton.setBounds(congratsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                congratsFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setBounds(congratsFrame.getFrameCenter().x - BUTTON_WIDTH/2,
                congratsFrame.getY()+ congratsFrame.getHeight()-BUTTON_HEIGHT-EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        mainMenuButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        mainMenuButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        mainMenuButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        mainMenuButton.setFocusable(false);
        exitButton.setFocusable(false);

        mainMenuButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        mainMenuButton.setFont(new Font("MV Boli", Font.PLAIN, 20));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20));

        ActionListener mainMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                congratsFrame.dispose();
                manager.processMenu(manager.user.getUserName(),
                        manager.user.getPassword(),new Scanner(System.in));
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        mainMenuButton.addActionListener(mainMenuListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(mainMenuButton);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        congratsFrame.setComponents("farm.jpg", components);














    }

    public void enableAndDisableShopButtons(){
        if (manager.coins < 100) {
            buyChickenButton.setEnabled(false);
            buyDogButton.setEnabled(false);
        }else{
            buyChickenButton.setEnabled(true);
            buyDogButton.setEnabled(true);
        }

        if (manager.coins < 200){
            buyTurkeyButton.setEnabled(false);
        }else{
            buyTurkeyButton.setEnabled(true);
        }

        if (manager.coins < 400){
            buyBuffaloButton.setEnabled(false);
        }else{
            buyBuffaloButton.setEnabled(true);
        }

        if (manager.coins < 150){
            buyCatButton.setEnabled(false);
        }else{
            buyCatButton.setEnabled(true);
        }

    }

    @Override
    public void run() {

    }

    public JLabel createGroundAndGrassLabel(int i, int j, ImageIcon imageIcon){
        JLabel label = new JLabel(imageIcon);
        label.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), wid, height);
        label.setBackground(new Color(0,0,0,0));
        return label;
    }

    public void showDetails(){
        for (JLabel detailsLabel : detailsLabels) {
            layeredPane.remove(detailsLabel);
        }

        detailsLabels = new ArrayList<>();

        final int DETAILS_AND_MARGIN_GAP = 5 * this.getWidth()/1200;
        final int DETAILS_AND_TRUCK_GAP = 5 * this.getHeight()/800;
        final int DETAILS_WIDTH = 145 * this.getWidth()/1200;
        final int DETAILS_HEIGHT = 55 * this.getHeight()/800;
        final int ICON_AND_TEXT_GAP = 10 * this.getWidth()/1200;

        ImageIcon timeIcon = new ImageIcon("clock_PS_R.png");
        JLabel timeLabel = new JLabel();
        timeLabel.setIcon(timeIcon);
        timeLabel.setText(Integer.toString(manager.time));
        timeLabel.setBounds(this.getX()+DETAILS_AND_MARGIN_GAP,
                truckLabel.getY()+truckLabel.getHeight()+DETAILS_AND_TRUCK_GAP,DETAILS_WIDTH,DETAILS_HEIGHT);
        timeLabel.setBackground(Color.BLUE);
        timeLabel.setForeground(Color.YELLOW);
        timeLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        timeLabel.setIconTextGap(ICON_AND_TEXT_GAP);
        timeLabel.setOpaque(true);
        detailsLabels.add(timeLabel);
        layeredPane.add(timeLabel, JLayeredPane.PALETTE_LAYER);

        ImageIcon coinsIcon = new ImageIcon("coin_PS_R.png");
        JLabel coinsLabel = new JLabel();
        coinsLabel.setIcon(coinsIcon);
        coinsLabel.setText((manager.coins)+"/"+(manager.level.getTaskCoins()));
        coinsLabel.setBounds(timeLabel.getX()+timeLabel.getWidth(),
                truckLabel.getY()+truckLabel.getHeight()+DETAILS_AND_TRUCK_GAP,DETAILS_WIDTH,DETAILS_HEIGHT);
        coinsLabel.setBackground(Color.BLUE);
        coinsLabel.setForeground(Color.YELLOW);
        coinsLabel.setFont(new Font("MV Boli", Font.PLAIN,12));
        coinsLabel.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        coinsLabel.setOpaque(true);
        detailsLabels.add(coinsLabel);
        layeredPane.add(coinsLabel, JLayeredPane.PALETTE_LAYER);

        int lastLabelX = coinsLabel.getX();

        if (manager.level.getTaskGoods().size() != 0) {
            for (Good taskGood : manager.level.getTaskGoods()) {

                int index = manager.level.getTaskGoods().indexOf(taskGood);

                for (Good good : manager.cellar.getGoods()) {
                    if (good.getName().equals(taskGood.getName())) {
                        ImageIcon goodIcon = new ImageIcon(good.getName() + "_PS_R.png");
                        JLabel goodLabel = new JLabel();
                        goodLabel.setIcon(goodIcon);
                        goodLabel.setText((good.getCount()) + "/" + (taskGood.getCount()));
                        goodLabel.setBounds(coinsLabel.getX() + coinsLabel.getWidth() + index * DETAILS_WIDTH,
                                truckLabel.getY() + truckLabel.getHeight() + DETAILS_AND_TRUCK_GAP,
                                DETAILS_WIDTH, DETAILS_HEIGHT);
                        goodLabel.setBackground(Color.BLUE);
                        goodLabel.setForeground(Color.YELLOW);
                        goodLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
                        goodLabel.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                        goodLabel.setIconTextGap(ICON_AND_TEXT_GAP);
                        goodLabel.setOpaque(true);
                        detailsLabels.add(goodLabel);
                        layeredPane.add(goodLabel, JLayeredPane.PALETTE_LAYER);

                        lastLabelX = goodLabel.getX();
                    }
                }
            }
        }

        if (manager.level.getTaskAnimals().size() != 0) {
            for (int i = 0; i < manager.level.getTaskAnimals().size(); i++) {
                String imageAddress = manager.level.getTaskAnimals().get(i).getName()+"\\right_C.png";
                String text = manager.animalCountOnMap(manager.level.getTaskAnimals().get(i).getName())
                            + "/" + manager.level.getEachTaskAnimalCount().get(i);
                ImageIcon animalIcon = new ImageIcon(imageAddress);
                JLabel animalLabel = new JLabel();
                animalLabel.setIcon(animalIcon);
                animalLabel.setText(text);
                animalLabel.setBounds(lastLabelX + DETAILS_WIDTH + i * DETAILS_WIDTH,
                        truckLabel.getY() + truckLabel.getHeight() + DETAILS_AND_TRUCK_GAP,
                        DETAILS_WIDTH, DETAILS_HEIGHT);
                animalLabel.setBackground(Color.BLUE);
                animalLabel.setForeground(Color.YELLOW);
                animalLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
                animalLabel.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                animalLabel.setIconTextGap(ICON_AND_TEXT_GAP);
                animalLabel.setOpaque(true);
                detailsLabels.add(animalLabel);
                layeredPane.add(animalLabel, JLayeredPane.PALETTE_LAYER);
            }
        }
    }
}








//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class Game extends JFrame implements MouseListener,Runnable {
//    JPanel panel = new JPanel();
//    int wid;
//    int height;
//    JMenuBar menuBar;
//    JMenu menu ;
//    JMenu pause ;
//    Clip clip ;
//    private Manager manager;
//    JLayeredPane layeredPane = new JLayeredPane();
//
//    JLabel[][] gamePlant = new JLabel[6][6];
//    JLabel[][] groundLabels = new JLabel[6][6];
//    JLabel[][] grassLabel = new JLabel[6][6];
//    JLabel[][] gameFactory = new JLabel[2][3];
//    JLabel truckLabel = new JLabel();
//    JLabel cellarLabel = new JLabel();
//    JLabel wellLabel = new JLabel();
//
//    ArrayList<JLabel> animalLabels = new ArrayList<>();
//    ArrayList<JLabel> cageLabels = new ArrayList<>();
//    ArrayList<JLabel> detailsLabels = new ArrayList<>();
//
//    JButton buyChickenButton = new JButton("100");
//    JButton buyTurkeyButton = new JButton("200");
//    JButton buyBuffaloButton = new JButton("400");
//    JButton buyCatButton = new JButton("150");
//    JButton buyDogButton = new JButton("100");
//
//    JProgressBar wellBar = new JProgressBar(JProgressBar.HORIZONTAL,0,5);
//    JProgressBar[][] factoryWorkingBars = new JProgressBar[2][3];
//    public Game(Manager manager) throws HeadlessException {
//        this.manager = manager;
//        this.setSize(1200, 800);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        menuBar = new JMenuBar();
//        menu = new JMenu("menu");
//        pause = new JMenu("pause");
//        menuBar.add(menu);
//        menuBar.add(pause);
//        wid = 70 * this.getWidth() / 1000;
//        height = 50 *this.getHeight()/600;
//        File musicPath = new File("AnyConv.com__32 - Bonus ~ ''Glou Glou'' (Reprise).wav");
//        if (musicPath.exists())
//        {
//            try {
//                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
//                clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.setFramePosition(0);
//                clip.start();
//                clip.loop(Clip.LOOP_CONTINUOUSLY);
//            }
//            catch (Exception e)
//            {
//                System.out.println(1);
//            }
//        }
//        this.setBackground(Color.BLACK);
//        this.setVisible(true);
//        layeredPane.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//
//        panel.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//        panel.setLayout(null);
//
//        panel.add(layeredPane);
//
//        ImageIcon backgroundIcon = new ImageIcon("back6_R.png");
//        JLabel backgroundLabel = new JLabel(backgroundIcon);
//        backgroundLabel.setBounds(panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight());
////        System.out.println(backgroundIcon == null);
//        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
//
//        final int SHOP_BUTTONS_WIDTH = 80 * this.getWidth() / 1000;
//        final int SHOP_BUTTONS_HEIGHT = 70 * this.getHeight() / 600;
//        final int SHOP_AND_LEFT_GAP = 10 * this.getWidth() / 1000;
//        final int SHOP_AND_TOP_GAP = 15 * this.getHeight() / 600;
//
//        ImageIcon chickenIcon = new ImageIcon("chicken_shop_PS_R.png");
//        ImageIcon turkeyIcon = new ImageIcon("turkey_shop_PS_R.png");
//        ImageIcon buffaloIcon = new ImageIcon("buffalo_shop_PS_R.png");
//        ImageIcon catIcon = new ImageIcon("cat_shop_PS_R.png");
//        ImageIcon dogIcon = new ImageIcon("dog_shop_PS_R.png");
//
//        buyChickenButton.setIcon(chickenIcon);
//        buyTurkeyButton.setIcon(turkeyIcon);
//        buyBuffaloButton.setIcon(buffaloIcon);
//        buyCatButton.setIcon(catIcon);
//        buyDogButton.setIcon(dogIcon);
//
//        buyChickenButton.setBounds(this.getX()+SHOP_AND_LEFT_GAP,this.getY()+SHOP_AND_TOP_GAP,
//                SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
//        buyTurkeyButton.setBounds(buyChickenButton.getX()+ buyChickenButton.getWidth(),
//                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
//        buyBuffaloButton.setBounds(buyTurkeyButton.getX()+ buyTurkeyButton.getWidth(),
//                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
//        buyCatButton.setBounds(buyBuffaloButton.getX()+ buyBuffaloButton.getWidth(),
//                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
//        buyDogButton.setBounds(buyCatButton.getX()+ buyCatButton.getWidth(),
//                this.getY()+SHOP_AND_TOP_GAP, SHOP_BUTTONS_WIDTH, SHOP_BUTTONS_HEIGHT);
//
//        buyChickenButton.setFont(new Font("MV Boli", Font.PLAIN,20));
//        buyTurkeyButton.setFont(new Font("MV Boli", Font.PLAIN,20));
//        buyBuffaloButton.setFont(new Font("MV Boli", Font.PLAIN,20));
//        buyCatButton.setFont(new Font("MV Boli", Font.PLAIN,20));
//        buyDogButton.setFont(new Font("MV Boli", Font.PLAIN,20));
//
//        buyChickenButton.setVerticalTextPosition(JLabel.BOTTOM);
//        buyTurkeyButton.setVerticalTextPosition(JLabel.BOTTOM);
//        buyBuffaloButton.setVerticalTextPosition(JLabel.BOTTOM);
//        buyCatButton.setVerticalTextPosition(JLabel.BOTTOM);
//        buyDogButton.setVerticalTextPosition(JLabel.BOTTOM);
//
//        buyChickenButton.setHorizontalTextPosition(JLabel.CENTER);
//        buyTurkeyButton.setHorizontalTextPosition(JLabel.CENTER);
//        buyBuffaloButton.setHorizontalTextPosition(JLabel.CENTER);
//        buyCatButton.setHorizontalTextPosition(JLabel.CENTER);
//        buyDogButton.setHorizontalTextPosition(JLabel.CENTER);
//
//        buyChickenButton.setFocusable(false);
//        buyTurkeyButton.setFocusable(false);
//        buyBuffaloButton.setFocusable(false);
//        buyCatButton.setFocusable(false);
//        buyDogButton.setFocusable(false);
//
//        buyChickenButton.setBackground(Color.blue);
//        buyTurkeyButton.setBackground(Color.blue);
//        buyBuffaloButton.setBackground(Color.blue);
//        buyCatButton.setBackground(Color.blue);
//        buyDogButton.setBackground(Color.blue);
//
//        buyChickenButton.setForeground(Color.YELLOW);
//        buyTurkeyButton.setForeground(Color.YELLOW);
//        buyBuffaloButton.setForeground(Color.YELLOW);
//        buyCatButton.setForeground(Color.YELLOW);
//        buyDogButton.setForeground(Color.YELLOW);
//
//        buyChickenButton.setBorder(BorderFactory.createEtchedBorder());
//        buyTurkeyButton.setBorder(BorderFactory.createEtchedBorder());
//        buyBuffaloButton.setBorder(BorderFactory.createEtchedBorder());
//        buyCatButton.setBorder(BorderFactory.createEtchedBorder());
//        buyDogButton.setBorder(BorderFactory.createEtchedBorder());
//
//        enableAndDisableShopButtons();
//        ActionListener menuListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.processMenu(manager.user.getUserName(),manager.user.getPassword(),new Scanner(System.in));
//                //TODO //pause
//            }
//        };
//        ActionListener pauseListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //TODO//pause
//            }
//        };
//        ActionListener buyChickenListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.coins-=100;
//                enableAndDisableShopButtons();
//
//                DomesticAnimal domesticAnimal = new DomesticAnimal("chicken");
//                manager.animalInMap.add(domesticAnimal);
//                manager.designMapForAnimals(domesticAnimal,true);
//
//                JOptionPane.showMessageDialog(layeredPane,
//                        "Chicken is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//
//                manager.writeLogFile("Info", "Bought chicken");
//            }
//        };
//        ActionListener buyTurkeyListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.coins-=200;
//                enableAndDisableShopButtons();
//
//                DomesticAnimal domesticAnimal = new DomesticAnimal("turkey");
//                manager.animalInMap.add(domesticAnimal);
//                manager.designMapForAnimals(domesticAnimal,true);
//
//                JOptionPane.showMessageDialog(layeredPane,
//                        "Turkey is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//
//                manager.writeLogFile("Info", "Bought turkey");
//            }
//        };
//        ActionListener buyBuffaloListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.coins-=400;
//                enableAndDisableShopButtons();
//
//                DomesticAnimal domesticAnimal = new DomesticAnimal("buffalo");
//                manager.animalInMap.add(domesticAnimal);
//                manager.designMapForAnimals(domesticAnimal,true);
//
//                JOptionPane.showMessageDialog(layeredPane,
//                        "Buffalo is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//
//                manager.writeLogFile("Info", "Bought buffalo");
//            }
//        };
//        ActionListener buyCatListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.coins-=150;
//                enableAndDisableShopButtons();
//
//                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("cat");
//                manager.animalInMap.add(goodWildAnimal);
//                manager.designMapForAnimals(goodWildAnimal, true);
//
//                JOptionPane.showMessageDialog(layeredPane,
//                        "Cat is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//
//                manager.writeLogFile("Info", "Bought cat");
//            }
//        };
//
//        ActionListener buyDogListener = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                manager.coins-=100;
//                enableAndDisableShopButtons();
//
//                GoodWildAnimal goodWildAnimal = new GoodWildAnimal("dog");
//                manager.animalInMap.add(goodWildAnimal);
//                manager.designMapForAnimals(goodWildAnimal, true);
//
//                JOptionPane.showMessageDialog(layeredPane,
//                        "Dog is bought!",
//                        "BUY SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//
//                manager.writeLogFile("Info", "Bought dog");
//
//            }
//        };
//
//        buyChickenButton.addActionListener(buyChickenListener);
//        buyTurkeyButton.addActionListener(buyTurkeyListener);
//        buyBuffaloButton.addActionListener(buyBuffaloListener);
//        buyCatButton.addActionListener(buyCatListener);
//        buyDogButton.addActionListener(buyDogListener);
//        menu.addActionListener(menuListener);
//        pause.addActionListener(pauseListener);
//
//        layeredPane.add(buyChickenButton, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(buyTurkeyButton, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(buyBuffaloButton, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(buyCatButton, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(buyDogButton, JLayeredPane.PALETTE_LAYER);
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
//        final int TRUCK_HEIGHT = 60 * this.getHeight() / 600;
//        final int TRUCK_WIDTH = 150 * this.getWidth() / 1000;
//        final int TRUCK_AND_LEFT_GAP = 280 * this.getWidth() / 1000;
//        final int TRUCK_AND_DOWN_GAP = 65 * this.getHeight() / 600;
//
//        createPanelWithBackAndForImage(truckLabel,this.getX() + TRUCK_AND_LEFT_GAP,
//                this.getHeight() - TRUCK_HEIGHT - TRUCK_AND_DOWN_GAP, TRUCK_WIDTH, TRUCK_HEIGHT,
//                new Color(0,200,0,0),"truck_0_PS_R.png",this, true);
//
////instead of above paragraph
////        truckLabel.setBounds(this.getX() + TRUCK_AND_LEFT_GAP,
////                this.getHeight() - TRUCK_HEIGHT - TRUCK_AND_DOWN_GAP, TRUCK_WIDTH, TRUCK_HEIGHT);
////        truckLabel.setIcon(new ImageIcon("R_truck_0.jpg"));
////        truckLabel.setBackground(Color.blue);
////        truckLabel.addMouseListener(this);
////        truckLabel.setOpaque(true);
////        layeredPane.add(truckLabel, JLayeredPane.PALETTE_LAYER);
//
//
//
//
////        File file = new File("truck_0.jpg");
////            BufferedImage image = new BufferedImage(ImageIO.read(file));
//
//        final int CELLAR_HEIGHT = 60 * this.getHeight() / 600;
//        final int CELLAR_WIDTH = 130 * this.getWidth() / 1000;
//        final int CELLAR_AND_RIGHT_GAP = 280 * this.getWidth() / 1000;
//        final int CELLAR_AND_DOWN_GAP = TRUCK_AND_DOWN_GAP;
//
//        createPanelWithBackAndForImage(cellarLabel,this.getX() + this.getWidth() - CELLAR_AND_RIGHT_GAP - CELLAR_WIDTH,
//                this.getHeight() - CELLAR_HEIGHT - CELLAR_AND_DOWN_GAP, CELLAR_WIDTH, CELLAR_HEIGHT,
//                new Color(0,200,0,0),"box_PS_R.png",this, true);
//
////instead of above paragraph
////        cellarLabel.setBounds(this.getX() + this.getWidth() - CELLAR_AND_RIGHT_GAP - CELLAR_WIDTH,
////                this.getHeight() - CELLAR_HEIGHT - TRUCK_AND_DOWN_GAP, CELLAR_WIDTH, CELLAR_HEIGHT);
////        cellarLabel.setBackground(Color.RED);
////        cellarLabel.addMouseListener(this);
////        cellarLabel.setOpaque(true);
////        layeredPane.add(cellarLabel, JLayeredPane.PALETTE_LAYER);
//
//        final int WELL_WIDTH = 75 * this.getWidth() / 1000;
//        final int WELL_HEIGHT = 60 * this.getHeight() / 600;
//        final int WELL_AND_TOP_GAP = 70 * this.getHeight() / 600;
//
//        createPanelWithBackAndForImage(wellLabel,this.getX() + this.getWidth() / 2 - WELL_WIDTH / 2,
//                this.getY() + WELL_AND_TOP_GAP,
//                WELL_WIDTH, WELL_HEIGHT,new Color(0,200,0,0),
//                "well_0_Photoshop_R.png",this, true);
//
//        final int WELL_AND_BAR_GAP = 0;
//        final int BAR_WIDTH = wellLabel.getWidth();
//        final int BAR_HEIGHT = 20;
//        int barX = wellLabel.getX();
//        int barY = wellLabel.getY()+ wellLabel.getHeight()+WELL_AND_BAR_GAP;
//
//        wellBar.setBounds(barX,barY,BAR_WIDTH,BAR_HEIGHT);
//        wellBar.setBackground(Color.black);
//        wellBar.setForeground(Color.red);
//        wellBar.setFont(new Font("MV Boli", Font.PLAIN,15));
//        wellBar.setStringPainted(true);
//        wellBar.setValue(manager.wellWater);
//
//        layeredPane.add(wellBar, JLayeredPane.PALETTE_LAYER);
//
//
//
//
//
////        wellLabel = new JLabel();
////        wellLabel.setBounds(this.getX() + this.getWidth() / 2 - WELL_WIDTH / 2, this.getY() + WELL_AND_TOP_GAP,
////                WELL_WIDTH, WELL_HEIGHT);
////        wellLabel.addMouseListener(this);
////        wellLabel.setBackground(null);
////        wellLabel.setIcon(new ImageIcon("well_0_Photoshop_R.png"));
////        wellLabel.setOpaque(true);
////        layeredPane.add(wellLabel, JLayeredPane.PALETTE_LAYER);
//
//        final int[] FACTORIES_WIDTH = {0};
//        final int[] FACTORIES_HEIGHT = {0};
//        int FACTORIES_AND_MARGIN_GAP = 50 * this.getWidth() / 1000;
//        int SAME_COL_FACTORIES_GAP = 70 * this.getHeight() / 600;
//        int FACTORIES_AND_TOP_GAP = 95 * this.getHeight() / 600;
//
//        final int FACTORY_BUTTONS_WIDTH = 80 * this.getWidth() / 1000;
//        final int FACTORY_BUTTONS_HEIGHT = 70 * this.getHeight() / 600;
//        final int SAME_COL_FACTORY_BUTTONS_GAP = 100 * this.getHeight() / 600;
//        final int LAST_ROW_FACTORIES_GAP = 30 * this.getHeight() / 600;
//
//        JButton[][] buildFactoryButtons = new JButton[2][3];
//        ArrayList<Factory> factories = manager.factories;
//        final String[] foreGroundImageName = {new String()};
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                Factory currentFactory = factories.get(3 * i + j);
//                gameFactory[i][j] = new JLabel();
//                factoryWorkingBars[i][j] = new JProgressBar(JProgressBar.VERTICAL,
//                        0,currentFactory.getTimeTakes());
//
//                if (i == 0 && j == 0) {
//                    foreGroundImageName[0] = "mill_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 120 * this.getHeight() / 600;
//
//                } else if (i == 0 && j == 1) {
//                    foreGroundImageName[0] = "weavingF_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 110 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 70 * this.getHeight() / 600;
//
//                } else if (i == 0 && j == 2) {
//                    foreGroundImageName[0] = "packingMilk_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 140 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 100 * this.getHeight() / 600;
//
//                } else if (i == 1 && j == 0) {
//                    foreGroundImageName[0] = "bakery_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 90 * this.getHeight() / 600;
//
//                } else if (i == 1 && j == 1) {
//                    foreGroundImageName[0] = "sewingF_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 100 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 100 * this.getHeight() / 600;
//
//                } else if (i == 1 && j == 2) {
//                    foreGroundImageName[0] = "iceCreamF_PS_R.png";
//
//                    FACTORIES_WIDTH[0] = 150 * this.getWidth() / 1000;
//                    FACTORIES_HEIGHT[0] = 130 * this.getHeight() / 600;
//                    // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
////                    foreGroundImageName = "aviculture_PS_R.png";
//
////                    FACTORIES_WIDTH = 120 * this.getWidth() / 1000;
////                    FACTORIES_HEIGHT = 120 * this.getHeight() / 600;
//
//                }
//
//                int factoryX = FACTORIES_AND_MARGIN_GAP + i *
//                        (this.getWidth() - FACTORIES_WIDTH[0] - 2 * FACTORIES_AND_MARGIN_GAP);
//                int factoryY;
//                if (j == 0) {
//                    factoryY = FACTORIES_AND_TOP_GAP;
//                }else if (j == 2){
//                    factoryY = gameFactory[i][j - 1].getY() + gameFactory[i][j - 1].getHeight()
//                            + LAST_ROW_FACTORIES_GAP;
//                }else{
//                    System.out.println(("i= " + i + ", j= " + j));
//                    factoryY = gameFactory[i][j - 1].getY() + gameFactory[i][j - 1].getHeight()
//                            + SAME_COL_FACTORIES_GAP;
//                }
//
//                gameFactory[i][j].setBounds(factoryX,factoryY,FACTORIES_WIDTH[0],FACTORIES_HEIGHT[0]);
//
//                int buttonX = FACTORIES_AND_MARGIN_GAP + i *
//                        (this.getWidth() - FACTORY_BUTTONS_WIDTH - 2 * FACTORIES_AND_MARGIN_GAP);
//                int buttonY;
//                if (j == 0) {
//                    buttonY = FACTORIES_AND_TOP_GAP;
//                } else {
//                    buttonY = buildFactoryButtons[i][j - 1].getY() + buildFactoryButtons[i][j - 1].getHeight()
//                            + SAME_COL_FACTORY_BUTTONS_GAP;
//                }
//
//                buildFactoryButtons[i][j] = new JButton(currentFactory.getFullFactoryName());
//                buildFactoryButtons[i][j].setBounds(buttonX, buttonY, FACTORY_BUTTONS_WIDTH, FACTORY_BUTTONS_HEIGHT);
//                buildFactoryButtons[i][j].setFocusable(false);
//                buildFactoryButtons[i][j].setFont(new Font("MV Boli", Font.PLAIN,15));
//                buildFactoryButtons[i][j].setBackground(Color.BLUE);
//                buildFactoryButtons[i][j].setForeground(Color.YELLOW);
//                buildFactoryButtons[i][j].setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
//
//                Game currentGame = this;
//                MouseListener workFactoriesListener = this;
//                ActionListener buildFactoryListener = new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        String errorExistingResult = manager.processBuildFactory(currentFactory.getName());
//
//                        if (errorExistingResult.equals("Error. This factory already exists.") ||
//                                errorExistingResult.equals("There is no factory with this name in the game!") ||
//                                errorExistingResult.equals("Error. You don't have enough coins.")) {
//                            JOptionPane.showMessageDialog(layeredPane,
//                                    errorExistingResult,
//                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
//
//                        } else if (errorExistingResult.equals("Factory was built successfully!")) {
//                            int index = factories.indexOf(currentFactory);
//                            int i = 0;
//                            int j = 0;
//
//                            if (index >= 0 && index <= 2) {
//                                i = 0;
//                                j = index;
//                            } else if (index > 2 && index <= 5) {
//                                i = 1;
//                                j = index - 3;
//                            }
//
//                            if (i == 0 && j == 0) {
//                                foreGroundImageName[0] = "mill_PS_R.png";
//                            } else if (i == 0 && j == 1) {
//                                foreGroundImageName[0] = "weavingF_PS_R.png";
//                            } else if (i == 0 && j == 2) {
//                                foreGroundImageName[0] = "packingMilk_PS_R.png";
//                            } else if (i == 1 && j == 0) {
//                                foreGroundImageName[0] = "bakery_PS_R.png";
//                            } else if (i == 1 && j == 1) {
//                                foreGroundImageName[0] = "sewingF_PS_R.png";
//                            } else if (i == 1 && j == 2) {
//                                foreGroundImageName[0] = "iceCreamF_PS_R.png";
//
//                                // TODO: 7/15/2021 for choosing between ice cream and aviculture, based on game level
//                            }
//
////                gameFactory[i][j].setBackground(new Color(0, 150, 0));
////                gameFactory[i][j].setBounds(FACTORIES_AND_MARGIN_GAP + i *
////                                (this.getWidth() - FACTORIES_WIDTH - 2 * FACTORIES_AND_MARGIN_GAP),
////                        FACTORIES_AND_TOP_GAP + j * (FACTORIES_HEIGHT + SAME_COL_FACTORIES_GAP),
////                        FACTORIES_WIDTH, FACTORIES_HEIGHT);
////                gameFactory[i][j].addMouseListener(this);
////                gameFactory[i][j].setOpaque(true);
////                layeredPane.add(gameFactory[i][j], JLayeredPane.PALETTE_LAYER);
//
//                            createPanelWithBackAndForImage(gameFactory[i][j], factoryX, factoryY, FACTORIES_WIDTH[0], FACTORIES_HEIGHT[0],
//                                    new Color(0, 200, 0,0), foreGroundImageName[0], workFactoriesListener, false);
////                            manager.buildFactory(currentFactory.getName());
//
//                            buildFactoryButtons[i][j].setEnabled(false);
//                            layeredPane.remove(buildFactoryButtons[i][j]);
//
//                            final int FACTORY_AND_BAR_GAP = 0;
//                            final int BAR_WIDTH = 20;
//                            final int BAR_HEIGHT = gameFactory[i][j].getHeight();
//                            int barX;
//                            int barY = gameFactory[i][j].getY()+gameFactory[i][j].getHeight()-BAR_HEIGHT;
//                            if (i==0){
//                                barX = gameFactory[i][j].getX()+gameFactory[i][j].getWidth()+FACTORY_AND_BAR_GAP;
//                            }else {
//                                barX = gameFactory[i][j].getX() - FACTORY_AND_BAR_GAP - BAR_WIDTH;
//                            }
//                            factoryWorkingBars[i][j].setBounds(barX,barY,BAR_WIDTH,BAR_HEIGHT);
//                            factoryWorkingBars[i][j].setBackground(Color.black);
//                            factoryWorkingBars[i][j].setForeground(Color.red);
//                            factoryWorkingBars[i][j].setFont(new Font("MV Boli", Font.PLAIN,15));
//                            factoryWorkingBars[i][j].setStringPainted(true);
//                            factoryWorkingBars[i][j].setValue(0);
//
//                            layeredPane.add(factoryWorkingBars[i][j], JLayeredPane.PALETTE_LAYER);
//
//                            final int BAR_AND_UPGRADE_GAP = 0 * currentGame.getWidth()/1000;
//                            final int UPGRADE_BUTTON_WIDTH = 70 * currentGame.getWidth()/1000;
//                            final int UPGRADE_BUTTON_HEIGHT = 70 * currentGame.getHeight()/600;
//                            int upgradeX = (i==0 ? factoryWorkingBars[i][j].getX()+
//                                    factoryWorkingBars[i][j].getWidth()+BAR_AND_UPGRADE_GAP :
//                                    factoryWorkingBars[i][j].getX()-UPGRADE_BUTTON_WIDTH-BAR_AND_UPGRADE_GAP);
//                            int upgradeY = factoryWorkingBars[i][j].getY()+factoryWorkingBars[i][j].getHeight()/2
//                                    -UPGRADE_BUTTON_HEIGHT/2;
//                            JButton upgradeButton = new JButton("UPGRADE");
//                            upgradeButton.setBounds(upgradeX,upgradeY,UPGRADE_BUTTON_WIDTH,UPGRADE_BUTTON_HEIGHT);
//                            upgradeButton.setFocusable(false);
//                            upgradeButton.setFont(new Font("MV Boli", Font.PLAIN, 12));
//                            upgradeButton.setForeground(Color.YELLOW);
//                            upgradeButton.setBackground(Color.BLUE);
//                            upgradeButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
//                            upgradeButton.setIcon(new ImageIcon("upgrade_R2.png"));
//                            upgradeButton.setVerticalTextPosition(JButton.BOTTOM);
//                            upgradeButton.setHorizontalTextPosition(JButton.CENTER);
//                            ActionListener upgradeListener = new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    manager.upgradeFactory(currentFactory.getName());
//                                    upgradeButton.setEnabled(false);
//                                    layeredPane.remove(upgradeButton);
//                                    SwingUtilities.updateComponentTreeUI(currentGame);
//                                    JOptionPane.showMessageDialog(layeredPane,
//                                            currentFactory.getFullFactoryName() + " upgraded successfully.",
//                                            "UPGRADE SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE,
//                                            new ImageIcon("done.png"));
//                                    manager.writeLogFile("Info", "Upgraded factory successfully");
//                                }
//                            };
//                            upgradeButton.addActionListener(upgradeListener);
//
//                            layeredPane.add(upgradeButton, JLayeredPane.PALETTE_LAYER);
//
//                            layeredPane.remove(buildFactoryButtons[i][j]);
//                            SwingUtilities.updateComponentTreeUI(currentGame);
//
//                            JOptionPane.showMessageDialog(layeredPane,
//                                    errorExistingResult,
//                                    "BUILD SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE,
//                                    new ImageIcon("done.png"));
//                        }
//                    }
//                };
//
//                buildFactoryButtons[i][j].addActionListener(buildFactoryListener);
//
//                layeredPane.add(buildFactoryButtons[i][j], JLayeredPane.PALETTE_LAYER);
//
//            }
//
//
//
//
//
//
//
//
//        }
//
//
//
//        this.setVisible(true);
//        this.setLayout(null);
//        ImageIcon groundIcon = new ImageIcon("ground.png");
//        ImageIcon grassIcon = new ImageIcon("flowerGrass_PS_R2.png");
////        for panel
//
//        final int GAME_PLANTS_AND_MIDDLE_GAP = -40;
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//
//                gamePlant[i][j] = new JLabel();
//                gamePlant[i][j].setBackground(new Color(0, 200, 0,0));
//                gamePlant[i][j].setBounds(this.getX() + this.getWidth() / 2 + (i - 3) * wid,
//                        this.getY() + this.getHeight() / 2 + (j - 3) * height - GAME_PLANTS_AND_MIDDLE_GAP,
//                        wid, height);
//                gamePlant[i][j].addMouseListener(this);
//                gamePlant[i][j].setOpaque(true);
//                gamePlant[i][j].setIcon(groundIcon);
////                groundLabels[i][j] = createGroundAndGrassLabel(i,j,groundIcon);
////                for panel
////                JLabel groundLabel = new JLabel(groundIcon);
////                groundLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
////                gamePlant[i][j].add(groundLabel);
//
//                layeredPane.add(gamePlant[i][j], JLayeredPane.PALETTE_LAYER);
//            }
//        }
//
//        showDetails();
//        //setPos
//        this.add(panel);
//        this.setVisible(true);
//
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                manager.turn(1);
//                turn();
//            }
//        },1000,1000);
//    }
//    //    public void run(){
//////        while(true){
//////        try {
//////            Thread.sleep(1000);
//////        } catch (InterruptedException e) {
//////            e.printStackTrace();
//////        }
////        long counter = 0;
////        while (counter <100000000){
////            counter++;
////        }
////        System.out.println("turning (main run)...");
////        manager.turn(1);
////        this.turn();
////        SwingUtilities.updateComponentTreeUI(this);
//////        processShowDetails();
//////        }
////
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (e.getSource() == wellLabel)
//        {
//            //only for test
//
////            layeredPane.remove(wellLabel);
////            SwingUtilities.updateComponentTreeUI(this);
//
//            //only for test
//            if (manager.fillingWellTimeCounter != 0){
//                JOptionPane.showMessageDialog(this,
//                        "Well is already filling!",
//                        "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
//                manager.writeLogFile("Error", "Repeated filling well");
//            }
//            else if (manager.wellWater != 0) {
//                JOptionPane.showMessageDialog(this,
//                        "Error. Well must be empty.",
//                        "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
//                manager.writeLogFile("Error", "Error filling well");
//            }
//            else {
//                manager.startFillingWell();
//                JOptionPane.showMessageDialog(this,
//                        "Filling well...",
//                        "Filling", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//                manager.writeLogFile("Info", "Filled well");
//            }
//        }
//        else if (e.getSource()==truckLabel)
//        {
//            new TruckPage(manager);
//            //TODO//pause
//        }
//        else if (e.getSource()==cellarLabel)
//        {
//            new GoodList(manager.cellar);
//            //TODO//pause
//        }
//        for(int i = 0 ; i < 6 ; i++)
//        {
//            for (int j = 0 ; j < 6 ; j++)
//            {
//                if (e.getSource()==gamePlant[i][j])
//                {
////                    if (manager.badWildAnimalCheck(i+1,j+1))
////                    {
//////                        manager.cager(i+1,j+1);
//////                        System.out.println("cager worked!(in this)");
////                    }
//                    /*else */if (manager.checkGood(i+1,j+1))
//                {
//                    manager.Catch(i+1,j+1);
//                    // TODO: 7/12/2021
//                    System.out.println("Goods were picked up.");
//                }
//                else
//                {
//                    if (manager.wellWater >= 1) {
//                        JOptionPane.showMessageDialog(this,
//                                "Planted grass on x=" + (i+1) + " , y=" + (j+1),
//                                "Filling", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//                        manager.plantGrass(i+1, j+1);
//
////                            ImageIcon bushIcon = new ImageIcon(/*freeTileset\\png\\Object\\Bush (1)*//*"labelsGrass.jpg"*/"flowerGrass_PS_R2.png");
//////                            gamePlant[i][j].setIcon(bushIcon);
//////                            for panel
////                            JLabel bushLabel = new JLabel(bushIcon);
////                            bushLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
////                            bushLabel.setOpaque(true);
////                            layeredPane.add(bushLabel, JLayeredPane.PALETTE_LAYER);
//
//                        ImageIcon grassIcon = new ImageIcon("flowerGrass_PS_R2.png");
////                            gamePlant[i][j].setIcon(grassIcon);
//                        JLabel grassLabel = new JLabel(grassIcon);
//                        grassLabel.setBackground(new Color(116, 57, 28, 0));
//                        grassLabel.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), gamePlant[i][j].getWidth(), gamePlant[i][j].getHeight());
//                        grassLabel.setOpaque(true);
//                        layeredPane.add(grassLabel, JLayeredPane.MODAL_LAYER);
//
//                        //                            gamePlant[i][j].add(bushLabel);
////                            gamePlant[i][j].setOpaque(true);
//
//
////                            gamePlant[i][j].setIcon(new ImageIcon("freeTileset\\png\\Object\\Bush (1).png"));
//
//
//
//
//
//
////                            for (int i1 = 0; i1 < 6; i1++) {
////                                for (int j1 = 0; j1 < 6; j1++) {
////                                    if (existsPointInLabel(e.getX(), e.getY(), gamePlant[i1][j1])){
////                                    }
////                                }
////                            }
////                            System.out.println("Planted grass on x=" + (i+1) + " , y=" + (j+1));
//                        manager.writeLogFile("Info", "Planted grass on " + i+1 + "," + j+1);
//                        wellBar.setValue(wellBar.getValue()-1);
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(this,
//                                "Error. There is not enough water.",
//                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
////                            System.out.println("Error. There is not enough water.");
//                        manager.writeLogFile("Error", "Water lack for planting");
//                    }
//                }
//                }
//            }
//        }
//        for (int i = 0 ; i < 2 ; i++)
//        {
//            for (int j = 0 ; j < 3 ; j++)
//            {
//                if (e.getSource()==gameFactory[i][j])
//                {
//
//                    String errorExistingResult = manager.processWork(i*7,2*j+1);
//                    System.out.println("result = "+errorExistingResult);
//                    if (errorExistingResult.equals("Factory is already working!") || errorExistingResult.equals("An error occurred!")
//                            || errorExistingResult.equals("There is not enough required good!")){
//                        JOptionPane.showMessageDialog(this,
//                                errorExistingResult,
//                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
//                    }else if (errorExistingResult.equals("Factory started working...")){
//
//
//
//
//                        JOptionPane.showMessageDialog(this,
//                                errorExistingResult,
//                                "Work started", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    public boolean existsPointInLabel(int x, int y, JLabel label){
//        if (label.getX() <= x && label.getX()+label.getWidth() >= x && label.getY()<=y && label.getY()+label.getHeight()>= y)
//            return true;
//        return false;
//    }
//    public void createPanelWithBackAndForImage(JLabel label,
//                                               int x, int y, int width, int height,
//                                               Color backgroundColor,
//                                               String foregroundImageName, MouseListener mouseListener,
//                                               boolean setBounds){
//
////        JLayeredPane helpLayeredPane = new JLayeredPane();
////        helpLayeredPane.setBounds(x,y,width,height);
//
////        ImageIcon backIcon = new ImageIcon(backgroundImageName);
//        ImageIcon foreIcon = new ImageIcon(foregroundImageName);
//
////        JLabel backLabel = new JLabel();
//
//        label.setBackground(backgroundColor);
//
////        backLabel.setBounds(x,y,width,height);
//        if (setBounds)
//            label.setBounds(x,y,width,height);
//
////        backLabel.setIcon(backIcon);
//        label.setIcon(foreIcon);
//
//        label.addMouseListener(mouseListener);
//
////        backLabel.setOpaque(true);
//        label.setOpaque(true);
//
////        layeredPane.add(backLabel, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(label, JLayeredPane.MODAL_LAYER);
//
//
////        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);
//    }
//
//    public void turn() {
//
//        if (manager.wellWater == 0){
//            if (manager.fillingWellTimeCounter == 2){
//                wellBar.setValue(2);
//            }else if(manager.fillingWellTimeCounter == 1){
//                wellBar.setValue(4);
//            }
//        }else{
//            wellBar.setValue(manager.wellWater);
//        }
//
//        for (int i =0;i<manager.factories.size();i++) {
//            Factory currentFactory = manager.factories.get(i);
//
//            if (currentFactory.isBuilt()) {
//                int progressBarI = (i <= 2 ? 0:1);
//                int progressBarJ = (i <= 2 ? i : i - 3);
//
//                int newProgressValue = currentFactory.getTimeTakes() - currentFactory.getWorkingRemainingTime();
//
////                System.out.println("progressBarI= "+progressBarI);
////                System.out.println("progressBarJ= "+progressBarJ);
//
//                if (!currentFactory.isWorking()){
//                    int previousBarValue = factoryWorkingBars[progressBarI][progressBarJ].getValue();
//                    if (previousBarValue != 0){
//                        factoryWorkingBars[progressBarI][progressBarJ].setValue(0);
//                    }
//                }else{
//                    factoryWorkingBars[progressBarI][progressBarJ].setValue(newProgressValue);
//                }
//            }
////                factory.setWorkingRemainingTime(factory.getWorkingRemainingTime() - /*1*/ factory.getLevel());
////                if (manager.factories.get(i).getWorkingRemainingTime() <=0){
////                    finishWork(factory);
////                }
////            }else{
////            }
//        }
//
//        for (JLabel animalLabel : animalLabels) {
//            layeredPane.remove(animalLabel);
//        }
//        for (JLabel cageLabel : cageLabels) {
//            layeredPane.remove(cageLabel);
//        }
//        animalLabels = new ArrayList<>();
//        cageLabels = new ArrayList<>();
//
//        for (Animal animal : manager.animalInMap) {
//            int i = animal.getX()-1;
//            int j = animal.getY()-1;
//
//            ImageIcon animalIcon = new ImageIcon();
//
//            if ((animal.getX() - animal.getPrevX() == 0 && animal.getY() - animal.getPrevY() == 0) || (animal.getX() - animal.getPrevX() == -1))
//                animalIcon = new ImageIcon(animal.getName()+"\\left_C.png");
//            else if (animal.getX() - animal.getPrevX() == 1)
//                animalIcon = new ImageIcon(animal.getName()+"\\right_C.png");
//            else if (animal.getY() - animal.getPrevY() == -1)
//                animalIcon = new ImageIcon(animal.getName()+"\\up_C.png");
//            else/* if (animal.getY() - animal.getPrevY() == 1)*/
//                animalIcon = new ImageIcon(animal.getName()+"\\down_C.png");
//
//
//
//
//            JLabel animalLabel = new JLabel(animalIcon);
//            animalLabel.setBounds(gamePlant[i][j].getX(),gamePlant[i][j].getY(),wid-1,height-1);
//            animalLabel.setBackground(new Color(0,0,0,0));
//            animalLabel.setOpaque(true);
//
//
//
//
//
//
//
//            if (animal instanceof BadWildAnimal){
//                String exactImageAddress = new String();
//                if (((BadWildAnimal) animal).getCagesCount() == ((BadWildAnimal) animal).getCagesRequiredCount()){
//
//                    ImageIcon cagedAnimalIcon = new ImageIcon(animal.getName()+"\\caged_C.png");
//                    animalLabel.setIcon(cagedAnimalIcon);
//
//                    exactImageAddress = "complete.png";
//                }else if (((BadWildAnimal) animal).getCagesCount() < ((BadWildAnimal) animal).getCagesRequiredCount()){
//                    exactImageAddress = "build"+((BadWildAnimal) animal).getCagesCount()+".png";
//                }
//
//                ImageIcon cageIcon = new ImageIcon(animal.getName()+"Cage\\"+exactImageAddress);
//
//                JLabel cageLabel = new JLabel(cageIcon);
//                cageLabel.setBounds(animalLabel.getX(),animalLabel.getY(),
//                        animalLabel.getWidth()-1,animalLabel.getHeight()-1);
//                cageLabel.setBackground(new Color(0,0,0,0));
//                cageLabel.setOpaque(true);
//
//                Game currentGame=this;
//                MouseListener cageListener = new MouseListener() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
////                        if (manager.badWildAnimalCheck(i+1,j+1))
////                        {
////                            manager.cager(i+1,j+1);
////                            System.out.println("cager worked!(in cageListener)\ncagesCount = "+((BadWildAnimal) animal).getCagesCount());
////                        }
////
//                    }
//
//                    @Override
//                    public void mousePressed(MouseEvent e) {
////                        if (manager.badWildAnimalCheck(i+1,j+1))
////                        {
//                        manager.cager(animal.getPrevX(),animal.getPrevY());
//                        System.out.println("cager worked!(in cageListener)\ncagesCount = "+((BadWildAnimal) animal).getCagesCount());
////                        }
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                    }
//
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//
//                    }
//                };
//                cageLabel.addMouseListener(cageListener);
//                cageLabels.add(cageLabel);
//                layeredPane.add(cageLabel, JLayeredPane.POPUP_LAYER);
//            }
//
//
//
//            animalLabels.add(animalLabel);
//            layeredPane.add(animalLabel, JLayeredPane.MODAL_LAYER);
//            SwingUtilities.updateComponentTreeUI(this);
//
//        }
//
////        for (int i = 0; i < 6; i++) {
////            for (int j = 0; j < 6; j++) {
////                int grassCount = manager.getGrassCount(i,j);
////                if (grassCount <= 0){
////                    if (grassLabel[i][j] != null) {
////                        layeredPane.remove(grassLabel[i][j]);
////                        grassLabel[i][j] = null;
////                    }
////                }else{
////                    if (grassLabel[i][j] == null) {
////                        grassLabel[i][j] = new JLabel(new ImageIcon("flowerGrass_PS_R2.png"));
////                        grassLabel[i][j].setBackground(new Color(0, 0, 0, 0));
////                    }
////                        layeredPane.add(grassLabel[i][j], JLayeredPane.MODAL_LAYER);
////                }
////            }
////        }
//        showDetails();
//        SwingUtilities.updateComponentTreeUI(this);
//    }
//
//
//    public void enableAndDisableShopButtons(){
//        if (manager.coins < 100) {
//            buyChickenButton.setEnabled(false);
//            buyDogButton.setEnabled(false);
//        }else{
//            buyChickenButton.setEnabled(true);
//            buyDogButton.setEnabled(true);
//        }
//
//        if (manager.coins < 200){
//            buyTurkeyButton.setEnabled(false);
//        }else{
//            buyTurkeyButton.setEnabled(true);
//        }
//
//        if (manager.coins < 400){
//            buyBuffaloButton.setEnabled(false);
//        }else{
//            buyBuffaloButton.setEnabled(true);
//        }
//
//        if (manager.coins < 150){
//            buyCatButton.setEnabled(false);
//        }else{
//            buyCatButton.setEnabled(true);
//        }
//
//    }
//
//    @Override
//    public void run() {
//
//    }
//
//    public JLabel createGroundAndGrassLabel(int i, int j, ImageIcon imageIcon){
//        JLabel label = new JLabel(imageIcon);
//        label.setBounds(gamePlant[i][j].getX(), gamePlant[i][j].getY(), wid, height);
//        label.setBackground(new Color(0,0,0,0));
//        return label;
//    }
//
//    public void showDetails(){
//        for (JLabel detailsLabel : detailsLabels) {
//            layeredPane.remove(detailsLabel);
//        }
//
//        detailsLabels = new ArrayList<>();
//
//        final int DETAILS_AND_MARGIN_GAP = 5 * this.getWidth()/1200;
//        final int DETAILS_AND_TRUCK_GAP = 5 * this.getHeight()/800;
//        final int DETAILS_WIDTH = 145 * this.getWidth()/1200;
//        final int DETAILS_HEIGHT = 55 * this.getHeight()/800;
//        final int ICON_AND_TEXT_GAP = 10 * this.getWidth()/1200;
//
//        ImageIcon timeIcon = new ImageIcon("clock_PS_R.png");
//        JLabel timeLabel = new JLabel();
//        timeLabel.setIcon(timeIcon);
//        timeLabel.setText(Integer.toString(manager.time));
//        timeLabel.setBounds(this.getX()+DETAILS_AND_MARGIN_GAP,
//                truckLabel.getY()+truckLabel.getHeight()+DETAILS_AND_TRUCK_GAP,DETAILS_WIDTH,DETAILS_HEIGHT);
//        timeLabel.setBackground(Color.BLUE);
//        timeLabel.setForeground(Color.YELLOW);
//        timeLabel.setFont(new Font("MV Boli", Font.PLAIN,20));
//        timeLabel.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
//        timeLabel.setIconTextGap(ICON_AND_TEXT_GAP);
//        timeLabel.setOpaque(true);
//        detailsLabels.add(timeLabel);
//        layeredPane.add(timeLabel, JLayeredPane.PALETTE_LAYER);
//
//        ImageIcon coinsIcon = new ImageIcon("coin_PS_R.png");
//        JLabel coinsLabel = new JLabel();
//        coinsLabel.setIcon(coinsIcon);
//        coinsLabel.setText((manager.coins)+"/"+(manager.level.getTaskCoins()));
//        coinsLabel.setBounds(timeLabel.getX()+timeLabel.getWidth(),
//                truckLabel.getY()+truckLabel.getHeight()+DETAILS_AND_TRUCK_GAP,DETAILS_WIDTH,DETAILS_HEIGHT);
//        coinsLabel.setBackground(Color.BLUE);
//        coinsLabel.setForeground(Color.YELLOW);
//        coinsLabel.setFont(new Font("MV Boli", Font.PLAIN,12));
//        coinsLabel.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
//        coinsLabel.setOpaque(true);
//        detailsLabels.add(coinsLabel);
//        layeredPane.add(coinsLabel, JLayeredPane.PALETTE_LAYER);
//
//        int lastLabelX = coinsLabel.getX();
//
//        if (manager.level.getTaskGoods().size() != 0) {
//            for (Good taskGood : manager.level.getTaskGoods()) {
//
//                int index = manager.level.getTaskGoods().indexOf(taskGood);
//
//                for (Good good : manager.cellar.getGoods()) {
//                    if (good.getName().equals(taskGood.getName())) {
//                        ImageIcon goodIcon = new ImageIcon(good.getName() + "_PS_R.png");
//                        JLabel goodLabel = new JLabel();
//                        goodLabel.setIcon(goodIcon);
//                        goodLabel.setText((good.getCount()) + "/" + (taskGood.getCount()));
//                        goodLabel.setBounds(coinsLabel.getX() + coinsLabel.getWidth() + index * DETAILS_WIDTH,
//                                truckLabel.getY() + truckLabel.getHeight() + DETAILS_AND_TRUCK_GAP,
//                                DETAILS_WIDTH, DETAILS_HEIGHT);
//                        goodLabel.setBackground(Color.BLUE);
//                        goodLabel.setForeground(Color.YELLOW);
//                        goodLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
//                        goodLabel.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
//                        goodLabel.setIconTextGap(ICON_AND_TEXT_GAP);
//                        goodLabel.setOpaque(true);
//                        detailsLabels.add(goodLabel);
//                        layeredPane.add(goodLabel, JLayeredPane.PALETTE_LAYER);
//
//                        lastLabelX = goodLabel.getX();
//                    }
//                }
//            }
//        }
//
//        if (manager.level.getTaskAnimals().size() != 0) {
//            for (int i = 0; i < manager.level.getTaskAnimals().size(); i++) {
//                String imageAddress = manager.level.getTaskAnimals().get(i).getName()+"\\right_C.png";
//                String text = manager.animalCountOnMap(manager.level.getTaskAnimals().get(i).getName())
//                        + "/" + manager.level.getEachTaskAnimalCount().get(i);
//                ImageIcon animalIcon = new ImageIcon(imageAddress);
//                JLabel animalLabel = new JLabel();
//                animalLabel.setIcon(animalIcon);
//                animalLabel.setText(text);
//                animalLabel.setBounds(lastLabelX + DETAILS_WIDTH + i * DETAILS_WIDTH,
//                        truckLabel.getY() + truckLabel.getHeight() + DETAILS_AND_TRUCK_GAP,
//                        DETAILS_WIDTH, DETAILS_HEIGHT);
//                animalLabel.setBackground(Color.BLUE);
//                animalLabel.setForeground(Color.YELLOW);
//                animalLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
//                animalLabel.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
//                animalLabel.setIconTextGap(ICON_AND_TEXT_GAP);
//                animalLabel.setOpaque(true);
//                detailsLabels.add(animalLabel);
//                layeredPane.add(animalLabel, JLayeredPane.PALETTE_LAYER);
//            }
//        }
//    }
//}