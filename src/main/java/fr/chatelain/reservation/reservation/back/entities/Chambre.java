package fr.chatelain.reservation.reservation.back.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Chambre extends AbstractEntities {

    private static final long serialVersionUID = 1365648515210158917L;

    @Column
    private String nom;

    @Column
    private Double nombrePersonne;

    @Column
    private BigDecimal prix;

    @Column
    private Double superficie;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Photos> photos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chambre_services", joinColumns = {
            @JoinColumn(name = "chambre_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "services_id", referencedColumnName = "id", nullable = false, updatable = false) })
    private Set<Service> services;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chambre_indisponibilites", joinColumns = {
            @JoinColumn(name = "chambre_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "indisponibilites_id", referencedColumnName = "id", nullable = false, updatable = false) })
    private Set<DateDebutFin> indisponibilites;

    public Chambre() {
        super();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(Double nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public Set<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photos> photos) {
        this.photos = photos;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<DateDebutFin> getIndisponibilites() {
        return indisponibilites;
    }

    public void setIndisponibilites(Set<DateDebutFin> indisponibilites) {
        this.indisponibilites = indisponibilites;
    }

}
