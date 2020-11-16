package fr.chatelain.reservation.reservation.back.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQuery(name = "Compte.findByEmail", query = "SELECT c FROM Compte c WHERE c.personne.mail LIKE :email")
public class Compte extends AbstractEntities {

    private static final long serialVersionUID = 1L;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Password password;

    @ManyToMany
    private Set<Role> roles;

    public Compte() {
        super();
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
