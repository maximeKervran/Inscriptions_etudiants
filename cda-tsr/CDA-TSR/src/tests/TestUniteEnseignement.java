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
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @BeforeEach
  void setUp() throws Exception {
    ue1 = new UniteEnseignement("Programmation C", "Jean Gabin");
  }
  
  
  
  /**
   * v�rifie le nom de l'ue.
   */
  @Test
  void testnomue() {
    assertEquals(ue1.getNom(), "Programmation C");
  }
  
  
  
  /**
   * v�rifie le nom de l'enseignant.
   */
  @Test
  void testnomenseignant() {
    assertEquals(ue1.getNom_enseignant(), "Jean Gabin");
  }
  
  
  
  
  /**
   * V�rifie que les param�tres des constructeurs sont correctement g�r�s.
   */
  @Test
  void testConstructeur() {
    UniteEnseignement ue2 = new UniteEnseignement("Anglais", "C�dric Dupont");
    assertEquals(ue2.getNom(), "Anglais");
    assertEquals(ue2.getNom_enseignant(), "C�dric Dupont");
  }
  
  
  
  
  
  /**
   * V�rifie le getter.
   */
  @Test
  void testGet() {
    final String nom;
    nom = ue1.getNom_enseignant();
    assertEquals(nom, "Jean Gabin");
  }
  
  
  
  
  
  /**
   * V�rifie le setter.
   */
  @Test
  void testSet() {
    
    ue1.setNom("Communication");
    assertEquals(ue1.getNom(), "Communication");
  }
  
}

