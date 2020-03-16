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

public class AddjiuyiyongyaoController implements Initializable {

    private static Stage self;

    @FXML
    private TextField jiuyibianhao;
    @FXML
    private TextField yaowumingcheng;
    @FXML
    private TextField yaowudanjia;
    @FXML
    private TextField yaowushuliang;
    @FXML
    private Button addButton;
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
        String vno = jiuyibianhao.getText();
        String mname = yaowumingcheng.getText();
        String mprice = yaowudanjia.getText();
        String mquantity = yaowushuliang.getText();
        if (MainController.addjiuyiyongyao(vno, mname, mprice, mquantity, String.valueOf(Double.valueOf(mprice) * Integer.valueOf(mquantity)))) {
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
        controller.jiuyiyongyaoQuery();
    }

}
