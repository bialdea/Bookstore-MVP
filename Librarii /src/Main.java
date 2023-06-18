
import View.*;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {


            SwingUtilities.invokeLater(() -> {
                    try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                            e.printStackTrace();
                    }
                    LoginForm loginForm = new LoginForm();
                    loginForm.initialize(); 
                    loginForm.setVisible(true);
            });

    }


}