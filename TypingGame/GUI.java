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

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem close = new JMenuItem("Close");
        menu.add(close);
        menuBar.add(menu);

        close.addActionListener(e -> frame.dispose());

        // text display
        JPanel textDisplay = new JPanel();
        JTextField text = new JTextField("Test", 20);
        textDisplay.setBounds(frame.getWidth() / 2, frame.getHeight() / 2, 250, 50);
        textDisplay.add(text);

        // Components
        frame.setJMenuBar(menuBar);
        frame.add(textDisplay);

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
