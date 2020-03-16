package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Minfo;
import model.Msinfo;
import model.Pinfo;
import model.Vinfo;
import util.DateCalculation;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static String baseClassPath;
    private static String connectionString;

    @FXML
    private BorderPane borderPane;

    //就医信息版块控件
    @FXML
    private TextField jiuyixinxijiuyibianhao;
    @FXML
    private TextField jiuyixinxijiuyinianyueri;
    @FXML
    private TextField jiuyixinxizuijinntian;
    @FXML
    private TableView jiuyixinxiTable;
    @FXML
    private TableColumn jiuyixinxiVnoColumn;
    @FXML
    private TableColumn jiuyixinxiTimeColumn;
    @FXML
    private TableColumn jiuyixinxiHospitalColumn;
    @FXML
    private TableColumn jiuyixinxiDnameColumn;
    @FXML
    private TableColumn jiuyixinxiTotalColumn;

    //就医项目控件
    @FXML
    private TextField jiuyixiangmubianhao;
    @FXML
    private TableView jiuyixiangmuTable;
    @FXML
    private TableColumn jiuyixiangmuVnoColumn;
    @FXML
    private TableColumn jiuyixiangmuPnoColumn;
    @FXML
    private TableColumn jiuyixiangmuPnameColumn;
    @FXML
    private TableColumn jiuyixiangmuPpriceColumn;
    @FXML
    private TableColumn jiuyixiangmuPquantityColumn;
    @FXML
    private TableColumn jiuyixiangmuPtotalpriceColumn;

    //就医用药控件
    @FXML
    private TextField jiuyiyongyaobianhao;
    @FXML
    private TableView jiuyiyongyaoTable;
    @FXML
    private TableColumn jiuyiyongyaoVnoColumn;
    @FXML
    private TableColumn jiuyiyongyaoMnoColumn;
    @FXML
    private TableColumn jiuyiyongyaoMnameColumn;
    @FXML
    private TableColumn jiuyiyongyaoMpriceColumn;
    @FXML
    private TableColumn jiuyiyongyaoMquantityColumn;
    @FXML
    private TableColumn jiuyiyongyaoMtotalpriceColumn;

    //现存药品控件
    @FXML
    private TextField xiancunyaopinbianhao;
    @FXML
    private TableView xiancunyaopinTable;
    @FXML
    private TableColumn xiancunyaopinMsnoColumn;
    @FXML
    private TableColumn xiancunyaopinMsnameColumn;
    @FXML
    private TableColumn xiancunyaopinDosageColumn;
    @FXML
    private TableColumn xiancunyaopinMsquantityColumn;
    @FXML
    private TableColumn xiancunyaopinShelflifeColumn;
    @FXML
    private TableColumn xiancunyaopinExpColumn;

    //初始化函数
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void initPath(String path) {
        baseClassPath = path;
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initDB(String connStr) {
        connectionString = connStr;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(connStr);
        } catch (ClassNotFoundException e) {
            System.out.println("加载MySQL驱动程序错误");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null == conn) {
                Platform.exit();
            }
        }
    }

    public void initMenu(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        borderPane.setTop(menuBar);

        Menu systemMenu = new Menu("系统");
        MenuItem exitMenuItem = new MenuItem("退出系统");
        systemMenu.getItems().add(exitMenuItem);
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        menuBar.getMenus().addAll(systemMenu);

        Menu jiuyixinxiMenu = new Menu("就医信息");
        MenuItem gerenjiuyiTianjiaMenuItem = new MenuItem("添加新纪录");
        jiuyixinxiMenu.getItems().add(gerenjiuyiTianjiaMenuItem);
        gerenjiuyiTianjiaMenuItem.setOnAction(actionEvent -> openAddjiuyixinxi());

        MenuItem gerenjiuyiXiugaiMenuItem = new MenuItem("修改已有纪录");
        jiuyixinxiMenu.getItems().add(gerenjiuyiXiugaiMenuItem);
        gerenjiuyiXiugaiMenuItem.setOnAction(actionEvent -> openUpdatejiuyixinxi());

        MenuItem gerenjiuyiShanchuMenuItem = new MenuItem("删除已有纪录");
        jiuyixinxiMenu.getItems().add(gerenjiuyiShanchuMenuItem);
        gerenjiuyiShanchuMenuItem.setOnAction(actionEvent -> openDeletejiuyixinxi());
        menuBar.getMenus().addAll(jiuyixinxiMenu);

        Menu jiuyixiangmuMenu = new Menu("就医项目");
        MenuItem jiuyixiangmuTianjiaMenuItem = new MenuItem("添加新纪录");
        jiuyixiangmuMenu.getItems().add(jiuyixiangmuTianjiaMenuItem);
        jiuyixiangmuTianjiaMenuItem.setOnAction(actionEvent -> openAddjiuyixiangmu());

        MenuItem jiuyixiangmuXiugaiMenuItem = new MenuItem("修改已有纪录");
        jiuyixiangmuMenu.getItems().add(jiuyixiangmuXiugaiMenuItem);
        jiuyixiangmuXiugaiMenuItem.setOnAction(actionEvent -> openUpdatejiuyixiangmu());

        MenuItem jiuyixiangmuShanchuMenuItem = new MenuItem("删除已有纪录");
        jiuyixiangmuMenu.getItems().add(jiuyixiangmuShanchuMenuItem);
        jiuyixiangmuShanchuMenuItem.setOnAction(actionEvent -> openDeletejiuyixiangmu());
        menuBar.getMenus().addAll(jiuyixiangmuMenu);

        Menu jiuyiyongyaoMenu = new Menu("就医用药");
        MenuItem jiuyiyongyaoTianjiaMenuItem = new MenuItem("添加新纪录");
        jiuyiyongyaoMenu.getItems().add(jiuyiyongyaoTianjiaMenuItem);
        jiuyiyongyaoTianjiaMenuItem.setOnAction(actionEvent -> openAddjiuyiyongyao());

        MenuItem jiuyiyongyaoXiugaiMenuItem = new MenuItem("修改已有纪录");
        jiuyiyongyaoMenu.getItems().add(jiuyiyongyaoXiugaiMenuItem);
        jiuyiyongyaoXiugaiMenuItem.setOnAction(actionEvent -> openUpdatejiuyiyongyao());

        MenuItem jiuyiyongyaoShanchuMenuItem = new MenuItem("删除已有纪录");
        jiuyiyongyaoMenu.getItems().add(jiuyiyongyaoShanchuMenuItem);
        jiuyiyongyaoShanchuMenuItem.setOnAction(actionEvent -> openDeletejiuyiyongyao());
        menuBar.getMenus().addAll(jiuyiyongyaoMenu);

        Menu xiancunyaopinMenu = new Menu("现存药品");
        MenuItem xiancunyaopinTianjiaMenuItem = new MenuItem("添加新纪录");
        xiancunyaopinMenu.getItems().add(xiancunyaopinTianjiaMenuItem);
        xiancunyaopinTianjiaMenuItem.setOnAction(actionEvent -> openAddxiancunyaopin());

        MenuItem xiancunyaopinXiugaiMenuItem = new MenuItem("修改已有纪录");
        xiancunyaopinMenu.getItems().add(xiancunyaopinXiugaiMenuItem);
        xiancunyaopinXiugaiMenuItem.setOnAction(actionEvent -> openUpdatexiancunyaopin());

        MenuItem xiancunyaopinShanchuMenuItem = new MenuItem("删除已有纪录");
        xiancunyaopinMenu.getItems().add(xiancunyaopinShanchuMenuItem);
        xiancunyaopinShanchuMenuItem.setOnAction(actionEvent -> openDeletexiancunyaopin());
        menuBar.getMenus().addAll(xiancunyaopinMenu);
    }

    //就医信息版块函数

    static boolean addjiuyixinxi(String date, String place, String dname, String total) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into vinfo (time, hospital, dname,total) values (?,?,?,?);");
            stmt.setString(1, date);
            stmt.setString(2, place);
            stmt.setString(3, dname);
            stmt.setString(4, total);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean updatejiuyixinxi(String vno, String date, String place, String dname, String total) {
        try {
            Statement statement = getConnection().createStatement();
            String sql = "update vinfo set time=";
            PreparedStatement stmt = getConnection().prepareStatement("update vinfo set time=?,hospital=?,dname=?,total=? where vno = ?;");
            if (date.isEmpty()) {
                sql += "time";
            } else {
                sql += date;
            }
            sql += ",hospital=";
            if (place.isEmpty()) {
                sql += "hospital";
            } else {
                sql += "'" + place + "'";
            }
            sql += ",dname=";
            if (dname.isEmpty()) {
                sql += "dname";
            } else {
                sql += "'" + dname + "'";
            }
            sql += ",total=";
            if (total.isEmpty()) {
                sql += "total";
            } else {
                sql += total;
            }
            sql += " where vno=" + vno + ";";
            System.out.println(sql);
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void deletejiuyixinxi(String vno) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from vinfo where vno = ?;");
            stmt.setString(1, vno);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAddjiuyixinxi() {
        try {
            URL location = new URL(baseClassPath + "addjiuyixinxi.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            AddjiuyixinxiController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            AddjiuyixinxiController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdatejiuyixinxi() {
        try {
            URL location = new URL(baseClassPath + "updatejiuyixinxi.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            UpdatejiuyixinxiController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            UpdatejiuyixinxiController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDeletejiuyixinxi() {
        try {
            URL location = new URL(baseClassPath + "deletejiuyixinxi.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            DeletejiuyixinxiController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            DeletejiuyixinxiController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jiuyixinxiResetQuerry() {
        jiuyixinxijiuyibianhao.setText("");
        jiuyixinxijiuyinianyueri.setText("");
        jiuyixinxizuijinntian.setText("");
        jiuyixinxiQuery();
    }

    public void jiuyixinxiQuery() {
        String sql = "select vno,time,hospital,dname,total from vinfo where ";
        int flag = 0;

        String vno = jiuyixinxijiuyibianhao.getText();
        String date = jiuyixinxijiuyinianyueri.getText();
        String days = jiuyixinxizuijinntian.getText();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        //原语句，但是现在用不到了
        //int iDate = Integer.valueOf(df.format(new Date()));

        if (!vno.isEmpty()) {
            sql += "vno=" + vno;
            ++flag;
        }

        if ((!date.isEmpty()) && days.isEmpty()) {
            if (flag > 0) {
                sql += " and ";
            }
            sql += "time like '" + date + "%'";
            ++flag;
        }

        if (!days.isEmpty()) {
            if (flag > 0) {
                sql += " and ";
            }
            //原语句如下
            //sql += "time>=" + String.valueOf(iDate - Integer.valueOf(days));
            //更新的语句如下
            sql += "time>=" + String.valueOf(DateCalculation.calculation(days));
            ++flag;
        }

        if (0 == flag) {
            sql += "1=1";
        }

        System.out.println(sql);
        Connection conn = getConnection();
        if (null == conn) {
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Vinfo> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Vinfo(rs.getInt("vno"), rs.getInt("time"), rs.getString("hospital"), rs.getString("dname"), rs.getDouble("total")));
            }
            jiuyixinxiVnoColumn.setCellValueFactory(new PropertyValueFactory("vno"));
            jiuyixinxiTimeColumn.setCellValueFactory(new PropertyValueFactory("time"));
            jiuyixinxiHospitalColumn.setCellValueFactory(new PropertyValueFactory("hospital"));
            jiuyixinxiDnameColumn.setCellValueFactory(new PropertyValueFactory("dname"));
            jiuyixinxiTotalColumn.setCellValueFactory(new PropertyValueFactory("total"));
            jiuyixinxiTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //就医项目函数

    static boolean addjiuyixiangmu(String vno, String pname, String pprice, String pquantity, String ptotalprice) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into pinfo (vno, pname, pprice, pquantity, ptotalprice) values (?,?,?,?,?);");
            stmt.setString(1, vno);
            stmt.setString(2, pname);
            stmt.setString(3, pprice);
            stmt.setString(4, pquantity);
            stmt.setString(5, ptotalprice);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean updatejiuyixiangmu(String pno, String vno, String pname, String pprice, String pquantity, String ptotalprice) {
        try {
            Statement statement = getConnection().createStatement();
            String sql = "update pinfo set vno=";
            if (vno.isEmpty()) {
                sql += "vno";
            } else {
                sql += vno;
            }
            sql += ",pname=";
            if (pname.isEmpty()) {
                sql += "pname";
            } else {
                sql += "'" + pname + "'";
            }
            sql += ",pprice=";
            if (pprice.isEmpty()) {
                sql += "pprice";
            } else {
                sql += pprice;
            }
            sql += ",pquantity=";
            if (pquantity.isEmpty()) {
                sql += "pquantity";
            } else {
                sql += pquantity;
            }
            sql += ",ptotalprice=";
            if (ptotalprice.isEmpty()) {
                sql += "ptotalprice";
            } else {
                sql += ptotalprice;
            }
            sql += " where pno=" + pno + ";";
            System.out.println(sql);
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void deletejiuyixiangmu(String pno) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from pinfo where pno = ?;");
            stmt.setString(1, pno);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAddjiuyixiangmu() {
        try {
            URL location = new URL(baseClassPath + "addjiuyixiangmu.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            AddjiuyixiangmuController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            AddjiuyixiangmuController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdatejiuyixiangmu() {
        try {
            URL location = new URL(baseClassPath + "updatejiuyixiangmu.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            UpdatejiuyixiangmuController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            UpdatejiuyixiangmuController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDeletejiuyixiangmu() {
        try {
            URL location = new URL(baseClassPath + "deletejiuyixiangmu.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            DeletejiuyixiangmuController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            DeletejiuyixiangmuController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jiuyixiangmuQuery() {
        String sql = "select vno,pno,pname,pprice,pquantity,ptotalprice from pinfo where ";
        boolean flag = false;
        String pno = jiuyixiangmubianhao.getText();

        if (!pno.isEmpty()) {
            sql += "vno=" + pno;
            flag = true;
        }

        if (!flag) {
            sql += "1=1";
        }

        System.out.println(sql);
        Connection conn = getConnection();
        if (null == conn) {
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Pinfo> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Pinfo(rs.getInt("vno"), rs.getInt("pno"), rs.getString("pname"), rs.getDouble("pprice"), rs.getInt("pquantity"), rs.getDouble("ptotalprice")));
            }
            jiuyixiangmuVnoColumn.setCellValueFactory(new PropertyValueFactory("vno"));
            jiuyixiangmuPnoColumn.setCellValueFactory(new PropertyValueFactory("pno"));
            jiuyixiangmuPnameColumn.setCellValueFactory(new PropertyValueFactory("pname"));
            jiuyixiangmuPpriceColumn.setCellValueFactory(new PropertyValueFactory("pprice"));
            jiuyixiangmuPquantityColumn.setCellValueFactory(new PropertyValueFactory("pquantity"));
            jiuyixiangmuPtotalpriceColumn.setCellValueFactory(new PropertyValueFactory("ptotalprice"));
            jiuyixiangmuTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //就医用药函数

    static boolean addjiuyiyongyao(String vno, String mname, String mprice, String mquantity, String mtotalprice) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into minfo (vno, mname, mprice, mquantity, mtotalprice) values (?,?,?,?,?);");
            stmt.setString(1, vno);
            stmt.setString(2, mname);
            stmt.setString(3, mprice);
            stmt.setString(4, mquantity);
            stmt.setString(5, mtotalprice);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean updatejiuyiyongyao(String mno, String vno, String mname, String mprice, String mquantity, String mtotalprice) {
        try {
            Statement statement = getConnection().createStatement();
            String sql = "update minfo set vno=";
            if (vno.isEmpty()) {
                sql += "vno";
            } else {
                sql += vno;
            }
            sql += ",mname=";
            if (mname.isEmpty()) {
                sql += "mname";
            } else {
                sql += "'" + mname + "'";
            }
            sql += ",mprice=";
            if (mprice.isEmpty()) {
                sql += "mprice";
            } else {
                sql += mprice;
            }
            sql += ",mquantity=";
            if (mquantity.isEmpty()) {
                sql += "mquantity";
            } else {
                sql += mquantity;
            }
            sql += ",mtotalprice=";
            if (mtotalprice.isEmpty()) {
                sql += "mtotalprice";
            } else {
                sql += mtotalprice;
            }
            sql += " where mno=" + mno + ";";
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void deletejiuyiyongyao(String mno) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from minfo where mno = ?;");
            stmt.setString(1, mno);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAddjiuyiyongyao() {
        try {
            URL location = new URL(baseClassPath + "addjiuyiyongyao.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            AddjiuyiyongyaoController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            AddjiuyiyongyaoController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdatejiuyiyongyao() {
        try {
            URL location = new URL(baseClassPath + "updatejiuyiyongyao.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            UpdatejiuyiyongyaoController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            UpdatejiuyiyongyaoController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDeletejiuyiyongyao() {
        try {
            URL location = new URL(baseClassPath + "deletejiuyiyongyao.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            DeletejiuyiyongyaoController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            DeletejiuyiyongyaoController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jiuyiyongyaoQuery() {
        String sql = "select vno,mno,mname,mprice,mquantity,mtotalprice from minfo where ";
        boolean flag = false;
        String pno = jiuyiyongyaobianhao.getText();

        if (!pno.isEmpty()) {
            sql += "vno=" + pno;
            flag = true;
        }

        if (!flag) {
            sql += "1=1";
        }

        System.out.println(sql);
        Connection conn = getConnection();
        if (null == conn) {
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Minfo> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Minfo(rs.getInt("vno"), rs.getInt("mno"), rs.getString("mname"), rs.getDouble("mprice"), rs.getInt("mquantity"), rs.getDouble("mtotalprice")));
            }
            jiuyiyongyaoVnoColumn.setCellValueFactory(new PropertyValueFactory("vno"));
            jiuyiyongyaoMnoColumn.setCellValueFactory(new PropertyValueFactory("mno"));
            jiuyiyongyaoMnameColumn.setCellValueFactory(new PropertyValueFactory("mname"));
            jiuyiyongyaoMpriceColumn.setCellValueFactory(new PropertyValueFactory("mprice"));
            jiuyiyongyaoMquantityColumn.setCellValueFactory(new PropertyValueFactory("mquantity"));
            jiuyiyongyaoMtotalpriceColumn.setCellValueFactory(new PropertyValueFactory("mtotalprice"));
            jiuyiyongyaoTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //现存药品函数

    static boolean addxiancunyaopin(String msname, String dosage, String msquantity, String shelflife, String exp) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into mstorage (msname, dosage, msquantity, shelflife, exp) values (?,?,?,?,?);");
            stmt.setString(1, msname);
            stmt.setString(2, dosage);
            stmt.setString(3, msquantity);
            stmt.setString(4, shelflife);
            stmt.setString(5, exp);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean updatexiancunyaopin(String msno, String msname, String dosage, String msquantity, String shelflife, String exp) {
        try {
            Statement statement = getConnection().createStatement();
            String sql = "update mstorage set msname=";
            if (msname.isEmpty()) {
                sql += "msname";
            } else {
                sql += "'"+msname+ "'";
            }
            sql+=",dosage=";
            if (dosage.isEmpty()) {
                sql += "dosage";
            } else {
                sql += "'"+dosage+ "'";
            }
            sql+=",msquantity=";
            if (msquantity.isEmpty()) {
                sql += "msquantity";
            } else {
                sql += msquantity;
            }
            sql+=",shelflife=";
            if (shelflife.isEmpty()) {
                sql += "shelflife";
            } else {
                sql += "'"+shelflife+ "'";
            }
            sql+=",exp=";
            if (exp.isEmpty()) {
                sql += "exp";
            } else {
                sql += exp;
            }
            sql+=" where msno="+msno+";";
            return statement.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void deletexiancunyaopin(String msno) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from mstorage where msno = ?;");
            stmt.setString(1, msno);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openAddxiancunyaopin() {
        try {
            URL location = new URL(baseClassPath + "addxiancunyaopin.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            AddxiancunyaopinController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            AddxiancunyaopinController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdatexiancunyaopin() {
        try {
            URL location = new URL(baseClassPath + "updatexiancunyaopin.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            UpdatexiancunyaopinController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            UpdatexiancunyaopinController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDeletexiancunyaopin() {
        try {
            URL location = new URL(baseClassPath + "deletexiancunyaopin.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(location);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            Parent target = fxmlLoader.load();
            DeletexiancunyaopinController controller = fxmlLoader.getController();
            controller.setController(this);

            Scene scene = new Scene(target); //创建场景；
            Stage stg = new Stage();//创建舞台；
            stg.setScene(scene); //将场景载入舞台；
            stg.show(); //显示窗口；
            DeletexiancunyaopinController.setStage(stg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void xiancunyaopinQuery() {
        String sql = "select msno,msname,dosage,msquantity,shelflife,exp from mstorage where ";
        boolean flag = false;
        String pno = xiancunyaopinbianhao.getText();

        if (!pno.isEmpty()) {
            sql += "msno=" + pno;
            flag = true;
        }

        if (!flag) {
            sql += "1=1";
        }

        System.out.println(sql);
        Connection conn = getConnection();
        if (null == conn) {
            return;
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Msinfo> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.add(new Msinfo(rs.getInt("msno"), rs.getString("msname"), rs.getString("dosage"), rs.getInt("msquantity"), rs.getString("shelflife"), rs.getInt("Exp")));
            }
            xiancunyaopinMsnoColumn.setCellValueFactory(new PropertyValueFactory("msno"));
            xiancunyaopinDosageColumn.setCellValueFactory(new PropertyValueFactory("dosage"));
            xiancunyaopinMsnameColumn.setCellValueFactory(new PropertyValueFactory("msname"));
            xiancunyaopinShelflifeColumn.setCellValueFactory(new PropertyValueFactory("shelflife"));
            xiancunyaopinMsquantityColumn.setCellValueFactory(new PropertyValueFactory("msquantity"));
            xiancunyaopinExpColumn.setCellValueFactory(new PropertyValueFactory("exp"));
            xiancunyaopinTable.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
