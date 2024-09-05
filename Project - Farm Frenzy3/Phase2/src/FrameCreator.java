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
        ImageIcon gameLogo = new ImageIcon("cow logo.png");
        this.setIconImage(gameLogo.getImage());
    }

    public void setComponents(String imageName, ArrayList<Component> components /*, boolean removePrevComponents*/){
//        if (removePrevComponents){
//            this.components = null;
            this.components = new ArrayList<>();
//        }
        if (components != null) {
            for (Component component : components) {
                this.components.add(component);
            }
        }
        JLabel label = new JLabel();
        label.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

//        if (this.backgroundImageName!= null && !this.backgroundImageName.equals(imageName)) {
        if (imageName == null){
            label.setBackground(Color.black);
        }else {
            this.backgroundImageName = imageName;
            ImageIcon background = new ImageIcon(backgroundImageName);
            label.setIcon(background);
        }
//        }

        if (this.components != null) {
            for (Component component : this.components) {
                label.add(component, JLayeredPane.DRAG_LAYER);
            }
        }

        label.setOpaque(true);
        this.add(label);


//        JPanel panel = new JPanel();
//        panel.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//        panel.add(label);
//        panel.setOpaque(true);
//
//        JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.add(label,JLayeredPane.DEFAULT_LAYER);
//        layeredPane.setOpaque(true);
        this.setVisible(true);

    }
//    public void paint(Graphics g){
//        Graphics2D graphics2D = (Graphics2D) g;
////        if (backgroundImageName != null) {
//            System.out.println("...");
////            if (!hasBackgroundImage) {
////                Image background = new ImageIcon(backgroundImageName).getImage();
////                graphics2D.drawImage(background, this.getX(), this.getY(), null);
////            }
//
//            if (components != null) {
//                for (Component component : components) {
//                    this.add(component, JLayeredPane.DRAG_LAYER);
//                }
//            }
////        }
//
////        graphics2D.drawLine(0,0,100,200);
//    }
    public Point getFrameCenter() {
        return frameCenter;
    }

//    public void setComponents(ArrayList<Component> components) {
//        this.components = components;
//    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
