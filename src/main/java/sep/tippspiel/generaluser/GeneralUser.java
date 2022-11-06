package sep.tippspiel.generaluser;

public abstract class GeneralUser {

    private String vorname;
    private String nachname;
    private String email;
    private String passwort;

    boolean logIn(String email, String passwort) {
        return this.email.equals(email) && this.passwort.equals(passwort);
    }

    boolean superCodeIsValid(int isCode, int emailCode) {
        return isCode == emailCode;
    }
}
