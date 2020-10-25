package fr.chatelain.reservation.reservation.back.entities;

public enum Civilite {
    M("Monsieur", "M."), MM("Madame", "Mme"), MLLE("Mademoiselle", "Mlle");

    private String libelle;

    private String abreviation;

    private Civilite(String libelle, String abreviation) {
        this.libelle = libelle;
        this.abreviation = abreviation;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getAbreviation() {
        return abreviation;
    }

}
