package formation;

/**
 * Classe pour définir un responsable avec son nom et son email.
 *
 * @author Maxime Kervran
 *
 */
public class Responsable {
  private String nomResponsable;
  private String adresse;
  
  
  public Responsable(String nomresp, String adresse) {
    this.nomResponsable = nomresp;
    this.adresse = adresse;
  }
  
  public String getNomResponsable() {
    return nomResponsable;
  }
  
  public void setNomResponsable(String nomResponsable) {
    this.nomResponsable = nomResponsable;
  }
  
  public String getAdresse() {
    return adresse;
  }
  
  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }
}
