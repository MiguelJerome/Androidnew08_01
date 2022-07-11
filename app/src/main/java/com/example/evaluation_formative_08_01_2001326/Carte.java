package com.example.evaluation_formative_08_01_2001326;




// Classe implantant une carte à jouer
public class Carte {
    // Familles de cartes
    public enum Famille { Pique, Coeur, Carreau, Trèfle};

    private int valeur = 2;                    // 2 à 14 (As)
    private Famille famille = Famille.Pique;

    // Constructeur paramétré
    public Carte(int valeur, Famille famille) {
        this.setValeur(valeur);
        this.setFamille(famille);
    }

    // Accesseur de valeur
    public int getValeur() {
        return this.valeur;
    }

    // Mutateur de valeur (s'assure que la valeur fournie est valide)
    public void setValeur(int valeur) {
        if (valeur < 2)
            this.valeur = 2;
        else if (valeur > 14)
            this.valeur = 14;
        else
            this.valeur = valeur;
    }

    // Accesseur de famille
    public Famille getFamille() {
        return this.famille;
    }

    // Mutateur de famille
    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    // Retourne une chaîne décrivant textuellement la carte
    public String descriptionTextuelle() {
        String s;  // chaîne construite

        // On commence par la valeur de la carte
        switch (this.valeur) {
            case 2:
                s = "Deux";
                break;
            case 3:
                s = "Trois";
                break;
            case 4:
                s = "Quatre";
                break;
            case 5:
                s = "Cinq";
                break;
            case 6:
                s = "Six";
                break;
            case 7:
                s = "Sept";
                break;
            case 8:
                s = "Huit";
                break;
            case 9:
                s = "Neuf";
                break;
            case 10:
                s = "Dix";
                break;
            case 11:
                s = "Valet";
                break;
            case 12:
                s = "Dame";
                break;
            case 13:
                s = "Roi";
                break;
            case 14:
                s = "As";
                break;
            default:
                s = Integer.toString(this.valeur);  // ne devrait jamais se produire
                break;
        }

        // On y ajoute ensuite la famille
        s += " de ";

        switch (this.famille) {
            case Pique:
                s += " Pique";
                break;

            case Coeur:
                s += " Coeur";
                break;

            case Carreau:
                s += " Carreau";
                break;

            case Trèfle:
                s += " Trèfle";
                break;

            default:
                s += " ???";   // ça ne devrait pas se produire!
                break;
        }

        return s;
    }

    // Retourne une chaîne décrivant la carte de façon abrégée
    public String descriptionAbrégée() {
        String s;  // chaîne construite

        // On commence par la valeur de la carte
        if (this.valeur == 11)
            s = "V";
        else if (this.valeur == 12)
            s = "D";
        else if (this.valeur == 13)
            s = "R";
        else if (this.valeur == 14)
            s = "A";
        else
            s = Integer.toString(this.valeur);

        // On y ajoute ensuite un symbole représentant la famille
        switch (this.famille) {
            case Pique:
                s += (char)'\u2660';
                break;

            case Coeur:
                s += (char)'\u2764';
                break;

            case Carreau:
                s += (char)'\u2666';
                break;

            case Trèfle:
                s += (char)'\u2663';
                break;

            default:
                s += " ???";   // ça ne devrait pas se produire!
                break;
        }

        return s;
    }

    // Retourne une chaîne décrivant la carte
    public String toString() {
        String s = this.descriptionTextuelle() + " (" + this.descriptionAbrégée() + ")";
        return s;
    }

}
