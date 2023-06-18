package Presenter;
import Model.*;
import View.IAngajatFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AngajatPresenter {
    private final IAngajatFrame vizualizareAngajat;
    private final CarteLibrariePersistenta persistentaCarteLibrarie;

    public AngajatPresenter(IAngajatFrame vizualizareAngajat) {
        this.vizualizareAngajat = vizualizareAngajat;
        this.persistentaCarteLibrarie = new CarteLibrariePersistenta();

    }
    public void adaugareTabel() {
        List<CarteLibrarie> cartil = persistentaCarteLibrarie.selectAllCarti(vizualizareAngajat.getidLibrarieT());
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };
        model.addColumn("id_librarie");
        model.addColumn("IdCarteLibrarie");
        model.addColumn("Domeniu");
        model.addColumn("Titlu");
        model.addColumn("Autor");
        model.addColumn("Pret");
        model.addColumn("Disponibil");
        model.addColumn("Editura");

        for (CarteLibrarie cl : cartil) {
            model.addRow(new Object[]{cl.getIdLibrarie(), cl.getIdCarteLibrarie(), cl.getDomeniu(), cl.getTitlu(), cl.getAutor(), cl.getPret(), cl.getDisponibil(), cl.getEditura()});
        }

        vizualizareAngajat.setTable(model);
    }

    public void readaugareTabel(){
        List<CarteLibrarie> cartil = persistentaCarteLibrarie.filterAllCarti(vizualizareAngajat.getCriteriu(), vizualizareAngajat.getFiltru(), vizualizareAngajat.getidLibrarieT());
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("id_librarie");
        model.addColumn("IdCarteLibrarie");
        model.addColumn("Domeniu");
        model.addColumn("Titlu");
        model.addColumn("Autor");
        model.addColumn("Pret");
        model.addColumn("Disponibil");
        model.addColumn("Editura");

        for (CarteLibrarie cl : cartil) {
            model.addRow(new Object[]{cl.getIdLibrarie(), cl.getIdCarteLibrarie(), cl.getDomeniu(), cl.getTitlu(), cl.getAutor(), cl.getPret(), cl.getDisponibil(), cl.getEditura()});
        }

        vizualizareAngajat.setTable(model);
    }

    public void curataCampuri(){
        vizualizareAngajat.setFiltru("");
    }

    public void adaugareCarteLibrarie() throws SQLException {
        int idCarte = vizualizareAngajat.getidCarteT();
        String domeniu = vizualizareAngajat.getDomeniuT();
        String titlu = vizualizareAngajat.getTitluT();
        String autor = vizualizareAngajat.getautorT();
        int idLibrarie = vizualizareAngajat.getidLibrarieT();
        int idCarteLibrarie = vizualizareAngajat.getidCarteLibrarieT();
        float pret = vizualizareAngajat.getpretT();
        int disponibil = vizualizareAngajat.getdisponibilT();
        String editura = vizualizareAngajat.getedituraT();

        CarteLibrarie carte = new CarteLibrarie(pret, disponibil, idCarteLibrarie, editura, idLibrarie, idCarte, domeniu, titlu, autor);
        boolean ok = persistentaCarteLibrarie.adaugareCarteLibrarie(carte);
        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Adaugare efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Adaugarea nu s-a efectuat!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void stergereCarteLibrarie() throws SQLException {
        boolean ok;
        int idcartelibrarie = vizualizareAngajat.getidCarteLibrarieT();
        ok = persistentaCarteLibrarie.stergereCarteLibrarie(idcartelibrarie);
        if(ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergere efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergerea nu s-a efectuat!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void actualizareCarteLibrarie() throws SQLException {
        int idCarte = vizualizareAngajat.getidCarteT();
        String domeniu = vizualizareAngajat.getDomeniuT();
        String titlu = vizualizareAngajat.getTitluT();
        String autor = vizualizareAngajat.getautorT();
        int idLibrarie = vizualizareAngajat.getidLibrarieT();
        int idCarteLibrarie = vizualizareAngajat.getidCarteLibrarieT();
        float pret = vizualizareAngajat.getpretT();
        int disponibil = vizualizareAngajat.getdisponibilT();
        String editura = vizualizareAngajat.getedituraT();

        CarteLibrarie carte = new CarteLibrarie(pret, disponibil, idCarteLibrarie, editura, idLibrarie, idCarte, domeniu, titlu, autor);
        boolean ok = persistentaCarteLibrarie.actualizareCarteLibrarie(carte);
        if (ok) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Actualizare efectuată cu succes!", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Actualizarea nu s-a efectuat!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
