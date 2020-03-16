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

public class AddjiuyixiangmuController implements Initializable {

    private static Stage self;

    @FXML
    private TextField jiuyibianhao;
    @FXML
    private TextField xiangmumingcheng;
    @FXML
    private TextField xiangmudanjia;
    @FXML
    private TextField xiangmushuliang;
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
        String pname = xiangmumingcheng.getText();
        String pprice = xiangmudanjia.getText();
        String pquantity = xiangmushuliang.getText();
        if (MainController.addjiuyixiangmu(vno, pname, pprice, pquantity, String.valueOf(Double.valueOf(pprice) * Integer.valueOf(pquantity)))) {
            self.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("过期提醒");
            alert.setHeaderText("您的药品过期了");
            alert.setContentText("阿米替林片今天过期，小助手会帮您清理");
//            alert.setTitle("错误");
//            alert.setHeaderText("添加失败");
//            alert.setContentText("添加记录时发生错误");
            alert.initOwner(self);
            alert.show();
            addButton.setDisable(false);
        }
        controller.jiuyixiangmuQuery();
    }

}
