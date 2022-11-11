package sep.tippspiel.systemdatum;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
public class SystemDatum {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SystemDatum() {};

    public SystemDatum(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {return "SystemDatum" + date.toString();}


}
