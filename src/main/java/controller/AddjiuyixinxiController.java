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

public class AddjiuyixinxiController implements Initializable {

    private static Stage self;

    @FXML
    private TextField jiuyiriqi;
    @FXML
    private TextField jiuyididian;
    @FXML
    private TextField bingzhengmingcheng;
    @FXML
    private TextField zongjihuafei;
    @FXML
    private Button addButton;
    @FXML
    private TextField jiuyiriqiyue;
    @FXML
    private TextField jiuyiriqiri;
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
        String date = jiuyiriqi.getText()+jiuyiriqiyue.getText()+jiuyiriqiri.getText();
        String place = jiuyididian.getText();
        String dname = bingzhengmingcheng.getText();
        String total=zongjihuafei.getText();
        if (MainController.addjiuyixinxi(date, place, dname,total)) {
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
        controller.jiuyixinxiQuery();
    }

}
