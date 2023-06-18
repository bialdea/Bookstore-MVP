package Presenter;

import Model.*;
import View.ILoginForm;

import java.sql.SQLException;


public class LoginPresenter {
    ILoginForm link;
    UtilizatorPersistenta utilizator;

    public LoginPresenter(ILoginForm link1)
    {
        link = link1;
        utilizator= new UtilizatorPersistenta();
    }

    public void autentificare() throws SQLException {
        String numeUtilizator = link.getUser();
        String password = link.getParola();
            Utilizator u = utilizator.cautareUtilizator(numeUtilizator, password);
            if (u == null)
                link.mesajEroare();
            else {
                if (u.getTip().equals("administrator")) // Use .equals() instead of ==
                    link.setAdministratorView(u.getUser());
                else if (u.getTip().equals("manager")) // Use .equals() instead of ==
                    link.setManagerView(u.getUser());
                else if (u.getTip().equals("angajat")){
                    link.setAngajatView(u.getUser(), u.getIdLibrarie());
                }

            }
        }
    }


