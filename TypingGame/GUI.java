import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;

public class GUI {
    
    public static void RenderGUI(int screenx, int screeny) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        JFrame frame = new JFrame();
        
        if (screenx == 0 && screeny == 0) {
            frame.setSize(screenSize);
        }

        else {
            frame.setSize(new Dimension(screenx, screeny));
        }

        JPanel panel = new JPanel();
        JButton Button1 = new JButton("testing");
        panel.add(Button1);
        
        Button1.addActionListener(e -> frame.dispose());

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(null);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        RenderGUI(0, 0);
    }

}
