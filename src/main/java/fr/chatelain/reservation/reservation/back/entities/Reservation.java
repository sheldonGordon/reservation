package fr.chatelain.reservation.reservation.back.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation extends AbstractEntities {

    private static final long serialVersionUID = -1872170167391711254L;

    @OneToOne
    private DateDebutFin dateDebutfin;

    @ManyToOne
    private Chambre chambre;

    @ManyToMany
    private Set<Option> options;

    @ManyToOne
    private Compte client;

    @Column
    private boolean annulation;

    @ManyToOne
    private CodePromo promotion;

    public Reservation() {
        super();
        this.setAnnulation(false);
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public DateDebutFin getDateDebutfin() {
        return dateDebutfin;
    }

    public void setDateDebutfin(DateDebutFin dateDebutfin) {
        this.dateDebutfin = dateDebutfin;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Compte getClient() {
        return client;
    }

    public void setClient(Compte client) {
        this.client = client;
    }

    public boolean isAnnulation() {
        return annulation;
    }

    public void setAnnulation(boolean annulation) {
        this.annulation = annulation;
    }

    public CodePromo getPromotion() {
        return promotion;
    }

    public void setPromotion(CodePromo promotion) {
        this.promotion = promotion;
    }

}
