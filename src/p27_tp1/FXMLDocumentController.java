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
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
    String nomFic = "ficher/notes.txt";
    @FXML
    private GridPane gripNotes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lireFichier("ficher/notes.txt");
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lsvDA.getItems().setAll("1234501", "1234502");
        lsvDA.getSelectionModel().selectedItemProperty().addListener(e -> {
            String lol = lsvDA.getSelectionModel().getSelectedItem();
            if (lol.equals("1234501")) {
                txfDA.setText("1234501");
                txfExam1.setText("51");
                txfExam2.appendText("62");
                txfTP1.appendText("71");
                txfTP2.appendText("81");

            }

        });
   
       
       
    }
    public static void ecrireFichier(String nomFichier) throws IOException{
        FileWriter fichier= new FileWriter(nomFichier);
        PrintWriter objSortie = new PrintWriter(fichier);
     
     

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
    }

    @FXML
    private void btnSuprimerAction(ActionEvent event) {
    }

    @FXML
    private void btnActionModifier(ActionEvent event) {
    }

    @FXML
    private void BtnActionOk(ActionEvent event) {
    }

    @FXML
    private void btnActionAnnuler(ActionEvent event) {
    }

    @FXML
    private void cbmActionTri(ActionEvent event) {
    }

}
