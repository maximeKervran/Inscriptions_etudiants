package io;

import formation.Formation;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implémentation de l'interface InterSauvegarde pour sauvegarder et charger les
 * données de la formation.
 */
public class GestionSauvegarde {
  
  private static final Logger LOGGER = Logger.getLogger(GestionSauvegarde.class.getName());
  
  /**
   * Sauvegarde des données.
   *
   */
  public void sauvegarderDonnees(String nomFichier, Formation formation)
      throws IOException {
    try (ObjectOutputStream oos =
        new ObjectOutputStream(new FileOutputStream(nomFichier))) {
      // Sauvegarde de la formation
      oos.writeObject(formation);
      LOGGER.log(Level.INFO,
          "Données sauvegardées avec succès dans le fichier : " + nomFichier);
    } catch (IOException e) {
      LOGGER.log(Level.SEVERE,
          "Erreur lors de la sauvegarde des données dans le fichier : "
              + nomFichier,
          e);
      throw e;
    }
  }
  
  
  
  /**
   * Récuperation des données sauvegardées.
   *
   */
  public Formation chargerDonnees(String nomFichier)
      throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois =
        new ObjectInputStream(new FileInputStream(nomFichier))) {
      // Chargement de la formation
      Formation formation = (Formation) ois.readObject();
      LOGGER.log(Level.INFO,
          "Données chargées avec succès depuis le fichier : " + nomFichier);
      return formation;
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.log(Level.SEVERE,
          "Erreur lors du chargement des données depuis le fichier : "
              + nomFichier,
          e);
      throw e;
    }
  }
}