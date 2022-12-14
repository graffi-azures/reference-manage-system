package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.dao.ReferenceDBDao;
import model.dao.UserDBDao;
import model.entity.Message;
import model.entity.Reference;
import model.entity.User;
import utils.StageManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class MainController implements Initializable {

    public VBox rightarea;

    public Button btnLLM;
    public Button btnOSM;
    public Button btnIOM;
    public Button btnSSP;
    public ChoiceBox selectBStyle;
    public Button btnCTL;
    public Button btnNRF;
    public Button btnIMP;
    public Button btnEXP;
    public Button btnOPF;
    public Button btnISC;
    public Button btnFOB;
    public Button btnTWD;
    public Button btnSYL;
    public Button btnSRE;
    public Button btnHLP;
    public TextField txtSearchBox;
    public Button btnQS;
    public BorderPane wholePage;
    public VBox leftnav;
    public TitledPane selectgroup;
    public VBox content;
    public TableView tvCenter;
    public TableColumn clAuthor;
    public TableColumn clYear;
    public TableColumn clTitle;
    public TableColumn clRating;
    public TableColumn clJournal;
    public TableColumn clUpdated;
    public TableColumn clType;
    public ToggleButton btnMsg;

    private ContextMenu searchPopup;

    @FXML
    private ChildRefController refContentController;

    @FXML
    private LoginController loginController;

//    ?????????????????????????????????????????????????????????????????????
    StringBuilder sb = new StringBuilder();
//    ????????????????????????????????????????????????
    String folder = new String();
//    ??????????????????????????????????????????
    String foldertemp = new String();
//    ??????????????????????????????????????????
    StringBuilder citation = new StringBuilder();
//    ???????????????????????????????????????
    Reference selectref = new Reference();
//    ????????????????????????????????????
    Reference shareref = new Reference();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageManager.CONTROLLER.put("MainController",this);

        Image imgssp = new Image("view/imgitem/??????_double-down.png/");
        Image imgllm = new Image("view/imgitem/localRefMode.png/");
        Image imgosm = new Image("view/imgitem/olSearchMode.png");
        Image imgiom = new Image("view/imgitem/igRenSeMode.png");
        Image imgctl = new Image("view/imgitem/cpytolib.png");
        Image imgnrf = new Image("view/imgitem/newrefer.png");
        Image imgimp = new Image("view/imgitem/import.png");
        Image imgexp = new Image("view/imgitem/export.png");
        Image imgopf = new Image("view/imgitem/openfile.png");
        Image imgisc = new Image("view/imgitem/insertctt.png");
        Image imgfob = new Image("view/imgitem/formatbib.png");
        Image imgtwd = new Image("view/imgitem/gotoword.png");
        Image imgsyl = new Image("view/imgitem/synclib.png");
        Image imgsre = new Image("view/imgitem/sharelib.png");
        Image imghlp = new Image("view/imgitem/helpdocu.png");
        Image imgqs = new Image("view/imgitem/??????_search.png");
        ImageView ivssp = new ImageView(imgssp);
        ImageView ivllm = new ImageView(imgllm);
        ImageView ivosm = new ImageView(imgosm);
        ImageView iviom = new ImageView(imgiom);
        ImageView ivctl = new ImageView(imgctl);
        ImageView ivnrf = new ImageView(imgnrf);
        ImageView ivimp = new ImageView(imgimp);
        ImageView ivexp = new ImageView(imgexp);
        ImageView ivopf = new ImageView(imgopf);
        ImageView ivisc = new ImageView(imgisc);
        ImageView ivfob = new ImageView(imgfob);
        ImageView ivtwd = new ImageView(imgtwd);
        ImageView ivsyl = new ImageView(imgsyl);
        ImageView ivsre = new ImageView(imgsre);
        ImageView ivhlp = new ImageView(imghlp);
        ImageView ivqs = new ImageView(imgqs);
        btnSSP.setGraphic(ivssp);
        btnLLM.setGraphic(ivllm);
        btnOSM.setGraphic(ivosm);
        btnIOM.setGraphic(iviom);
        btnCTL.setGraphic(ivctl);
        btnNRF.setGraphic(ivnrf);
        btnIMP.setGraphic(ivimp);
        btnEXP.setGraphic(ivexp);
        btnOPF.setGraphic(ivopf);
        btnISC.setGraphic(ivisc);
        btnFOB.setGraphic(ivfob);
        btnTWD.setGraphic(ivtwd);
        btnSYL.setGraphic(ivsyl);
        btnSRE.setGraphic(ivsre);
        btnHLP.setGraphic(ivhlp);
        btnQS.setGraphic(ivqs);
        Tooltip llmtip = new Tooltip("Local Library Mode");
        btnLLM.setTooltip(llmtip);
        Tooltip osmtip = new Tooltip("Online Search Mode");
        btnOSM.setTooltip(osmtip);
        Tooltip iomtip = new Tooltip("Integrated Library & Online Search Mode");
        btnIOM.setTooltip(iomtip);
        Tooltip ctltip = new Tooltip("Copy to local library");
        btnCTL.setTooltip(ctltip);
        Tooltip nrftip = new Tooltip("New Reference");
        btnNRF.setTooltip(nrftip);
        Tooltip imptip = new Tooltip("Import");
        btnIMP.setTooltip(imptip);
        Tooltip exptip = new Tooltip("Export");
        btnEXP.setTooltip(exptip);
        Tooltip opftip = new Tooltip("Open link");
        btnOPF.setTooltip(opftip);
        Tooltip isctip = new Tooltip("Insert Citation");
        btnISC.setTooltip(isctip);
        Tooltip fobtip = new Tooltip("Format bibliography");
        btnFOB.setTooltip(fobtip);
        Tooltip twdtip = new Tooltip("Go to Word Processor");
        btnTWD.setTooltip(twdtip);
        Tooltip syltip = new Tooltip("Edit Reference");
        btnSYL.setTooltip(syltip);
        Tooltip sretip = new Tooltip("Share library");
        btnSRE.setTooltip(sretip);
        Tooltip hlptip = new Tooltip("Help");
        btnHLP.setTooltip(hlptip);
        Tooltip qstip = new Tooltip("Quick Search");
        btnQS.setTooltip(qstip);
        selectBStyle.setTooltip(new Tooltip("bibliography output style"));

        selectgroup.setExpanded(true);
        selectgroup.setAnimated(false);
        initSearchPopup();
//        bibliography output style??????
        selectBStyle.getItems().addAll("Periodical literature[J]","Work[M]","Dissertation[D]","Patent[P]","Standard[S]","Proceedings","Newspaper","Electronic literature");
        selectBStyle.getSelectionModel().select(0);
        selectBStyle.getSelectionModel().selectedItemProperty().addListener(
                ((observable,oldValue, newValue) -> setNrDetails((String) newValue)));
//        tvCenter????????????
        tvCenter.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    try {
                        getReferenceDetails((Reference) newValue);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }));

//        ???selectgroup??????????????????
        ContextMenu cm = new ContextMenu();
        MenuItem creatitem = new MenuItem("create group");
        MenuItem deleteitem = new MenuItem("delete group");
        VBox gitems = new VBox();
        List<Button> glist = new ArrayList<>();
        creatitem.setOnAction((ActionEvent e)->{

            gitems.setPrefHeight(500);
            Button btncg = new Button("New Group");
            glist.add(btncg);
            for (Button btn:glist){
                gitems.getChildren().addAll(btn);
            }
        });
        selectgroup.setContent(gitems);
        cm.getItems().add(creatitem);
        cm.getItems().add(deleteitem);
        selectgroup.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            if(e.getButton()== MouseButton.SECONDARY&&e.getClickCount()==1){
                cm.show(selectgroup,e.getScreenX(),e.getScreenY());
            }
        });

    }
/*???????????????????????????????????????:
1???????????????????????????????????????????????????????????????
2????????????????????????????????????????????????
3???????????????????????????.??????.????????????????????????????????????????????????
4?????????????????????????????????????????????
5????????????????????????????????????????????????????????????
6????????????????????????????????????
*/
    private void getReferenceDetails(Reference newValue) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //????????????????????????????????????????????????;
        ContextMenu rightclick = new ContextMenu();
//        MenuItem editref = new MenuItem("edit reference");
        MenuItem deleteref = new MenuItem("delete reference");
//        editref.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                Stage stage = new Stage();
//                Parent root = null;
//                try {
//                    root = FXMLLoader.load(getClass().getResource("../view/editRef.fxml"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                stage.setTitle("Edit Reference");
//                stage.setScene(new Scene(root));
//                stage.show();
//                StageManager.STAGE.put("Edit",stage);
//            }
//        });
        deleteref.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ReferenceDBDao referenceDBDao = new ReferenceDBDao();
                referenceDBDao.deleteSelect(newValue.getId());
                showAllRef();
                alertInputFail("????????????");
            }
        });
//        rightclick.getItems().add(editref);
        rightclick.getItems().add(deleteref);
        tvCenter.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            if(e.getButton()== MouseButton.SECONDARY&&e.getClickCount()==1){
                rightclick.show(tvCenter,e.getScreenX(),e.getScreenY());
            }
        });
        //????????????????????????,?????????from???????????????????????????
        shareref = newValue;
        loginController = (LoginController) StageManager.CONTROLLER.get("Login");
        UserDBDao userDBDao = new UserDBDao();
        User user = userDBDao.getUser(loginController.txtUsername.getText(),loginController.txtPassword.getText());
        shareref.setFrom(user.getUsername());
        shareref.setTitle(newValue.getTitle()+new Random().nextLong());
        //??????????????????id,???????????????????????????????????????
        selectref.setId(newValue.getId());
        if (newValue.getAuthor()==null||newValue.getAuthor().equals("")){
            selectref.setAuthor("??????");//??????????????????
        }else
            selectref.setAuthor(newValue.getAuthor());
        if (newValue.getYear()==null||newValue.getYear().equals("")||newValue.getYear()<2000){
            selectref.setYear(2000);//???????????????2000
        }else
            selectref.setYear(newValue.getYear());
        if (newValue.getTitle()==null||newValue.getTitle().equals("")){
            Random r = new Random();
            long l = r.nextLong();
            selectref.setTitle("?????????"+l);//???????????????????????????
        }else
            selectref.setTitle(newValue.getTitle());
        if (newValue.getRating()==null||newValue.getRating().equals("")){
            selectref.setRating("weak");//?????????weak
        }else
            selectref.setRating(newValue.getRating());
        if (newValue.getJournal()==null||newValue.getJournal().equals("")){
            selectref.setJournal("Periodical literature[J]");//???????????????
        }else
            selectref.setJournal(newValue.getJournal());
        if (newValue.getLastupdate()==null||newValue.getLastupdate().equals("")){
            selectref.setLastupdate("2022-01-01");
        }else
            selectref.setLastupdate(newValue.getLastupdate());
        if (newValue.getType()==null||newValue.getType().equals("")){
            selectref.setType("computer");
        }else
            selectref.setType(newValue.getType());
        if (newValue.getCategoryid()==null||newValue.getCategoryid().equals("")){
            selectref.setCategoryid(null);//????????????????????????,????????????
        }else
            selectref.setCategoryid(newValue.getCategoryid());
        if (newValue.getIsdelete()==null||newValue.getIsdelete().equals("")){
            selectref.setIsdelete(0);
        }else
            selectref.setIsdelete(newValue.getIsdelete());
        if (newValue.getGroup()==null||newValue.getGroup().equals("")){
            selectref.setGroup("null");//??????????????????,??????????????????operator??????????????????,????????????????????????
        }else
            selectref.setGroup(newValue.getGroup());
        if (newValue.getUrl()==null||newValue.getUrl().equals("")){
            selectref.setUrl(null);//???????????????????????????
        }else
            selectref.setUrl(newValue.getUrl());
        if (newValue.getCitation()==null||newValue.getCitation().equals("")){
            selectref.setCitation(null);//?????????????????????;
        }else
            selectref.setCitation(newValue.getCitation());
        if (newValue.getSource()==null||newValue.getSource().equals("")){
            selectref.setSource("Internet");//?????????????????????,onlinemode????????????Internet,llmode?????????Local
        }else
            selectref.setSource(newValue.getSource());
        if (newValue.getFrom()==null||newValue.getFrom().equals("")){
            selectref.setFrom(null);//????????????,????????????????????????????????????;
        }else
            selectref.setFrom(newValue.getFrom());
        if (newValue.getOperator()==null||newValue.getOperator().equals("")){
            selectref.setOperator(null);//???categoryid,group??????,newref?????????????????????group???categoryid???????????????????????????????????????
        }else
            selectref.setOperator(newValue.getOperator());
        if (newValue.getLocalsource()==null||newValue.getLocalsource().equals("")){
            selectref.setLocalsource(null);//??????????????????????????????
        }else
            selectref.setLocalsource(newValue.getLocalsource());


        //???????????????
        if (newValue.getAuthor()==null||newValue.getAuthor().equals("")||newValue.getTitle()==null||newValue.getTitle().equals("")||newValue.getJournal()==null||newValue.getJournal().equals("")){
            citation = new StringBuilder("");
        }else{
            String author = newValue.getAuthor();
            String title = newValue.getTitle();
            String journal = newValue.getJournal();
            citation.append("[]").append(author).append(". ").append(title).append("[").append(journal).append("].");
        }
        System.out.println(citation);
        //?????????
        if (newValue.getLocalsource()==null||newValue.getLocalsource().equals("")){
            foldertemp = "";
        }else
            foldertemp = newValue.getLocalsource();
        if (foldertemp==null||foldertemp.equals("")){
            folder = "";
        }else
            folder = foldertemp.substring(0,foldertemp.lastIndexOf("\\"));
        System.out.println(folder);
        //?????????????????????
        if ((newValue==null)){
            return;
        }else {
            Field[] fields = newValue.getClass().getDeclaredFields();
            for(int i = 1;i<fields.length;i++){
                String propertyname = fields[i].getName();
                propertyname = propertyname.substring(0,1).toUpperCase()+propertyname.substring(1);
                String type = fields[i].getGenericType().toString();
                if(type.equals("class java.lang.String")){
                    Method m = newValue.getClass().getMethod("get" + propertyname);
                    String finalvalue = (String) m.invoke(newValue);
                    sb.append(fields[i].getName()).append(":").append(finalvalue).append(" ");
                }
                if(type.equals("class java.lang.Integer")){
                    Method m = newValue.getClass().getMethod("get"+propertyname);
                    Integer finalvalue = (Integer) m.invoke(newValue);
                    sb.append(fields[i].getName()).append(":").append(finalvalue).append(" ");
                }
            }
        }
    }
//?????????????????????????????????????????????????????????????????????
    private void setNrDetails(String newValue) {
        //????????????
        if(newValue==null){
            return;
        } else {
            //????????????????????????????????????????????????journal???????????????,??????????????????????????????:???????????????????????????????????????????????????
            refContentController = (ChildRefController) StageManager.CONTROLLER.get("refContentController");
            refContentController.txtJournal.setText(newValue);
            ReferenceDBDao referenceDBDao = new ReferenceDBDao();
            ObservableList<Reference> list = referenceDBDao.findJournal(newValue);

            clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
            clYear.setCellValueFactory(new PropertyValueFactory("year"));
            clTitle.setCellValueFactory(new PropertyValueFactory("title"));
            clRating.setCellValueFactory(new PropertyValueFactory("rating"));
            clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
            clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
            clType.setCellValueFactory(new PropertyValueFactory("type"));

            tvCenter.setItems(list); //tableview??????list
        }
    }

//?????????????????????
    private void initSearchPopup() {
        searchPopup = new ContextMenu(new SeparatorMenuItem());
        Parent searchroot = null;
        try {
            searchroot = FXMLLoader.load(getClass().getResource("../view/searchpane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchPopup.getScene().setRoot(searchroot);
    }

    public void llmButtonClicked(ActionEvent actionEvent) throws IOException {
        leftnav.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/llmode.fxml"));
        leftnav.getChildren().setAll(pane);
    }

    public void osmButtonClicked(ActionEvent actionEvent) throws IOException {
        leftnav.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/osmode.fxml"));
        leftnav.getChildren().setAll(pane);
    }

    public void iomButtonClicked(ActionEvent actionEvent) throws IOException {
        leftnav.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/iomode.fxml"));
        leftnav.getChildren().setAll(pane);
    }

    public void RefeButtonClicked(ActionEvent actionEvent) throws IOException {
        rightarea.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/brief.fxml"));
        rightarea.getChildren().setAll(pane);
    }

    public void PrevButtonClicked(ActionEvent actionEvent) throws IOException {
        rightarea.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/preview.fxml"));
        rightarea.getChildren().setAll(pane);
    }

    public void AttaButtonClicked(ActionEvent actionEvent) throws IOException {
        rightarea.getChildren().clear();
        Pane pane = FXMLLoader.load(getClass().getResource("../view/attached.fxml"));
        rightarea.getChildren().setAll(pane);
    }
//??????????????????
    public void onSearchPopupAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnLLM.getScene().getWindow();
//        Bounds bounds = btnSSP.localToScreen(btnSSP.getBoundsInLocal());//?????????????????????????????????,?????????????????????????????????
        searchPopup.show(stage,630,210);
    }

    public void onListAllRefAction(MouseEvent mouseEvent) {
        showAllRef();
    }

    public void onListRecAddAction(MouseEvent mouseEvent) {
        showLastRef();
    }

    public void onListUnfiledAction(MouseEvent mouseEvent) {
        showUnfiled();
    }

    public void onListTrashAction(MouseEvent mouseEvent) {
        showTrash();
    }

    public void showAllRef() {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findAll();

        clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
        clYear.setCellValueFactory(new PropertyValueFactory("year"));
        clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        clType.setCellValueFactory(new PropertyValueFactory("type"));

        tvCenter.setItems(list); //tableview??????list
    }

    public void showLastRef() {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findLastone();

        clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
        clYear.setCellValueFactory(new PropertyValueFactory("year"));
        clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        clType.setCellValueFactory(new PropertyValueFactory("type"));

        tvCenter.setItems(list); //tableview??????list
    }

    public void showUnfiled(){
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findUnfiled();

        clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
        clYear.setCellValueFactory(new PropertyValueFactory("year"));
        clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        clType.setCellValueFactory(new PropertyValueFactory("type"));

        tvCenter.setItems(list); //tableview??????list
    }

    public void showTrash(){
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findTrash();

        clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
        clYear.setCellValueFactory(new PropertyValueFactory("year"));
        clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        clType.setCellValueFactory(new PropertyValueFactory("type"));

        tvCenter.setItems(list); //tableview??????list
    }
//????????????
    public void onNrPopupAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/newRef.fxml"));
        stage.setTitle("New Reference");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("NewRef",stage);
//        StageManager.CONTROLLER.put("NewRef",NewRefController.class);
//        StageManager.CONTROLLER.put("refContent",ChildRefController.class);
//        StageManager.CONTROLLER.put("ataContent",ChildAtaController.class);
    }
//????????????
    public void OnIfPopupAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/importfile.fxml"));
        stage.setTitle("Import File");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("ImportFile",stage);
    }
//????????????
    public void OnExportFileAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle("Export file name:");
        fc.setInitialDirectory(new File("D:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.3.1\\IdeaProjects\\rmcs\\src\\resources"));
        File file = fc.showSaveDialog(stage);
        if (!file.exists()){
            file.createNewFile();
        }
        System.out.println(file);//???????????????file????????????????????????
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(sb.toString());
        fileWriter.flush();
        fileWriter.close();
    }
//?????????????????????????????????????????????
    public void onOpenFolderAction(MouseEvent mouseEvent) {
        System.out.println(folder);
        if (folder!=null){
            try {
                Desktop.getDesktop().open(new File(folder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            alertInputFail("????????????????????????????????????");
    }
//    ??????word???????????????????????????????????????????????????????????????????????????????????????
    public void onInsertCitationAction(MouseEvent mouseEvent) throws IOException, InterruptedException {
        if (citation==null){
            alertInputFail("??????????????????????????????");
        }else{
            System.out.println(foldertemp);
//            FileWriter writer = new FileWriter(foldertemp);//filewriter???????????????word
//            writer.write(citation.toString());
//            FileInputStream fis = new FileInputStream(citation.toString());
            FileOutputStream fos = new FileOutputStream(foldertemp);
            byte[] bytes = citation.toString().getBytes(StandardCharsets.UTF_8);
            fos.write(bytes,0,bytes.length);
            fos.flush();
            fos.close();
            alertInputFail("????????????????????????word");
            //??????word
            String[] cmd = {"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.exe",foldertemp};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
//            writer.flush();
//            writer.close();
        }
    }
//    ??????????????????????????????
    public void onFormatAction(MouseEvent mouseEvent) {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("????????????????????????,?????????????????????");
        alert.show();
        Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        Button cancel = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                referenceDBDao.updateSelect(selectref);
                showAllRef();
            }
        });
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.close();
            }
        });
    }
//    ???word???????????????foldertemp?????????
    public void onToWordAction(MouseEvent mouseEvent) throws InterruptedException, IOException {
        String[] cmd = {"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.exe",foldertemp};
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
    }
//    ????????????
//    ????????????
    public void onShareAction(MouseEvent mouseEvent) throws IOException {
        System.out.println(shareref.toString());
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/share.fxml"));
        stage.setTitle("Share");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("Share",stage);
    }
//    ????????????
    public void onHelpPopupAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/help.fxml"));
        stage.setTitle("Help");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("Help",stage);
    }
//    ????????????
    public void onQkSearchAction(MouseEvent mouseEvent) {
        ReferenceDBDao referenceDBDao = new ReferenceDBDao();
        ObservableList<Reference> list = referenceDBDao.findlikeTitle(txtSearchBox.getText());;

        clAuthor.setCellValueFactory(new PropertyValueFactory("author"));//??????
        clYear.setCellValueFactory(new PropertyValueFactory("year"));
        clTitle.setCellValueFactory(new PropertyValueFactory("title"));
        clRating.setCellValueFactory(new PropertyValueFactory("rating"));
        clJournal.setCellValueFactory(new PropertyValueFactory("journal"));
        clUpdated.setCellValueFactory(new PropertyValueFactory("lastupdate"));
        clType.setCellValueFactory(new PropertyValueFactory("type"));

        tvCenter.setItems(list); //tableview??????list
    }
//    ????????????
    public void onMsgPopupAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/message.fxml"));
        stage.setTitle("Communication Zone");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("Message",stage);
    }

    private void alertInputFail(String string) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().lookupButton(ButtonType.CLOSE);
        alert.setContentText(string);
        alert.showAndWait();
    }

    public void onCtlAction(MouseEvent mouseEvent) {
        alertInputFail("????????????");
    }

//linshigai
    public void onEditAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/editRef.fxml"));
        stage.setTitle("Edit Reference");
        stage.setScene(new Scene(root));
        stage.show();
        StageManager.STAGE.put("Edit",stage);
    }
}
