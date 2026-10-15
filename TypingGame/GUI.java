import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class GUI {

    static Toolkit tk = Toolkit.getDefaultToolkit();

    // color scheme by copilot (ek nie lus vir aai nie ekkie doktor design nie)
    private static Color BACKGROUND = new Color(15, 23, 42);
    private static Color PANEL_BACKGROUND = new Color(30, 41, 59);
    private static Color TEXT_COLOR = new Color(226, 232, 240);
    private static Color MUTED_TEXT_COLOR = new Color(148, 163, 184);
    private static Color BORDER_COLOR = new Color(51, 65, 85);

    enum PromptStyles {
        WORDS, LETTERS
    }

    JFrame instance = null;
    static String targetString = prompts.randomWords(4);

    
    public static JFrame renderGUI() {

        JFrame frame = new JFrame();

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(tk.getScreenSize());
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setBackground(BACKGROUND);

        class GameStates {
            int currentIndex = 0;
            int totalTyped = 0;
            long startTime = 0;
            PromptStyles currentStyle = PromptStyles.WORDS;  
        }

        GameStates states = new GameStates();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(true);
        menuBar.setBackground(BACKGROUND);
        menuBar.setBorder(BorderFactory.createEmptyBorder());

        JMenu menu = new JMenu("Options");
        menu.setOpaque(true);
        menu.setBackground(BACKGROUND);
        menu.setForeground(TEXT_COLOR);
        menuBar.add(menu);

        JMenuItem close = new JMenuItem("Close");
        close.setBackground(PANEL_BACKGROUND);
        close.setForeground(TEXT_COLOR);
        menu.add(close);
        
        JMenu promptStyle = new JMenu("Prompt Style");
        promptStyle.setBackground(BACKGROUND);
        promptStyle.setForeground(TEXT_COLOR);
        menuBar.add(promptStyle);

        JMenuItem style = new JMenuItem("Change Style (word/letters)");
        style.setBackground(PANEL_BACKGROUND);
        style.setForeground(TEXT_COLOR);
        promptStyle.add(style);

        JMenu windowStyles = new JMenu("Themes");
        windowStyles.setBackground(BACKGROUND);
        windowStyles.setForeground(TEXT_COLOR);
        menuBar.add(windowStyles);

        JMenuItem navyTheme = new JMenuItem("Navy");
        navyTheme.setBackground(PANEL_BACKGROUND);
        navyTheme.setForeground(TEXT_COLOR);
        windowStyles.add(navyTheme);

        JMenuItem lightTheme = new JMenuItem("Light");
        lightTheme.setBackground(PANEL_BACKGROUND);
        lightTheme.setForeground(TEXT_COLOR);
        windowStyles.add(lightTheme);

        JMenuItem darkTheme = new JMenuItem("Dark");
        darkTheme.setBackground(PANEL_BACKGROUND);
        darkTheme.setForeground(TEXT_COLOR);
        windowStyles.add(darkTheme);

        style.addActionListener(e -> {
            
            if (states.currentStyle == PromptStyles.WORDS) {
                states.currentStyle = PromptStyles.LETTERS;
            }
            else {
                states.currentStyle = PromptStyles.WORDS;
            }

        });

        close.addActionListener(e -> frame.dispose());

        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(PANEL_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 65, 85)),
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));


        JTextPane textPane = new JTextPane();
        textPane.setText(targetString);
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setOpaque(false);
        textPane.setFont(new Font("Consolas", Font.PLAIN, 24));
        textPane.setForeground(TEXT_COLOR);

        JLabel tip = new JLabel("Type the prompt shown Exactly");
        tip.setForeground(MUTED_TEXT_COLOR);

        Runnable applyTheme = () -> {
            frame.getContentPane().setBackground(BACKGROUND);
            frame.setBackground(BACKGROUND);
            panel.setBackground(PANEL_BACKGROUND);
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
            ));
            textPane.setForeground(TEXT_COLOR);
            tip.setForeground(MUTED_TEXT_COLOR);
            menuBar.setBackground(BACKGROUND);
            menuBar.setForeground(TEXT_COLOR);

            for (JMenu themeMenu : new JMenu[] { menu, promptStyle, windowStyles }) {
                themeMenu.setOpaque(true);
                themeMenu.setBackground(BACKGROUND);
                themeMenu.setForeground(TEXT_COLOR);
                themeMenu.getPopupMenu().setOpaque(true);
                themeMenu.getPopupMenu().setBackground(PANEL_BACKGROUND);
            }

            for (JMenuItem item : new JMenuItem[] {
                close, style, navyTheme, lightTheme, darkTheme
            }) {
                item.setOpaque(true);
                item.setBackground(PANEL_BACKGROUND);
                item.setForeground(TEXT_COLOR);
            }

            frame.repaint();
        };

        darkTheme.addActionListener(e -> {
            BACKGROUND = new Color(17, 19, 24);
            PANEL_BACKGROUND = new Color(30, 35, 43);
            TEXT_COLOR = new Color(230, 233, 238);
            MUTED_TEXT_COLOR = new Color(155, 164, 178);
            BORDER_COLOR = new Color(52, 60, 72);
            applyTheme.run();
        });

        lightTheme.addActionListener(e -> {
            BACKGROUND = new Color(243, 246, 251);
            PANEL_BACKGROUND = new Color(255, 255, 255);
            TEXT_COLOR = new Color(23, 32, 51);
            MUTED_TEXT_COLOR = new Color(100, 116, 139);
            BORDER_COLOR = new Color(213, 222, 234);
            applyTheme.run();
        });

        navyTheme.addActionListener(e -> {
            BACKGROUND = new Color(15, 23, 42);
            PANEL_BACKGROUND = new Color(30, 41, 59);
            TEXT_COLOR = new Color(226, 232, 240);
            MUTED_TEXT_COLOR = new Color(148, 163, 184);
            BORDER_COLOR = new Color(51, 65, 85);
            applyTheme.run();
        });

        panel.setBounds(
            (frame.getWidth() - 600) / 2,
            (frame.getHeight() - 400) / 2,
            600, 400
        );
        

        frame.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyTyped(KeyEvent e) {
                
                char typed = e.getKeyChar();
                char expected = targetString.charAt(states.currentIndex);

                SimpleAttributeSet incorrectStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(incorrectStyle, new Color(254, 226, 226));
                StyleConstants.setBackground(incorrectStyle, new Color(127, 29, 29));

                if (typed != expected) {
                    textPane.getStyledDocument().setCharacterAttributes(
                        states.currentIndex, 1, incorrectStyle, false);
                    states.totalTyped++;
                    return;
                }

                SimpleAttributeSet correctStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(correctStyle, new Color(220, 252, 231));
                StyleConstants.setBackground(correctStyle, new Color(20, 83, 45));

                textPane.getStyledDocument().setCharacterAttributes(
                    states.currentIndex, 1, correctStyle, false
                );

                states.currentIndex++;
                states.totalTyped++;

                if (states.currentIndex >= targetString.length()) {

                    long endTime = System.nanoTime();

                    double elpasedMinutes = (endTime - states.startTime) / 60_000_000_000.0;
                    double wpm = (targetString.length() / 5.0) / elpasedMinutes;
                    double accuracy = (targetString.length() * 100.0) / states.totalTyped;

                    if (states.currentStyle == PromptStyles.WORDS) {
                        targetString = prompts.randomWords(4);
                    }
                    else if (states.currentStyle == PromptStyles.LETTERS) {
                        targetString = prompts.randomLetters(4, 4);
                    }

                    textPane.setText(targetString);
                    states.currentIndex = 0;
                    tip.setText(String.format("WPM : %.1f | Accuracy %.1f%%", wpm, accuracy));
                    states.startTime = System.nanoTime();
                    states.totalTyped = 0;
                }
            }
        });

        // Nie die shit geskryfie maar ek nie lus om dit te probeer uitfigurie
        GridBagConstraints textConstraints = new GridBagConstraints();
        textConstraints.gridx = 0;
        textConstraints.gridy = 0;
        panel.add(textPane, textConstraints);

        GridBagConstraints tipConstraints = new GridBagConstraints();
        tipConstraints.gridx = 0;
        tipConstraints.gridy = 1;
        tipConstraints.insets = new Insets(16, 0, 0, 0);
        panel.add(tip, tipConstraints);

        frame.add(panel);

        System.out.println(String.format("%d;%d", panel.getHeight(), panel.getWidth()));

        frame.setVisible(true);
        states.startTime = System.nanoTime();
        frame.requestFocusInWindow();

        return frame;

    }

    public GUI() {
        this.instance = renderGUI();
    }
    

    public void end() {

        this.instance.dispose();
        
    }

}
