package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeletejiuyixiangmuController implements Initializable {
    private static Stage self;

    @FXML
    private TextField xiangmupno;
    @FXML
    private Button deleteButton;
    private MainController controller;

    public void setController(MainController cntrllr) {
        controller = cntrllr;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    static void setStage(Stage stage){
        self=stage;
    }

    public void delete(){
        deleteButton.setDisable(true);
        String pno=xiangmupno.getText();
        MainController.deletejiuyixiangmu(pno);
        self.close();
        controller.jiuyixiangmuQuery();
    }

}
