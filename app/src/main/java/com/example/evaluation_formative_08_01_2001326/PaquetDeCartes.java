package com.example.evaluation_formative_08_01_2001326;

import java.util.ArrayList;
import java.util.Collections;

// Classe implantant un paquet de cartes à jouer
public class PaquetDeCartes {
    // Cartes dans le paquet
    private ArrayList<Carte> cartes = new ArrayList<>();

    // Constructeur par défaut : construit un paquet de 52 cartes
    public PaquetDeCartes() {
        for (int valeur = 2; valeur < 15; valeur++) {
            cartes.add(new Carte(valeur, Carte.Famille.Pique));
            cartes.add(new Carte(valeur, Carte.Famille.Coeur));
            cartes.add(new Carte(valeur, Carte.Famille.Carreau));
            cartes.add(new Carte(valeur, Carte.Famille.Trèfle));
        }

        // Les cartes sont brassées
        Collections.shuffle(cartes);
    }

    // Retourne le nombre de cartes dans le paquet
    public int taille() {
        return cartes.size();
    }

    // Extrait la carte du dessus du paquet et la retourne (null si le paquet est vide)
    public Carte piger() {
        if (cartes.size() > 0) {
            Carte cartePigée = cartes.remove(0);
            return cartePigée;
        }
        else
            return null;
    }
}
