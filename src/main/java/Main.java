import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        //与Fxml连接的方式
        URL location = getClass().getResource("main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);

        MainController controller = fxmlLoader.getController();
        String path=getClass().getResource("main.fxml").toString();
        path=path.substring(0,path.lastIndexOf('/')+1);
        System.out.println(path);
        controller.initPath(path);
        controller.initMenu(primaryStage);
        //修改数据库连接地址，用户名，密码
        controller.initDB("jdbc:mysql://localhost:3306/gerenjiuyi?user=root&password=123456&useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true");
        controller.jiuyixinxiQuery();
        controller.jiuyixiangmuQuery();
        controller.jiuyiyongyaoQuery();
        controller.xiancunyaopinQuery();

        primaryStage.setTitle("个人就医系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
