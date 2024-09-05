import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TruckPage extends JFrame implements ActionListener {

    private Manager manager;
    ArrayList<JLabel> labelsOfPrice = new ArrayList<>();
    ArrayList<JLabel> labelsOfName = new ArrayList<>();
    ArrayList<JLabel> labelsOfNumber = new ArrayList<>();
    ArrayList<JButton> sellButtons = new ArrayList<>();
    JButton inTruckButton ;
    JButton truckGo ;
    JPanel panel ;
    public TruckPage(Manager manager) throws HeadlessException {
        this.manager = manager;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        designTable();
        this.setVisible(true);
    }
    private void designTable ()
    {
        this.setSize(750,600);
        panel = new JPanel();
        panel.setLayout(null);
        int x = 0 ;
        int y = 0 ;
        int wid=150;
        int length=40;
        JLabel goodName = new JLabel("Good name");
        goodName.setBounds(x,y,wid,length);
        goodName.setBackground(Color.orange);
        goodName.setVerticalTextPosition(JLabel.CENTER);
        goodName.setOpaque(true);
        x+=wid;
        JLabel Inventory = new JLabel("Inventory");
        Inventory.setBounds(x,y,wid,length);
        Inventory.setBackground(Color.orange);
        Inventory.setVerticalTextPosition(JLabel.CENTER);
        Inventory.setOpaque(true);
        x+=wid;
        JLabel Price = new JLabel("Price");
        Price.setBounds(x,y,wid,length);
        Price.setHorizontalTextPosition(JLabel.CENTER);
        Price.setBackground(Color.orange);
        Price.setVerticalTextPosition(JLabel.CENTER);
        Price.setOpaque(true);
        x+=wid;
        JLabel buttons = new JLabel("sell button");
        buttons.setBounds(x,y,wid,length);
        buttons.setHorizontalTextPosition(JLabel.CENTER);
        buttons.setBackground(Color.orange);
        buttons.setVerticalTextPosition(JLabel.CENTER);
        buttons.setOpaque(true);
        x+=wid;
        inTruckButton = new JButton("show in truck");
        inTruckButton.setBackground(Color.orange);
        inTruckButton.setOpaque(true);
        inTruckButton.addActionListener(this);
        inTruckButton.setBounds(x,y,wid,length);
        panel.add(inTruckButton);
        truckGo = new JButton("truck go");
        truckGo.setBounds(x,y+length,wid,length);
        truckGo.setBackground(Color.magenta);
        truckGo.setOpaque(true);
        truckGo.addActionListener(this);
        panel.add(truckGo);
        panel.add(goodName);
        panel.add(Inventory);
        panel.add(Price);
        panel.add(buttons);
        panel.setLayout(null);
        panel.setVisible(true);
        y+=length;
        for (int i = 0 ; i < manager.cellar.getGoods().size() ; i ++)
        {
            labelsOfName.add(new JLabel(manager.cellar.getGoods().get(i).getName()));
            labelsOfName.get(labelsOfName.size()-1).setBounds(0,y,wid,length);
            labelsOfName.get(labelsOfName.size()-1).setBackground(Color.yellow);
            labelsOfName.get(labelsOfName.size()-1).setOpaque(true);
            panel.add(labelsOfName.get(labelsOfName.size()-1));
            labelsOfNumber.add(new JLabel(String.valueOf(manager.cellar.getGoods().get(i).getCount())));
            labelsOfNumber.get(labelsOfNumber.size()-1).setBounds(wid,y,wid,length);
            labelsOfNumber.get(labelsOfNumber.size()-1).setBackground(Color.yellow);
            labelsOfNumber.get(labelsOfNumber.size()-1).setOpaque(true);
            panel.add(labelsOfNumber.get(labelsOfNumber.size()-1));
            labelsOfPrice.add(new JLabel(String.valueOf(manager.cellar.getGoods().get(i).getSellPrice())));
            labelsOfPrice.get(labelsOfPrice.size()-1).setBounds(2*wid,y,wid,length);
            labelsOfPrice.get(labelsOfPrice.size()-1).setBackground(Color.yellow);
            labelsOfPrice.get(labelsOfPrice.size()-1).setOpaque(true);
            panel.add(labelsOfPrice.get(labelsOfPrice.size()-1));
            sellButtons.add(new JButton("add to truck"));
            sellButtons.get(sellButtons.size()-1).setBounds(3*wid,y,wid,length);
            sellButtons.get(sellButtons.size()-1).setBackground(Color.yellow);
            sellButtons.get(sellButtons.size()-1).setOpaque(true);
            sellButtons.get(sellButtons.size()-1).addActionListener(this);
            panel.add(sellButtons.get(sellButtons.size()-1));
            y+=length;
        }
        this.add(panel);
        if (manager.countAnimal("chicken")!=0)
        {
            labelsOfName.add(new JLabel("chicken"));
            labelsOfName.get(labelsOfName.size()-1).setBounds(0,y,wid,length);
            labelsOfName.get(labelsOfName.size()-1).setBackground(Color.yellow);
            labelsOfName.get(labelsOfName.size()-1).setOpaque(true);
            panel.add(labelsOfName.get(labelsOfName.size()-1));
            labelsOfNumber.add(new JLabel(String.valueOf(manager.countAnimal("chicken"))));
            labelsOfNumber.get(labelsOfNumber.size()-1).setBackground(Color.yellow);
            labelsOfNumber.get(labelsOfNumber.size()-1).setBounds(wid,y,wid,length);
            labelsOfNumber.get(labelsOfNumber.size()-1).setOpaque(true);
            panel.add(labelsOfNumber.get(labelsOfNumber.size()-1));
            labelsOfPrice.add(new JLabel("100"));
            labelsOfPrice.get(labelsOfPrice.size()-1).setBounds(2*wid,y,wid,length);
            labelsOfPrice.get(labelsOfPrice.size()-1).setBackground(Color.yellow);
            labelsOfPrice.get(labelsOfPrice.size()-1).setOpaque(true);
            panel.add(labelsOfPrice.get(labelsOfPrice.size()-1));
            sellButtons.add(new JButton("add to truck"));
            sellButtons.get(sellButtons.size()-1).setBounds(3*wid,y,wid,length);
            sellButtons.get(sellButtons.size()-1).setBackground(Color.yellow);
            sellButtons.get(sellButtons.size()-1).setOpaque(true);
            sellButtons.get(sellButtons.size()-1).addActionListener(this);
            panel.add(sellButtons.get(sellButtons.size()-1));
            y+=length;
        }
        if (manager.countAnimal("buffalo")!=0)
        {
            labelsOfName.add(new JLabel("buffalo"));
            labelsOfName.get(labelsOfName.size()-1).setBounds(0,y,wid,length);
            labelsOfName.get(labelsOfName.size()-1).setBackground(Color.yellow);
            labelsOfName.get(labelsOfName.size()-1).setOpaque(true);
            panel.add(labelsOfName.get(labelsOfName.size()-1));
            labelsOfNumber.add(new JLabel(String.valueOf(manager.countAnimal("buffalo"))));
            labelsOfNumber.get(labelsOfNumber.size()-1).setBackground(Color.yellow);
            labelsOfNumber.get(labelsOfNumber.size()-1).setBounds(wid,y,wid,length);
            labelsOfNumber.get(labelsOfNumber.size()-1).setOpaque(true);
            panel.add(labelsOfNumber.get(labelsOfNumber.size()-1));
            labelsOfPrice.add(new JLabel("400"));
            labelsOfPrice.get(labelsOfPrice.size()-1).setBounds(2*wid,y,wid,length);
            labelsOfPrice.get(labelsOfPrice.size()-1).setBackground(Color.yellow);
            labelsOfPrice.get(labelsOfPrice.size()-1).setOpaque(true);
            panel.add(labelsOfPrice.get(labelsOfPrice.size()-1));
            sellButtons.add(new JButton("add to truck"));
            sellButtons.get(sellButtons.size()-1).setBounds(3*wid,y,wid,length);
            sellButtons.get(sellButtons.size()-1).setBackground(Color.yellow);
            sellButtons.get(sellButtons.size()-1).setOpaque(true);
            sellButtons.get(sellButtons.size()-1).addActionListener(this);
            panel.add(sellButtons.get(sellButtons.size()-1));
            y+=length;
        }
        if (manager.countAnimal("turkey")!=0)
        {
            labelsOfName.add(new JLabel("turkey"));
            labelsOfName.get(labelsOfName.size()-1).setBounds(0,y,wid,length);
            labelsOfName.get(labelsOfName.size()-1).setBackground(Color.yellow);
            labelsOfName.get(labelsOfName.size()-1).setOpaque(true);
            panel.add(labelsOfName.get(labelsOfName.size()-1));
            labelsOfNumber.add(new JLabel(String.valueOf(manager.countAnimal("turkey"))));
            labelsOfNumber.get(labelsOfNumber.size()-1).setBackground(Color.yellow);
            labelsOfNumber.get(labelsOfNumber.size()-1).setBounds(wid,y,wid,length);
            labelsOfNumber.get(labelsOfNumber.size()-1).setOpaque(true);
            panel.add(labelsOfNumber.get(labelsOfNumber.size()-1));
            labelsOfPrice.add(new JLabel("200"));
            labelsOfPrice.get(labelsOfPrice.size()-1).setBounds(2*wid,y,wid,length);
            labelsOfPrice.get(labelsOfPrice.size()-1).setBackground(Color.yellow);
            labelsOfPrice.get(labelsOfPrice.size()-1).setOpaque(true);
            panel.add(labelsOfPrice.get(labelsOfPrice.size()-1));
            sellButtons.add(new JButton("add to truck"));
            sellButtons.get(sellButtons.size()-1).setBounds(3*wid,y,wid,length);
            sellButtons.get(sellButtons.size()-1).setBackground(Color.yellow);
            sellButtons.get(sellButtons.size()-1).setOpaque(true);
            sellButtons.get(sellButtons.size()-1).addActionListener(this);
            panel.add(sellButtons.get(sellButtons.size()-1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0 ; i < sellButtons.size() ; i++)
        {
            if (e.getSource()==sellButtons.get(i))
            {
                manager.processLoad(labelsOfName.get(i).getText());
                this.remove(panel);
                designTable();
                this.add(panel);
                this.setVisible(false);
                this.setVisible(true);
            }
        }
        if (e.getSource()==inTruckButton)
        {
            new InTruck(manager);
            this.dispose();
        }
        else if (e.getSource()==truckGo)
        {
            manager.processSell();
        }
    }
}