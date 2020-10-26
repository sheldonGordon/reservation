package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;

public class Photos extends AbstractEntities {

    private static final long serialVersionUID = 7714218428585395888L;

    @Column
    private String data;

    @Column
    private String nom;
}
