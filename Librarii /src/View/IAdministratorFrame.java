package View;

import javax.swing.table.DefaultTableModel;

public interface IAdministratorFrame {
    void setTable(DefaultTableModel model);
    int getidUtilizatorT();
    String getNameT();
    String getPasswordT();
    String getRoleT();
    int getidLibrarieT();

}
