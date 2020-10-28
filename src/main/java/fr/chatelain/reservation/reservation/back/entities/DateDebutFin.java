package fr.chatelain.reservation.reservation.back.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DateDebutFin extends AbstractEntities {

    private static final long serialVersionUID = 3367530931755645512L;

    @Column
    private LocalDate dateDebut;

    @Column
    private LocalDate dateFin;

    public DateDebutFin() {
        super();
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

}
