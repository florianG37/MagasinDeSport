package sample;

import controlleur.ChefMagasinDAO;
import controlleur.VendeurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.ChefMagasin;
import modele.Vendeur;

import java.net.URL;
import java.util.ResourceBundle;

public class BoiteDialogueParametresControleur implements Initializable {

    private Stage dialogStage;
    private applicationPrincipaleChefControleur applicationPrincipaleChefControleur1;
    private ChefMagasin chefMagasin;
    private ChefMagasinDAO chefMagasinDAO;
    @FXML
    private TextField nomTextF;
    @FXML
    private TextField prenomTextF;
    @FXML
    private TextField motDePasseTextF;

    public void setApplicationPrincipaleChefControleur(applicationPrincipaleChefControleur applicationPrincipaleChefControleur1) {
        this.applicationPrincipaleChefControleur1 = applicationPrincipaleChefControleur1;
    }

    public void setChefMagasin(ChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }

    public void setChefMagasinDAO(ChefMagasinDAO chefMagasinDAO) {
        this.chefMagasinDAO = chefMagasinDAO;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chefMagasinDAO = new ChefMagasinDAO();
    }

    public void remplirFormulaire()
    {

        nomTextF.setText(chefMagasin.getNom());
        prenomTextF.setText(chefMagasin.getPrenom() + "");
        motDePasseTextF.setText(chefMagasin.getMotDePasse());
    }


    public void cliqueSurValider(ActionEvent actionEvent) {

        chefMagasin.setNom(nomTextF.getText());
        chefMagasin.setPrenom(prenomTextF.getText());
        chefMagasin.setMotDePasse(motDePasseTextF.getText());

        chefMagasinDAO.modifierChefMagasin(chefMagasin);

        //Fermer le formulaire
        dialogStage.close();


    }

    public void cliqueSurAnnuler(ActionEvent actionEvent) {
        //Fermer le formulaire
        dialogStage.close();
    }


}