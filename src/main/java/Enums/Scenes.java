package Enums;

public enum Scenes {

     GUI("/gui.fxml", "Buchungsseite"),
     ADMIN("/admin.fxml", "Adminseite"),
     LOGIN("/login.fxml", "Loginseite"),
     UEBERSICHT("/uebersicht.fxml", "Übersichtsseite"),
     REGISTRIEREN("/registrieren.fxml", "Registrierungsseite");

    private Scenes(String ressource, String titel){
        setUri(ressource);
        setTitel(titel);
    }

    private String uri;
    private String titel;

    public String getUri() {
        return uri;
    }

    private void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitel() {
        return titel;
    }

    private void setTitel(String titel) {
        this.titel = titel;
    }
}
