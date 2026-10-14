import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;


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

        frame.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyTyped(KeyEvent e) {
                if (currentIndex[0] >= targetString.length()) {
                    targetString = prompts.randomLetters(4, 4);
                    return;
                }
                char typed = e.getKeyChar();
                char expected = targetString.charAt(currentIndex[0]);

                if (typed == expected) {
                    currentIndex[0]++;

                    if (currentIndex[0] >= targetString.length()) {
                        System.out.println("target completed!!");
                    }
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem close = new JMenuItem("Close");
        menu.add(close);

        close.addActionListener(e -> frame.dispose());

        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.GRAY);

        JLabel label = new JLabel(targetString);
        label.setFont(new Font("Arial", Font.PLAIN, 24));

        panel.setBounds(
            (frame.getWidth() - 600) / 2,
            (frame.getHeight() - 400) / 2,
            600, 400
        );

        //label.setBounds(
        //   (panel.getWidth() - 200) / 2,
        //    (panel.getHeight() - 100) / 2,
        //    200, 100
        //);
        label.setForeground(Color.BLACK);

        panel.add(label);
        frame.add(panel);

        System.out.println(String.format("%s;%s", panel.getHeight(), panel.getWidth()));

        frame.setVisible(true);
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
