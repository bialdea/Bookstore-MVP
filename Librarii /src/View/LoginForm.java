package View;
import java.awt.*;
import java.sql.SQLException;

import Presenter.LoginPresenter;

import javax.swing.*;

public class LoginForm extends JFrame implements ILoginForm {
    final private Font mainFont = new Font("Segoe Print", Font.BOLD, 20);
    final private Font font1 = new Font("Segoe Print", Font.BOLD, 18);
    final private Font font2 = new Font("Times New Roman", Font.BOLD, 16);
    JTextField tfUser;
    JPasswordField pfPassword;

    public void initialize() {
        JLabel lbLoginForm = new JLabel("Autentificare utilizator", SwingConstants.CENTER);
        lbLoginForm.setFont(mainFont);

        JLabel lbUser = new JLabel("Utilizator");
        lbUser.setFont(font1);

        tfUser = new JTextField();
        tfUser.setFont(font1);

        JLabel lbPassword = new JLabel("Parola");
        lbPassword.setFont(font1);

        pfPassword = new JPasswordField();
        pfPassword.setFont(font1);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 1, 20, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formPanel.add(lbLoginForm);
        formPanel.add(lbUser);
        formPanel.add(tfUser);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);
        LoginPresenter p = new LoginPresenter(this);


        JButton btnLogin = new JButton("Autentificare");
        btnLogin.setFont(font2);
        btnLogin.addActionListener(e -> {
            try {
                p.autentificare();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        JButton btnCancel = new JButton("Anulare");
        btnCancel.setFont(font2);
        btnCancel.addActionListener(e -> dispose());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);

        add(formPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Formular autentificare");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 500);
        setMinimumSize(new Dimension(350, 450));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public String getUser() {
        return tfUser.getText();
    }

    @Override
    public String getParola() {
        return pfPassword.getText();
    }
    @Override
    public void mesajEroare() {
        JOptionPane.showMessageDialog(LoginForm.this,
                "Model.Utilizator sau parola invalidă",
                "Încearcă din nou",
                JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void setAdministratorView(String user) {
        AdministratorFrame administratorFrame = new AdministratorFrame();
        dispose();
    }

    @Override
    public void setManagerView(String user) {
        ManagerFrame managerFrame = new ManagerFrame();
        dispose();
    }

    @Override
    public void setAngajatView(String user, int id_librarie) {
        AngajatFrame.id_librarie = id_librarie;
        AngajatFrame angajatFrame = new AngajatFrame();
        dispose();
    }
}

