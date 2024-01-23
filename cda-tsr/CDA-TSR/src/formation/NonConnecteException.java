package formation;

/**
 * Exception levée quand l'étudiant essaye d'accéder à des services alors qu'il
 * n'est pas connecté.
 *
 * @author Eric Cariou
 */
public class NonConnecteException extends Exception {
  
  public NonConnecteException(String message) {
   
  }
  
  
  /**
   * Identifiant de sérialisation.
   */
  private static final long serialVersionUID = -1842867585789051307L;
  
}
