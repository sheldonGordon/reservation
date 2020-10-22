package fr.chatelain.reservation.reservation.entities;

public enum Sexe {
    HOMME("Homme"), FEMME("Femme"), AUTRE("Autre");

    private String genre;

    private Sexe(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

}
