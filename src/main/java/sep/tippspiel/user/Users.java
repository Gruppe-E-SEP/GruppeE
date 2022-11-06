package sep.tippspiel.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Users implements Serializable {

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

    @Column(name = "vorname")
    private String vorname;
    @Column(name = "nachname")
    private String nachname;
    @Column(name = "email")
    private String email;
    @Column(name = "date")
    private Date date;
    @Column(name = "passwort")
    private String passwort;
    @Column(name = "image")
    private String image;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Users() {};

    public Users(String vorname, String nachname, String email, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
    }

    @Override
    public String toString() {return "Vorname: " + vorname + " Nachname: " + nachname + "E-Mail: " + email; }


}