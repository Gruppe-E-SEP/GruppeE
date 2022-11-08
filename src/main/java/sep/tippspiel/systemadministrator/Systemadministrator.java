package sep.tippspiel.systemadministrator;


import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Entity
public class Systemadministrator implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    @NotNull
    @NotEmpty(message = "Vorname darf nicht leer sein")
    @Column(name = "vorname")
    private String vorname;

    @NotNull
    @NotEmpty(message = "Nachname darf nicht leer sein")
    @Column(name = "nachname")
    private String nachname;

    @NotNull
    @NotEmpty(message = "Email darf nicht leer sein")
    @Column(name = "email")
    private String email;

    @NotNull
    @NotEmpty(message = "Passwort darf nicht leer sein")
    @Column(name = "passwort")
    private String passwort;

    public Systemadministrator() {};

    public Systemadministrator(String vorname, String nachname, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
    }

    @Override
    public String toString() {return "Vorname: " + vorname + " Nachname: " + nachname + "E-Mail: " + email; }

}
