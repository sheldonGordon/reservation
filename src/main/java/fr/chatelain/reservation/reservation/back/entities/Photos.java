package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Photos extends AbstractEntities {

    private static final long serialVersionUID = 7714218428585395888L;

    @Column(columnDefinition = "text")
    private String data;

    @Column
    private String nom;

    @Column
    private String typeMime;

    public Photos() {
        super();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeMime() {
        return typeMime;
    }

    public void setTypeMime(String typeMime) {
        this.typeMime = typeMime;
    }

}
