package fr.chatelain.reservation.reservation.back.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CodePromo extends AbstractEntities {

    private static final long serialVersionUID = 8505905252780920773L;

    @Column
    private String code;

    @Column
    private double pourcentage;

    public CodePromo() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

}
