package GUI;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Login {

    final Dimension screenSize;

    public Login(Dimension screenSize) {

        this.screenSize = screenSize;

    }

    public void renderLogin() {
        
        JFrame mainLoginFrame = new JFrame();

        // main frame settings
        mainLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainLoginFrame.setUndecorated(true);
        mainLoginFrame.setSize(this.screenSize);
        
        // main frame colour
        

    }

} 
