import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInputProcessor {
    private CustomerManager customerManager = new CustomerManager();
    private Scanner scanner = new Scanner(System.in);

    public CustomerInputProcessor(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    private void processShowGoods(ArrayList<Good> goods, boolean areAvailable, int startingIndex, int pageNum){

        String frameTitle = (areAvailable?"AVAILABLE GOODS":"UNAVAILABLE GOODS");
        FrameCreator frame = new FrameCreator(frameTitle, 1600, 800, Color.BLACK,
                                        null, 3, false);

        String labelText = (areAvailable?"Available goods list (page "+pageNum+"):":"Unavailable goods list (page "+pageNum+"):");
        JLabel mainLabel = new JLabel(labelText);
        JLabel moneyLabel = new JLabel("Your wallet money: " + customerManager.getUser().getMoney()+
                (!areAvailable?"_Click on goods to cancel your order":""));

        mainLabel.setPreferredSize(new Dimension(1600, 800));



        mainLabel.setForeground(new Color(156, 54, 6));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        mainLabel.setBounds(frame.getX(), frame.getY(), frame.getWidth(), 800);
        mainLabel.setFont(new Font("MV Boli", Font.BOLD, 35*frame.getWidth()/1600));

        moneyLabel.setForeground(new Color(156, 54, 6));
        moneyLabel.setVerticalAlignment(JLabel.TOP);
        moneyLabel.setHorizontalAlignment(JLabel.CENTER);
        moneyLabel.setLayout(null);
        final int MONEY_LABEL_HEIGHT = 50*frame.getHeight()/800;
        final int MONEY_LABEL_AND_TOP_GAP = 100*frame.getHeight()/800;
        moneyLabel.setBounds(frame.getX(), frame.getY() + MONEY_LABEL_AND_TOP_GAP, frame.getWidth(), MONEY_LABEL_HEIGHT);
        moneyLabel.setFont(new Font("MV Boli", Font.BOLD, 35*frame.getWidth()/1600));



        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu(customerManager.getUser().getUserName(),customerManager.getUser().getPassword(),scanner);
            }
        };
        final int TOP_AND_BUTTONS_GAP = 20;
        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int NEXT_AND_RIGHT_GAP = 90*frame.getWidth()/1600;
        final int NEXT_AND_PREV_GAP = 5;
        final int TOP_BUTTONS_WIDTH = 175*frame.getWidth()/1600;
        JButton backButton = new JButton("MAIN MENU");
        backButton.setBounds(moneyLabel.getX() + BACK_AND_LEFT_GAP,
                moneyLabel.getY(), TOP_BUTTONS_WIDTH, moneyLabel.getHeight());
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.YELLOW);
        backButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        backButton.setFocusable(false);
        backButton.setVerticalAlignment(moneyLabel.CENTER);
        backButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        backButton.addActionListener(backListener);

        JButton nextButton = new JButton("NEXT");
        JButton prevButton = new JButton("PREVIOUS");


        nextButton.setBounds(frame.getX()+frame.getWidth()-TOP_BUTTONS_WIDTH-NEXT_AND_RIGHT_GAP,
                          mainLabel.getY()+TOP_AND_BUTTONS_GAP,
                            TOP_BUTTONS_WIDTH,moneyLabel.getHeight());
        prevButton.setBounds(nextButton.getX()-NEXT_AND_PREV_GAP-TOP_BUTTONS_WIDTH,
                            mainLabel.getY()+TOP_AND_BUTTONS_GAP,TOP_BUTTONS_WIDTH,
                            moneyLabel.getHeight());

        nextButton.setBackground(Color.BLUE);
        prevButton.setBackground(Color.BLUE);

        nextButton.setForeground(Color.YELLOW);
        prevButton.setForeground(Color.YELLOW);

        nextButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        prevButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        nextButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        prevButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));

        nextButton.setFocusable(false);
        prevButton.setFocusable(false);

        nextButton.setVerticalAlignment(moneyLabel.CENTER);
        prevButton.setVerticalAlignment(moneyLabel.CENTER);



        final int TEXTS_AND_TABLE_GAP = 15*frame.getHeight()/800;;
        final int ROWS_GAP = 10*frame.getHeight()/800;;
        int lastUsedY = frame.getY() + (moneyLabel.getY() + moneyLabel.getHeight());
        int remainedFreeY = frame.getHeight() - (lastUsedY + TEXTS_AND_TABLE_GAP);
        final int buttonsHeight = 55 /*(remainedFreeY-(goodsCount*ROWS_GAP))/goodsCount*/;
        final int BUTTONS_PER_PAGE = remainedFreeY/(buttonsHeight+ROWS_GAP)-3>0?remainedFreeY/(buttonsHeight+ROWS_GAP)-3:0;

        final int TOTAL_FREE_WIDTH = frame.getWidth()-150;
        final int BUTTONS_WIDTH = (300*TOTAL_FREE_WIDTH/1450);
        final int COL1_WIDTH = BUTTONS_WIDTH;
        final int COL2_WIDTH = (350*TOTAL_FREE_WIDTH/1450);
        final int COL3_WIDTH = (650*TOTAL_FREE_WIDTH/1450);
        final int COL4_WIDTH = (150*TOTAL_FREE_WIDTH/1450);

        if (startingIndex == 0){
            prevButton.setEnabled(false);
        }else{
            ActionListener prevListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    processShowGoods(goods, areAvailable, startingIndex-BUTTONS_PER_PAGE,pageNum-1);
                    frame.dispose();
                }
            };
            prevButton.addActionListener(prevListener);
        }

        nextButton.setEnabled(false);

        JLabel goodNameLabel = new JLabel("GOOD NAME");
        JLabel goodIDLabel = new JLabel("GOOD ID");
        JLabel goodPriceLabel = new JLabel("PRICE(T)");
        JLabel goodCountLabel = new JLabel("COUNT");

        goodNameLabel.setBounds(frame.getX(), moneyLabel.getY()+ moneyLabel.getHeight()+TEXTS_AND_TABLE_GAP,
                                COL1_WIDTH, buttonsHeight);
        goodIDLabel.setBounds(goodNameLabel.getX()+goodNameLabel.getWidth(),
                moneyLabel.getY()+ moneyLabel.getHeight()+TEXTS_AND_TABLE_GAP,
                               COL2_WIDTH, buttonsHeight);
        goodPriceLabel.setBounds(goodIDLabel.getX()+goodIDLabel.getWidth(),
                moneyLabel.getY()+ moneyLabel.getHeight()+TEXTS_AND_TABLE_GAP,
                                 COL3_WIDTH, buttonsHeight);
        goodCountLabel.setBounds(goodPriceLabel.getX()+goodPriceLabel.getWidth(),
                moneyLabel.getY()+ moneyLabel.getHeight()+TEXTS_AND_TABLE_GAP,
                                 COL4_WIDTH, buttonsHeight);

        goodNameLabel.setForeground(new Color(156, 54, 6));
        goodIDLabel.setForeground(new Color(156, 54, 6));
        goodPriceLabel.setForeground(new Color(156, 54, 6));
        goodCountLabel.setForeground(new Color(156, 54, 6));

        goodNameLabel.setVerticalAlignment(JLabel.CENTER);
        goodNameLabel.setHorizontalAlignment(JLabel.CENTER);

        goodIDLabel.setVerticalAlignment(JLabel.CENTER);
        goodIDLabel.setHorizontalAlignment(JLabel.CENTER);

        goodPriceLabel.setVerticalAlignment(JLabel.CENTER);
        goodPriceLabel.setHorizontalAlignment(JLabel.CENTER);

        goodCountLabel.setVerticalAlignment(JLabel.CENTER);
        goodCountLabel.setHorizontalAlignment(JLabel.CENTER);


        goodNameLabel.setLayout(null);
        goodIDLabel.setLayout(null);
        goodPriceLabel.setLayout(null);
        goodCountLabel.setLayout(null);

        goodNameLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));
        goodIDLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));
        goodPriceLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));
        goodCountLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*frame.getWidth()/1600));

        mainLabel.add(backButton);
        mainLabel.add(nextButton);
        mainLabel.add(prevButton);
        mainLabel.add(moneyLabel);
        mainLabel.add(goodNameLabel);
        mainLabel.add(goodIDLabel);
        mainLabel.add(goodPriceLabel);
        mainLabel.add(goodCountLabel);

        if(goods.size() == 0){
            ArrayList<Component> components = new ArrayList<>();
            components.add(mainLabel);

            frame.setComponents("listBackground.jpg", components);
            return;
        }

        int i;
        for ( i=startingIndex; i < goods.size(); i++) {
            if (i-startingIndex+1>BUTTONS_PER_PAGE){
                int nextStartingIndex= i;
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
            }else {
                Good currentGood = goods.get(i);
                JButton newButton = new JButton(currentGood.getName());
                newButton.setBounds(goodNameLabel.getX(),
                        goodNameLabel.getY() + goodNameLabel.getHeight() + (i+1-startingIndex) * (buttonsHeight + ROWS_GAP),
                        BUTTONS_WIDTH, buttonsHeight);
                newButton.setBackground(Color.BLUE);
                newButton.setForeground(Color.YELLOW);
                newButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
                newButton.setFocusable(false);
                newButton.setVerticalAlignment(newButton.CENTER);
                newButton.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                if (!areAvailable){
                    Order correspondingOrder = customerManager.firstMatchedOrderWithGood(currentGood);
                    if (correspondingOrder == null) {
                        newButton.setEnabled(false);
                    }else{
                        newButton.setEnabled(true);
                        ActionListener newListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == newButton) {
                                    customerManager.cancelOrder(currentGood.getID());
                                    JOptionPane.showMessageDialog(mainLabel,
                                            "Order cancelled successfully!",
                                            "Cancel successful", JOptionPane.INFORMATION_MESSAGE,
                                            new ImageIcon("cancel logo.jpg"));
                                }
                            }
                        };
                        newButton.addActionListener(newListener);
                    }
                }else {
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == newButton) {
                                frame.dispose();
                                createOrderFrame(currentGood);
                            }
                        }
                    };
                    newButton.addActionListener(listener);
                }

                JLabel IDLabel = new JLabel(Integer.toString(currentGood.getID()));
                JLabel priceLabel = new JLabel(Integer.toString(currentGood.getSellPrice()));
                JLabel countLabel = new JLabel(Integer.toString(currentGood.getRemainingCount()));

                IDLabel.setBounds(goodIDLabel.getX(),
                        goodIDLabel.getY()+goodIDLabel.getHeight()+(i+1-startingIndex)*(buttonsHeight+ROWS_GAP),
                        goodIDLabel.getWidth(),buttonsHeight);
                priceLabel.setBounds(goodPriceLabel.getX(),
                        goodPriceLabel.getY()+goodPriceLabel.getHeight()+(i+1-startingIndex)*(buttonsHeight+ROWS_GAP),
                        goodPriceLabel.getWidth(),buttonsHeight);
                countLabel.setBounds(goodCountLabel.getX(),
                        goodCountLabel.getY()+goodCountLabel.getHeight()+(i+1-startingIndex)*(buttonsHeight+ROWS_GAP),
                        goodCountLabel.getWidth(),buttonsHeight);

                IDLabel.setForeground(new Color(156, 54, 6));
                priceLabel.setForeground(new Color(156, 54, 6));
                countLabel.setForeground(new Color(156, 54, 6));

                IDLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                priceLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));
                countLabel.setFont(new Font("MV Boli", Font.PLAIN, 20 * frame.getWidth() / 1600));

                IDLabel.setHorizontalAlignment(JLabel.CENTER);
                priceLabel.setHorizontalAlignment(JLabel.CENTER);
                countLabel.setHorizontalAlignment(JLabel.CENTER);


                mainLabel.add(IDLabel);
                mainLabel.add(priceLabel);
                mainLabel.add(countLabel);

                mainLabel.add(newButton);
                if (i==goods.size()-1){
                    ArrayList<Component> components = new ArrayList<>();
                    components.add(mainLabel);

                    frame.setComponents("listBackground.jpg", components);
                }
            }
        }
    }

    private void createOrderFrame(Good currentGood) {
        FrameCreator frame = new FrameCreator(currentGood.getName(), 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Select one");

        mainLabel.setForeground(new Color(4, 88, 15));
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
                processMenu(customerManager.getUser().getUserName(),customerManager.getUser().getPassword(),scanner);
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




        JButton orderButton = new JButton("ORDER");
        JButton cancelOrderButton = new JButton("CANCEL ORDER");

        orderButton.setBounds(frame.getFrameCenter().x-BUTTON_WIDTH/2,
                frame.getY()+TOP_AND_BUTTONS_GAP,BUTTON_WIDTH,BUTTON_HEIGHT);
        cancelOrderButton.setBounds(frame.getFrameCenter().x-BUTTON_WIDTH/2,
                orderButton.getY()+orderButton.getHeight()+TOP_AND_BUTTONS_GAP,BUTTON_WIDTH,BUTTON_HEIGHT);

        orderButton.setBackground(Color.BLUE);
        cancelOrderButton.setBackground(Color.BLUE);

        orderButton.setForeground(Color.YELLOW);
        cancelOrderButton.setForeground(Color.YELLOW);

        orderButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));
        cancelOrderButton.setBorder(BorderFactory.createBevelBorder(1,Color.CYAN,Color.black,Color.GRAY,Color.green));

        orderButton.setFocusable(false);
        cancelOrderButton.setFocusable(false);

        orderButton.setFont(new Font("MV Boli", Font.PLAIN, 30*frame.getWidth()/1600));
        cancelOrderButton.setFont(new Font("MV Boli", Font.PLAIN, 30*frame.getWidth()/1600));

        ActionListener orderListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                createOrderInputFrame(currentGood);
            }
        };
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerManager.cancelOrder(currentGood.getID());
                JOptionPane.showMessageDialog(mainLabel,
                        "Order cancelled successfully!",
                        "Cancel successful", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("cancel logo.jpg"));
            }
        };

        orderButton.addActionListener(orderListener);
        cancelOrderButton.addActionListener(cancelListener);

        boolean existsThisOrder = false;
        for (Order order : customerManager.getOrders()) {
            if (order.orderID.equals(customerManager.getUser().getUserName()) && (order.goodID == currentGood.getID())){
                existsThisOrder = true;
                break;
            }
        }
        if(!existsThisOrder){
            cancelOrderButton.setEnabled(false);
        }

        mainLabel.add(backButton);
        mainLabel.add(orderButton);
        mainLabel.add(cancelOrderButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);
        frame.setComponents("listBackground.jpg", components);

    }

    private void createOrderInputFrame(Good currentGood) {
        FrameCreator frame = new FrameCreator(currentGood.getName(), 1600, 800, Color.black,
                null, 3, false);
        JLabel mainLabel = new JLabel();

        final int BACK_AND_LEFT_GAP = 15*frame.getWidth()/1600;
        final int BUTTON_WIDTH = 150*frame.getWidth()/1600;
        final int BUTTON_HEIGHT = 75*frame.getHeight()/800;
        final int TOP_AND_BACK_BUTTON_GAP = 50*frame.getHeight()/800;

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                processMenu(customerManager.getUser().getUserName(),customerManager.getUser().getPassword(),scanner);
            }
        };

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

        mainLabel.add(backButton);

        JTextField textField = new JTextField();
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                boolean existsError = false;
                if (Integer.parseInt(input) == 0){
                    JOptionPane.showMessageDialog(mainLabel,
                            "Please check your input!",
                            "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                    existsError = true;
                }else {
                    for (int i = 0; i < input.length(); i++) {
                        if (input.charAt(i) > '9' || input.charAt(i) < '0') {
                            JOptionPane.showMessageDialog(mainLabel,
                                    "Please check your input!",
                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                            existsError = true;
                            break;

                        }
                    }
                    if(!existsError){
                        int inputCount = Integer.parseInt(input);
                        if (inputCount>currentGood.getRemainingCount()){
                            JOptionPane.showMessageDialog(mainLabel,
                                    "There is not enough good in the market!",
                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                            existsError = true;
                        }else if(customerManager.getUser().getMoney() < inputCount*currentGood.getSellPrice()){
                            JOptionPane.showMessageDialog(mainLabel,
                                    "You don't have enough money in your wallet!",
                                    "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
                            existsError = true;
                        }
                    }
                }
                if (!existsError){
                    customerManager.addOrder(currentGood.getID(), Integer.parseInt(input));
                    JOptionPane.showMessageDialog(mainLabel,
                            "Order successful!",
                            "Order successful", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("done.png"));

                }
            }
        };
        createTextFieldFrame(frame, mainLabel, "Please enter the count for your order",
                textField, "0", "submit",submitListener,"listBackground.jpg"  );
    }


    private void processLoadObjects(String [] objectsNames){
        for (int i = 0; i < objectsNames.length; i++) {
            customerManager.loadObjects(objectsNames[i]);
        }
    }

    private void processSaveObjects(String [] objectsNames){
        for (int i = 0; i < objectsNames.length; i++) {
            customerManager.saveObjects(objectsNames[i]);
        }
    }








    public void run() {
        processLoadObjects(new String[]{"goods" , "unavailableGoods" , "orders" , "checkedOrders" , "users"});
        createWelcomeFrame();
    }

    private void createWelcomeFrame(){
        FrameCreator frame = new FrameCreator("Welcome frame", 1600, 800, Color.BLACK,
                null, 3, false);

        JLabel initialText = new JLabel("Welcome ! If you have an account press 'LOG IN' , else press 'SIGNUP' to create a new one .Press 'EXIT' to quit the program.");
        initialText.setForeground(new Color(7, 31, 186));
        initialText.setFont(new Font("MV Boli", Font.BOLD,23*frame.getWidth()/1600));
        initialText.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        initialText.setHorizontalAlignment(JLabel.CENTER);
        initialText.setVerticalAlignment(JLabel.TOP);
        initialText.setLayout(null);


        JButton loginButton = new JButton("LOG IN");
        JButton signupButton = new JButton("SIGN UP");
        JButton exitButton = new JButton("EXIT");

        final int TEXT_AND_BUTTON1_GAP = 150*frame.getHeight()/800;;
        final int BUTTONS_WIDTH = 350*frame.getWidth()/1600;;
        final int BUTTONS_HEIGHT = 100*frame.getHeight()/800;;
        final int BUTTONS_GAP = 20*frame.getHeight()/800;;
        final int EXIT_AND_BOTTOM_GAP = 50*frame.getHeight()/800;;

        loginButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, frame.getY() + TEXT_AND_BUTTON1_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        signupButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, loginButton.getY() + loginButton.getHeight() + BUTTONS_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        exitButton.setBounds(frame.getFrameCenter().x - BUTTONS_WIDTH/2, frame.getY()+frame.getHeight()-BUTTONS_HEIGHT-EXIT_AND_BOTTOM_GAP, BUTTONS_WIDTH, BUTTONS_HEIGHT);

        loginButton.setFocusable(false);
        signupButton.setFocusable(false);
        exitButton.setFocusable(false);

        loginButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        signupButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20*frame.getWidth()/1600));

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
                    frame.dispose();
                    processLogin(new Scanner(System.in));
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
                    customerManager.saveObjects("users");
                    System.exit(0);
                }
            }
        });

        initialText.add(loginButton);
        initialText.add(signupButton);
        initialText.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(initialText);

        frame.setComponents("background.jpg", components);

    }

    private void processLogin(Scanner scanner) {

        //GRAPHICS
        FrameCreator userNameFrame = new FrameCreator("userName", 1600, 800, Color.black,
                null, 3, false);

        JLabel enterUserName = new JLabel();
        JTextField userNameField = new JTextField();
        ActionListener userNameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                if (!customerManager.existsUserName(userName)) {
                    JOptionPane.showMessageDialog(enterUserName,
                            "This username does not exist. Please enter another one",
                            "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("error.png"));
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
                            if (!customerManager.isCorrectPass(userName, password)){
                                JOptionPane.showMessageDialog(enterPass,
                                        "Password is not correct. Enter the password again",
                                        "ERROR",JOptionPane.ERROR_MESSAGE,
                                        new ImageIcon("error.png"));
                            }else{
                                customerManager.setUser(userName, password);
                                passFrame.dispose();
                                processMenu(userName, password, scanner);
                            }
                        }
                    };
                    createTextFieldFrame(passFrame, enterPass, "Please enter your password",passField,
                            "password","submit", passListener, "background.jpg");
                }

            }
        };

        createTextFieldFrame(userNameFrame, enterUserName,"Please enter your username",userNameField,
                "username","submit", userNameListener, "background.jpg");


    }

    private void processMenu(String userName, String password, Scanner scanner) {

        FrameCreator menuFrame = new FrameCreator("MENU", 1600, 800, Color.black,
                null, 3, false);

        JLabel mainLabel = new JLabel("Select one");

        mainLabel.setForeground(new Color(7, 31, 186));
        mainLabel.setVerticalAlignment(JLabel.TOP);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setLayout(null);
        final int TEXT_HEIGHT = 50;
        mainLabel.setBounds(menuFrame.getX(), menuFrame.getY(), menuFrame.getWidth(), menuFrame.getHeight());
        mainLabel.setFont(new Font("MV Boli", Font.PLAIN, 35*menuFrame.getWidth()/1600));


        final int BUTTON_WIDTH = 350*menuFrame.getWidth()/1600;;
        final int BUTTON_HEIGHT = 90*menuFrame.getHeight()/800;;
        final int TOP_AND_BUTTONS_GAP = 200*menuFrame.getHeight()/800;;
        final int BUTTONS_GAP = 20*menuFrame.getHeight()/800;;
        final int EXIT_AND_BOTTOM_GAP = 50*menuFrame.getHeight()/800;;

        JButton showAvailableButton = new JButton("SHOW AVAILABLE GOODS");
        JButton showUnavailableButton = new JButton("SHOW UNAVAILABLE GOODS");
        JButton logoutButton = new JButton("LOG OUT");
        JButton exitButton = new JButton("EXIT");

        showAvailableButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + TOP_AND_BUTTONS_GAP, BUTTON_WIDTH, BUTTON_HEIGHT);
        showUnavailableButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                showAvailableButton.getY() + showAvailableButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        logoutButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                showUnavailableButton.getY() + showUnavailableButton.getHeight() + BUTTONS_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        exitButton.setBounds(menuFrame.getFrameCenter().x - BUTTON_WIDTH / 2,
                menuFrame.getY() + menuFrame.getHeight() - BUTTON_HEIGHT - EXIT_AND_BOTTOM_GAP,
                BUTTON_WIDTH, BUTTON_HEIGHT);

        showAvailableButton.setBackground(Color.BLUE);
        showUnavailableButton.setBackground(Color.BLUE);
        logoutButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.BLUE);

        showAvailableButton.setForeground(Color.YELLOW);
        showUnavailableButton.setForeground(Color.YELLOW);
        logoutButton.setForeground(Color.YELLOW);
        exitButton.setForeground(Color.YELLOW);

        showAvailableButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        showUnavailableButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        logoutButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));
        exitButton.setBorder(BorderFactory.createBevelBorder(1, Color.CYAN, Color.black, Color.GRAY, Color.green));

        showAvailableButton.setFocusable(false);
        showUnavailableButton.setFocusable(false);
        logoutButton.setFocusable(false);
        exitButton.setFocusable(false);

        showAvailableButton.setVerticalAlignment(mainLabel.CENTER);
        showUnavailableButton.setVerticalAlignment(mainLabel.CENTER);
        logoutButton.setVerticalAlignment(mainLabel.CENTER);
        exitButton.setVerticalAlignment(mainLabel.CENTER);

        showAvailableButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        showUnavailableButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        logoutButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 20*menuFrame.getWidth()/1600));

        ActionListener showAvailableListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowGoods(customerManager.getGoods(), true,0,1);
            }
        };
        ActionListener showUnavailableListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processShowGoods(customerManager.getUnavailableGoods(), false,0,1);

            }
        };
        ActionListener logoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                processLogout(userName, password);
            }
        };
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        showAvailableButton.addActionListener(showAvailableListener);
        showUnavailableButton.addActionListener(showUnavailableListener);
        logoutButton.addActionListener(logoutListener);
        exitButton.addActionListener(exitListener);

        mainLabel.add(showAvailableButton);
        mainLabel.add(showUnavailableButton);
        mainLabel.add(logoutButton);
        mainLabel.add(exitButton);

        ArrayList<Component> components = new ArrayList<>();
        components.add(mainLabel);

        menuFrame.setComponents("background.jpg", components);

    }

    private void processLogout(String userName, String password) {
        customerManager.setUser(null);
        customerManager.saveObjects("users");
        run();
    }


    private void createTextFieldFrame(FrameCreator frame, JLabel label,
                                      String labelText, JTextField textField,
                                      String fieldInitialText, String buttonText,
                                      ActionListener listener, String backgroundName){
        label.setText(labelText);
        label.setForeground(new Color(7, 31, 186));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLayout(null);
        final int TEXT_HEIGHT = 50;
        label.setBounds(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        label.setFont(new Font("MV Boli", Font.BOLD, 35*frame.getWidth()/1600));

        final int TEXT_AND_FIELD_GAP = 100*frame.getHeight()/800;;
        final int TEXT_FIELD_WIDTH = 800*frame.getWidth()/1600;;
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

    private void processSignUp(Scanner scanner) {

        FrameCreator userNameFrame = new FrameCreator("userName", 1600, 800, Color.black,
                null, 3, false);

        JLabel enterUserName = new JLabel();
        JTextField userNameField = new JTextField();

        ActionListener userNameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText();
                if (customerManager.existsUserName(userName)){
                    JOptionPane.showMessageDialog(userNameFrame,
                            "This username already exists. Please enter another one",
                            "ERROR",JOptionPane.ERROR_MESSAGE,
                            new ImageIcon("error.png"));
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
                            passFrame.dispose();
                            String password = passField.getText();
                            customerManager.addUser(userName , password);
                            customerManager.setUser(userName, password);
                            processMenu(userName, password, scanner);
                        }
                    };

                    createTextFieldFrame(passFrame,enterPass,
                            "Please enter a password for your account",passField,
                            "password","submit",passListener,"background.jpg");
                }
            }
        };

        createTextFieldFrame(userNameFrame,enterUserName,"Enter a username",userNameField,
                "username","submit",userNameListener,"background.jpg");



    }
}
