package main;

import javax.swing.*;
import java.io.IOException;

public class Main {
 public static void main(String[] args) throws IOException {

     JFrame window = new JFrame("simple chess");
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setResizable(false);

     GamePanel gamepanel = new GamePanel();
     window.add(gamepanel);
     window.pack();

     window.setLocationRelativeTo(null);
     window.setVisible(true);
     gamepanel.launchGame();
 }
}
