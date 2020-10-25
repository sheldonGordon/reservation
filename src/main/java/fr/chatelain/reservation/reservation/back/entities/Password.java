package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Password extends AbstractEntities {

    private static final long serialVersionUID = 6338037257230920268L;

    @Column
    private String hash;

    @Column
    private String salt;

    public Password() {
        super();
    }

    public Password(String hash, String salt) {
        super();
        this.hash = hash;
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
