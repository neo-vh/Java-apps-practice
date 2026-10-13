import java.awt.Toolkit;
import javax.swing.*;


public class GUI {

    static Toolkit tk = Toolkit.getDefaultToolkit();

    
    public static void renderGUI() {

        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(tk.getScreenSize());
        frame.setUndecorated(true);

        frame.setVisible(true);

    }

}
