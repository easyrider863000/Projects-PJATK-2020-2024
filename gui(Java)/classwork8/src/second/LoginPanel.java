package second;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JFrame {
    public JPanel getMainPanel() {
        return panel1;
    }
    private JTextField textField1;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton submitButton;

    public LoginPanel(){
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log = textField1.getText();
                String pass = String.valueOf(passwordField1.getPassword());
                if(pass.equals("pjatk") && log.equals("gui")){
                    JOptionPane.showMessageDialog(null, "You are logged in");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Password or login is not correct", "Error", 4);
                }

            }
        };
        submitButton.addActionListener(al);
    }

}
