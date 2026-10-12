import javax.swing.*;
import java.util.*;

public class GUI {
    
    public static void RenderGUI(int screenx, int screeny) {
        
        if (screenx == 0 && screeny == 0) {
            screenx = 400;
            screeny = 300;
        }

        JFrame frame = new JFrame();

        JLabel labelT = new JLabel("testing");
        
        frame.add(labelT);
        
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        RenderGUI(100, 100);
    }

}
