package formation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javafx.event.ActionEvent;


/**
 * Classe pour définir une formation.
 *
 * @author Maxime Kervran
 *
 */
public class Formation implements InterGestionFormation {
  private Responsable infos;
  private Formation gestionFormation;
  private Set<Etudiant> etudiantsNonAffectes;
  public Set<UniteEnseignement> listeUeobligatoires;
  public Map<UniteEnseignement, Integer> listeUeoptionnel;
  public List<Set<Etudiant>> listegroupeTd;
  public List<Set<Etudiant>> listegroupeTp;
  
  
  int nombreOptions = -1;
  int nombreplacesTd = -1;
  int nombreplacesTp = -1;
 
  
  
  public Responsable getInfos() {
    return infos;
  }
  
  
  public void setInfos(Responsable infos) {
    this.infos = infos;
  }
  
  
  public InterGestionFormation getGestionFormation() {
    return gestionFormation;
  }
  
  
  public void setGestionFormation(Formation gestionFormation) {
    this.gestionFormation = gestionFormation;
  }
  
  
  @Override
  public int hashCode() {
    return Objects.hash(infos);
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Formation other = (Formation) obj;
    return Objects.equals(infos, other.infos);
  }
  
  /**
   * Creation de la formation.
   *
   */
  public void creerFormation(String nomFormation, String nomResponsable,
      String email) {
    Responsable responsable = new Responsable(nomResponsable, email);
    responsable.setNomResponsable(nomResponsable);
    responsable.setAdresse(email);
    this.infos = responsable;
  }
  
  /**
   * retourne le nom du responsable de formation ou <code>null</code> si elle
   * n'a pas été définie.
   */
  public String getNomResponsableFormation() {
    if (infos != null) {
      return infos.getNomResponsable();
    } else {
      return null;
    }
  }
  
  /**
   * retourne l'adresse email du responsable de formation ou <code>null</code>
   * si elle n'a pas été définie.
   */
  public String getEmailResponsableFormation() {
    if (infos != null) {
      return infos.getAdresse();
    } else {
      return null;
    }
  }
  
  /**
   * retourne le nom de la formation ou <code>null</code> si elle n'a pas été
   * définie.
   */
  public String getNomFormation() {
    if (gestionFormation != null) {
      return gestionFormation.getNomFormation();
    } else {
      return null;
    }
  }
  
  /**
   * ajoute une UE obligatoire dans la liste si elle n'est pas déjà présente et
   * renvoie true si c'est bon.
   *
   */
  public boolean ajouterEnseignementObligatoire(UniteEnseignement ue) {
    if (listeUeobligatoires == null) {
      listeUeobligatoires = new HashSet<>();
    }
    if (!listeUeobligatoires.contains(ue)) {
      listeUeobligatoires.add(ue);
      return true;
    }
    return false;
  }
  
  /**
   * ajoute une UE optionnelle et son nombre de places dans la liste optionnelle
   * si elle n'est pas déjà présente en mettant le nombre de places si il y en a
   * un et si c'est null ou inférieur à 0 on met 0 en nombre de places et
   * renvoie true si c'est bon.
   *
   */
  public boolean ajouterEnseignementOptionnel(UniteEnseignement ue,
      int nbPlaces) {
    if (listeUeoptionnel == null) {
      listeUeoptionnel = new HashMap<>();
    }
    if (!listeUeoptionnel.containsKey(ue)) {
      listeUeoptionnel.put(ue, (nbPlaces >= 0) ? nbPlaces : 0);
      return true;
    }
    return false;
  }
  
  
  /**
   * défini le nombre d'options possibles à prendre, on met nombreOptions à -1
   * pour le définir une seule fois et ne pas pouvoir le définir ensuite.
   *
   */
  public void definirNombreOptions(int nombre) {
    if (nombreOptions == -1 && nombre >= 1) {
      nombreOptions = nombre;
    } else {
      System.out.println(
          "Le nombre d'options doit être plus grand que 1 ou a déjà été défini.");
    }
  }
  
  public int getNombreOptions() {
    return nombreOptions;
  }
  
  /**
   * défini le nombre de places possibles dans un groupe de TD, on met
   * nombreplacesTd à -1 pour le définir une seule fois et ne pas pouvoir le
   * définir ensuite.
   *
   */
  public void setTailleGroupeDirige(int taille) {
    if (nombreplacesTd == -1 && taille >= 1) {
      nombreplacesTd = taille;
    } else {
      System.out.println(
          "Le nombre de places doit être plus grand que 1 ou a déjà été défini.");
    }
  }
  
  /**
   * défini le nombre de places possibles dans un groupe de TP, on met
   * nombreplaceTp à -1 pour le définir une seule fois et ne pas pouvoir le
   * définir ensuite.
   *
   */
  public void setTailleGroupePratique(int taille) {
    if (nombreplacesTp == -1 && taille >= 1) {
      nombreplacesTp = taille;
    } else {
      System.out.println(
          "Le nombre d'options doit être plus grand que 1 ou a déja été défini. ");
    }
  }
  
  
  public int getTailleGroupeDirige() {
    return nombreplacesTd;
  }
  
  public int getTailleGroupePratique() {
    return nombreplacesTp;
  }
  
  
  /**
   * Attribue automatiquement les étudiants non encore affectés à des groupes de
   * TD et de TP. Au besoin, crée de nouveaux groupes de TD ou de TP. Pour
   * harmoniser la taille des groupes, des étudiants déjà placés peuvent être
   * déplacés. Les étudiants concernés par une affectation ou un changement
   * d'affectation reçoivent un message pour leur préciser ce qu'il s'est passé.
   */
  public void attribuerAutomatiquementGroupes() {
    // Check the sizes of TD and TP groups
    int taillegroupeTd = getTailleGroupeDirige();
    int taillegroupeTp = getTailleGroupePratique();
    
    for (Etudiant etudiant : etudiantsNonAffectes) {
      Set<Etudiant> etudiantsgroupeTd = listegroupeTd.get(0);
      Set<Etudiant> etudiantsgroupeTp = listegroupeTp.get(0);
      
      if (etudiantsgroupeTd.size() < taillegroupeTd
          && etudiantsgroupeTp.size() < taillegroupeTp) {
        // Ajout d'étudiants
        etudiantsgroupeTd.add(etudiant);
        etudiantsgroupeTp.add(etudiant);
        
        if (etudiantsgroupeTd.add(etudiant)
            && etudiantsgroupeTp.add(etudiant)) {
          // If successfully added to both groups, remove from non-affected list
          etudiantsNonAffectes.remove(etudiant);
          // Notify the student about the assignment
          System.out
              .println("Un groupe de TP et un groupe de TD t'ont été assignés");
        }
        
      } else {
        // On crée des nouveaux groupes et on les met dans la liste
        Set<Etudiant> nouveaugroupeTd = new HashSet<>();
        Set<Etudiant> nouveaugroupeTp = new HashSet<>();
        nouveaugroupeTd.add(etudiant);
        nouveaugroupeTp.add(etudiant);
        listegroupeTd.add(nouveaugroupeTd);
        listegroupeTp.add(nouveaugroupeTp);
      }
    }
  }
  
  
  
  public int changerGroupe(Etudiant etudiant, int groupeDirige,
      int groupePratique) {
    return gestionFormation.changerGroupe(etudiant, groupeDirige,
        groupePratique);
  }
  
  
  public int nombreGroupesTravauxDiriges() {
    int res = listegroupeTd.size();
    return res;
  }
  
  /**
   * caste le nombre de groupes de td.
   */
  public String nbGroupesTravauxDiriges() {
    int resTd = nombreGroupesTravauxDiriges();
    String res = String.valueOf(resTd);
    return res;
  }
  
  public int nombreGroupesTravauxPratiques() {
    int res = listegroupeTp.size();
    return res;
  }
  
  /**
   * caste le nombre de groupes de td.
   */
  public String nbGroupesTravauxPratiques() {
    int resTp = nombreGroupesTravauxPratiques();
    String res = String.valueOf(resTp);
    return res;
  }
  
  /**
   * Les étudiants affectés à un certain groupe de TD. prends en param un groupe
   * de td et retourne l'ensemble des étudiants affectés au groupe ou
   * <code>null</code> si le groupe n'existe pas
   */
  public Set<Etudiant> listeEtudiantsGroupeDirige(int groupe) {
    if (groupe >= 0 && groupe < listegroupeTd.size()) {
      return listegroupeTd.get(groupe);
    } else {
      System.out.println("Le groupe de TD spécifié n'existe pas.");
      return null;
    }
  }
  
  
  /**
   * Les étudiants affectés à un certain groupe de TP. prends en param un groupe
   * de tp et retourne l'ensemble des étudiants affectés au groupe ou
   * <code>null</code> si le groupe n'existe pas
   */
  public Set<Etudiant> listeEtudiantsGroupePratique(int groupe) {
    if (groupe >= 0 && groupe < listegroupeTp.size()) {
      return listegroupeTp.get(groupe);
    } else {
      System.out.println("Le groupe de TP spécifié n'existe pas.");
      
      return null;
    }
  }
  
  
  
  /**
   * prends en param une option et retourne l'ensemble des étudiants inscrits à
   * l'UE optionnelle ou null si l'UE n'est pas proposée en option.
   */
  @Override
  public Set<Etudiant> listeEtudiantsOption(UniteEnseignement option) {
    boolean optionExiste = listeUeoptionnel.containsKey(option);
    if (optionExiste) {
      return gestionFormation.listeEtudiantsOption(option);
    } else {
      System.out.println("L'UE optionnelle spécifiée n'existe pas.");
      return null;
    }
  }
}
