import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Scanner;

public class AdminInputProcessor {
    private AdminManager adminManager = new AdminManager();
    private Scanner scanner = new Scanner(System.in);

    public AdminInputProcessor(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    private int processAddGood(String name, int sellPrice, int buyPrice, int count) {

//        if (split.length != 5)
//            System.out.println("Error adding good . Please check your input.");
//
//        else if (Integer.parseInt(split[4]) > Integer.parseInt(split[3]))
//            System.out.println("Error . Sell price is less than buy price !");
//
//        else {
//            boolean isNewGood = true;
//            for (Good good : adminManager.getGoods()) {
//                if (split[1].equals(good.getName())) {
//                    System.out.println("Error . This item has already exists in the market !");
//                    isNewGood = false;
//                    break;
//                }
//            }
//            if (isNewGood) {
                Random random = new Random();
                int randomID = random.nextInt(90000) + 10000;
                boolean existsID = true;
                while (existsID) {
                    existsID = false;
                    for (Good good : adminManager.getGoods()) {
                        if (good.getID() == randomID) {
                            existsID = true;
                            randomID = random.nextInt(90000) + 10000;
                        }
                    }
                }
                Good result = new Good(name, randomID,buyPrice,
                        sellPrice, count, 0);
                adminManager.addGood(result);
                return result.getID();
//            }
        }

    private void processLoadObjects(String[] objectsNames) {
        for (int i = 0; i < objectsNames.length; i++) {
            adminManager.loadObjects(objectsNames[i]);
        }
    }

    public void run() {
        processLoadObjects(new String[]{"goods", "unavailableGoods", "orders", "checkedOrders",
                "sales count", "sales price", "profit"});
        processMenu();
    }
    
    private void processMenu() {

        FrameCreator menuFrame = new FrameCreator("MENU", 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Select one");

        mainLabel.setForeground(new Color(7, 31, 186));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(menuFrame.getX(), menuFrame.getY(), menuFrame.getWidth(), menuFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * menuFrame.getWidth() / 1600));


        final int BUTTON_WIDTH = 300 * menuFrame.getWidth() / 1600;
        final int BUTTON_HEIGHT = 60 * menuFrame.getHeight() / 800;
        final int TOP_AND_BUTTONS_GAP = 200 * menuFrame.getHeight() / 800;
        final int BUTTONS_GAP = 20 * menuFrame.getHeight() / 800;
        final int EXIT_AND_BOTTOM_GAP = 50 * menuFrame.getHeight() / 800;


        JButton showAvailableButton = new JButton("SHOW AVAILABLE GOODS");
        JButton showUnavailableButton = new JButton("SHOW UNAVAILABLE GOODS");
        JButton showAllButton = new JButton("SHOW ALL GOODS");
        JButton checkoutButton = new JButton("CHECKOUT ORDERS");
        JButton ordersHistoryButton = new JButton("ORDERS HISTORY");
        JButton sellsProfitButton = new JButton("SHOW PROFIT AND SELLS");
        JButton exitButton = new JButton("EXIT");

        showAvailableButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        showUnavailableButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                showAvailableButton.getY() + showAvailableButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        showAllButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                showUnavailableButton.getY() + showUnavailableButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        checkoutButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                showAllButton.getY() + showAllButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        ordersHistoryButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                checkoutButton.getY() + checkoutButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        sellsProfitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                ordersHistoryButton.getY() + ordersHistoryButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        exitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + menuFrame.getHeight() - BUTTON_HEIGHT - EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        showAvailableButton.setBackground(Color.BLUE);
        showUnavailableButton.setBackground(Color.BLUE);
        showAllButton.setBackground(Color.BLUE);
        checkoutButton.setBackground(Color.BLUE);
        ordersHistoryButton.setBackground(Color.BLUE);
        sellsProfitButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        showAvailableButton.setForeground(Color.YELLOW);
        showUnavailableButton.setForeground(Color.YELLOW);
        showAllButton.setForeground(Color.YELLOW);
        checkoutButton.setForeground(Color.YELLOW);
        ordersHistoryButton.setForeground(Color.YELLOW);
        sellsProfitButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        showAvailableButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        showUnavailableButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        showAllButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        checkoutButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        ordersHistoryButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        sellsProfitButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        showAvailableButton.setFocusable(false);
        showUnavailableButton.setFocusable(false);
        showAllButton.setFocusable(false);
        checkoutButton.setFocusable(false);
        ordersHistoryButton.setFocusable(false);
        sellsProfitButton.setFocusable(false);
        exitButton.setFocusable(false);

        showAvailableButton.setVerticalAlignment(mainLabel.CENTER);
        showUnavailableButton.setVerticalAlignment(mainLabel.CENTER);
        showAllButton.setVerticalAlignment(mainLabel.CENTER);
        checkoutButton.setVerticalAlignment(mainLabel.CENTER);
        ordersHistoryButton.setVerticalAlignment(mainLabel.CENTER);
        sellsProfitButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        showAvailableButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        showUnavailableButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        showAllButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        checkoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        ordersHistoryButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        sellsProfitButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * menuFrame.getWidth() / 1600));

        ActionListener showAvailableListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowGoods(adminManager.getGoods(), true, 0, 1);
            }
        };
        ActionListener showUnavailableListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowGoods(adminManager.getUnavailableGoods(), false, 0, 1);

            }
        };
        ActionListener showAllListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowAllGoods(0, 1);
            }
        };
        ActionListener checkoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowOrders(0,1);
            }
        };
        ActionListener historyListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowHistory(0,1);
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        ActionListener profitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowProfit(0,1);
            }
        };

        showAvailableButton.addActionListener(showAvailableListener);
        showUnavailableButton.addActionListener(showUnavailableListener);
        showAllButton.addActionListener(showAllListener);
        checkoutButton.addActionListener(checkoutListener);
        ordersHistoryButton.addActionListener(historyListener);
        sellsProfitButton.addActionListener(profitListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(showAvailableButton);
        mainLabel.add(showUnavailableButton);
        mainLabel.add(showAllButton);
        mainLabel.add(checkoutButton);
        mainLabel.add(ordersHistoryButton);
        mainLabel.add(sellsProfitButton);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        menuFrame.setComponents("background.jpg", components);


    }


    private void processShowAllGoods(int startingIndex, int pageNum) {

        ArrayList<Good> goods = new ArrayList<>();
        for (Good good : adminManager.getGoods()) {
            goods.add(good);
        }
        for (Good unavailableGood : adminManager.getUnavailableGoods()) {
            goods.add(unavailableGood);
        }
            String frameTitle = ("ALL GOODS");
            FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                    null, 3, false);

            JLabel mainLabel = new JLabel("ALL GOODS (PAGE " + pageNum + ")");
            JLabel topLabel = new JLabel();

            mainLabel.setPreferredSize(new Dimension(1600, 800));


            mainLabel.setForeground(new Color(156, 54, 6));
            mainLabel.setVerticalAlignment(JLabel.TOP);
            mainLabel.setHorizontalAlignment(JLabel.CENTER);
            mainLabel.setLayout(null);
            mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
            mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));

            topLabel.setForeground(new Color(156, 54, 6));
            topLabel.setVerticalAlignment(JLabel.TOP);
            topLabel.setHorizontalAlignment(JLabel.CENTER);
            topLabel.setLayout(null);
            final int MONEY_LABEL_HEIGHT = 50 * frame.getHeight() / 800;
            final int MONEY_LABEL_AND_TOP_GAP = 100 * frame.getHeight() / 800;
            topLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
            topLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));


            ActionListener backListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    processMenu();
                }
            };
            ActionListener addListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    createAddNameFrame();
                }
            };

            final int TOP_AND_BUTTONS_GAP = 20;
            final int BACK_AND_LEFT_GAP = 15 * frame.getWidth() / 1600;
            final int NEXT_AND_RIGHT_GAP = 90 * frame.getWidth() / 1600;
            final int NEXT_AND_PREV_GAP = 5;
            final int TOP_BUTTONS_WIDTH = 175 * frame.getWidth() / 1600;
            JButton backButton = new JButton("MAIN MENU");
            backButton.setBounds(topLabel.getX() + BACK_AND_LEFT_GAP,
                    topLabel.getY(), TOP_BUTTONS_WIDTH, topLabel.getHeight());
            backButton.setBackground(Color.BLUE);
            backButton.setForeground(Color.YELLOW);
            backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
            backButton.setFocusable(false);
            backButton.setVerticalAlignment(topLabel.CENTER);
            backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
            backButton.addActionListener(backListener);

            JButton addButton = new JButton("ADD NEW GOOD");
            addButton.setBounds(frame.getFrameCenter().x-TOP_BUTTONS_WIDTH/2,
                    topLabel.getY(), TOP_BUTTONS_WIDTH, topLabel.getHeight());
            addButton.setBackground(Color.BLUE);
            addButton.setForeground(Color.YELLOW);
            addButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
            addButton.setFocusable(false);
            addButton.setVerticalAlignment(topLabel.CENTER);
            addButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
            addButton.addActionListener(addListener);

            JButton nextButton = new JButton("NEXT");
            JButton prevButton = new JButton("PREVIOUS");


            nextButton.setBounds(frame.getX() + frame.getWidth() - TOP_BUTTONS_WIDTH - NEXT_AND_RIGHT_GAP,
                    mainLabel.getY() + TOP_AND_BUTTONS_GAP,
                    TOP_BUTTONS_WIDTH, topLabel.getHeight());
            prevButton.setBounds(nextButton.getX() - NEXT_AND_PREV_GAP - TOP_BUTTONS_WIDTH,
                    mainLabel.getY() + TOP_AND_BUTTONS_GAP, TOP_BUTTONS_WIDTH,
                    topLabel.getHeight());

            nextButton.setBackground(Color.BLUE);
            prevButton.setBackground(Color.BLUE);

            nextButton.setForeground(Color.YELLOW);
            prevButton.setForeground(Color.YELLOW);

            nextButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
            prevButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

            nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
            prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

            nextButton.setFocusable(false);
            prevButton.setFocusable(false);

            nextButton.setVerticalAlignment(topLabel.CENTER);
            prevButton.setVerticalAlignment(topLabel.CENTER);


            final int TEXTS_AND_TABLE_GAP = 15 * frame.getHeight() / 800;
            final int ROWS_GAP = 10 * frame.getHeight() / 800;
            int lastUsedY = frame.getY() + (topLabel.getY() + topLabel.getHeight());
            int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
            int goodsCount = goods.size();
            final int buttonsHeight = 55 /*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
            final int BUTTONS_PER_PAGE = remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 > 0 ? remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 : 0;

            final int TOTAL_FREE_WIDTH = frame.getWidth() - 150;
            final int BUTTONS_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
            final int COL1_WIDTH = BUTTONS_WIDTH;
            final int COL2_WIDTH = (200 * TOTAL_FREE_WIDTH / 1450);
            final int COL3_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
            final int COL4_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
            final int COL5_WIDTH = (175 * TOTAL_FREE_WIDTH / 1450);

            if (startingIndex == 0) {
                prevButton.setEnabled(false);
            } else {
                ActionListener prevListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        processShowAllGoods(startingIndex - BUTTONS_PER_PAGE, pageNum - 1);
                    }
                };
                prevButton.addActionListener(prevListener);
            }

            nextButton.setEnabled(false);

            JLabel goodNameLabel = new JLabel("GOOD NAME");
            JLabel goodIDLabel = new JLabel("GOOD ID");
            JLabel goodBuyPriceLabel = new JLabel("BUY PRICE(T)");
            JLabel goodSellPriceLabel = new JLabel("SELL PRICE(T)");
            JLabel goodCountLabel = new JLabel("COUNT");

            goodNameLabel.setBounds(frame.getX(), topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL1_WIDTH, buttonsHeight);
            goodIDLabel.setBounds(goodNameLabel.getX() + goodNameLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL2_WIDTH, buttonsHeight);
            goodBuyPriceLabel.setBounds(goodIDLabel.getX() + goodIDLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL3_WIDTH, buttonsHeight);
            goodSellPriceLabel.setBounds(goodBuyPriceLabel.getX() + goodBuyPriceLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL4_WIDTH, buttonsHeight);
            goodCountLabel.setBounds(goodSellPriceLabel.getX() + goodSellPriceLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL5_WIDTH, buttonsHeight);

            goodNameLabel.setForeground(new Color(156, 54, 6));
            goodIDLabel.setForeground(new Color(156, 54, 6));
            goodBuyPriceLabel.setForeground(new Color(156, 54, 6));
            goodSellPriceLabel.setForeground(new Color(156, 54, 6));
            goodCountLabel.setForeground(new Color(156, 54, 6));

            goodNameLabel.setVerticalAlignment(JLabel.CENTER);
            goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

            goodIDLabel.setVerticalAlignment(JLabel.CENTER);
            goodIDLabel.setHorizontalAlignment(JLabel.CENTER);

            goodBuyPriceLabel.setVerticalAlignment(JLabel.CENTER);
            goodBuyPriceLabel.setHorizontalAlignment(JLabel.CENTER);

            goodSellPriceLabel.setVerticalAlignment(JLabel.CENTER);
            goodSellPriceLabel.setHorizontalAlignment(JLabel.CENTER);

            goodCountLabel.setVerticalAlignment(JLabel.CENTER);
            goodCountLabel.setHorizontalAlignment(JLabel.CENTER);


            goodNameLabel.setLayout(null);
            goodIDLabel.setLayout(null);
            goodBuyPriceLabel.setLayout(null);
            goodSellPriceLabel.setLayout(null);
            goodCountLabel.setLayout(null);

            goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodBuyPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodSellPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodCountLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));

            mainLabel.add(backButton);
            mainLabel.add(nextButton);
            mainLabel.add(prevButton);
            mainLabel.add(addButton);
            mainLabel.add(topLabel);
            mainLabel.add(goodNameLabel);
            mainLabel.add(goodIDLabel);
            mainLabel.add(goodBuyPriceLabel);
            mainLabel.add(goodSellPriceLabel);
            mainLabel.add(goodCountLabel);

            if (goods.size() == 0){
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
            }

            int i;
            for (i = startingIndex; i < goods.size(); i++) {
                if (i - startingIndex + 1 > BUTTONS_PER_PAGE) {
                    int nextStartingIndex = i;
                    nextButton.setEnabled(true);
                    ActionListener nextListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == nextButton) {
                                frame.dispose();
                                processShowAllGoods(nextStartingIndex, pageNum + 1);
                            }
                        }
                    };
                    nextButton.addActionListener(nextListener);
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                    return;
                } else {
                    Good currentGood = goods.get(i);
                    JButton newButton = new JButton(currentGood.getName());
                    newButton.setBounds(goodNameLabel.getX(),
                            goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            BUTTONS_WIDTH, buttonsHeight);
                    newButton.setBackground(Color.BLUE);
                    newButton.setForeground(Color.YELLOW);
                    newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                    newButton.setFocusable(false);
                    newButton.setVerticalAlignment(newButton.CENTER);
                    newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                    ActionListener newListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            createGoodOptionsFrame(currentGood);
                        }
                    };

                    newButton.addActionListener(newListener);

                    JLabel IDLabel = new JLabel(Integer.toString(currentGood.getID()));
                    JLabel buyPriceLabel = new JLabel(Integer.toString(currentGood.getBuyPrice()));
                    JLabel sellPriceLabel = new JLabel(Integer.toString(currentGood.getSellPrice()));
                    JLabel countLabel = new JLabel(Integer.toString(currentGood.getRemainingCount()));

                    IDLabel.setBounds(goodIDLabel.getX(),
                            goodIDLabel.getY() + goodIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodIDLabel.getWidth(), buttonsHeight);
                    buyPriceLabel.setBounds(goodBuyPriceLabel.getX(),
                            goodBuyPriceLabel.getY() + goodBuyPriceLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodBuyPriceLabel.getWidth(), buttonsHeight);
                    sellPriceLabel.setBounds(goodSellPriceLabel.getX(),
                            goodSellPriceLabel.getY() + goodSellPriceLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodSellPriceLabel.getWidth(), buttonsHeight);
                    countLabel.setBounds(goodCountLabel.getX(),
                            goodCountLabel.getY() + goodCountLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodCountLabel.getWidth(), buttonsHeight);

                    IDLabel.setForeground(new Color(156, 54, 6));
                    buyPriceLabel.setForeground(new Color(156, 54, 6));
                    sellPriceLabel.setForeground(new Color(156, 54, 6));
                    countLabel.setForeground(new Color(156, 54, 6));

                    IDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                    buyPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                    sellPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                    countLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                    IDLabel.setHorizontalAlignment(JLabel.CENTER);
                    buyPriceLabel.setHorizontalAlignment(JLabel.CENTER);
                    sellPriceLabel.setHorizontalAlignment(JLabel.CENTER);
                    countLabel.setHorizontalAlignment(JLabel.CENTER);


                    mainLabel.add(IDLabel);
                    mainLabel.add(buyPriceLabel);
                    mainLabel.add(sellPriceLabel);
                    mainLabel.add(countLabel);


                    mainLabel.add(newButton);
                    if (i == goods.size() - 1) {
                        ArrayList<Component> components = new ArrayList<>();
                        components.add(mainLabel);

                        frame.setComponents("listBackground.jpg", components);
                    }
                }
            }
        }

    private void createAddNameFrame() {
        FrameCreator frameCreator = new FrameCreator("ADD GOOD", 1600, 800, Color.BLACK,
                null, 3, false);
        JLabel label = new JLabel();
        label.setLayout(null);
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCreator.dispose();
                processMenu();
            }
        };
        final int BACK_AND_LEFT_GAP = 15 * frameCreator.getWidth() / 1600;
        final int BUTTON_HEIGHT = 75*frameCreator.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frameCreator.getHeight()/800;
        final int TOP_BUTTONS_WIDTH = 175 * frameCreator.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY()+TOP_AND_BACK_BUTTON_GAP, TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frameCreator.getWidth() / 1600));
        backButton.addActionListener(backListener);

        label.add(backButton);



        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean existsError = false;
                String input = textField.getText();
                for (Good good : adminManager.getGoods()) {
                    if (good.getName().equals(input)){
                        JOptionPane.showMessageDialog(frameCreator,
                                "This good already exists in the market!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                for (Good unavailableGood : adminManager.getUnavailableGoods()) {
                    if (unavailableGood.getName().equals(input)) {
                        JOptionPane.showMessageDialog(frameCreator,
                                "This good already exists in the market!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError){
                    frameCreator.dispose();
                    createAddSPFrame(input);
                }
            }
        };

        createTextFieldFrame(frameCreator,label,"Enter the good name",
                            new Color(156, 54, 6), textField,"Name","SUBMIT",
                            listener,"listBackground.jpg" );
    }

    private void createAddSPFrame(String name) {
        FrameCreator frameCreator = new FrameCreator("ADD GOOD", 1600, 800, Color.BLACK,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCreator.dispose();
                processMenu();
            }
        };
        final int BACK_AND_LEFT_GAP = 15 * frameCreator.getWidth() / 1600;
        final int BUTTON_HEIGHT = 75*frameCreator.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frameCreator.getHeight()/800;
        final int TOP_BUTTONS_WIDTH = 175 * frameCreator.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY()+TOP_AND_BACK_BUTTON_GAP, TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frameCreator.getWidth() / 1600));
        backButton.addActionListener(backListener);

        label.add(backButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError) {
                    if (Integer.parseInt(input) <= 0) {
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                    } else {
                        frameCreator.dispose();
                        createAddBPFrame(name, Integer.parseInt(input));
                    }
                }
            }
        };


        createTextFieldFrame(frameCreator,label,"Enter the good sell price",
                new Color(156, 54, 6), textField,"0","SUBMIT",
                listener,"listBackground.jpg" );
    }

    private void createAddBPFrame(String name, int sellPrice) {
        FrameCreator frameCreator = new FrameCreator("ADD GOOD", 1600, 800, Color.BLACK,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCreator.dispose();
                processMenu();
            }
        };
        final int BACK_AND_LEFT_GAP = 15 * frameCreator.getWidth() / 1600;
        final int BUTTON_HEIGHT = 75*frameCreator.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frameCreator.getHeight()/800;
        final int TOP_BUTTONS_WIDTH = 175 * frameCreator.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY()+TOP_AND_BACK_BUTTON_GAP, TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frameCreator.getWidth() / 1600));
        backButton.addActionListener(backListener);

        label.add(backButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError){
                    if (Integer.parseInt(input) > sellPrice){
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!"+(Integer.parseInt(input)>0?" (Buy price is more than sell price!)":""),
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                    }else{
                        frameCreator.dispose();
                        createAddCountFrame(name, sellPrice, Integer.parseInt(input));
                    }
                }
            }
        };
        createTextFieldFrame(frameCreator,label,"Enter the good buy price",
                new Color(156, 54, 6), textField,"0","SUBMIT",
                listener,"listBackground.jpg" );
    }

    private void createAddCountFrame(String name, int sellPrice, int buyPrice) {
        FrameCreator frameCreator = new FrameCreator("ADD GOOD", 1600, 800, Color.BLACK,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();


        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameCreator.dispose();
                processMenu();
            }
        };
        final int BACK_AND_LEFT_GAP = 15 * frameCreator.getWidth() / 1600;
        final int BUTTON_HEIGHT = 75*frameCreator.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frameCreator.getHeight()/800;
        final int TOP_BUTTONS_WIDTH = 175 * frameCreator.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY()+TOP_AND_BACK_BUTTON_GAP, TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frameCreator.getWidth() / 1600));
        backButton.addActionListener(backListener);

        label.add(backButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError){
                    if (Integer.parseInt(input) <= 0){
                        JOptionPane.showMessageDialog(frameCreator,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                    }else{
                        JOptionPane.showMessageDialog(frameCreator,
                                "Add was successful -> good_ID = "+processAddGood(name, sellPrice, buyPrice, Integer.parseInt(input)),
                                "Add successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
                    }
                }

            }
        };
        createTextFieldFrame(frameCreator,label,"Enter the good count",
                new Color(156, 54, 6), textField,"0","SUBMIT",
                listener,"listBackground.jpg" );
    }

    private void createGoodOptionsFrame(Good currentGood) {
        FrameCreator frame = new FrameCreator(currentGood.getName(), 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Select one");

        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));

        final int BUTTON_WIDTH = 350*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 90*frame.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frame.getHeight()/800;
        final int TOP_AND_BUTTONS_GAP = 200*frame.getHeight()/800;
        final int BUTTONS_GAP = 20*frame.getHeight()/800;

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(frame.getX() + BACK_AND_LEFT_GAP,
                mainLabel.getY()+TOP_AND_BACK_BUTTON_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(backButton.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);




        JButton editGoodButton = new JButton("EDIT GOOD");
        JButton removeGoodButton = new JButton("REMOVE GOOD");

        editGoodButton.setBounds(frame.getFrameCenter().x-BUTTON_WIDTH/2,
                frame.getY()+TOP_AND_BUTTONS_GAP,BUTTON_WIDTH,BUTTON_HEIGHT);
        removeGoodButton.setBounds(frame.getFrameCenter().x-BUTTON_WIDTH/2,
                editGoodButton.getY()+ editGoodButton.getHeight()+TOP_AND_BUTTONS_GAP,BUTTON_WIDTH,BUTTON_HEIGHT);

        editGoodButton.setBackground(Color.BLUE);
        removeGoodButton.setBackground(Color.BLUE);

        editGoodButton.setForeground(Color.YELLOW);
        removeGoodButton.setForeground(Color.YELLOW);

        editGoodButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        removeGoodButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        editGoodButton.setFocusable(false);
        removeGoodButton.setFocusable(false);

        editGoodButton.setFont(new Font("MV Boli", Font.PLAIN, 30*frame.getWidth()/1600));
        removeGoodButton.setFont(new Font("MV Boli", Font.PLAIN, 30*frame.getWidth()/1600));

        ActionListener editListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createEditFrame(currentGood);
            }
        };
        ActionListener removeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminManager.removeGood(currentGood);
//                boolean foundOrderToCancel = false;
//                for (Order order : customerManager.getOrders()) {
//                    if (order.orderID.equals(customerManager.getUser().getUserName())
//                            && order.goodID==currentGood.getID()) {
//                        foundOrderToCancel = true;
//                    }
//                }
//                if (!foundOrderToCancel) {
//                    JOptionPane.showMessageDialog(new JLabel(),
//                            "No other order to cancel!",
//                            "Cancel not successful", JOptionPane.ERROR_MESSAGE,
//                            new ImageIcon("error.png"));
//                }else {
                JOptionPane.showMessageDialog(mainLabel,
                        "Good deleted successfully!",
                        "Delete successful", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("done.png"));
//                }
            }
        };

        editGoodButton.addActionListener(editListener);
        removeGoodButton.addActionListener(removeListener);

//        boolean existsThisOrder = false;
//        for (Order order : customerManager.getOrders()) {
//            if (order.orderID.equals(customerManager.getUser().getUserName()) && (order.goodID == currentGood.getID())){
//                existsThisOrder = true;
//                break;
//            }
//        }
//        if(!existsThisOrder){
//            removeGoodButton.setEnabled(false);
//        }

        mainLabel.add(backButton);
        mainLabel.add(editGoodButton);
        mainLabel.add(removeGoodButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);
        frame.setComponents("listBackground.jpg", components);
    }

    private void createEditFrame(Good selectedGood) {
        FrameCreator menuFrame = new FrameCreator("EDIT", 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Which one do you want to edit?");

        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        final int TEXT_HEIGHT = 50;
        mainLabel.setBounds(menuFrame.getX(), menuFrame.getY(), menuFrame.getWidth(), menuFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 30*menuFrame.getWidth()/1600));


        final int BUTTON_WIDTH = 350*menuFrame.getWidth()/1600;;
        final int BUTTON_HEIGHT = 90*menuFrame.getHeight()/800;;
        final int TOP_AND_BUTTONS_GAP = 200*menuFrame.getHeight()/800;;
        final int BUTTONS_GAP = 20*menuFrame.getHeight()/800;;
        final int EXIT_AND_BOTTOM_GAP = 50*menuFrame.getHeight()/800;;

        JButton editNameButton = new JButton("NAME");
        JButton editBPButton = new JButton("BUY PRICE");
        JButton editSPButton = new JButton("SELL PRICE");
        JButton editCountButton = new JButton("COUNT");
        JButton exitButton = new JButton("EXIT");

        editNameButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        editBPButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                editNameButton.getY() + editNameButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        editSPButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                editBPButton.getY() + editBPButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        editCountButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                editSPButton.getY() + editSPButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        exitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + menuFrame.getHeight() - BUTTON_HEIGHT - EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        editNameButton.setBackground(Color.BLUE);
        editBPButton.setBackground(Color.BLUE);
        editSPButton.setBackground(Color.BLUE);
        editCountButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        editNameButton.setForeground(Color.YELLOW);
        editBPButton.setForeground(Color.YELLOW);
        editSPButton.setForeground(Color.YELLOW);
        editCountButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        editNameButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        editBPButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        editSPButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        editCountButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        editNameButton.setFocusable(false);
        editBPButton.setFocusable(false);
        editSPButton.setFocusable(false);
        editCountButton.setFocusable(false);
        exitButton.setFocusable(false);

        editNameButton.setVerticalAlignment(mainLabel.CENTER);
        editBPButton.setVerticalAlignment(mainLabel.CENTER);
        editSPButton.setVerticalAlignment(mainLabel.CENTER);
        editCountButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        editNameButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        editBPButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        editSPButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        editCountButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));

        ActionListener editNameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                createEditNameFrame(selectedGood);
            }
        };
        ActionListener editBPListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                createEditBPFrame(selectedGood);
            }
        };
        ActionListener editSPListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                createEditSPFrame(selectedGood);
            }
        };
        ActionListener editCountListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                createEditCountFrame(selectedGood);
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        editNameButton.addActionListener(editNameListener);
        editBPButton.addActionListener(editBPListener);
        editSPButton.addActionListener(editSPListener);
        editCountButton.addActionListener(editCountListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(editNameButton);
        mainLabel.add(editBPButton);
        mainLabel.add(editSPButton);
        mainLabel.add(editCountButton);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        menuFrame.setComponents("background.jpg", components);
    }

    private void createEditCountFrame(Good selectedGood) {
        FrameCreator frame = new FrameCreator("EDIT", 1600, 800, Color.black,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int NEXT_AND_RIGHT_GAP = 90*frame.getWidth()/1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 90*frame.getHeight()/800;

        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY(), TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);

        label.add(backButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                        JOptionPane.showMessageDialog(label,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError) {
                    adminManager.editCount(selectedGood, Integer.parseInt(input));
                    JOptionPane.showMessageDialog(label,
                            "Good count edited successfully!",
                            "Edit successful", JOptionPane.INFORMATION_MESSAGE,
                            new ImageIcon("done.png"));
                }
            }
        };
        createTextFieldFrame(frame, label, "Enter the new count", new Color(156, 54, 6), textField,
                "0", "SUBMIT", listener, "listBackground.jpg");
    }

    private void createEditSPFrame(Good selectedGood) {
        FrameCreator frame = new FrameCreator("EDIT", 1600, 800, Color.black,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int NEXT_AND_RIGHT_GAP = 90*frame.getWidth()/1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 90*frame.getHeight()/800;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY(), TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);

        label.add(backButton);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                        JOptionPane.showMessageDialog(label,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError){
                    if (Integer.parseInt(input) < selectedGood.getBuyPrice()){
                        JOptionPane.showMessageDialog(label,
                                "Invalid input!"+(Integer.parseInt(input)>0?" (Buy price is more than sell price!)":""),
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                    }else{
                        adminManager.editSellPrice(selectedGood, Integer.parseInt(input));
                        JOptionPane.showMessageDialog(label,
                                "Good sell price edited successfully!",
                                "Edit successful", JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("done.png"));
                    }
                }
            }
        };
        createTextFieldFrame(frame, label, "Enter the new sell price", new Color(156, 54, 6), textField,
                "0", "SUBMIT", listener, "listBackground.jpg");
    }

    private void createEditBPFrame(Good selectedGood) {
        FrameCreator frame = new FrameCreator("EDIT", 1600, 800, Color.black,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int NEXT_AND_RIGHT_GAP = 90*frame.getWidth()/1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 90*frame.getHeight()/800;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY(), TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);

        label.add(backButton);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean existsError = false;
                String input = textField.getText();
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) < '0' || input.charAt(i) > '9'){
                        JOptionPane.showMessageDialog(label,
                                "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                        break;
                    }
                }
                if (!existsError){
                    if (Integer.parseInt(input) > selectedGood.getSellPrice()){
                        JOptionPane.showMessageDialog(label,
                                "Invalid input!"+(Integer.parseInt(input)>0?" (Buy price is more than sell price!)":""),
                                "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                        existsError = true;
                    }else{
                        adminManager.editBuyPrice(selectedGood, Integer.parseInt(input));
                        JOptionPane.showMessageDialog(label,
                                "Good buy price edited successfully!",
                                "Edit successful", JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon("done.png"));
                    }
                }
            }
        };
        createTextFieldFrame(frame, label, "Enter the new buy price", new Color(156, 54, 6), textField,
                "0", "SUBMIT", listener, "listBackground.jpg");

    }

    private void createEditNameFrame(Good selectedGood) {
        FrameCreator frame = new FrameCreator("EDIT", 1600, 800, Color.black,
                null, 3, false);
        JLabel label = new JLabel();
        JTextField textField = new JTextField();

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int NEXT_AND_RIGHT_GAP = 90*frame.getWidth()/1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 90*frame.getHeight()/800;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(label.getX() + BACK_AND_LEFT_GAP,
                label.getY(), TOP_BUTTONS_WIDTH, BUTTON_HEIGHT);
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(label.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);

        label.add(backButton);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminManager.editName(selectedGood, textField.getText());
                JOptionPane.showMessageDialog(label,
                        "Good name edited successfully!",
                        "Edit successful", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("done.png"));
            }
        };
        createTextFieldFrame(frame, label, "Enter the new name", new Color(156, 54, 6), textField,
                "Name", "SUBMIT", listener, "listBackground.jpg");
    }

    private void processShowOrders(int startingIndex, int pageNum){
        ArrayList<Order> orders = adminManager.getOrders();
        String frameTitle = ("ORDERS");
        FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                null, 3, false);

        JLabel mainLabel = new JLabel("UNCHECKED ORDERS (PAGE "+pageNum+")");
        JLabel topLabel = new JLabel("Click on orders to check them out");

        mainLabel.setPreferredSize(new Dimension(1600, 800));


        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));

        topLabel.setForeground(new Color(156, 54, 6));
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setLayout(null);
        final int MONEY_LABEL_HEIGHT = 50 * frame.getHeight() / 800;
        final int MONEY_LABEL_AND_TOP_GAP = 100 * frame.getHeight() / 800;
        topLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
        topLabel.setFont(new Font("MV Boli", Font.BOLD, 30 * frame.getWidth() / 1600));


        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };

        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15 * frame.getWidth() / 1600;
        final int NEXT_AND_RIGHT_GAP = 90 * frame.getWidth() / 1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175 * frame.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(topLabel.getX() + BACK_AND_LEFT_GAP,
                topLabel.getY(), TOP_BUTTONS_WIDTH, topLabel.getHeight());
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(topLabel.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        backButton.addActionListener(backListener);

        JButton nextButton = new JButton("NEXT");
        JButton prevButton = new JButton("PREVIOUS");


        nextButton.setBounds(frame.getX() + frame.getWidth() - TOP_BUTTONS_WIDTH - NEXT_AND_RIGHT_GAP,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP,
                TOP_BUTTONS_WIDTH, topLabel.getHeight());
        prevButton.setBounds(nextButton.getX() - NEXT_AND_PREV_GAP - TOP_BUTTONS_WIDTH,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP, TOP_BUTTONS_WIDTH,
                topLabel.getHeight());

        nextButton.setBackground(Color.BLUE);
        prevButton.setBackground(Color.BLUE);

        nextButton.setForeground(Color.YELLOW);
        prevButton.setForeground(Color.YELLOW);

        nextButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        prevButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

        nextButton.setFocusable(false);
        prevButton.setFocusable(false);

        nextButton.setVerticalAlignment(topLabel.CENTER);
        prevButton.setVerticalAlignment(topLabel.CENTER);


        final int TEXTS_AND_TABLE_GAP = 15 * frame.getHeight() / 800;
        final int ROWS_GAP = 10 * frame.getHeight() / 800;
        int lastUsedY = frame.getY() + (topLabel.getY() + topLabel.getHeight());
        int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
        final int buttonsHeight = 55 * frame.getHeight() / 800;/*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
        final int BUTTONS_PER_PAGE = remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 > 0 ? remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 : 0;

        final int TOTAL_FREE_WIDTH = frame.getWidth() - 150;
        final int BUTTONS_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
        final int COL1_WIDTH = BUTTONS_WIDTH;
        final int COL2_WIDTH = (300 * TOTAL_FREE_WIDTH / 1450);
        final int COL3_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
        final int COL4_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
        final int COL5_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);

        if (startingIndex == 0) {
            prevButton.setEnabled(false);
        } else {
            ActionListener prevListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    processShowAllGoods(startingIndex - BUTTONS_PER_PAGE, pageNum - 1);
                }
            };
            prevButton.addActionListener(prevListener);
        }

        nextButton.setEnabled(false);

        JLabel goodNameLabel = new JLabel("GOOD NAME");
        JLabel orderIDLabel = new JLabel("ORDER ID");
        JLabel orderDateLabel = new JLabel("ORDER DATE");
        JLabel goodIDLabel = new JLabel("GOOD ID");
        JLabel goodCountLabel = new JLabel("GOOD COUNT");

        goodNameLabel.setBounds(frame.getX(), topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL1_WIDTH, buttonsHeight);
        orderIDLabel.setBounds(goodNameLabel.getX() + goodNameLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL2_WIDTH, buttonsHeight);
        orderDateLabel.setBounds(orderIDLabel.getX() + orderIDLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL3_WIDTH, buttonsHeight);
        goodIDLabel.setBounds(orderDateLabel.getX() + orderDateLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL4_WIDTH, buttonsHeight);
        goodCountLabel.setBounds(goodIDLabel.getX() + goodIDLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL5_WIDTH, buttonsHeight);

        goodNameLabel.setForeground(new Color(156, 54, 6));
        orderIDLabel.setForeground(new Color(156, 54, 6));
        orderDateLabel.setForeground(new Color(156, 54, 6));
        goodIDLabel.setForeground(new Color(156, 54, 6));
        goodCountLabel.setForeground(new Color(156, 54, 6));

        goodNameLabel.setVerticalAlignment(JLabel.CENTER);
        goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

        orderIDLabel.setVerticalAlignment(JLabel.CENTER);
        orderIDLabel.setHorizontalAlignment(JLabel.CENTER);

        orderDateLabel.setVerticalAlignment(JLabel.CENTER);
        orderDateLabel.setHorizontalAlignment(JLabel.CENTER);

        goodIDLabel.setVerticalAlignment(JLabel.CENTER);
        goodIDLabel.setHorizontalAlignment(JLabel.CENTER);

        goodCountLabel.setVerticalAlignment(JLabel.CENTER);
        goodCountLabel.setHorizontalAlignment(JLabel.CENTER);


        goodNameLabel.setLayout(null);
        orderIDLabel.setLayout(null);
        orderDateLabel.setLayout(null);
        goodIDLabel.setLayout(null);
        goodCountLabel.setLayout(null);

        goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        orderIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        orderDateLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodCountLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));

        mainLabel.add(backButton);
        mainLabel.add(nextButton);
        mainLabel.add(prevButton);
        mainLabel.add(topLabel);
        mainLabel.add(goodNameLabel);
        mainLabel.add(orderIDLabel);
        mainLabel.add(orderDateLabel);
        mainLabel.add(goodIDLabel);
        mainLabel.add(goodCountLabel);

        if (orders.size() == 0){
            ArrayList<Component> components = new ArrayList<>();
            components.add(mainLabel);

            frame.setComponents("listBackground.jpg", components);
            return;
        }

        int i;
        for (i = startingIndex; i < orders.size(); i++) {
            if (i - startingIndex + 1 > BUTTONS_PER_PAGE) {
                int nextStartingIndex = i;
                nextButton.setEnabled(true);
                ActionListener nextListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == nextButton) {
                            frame.dispose();
                            processShowOrders(nextStartingIndex, pageNum + 1);
                        }
                    }
                };
                nextButton.addActionListener(nextListener);
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
            } else {
                Order currentOrder = orders.get(i);
                JButton newButton = new JButton(adminManager.getGoodByID(currentOrder.goodID).getName());
                newButton.setBounds(goodNameLabel.getX(),
                        goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        BUTTONS_WIDTH, buttonsHeight);
                newButton.setBackground(Color.BLUE);
                newButton.setForeground(Color.YELLOW);
                newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                newButton.setFocusable(false);
                newButton.setVerticalAlignment(newButton.CENTER);
                newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                if(adminManager.getGoodByID(currentOrder.goodID).getRemainingCount() < currentOrder.goodCount){
                    newButton.setEnabled(false);
                }

                ActionListener newListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adminManager.checkoutOrder(currentOrder);
                        JOptionPane.showMessageDialog(frame,
                                "Order checked out successfully!",
                                "CHECKOUT SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));
                    }
                };

                newButton.addActionListener(newListener);

                JLabel OIDLabel = new JLabel(currentOrder.orderID);
                JLabel dateLabel = new JLabel(currentOrder.Date);
                JLabel GIDLabel = new JLabel(Integer.toString(currentOrder.goodID));
                JLabel countLabel = new JLabel(Integer.toString(currentOrder.goodCount));

                OIDLabel.setBounds(orderIDLabel.getX(),
                        orderIDLabel.getY() + orderIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        orderIDLabel.getWidth(), buttonsHeight);
                dateLabel.setBounds(orderDateLabel.getX(),
                        orderDateLabel.getY() + orderDateLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        orderDateLabel.getWidth(), buttonsHeight);
                GIDLabel.setBounds(goodIDLabel.getX(),
                        goodIDLabel.getY() + goodIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodIDLabel.getWidth(), buttonsHeight);
                countLabel.setBounds(goodCountLabel.getX(),
                        goodCountLabel.getY() + goodCountLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodCountLabel.getWidth(), buttonsHeight);

                OIDLabel.setForeground(new Color(156, 54, 6));
                dateLabel.setForeground(new Color(156, 54, 6));
                GIDLabel.setForeground(new Color(156, 54, 6));
                countLabel.setForeground(new Color(156, 54, 6));

                OIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                dateLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                GIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                countLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                OIDLabel.setHorizontalAlignment(JLabel.CENTER);
                dateLabel.setHorizontalAlignment(JLabel.CENTER);
                GIDLabel.setHorizontalAlignment(JLabel.CENTER);
                countLabel.setHorizontalAlignment(JLabel.CENTER);


                mainLabel.add(OIDLabel);
                mainLabel.add(dateLabel);
                mainLabel.add(GIDLabel);
                mainLabel.add(countLabel);


                mainLabel.add(newButton);
                if (i == orders.size() - 1) {
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                }
            }
        }
    }

    private void processShowHistory(int startingIndex, int pageNum){
        ArrayList<Order> orders = adminManager.getCheckedOrders();
        String frameTitle = ("CHECKED ORDERS");
        FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                null, 3, false);

        JLabel mainLabel = new JLabel("LAST 10 CHECKED ORDERS (PAGE "+pageNum+")");
        JLabel topLabel = new JLabel();

        mainLabel.setPreferredSize(new Dimension(1600, 800));


        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));

        topLabel.setForeground(new Color(156, 54, 6));
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setLayout(null);
        final int MONEY_LABEL_HEIGHT = 50 * frame.getHeight() / 800;
        final int MONEY_LABEL_AND_TOP_GAP = 100 * frame.getHeight() / 800;
        topLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
        topLabel.setFont(new Font("MV Boli", Font.BOLD, 30 * frame.getWidth() / 1600));


        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };

        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15 * frame.getWidth() / 1600;
        final int NEXT_AND_RIGHT_GAP = 90 * frame.getWidth() / 1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175 * frame.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(topLabel.getX() + BACK_AND_LEFT_GAP,
                topLabel.getY(), TOP_BUTTONS_WIDTH, topLabel.getHeight());
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(topLabel.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        backButton.addActionListener(backListener);


        JButton nextButton = new JButton("NEXT");
        JButton prevButton = new JButton("PREVIOUS");


        nextButton.setBounds(frame.getX() + frame.getWidth() - TOP_BUTTONS_WIDTH - NEXT_AND_RIGHT_GAP,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP,
                TOP_BUTTONS_WIDTH, topLabel.getHeight());
        prevButton.setBounds(nextButton.getX() - NEXT_AND_PREV_GAP - TOP_BUTTONS_WIDTH,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP, TOP_BUTTONS_WIDTH,
                topLabel.getHeight());

        nextButton.setBackground(Color.BLUE);
        prevButton.setBackground(Color.BLUE);

        nextButton.setForeground(Color.YELLOW);
        prevButton.setForeground(Color.YELLOW);

        nextButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        prevButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

        nextButton.setFocusable(false);
        prevButton.setFocusable(false);

        nextButton.setVerticalAlignment(topLabel.CENTER);
        prevButton.setVerticalAlignment(topLabel.CENTER);


        final int TEXTS_AND_TABLE_GAP = 15 * frame.getHeight() / 800;
        final int ROWS_GAP = 10 * frame.getHeight() / 800;
        int lastUsedY = frame.getY() + (topLabel.getY() + topLabel.getHeight());
        int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
        final int buttonsHeight = 55 * frame.getHeight() / 800;/*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
        final int BUTTONS_PER_PAGE = remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 > 0 ? remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 : 0;

        final int TOTAL_FREE_WIDTH = frame.getWidth() - 150;
        final int BUTTONS_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
        final int COL1_WIDTH = BUTTONS_WIDTH;
        final int COL2_WIDTH = (300 * TOTAL_FREE_WIDTH / 1450);
        final int COL3_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
        final int COL4_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
        final int COL5_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);

        if (startingIndex == 0) {
            prevButton.setEnabled(false);
        } else {
            ActionListener prevListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    processShowAllGoods(startingIndex - BUTTONS_PER_PAGE, pageNum - 1);
                }
            };
            prevButton.addActionListener(prevListener);
        }

        nextButton.setEnabled(false);

        JLabel goodNameLabel = new JLabel("GOOD NAME");
        JLabel orderIDLabel = new JLabel("ORDER ID");
        JLabel orderDateLabel = new JLabel("ORDER DATE");
        JLabel goodIDLabel = new JLabel("GOOD ID");
        JLabel goodCountLabel = new JLabel("GOOD COUNT");

        goodNameLabel.setBounds(frame.getX(), topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL1_WIDTH, buttonsHeight);
        orderIDLabel.setBounds(goodNameLabel.getX() + goodNameLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL2_WIDTH, buttonsHeight);
        orderDateLabel.setBounds(orderIDLabel.getX() + orderIDLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL3_WIDTH, buttonsHeight);
        goodIDLabel.setBounds(orderDateLabel.getX() + orderDateLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL4_WIDTH, buttonsHeight);
        goodCountLabel.setBounds(goodIDLabel.getX() + goodIDLabel.getWidth(),
                topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL5_WIDTH, buttonsHeight);

        goodNameLabel.setForeground(new Color(156, 54, 6));
        orderIDLabel.setForeground(new Color(156, 54, 6));
        orderDateLabel.setForeground(new Color(156, 54, 6));
        goodIDLabel.setForeground(new Color(156, 54, 6));
        goodCountLabel.setForeground(new Color(156, 54, 6));

        goodNameLabel.setVerticalAlignment(JLabel.CENTER);
        goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

        orderIDLabel.setVerticalAlignment(JLabel.CENTER);
        orderIDLabel.setHorizontalAlignment(JLabel.CENTER);

        orderDateLabel.setVerticalAlignment(JLabel.CENTER);
        orderDateLabel.setHorizontalAlignment(JLabel.CENTER);

        goodIDLabel.setVerticalAlignment(JLabel.CENTER);
        goodIDLabel.setHorizontalAlignment(JLabel.CENTER);

        goodCountLabel.setVerticalAlignment(JLabel.CENTER);
        goodCountLabel.setHorizontalAlignment(JLabel.CENTER);


        goodNameLabel.setLayout(null);
        orderIDLabel.setLayout(null);
        orderDateLabel.setLayout(null);
        goodIDLabel.setLayout(null);
        goodCountLabel.setLayout(null);

        goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        orderIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        orderDateLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodCountLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));

        mainLabel.add(backButton);
        mainLabel.add(nextButton);
        mainLabel.add(prevButton);
        mainLabel.add(topLabel);
        mainLabel.add(goodNameLabel);
        mainLabel.add(orderIDLabel);
        mainLabel.add(orderDateLabel);
        mainLabel.add(goodIDLabel);
        mainLabel.add(goodCountLabel);

        if (orders.size() == 0){
            ArrayList<Component> components = new ArrayList<>();
            components.add(mainLabel);

            frame.setComponents("listBackground.jpg", components);
            return;
        }

        int ordersToShowCount = ((orders.size() < 10)?orders.size():10);

        int i;
        for (i = ordersToShowCount-1; i >= startingIndex; i--) {
            if (i - startingIndex + 1 > BUTTONS_PER_PAGE) {
                int nextStartingIndex = i;
                nextButton.setEnabled(true);
                ActionListener nextListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == nextButton) {
                            frame.dispose();
                            processShowOrders(nextStartingIndex, pageNum + 1);
                        }
                    }
                };
                nextButton.addActionListener(nextListener);
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
            } else {
                Order currentOrder = orders.get(i);
                JButton newButton = new JButton(adminManager.getGoodByID(currentOrder.goodID).getName());
                newButton.setBounds(goodNameLabel.getX(),
                        goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        BUTTONS_WIDTH, buttonsHeight);
                newButton.setBackground(Color.BLUE);
                newButton.setForeground(Color.YELLOW);
                newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                newButton.setFocusable(false);
                newButton.setVerticalAlignment(newButton.CENTER);
                newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                newButton.setEnabled(false);

                JLabel OIDLabel = new JLabel(currentOrder.orderID);
                JLabel dateLabel = new JLabel(currentOrder.Date);
                JLabel GIDLabel = new JLabel(Integer.toString(currentOrder.goodID));
                JLabel countLabel = new JLabel(Integer.toString(currentOrder.goodCount));

                OIDLabel.setBounds(orderIDLabel.getX(),
                        orderIDLabel.getY() + orderIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        orderIDLabel.getWidth(), buttonsHeight);
                dateLabel.setBounds(orderDateLabel.getX(),
                        orderDateLabel.getY() + orderDateLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        orderDateLabel.getWidth(), buttonsHeight);
                GIDLabel.setBounds(goodIDLabel.getX(),
                        goodIDLabel.getY() + goodIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodIDLabel.getWidth(), buttonsHeight);
                countLabel.setBounds(goodCountLabel.getX(),
                        goodCountLabel.getY() + goodCountLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodCountLabel.getWidth(), buttonsHeight);

                OIDLabel.setForeground(new Color(156, 54, 6));
                dateLabel.setForeground(new Color(156, 54, 6));
                GIDLabel.setForeground(new Color(156, 54, 6));
                countLabel.setForeground(new Color(156, 54, 6));

                OIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                dateLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                GIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                countLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                OIDLabel.setHorizontalAlignment(JLabel.CENTER);
                dateLabel.setHorizontalAlignment(JLabel.CENTER);
                GIDLabel.setHorizontalAlignment(JLabel.CENTER);
                countLabel.setHorizontalAlignment(JLabel.CENTER);


                mainLabel.add(OIDLabel);
                mainLabel.add(dateLabel);
                mainLabel.add(GIDLabel);
                mainLabel.add(countLabel);


                mainLabel.add(newButton);
                if (i == orders.size() - 1) {
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                }
            }
        }
    }

    private void processShowProfit(int startingIndex, int pageNum) {

        ArrayList<Good> goods = new ArrayList<>();

        for (Good good : adminManager.getGoods()) {
            goods.add(good);
        }
        for (Good good : adminManager.getUnavailableGoods()) {
            goods.add(good);
        }

            String frameTitle = ("SALE STATUS");
            FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                    null, 3, false);

            String labelText = ("Sale status");
            JLabel mainLabel = new JLabel(labelText);
            JLabel topLabel = new JLabel();

            mainLabel.setPreferredSize(new Dimension(1600, 800));


            mainLabel.setForeground(new Color(156, 54, 6));
            mainLabel.setVerticalAlignment(JLabel.TOP);
            mainLabel.setHorizontalAlignment(JLabel.CENTER);
            mainLabel.setLayout(null);
            mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
            mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));

            topLabel.setForeground(new Color(156, 54, 6));
            topLabel.setVerticalAlignment(JLabel.TOP);
            topLabel.setHorizontalAlignment(JLabel.CENTER);
            topLabel.setLayout(null);
            final int MONEY_LABEL_HEIGHT = 50 * frame.getHeight() / 800;
//        System.out.println("MONEY_LABEL_HEIGHT= "+MONEY_LABEL_HEIGHT);
            final int MONEY_LABEL_AND_TOP_GAP = 100 * frame.getHeight() / 800;
            topLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
            topLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));


            ActionListener backListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    processMenu();
                }
            };
            final int TOP_AND_BUTTONS_GAP = 20;
            final int BACK_AND_LEFT_GAP = 15 * frame.getWidth() / 1600;
            final int NEXT_AND_RIGHT_GAP = 90 * frame.getWidth() / 1600;
            final int NEXT_AND_PREV_GAP = 5;
            final int TOP_BUTTONS_WIDTH = 175 * frame.getWidth() / 1600;
            JButton backButton = new JButton("MAIN MENU");
            backButton.setBounds(topLabel.getX() + BACK_AND_LEFT_GAP,
                    topLabel.getY(), TOP_BUTTONS_WIDTH, topLabel.getHeight());
            backButton.setBackground(Color.BLUE);
            backButton.setForeground(Color.YELLOW);
            backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
            backButton.setFocusable(false);
            backButton.setVerticalAlignment(topLabel.CENTER);
            backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
            backButton.addActionListener(backListener);

            JButton nextButton = new JButton("NEXT");
            JButton prevButton = new JButton("PREVIOUS");


            nextButton.setBounds(frame.getX() + frame.getWidth() - TOP_BUTTONS_WIDTH - NEXT_AND_RIGHT_GAP,
                    mainLabel.getY() + TOP_AND_BUTTONS_GAP,
                    TOP_BUTTONS_WIDTH, topLabel.getHeight());
            prevButton.setBounds(nextButton.getX() - NEXT_AND_PREV_GAP - TOP_BUTTONS_WIDTH,
                    mainLabel.getY() + TOP_AND_BUTTONS_GAP, TOP_BUTTONS_WIDTH,
                    topLabel.getHeight());

            nextButton.setBackground(Color.BLUE);
            prevButton.setBackground(Color.BLUE);

            nextButton.setForeground(Color.YELLOW);
            prevButton.setForeground(Color.YELLOW);

            nextButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
            prevButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

            nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
            prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

            nextButton.setFocusable(false);
            prevButton.setFocusable(false);

            nextButton.setVerticalAlignment(topLabel.CENTER);
            prevButton.setVerticalAlignment(topLabel.CENTER);


            final int TEXTS_AND_TABLE_GAP = 15 * frame.getHeight() / 800;
            final int ROWS_GAP = 10 * frame.getHeight() / 800;
            int lastUsedY = frame.getY() + (topLabel.getY() + topLabel.getHeight());
            int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
            final int buttonsHeight = 55 /*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
//        System.out.println(remainedFreeY/(buttonsHeight+ROWS_GAP)-3);
            final int BUTTONS_PER_PAGE = remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 > 0 ? remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 : 0;

            final int TOTAL_FREE_WIDTH = frame.getWidth() - 150;
            final int BUTTONS_WIDTH = (350 * TOTAL_FREE_WIDTH / 1450);
            final int COL1_WIDTH = BUTTONS_WIDTH;
            final int COL2_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
            final int COL3_WIDTH = (600 * TOTAL_FREE_WIDTH / 1450);

//        System.out.println("BUTTONS_PER_PAGE= "+BUTTONS_PER_PAGE);

            if (startingIndex == 0) {
                prevButton.setEnabled(false);
            } else {
                ActionListener prevListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        processShowProfit(startingIndex - BUTTONS_PER_PAGE, pageNum - 1);
                        frame.dispose();
                    }
                };
                prevButton.addActionListener(prevListener);
            }
            nextButton.setEnabled(false);

//        for (int i = 0; i < 4; i++) {
//            System.out.println("col "+i+" width: "+ (i==1?COL1_WIDTH:i==2?COL2_WIDTH:i==3?COL3_WIDTH:COL4_WIDTH));
//        }


            JLabel goodNameLabel = new JLabel("GOOD NAME");
            JLabel goodSalesLabel = new JLabel("GOOD SALES(COUNT)");
            JLabel goodProfitLabel = new JLabel("GOOD PROFIT");

            goodNameLabel.setBounds(frame.getX(), topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL1_WIDTH, buttonsHeight);
            goodSalesLabel.setBounds(goodNameLabel.getX() + goodNameLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL2_WIDTH, buttonsHeight);
            goodProfitLabel.setBounds(goodSalesLabel.getX() + goodSalesLabel.getWidth(),
                    topLabel.getY() + topLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                    COL3_WIDTH, buttonsHeight);

            goodNameLabel.setForeground(new Color(156, 54, 6));
            goodSalesLabel.setForeground(new Color(156, 54, 6));
            goodProfitLabel.setForeground(new Color(156, 54, 6));

            goodNameLabel.setVerticalAlignment(JLabel.CENTER);
            goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

            goodSalesLabel.setVerticalAlignment(JLabel.CENTER);
            goodSalesLabel.setHorizontalAlignment(JLabel.CENTER);

            goodProfitLabel.setVerticalAlignment(JLabel.CENTER);
            goodProfitLabel.setHorizontalAlignment(JLabel.CENTER);


            goodNameLabel.setLayout(null);
            goodSalesLabel.setLayout(null);
            goodProfitLabel.setLayout(null);

            goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodSalesLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
            goodProfitLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));

            mainLabel.add(backButton);
            mainLabel.add(nextButton);
            mainLabel.add(prevButton);
            mainLabel.add(topLabel);
            mainLabel.add(goodNameLabel);
            mainLabel.add(goodSalesLabel);
            mainLabel.add(goodProfitLabel);

            int i;
            if(goods.size() == 0){
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
            }
            for (i = startingIndex; i < goods.size(); i++) {
                if (i - startingIndex + 1 > BUTTONS_PER_PAGE) {
//                System.out.println("i= " + i);
                    int nextStartingIndex = i;
                    nextButton.setEnabled(true);
                    ActionListener nextListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == nextButton) {
                                frame.dispose();
                                processShowProfit(nextStartingIndex, pageNum + 1);
                            }
                        }
                    };
                    nextButton.addActionListener(nextListener);
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                    return;
                } else {
                    Good currentGood = goods.get(i);
                    JButton newButton = new JButton(currentGood.getName());
                    newButton.setBounds(goodNameLabel.getX(),
                            goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            BUTTONS_WIDTH, buttonsHeight);
                    newButton.setBackground(Color.BLUE);
                    newButton.setForeground(Color.YELLOW);
                    newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                    newButton.setFocusable(false);
                    newButton.setVerticalAlignment(newButton.CENTER);
                    newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                    newButton.setEnabled(false);

                    JLabel salesLabel = new JLabel(Long.toString(currentGood.getSoldPrice()) +
                            "(" + Integer.toString(currentGood.getSoldCount()) + ")");
                    JLabel profitLabel = new JLabel(Long.toString(adminManager.goodProfitCal(currentGood.getID())));

                    salesLabel.setBounds(goodSalesLabel.getX(),
                            goodSalesLabel.getY() + goodSalesLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodSalesLabel.getWidth(), buttonsHeight);
                    profitLabel.setBounds(goodProfitLabel.getX(),
                            goodProfitLabel.getY() + goodProfitLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                            goodProfitLabel.getWidth(), buttonsHeight);

                    salesLabel.setForeground(new Color(156, 54, 6));
                    profitLabel.setForeground(new Color(156, 54, 6));

                    salesLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                    profitLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                    salesLabel.setHorizontalAlignment(JLabel.CENTER);
                    profitLabel.setHorizontalAlignment(JLabel.CENTER);


                    mainLabel.add(salesLabel);
                    mainLabel.add(profitLabel);


                    mainLabel.add(newButton);
                    if (i == goods.size() - 1) {
                        JLabel totalLabel = new JLabel("TOTAL");
                        JLabel totalSaleLabel = new JLabel(Long.toString(adminManager.getTotalSalePrice()) +
                                "(" + Integer.toString(adminManager.getTotalSalesCount()) + ")");
                        JLabel totalProfitLabel = new JLabel(Long.toString(adminManager.getTotalProfit()));

                        totalLabel.setBounds(goodNameLabel.getX(),
                                goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 2 - startingIndex) * (buttonsHeight + ROWS_GAP),
                                goodNameLabel.getWidth(),buttonsHeight);
                        totalSaleLabel.setBounds(goodSalesLabel.getX(),
                                goodSalesLabel.getY() + goodSalesLabel.getHeight() + (i + 2 - startingIndex) * (buttonsHeight + ROWS_GAP),
                                goodSalesLabel.getWidth(), buttonsHeight);
                        totalProfitLabel.setBounds(goodProfitLabel.getX(),
                                goodProfitLabel.getY() + goodProfitLabel.getHeight() + (i + 2 - startingIndex) * (buttonsHeight + ROWS_GAP),
                                goodProfitLabel.getWidth(), buttonsHeight);

                        totalLabel.setForeground(new Color(156, 54, 6));
                        totalSaleLabel.setForeground(new Color(156, 54, 6));
                        totalProfitLabel.setForeground(new Color(156, 54, 6));

                        totalLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                        totalSaleLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                        totalProfitLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                        totalLabel.setHorizontalAlignment(JLabel.CENTER);
                        totalSaleLabel.setHorizontalAlignment(JLabel.CENTER);
                        totalProfitLabel.setHorizontalAlignment(JLabel.CENTER);

                        mainLabel.add(totalLabel);
                        mainLabel.add(totalSaleLabel);
                        mainLabel.add(totalProfitLabel);



                        ArrayList<Component> components = new ArrayList<>();
                        components.add(mainLabel);

                        frame.setComponents("listBackground.jpg", components);
                    }
                }
            }
        }

    private void processShowGoods(ArrayList<Good> goods, boolean areAvailable, int startingIndex, int pageNum) {

        String frameTitle = (areAvailable ? "AVAILABLE GOODS" : "UNAVAILABLE GOODS");
        FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                null, 3, false);

        String labelText = (areAvailable ? "Available goods list (page " + pageNum + "):" : "Unavailable goods list (page " + pageNum + "):");
        JLabel mainLabel = new JLabel(labelText);
        JLabel moneyLabel = new JLabel();

        mainLabel.setPreferredSize(new Dimension(1600, 800));


        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));

        moneyLabel.setForeground(new Color(156, 54, 6));
        moneyLabel.setVerticalAlignment(JLabel.TOP);
        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setLayout(null);
        final int MONEY_LABEL_HEIGHT = 50 * frame.getHeight() / 800;
        final int MONEY_LABEL_AND_TOP_GAP = 100 * frame.getHeight() / 800;
        moneyLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
        moneyLabel.setFont(new Font("MV Boli", Font.BOLD, 35 * frame.getWidth() / 1600));


        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu();
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15 * frame.getWidth() / 1600;
        final int NEXT_AND_RIGHT_GAP = 90 * frame.getWidth() / 1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175 * frame.getWidth() / 1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(moneyLabel.getX() + BACK_AND_LEFT_GAP,
                moneyLabel.getY(), TOP_BUTTONS_WIDTH, moneyLabel.getHeight());
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(moneyLabel.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        backButton.addActionListener(backListener);

        JButton nextButton = new JButton("NEXT");
        JButton prevButton = new JButton("PREVIOUS");


        nextButton.setBounds(frame.getX() + frame.getWidth() - TOP_BUTTONS_WIDTH - NEXT_AND_RIGHT_GAP,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP,
                TOP_BUTTONS_WIDTH, moneyLabel.getHeight());
        prevButton.setBounds(nextButton.getX() - NEXT_AND_PREV_GAP - TOP_BUTTONS_WIDTH,
                mainLabel.getY() + TOP_AND_BUTTONS_GAP, TOP_BUTTONS_WIDTH,
                moneyLabel.getHeight());

        nextButton.setBackground(Color.BLUE);
        prevButton.setBackground(Color.BLUE);

        nextButton.setForeground(Color.YELLOW);
        prevButton.setForeground(Color.YELLOW);

        nextButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        prevButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
        prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

        nextButton.setFocusable(false);
        prevButton.setFocusable(false);

        nextButton.setVerticalAlignment(moneyLabel.CENTER);
        prevButton.setVerticalAlignment(moneyLabel.CENTER);


        final int TEXTS_AND_TABLE_GAP = 15 * frame.getHeight() / 800;
        final int ROWS_GAP = 10 * frame.getHeight() / 800;
        int lastUsedY = frame.getY() + (moneyLabel.getY() + moneyLabel.getHeight());
        int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
        int goodsCount = goods.size();
        final int buttonsHeight = 55 /*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
        final int BUTTONS_PER_PAGE = remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 > 0 ? remainedFreeY / (buttonsHeight + ROWS_GAP) - 3 : 0;

        final int TOTAL_FREE_WIDTH = frame.getWidth() - 150;
        final int BUTTONS_WIDTH = (250 * TOTAL_FREE_WIDTH / 1450);
        final int COL1_WIDTH = BUTTONS_WIDTH;
        final int COL2_WIDTH = (200 * TOTAL_FREE_WIDTH / 1450);
        final int COL3_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
        final int COL4_WIDTH = (400 * TOTAL_FREE_WIDTH / 1450);
        final int COL5_WIDTH = (175 * TOTAL_FREE_WIDTH / 1450);

        if (startingIndex == 0) {
            prevButton.setEnabled(false);
        } else {
            ActionListener prevListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processShowGoods(goods, areAvailable, startingIndex - BUTTONS_PER_PAGE, pageNum - 1);
                    frame.dispose();
                }
            };
            prevButton.addActionListener(prevListener);
        }

        nextButton.setEnabled(false);

        JLabel goodNameLabel = new JLabel("GOOD NAME");
        JLabel goodIDLabel = new JLabel("GOOD ID");
        JLabel goodBuyPriceLabel = new JLabel("BUY PRICE(T)");
        JLabel goodSellPriceLabel = new JLabel("SELL PRICE(T)");
        JLabel goodCountLabel = new JLabel("COUNT");

        goodNameLabel.setBounds(frame.getX(), moneyLabel.getY() + moneyLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL1_WIDTH, buttonsHeight);
        goodIDLabel.setBounds(goodNameLabel.getX() + goodNameLabel.getWidth(),
                moneyLabel.getY() + moneyLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL2_WIDTH, buttonsHeight);
        goodBuyPriceLabel.setBounds(goodIDLabel.getX() + goodIDLabel.getWidth(),
                moneyLabel.getY() + moneyLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL3_WIDTH, buttonsHeight);
        goodSellPriceLabel.setBounds(goodBuyPriceLabel.getX() + goodBuyPriceLabel.getWidth(),
                moneyLabel.getY() + moneyLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL4_WIDTH, buttonsHeight);
        goodCountLabel.setBounds(goodSellPriceLabel.getX() + goodSellPriceLabel.getWidth(),
                moneyLabel.getY() + moneyLabel.getHeight() + TEXTS_AND_TABLE_GAP,
                COL5_WIDTH, buttonsHeight);

        goodNameLabel.setForeground(new Color(156, 54, 6));
        goodIDLabel.setForeground(new Color(156, 54, 6));
        goodBuyPriceLabel.setForeground(new Color(156, 54, 6));
        goodSellPriceLabel.setForeground(new Color(156, 54, 6));
        goodCountLabel.setForeground(new Color(156, 54, 6));

        goodNameLabel.setVerticalAlignment(JLabel.CENTER);
        goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

        goodIDLabel.setVerticalAlignment(JLabel.CENTER);
        goodIDLabel.setHorizontalAlignment(JLabel.CENTER);

        goodBuyPriceLabel.setVerticalAlignment(JLabel.CENTER);
        goodBuyPriceLabel.setHorizontalAlignment(JLabel.CENTER);

        goodSellPriceLabel.setVerticalAlignment(JLabel.CENTER);
        goodSellPriceLabel.setHorizontalAlignment(JLabel.CENTER);

        goodCountLabel.setVerticalAlignment(JLabel.CENTER);
        goodCountLabel.setHorizontalAlignment(JLabel.CENTER);


        goodNameLabel.setLayout(null);
        goodIDLabel.setLayout(null);
        goodBuyPriceLabel.setLayout(null);
        goodSellPriceLabel.setLayout(null);
        goodCountLabel.setLayout(null);

        goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodBuyPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodSellPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));
        goodCountLabel.setFont(new Font("MV Boli", Font.PLAIN, 35 * frame.getWidth() / 1600));

        mainLabel.add(backButton);
        mainLabel.add(nextButton);
        mainLabel.add(prevButton);
        mainLabel.add(moneyLabel);
        mainLabel.add(goodNameLabel);
        mainLabel.add(goodIDLabel);
        mainLabel.add(goodBuyPriceLabel);
        mainLabel.add(goodSellPriceLabel);
        mainLabel.add(goodCountLabel);

        if(goods.size() == 0){
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
        }
        int i;
        for (i = startingIndex; i < goods.size(); i++) {
            if (i - startingIndex + 1 > BUTTONS_PER_PAGE) {
                int nextStartingIndex = i;
                nextButton.setEnabled(true);
                ActionListener nextListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == nextButton) {
                            frame.dispose();
                            processShowGoods(goods, areAvailable, nextStartingIndex, pageNum + 1);
                        }
                    }
                };
                nextButton.addActionListener(nextListener);
                ArrayList<Component> components = new ArrayList<>();
                components.add(mainLabel);

                frame.setComponents("listBackground.jpg", components);
                return;
            } else {
                Good currentGood = goods.get(i);
                JButton newButton = new JButton(currentGood.getName());
                newButton.setBounds(goodNameLabel.getX(),
                        goodNameLabel.getY() + goodNameLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        BUTTONS_WIDTH, buttonsHeight);
                newButton.setBackground(Color.BLUE);
                newButton.setForeground(Color.YELLOW);
                newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                newButton.setFocusable(false);
                newButton.setVerticalAlignment(newButton.CENTER);
                newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                newButton.setEnabled(false);

                JLabel IDLabel = new JLabel(Integer.toString(currentGood.getID()));
                JLabel buyPriceLabel = new JLabel(Integer.toString(currentGood.getBuyPrice()));
                JLabel sellPriceLabel = new JLabel(Integer.toString(currentGood.getSellPrice()));
                JLabel countLabel = new JLabel(Integer.toString(currentGood.getRemainingCount()));

                IDLabel.setBounds(goodIDLabel.getX(),
                        goodIDLabel.getY() + goodIDLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodIDLabel.getWidth(), buttonsHeight);
                buyPriceLabel.setBounds(goodBuyPriceLabel.getX(),
                        goodBuyPriceLabel.getY() + goodBuyPriceLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodBuyPriceLabel.getWidth(), buttonsHeight);
                sellPriceLabel.setBounds(goodSellPriceLabel.getX(),
                        goodSellPriceLabel.getY() + goodSellPriceLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodSellPriceLabel.getWidth(), buttonsHeight);
                countLabel.setBounds(goodCountLabel.getX(),
                        goodCountLabel.getY() + goodCountLabel.getHeight() + (i + 1 - startingIndex) * (buttonsHeight + ROWS_GAP),
                        goodCountLabel.getWidth(), buttonsHeight);

                IDLabel.setForeground(new Color(156, 54, 6));
                buyPriceLabel.setForeground(new Color(156, 54, 6));
                sellPriceLabel.setForeground(new Color(156, 54, 6));
                countLabel.setForeground(new Color(156, 54, 6));

                IDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                buyPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                sellPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                countLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                IDLabel.setHorizontalAlignment(JLabel.CENTER);
                buyPriceLabel.setHorizontalAlignment(JLabel.CENTER);
                sellPriceLabel.setHorizontalAlignment(JLabel.CENTER);
                countLabel.setHorizontalAlignment(JLabel.CENTER);


                mainLabel.add(IDLabel);
                mainLabel.add(buyPriceLabel);
                mainLabel.add(sellPriceLabel);
                mainLabel.add(countLabel);


                mainLabel.add(newButton);
                if (i == goods.size() - 1) {
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                }
            }
        }
    }

    private void createTextFieldFrame(FrameCreator frame, JLabel label,
                                      String labelText,Color labelColor, JTextField textField,
                                      String fieldInitialText, String buttonText,
                                      ActionListener listener, String backgroundName){
        label.setText(labelText);
        label.setForeground(labelColor);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLayout(null);
        final int TEXT_HEIGHT = 50;
        label.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        label.setFont(new Font("MV Boli", Font.BOLD, 35*frame.getWidth()/1600));

        final int TEXT_AND_FIELD_GAP = 100*frame.getHeight()/800;;
        final int TEXT_FIELD_WIDTH = 500*frame.getWidth()/1600;;
        final int TEXT_FIELD_HEIGHT = 50*frame.getHeight()/800;;

        textField.setText(fieldInitialText);
        textField.setBounds(frame.getFrameCenter().x - TEXT_FIELD_WIDTH/2,
                label.getY() + TEXT_AND_FIELD_GAP, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT );
        textField.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));
        textField.setHorizontalAlignment(JTextField.LEFT);


        final int BUTTON_AND_FIELD_GAP = 20*frame.getHeight()/800;;
        final int BUTTON_WIDTH = 150*frame.getWidth()/1600;;
        final int BUTTON_HEIGHT = 60*frame.getHeight()/800;;

        JButton button = new JButton(buttonText);
        button.setBounds(textField.getX() - BUTTON_AND_FIELD_GAP - BUTTON_WIDTH,
                textField.getY(), BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBackground(Color.BLUE);
        button.setForeground(Color.YELLOW);
        button.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        button.setFocusable(false);
        button.setVerticalAlignment(label.CENTER);
        button.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        button.addActionListener(listener);

        final int EXIT_AND_BOTTOM_GAP = 20*frame.getHeight()/800;;
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
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
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

}
