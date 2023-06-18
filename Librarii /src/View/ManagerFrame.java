package View;

import Presenter.ManagerPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ManagerFrame extends JFrame implements IManagerFrame{

    public final JTextField txtFiltru = new JTextField();
    String[] options = {"Domeniu", "Disponibil", "Editura", "Autor", "Pret"};
    JComboBox<String> comboCriteriu = new JComboBox<>(options);
    public final JTable table = new JTable();
    public final JScrollPane scrollPane = new JScrollPane();
    private final JTextField txtIdLibrarie = new JTextField();

    private final ManagerPresenter m;

    public ManagerFrame() {

        JPanel panelManager = new JPanel();
        setContentPane(panelManager);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        m = new ManagerPresenter(this);

        JButton btnCauta = new JButton("Cauta");
        btnCauta.addActionListener(e -> m.readaugareTabel());

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> m.adaugareTabel());
        JButton btnCuratare = new JButton("Curatare");
        btnCuratare.addActionListener(e -> m.curataCampuri());

        // set layout for the panel
        panelManager.setLayout(new BorderLayout());

        // add the filter panel
        JPanel filterPanel = new JPanel();
        JLabel lblIdLibrarie = new JLabel("IdLibrarie:");
        filterPanel.add(lblIdLibrarie);
        txtIdLibrarie.setPreferredSize(new Dimension( 80, 25));
        filterPanel.add(txtIdLibrarie);
        filterPanel.add(new JLabel("Criteriu:"));
        filterPanel.add(comboCriteriu);
        filterPanel.add(new JLabel("Filtru:"));
        txtFiltru.setPreferredSize(new Dimension( 80, 25)); // setam dimensiunea campului text
        filterPanel.add(txtFiltru);
        filterPanel.add(btnCauta);
        filterPanel.add(btnCuratare);
        filterPanel.setBackground(new Color(237, 245, 152));
        panelManager.add(filterPanel, BorderLayout.NORTH);

        // add the table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelManager.add(tablePanel, BorderLayout.CENTER);

        // add the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.setBackground(new Color(237, 245, 152));
        panelManager.add(buttonPanel, BorderLayout.SOUTH);
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
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
    @Override
    public int getidLibrarieT() {
        return Integer.parseInt(txtIdLibrarie.getText());
    }

}
