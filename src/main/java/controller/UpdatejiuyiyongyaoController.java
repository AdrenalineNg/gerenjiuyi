package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatejiuyiyongyaoController implements Initializable {
    private static Stage self;

    @FXML
    private TextField yongyaomno;
    @FXML
    private TextField jiuyibianhao;
    @FXML
    private TextField yaowumingcheng;
    @FXML
    private TextField yaowudanjia;
    @FXML
    private TextField yaowushuliang;
    @FXML
    private Button updateButton;
    @FXML
    private TextField yaowuzongjia;
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
        String mno = yongyaomno.getText();
        String vno = jiuyibianhao.getText();
        String mname = yaowumingcheng.getText();
        String mprice = yaowudanjia.getText();
        String mquantity = yaowushuliang.getText();
        String mtotalprice = yaowuzongjia.getText();
        if (MainController.updatejiuyiyongyao(mno, vno, mname, mprice, mquantity, mtotalprice)) {
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
        controller.jiuyiyongyaoQuery();
    }

}
