import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class GUI {

    static Toolkit tk = Toolkit.getDefaultToolkit();

    JFrame instance = null;
    
    public static JFrame renderGUI() {

        JFrame frame = new JFrame();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem close = new JMenuItem("Close");
        menu.add(close);

        close.addActionListener(e -> frame.dispose());

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(tk.getScreenSize());
        frame.setUndecorated(true);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);

        return frame;

    }

    public GUI() {
        this.instance = renderGUI();
    }

    

    public void end() {

        this.instance.dispose();
        
    }

}
