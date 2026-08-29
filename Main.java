/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package finalproject;

/**
 *
 * @author pumiphatsukkho
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Welcome welcomeFrame = new Welcome();
        welcomeFrame.setVisible(true);
        welcomeFrame.pack();
        welcomeFrame.setLocationRelativeTo(null);
        /*
        BankerLogin LoginFrame1 = new BankerLogin();
        LoginFrame1.setVisible(false);
        LoginFrame1.pack();
        LoginFrame1.setLocationRelativeTo(null);
        
        UserLogin LoginFrame2 = new UserLogin();
        LoginFrame2.setVisible(false);
        LoginFrame2.pack();
        LoginFrame2.setLocationRelativeTo(null);
        
        ForgotPassword verifyFrame = new ForgotPassword();
        verifyFrame.setVisible(false);
        verifyFrame.pack();
        verifyFrame.setLocationRelativeTo(null);
        
        ChangePassword changeFrame = new ChangePassword();
        changeFrame.setVisible(false);
        changeFrame.pack();
        changeFrame.setLocationRelativeTo(null);
        */
    }  
}
