import javax.swing.*;
import java.awt.*;
//import java.util.*;
import java.awt.event.*;

public class GUI {

    // Color Scheme

    private static final Color BACKGROUND = new Color(30, 30, 30);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final Color PANEL_BACKGROUND = new Color(50, 50, 50);
    private static final Color FIELD_COLOR = new Color(70, 70, 70);
    private static final Color INVALID = new Color(40, 0, 0);
    private static final Color INVALID_BORDER = new Color(60, 0, 0);
    
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
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        signUpPanel.setBounds(
            (signUp.getWidth() - 350) / 2,
            (signUp.getHeight() - 250) / 2,
            350, 250
        );

        JTextField name = new JTextField(10);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        
        name.setForeground(TEXT_COLOR);
        name.setBackground(FIELD_COLOR);

        name.addFocusListener(new FocusAdapter() {
            @Override 
            public void focusGained(FocusEvent e) {
                name.setBackground(FIELD_COLOR);
                name.setBorder(BorderFactory.createEmptyBorder());
            }
        });

        signUpPanel.add(name, c);

        JTextField surname = new JTextField(10);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;

        surname.setBackground(FIELD_COLOR);
        surname.setForeground(TEXT_COLOR);

        surname.addFocusListener(new FocusAdapter() {
            @Override 
            public void focusGained(FocusEvent e) {
                surname.setBackground(FIELD_COLOR);
                surname.setBorder(BorderFactory.createEmptyBorder());
            }
        });

        signUpPanel.add(surname, c);

        JButton confirm = new JButton("Confirm");

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;

        confirm.setBackground(FIELD_COLOR);
        confirm.setForeground(TEXT_COLOR);
        signUpPanel.add(confirm, c);

        confirm.addActionListener(e -> {
            if (! name.getText().equals("") && ! surname.getText().equals("")) {
                signUp.dispose();
                mainFrame.setVisible(true);
            }
            else {
                if (name.getText().isBlank()) {
                    name.setBackground(INVALID);
                    name.setBorder(BorderFactory.createLineBorder(INVALID_BORDER));
                }
                if (surname.getText().isBlank()) {
                    surname.setBackground(INVALID);
                    surname.setBorder(BorderFactory.createLineBorder(INVALID_BORDER));
                }
            }
        });

        signUpPanel.setBackground(PANEL_BACKGROUND);

        signUp.add(signUpPanel);
        signUp.setVisible(true);
    }

    public static void main(String[] args) {
        renderGUI();
    }


}
