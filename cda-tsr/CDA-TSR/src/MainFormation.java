import formation.Formation;
import formation.Responsable;
import formation.UniteEnseignement;
import java.io.IOException;



/**
 * Main de la formation.
 * 
 *
 * @author Soilihi SAADI
 */
public class MainFormation {
  /**
   * déclaration de la classe main.
   * 
   *
   * @author Soilihi SAADI
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // System.out.println("\nAppuyez sur Entrée pour terminer le programme
    // ...");
    // try {
    // System.in.read();
    // } catch (IOException e) {
    // System.err.println("Vous avez réussi à casser le clavier : " + e);
    // }
    Responsable respformation;
    Formation l3;
    UniteEnseignement ueobligatoire1;
    final UniteEnseignement ueobligatoire2;
    final UniteEnseignement ueobligatoire3;
    final UniteEnseignement ueoptionnelles1;
    final UniteEnseignement ueoptionnelles2;
    
    
    
    respformation = new Responsable("Dark Vador", "dark.vador@empire.com");
    
    l3 = new Formation("L3 Informatique", respformation.getNomResponsable(),
        respformation.getAdresse());
    
    ueobligatoire1 = new UniteEnseignement("Java 2", "Mickael Kerboeuf",false);
    ueobligatoire2 =
        new UniteEnseignement("Conception d'Applications", "Eric Cariou",false);
    ueobligatoire3 =
        new UniteEnseignement("Programmation C Avancée", "Stéphane Rubini",false);
    
    ueoptionnelles1 =
        new UniteEnseignement("Objets connectés et robotique", "Yvon Autret",true);
    ueoptionnelles2 =
        new UniteEnseignement("Administration Système", "Laurent Nana",true);
    
    l3.ajouterEnseignementObligatoire(ueobligatoire1);
    l3.ajouterEnseignementObligatoire(ueobligatoire2);
    l3.ajouterEnseignementObligatoire(ueobligatoire3);
    
    l3.ajouterEnseignementOptionnel(ueoptionnelles1, 3);
    l3.ajouterEnseignementOptionnel(ueoptionnelles2, 3);
    
    l3.setTailleGroupeDirige(3);
    l3.setTailleGroupePratique(2);
    
    l3.definirNombreOptions(1);
    
    
    
  }
}

