package main;

import javax.swing.JFrame;

public class Main extends JFrame {
   //main class -> wo alles beginnt :)
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("69");

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }


}
