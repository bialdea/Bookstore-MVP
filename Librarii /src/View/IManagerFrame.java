package View;

import javax.swing.table.DefaultTableModel;

public interface IManagerFrame {
    String getCriteriu();
    String getFiltru();

    void setTable(DefaultTableModel model);
    void setFiltru(String filtru);
    int getidLibrarieT();
}
