package View;

import javax.swing.table.DefaultTableModel;

public interface IAngajatFrame {
    String getCriteriu();
    String getFiltru();
    void setTable(DefaultTableModel model);
    void setFiltru(String filtru);
    int getidCarteLibrarieT();
    int getidLibrarieT();

    String getDomeniuT();

    String getTitluT();

    String getautorT();

    float getpretT();

    int getdisponibilT();

    String getedituraT();


    int getidCarteT();

    void mesajEroare();
}
