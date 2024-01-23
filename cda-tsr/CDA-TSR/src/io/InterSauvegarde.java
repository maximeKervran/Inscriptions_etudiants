package io;

import java.io.IOException;

/**
 * D�finit les m�thodes permettant de sauvegarder les donn�es dans un fichier.
 *
 * @author Eric Cariou
 */
public interface InterSauvegarde {
  
  /**
   * Sauvegarde toutes les donn�es de la formation : liste des UEs, des
   * �tudiants et des groupes.
   *
   * @param nomFichier le fichier dans lequel sauvegarder les donn�es
   * @throws IOException en cas de probl�me de sauvegarde
   */
  void sauvegarderDonnees(String nomFichier) throws IOException;
  
  /**
   * Charge les donn�es de la formation (UEs, �tudiants, groupes) � partir d'un
   * fichier.
   *
   * @param nomFichier le fichier dans lequel les donn�es ont �t� sauvegard�es
   * @throws IOException en cas de probl�me de chargement
   */
  void chargerDonnees(String nomFichier) throws IOException;
}
