import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.xml.crypto.dsig.SignatureProperties;

public class GUI {

    // Color Scheme

    private static final Color BACKGROUND = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color PANEL_BACKGROUND = new Color(50, 50, 50);
    
    public static void renderGUI() {

        Toolkit tk = Toolkit.getDefaultToolkit();

        JFrame signUp = new JFrame();
        signUp.setLayout(null);
        signUp.setSize(new Dimension(400, 400));
        signUp.setUndecorated(true);
        signUp.getContentPane().setBackground(BACKGROUND);
        signUp.setLocationRelativeTo(null);

        JMenuBar singUpBar = new JMenuBar();
        singUpBar.setOpaque(true);
        singUpBar.setBackground(BACKGROUND);
        singUpBar.setBorder(BorderFactory.createEmptyBorder());

        JMenu signUpOptions = new JMenu("Options");
        signUpOptions.setBackground(BACKGROUND);
        signUpOptions.setForeground(TEXT_COLOR);
        singUpBar.add(signUpOptions);

        JMenuItem signUpClose = new JMenuItem("Close");
        signUpClose.setBackground(PANEL_BACKGROUND);
        signUpClose.setForeground(TEXT_COLOR);
        signUpOptions.add(signUpClose);

        signUpClose.addActionListener(e -> {
            signUp.dispose();
        });

        signUp.setJMenuBar(singUpBar);

        JFrame mainFrame = new JFrame();
        mainFrame.setSize(tk.getScreenSize());
        mainFrame.setUndecorated(true);
        mainFrame.getContentPane().setBackground(BACKGROUND);
        mainFrame.setResizable(false);
        mainFrame.setAutoRequestFocus(true);

        JPanel signUpPanel = new JPanel(new GridBagLayout());
        signUpPanel.setBounds(
            (signUp.getWidth() - 350) / 2,
            (signUp.getHeight() - 200) / 2,
            350, 250
        );
        signUpPanel.setBackground(PANEL_BACKGROUND);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setForeground(TEXT_COLOR);
        signUpLabel.setBounds(
            (signUp.getWidth() - 100) / 2,
            (signUp.getHeight() - 50),
            100, 20
        );

        signUp.add(signUpLabel);
        signUp.add(signUpPanel);
        signUp.setVisible(true);
    }

    public static void main(String[] args) {
        renderGUI();
    }


}
