package com.tylervp.util;

import java.net.URL;

import java.awt.*;
import javax.swing.*;

public class SpashScreen {
    private static JFrame window;

    public SpashScreen() {}

    public static void start() {
        try {
            window = new JFrame();

            ImageIcon imageIcon = new ImageIcon(new URL("https://github.com/tvanprooyen/mbm/raw/main/images/MBM%20Header.jpg")); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(957, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);  // transform it back

            window.getContentPane().add(
                new JLabel("", imageIcon, SwingConstants.CENTER));
            window.setSize(957, 500);

            window.pack();
            window.setLocationRelativeTo(null);

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        } catch (Exception e) {

        }
    }

    public static void exit() {
        window.setVisible(false);
        window.dispose();
    }

    /* public static void main (String args[]) {
        start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exit();
    } */
}

/* import java.awt.*;
import java.awt.event.*;

public class SpashScreen extends Frame implements ActionListener {

    static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"foo", "bar", "baz"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading "+comps[(frame/5)%3]+"...", 120, 150);
    }
    public SpashScreen() {
        super("MBM Loading...");
        setSize(300, 200);
        setLayout(new BorderLayout());
        Menu m1 = new Menu("File");
        MenuItem mi1 = new MenuItem("Exit");
        m1.add(mi1);
        mi1.addActionListener(this);
        this.addWindowListener(closeWindow);

        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        mb.add(m1);
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<100; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
        setVisible(true);
        toFront();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
    
    private static WindowListener closeWindow = new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
        }
    };
    
    public static void main (String args[]) {
        SpashScreen test = new SpashScreen();
    }
}
 */