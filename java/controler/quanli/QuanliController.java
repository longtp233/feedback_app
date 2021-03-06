package controler.quanli;

import exception.NullChosseException;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Letter;
import service.LetterService;
import service.impl.LetterServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuanliController implements Initializable {

    public TableView tableViewLetter;
    public TableColumn tableColumnSTT;
    public TableColumn tableColumnLoaiDon;
    public TableColumn tableColumnNgayVietDon;
    public TableColumn tableColumnNoiDung;
    public TableColumn tableColumnTrangThai;
    public Button btnReset;
    public Button butChinhsua;
    public Button butXoa;
    public Button butGopNhom;
    public TableColumn tableColumnAction;




    @FXML
    private Button butTaoDon,  butTimKiem, butXemChiTiet;

    private LetterService letterService = new LetterServiceIMPL();


    private ObservableList<Letter> lettersObservableList=null;

    private ObservableList<Letter> lettersObservableListSearch;

    private List<Letter> letters = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        try {
            initBut();
        }catch (IndexOutOfBoundsException e) {


        }


    }

    private void initBut() {

        btnReset.setOnAction(actionEvent -> {
            // cai nay chua test
            lettersObservableListSearch=null;
            initTable();
        });

        butTaoDon.setOnAction(actionEvent -> {
// tat man hinh hien tai de hien thi man hinh tao don
//            try {
//                Parent blad = FXMLLoader.load(getClass().getResource("/view/main/add.fxml"));
//                Scene scene = new Scene(blad);
//                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                appStage.setTitle("Tao Don");
//                appStage.setScene(scene);
//                appStage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/home/quanli/add.fxml"));
                Parent parent=loader.load();
                Scene scene = new Scene(parent);
                Stage stageAdd = new Stage();
                stageAdd.setTitle("Tạo đơn mới");
                stageAdd.setScene(scene);
                stageAdd.initModality(Modality.WINDOW_MODAL);
                stageAdd.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                stageAdd.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        butTimKiem.setOnAction(actionEvent -> {
            try {
                lettersObservableListSearch=null;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/home/quanli/search.fxml"));
                SearchController controller =new SearchController();
                loader.setController(controller);
                Parent parent=loader.load();
                Scene scene = new Scene(parent);
                Stage stageSearch = new Stage();
                stageSearch.setTitle("Tìm đơn");
                stageSearch.setScene(scene);
                stageSearch.initModality(Modality.WINDOW_MODAL);
                stageSearch.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                stageSearch.showAndWait();
                lettersObservableListSearch=FXCollections.observableList(controller.getList());
                System.out.println("tra ve list "+lettersObservableListSearch.toString());
                if(lettersObservableListSearch.get(0)==null){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("infor");
                    alert.setContentText("Không Tìm Thấy Đơn ");
                    alert.show();

                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("infor");
                    alert.setContentText("Đã Tìm Đơn ");
                    alert.show();
                }
                initTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // khong tat man hinh hien tai
        // hien thi ca 2 cung luc
        // khong the thao tac voi cua so cha
        butXemChiTiet.setOnAction(actionEvent -> {
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
//            if (lettersSelected.get(0) == null) {
//                System.out.println("bat duoc loi khong chon 1 don ");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Infor");
//                alert.setContentText("hãy chọn một đơn!");
//                alert.showAndWait();
//            };
            try{
                if (lettersSelected.get(0) != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/home/quanli/show.fxml"));

                        // set quyen conntroler cho cai stage (<=> tuong duong viec fx:conntroller trong fxml)
                        ShowControler controllerChiTietDon =new ShowControler();
                        //-------------------------
                        // neu dao vi tri 2 dong nay cho nhau se bi loi
                        controllerChiTietDon.setLetter(lettersSelected.get(0));
//                    controllerChiTietDon =loader.getController();
                        loader.setController(controllerChiTietDon);
                        //------------------------

                        // phai load cai parent nay sau khi new controler neu khong no se goi ham init ma khong co du lieu
                        Parent parent=loader.load();
                        Scene scene = new Scene(parent);
                        Stage stageChinhSua = new Stage();
                        stageChinhSua.setTitle("Chi Tiết Đơn ");
                        stageChinhSua.setScene(scene);
                        stageChinhSua.initModality(Modality.WINDOW_MODAL);
                        stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                        stageChinhSua.show();

                    }  catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("Hãy chọn một đơn!");
                alert.showAndWait();
            }

        });


        butGopNhom.setOnAction(actionEvent -> {
            // phai xu li lay ra mot list

            ObservableList<Letter> lettersSelected = FXCollections.observableArrayList();

            for(Letter iter: lettersObservableList){
                if (iter.getCheckBox().isSelected()){
                    lettersSelected.add(iter);
                }
            }
//            for(Letter iter: lettersObservableListSearch){
//
//            }

            System.out.println("tick vao ");
            lettersSelected.stream().forEach(s-> System.out.println(s));

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/home/quanli/collect.fxml"));
                    CollectLetterControler controller =new CollectLetterControler();
                    controller.setListLetter(lettersSelected);
                    loader.setController(controller);
                    Parent parent=loader.load();
                    Scene scene = new Scene(parent);
                    Stage stageChinhSua = new Stage();
                    stageChinhSua.setTitle("Gộp Nhóm");
                    stageChinhSua.setScene(scene);
                    stageChinhSua.initModality(Modality.WINDOW_MODAL);
                    stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                    stageChinhSua.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        });
        butChinhsua.setOnAction(actionEvent -> {
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            try{
                if (lettersSelected.get(0) != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/home/quanli/edit.fxml"));
                        EditControler controller =new EditControler();
                        controller.setLetter(lettersSelected.get(0));
                        loader.setController(controller);
                        Parent parent=loader.load();
                        Scene scene = new Scene(parent);
                        Stage stageChinhSua = new Stage();
                        stageChinhSua.setTitle("Sửa Đơn ");
                        stageChinhSua.setScene(scene);
                        stageChinhSua.initModality(Modality.WINDOW_MODAL);
                        stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                        stageChinhSua.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("Hãy chọn một đơn!");
                alert.showAndWait();
            }

        });
        butXoa.setOnAction(actionEvent -> {
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            try{
                if (lettersSelected.get(0) != null) {
                    // del xong hien thi thon bao cho nguoi duing

                    try {

                        if(letterService.delete(lettersSelected.get(0).getId())){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("infor");
                            alert.setContentText("Xóa Đơn Thành Công ! ");
                            alert.showAndWait();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("infor");
                        alert.setContentText("Xóa Đơn Không Thành Công ! ");
                        alert.showAndWait();
                    }


                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("Hãy chọn một đơn!");
                alert.showAndWait();
            }

        });
    }

    private void initTable() {
        //chon duoc nhieu dong
        tableViewLetter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnSTT.setCellFactory(col -> {
            TableCell<Letter, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.when(cell.emptyProperty())
                    .then("")
                    .otherwise(cell.indexProperty().asString()));
            return cell ;
        });
        // nhung truong nay bat buoc phai tuong ung thuoc tinh cua model thi moi hien thi dc
//        tableColumnSTT.setCellValueFactory(new PropertyValueFactory<Letter, String>("id"));
        tableColumnLoaiDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("category"));
        tableColumnNgayVietDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("applyDate"));
        tableColumnNoiDung.setCellValueFactory(new PropertyValueFactory<Letter, String>("content"));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<Letter, String>("statusString"));
        tableColumnAction.setCellValueFactory(new PropertyValueFactory<Letter,String>("checkBox"));


            try {
                refreshTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

    }


    public void refreshTable() throws SQLException {
        System.out.println("refresh table");

        if(lettersObservableListSearch!=null&&!lettersObservableListSearch.isEmpty()){
            tableViewLetter.setItems(lettersObservableListSearch);


        }else {
            letters.removeAll(letters);
            letters.addAll(letterService.findAll());
            lettersObservableList = FXCollections.observableList(letters);
            tableViewLetter.setItems(lettersObservableList);
        }
    }


}
