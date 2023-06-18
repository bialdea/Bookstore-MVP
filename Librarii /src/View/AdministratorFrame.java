package View;

import Presenter.AdministratorPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class AdministratorFrame extends JFrame implements IAdministratorFrame{
    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane();

    private final JTextField txtUsername = new JTextField();
    private final JTextField txtParola = new JTextField();
    private final JTextField txtTip = new JTextField();
    private final JTextField txtIdUtilizator = new JTextField();
    private final JTextField txtIdLibrarie = new JTextField();

    private final AdministratorPresenter a;

    public AdministratorFrame() {

        JPanel panelAdministrator = new JPanel();
        setContentPane(panelAdministrator);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        a = new AdministratorPresenter(this);
        a.adaugareTabel();


        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> a.adaugareTabel());

        JButton btnAdauga = new JButton("Adauga");
        btnAdauga.addActionListener(e -> {
            try {
                a.adaugareUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnSterge = new JButton("Sterge");
        btnSterge.addActionListener(e -> {
            try {
                a.stergereUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnActualizeaza = new JButton("Actualizeaza");
        btnActualizeaza.addActionListener(e -> {
            try {
                a.actualizareUtilizator();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        panelAdministrator.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel(new GridLayout(5, 2)); // setam un grid layout cu 5 randuri si 2 coloane
        // 5 etichete si 5 campuri text
        JLabel lblUsername = new JLabel("Username:");
        filterPanel.add(lblUsername);
        filterPanel.add(txtUsername);
        JLabel lblParola = new JLabel("Parola:");
        filterPanel.add(lblParola);
        filterPanel.add(txtParola);
        JLabel lblTip = new JLabel("Tip:");
        filterPanel.add(lblTip);
        filterPanel.add(txtTip);
        JLabel lblIdUtilizator = new JLabel("Id Utilizator:");
        filterPanel.add(lblIdUtilizator);
        filterPanel.add(txtIdUtilizator);
        JLabel lblIdLibrarie = new JLabel("Id Librarie: (DOAR PENTRU ANGAJATI)");
        filterPanel.add(lblIdLibrarie);
        filterPanel.add(txtIdLibrarie);
        panelAdministrator.add(filterPanel, BorderLayout.NORTH);

        filterPanel.setBackground(new Color(160, 196, 147));

        // add the table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelAdministrator.add(tablePanel, BorderLayout.CENTER);

        // add the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdauga);
        buttonPanel.add(btnSterge);
        buttonPanel.add(btnActualizeaza);
        buttonPanel.add(btnRefresh);
        buttonPanel.setBackground(new Color(160, 196, 147));
        panelAdministrator.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

    @Override
    public int getidUtilizatorT() {
        return Integer.parseInt(txtIdUtilizator.getText());
    }

    @Override
    public String getNameT() {
        return txtUsername.getText();
    }

    @Override
    public String getPasswordT() {
        return txtParola.getText();
    }

    @Override
    public String getRoleT() {
        return txtTip.getText();
    }

    @Override
    public int getidLibrarieT() {
        return Integer.parseInt(txtIdLibrarie.getText());
    }


}
