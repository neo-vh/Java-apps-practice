import javax.swing.*;
import java.awt.*;
//import java.util.*;
import java.awt.event.*;

public class GUI {

    // Color Scheme

    private static Color BACKGROUND = new Color(30, 30, 30);
    private static Color TEXT_COLOR = new Color(255, 255, 255);
    private static Color PANEL_BACKGROUND = new Color(50, 50, 50);
    private static Color FIELD_COLOR = new Color(70, 70, 70);
    private static Color INVALID = new Color(40, 0, 0);
    private static Color INVALID_BORDER = new Color(60, 0, 0);
    private static final String NAME_PLACEHOLDER = "Enter Name..";
    private static final String SURNAME_PLACEHOLDER = "Enter Surname..";
    private static final String PASS_PLACEHOLDER = "Enter Password..";

    public static boolean validate(String input, String placeholder) {
        return (!input.isBlank() && !input.equals(placeholder));
    }
    
    public static void renderGUI() {

        Toolkit tk = Toolkit.getDefaultToolkit();

        // Sign Up / Login Menu

        JFrame signUp = new JFrame();
        signUp.setLayout(null);
        signUp.setSize(new Dimension(400, 400));
        signUp.setUndecorated(true);
        signUp.getContentPane().setBackground(BACKGROUND);
        signUp.setLocationRelativeTo(null);
        signUp.setAutoRequestFocus(true);

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

        JPanel signUpPanel = new JPanel(new GridBagLayout()); 
        signUpPanel.setFocusable(true);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        signUpPanel.setBounds(
            (signUp.getWidth() - 350) / 2,
            (signUp.getHeight() - 250) / 2,
            350, 250
        );

        JTextField name = new JTextField(NAME_PLACEHOLDER, 10);

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        
        name.setForeground(TEXT_COLOR);
        name.setBackground(FIELD_COLOR);
        name.setBorder(BorderFactory.createEmptyBorder());

        name.addFocusListener(new FocusAdapter() {
            @Override 
            public void focusGained(FocusEvent e) {
                name.setBackground(FIELD_COLOR);
                name.setBorder(BorderFactory.createEmptyBorder());
                if (name.getText().equals(NAME_PLACEHOLDER)) {
                    name.setText("");
                }
            }
        });

        signUpPanel.add(name, c);

        JTextField surname = new JTextField(SURNAME_PLACEHOLDER, 10);

        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;

        surname.setBackground(FIELD_COLOR);
        surname.setForeground(TEXT_COLOR);
        surname.setBorder(BorderFactory.createEmptyBorder());

        surname.addFocusListener(new FocusAdapter() {
            @Override 
            public void focusGained(FocusEvent e) {
                surname.setBackground(FIELD_COLOR);
                surname.setBorder(BorderFactory.createEmptyBorder());
                if (surname.getText().equals(SURNAME_PLACEHOLDER)) {
                    surname.setText("");
                }
            }
        });

        signUpPanel.add(surname, c);

        JTextField password = new JTextField(PASS_PLACEHOLDER, 20);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        password.setBackground(FIELD_COLOR);
        password.setForeground(TEXT_COLOR);
        password.setBorder(BorderFactory.createEmptyBorder());

        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                password.setBackground(FIELD_COLOR);
                password.setBorder(BorderFactory.createEmptyBorder());
                if (password.getText().equals(PASS_PLACEHOLDER)) {
                    password.setText("");
                }
            }
        });

        signUpPanel.add(password, c);
        SwingUtilities.invokeLater(signUpPanel::requestFocusInWindow);


        JButton confirm = new JButton("Confirm");

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;

        confirm.setBackground(FIELD_COLOR);
        confirm.setForeground(TEXT_COLOR);
        signUpPanel.add(confirm, c);
        confirm.setBorder(BorderFactory.createLineBorder(FIELD_COLOR));

        // Main App

        JFrame mainFrame = new JFrame();
        mainFrame.setSize(tk.getScreenSize());
        mainFrame.setUndecorated(true);
        mainFrame.getContentPane().setBackground(BACKGROUND);
        mainFrame.setResizable(false);
        mainFrame.setAutoRequestFocus(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        JMenuBar mainMenuBar = new JMenuBar();
        mainMenuBar.setBackground(BACKGROUND);
        mainMenuBar.setForeground(TEXT_COLOR);
        mainMenuBar.setBorder(BorderFactory.createEmptyBorder());

        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setBackground(PANEL_BACKGROUND);
        optionsMenu.setForeground(TEXT_COLOR);
        mainMenuBar.add(optionsMenu);

        JMenuItem mainClose = new JMenuItem("Close");
        mainClose.setBackground(PANEL_BACKGROUND);
        mainClose.setForeground(TEXT_COLOR);
        optionsMenu.add(mainClose);

        mainClose.addActionListener(e -> {
            mainFrame.dispose();
        }); 

        JMenu themesMenu = new JMenu("Themes");
        themesMenu.setBackground(PANEL_BACKGROUND);
        themesMenu.setForeground(TEXT_COLOR);
        mainMenuBar.add(themesMenu);

        JMenuItem darkTheme = new JMenuItem("Dark");
        darkTheme.setBackground(PANEL_BACKGROUND);
        darkTheme.setForeground(TEXT_COLOR);
        themesMenu.add(darkTheme);

        JMenuItem pastelTheme = new JMenuItem("Pastel");
        pastelTheme.setBackground(PANEL_BACKGROUND);
        pastelTheme.setForeground(TEXT_COLOR);
        themesMenu.add(pastelTheme);

        JMenuItem navyTheme = new JMenuItem("Navy");
        navyTheme.setBackground(PANEL_BACKGROUND);
        navyTheme.setForeground(TEXT_COLOR);
        themesMenu.add(navyTheme);

        JMenuItem lightTheme = new JMenuItem("Light");
        lightTheme.setBackground(PANEL_BACKGROUND);
        lightTheme.setForeground(TEXT_COLOR);
        themesMenu.add(lightTheme);

        JPanel balancePanel = new JPanel(new GridBagLayout());
        balancePanel.setBackground(PANEL_BACKGROUND);
        balancePanel.setForeground(TEXT_COLOR);

        balancePanel.setBounds(
            (mainFrame.getWidth() - 400) / 2,
            (mainFrame.getHeight() - 100) / 2,
            400, 100
        );

        // main frame components should be placed above this comment

        Runnable applyTheme = () -> {
            mainFrame.getContentPane().setBackground(BACKGROUND);
            mainMenuBar.setBackground(BACKGROUND);
            mainMenuBar.setForeground(TEXT_COLOR);
            optionsMenu.setBackground(PANEL_BACKGROUND);
            optionsMenu.setForeground(TEXT_COLOR);
            themesMenu.setBackground(PANEL_BACKGROUND);
            themesMenu.setForeground(TEXT_COLOR);
            mainClose.setBackground(PANEL_BACKGROUND);
            mainClose.setForeground(TEXT_COLOR);
            darkTheme.setBackground(PANEL_BACKGROUND);
            darkTheme.setForeground(TEXT_COLOR);
            pastelTheme.setBackground(PANEL_BACKGROUND);
            pastelTheme.setForeground(TEXT_COLOR);
            navyTheme.setBackground(PANEL_BACKGROUND);
            navyTheme.setForeground(TEXT_COLOR);
            lightTheme.setBackground(PANEL_BACKGROUND);
            lightTheme.setForeground(TEXT_COLOR);
            balancePanel.setBackground(PANEL_BACKGROUND);
            balancePanel.setForeground(TEXT_COLOR);
        };

        darkTheme.addActionListener(e -> {
            BACKGROUND = new Color(30, 30, 30);
            TEXT_COLOR = new Color(255, 255, 255);
            PANEL_BACKGROUND = new Color(50, 50, 50);
            FIELD_COLOR = new Color(70, 70, 70);
            INVALID = new Color(40, 0, 0);
            INVALID_BORDER = new Color(120, 30, 30);
            applyTheme.run();
        });

        pastelTheme.addActionListener(e -> {
            BACKGROUND = new Color(188, 207, 188);
            TEXT_COLOR = new Color(38, 58, 42);
            PANEL_BACKGROUND = new Color(160, 185, 162);
            FIELD_COLOR = new Color(211, 225, 210);
            INVALID = new Color(232, 186, 186);
            INVALID_BORDER = new Color(172, 78, 78);
            applyTheme.run();
        });

        navyTheme.addActionListener(e -> {
            BACKGROUND = new Color(16, 24, 39);
            TEXT_COLOR = new Color(230, 237, 247);
            PANEL_BACKGROUND = new Color(29, 42, 64);
            FIELD_COLOR = new Color(38, 56, 82);
            INVALID = new Color(74, 32, 40);
            INVALID_BORDER = new Color(193, 99, 112);
            applyTheme.run();
        });

        lightTheme.addActionListener(e -> {
            BACKGROUND = new Color(244, 241, 232);
            TEXT_COLOR = new Color(52, 52, 48);
            PANEL_BACKGROUND = new Color(229, 224, 212);
            FIELD_COLOR = new Color(255, 253, 247);
            INVALID = new Color(245, 218, 218);
            INVALID_BORDER = new Color(183, 90, 90);
            applyTheme.run();
        });
        
        mainFrame.setJMenuBar(mainMenuBar);
        mainFrame.add(balancePanel);

        // confirm Button action (needs to be after mainFrame created due to mainFrame needing to open once pressed)

        confirm.addActionListener(e -> {
            if (validate(name.getText(), NAME_PLACEHOLDER) && validate(surname.getText(), SURNAME_PLACEHOLDER) && validate(password.getText(), PASS_PLACEHOLDER)) { // check if all fields have been filled
                signUp.dispose();
                mainFrame.setVisible(true);
            }
            else {
                if (!validate(name.getText(), NAME_PLACEHOLDER)) {
                    name.setBackground(INVALID);
                    name.setBorder(BorderFactory.createLineBorder(INVALID_BORDER));
                }
                if (!validate(surname.getText(), SURNAME_PLACEHOLDER)) {
                    surname.setBackground(INVALID);
                    surname.setBorder(BorderFactory.createLineBorder(INVALID_BORDER));
                }
                if (!validate(password.getText(), PASS_PLACEHOLDER)) {
                    password.setBackground(INVALID);
                    password.setBorder(BorderFactory.createLineBorder(INVALID_BORDER));
                }
            }
        });


        // Make Signup visible
        signUpPanel.setBackground(PANEL_BACKGROUND);
        signUp.add(signUpPanel);
        signUp.setVisible(true);



    }

    public static void main(String[] args) {
        renderGUI();
    }


}
