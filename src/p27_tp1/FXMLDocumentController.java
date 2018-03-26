/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p27_tp1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import static p27_tp1.Utilitaires.AjouterLigneTab2D;
import static p27_tp1.Utilitaires.CalculerNoteFinale;
import static p27_tp1.Utilitaires.ModifierLigneTab2D;
import static p27_tp1.Utilitaires.SupprimerLigneTab2D;
import static p27_tp1.Utilitaires.maxEval;
import static p27_tp1.Utilitaires.minEval;
import static p27_tp1.Utilitaires.moyenneEval;

/**
 *
 * @author Rostand Prud'Homme
 * @author Antoine Tardif
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private ListView<String> lsvDA;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField txfDA;
    @FXML
    private TextField txfExam1;
    @FXML
    private TextField txfExam2;
    @FXML
    private TextField txfTP1;
    @FXML
    private TextField txfTP2;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Label lblExam1;
    @FXML
    private Label lblDA;
    @FXML
    private Label lblExam2;
    @FXML
    private Label lblTP1;
    @FXML
    private Label lblTP2;
    @FXML
    private ImageView imgPoubelle;
    @FXML
    private ComboBox<String> cmbTri;
    @FXML
    private Button btnQuitter;
    private static final int NB_ELEVES = 25;
    private static final int NB_EVALS = 4;
    private static int nbEleves = 0; //Compteur du nombre d'élèves
    private static int[][] tabNotes = new int[NB_ELEVES][NB_EVALS + 2];
    private static int[] index = new int[NB_ELEVES];
    //Pour accéder au tableau utiliser des constantes pour les colonnes, par exemple:
    private static final int DA = 0;
    private static final int EXA1 = 1;
    private static boolean modification = false;
    String nomFic = "ficher/notes.txt";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lireFichier("ficher/notes.txt");
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Ajouter les DA dans le lsv
        for (int indEleve = 0; indEleve < NB_ELEVES; indEleve++) {
            lsvDA.getItems().addAll(Integer.toString(tabNotes[indEleve][0]));  
        }
        
        //Ajouter les infos de l'élèves dans les txf lors d'un clic
        lsvDA.getSelectionModel().selectedItemProperty().addListener(e -> {
            int indexSelection = lsvDA.getSelectionModel().getSelectedIndex();

            txfDA.setText(Integer.toString(tabNotes[indexSelection][0]));
            txfExam1.setText(Integer.toString(tabNotes[indexSelection][1]));
            txfExam2.setText(Integer.toString(tabNotes[indexSelection][2]));
            txfTP1.setText(Integer.toString(tabNotes[indexSelection][3]));
            txfTP2.setText(Integer.toString(tabNotes[indexSelection][4]));
        });
        
        nbEleves = tabNotes.length;
        System.out.println(nbEleves);
    }
    public static void ecrireFichier(String nomFichier) throws IOException{
        /**
         * Écrire dans un fichier
         * @param nomFichier String contenant le chemin vers le fichier
         */
        
        //Ouvrir le fichier en écriture
        PrintWriter objSortie = new PrintWriter(new FileWriter(nomFichier));
        
        //Boucler sur le tableau pour ajouter les infos dans le fichier
        for (int i = 0; i < nbEleves; i++) {
            objSortie.println(tabNotes[i][0] + " " + tabNotes[i][1] + " " +
                               tabNotes[i][2] + " " + tabNotes[i][3] + " " +
                               tabNotes[i][4] + " " + tabNotes[i][5]);
        }
        
        //Fermer le fichier
        objSortie.close();
    }   
    public static void lireFichier(String nomFichier) throws FileNotFoundException, IOException{
        //Lire le fichier
        BufferedReader objEntree = new BufferedReader(new FileReader(nomFichier));
    
        String ligne; //ligne du ficher
        int indLigne = 0;
        //Lire chacune des lignes jusqu'a atteindre la fin
        while ((ligne = objEntree.readLine()) != null) {
            //Splitter la ligne et l'insérer dans un tab    
            String tabTemp[] = ligne.split(" ");                
            
            //Convertir en entiers    
            for (int i = 0; i <= 5; i++){
                tabNotes[indLigne][i] = Integer.parseInt(tabTemp[i]);
            }
            indLigne++;
        }
        objEntree.close();
    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {
        modification = false;
        
        //Vérifier si le max d'élèves est atteint
        if (nbEleves < NB_ELEVES) {
            txfDA.setEditable(true);
            txfExam1.setEditable(true);
            txfExam2.setEditable(true);
            txfTP1.setEditable(true);
            txfTP2.setEditable(true);
            btnOK.setDisable(false);
            btnAnnuler.setDisable(false);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention!");
            alert.setContentText("Le maximum d'élèves est déjà atteint (25).");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) {
        
        if (nbEleves > 0) {
            //Stocker l'index du DA sélectionné
            int indexSelection = lsvDA.getSelectionModel().getSelectedIndex();
            
            //Supprimer l'élève
            nbEleves = SupprimerLigneTab2D(tabNotes, indexSelection, nbEleves);
            
            //Enlever le DA du lsv
            lsvDA.getItems().remove(indexSelection);
            
            //Décrémenter le nombre d'éleves
            nbEleves--;
        }
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {
        modification = true;
        
        txfDA.setEditable(true);
        txfExam1.setEditable(true);
        txfExam2.setEditable(true);
        txfTP1.setEditable(true);
        txfTP2.setEditable(true);
        btnOK.setDisable(false);
        btnAnnuler.setDisable(false);
    }

    @FXML
    private void btnOKAction(ActionEvent event) {
        if (modification) {
            //Contient les infos à modifier
            int[] tabLigne = new int[6];
            
            //Ajouter les infos au tableau temporaire
            tabLigne[0] = Integer.parseInt(txfDA.getText());
            tabLigne[1] = Integer.parseInt(txfExam1.getText());
            tabLigne[2] = Integer.parseInt(txfExam2.getText());
            tabLigne[3] = Integer.parseInt(txfTP1.getText());
            tabLigne[4] = Integer.parseInt(txfTP2.getText());
            //Calculer la note finale
            tabLigne[5] = CalculerNoteFinale(tabLigne);
            
            //Stocker l'index du DA sélectionné
            int indexSelection = lsvDA.getSelectionModel().getSelectedIndex();
            
            //Modifier le tab à l'index passé en paramètre
            ModifierLigneTab2D(tabNotes, tabLigne, indexSelection);
            
            //Mettre à jour le DA dans le ListView
            lsvDA.getItems().set(indexSelection, Integer.toString(tabNotes[indexSelection][0]));  
            
        } else {
            //Contient les infos à ajouter
            int[] tabLigne = new int[6];
            
            //Ajouter les infos au tableau temporaire
            tabLigne[0] = Integer.parseInt(txfDA.getText());
            tabLigne[1] = Integer.parseInt(txfExam1.getText());
            tabLigne[2] = Integer.parseInt(txfExam2.getText());
            tabLigne[3] = Integer.parseInt(txfTP1.getText());
            tabLigne[4] = Integer.parseInt(txfTP2.getText());
            //Calculer la note finale
            tabLigne[5] = CalculerNoteFinale(tabLigne);
            
            //Ajouter les infos à la fin du tableau
            nbEleves = AjouterLigneTab2D(tabNotes, tabLigne, nbEleves);
            
            //Incrémenter le nombre d'élèves
            nbEleves++;
            
            //Ajouter au lsv
            lsvDA.getItems().addAll(Integer.toString(tabNotes[nbEleves - 1][0]));
        }
        
        txfDA.setEditable(false);
        txfExam1.setEditable(false);
        txfExam2.setEditable(false);
        txfTP1.setEditable(false);
        txfTP2.setEditable(false);
        btnOK.setDisable(true);
        btnAnnuler.setDisable(true);
    }

    @FXML
    private void btnAnnulerAction(ActionEvent event) {
        txfDA.setEditable(false);
        txfExam1.setEditable(false);
        txfExam2.setEditable(false);
        txfTP1.setEditable(false);
        txfTP2.setEditable(false);
        btnOK.setDisable(true);
        btnAnnuler.setDisable(true);
    }

    @FXML
    private void btnQuitterAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CA TE TENTE TU DE SAUVGARDER LE BIG ?");
        alert.setContentText("TU SAVE TU OU PAS ?");
        ButtonType buttonTypeOK = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            try {
                ecrireFichier("notes.txt");
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.exit();
        } else {
            Platform.exit();
        }
    }
}

