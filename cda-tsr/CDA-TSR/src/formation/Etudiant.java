package formation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Classe regroupant toutes les infos et les actions concernant les étudiants.
 *
 * @author reda.lahdoudi
 */
public class Etudiant extends GestionEtudiant implements InterEtudiant {
  /**
   * Instance de InformationPersonnelle, contenant nom, prénom, age et adresse
   * de chaque etudiants.
   *
   */
  private InformationPersonnelle infos;
  private static int nbEtu = 0;
  int n_etu;
  String mdp;
  public Map<String, Boolean> Allmessages;
  private int tp;
  private int td;
  
  boolean conected;
  
  
  /**
   * Constructeur Etudiant.
   *
   * @param infos
   * @param mdp
   * 
   */
  public Etudiant(InformationPersonnelle infos,
      String mdp/* , int tp, int td */) {
    
    this.infos = infos;
    Etudiant.nbEtu++;
    this.n_etu = Etudiant.nbEtu;
    this.mdp = mdp;
    
    this.Allmessages = new LinkedHashMap<>();
    this.Allmessages.put("Premier message d'initialisation !", false);
    this.conected = false;
  }
  
  public InformationPersonnelle getInfos() {
    return infos;
  }
  
  public boolean isConected() {
    return conected;
  }
  
  public void setConected(boolean conected) {
    this.conected = conected;
  }
  
  public void setInfos(InformationPersonnelle infos) {
    this.infos = infos;
  }
  
  
  
  public int inscription(InformationPersonnelle infos, String motDePasse) {
    return super.inscription(infos, motDePasse);
  }
  
  public Etudiant getEtudiantByInfo(InformationPersonnelle infos) {
    return super.getEtudiantByInfo(infos);
  }
  
  public Set<UniteEnseignement> enseignementsObligatoires() {
    return super.enseignementsObligatoires();
  }
  
  public Set<UniteEnseignement> enseignementsOptionnels() {
    return super.enseignementsOptionnels();
  }
  

  public String getNom() {
    return this.infos.getNom();
  }
  
  public String getPrenom() {
    return this.infos.getPrenom();
  }
  

  public int getAge() {
    return this.infos.getAge();
  }
  

  public void setAge(int age) {
    this.infos.setAge(age);
  }
  

  public String getAdresse() {
    return this.infos.getAdresse();
  }
  

  public void setAdresse(String adresse) {
    this.infos.setAdresse(adresse);
  }
  

  public int getN_etu() {
    return this.n_etu;
  }
  

  public void setN_etu(int n_etu) {
    this.n_etu = n_etu;
  }
  
  /**
   * Renvoie  le mot de passe.
   *
   * @return le mot de passe de l'étudiant.
   *
   */
  public String getMdp() {
    return this.mdp;
  }
  
  /**
   * Modifie Le mot de passe.
   *
   * @param mdp
   *
   */
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }
  
  /**
   *Retourne le groupe de TP.
   *
   * @return le groupe de TP de l'étudiant
   */
  public int getTp() {
    return this.tp;
  }
  
  /**
   * Modifie le groupe de TP de l'étudiant.
   *
   */
  public void setTp(int tp) {
    this.tp = tp;
  }
  
  /**
   * Retourne le groupe de TD de l'étudiant.
   *
   */
  public int getTd() {
    return this.td;
  }
  
  /**
   * Modifie le groupe de TD de l'étudiant.
   *
   */
  public void setTd(int td) {
    this.td = td;
  }
  
  /**
   * Méthode toString.
   * 
   */
  @Override
  public String toString() {
    return "Etudiant [Nom=" + this.getNom() + ", Prenom=" + this.getPrenom()
        + ", Age=" + this.getAge() + ", Adresse=" + this.getAdresse()
        + ", Numero Etudiant =" + this.getN_etu() + ", Mot de passe="
        + this.getMdp() + ", tp=" + this.getTp() + ", td=" + this.getTd() + "]";
  }
  
  /**
   * Méthode hashCode.
   * 
   */
  @Override
  public int hashCode() {
    return Objects.hash(infos, mdp, td, tp);
  }
  
  /**
   * Méthode equals.
   * 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Etudiant other = (Etudiant) obj;
    return Objects.equals(infos, other.infos) && Objects.equals(mdp, other.mdp)
        && td == other.td && tp == other.tp;
  }
  
  
  /************* ICI LES METHODES PROVENANT DE INTERETUDIANT. ************/
  
  
  @Override
  public boolean connexion(int numero, String motDePasse) {
    // TODO Auto-generated method stub
    return super.connexion(numero, motDePasse);
    
  }
  
  
  
  @Override
  public void deconnexion() throws NonConnecteException {
    // TODO Auto-generated method stub
    // si les infos ne sont pas pr�sent ou le mot de passe n'est pas pr�sent ,
    // ou les deux.
    // if (this.infos == null || this.mdp == null) {
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    this.setConected(false);
    
    // }
    // (r�initialisation des attributs par exemple)
    // this.infos = null;
    //// this.mdp = null;
    // this.td = 0;
    // this.tp = 0;
    /* this.inscriptionFinalisee=false; */// Pas encore d�fini
    
  }
  
  
  // TODO JAVADOC
  @Override
  public int nombreOptions() throws NonConnecteException {
    /*
     * ArrayList<String> ue = new ArrayList<String>(); return ue; // TODO
     * Auto-generated method stub
     *
     */
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    if (enseignementsOptionnels().isEmpty()) {
      return -1;
    }
    return enseignementsOptionnels().size();
  }
  
  
  
  // TODO JAVADOC
  @Override
  public boolean choisirOption(UniteEnseignement ue)
      throws NonConnecteException {
    
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    if (this.formation.listeEtudiantsOption(ue).size() <= ue
        .getCapaciteAccueil() && ue.isOptionnelle()) {
      this.formation.listeEtudiantsOption(ue).add(this);
      return true;
    }
    
    
    
    /*
     * if (this.infos == null || this.mdp == null) { return false; } int nombre
     * = nombreOptions(); ArrayList<String> ue = new ArrayList<String>();
     *
     * for(int i=0;i<ue.size();i++) { if(lang.get(i)>0) { // s'il existe au
     * moins une option l'etudiant peut choisir , sinon et si l'etudiant n'est
     * pas connect� renvoie false return true; } }
     *
     * // TODO Auto-generated method stub return false;
     */
    return false;
  }
  
  
  // TODO JAVADOC
  @Override
  public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
    // TODO Auto-generated method stub
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    int nbGrp = this.formation.nombreGroupesTravauxDiriges();
    
    for (int i = 0; i < nbGrp; i++) {
      for (Etudiant etu : formation.listeEtudiantsGroupeDirige(i + 1)) {
        if (this.equals(etu)) {
          return i;
        }
      }
    }
    
    return -1;
  }
  
  // TODO JAVADOC
  @Override
  public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
    // TODO Auto-generated method stub
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    
    int nbGrp = this.formation.nombreGroupesTravauxPratiques();
    
    for (int i = 0; i < nbGrp; i++) {
      for (Etudiant etu : formation.listeEtudiantsGroupePratique(i + 1)) {
        if (this.equals(etu)) {
          return i;
        }
      }
    }
    
    return -1;
  }
  
  // TODO JAVADOC
  @Override
  public Set<UniteEnseignement> enseignementsSuivis()
      throws NonConnecteException {
    // TODO Auto-generated method stub
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    
    Set<UniteEnseignement> listeUe = new LinkedHashSet<>();
    listeUe.addAll(formation.listeUeobligatoires);
    
    
    for (Map.Entry<UniteEnseignement, Integer> ueop : formation.listeUeoptionnel
        .entrySet()) {
      for (Etudiant etu : formation.listeEtudiantsOption(ueop.getKey())) {
        if (this.equals(etu)) {
          listeUe.add(ueop.getKey());
        }
      }
    }
    
    return listeUe;
  }
  
  
  
  // TODO JAVADOC
  @Override
  public List<String> listeTousMessages() throws NonConnecteException {
    // TODO Auto-generated method stub
    
    
    
    if (!this.isConected()) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    
    List<String> listMess = new ArrayList<>();
    
    for (String mess : Allmessages.keySet()) {
      listMess.add(mess);
    }
    
    return listMess;
  }
  
  
  // TODO JAVADOC
  @Override
  public List<String> listeMessageNonLus() throws NonConnecteException {
    // TODO Auto-generated method stub
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    
    List<String> listMessnl = new ArrayList<>();
    
    for (Map.Entry<String, Boolean> mess : Allmessages.entrySet()) {
      if (mess.getValue() == false) {
        listMessnl.add(mess.getKey());
      }
    }
    
    return listMessnl;
  }
  
  // TODO JAVADOC
  @Override
  public boolean inscriptionFinalisee() throws NonConnecteException {
    // TODO Auto-generated method stub
    if (!this.conected) {
      throw new NonConnecteException("L'étudiant n'est pas connecté.");
    }
    
    
    
    boolean inscriptionF = true;
    
    // OPTIONNEL
    for (Map.Entry<UniteEnseignement, Integer> ueop : formation.listeUeoptionnel
        .entrySet()) {
      for (Etudiant etu : formation.listeEtudiantsOption(ueop.getKey())) {
        if (!this.equals(etu)) {
          inscriptionF = false;
        }
      }
    }
    // td et tp
    
    for (Set<Etudiant> listTd : formation.listegroupeTd) {
      for (Etudiant etu : listTd) {
        
      }
    }
    
    
    return inscriptionF;
  }
  
  
  
}

