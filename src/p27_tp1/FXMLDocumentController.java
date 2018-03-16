/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p27_tp1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
