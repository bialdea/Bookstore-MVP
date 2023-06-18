package Presenter;
import Model.*;
import View.IAdministratorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AdministratorPresenter {
    private final IAdministratorFrame vizualizareAdministrator;
    private final UtilizatorPersistenta persistentaUtilizator;

    public AdministratorPresenter(IAdministratorFrame vizualizareAdministrator) {

        this.vizualizareAdministrator = vizualizareAdministrator;
        this.persistentaUtilizator = new UtilizatorPersistenta();
    }

    public void adaugareTabel() {
        List<Utilizator> utilizatori = persistentaUtilizator.totiUtilizatorii();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };
        model.addColumn("IdUtilizator");
        model.addColumn("Username");
        model.addColumn("Parola");
        model.addColumn("Tip");
        model.addColumn("IdLibrarie");


        for (Utilizator u : utilizatori) {
            model.addRow(new Object[]{u.getIdUtilizator(), u.getUser(), u.getParola(), u.getTip(), u.getIdLibrarie()});
        }

        vizualizareAdministrator.setTable(model);
    }

    public void adaugareUtilizator() throws SQLException {
        boolean ok;
        int idUtilizator = vizualizareAdministrator.getidUtilizatorT();
        String username = vizualizareAdministrator.getNameT();
        String parola = vizualizareAdministrator.getPasswordT();
        String tip = vizualizareAdministrator.getRoleT();
        int idLibrarie = vizualizareAdministrator.getidLibrarieT();
        Utilizator utl = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);

        ok = persistentaUtilizator.adaugareUtilizator(utl);

        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Adaugare efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Username deja existent!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void stergereUtilizator() throws SQLException {
        boolean ok;
        ok = persistentaUtilizator.stergereUtilizator(vizualizareAdministrator.getidUtilizatorT());
        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergere efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergerea nu s-a efectuat!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void actualizareUtilizator() throws SQLException {
        boolean ok;
        int idUtilizator = vizualizareAdministrator.getidUtilizatorT();
        String username = vizualizareAdministrator.getNameT();
        String parola = vizualizareAdministrator.getPasswordT();
        String tip = vizualizareAdministrator.getRoleT();
        int idLibrarie = vizualizareAdministrator.getidLibrarieT();
        Utilizator utl = new Utilizator(idUtilizator, username, parola, tip, idLibrarie);

        ok = persistentaUtilizator.actualizareUtilizator(utl);


        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Actualizare efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Actualizarea nu s-a efectuat!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

