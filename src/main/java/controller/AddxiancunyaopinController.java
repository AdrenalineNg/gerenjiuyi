package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddxiancunyaopinController implements Initializable {

    private static Stage self;

    @FXML
    private TextField yaowumingcheng;
    @FXML
    private TextField yaowuyongliang;
    @FXML
    private TextField yaowushuliang;
    @FXML
    private TextField yaowubaozhiqi;
    @FXML
    private TextField guoqishijian;
    @FXML
    private Button addButton;
    @FXML
    private TextField guoqishijianyue;
    @FXML
    private TextField guoqishijianri;
    private MainController controller;

    public void setController(MainController cntrllr) {
        controller = cntrllr;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    static void setStage(Stage stage) {
        self = stage;
    }

    public void add(ActionEvent event) {
        addButton.setDisable(true);
        String msname = yaowumingcheng.getText();
        String dosage = yaowuyongliang.getText();
        String msquantity = yaowushuliang.getText();
        String shelflife=yaowubaozhiqi.getText();
        String exp=guoqishijian.getText()+guoqishijianyue.getText()+guoqishijianri.getText();
        if (MainController.addxiancunyaopin( msname, dosage, msquantity, shelflife,exp)) {
            self.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("添加失败");
            alert.setContentText("添加记录时发生错误");
            alert.initOwner(self);
            alert.show();
            addButton.setDisable(false);
        }
        controller.xiancunyaopinQuery();
    }

}
