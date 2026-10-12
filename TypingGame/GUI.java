import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI {
    
    public static void RenderGUI(int screenx, int screeny) {

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        
        if (screenx == 0 && screeny == 0) {
            frame.setSize(screenSize);
        }

        else {
            frame.setSize(new Dimension(screenx, screeny));
        }

        JPanel panel = new JPanel(null);
        panel.setSize(new Dimension(300, 600));
        JButton Button1 = new JButton("testing");
        Button1.setBounds(panel.getWidth() / 2, panel.getHeight() / 2, 50, 25);
        panel.add(Button1);

        
        Button1.addActionListener(e -> frame.dispose());

        // Components
        panel.setBounds(frame.getContentPane().getWidth() / 2, frame.getContentPane().getHeight() / 2, frame.getWidth(), frame.getHeight());
        frame.add(panel);

        // Make Frame fullscreen (hide taskbar)
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        RenderGUI(0, 0);
    }

}
