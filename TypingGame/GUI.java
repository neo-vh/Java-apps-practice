import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class GUI {

    static Toolkit tk = Toolkit.getDefaultToolkit();

    JFrame instance = null;
    static String targetString = prompts.randomLetters(4, 4);
    
    public static JFrame renderGUI() {

        JFrame frame = new JFrame();

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(tk.getScreenSize());
        frame.setUndecorated(true);

        int[] currentIndex = {0};
        int[] totalTyped = {0};
        long[] startTime = {0};

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem close = new JMenuItem("Close");
        menu.add(close);

        close.addActionListener(e -> frame.dispose());

        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);


        JTextPane textPane = new JTextPane();
        textPane.setText(targetString);
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setOpaque(false);
        textPane.setFont(new Font("Arial", Font.PLAIN, 24));
        textPane.setForeground(Color.BLACK);

        JLabel tip = new JLabel("Type the prompt shown Exactly");

        panel.setBounds(
            (frame.getWidth() - 600) / 2,
            (frame.getHeight() - 400) / 2,
            600, 400
        );
        

        frame.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyTyped(KeyEvent e) {
                
                char typed = e.getKeyChar();
                char expected = targetString.charAt(currentIndex[0]);

                SimpleAttributeSet incorrectStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(incorrectStyle, new Color(128, 0, 0));
                StyleConstants.setBackground(incorrectStyle, new Color(240, 190, 190));

                if (typed != expected) {
                    textPane.getStyledDocument().setCharacterAttributes(
                        currentIndex[0], 1, incorrectStyle, false);
                    totalTyped[0]++;
                    return;
                }

                SimpleAttributeSet correctStyle = new SimpleAttributeSet();
                StyleConstants.setForeground(correctStyle, new Color(0, 128, 0));
                StyleConstants.setBackground(correctStyle, new Color(190, 240, 190));

                textPane.getStyledDocument().setCharacterAttributes(
                    currentIndex[0], 1, correctStyle, false
                );

                currentIndex[0]++;
                totalTyped[0]++;

                if (currentIndex[0] >= targetString.length()) {

                    long endTime = System.nanoTime();

                    double elpasedMinutes = (endTime - startTime[0]) / 60_000_000_000.0;
                    double wpm = (targetString.length() / 5.0) / elpasedMinutes;
                    double accuracy = (targetString.length() * 100.0) / totalTyped[0];

                    targetString = prompts.randomLetters(4, 4);
                    textPane.setText(targetString);
                    currentIndex[0] = 0;
                    tip.setText(String.format("WPM : %.1f | Accuracy %.1f%%", wpm, accuracy));
                    startTime[0] = System.nanoTime();
                    totalTyped[0] = 0;
                }
            }
        });

        frame.setBackground(new Color(230, 240, 255));


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
        startTime[0] = System.nanoTime();
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
