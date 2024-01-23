package formation;

import java.util.Objects;

/**
 * Classe regroupant toutes les infos et les actions concernant les �tudiants.
 *
 * @author soilihi.saadi
 */

// A DEFINIR
public class UniteEnseignement {
  private String nom;
  private String nomenseignant;
  private boolean optionnelle;
  private int capaciteAccueil;
  
  
  /**
   * Constructeur, getter et setter.
   */
  public UniteEnseignement(String nom, String nomenseignant,
      boolean optionnelle) {
    super();
    this.nom = nom;
    this.nomenseignant = nomenseignant;
    this.optionnelle = optionnelle;
    
  }
  
  /**
   * surcharge pour optionnel.
   */
  public UniteEnseignement(String nom, String nomenseignant,
      boolean optionnelle, int capaciteAccueil) {
    super();
    this.nom = nom;
    this.nomenseignant = nomenseignant;
    this.optionnelle = optionnelle;
    this.capaciteAccueil = capaciteAccueil;
  }
  
  
  public String getNom() {
    return this.nom;
  }
  
  public String getNomEnseignant() {
    return this.nomenseignant;
  }
  
  public void setNomEnseignant(String nomenseignant) {
    this.nomenseignant = nomenseignant;
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }
  
  public boolean isOptionnelle() {
    return optionnelle;
  }
  
  public int getCapaciteAccueil() {
    return optionnelle ? capaciteAccueil : 0;
  }
  
  /**
   * capaciteAccueil.
   */
  public void setCapaciteAccueil(int capaciteAccueil) {
    if (optionnelle) {
      this.capaciteAccueil = capaciteAccueil;
    }
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(nom, nomenseignant, optionnelle);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UniteEnseignement other = (UniteEnseignement) obj;
    return Objects.equals(nom, other.nom)
        && Objects.equals(nomenseignant, other.nomenseignant)
        && optionnelle == other.optionnelle;
  }
  
  @Override
  public String toString() {
    return "UniteEnseignement [nom=" + nom + ", nomenseignant=" + nomenseignant
        + ", optionnelle=" + optionnelle + "]";
  }
}
