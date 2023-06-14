package main;

import javax.swing.JFrame;

public class Main extends JFrame {
   //main class -> wo alles beginnt :)

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("PROJECTG_");

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }


}
