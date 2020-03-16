package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatejiuyixiangmuController implements Initializable {
    private static Stage self;

    @FXML
    private TextField xiangmupno;
    @FXML
    private TextField jiuyibianhao;
    @FXML
    private TextField xiangmumingcheng;
    @FXML
    private TextField xiangmudanjia;
    @FXML
    private TextField xiangmushuliang;
    @FXML
    private Button updateButton;
    @FXML
    private TextField xiangmuzongjia;
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
        String pno = xiangmupno.getText();
        String vno = jiuyibianhao.getText();
        String pname = xiangmumingcheng.getText();
        String pprice = xiangmudanjia.getText();
        String pquantity = xiangmushuliang.getText();
        String ptotalprice = xiangmuzongjia.getText();
        if (MainController.updatejiuyixiangmu(pno, vno, pname, pprice, pquantity, ptotalprice)) {
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
        controller.jiuyixiangmuQuery();
    }

}
