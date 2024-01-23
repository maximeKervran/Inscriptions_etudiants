package formation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import formation.Etudiant;
import formation.Formation;
import formation.UniteEnseignement;

public class TestFormation {
  
  private Formation formation;
  
  @Before
  public void setUp() {
    formation = new Formation();
  }
  
  @Test
  public void testCreerFormation() {
    formation.creerFormation("Informatique", "John Doe",
        "john.doe@example.com");
    
    assertEquals("Informatique", formation.getNomFormation());
    assertEquals("John Doe", formation.getNomResponsableFormation());
    assertEquals("john.doe@example.com",
        formation.getEmailResponsableFormation());
  }
  
  @Test
  public void testAjouterEnseignementObligatoire() {
    UniteEnseignement ue1 = new UniteEnseignement("UE1", "Prof1", false);
    UniteEnseignement ue2 = new UniteEnseignement("UE2", "Prof2", false);
    
    assertTrue(formation.ajouterEnseignementObligatoire(ue1));
    assertTrue(formation.ajouterEnseignementObligatoire(ue2));
    
    assertFalse(formation.ajouterEnseignementObligatoire(ue1)); // Déjà ajouté
  }
  
  @Test
  public void testAjouterEnseignementOptionnel() {
    UniteEnseignement ue1 = new UniteEnseignement("UE1", "Prof1", true);
    UniteEnseignement ue2 = new UniteEnseignement("UE2", "Prof2", true);
    
    assertTrue(formation.ajouterEnseignementOptionnel(ue1, 50));
    assertTrue(formation.ajouterEnseignementOptionnel(ue2, 30));
    
    assertFalse(formation.ajouterEnseignementOptionnel(ue1, 20)); // Déjà ajouté
  }
  
  @Test
  public void testDefinirNombreOptions() {
    assertEquals(-1, formation.getNombreOptions());
    
    formation.definirNombreOptions(3);
    
    assertEquals(3, formation.getNombreOptions());
    formation.definirNombreOptions(5); // Ne doit pas être modifié après la
                                       // définition
    assertEquals(3, formation.getNombreOptions());
  }
  
  @Test
  public void testSetTailleGroupeDirige() {
    assertEquals(-1, formation.getTailleGroupeDirige());
    
    formation.setTailleGroupeDirige(30);
    
    assertEquals(30, formation.getTailleGroupeDirige());
    formation.setTailleGroupeDirige(50); // Ne doit pas être modifié après la
                                         // définition
    assertEquals(30, formation.getTailleGroupeDirige());
  }
  
  @Test
  public void testSetTailleGroupePratique() {
    assertEquals(-1, formation.getTailleGroupePratique());
    
    formation.setTailleGroupePratique(25);
    
    assertEquals(25, formation.getTailleGroupePratique());
    formation.setTailleGroupePratique(40); // Ne doit pas être modifié après la
                                           // définition
    assertEquals(25, formation.getTailleGroupePratique());
  }
  
  @Test
  public void testAttribuerAutomatiquementGroupes() {
    // Créer des étudiants non affectés
    Set<Etudiant> etudiantsNonAffectes = new HashSet<>();
    for (int i = 0; i < 40; i++) {
      Etudiant etudiant =
          new Etudiant("E" + i, "Nom" + i, "Prenom" + i, 20 + i);
      etudiantsNonAffectes.add(etudiant);
    }
    
    formation.setTailleGroupeDirige(30);
    formation.setTailleGroupePratique(25);
    
    // Affecter automatiquement les groupes
    formation.attribuerAutomatiquementGroupes();
    
    // Vérifier si les groupes ont été créés et si les étudiants ont été
    // affectés
    assertEquals(2, formation.nombreGroupesTravauxDiriges());
    assertEquals(2, formation.nombreGroupesTravauxPratiques());
    
    // Vérifier que les étudiants non affectés ont diminué
    assertEquals(10, etudiantsNonAffectes.size());
  }
  
  @Test
  public void testListeEtudiantsGroupeDirige() {
    // Créer des groupes de TD
    for (int i = 0; i < 3; i++) {
      Set<Etudiant> groupe = new HashSet<>();
      for (int j = 0; j < 10; j++) {
        Etudiant etudiant = new Etudiant("E" + (i * 10 + j),
            "Nom" + (i * 10 + j), "Prenom" + (i * 10 + j), 20 + (i * 10 + j));
        groupe.add(etudiant);
      }
      formation.getListeGroupeTd().add(groupe);
    }
    
    // Vérifier la liste des étudiants d'un groupe de TD
    Set<Etudiant> etudiantsGroupe = formation.listeEtudiantsGroupeDirige(1);
    
    assertEquals(10, etudiantsGroupe.size());
    assertTrue(etudiantsGroupe.stream().allMatch(
        etudiant -> etudiant.getAge() >= 20 && etudiant.getAge() < 30));
  }
  
  @Test
  public void testListeEtudiantsGroupePratique() {
    // Créer des groupes de TP
    for (int i = 0; i < 2; i++) {
      Set<Etudiant> groupe = new HashSet<>();
      for (int j = 0; j < 15; j++) {
        Etudiant etudiant = new Etudiant("E" + (i * 15 + j),
            "Nom" + (i * 15 + j), "Prenom" + (i * 15 + j), 20 + (i * 15 + j));
        groupe.add(etudiant);
      }
      formation.getListeGroupeTp().add(groupe);
    }
    
    // Vérifier la liste des étudiants d'un groupe de TP
    Set<Etudiant> etudiantsGroupe = formation.listeEtudiantsGroupePratique(0);
    
    assertEquals(15, etudiantsGroupe.size());
    assertTrue(etudiantsGroupe.stream().allMatch(
        etudiant -> etudiant.getAge() >= 20 && etudiant.getAge() < 35));
  }
  
  @Test
  public void testListeEtudiantsOption() {
    UniteEnseignement ueOptionnelle =
        new UniteEnseignement("UE Option", "Prof Option", true);
    
    // Ajouter l'UE optionnelle à la liste des UE optionnelles
    formation.getListeUeOptionnel().put(ueOptionnelle, 20);
    
    // Créer des étudiants inscrits à l'UE optionnelle
    Set<Etudiant> etudiantsOption = new HashSet<>();
    for (int i = 0; i < 20; i++) {
      Etudiant etudiant =
          new Etudiant("E" + i, "Nom" + i, "Prenom" + i, 20 + i);
      etudiantsOption.add(etudiant);
    }
    
    // Affecter les étudiants à l'UE optionnelle
    formation.getListeUeOptionnel().put(ueOptionnelle, etudiantsOption);
    
    // Vérifier la liste des étudiants inscrits à l'UE optionnelle
    Set<Etudiant> etudiantsUeOption =
        formation.listeEtudiantsOption(ueOptionnelle);
    
    assertEquals(20, etudiantsUeOption.size());
    assertTrue(etudiantsUeOption.stream().allMatch(
        etudiant -> etudiant.getAge() >= 20 && etudiant.getAge() < 40));
  }
  
  @Test
  public void testChangerGroupe() {
    // Créer un étudiant
    Etudiant etudiant = new Etudiant("E1", "Nom1", "Prenom1", 22);
    
    // Affecter l'étudiant à un groupe existant
    int groupeDirigeAvant = formation.nombreGroupesTravauxDiriges();
    int groupePratiqueAvant = formation.nombreGroupesTravauxPratiques();
    int resultat = formation.changerGroupe(etudiant, 0, 1);
    
    assertEquals(0, resultat);
    assertEquals(groupeDirigeAvant, formation.nombreGroupesTravauxDiriges());
    assertEquals(groupePratiqueAvant,
        formation.nombreGroupesTravauxPratiques());
    
    // Affecter l'étudiant à un groupe inexistant
    resultat = formation.changerGroupe(etudiant, 5, 3);
    
    assertEquals(-1, resultat);
    assertEquals(groupeDirigeAvant, formation.nombreGroupesTravauxDiriges());
    assertEquals(groupePratiqueAvant,
        formation.nombreGroupesTravauxPratiques());
  }
  
  @Test
  public void testNombreGroupesTravauxDirigesEtPratiques() {
    // Ajouter quelques groupes de TD et TP
    for (int i = 0; i < 3; i++) {
      formation.getListeGroupeTd().add(new HashSet<>());
      formation.getListeGroupeTp().add(new HashSet<>());
    }
    
    assertEquals(3, formation.nombreGroupesTravauxDiriges());
    assertEquals(3, formation.nombreGroupesTravauxPratiques());
  }
  
  @Test
  public void testNbGroupesTravauxDirigesEtPratiques() {
    // Ajouter quelques groupes de TD et TP
    for (int i = 0; i < 4; i++) {
      formation.getListeGroupeTd().add(new HashSet<>());
      formation.getListeGroupeTp().add(new HashSet<>());
    }
    
    assertEquals("4", formation.nbGroupesTravauxDiriges());
    assertEquals("4", formation.nbGroupesTravauxPratiques());
  }
  
  @Test
  public void testListeEtudiantsOptionUEInexistante() {
    // Créer une UE optionnelle
    UniteEnseignement ueInexistante =
        new UniteEnseignement("UE Inexistante", "Prof Inexistant", true);
    
    // Vérifier que la liste d'étudiants pour une UE inexistante est null
    assertNull(formation.listeEtudiantsOption(ueInexistante));
  }
  
  @Test
  public void testListeEtudiantsOptionUEExistante() {
    // Créer une UE optionnelle
    UniteEnseignement ueOptionnelle =
        new UniteEnseignement("UE Option", "Prof Option", true);
    
    // Ajouter l'UE optionnelle à la liste des UE optionnelles
    formation.getListeUeOptionnel().put(ueOptionnelle, 20);
    
    Set<Etudiant> etudiantsOption = new HashSet<>();
    for (int i = 0; i < 20; i++) {
      Etudiant etudiant =
          new Etudiant("E" + i, "Nom" + i, "Prenom" + i, 20 + i);
      etudiantsOption.add(etudiant);
    }
    
    formation.getListeUeOptionnel().put(ueOptionnelle, etudiantsOption);
    
    Set<Etudiant> etudiantsUeOption =
        formation.listeEtudiantsOption(ueOptionnelle);
    
    assertEquals(20, etudiantsUeOption.size());
    assertTrue(etudiantsUeOption.stream().allMatch(
        etudiant -> etudiant.getAge() >= 20 && etudiant.getAge() < 40));
  }
  
}
