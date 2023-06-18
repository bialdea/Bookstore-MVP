package View;

import Presenter.AngajatPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class AngajatFrame extends ManagerFrame implements IAngajatFrame {

    public static int id_librarie = -1;

    private final AngajatPresenter a;

    private final JTextField txtIdLibrarie;

    private final JTextField txtIdCarteLibrarie = new JTextField();

    private final JTextField txtIdCarte = new JTextField();

    private final JTextField txtDomeniu = new JTextField();

    private final JTextField txtTitlu = new JTextField();

    private final JTextField txtAutor = new JTextField();

    private final JTextField txtDisponibil = new JTextField();

    private final JTextField txtEditura = new JTextField();

    public AngajatFrame() {

        a = new AngajatPresenter(this);
        this.txtIdLibrarie = new JTextField(Integer.toString(id_librarie));
        this.txtIdLibrarie.setFocusable(false);

        JButton btnAdauga = new JButton("Adauga");
        btnAdauga.addActionListener(e -> {
            try {
                a.adaugareCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnActualizeaza = new JButton("Actualizeaza");
        btnActualizeaza.addActionListener(e -> {
            try {
                    a.actualizareCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnSterge = new JButton("Sterge");
        btnSterge.addActionListener(e -> {
            try {
                a.stergereCarteLibrarie();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton btnCauta = new JButton("Cauta");
        btnCauta.addActionListener(e -> a.readaugareTabel());

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> a.adaugareTabel());
        JButton btnCuratare = new JButton("Curatare");
        btnCuratare.addActionListener(e -> a.curataCampuri());

        JPanel panelAngajat = new JPanel();
        setContentPane(panelAngajat);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panelAngajat.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Criteriu:"));
        filterPanel.add(comboCriteriu);
        filterPanel.add(new JLabel("Filtru:"));
        txtFiltru.setPreferredSize(new Dimension( 70, 25)); // setam dimensiunea campului text
        filterPanel.add(txtFiltru);
        filterPanel.add(btnCauta);
        filterPanel.add(btnCuratare);
        panelAngajat.add(filterPanel, BorderLayout.WEST);
        filterPanel.setBackground(new Color(180, 241, 233)); // culoarea crem, in RGB

        JPanel addPanel = new JPanel(new GridLayout(7, 4));

        JLabel lblIdLibrarie = new JLabel("IdLibrarie:");
        addPanel.add(lblIdLibrarie);
        addPanel.add(txtIdLibrarie);

        JLabel lblIdCarteLibrarie = new JLabel("IdCarteLibrarie:");
        addPanel.add(lblIdCarteLibrarie);
        addPanel.add(txtIdCarteLibrarie);

        JLabel lblIdCarte = new JLabel("IdCarte:");
        addPanel.add(lblIdCarte);
        addPanel.add(txtIdCarte);

        JLabel lblDomeniu = new JLabel("Domeniu:");
        addPanel.add(lblDomeniu);
        addPanel.add(txtDomeniu);

        JLabel lblTitlu = new JLabel("Titlu:");
        addPanel.add(lblTitlu);
        addPanel.add(txtTitlu);

        JLabel lblAutor = new JLabel("Autor:");
        addPanel.add(lblAutor);
        addPanel.add(txtAutor);

        JLabel lblPret = new JLabel("Pret:");
        addPanel.add(lblPret);
        JTextField txtPret = new JTextField();
        addPanel.add(txtPret);

        JLabel lblDisponibil = new JLabel("Disponibil:");
        addPanel.add(lblDisponibil);
        addPanel.add(txtDisponibil);

        JLabel lblEditura = new JLabel("Editura:");
        addPanel.add(lblEditura);
        addPanel.add(txtEditura);
        addPanel.add(Box.createHorizontalGlue());

        addPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        addPanel.add(btnAdauga);
        addPanel.add(btnSterge);
        addPanel.add(btnActualizeaza);
        btnAdauga.setPreferredSize(new Dimension( 40, 25));
        btnSterge.setPreferredSize(new Dimension( 40, 25));
        btnActualizeaza.setPreferredSize(new Dimension( 40, 25));

        addPanel.setBackground(new Color(180, 238, 217));


        panelAngajat.add(addPanel, BorderLayout.NORTH);


        // add the table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelAngajat.add(tablePanel, BorderLayout.CENTER);
        panelAngajat.setSize(800, 700);

        // add the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        panelAngajat.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(new Color(180, 238, 217));

    }

    @Override
    public String getCriteriu(){String data = null;
        Object selectedItem = comboCriteriu.getSelectedItem();
        if (selectedItem != null) {
            data = selectedItem.toString();
        }
        return data;}
    @Override
    public String getFiltru(){return txtFiltru.getText();}

    @Override
    public void setFiltru(String filtru){txtFiltru.setText(filtru);}

    @Override
    public int getidCarteLibrarieT() {
        return Integer.parseInt(txtIdCarteLibrarie.getText());
    }

    @Override
    public int getidCarteT() {
        return Integer.parseInt(txtIdCarte.getText());
    }

    @Override
    public int getidLibrarieT() {
        return Integer.parseInt(txtIdLibrarie.getText());
    }

    @Override
    public String getDomeniuT() {
        return txtDomeniu.getText();
    }

    @Override
    public String getTitluT() {
        return txtTitlu.getText();    }

    @Override
    public String getautorT() {
        return txtAutor.getText();
    }

    @Override
    public float getpretT() {
        return Float.parseFloat(txtIdLibrarie.getText());
    }

    @Override
    public int getdisponibilT() {
        return Integer.parseInt(txtDisponibil.getText());
    }

    @Override
    public String getedituraT() {
        return txtEditura.getText();
    }


    @Override
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
    @Override
    public void mesajEroare() {
        JOptionPane.showMessageDialog(AngajatFrame.this,
                "Utilizator sau parola invalidă",
                "Încearcă din nou",
                JOptionPane.ERROR_MESSAGE);

}}
