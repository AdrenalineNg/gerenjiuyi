package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatexiancunyaopinController implements Initializable {
    private static Stage self;

    @FXML
    private TextField yongyaomsno;
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
    private Button updateButton;
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

    public void update() {
        updateButton.setDisable(true);
        String msno = yongyaomsno.getText();
        String msname = yaowumingcheng.getText();
        String dosage = yaowuyongliang.getText();
        String msquantity = yaowushuliang.getText();
        String shelflife = yaowubaozhiqi.getText();
        String exp = guoqishijian.getText()+guoqishijianyue.getText()+guoqishijianri.getText();
        if (MainController.updatexiancunyaopin(msno, msname, dosage, msquantity, shelflife, exp)) {
            self.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("修改失败");
            alert.setContentText("修改记录时发生错误");
            alert.initOwner(self);
            alert.show();
            updateButton.setDisable(false);
        }
        controller.xiancunyaopinQuery();
    }

}
