package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import formation.UniteEnseignement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests JUnit de la classe {@link formation.UniteEnseignement
 * UniteEnseignement}.
 *
 * @author Soilihi SAADI
 * @see formation.UniteEnseignement
 */

class TestUniteEnseignement {
  private UniteEnseignement ue1;
  
  /**
   * Instancie une unite d'enseignement.
   *
   * @throws Exception ne peut pas être levée ici
   */
  @BeforeEach
  void setUp() throws Exception {
    ue1 = new UniteEnseignement("Programmation C", "Jean Gabin");
  }
  
  
  
  /**
   * vérifie le nom de l'ue.
   */
  @Test
  void testnomue() {
    assertEquals(ue1.getNom(), "Programmation C");
  }
  
  
  
  /**
   * vérifie le nom de l'enseignant.
   */
  @Test
  void testnomenseignant() {
    assertEquals(ue1.getNom_enseignant(), "Jean Gabin");
  }
  
  
  
  
  /**
   * Vérifie que les paramètres des constructeurs sont correctement gérés.
   */
  @Test
  void testConstructeur() {
    UniteEnseignement ue2 = new UniteEnseignement("Anglais", "Cédric Dupont");
    assertEquals(ue2.getNom(), "Anglais");
    assertEquals(ue2.getNom_enseignant(), "Cédric Dupont");
  }
  
  
  
  
  
  /**
   * Vérifie le getter.
   */
  @Test
  void testGet() {
    final String nom;
    nom = ue1.getNom_enseignant();
    assertEquals(nom, "Jean Gabin");
  }
  
  
  
  
  
  /**
   * Vérifie le setter.
   */
  @Test
  void testSet() {
    
    ue1.setNom("Communication");
    assertEquals(ue1.getNom(), "Communication");
  }
  
}

