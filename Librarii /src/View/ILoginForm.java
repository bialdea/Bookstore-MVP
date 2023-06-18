package View;

public interface ILoginForm {

    String getUser();
    String getParola();

    void mesajEroare();

    void setAdministratorView(String user);
    void setManagerView(String user);

    void setAngajatView(String user, int id_librarie);

}
