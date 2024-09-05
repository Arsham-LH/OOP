import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GoodList extends JFrame  {
    ArrayList<JLabel> labelsOfPrice = new ArrayList<>();
    ArrayList<JLabel> labelsOfName = new ArrayList<>();
    ArrayList<JLabel> labelsOfNumber = new ArrayList<>();
    JPanel panel ;
    private Cellar cellar;
    public GoodList(Cellar cellar) throws HeadlessException {
        this.cellar = cellar;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        designTable();
    }
    private void designTable ()
    {
        this.setSize(240,600);
        panel = new JPanel();
        panel.setLayout(null);
        int x = 0 ;
        int y = 0 ;
        int wid=80;
        int length=60;
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
        y+=length;
        for (int i = 0 ; i < cellar.getGoods().size() ; i ++)
        {
            labelsOfName.add(new JLabel(cellar.getGoods().get(i).getName()));
            labelsOfName.get(labelsOfName.size()-1).setBounds(0,y,wid,length);
            labelsOfName.get(labelsOfName.size()-1).setBackground(Color.yellow);
            labelsOfName.get(labelsOfName.size()-1).setOpaque(true);
            panel.add(labelsOfName.get(labelsOfName.size()-1));
            labelsOfNumber.add(new JLabel(String.valueOf(cellar.getGoods().get(i).getCount())));
            labelsOfNumber.get(labelsOfNumber.size()-1).setBounds(wid,y,wid,length);
            labelsOfNumber.get(labelsOfNumber.size()-1).setBackground(Color.yellow);
            labelsOfNumber.get(labelsOfNumber.size()-1).setOpaque(true);
            panel.add(labelsOfNumber.get(labelsOfNumber.size()-1));
            labelsOfPrice.add(new JLabel(String.valueOf(cellar.getGoods().get(i).getSellPrice())));
            labelsOfPrice.get(labelsOfPrice.size()-1).setBounds(2*wid,y,wid,length);
            labelsOfPrice.get(labelsOfPrice.size()-1).setBackground(Color.yellow);
            labelsOfPrice.get(labelsOfPrice.size()-1).setOpaque(true);
            panel.add(labelsOfPrice.get(labelsOfPrice.size()-1));
            y+=length;
        }
        panel.add(goodName);
        panel.add(Inventory);
        panel.add(Price);
        this.add(panel);
        this.setVisible(true);
    }
}