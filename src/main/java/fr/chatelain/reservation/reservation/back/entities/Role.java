package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = Role.FIND_ALL_BY_LIBELLE, query = "SELECT r FROM Role r WHERE r.libelle = ?1")
public class Role extends AbstractEntities {

    public static final String FIND_ALL_BY_LIBELLE = "Role.findAllByLibelle";

    private static final long serialVersionUID = 1L;

    @Column
    private String libelle;

    public Role() {
        super();
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
