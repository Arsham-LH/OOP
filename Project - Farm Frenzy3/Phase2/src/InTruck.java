import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InTruck extends JFrame implements ActionListener {

    private Manager manager ;
    ArrayList<JLabel> names = new ArrayList<>() ;
    ArrayList<JLabel> prices = new ArrayList<>();
    ArrayList<JLabel> numbers = new ArrayList<>();
    ArrayList<JButton> removeFromTruckButtons = new ArrayList<>();
    JLabel name ;
    JLabel price ;
    JLabel number ;
    JLabel button ;
    JPanel panel ;
    public InTruck(Manager manager) throws HeadlessException {
        this.manager = manager;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,600);
        design();
        this.add(panel);
        this.setVisible(true);
    }
    public void design()
    {
        panel = new JPanel();
        panel.setLayout(null);
        int wid = 100 ;
        int length = 40 ;
        int x = 0 ;
        int y = 0 ;
        name = new JLabel("name") ;
        name.setBounds(x,y,wid,length) ;
        name.setBackground(Color.orange) ;
        name.setOpaque(true) ;
        panel.add(name) ;
        x += wid ;
        price = new JLabel("price");
        price.setBounds(x,y,wid,length);
        price.setBackground(Color.orange);
        price.setOpaque(true);
        panel.add(price);
        x += wid;
        number = new JLabel("number");
        number.setBounds(x,y,wid,length)  ;
        number.setBackground(Color.orange);
        number.setOpaque(true);
        panel.add(number);
        x += wid;
        button = new JLabel("remove buttons");
        button.setBounds(x,y,wid,length);
        button.setBackground(Color.orange);
        button.setOpaque(true);
        panel.add(button);
        y = length ;
        for (int i = 0 ; i < manager.truck.getGoods().size() ; i++)
        {
            if (manager.truck.getGoods().get(i).getCount()!=0)
            {
                names.add(new JLabel(manager.truck.getGoods().get(i).getName()));
                names.get(names.size()-1).setBackground(Color.yellow);
                names.get(names.size()-1).setBounds(0,y,wid,length);
                names.get(names.size()-1).setOpaque(true);
                panel.add(names.get(names.size()-1));
                prices.add(new JLabel(String.valueOf(manager.truck.getGoods().get(i).getSellPrice())));
                prices.get(prices.size()-1).setBounds(wid,y,wid,length);
                prices.get(prices.size()-1).setBackground(Color.yellow);
                prices.get(prices.size()-1).setOpaque(true);
                panel.add(prices.get(prices.size()-1));
                numbers.add(new JLabel(String.valueOf(manager.truck.getGoods().get(i).getCount())));
                numbers.get(numbers.size()-1).setBounds(2*wid,y,wid,length);
                numbers.get(numbers.size()-1).setBackground(Color.yellow);
                numbers.get(numbers.size()-1).setOpaque(true);
                panel.add(numbers.get(numbers.size()-1));
                removeFromTruckButtons.add(new JButton("unload from truck"));
                removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBackground(Color.yellow);
                removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBounds(3*wid,y,wid,length);
                removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setOpaque(true);
                removeFromTruckButtons.get(removeFromTruckButtons.size()-1).addActionListener(this);
                panel.add(removeFromTruckButtons.get(removeFromTruckButtons.size()-1));
                y += length ;
            }
        }
        if (manager.truck.getAnimalV("chicken")!=0)
        {
            names.add(new JLabel("chicken"));
            names.get(names.size()-1).setBackground(Color.yellow);
            names.get(names.size()-1).setBounds(0,y,wid,length);
            names.get(names.size()-1).setOpaque(true);
            panel.add(names.get(names.size()-1));
            prices.add(new JLabel(String.valueOf(100)));
            prices.get(prices.size()-1).setBounds(wid,y,wid,length);
            prices.get(prices.size()-1).setBackground(Color.yellow);
            prices.get(prices.size()-1).setOpaque(true);
            panel.add(prices.get(prices.size()-1));
            numbers.add(new JLabel(String.valueOf(manager.truck.getAnimalV("chicken")/2)));
            numbers.get(numbers.size()-1).setBounds(2*wid,y,wid,length);
            numbers.get(numbers.size()-1).setBackground(Color.yellow);
            numbers.get(numbers.size()-1).setOpaque(true);
            panel.add(numbers.get(numbers.size()-1));
            removeFromTruckButtons.add(new JButton("unload from truck"));
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBackground(Color.yellow);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBounds(3*wid,y,wid,length);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setOpaque(true);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).addActionListener(this);
            panel.add(removeFromTruckButtons.get(removeFromTruckButtons.size()-1));
            y += length ;
        }
        if (manager.truck.getAnimalV("buffalo")!=0)
        {
            names.add(new JLabel("buffalo"));
            names.get(names.size()-1).setBackground(Color.yellow);
            names.get(names.size()-1).setBounds(0,y,wid,length);
            names.get(names.size()-1).setOpaque(true);
            panel.add(names.get(names.size()-1));
            prices.add(new JLabel(String.valueOf(400)));
            prices.get(prices.size()-1).setBounds(wid,y,wid,length);
            prices.get(prices.size()-1).setBackground(Color.yellow);
            prices.get(prices.size()-1).setOpaque(true);
            panel.add(prices.get(prices.size()-1));
            numbers.add(new JLabel(String.valueOf(manager.truck.getAnimalV("buffalo")/5)));
            numbers.get(numbers.size()-1).setBounds(2*wid,y,wid,length);
            numbers.get(numbers.size()-1).setBackground(Color.yellow);
            numbers.get(numbers.size()-1).setOpaque(true);
            panel.add(numbers.get(numbers.size()-1));
            removeFromTruckButtons.add(new JButton("unload from truck"));
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBackground(Color.yellow);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBounds(3*wid,y,wid,length);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setOpaque(true);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).addActionListener(this);
            panel.add(removeFromTruckButtons.get(removeFromTruckButtons.size()-1));
            y += length ;
        }
        if (manager.truck.getAnimalV("turkey")!=0)
        {
            names.add(new JLabel("turkey"));
            names.get(names.size()-1).setBackground(Color.yellow);
            names.get(names.size()-1).setBounds(0,y,wid,length);
            names.get(names.size()-1).setOpaque(true);
            panel.add(names.get(names.size()-1));
            prices.add(new JLabel(String.valueOf(100)));
            prices.get(prices.size()-1).setBounds(wid,y,wid,length);
            prices.get(prices.size()-1).setBackground(Color.yellow);
            prices.get(prices.size()-1).setOpaque(true);
            panel.add(prices.get(prices.size()-1));
            numbers.add(new JLabel(String.valueOf(manager.truck.getAnimalV("turkey")/5)));
            numbers.get(numbers.size()-1).setBounds(2*wid,y,wid,length);
            numbers.get(numbers.size()-1).setBackground(Color.yellow);
            numbers.get(numbers.size()-1).setOpaque(true);
            panel.add(numbers.get(numbers.size()-1));
            removeFromTruckButtons.add(new JButton("unload from truck"));
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBackground(Color.yellow);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setBounds(3*wid,y,wid,length);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).setOpaque(true);
            removeFromTruckButtons.get(removeFromTruckButtons.size()-1).addActionListener(this);
            panel.add(removeFromTruckButtons.get(removeFromTruckButtons.size()-1));
            y += length ;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0 ; i < removeFromTruckButtons.size() ; i++)
        {
            if (e.getSource()==removeFromTruckButtons.get(i))
            {
                manager.processUnload(names.get(i).getText());
                this.remove(panel);
                design();
                this.add(panel);
                this.setVisible(false);
                this.setVisible(true);
            }
        }
    }
}