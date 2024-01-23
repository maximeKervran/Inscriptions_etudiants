package ui;

import formation.Formation;
import formation.UniteEnseignement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import formation.Etudiant;
import formation.GestionEtudiant;
import formation.Formation;
import formation.UniteEnseignement;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * Le contr�leur associ� � la fen�tre d�finie dans formation.fxml.
 *
 * @author Eric Cariou
 */
public class FormationControleur {
  private Formation formationActuelle;
  
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
  private TextField entreeCapaciteAccueil;
  
  @FXML
  private TextField entreeEmailResponsableFormation;
  
  @FXML
  private TextField entreeGroupeTDEtudiant;
  
  @FXML
  private TextField entreeGroupeTPEtudiant;
  
  @FXML
  private TextField entreeNomEtudiant;
  
  @FXML
  private TextField entreeNomFormation;
  
  @FXML
  private TextField entreeNomResponsableFormation;
  
  @FXML
  private TextField entreeNomResponsableUE;
  
  @FXML
  private TextField entreeNomUE;
  
  @FXML
  private TextField entreeNombreChoixOptions;
  
  @FXML
  private TextField entreePrenomEtudiant;
  
  @FXML
  private TextField entreeTailleGroupeTD;
  
  @FXML
  private TextField entreeTailleGroupeTP;
  
  @FXML
  private Label labelListeEtudiants;
  
  @FXML
  private Label labelNbGroupesTD;
  
  
  @FXML
  private Label labelNbGroupesTP;
  
  @FXML
  private ListView<?> listeEtudiants;
  
  @FXML
  private ListView<UniteEnseignement> listeUEObligatoires;
  
  @FXML
  private ListView<UniteEnseignement> listeUEOptionnelles;
  
  @FXML
  private ToggleGroup obligation;
  
  @FXML
  private RadioButton radioBoutonObligatoire;
  
  @FXML
  private RadioButton radioBoutonOptionnelle;
  
  @FXML
  private MenuItem menuCharger;
  
  @FXML
  private MenuItem menuSauvegarder;
  
  @FXML
  private MenuItem menuQuitter;
  
  @FXML
  private MenuItem menuApropos;
  
  @FXML
  void actionBoutonAffectationAutomatique(ActionEvent event) {
    if (formationActuelle == null) {
      System.out.println("Veuillez d'abord créer une formation.");
      return;
    }
    
    // Appel de la méthode d'affectation automatique de la formation
    formationActuelle.attribuerAutomatiquementGroupes();
    
    // Mise à jour des labels sur le nombre de groupes de TD et TP
    labelNbGroupesTD.setText("Nombre de groupes de TD : "
        + formationActuelle.nombreGroupesTravauxDiriges());
    labelNbGroupesTP.setText("Nombre de groupes de TP : "
        + formationActuelle.nombreGroupesTravauxPratiques());
    
    System.out.println("Affectation automatique terminée.");
  }
  
  
  
  @FXML
  void actionBoutonAffectationManuelleGroupes(ActionEvent event) {
    if (formationActuelle == null) {
      System.out.println("Veuillez d'abord créer une formation.");
      return;
    }
    
    Etudiant etudiantSelectionne =
        (Etudiant) listeEtudiants.getSelectionModel().getSelectedItem();
    
    if (etudiantSelectionne == null) {
      System.out.println(
          "Veuillez sélectionner un étudiant pour l'affectation manuelle.");
      return;
    }
    
    int nouveauGroupeTd = Integer.parseInt(entreeGroupeTDEtudiant.getText());
    int nouveauGroupeTp = Integer.parseInt(entreeGroupeTPEtudiant.getText());
    
    formationActuelle.changerGroupe(etudiantSelectionne, nouveauGroupeTd,
        nouveauGroupeTp);
    
    entreeGroupeTDEtudiant.clear();
    entreeGroupeTPEtudiant.clear();
    
    System.out
        .println("Affectation manuelle effectuée pour " + etudiantSelectionne);
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTD(ActionEvent event) {
    String numeroGroupeTD = entreeGroupeTDEtudiant.getText();
    if (!numeroGroupeTD.isEmpty()) {
      labelListeEtudiants
          .setText("Étudiants du groupe de TD " + numeroGroupeTD);
      listeEtudiants.getItems()
          .setAll(formationActuelle.getEtudiantsParGroupeTD(numeroGroupeTD));
    } else {
      System.out.println("Cet étudiant n'est pas dans un groupe de TD");
    }
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsGroupeTP(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherEtudiantsUEOptionnelle(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherTousEtudiants(ActionEvent event) {
    labelListeEtudiants.setText("Tous les étudiants inscrits");
    listeEtudiants.getItems().setAll(formationActuelle.getListeEtudiants());
    listeEtudiants.getItems().setAll(formationActuelle.getlisteEtudiants());
  }
  
  
  
  @FXML
  void actionBoutonCreerFormation(ActionEvent event) {
    String nomFormation = entreeNomFormation.getText();
    String nomResponsable = entreeNomResponsableFormation.getText();
    String emailResponsable = entreeEmailResponsableFormation.getText();
    
    if (nomFormation.isEmpty() || nomResponsable.isEmpty()
        || emailResponsable.isEmpty()) {
      System.out.println(
          "Veuillez fournir toutes les informations nécessaires pour créer la formation.");
      return;
    }
    
    formationActuelle = new Formation();
    formationActuelle.creerFormation(nomFormation, nomResponsable,
        emailResponsable);
    
    System.out.println("Nouvelle formation créée : " + nomFormation);
    
    entreeNomFormation.clear();
    entreeNomResponsableFormation.clear();
    entreeEmailResponsableFormation.clear();
    
    
    
  }
  
  @FXML
  void actionBoutonCreerNouvelleUE(ActionEvent event) {
    
    if (formationActuelle == null) {
      System.out.println("Veuillez d'abord créer une formation.");
      return;
    }
    
    String nomUe = entreeNomUE.getText();
    String nomResponsableUe = entreeNomResponsableUE.getText();
    String cap = entreeCapaciteAccueil.getText();
    boolean optionnelle = radioBoutonOptionnelle.isSelected();
    
    if (optionnelle) {
      if (nomUe.isEmpty() || nomResponsableUe.isEmpty() || cap.isEmpty()) {
        System.out.println("Veuillez fournir toutes les "
            + "informations nécessaires pour créer une UE optionnelle.");
        return;
      }
      int capaciteAccueil = Integer.parseInt(cap);
      
      UniteEnseignement nouvelleUe =
          new UniteEnseignement(nomUe, nomResponsableUe, true, capaciteAccueil);
      
      if (formationActuelle.ajouterEnseignementOptionnel(nouvelleUe,
          capaciteAccueil)) {
        System.out.println("Nouvelle UE optionnelle créée : " + nomUe);
        listeUEOptionnelles.getItems().add(nouvelleUe);
      } else {
        System.out
            .println("UE déjà existante dans la liste des UE optionnelles");
      }
    } else {
      if (nomUe.isEmpty() || nomResponsableUe.isEmpty()) {
        System.out.println("Veuillez fournir toutes les "
            + "informations nécessaires pour créer une UE obligatoire.");
        return;
      }
      UniteEnseignement nouvelleUe =
          new UniteEnseignement(nomUe, nomResponsableUe, false);
      if (formationActuelle.ajouterEnseignementObligatoire(nouvelleUe)) {
        System.out.println("Nouvelle UE obligatoire créée : " + nomUe);
        listeUEObligatoires.getItems().add(nouvelleUe);
      } else {
        System.out
            .println("UE déjà existante dans la liste des UE obligatoires");
      }
    }
    
    entreeNomUE.clear();
    entreeNomResponsableUE.clear();
    entreeCapaciteAccueil.clear();
    
  }
  
  @FXML
  void actionBoutonNombreChoixOptions(ActionEvent event) {
    String nombre = entreeNombreChoixOptions.getText();
    
    
    if (nombre.isEmpty()) {
      System.out.println("Veuillez fournir le nombre maximal d'options.");
      return;
    }
    int nbOption = Integer.parseInt(nombre);
    
    if (formationActuelle.getNombreOptions() != -1 || nbOption <= 0) {
      System.out.println(
          "Le nombre d'options doit être plus grand que 1 ou a déjà été défini.");
      entreeNombreChoixOptions.clear();
      return;
    }
    formationActuelle.definirNombreOptions(nbOption);
    
    System.out
        .println("nombre d'option maximal pour chaque étudiant : " + nbOption);
    
    entreeNombreChoixOptions.clear();
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTD(ActionEvent event) {
    String taille = entreeTailleGroupeTD.getText();
    
    
    if (taille.isEmpty()) {
      System.out
          .println("Veuillez fournir la taille maximale d'un groupe de TD.");
      return;
    }
    int tailleTd = Integer.parseInt(taille);
    
    if (formationActuelle.getTailleGroupeDirige() != -1 || tailleTd <= 0) {
      System.out.println("Le nombre maximal d'étudiants dans un groupe de TD "
          + "doit être plus grand que 1 ou a déjà été défini.");
      entreeTailleGroupeTD.clear();
      return;
    }
    formationActuelle.setTailleGroupeDirige(tailleTd);
    
    System.out.println("taille max pour les groupes de TD : " + tailleTd);
    
    entreeTailleGroupeTD.clear();
  }
  
  @FXML
  void actionBoutonSetTailleGroupeTP(ActionEvent event) {
    String taille = entreeTailleGroupeTP.getText();
    
    
    if (taille.isEmpty()) {
      System.out
          .println("Veuillez fournir la taille maximale d'un groupe de TP.");
      return;
    }
    int tailleTp = Integer.parseInt(taille);
    
    if (formationActuelle.getTailleGroupePratique() != -1 || tailleTp <= 0) {
      System.out.println("Le nombre maximal d'étudiants dans un groupe de TP "
          + "doit être plus grand que 1 ou a déjà été défini.");
      entreeTailleGroupeTP.clear();
      return;
    }
    formationActuelle.setTailleGroupePratique(tailleTp);
    
    System.out.println("taille max pour les groupes de TP : " + tailleTp);
    
    entreeTailleGroupeTP.clear();
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    afficherAPropos();
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    chargerDonnees();
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    quitterApplication();
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    sauvegarderDonnees();
  }
  
  
  private void afficherAPropos() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("À propos");
    alert.setHeaderText("Votre application");
    alert.setContentText(
        "Cette application permet de gérer une formation avec les matières, les groupes de travail et les étudiants inscrits");
    alert.showAndWait();
  }
  
  private void chargerDonnees() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir un fichier de sauvegarde");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Sauvegarde_Formation", "*.dat"),
        new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
    
    File selectedFile = fileChooser.showOpenDialog(null);
    
    if (selectedFile != null) {
      try {
        Formation formation =
            GestionSauvegarde.chargerDonnees(selectedFile.getAbsolutePath());
        
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("Erreur lors du chargement des données.");
      }
    }
  }
  
  private void sauvegarderDonnees() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir un emplacement pour sauvegarder");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichiers de sauvegarde", "*.dat"),
        new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
    
    File selectedFile = fileChooser.showSaveDialog(null);
    
    if (selectedFile != null) {
      try {
        gestionSauvegarde.sauvegarderDonnees(selectedFile.getAbsolutePath(),
            formationActuelle);
        System.out.println("Sauvegarde des données dans un fichier.");
        
      } catch (IOException e) {
        System.out.println("Erreur lors de la sauvegarde des données.");
      }
    }
  }
  
  private void quitterApplication() {
    Platform.exit();
    System.exit(0);
  }
  
  @FXML
  void actionSelectionEtudiant(MouseEvent event) {
    
  }
  
  @FXML
  void actionSelectionUEObligatoire(MouseEvent event) {
    UniteEnseignement ueSelectionnee =
        listeUEObligatoires.getSelectionModel().getSelectedItem();
    
    if (ueSelectionnee != null) {
      entreeNomUE.setText(ueSelectionnee.getNom());
      entreeNomResponsableUE.setText(ueSelectionnee.getNomEnseignant());
      entreeCapaciteAccueil
          .setText(String.valueOf(ueSelectionnee.getCapaciteAccueil()));
      
      if (ueSelectionnee.isOptionnelle()) {
        radioBoutonOptionnelle.setSelected(true);
        radioBoutonObligatoire.setSelected(false);
      } else {
        radioBoutonObligatoire.setSelected(true);
        radioBoutonOptionnelle.setSelected(false);
      }
    }
  }
  
  @FXML
  void actionSelectionUEOptionnelle(MouseEvent event) {
    UniteEnseignement ueSelectionnee =
        listeUEOptionnelles.getSelectionModel().getSelectedItem();
    
    if (ueSelectionnee != null) {
      entreeNomUE.setText(ueSelectionnee.getNom());
      entreeNomResponsableUE.setText(ueSelectionnee.getNomEnseignant());
      entreeCapaciteAccueil
          .setText(String.valueOf(ueSelectionnee.getCapaciteAccueil()));
      
      if (ueSelectionnee.isOptionnelle()) {
        radioBoutonOptionnelle.setSelected(true);
        radioBoutonObligatoire.setSelected(false);
      } else {
        radioBoutonObligatoire.setSelected(true);
        radioBoutonOptionnelle.setSelected(false);
      }
    }
  }
  
  
  @FXML
  void initialize() {
    
  }
  
}
