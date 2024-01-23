package formation;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Classe regroupant toutes les actions concernant les �tudiants.
 *
 * @author taha.zeouine
 * @author reda.lahdoudi
 */



public class GestionEtudiant {
  
  // private InformationPersonnelle infos;
  // private static int n_etu = 22005264;
  // private String mdp;
  private int tp;
  private int td;
  private int nb_options;
  Formation formation;
  
  /**
   * Liste des instances d'�tudiants pour verifier nottament � l'inscription.
   */
  List<Etudiant> listEtu = new ArrayList<>();
  
  public List<Etudiant> getlisteEtudiants() {
    return this.listEtu;
  }
  
  
  /**
   * Crée le compte d'un étudiant à partir de ses informations personnelles et
   * de son mot de passe puis retourne son numéro d'étudiant généré
   * automatiquement.
   *
   * @param infos les informations personnelles de l'étudiant
   * @param motDePasse le mot de passe de l'étudiant pour se connecter (la
   *        chaine doit être non vide)
   * @return le numéro unique de l'étudiant ou -1 en cas de problème
   */
  public int inscription(InformationPersonnelle infos, String motDePasse) {
    // v�rifier si les informations que l'etudiant a inserer ne sont pas deja
    // present
    Etudiant nouvelEtudiant = new Etudiant(infos, motDePasse);
    
    if (listEtu.add(nouvelEtudiant)) {
      
      System.out.println("�tudiant ajout� avec succ�s !");
      
      return nouvelEtudiant.getN_etu();
    } else {
      System.out.println("�tudiant d�j� existant !");
      return -1;
    }
  }
  
  
  
  /**
   * Retourne un étudiant en fonction des infos passées en paramètre.
   * 
   * @param infos
   *
   * @return un etudiant si il existe, <code>null<code> sinon.
   */
  public Etudiant getEtudiantByInfo(InformationPersonnelle infos) {
    for (Etudiant etudiant : this.listEtu) {
      if (etudiant.getInfos() == infos) {
        return etudiant;
      }
    }
    return null; // Retourne null si l'�tudiant n'est pas trouv�
  }
  
  
  /**
   * Retourne un étudiant en fonction des son numéro passées en paramètre.
   * 
   * @param numero
   *
   * @return un etudiant si il existe, <code>null<code> sinon.
   */
  public Etudiant getEtudiantByNumEtu(int numero) {
	    for (Etudiant etudiant : this.listEtu) {
	      if (etudiant.getN_etu() == numero) {
	        return etudiant;
	      }
	    }
	    return null; // Retourne null si l'�tudiant n'est pas trouv�
	  }
  
  
  /*
   * public int getTp() { return tp; }
   * 
   * 
   * 
   * public void setTp(int tp) { this.tp = tp; }
   * 
   * 
   * 
   * public int getTd() { return td; }
   * 
   * 
   * 
   * public void setTd(int td) { this.td = td; }
   */
  
  
  public int getNb_options() {
    return nb_options;
  }
  
  
  
  public void setNb_options(int nb_options) {
    this.nb_options = nb_options;
  }
  
  
  
  public Set<Etudiant> getListEtu() {
    Set<Etudiant> ensembleEtudiants = new HashSet<>(this.listEtu);
    return ensembleEtudiants;
  }
  
  
  
  public void setListEtu(Set<Etudiant> listEtu) {
    List<Etudiant> listeEtudiants = new ArrayList<>(listEtu);
    this.listEtu = listeEtudiants;
  }
  
  
  /**
   * Permet de connecter  un étudiant.
   *
   * @param numero
   * 
   * @param motDePasse
   * 
   * @return un booléan précisant si la connexion a réussie ou non
   */
  public boolean connexion(int numero, String motDePasse) {
    // TODO Auto-generated method stub
    if (this.listEtu.isEmpty()) {
      System.out.println("LISTE ETUDIANT VIDE");
    }
    System.out.println(numero + "    " + motDePasse );
	
    for (Etudiant etudiant : this.listEtu) {
      System.out.println(etudiant.getN_etu() + "    " + etudiant.getMdp() + "  " + etudiant
          .isConected());
    	
      if (etudiant.n_etu == numero && etudiant.mdp.equals(motDePasse)) {
        etudiant.setConected(true);
        return true; // Connexion r�ussie
      }
    }
    return false; // Connexion �chou�e
  }
  
  /**
   * L'ensemble des unités d'enseignement obligatoires de l'année de formation.
   *
   * @return l'ensemble des UE obligatoires
   */
  public Set<UniteEnseignement> enseignementsObligatoires() {
    // TODO Auto-generated method stub
    return this.formation.listeUeobligatoires;
  }
  
  /**
   * L'ensemble des unités d'enseignement optionnelles de l'année de formation.
   *
   * @return l'ensemble des UE optionnelles
   */
  public Set<UniteEnseignement> enseignementsOptionnels() {
    // TODO Auto-generated method stub
    return this.formation.listeUeoptionnel.keySet();
  }
  
  
  
}
