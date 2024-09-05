import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameCreator extends JFrame implements ActionListener {
    private Point frameCenter;
    private String backgroundImageName;
    private ArrayList<Component> components = new ArrayList<>();

    FrameCreator(String title, int width, int height, Color backgroundColor,
                 LayoutManager layout, int DCO, boolean pack){
        this.setTitle(title);
        this.setSize(width,height);
        if (backgroundColor != null) {
            this.getContentPane().setBackground(backgroundColor);
        }
        this.setLayout(layout);
        this.setDefaultCloseOperation(DCO);
        if (pack)
            this.pack();
        frameCenter = new Point(this.getWidth()/2, this.getHeight()/2);
        ImageIcon gameLogo = new ImageIcon("logoResized.jpg");
        this.setIconImage(gameLogo.getImage());
    }

    public void setComponents(String imageName, ArrayList<Component> components /*, boolean removePrevComponents*/){
        this.components = new ArrayList<>();
        if (components != null) {
            for (Component component : components) {
                this.components.add(component);
            }
        }
        JLabel label = new JLabel();
        label.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        if (imageName == null){
            label.setBackground(Color.black);
        }else {
            this.backgroundImageName = imageName;
            ImageIcon background = new ImageIcon(backgroundImageName);
            label.setIcon(background);
        }

        if (this.components != null) {
            for (Component component : this.components) {
                label.add(component, JLayeredPane.DRAG_LAYER);
            }
        }

        label.setOpaque(true);
        this.add(label);


        this.setVisible(true);

    }
    public Point getFrameCenter() {
        return frameCenter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
