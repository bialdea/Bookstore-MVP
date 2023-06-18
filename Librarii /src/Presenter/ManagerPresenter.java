package Presenter;
import Model.*;
import View.IManagerFrame;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ManagerPresenter {
    public final IManagerFrame vizualizareManager;
    private final CarteLibrariePersistenta persistentaCarteLibrarie;

    public ManagerPresenter(IManagerFrame vizualizareManager) {

        this.vizualizareManager = vizualizareManager;
        this.persistentaCarteLibrarie = new CarteLibrariePersistenta();
    }

    public void adaugareTabel() {
        List<CarteLibrarie> cartil = persistentaCarteLibrarie.selectAllCarti(vizualizareManager.getidLibrarieT());
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

        vizualizareManager.setTable(model);
    }

    public void readaugareTabel(){
        List<CarteLibrarie> cartil = persistentaCarteLibrarie.filterAllCarti(vizualizareManager.getCriteriu(), vizualizareManager.getFiltru(), vizualizareManager.getidLibrarieT());
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

        vizualizareManager.setTable(model);
    }

    public void curataCampuri(){
        vizualizareManager.setFiltru("");
    }
}

