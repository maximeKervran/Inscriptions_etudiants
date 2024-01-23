package ui;

import formation.GestionEtudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import formation.Etudiant;
import formation.InformationPersonnelle;
import formation.NonConnecteException;
import formation.UniteEnseignement;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Le contrôleur associé à la fenêtre définie dans etudiants.fxml.
 *
 * @author Eric Cariou
 */
public class EtudiantsControleur {
  
  private final GestionEtudiant Getu = new GestionEtudiant();
  
  
  
  @FXML
  private ResourceBundle resources;
  
  @FXML
  private URL location;
  
  @FXML
  private CheckBox checkInscriptionFinalisee;
  
  @FXML
  private TextField entreeAdresseEtudiant;
  
  @FXML
  private TextField entreeAgeEtudiant;
  
  @FXML
  private TextField entreeGroupeTD;
  
  @FXML
  private TextField entreeGroupeTP;
  
  @FXML
  private TextField entreeMotDePasseEtudiant;
  
  @FXML
  private TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNombreOptions;
  
  @FXML
  private TextField entreeNumeroEtudiant;
  
  @FXML
  private TextField entreePrenomEtudiant;
  
  @FXML
  private ListView<String> listeMessagesNonLus;
  
  @FXML
  private ListView<String> listeTousMessages;
  
  @FXML
  private ListView<String> listeUEOptionnellesFormation;
  
  @FXML
  private ListView<String> listeUESuiviesEtudiant;
  
  @FXML
  private TextArea zoneTexteContenuMessage;
  
  @FXML
  void actionBoutonChoisirOption(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonConnexion(ActionEvent event) throws NonConnecteException {
    boolean connexionReu =
        Getu.connexion(Integer.parseInt(entreeNumeroEtudiant.getText()),
            entreeMotDePasseEtudiant.getText());
    
    if (connexionReu == true) {
      for (Etudiant etu : Getu.getListEtu()) {
        if (etu.isConected() == true) {
          this.entreeGroupeTD.setText("" + etu.getNumeroGroupeTravauxDiriges());
          this.entreeGroupeTP
              .setText("" + etu.getNumeroGroupeTravauxPratiques());
        }
      }
      
      
      
      System.out.println("Connexion réussie !");
      System.out.println("");
    } else {
      System.out.println("Connexion échouée !");
      System.out.println("");
    }
  }
  
  @FXML
  void actionBoutonDeconnexion(ActionEvent event) throws NonConnecteException {
    for (Etudiant etu : Getu.getlisteEtudiants()) {
      if (etu.isConected() == true) {
        etu.deconnexion();
      }
    }
  }
  
  @FXML
  void actionBoutonInscription(ActionEvent event) {
    final InformationPersonnelle infos;
    infos = new InformationPersonnelle(entreeNomEtudiant.getText(),
        entreePrenomEtudiant.getText(), entreeAdresseEtudiant.getText(),
        Integer.parseInt(entreeAgeEtudiant.getText()));
    final Etudiant etudiant;
    
    if (entreeNomEtudiant.getText().isEmpty()
        || entreePrenomEtudiant.getText().isEmpty()
        || entreeAdresseEtudiant.getText().isEmpty()
        || entreeAgeEtudiant.getText().isEmpty()
        || entreeMotDePasseEtudiant.getText().isEmpty()) {
      System.out.println(
          "Veuillez fournir toutes les informations nÃ©cessaires pour vous inscrire.");
      return;
    }
    
    int reuInscr = Getu.inscription(infos, entreeMotDePasseEtudiant.getText());
    if (reuInscr == -1) {
      System.out.println("L'inscription a échouée !");
      System.out.println("");
    }
    etudiant = Getu.getEtudiantByNumEtu(reuInscr);
    
    entreeNumeroEtudiant.setText("" + etudiant.getN_etu());
    System.out.println("Nouvel étudiant crée !");
    System.out.println("Nom : " + etudiant.getInfos().getNom());
    System.out.println("Prénom : " + etudiant.getInfos().getPrenom());
    System.out.println("Adresse : " + etudiant.getInfos().getAdresse());
    System.out.println("Age : " + etudiant.getInfos().getAge());
    System.out.println("Numéro étudiant : " + reuInscr);
    System.out.println("");
    
    
    
  }
  
  @FXML
  void actionBoutonRafraichirListesMessages(ActionEvent event)
      throws NonConnecteException {
    listeTousMessages.getItems().clear();
    listeMessagesNonLus.getItems().clear();
    for (Etudiant etu : Getu.getlisteEtudiants()) {
      if (etu.isConected() == true) {
        for (String mess : etu.listeTousMessages()) {
          listeTousMessages.getItems().add(mess);
        }
        
      }
    }
    
    for (Etudiant etu : Getu.getlisteEtudiants()) {
      if (etu.isConected() == true) {
        for (Map.Entry<String, Boolean> mess : etu.Allmessages.entrySet()) {
          if (mess.getValue() == false) {
            listeMessagesNonLus.getItems().add(mess.getKey());
          }
        }
        
      }
    }
    
  }
  
  @FXML
  void actionSelectionMessageListeMessagesNonLus(MouseEvent event)
      throws NonConnecteException {
    
    
    zoneTexteContenuMessage
        .setText(listeMessagesNonLus.getSelectionModel().getSelectedItem());
    for (Etudiant etu : Getu.getlisteEtudiants()) {
      if (etu.isConected() == true) {
        for (Map.Entry<String, Boolean> mess : etu.Allmessages.entrySet()) {
          if (mess.getKey() == listeMessagesNonLus.getSelectionModel()
              .getSelectedItem()) {
            mess.setValue(true);
          }
        }
        
      }
    }
  }
  
  @FXML
  void actionSelectionMessageListeTousMessages(MouseEvent event) {
    zoneTexteContenuMessage
        .setText(listeTousMessages.getSelectionModel().getSelectedItem());
    for (Etudiant etu : Getu.getlisteEtudiants()) {
      if (etu.isConected() == true) {
        for (Map.Entry<String, Boolean> mess : etu.Allmessages.entrySet()) {
          if (mess.getKey() == listeTousMessages.getSelectionModel()
              .getSelectedItem()) {
            mess.setValue(true);
          }
        }
        
      }
    }
    
  }
  
  @FXML
  void initialize() {}
  
}
