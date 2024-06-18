package soceconeprof;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button addHousehold_Btn;

    @FXML
    private Button addHousehold_addBtn;

    @FXML
    private TextField addHousehold_address;

    @FXML
    private Button addHousehold_clearBtn;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_address;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_date;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_employed;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_houseMembers;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_houseNumber;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_phoneNumber;

    @FXML
    private TableColumn<householdData, String> addHousehold_col_representative;

    @FXML
    private Button addHousehold_deleteBtn;

    @FXML
    private TextField addHousehold_employed;

    @FXML
    private AnchorPane addHousehold_form;

    @FXML
    private TextField addHousehold_houseNumber;

    @FXML
    private TextField addHousehold_householdMembers;

    @FXML
    private ImageView addHousehold_image;

    @FXML
    private Button addHousehold_importBtn;

    @FXML
    private TextField addHousehold_phoneNumber;

    @FXML
    private TextField addHousehold_representative;

    @FXML
    private TextField addHousehold_search;

    @FXML
    private TableView<householdData> addHousehold_tableView;

    @FXML
    private Button addHousehold_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_Btn;

    @FXML
    private BarChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totalHousehold;

    @FXML
    private Label dashboard_totalNonUpdated;

    @FXML
    private Label dashboard_totalPopulation;

    @FXML
    private Button income_Btn;

    @FXML
    private Label income_address;

    @FXML
    private Button income_clearBtn;

    @FXML
    private TableColumn<householdData,String> income_col_address;

    @FXML
    private TableColumn<householdData,String> income_col_houseNumber;

    @FXML
    private TableColumn<householdData,String> income_col_income;

    @FXML
    private TableColumn<householdData,String> income_col_phoneNumber;

    @FXML
    private TableColumn<householdData,String> income_col_representative;

    @FXML
    private AnchorPane income_form;

    @FXML
    private Label income_houseNumber;

    @FXML
    private ComboBox<?> income_income;

    @FXML
    private Label income_phoneNum;

    @FXML
    private Label income_representative;

    @FXML
    private TableView<householdData> income_tableView;

    @FXML
    private Button income_report;
    
    @FXML
    private Button income_updateBtn;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private TextField income_search;
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;
    
    public void printReport(){
        connect = database.connectDb();
        try{
        JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\QUEANA WESLEY\\OneDrive\\Documents\\NetBeansProjects\\soceconEprof\\src\\soceconeprof\\report1.jrxml");
        
        JasperReport jReport = JasperCompileManager.compileReport(jDesign);
        
        JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
        
        JasperViewer viewer = new JasperViewer(jPrint, false);
        
        viewer.setTitle("File Report");
        viewer.show();
        
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void defaultNav(){
        dashboard_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #44bc8c, #90e1bc);");
        
    }

    public void homeTotalHousehold(){
        String sql = "SELECT COUNT(number) FROM household";
        
        connect = database.connectDb();
        
        int countData = 0;
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countData = result.getInt("COUNT(number)");
                
            }
            dashboard_totalHousehold.setText(String.valueOf(countData));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeTotalPopulation(){
        String sql = "SELECT SUM(householdMembers) FROM household";
        
        connect = database.connectDb();
        
        int countData = 0;
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countData = result.getInt("SUM(householdMembers)");
                
            }
            dashboard_totalPopulation.setText(String.valueOf(countData));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeTotalNonupdatedHousehold(){
        String sql = "SELECT COUNT(number) FROM householdinfo WHERE income = '0'";
        
        connect = database.connectDb();
        
        int countData = 0;
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countData = result.getInt("COUNT(number)");
                
            }
            
            dashboard_totalNonUpdated.setText(String.valueOf(countData));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void homeChart(){
        dashboard_chart.getData().clear();
        
        String sql = "SELECT income, COUNT(number) AS num_representative FROM householdinfo WHERE income != '0' GROUP BY income ORDER BY income LIMIT 15";
        
        connect = database.connectDb();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString("income"), result.getInt("num_representative")));
            }
            
            dashboard_chart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void addHouseholdAdd() {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO household "
                + "(representative,house_number,zone,phoneNumber,householdMembers,employed,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (addHousehold_representative.getText().isEmpty()
                    || addHousehold_houseNumber.getText().isEmpty()
                    || addHousehold_address.getText().isEmpty()
                    || addHousehold_phoneNumber.getText().isEmpty()
                    || addHousehold_householdMembers.getText().isEmpty()
                    || addHousehold_employed.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all black fields");
                alert.showAndWait();
            } else {

                String check = "SELECT representative FROM household WHERE representative = '"
                        +addHousehold_representative.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Representative: " + addHousehold_representative.getText() + " was already exist!");
                    alert.showAndWait();

                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addHousehold_representative.getText());
                    prepare.setString(2, addHousehold_houseNumber.getText());
                    prepare.setString(3, addHousehold_address.getText());
                    prepare.setString(4, addHousehold_phoneNumber.getText());
                    prepare.setString(5, addHousehold_householdMembers.getText());
                    prepare.setString(6, addHousehold_employed.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);
                    prepare.setString(8, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    
                    String insertInfo = "INSERT INTO householdinfo"
                            + "(representative,house_number,zone,phoneNumber,householdMembers,employed,income)"
                            + "VALUES(?,?,?,?,?,?,?)";
                    
                    prepare = connect.prepareStatement(insertInfo);
                    prepare.setString(1, addHousehold_representative.getText());
                    prepare.setString(2, addHousehold_houseNumber.getText());
                    prepare.setString(3, addHousehold_address.getText());
                    prepare.setString(4, addHousehold_phoneNumber.getText());
                    prepare.setString(5, addHousehold_householdMembers.getText());
                    prepare.setString(6, addHousehold_employed.getText());
                    prepare.setString(7, "0");
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    addHouseholdShowListData();
                    addHouseholdReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void addHouseholdUpdate(){
    
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        String sql = "UPDATE household SET house_number = '"
                +addHousehold_houseNumber.getText()+"', zone = '"
                +addHousehold_address.getText()+"', phoneNumber = '"
                +addHousehold_phoneNumber.getText()+"', householdMembers = '"
                +addHousehold_householdMembers.getText()+"', employed = '"
                +addHousehold_employed.getText()+"', image = '"
                +uri+"', date = '"+sqlDate+"' WHERE representative = '"
                +addHousehold_representative.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;

            if (addHousehold_representative.getText().isEmpty()
                    || addHousehold_houseNumber.getText().isEmpty()
                    || addHousehold_address.getText().isEmpty()
                    || addHousehold_phoneNumber.getText().isEmpty()
                    || addHousehold_householdMembers.getText().isEmpty()
                    || addHousehold_employed.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all black fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Household Representative: "+ addHousehold_representative.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    String income;
                    
                    String checkData = "SELECT * FROM householdinfo WHERE representative = '"
                            +addHousehold_representative.getText()+"'";
                    
                    prepare = connect.prepareStatement(checkData);
                    result = prepare.executeQuery();
                    
                    while(result.next()){
                        income = result.getString("income");
                        
                    }
                    
                    String updateInfo = "UPDATE householdinfo SET house_number = '"
                            +addHousehold_houseNumber.getText()+"', zone = '"
                            +addHousehold_address.getText()+"', phoneNumber = '"
                            +addHousehold_phoneNumber.getText()
                            +"' WHERE representative = '"
                            +addHousehold_representative.getText()+"'";
                    
                    prepare = connect.prepareStatement(updateInfo);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    addHouseholdShowListData();
                    addHouseholdReset();
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addHouseholdDelete(){
    
        String sql = "DELETE FROM household WHERE representative = '"
                +addHousehold_representative.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;

            if (addHousehold_representative.getText().isEmpty()
                    || addHousehold_houseNumber.getText().isEmpty()
                    || addHousehold_address.getText().isEmpty()
                    || addHousehold_phoneNumber.getText().isEmpty()
                    || addHousehold_householdMembers.getText().isEmpty()
                    || addHousehold_employed.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all black fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Household Representative: "+ addHousehold_representative.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    String deleteInfo = "DELETE FROM householdinfo WHERE representative = '"
                            +addHousehold_representative.getText()+"'";
                    
                    prepare = connect.prepareStatement(deleteInfo);
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    addHouseholdShowListData();
                    addHouseholdReset();
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void addHouseholdReset(){
    
        addHousehold_representative.setText("");
        addHousehold_houseNumber.setText("");
        addHousehold_address.setText("");
        addHousehold_phoneNumber.setText("");
        addHousehold_householdMembers.setText("");
        addHousehold_employed.setText("");
        addHousehold_image.setImage(null);
        getData.path = "";
    
    }

    public void addHouseholdInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 110, 110, false, true);
            addHousehold_image.setImage(image);
        }

    }
    
    public void addHouseholdSearch(){
    
        FilteredList<householdData> filter = new FilteredList<>(addHouseholdList, e-> true);
        
        addHousehold_search.textProperty().addListener((Observable, oldValue, newValue) ->{
        
            filter.setPredicate(predicatedHouseholdData ->{
            
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if(predicatedHouseholdData.getHouseholdRepresentative().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getHouseNumber().toString().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getZone().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getPhoneNumber().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getHouseMembers().toString().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getEmployed().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicatedHouseholdData.getDate().toString().contains(searchKey)){
                    return true;
                }else{ 
                    return false;
                }
            });
        });
        
        SortedList<householdData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addHousehold_tableView.comparatorProperty());
        addHousehold_tableView.setItems(sortList);
    
    }

    public ObservableList<householdData> addHouseholdListData() {

        ObservableList<householdData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM household";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            householdData householdD;

            while (result.next()) {
                householdD = new householdData(result.getString("representative"),
                         result.getInt("house_number"),
                         result.getString("zone"),
                         result.getString("phoneNumber"),
                         result.getInt("householdMembers"),
                         result.getString("employed"),
                         result.getString("image"),
                         result.getDate("date"));

                listData.add(householdD);

            }

        }catch(Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<householdData> addHouseholdList;

    public void addHouseholdShowListData() {
        addHouseholdList = addHouseholdListData();
        
        addHousehold_col_representative.setCellValueFactory(new PropertyValueFactory<>("householdRepresentative"));
        addHousehold_col_houseNumber.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        addHousehold_col_address.setCellValueFactory(new PropertyValueFactory<>("zone"));
        addHousehold_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addHousehold_col_houseMembers.setCellValueFactory(new PropertyValueFactory<>("houseMembers"));
        addHousehold_col_employed.setCellValueFactory(new PropertyValueFactory<>("employed"));
        addHousehold_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addHousehold_tableView.setItems(addHouseholdList);
        
    }

    public void addHouseholdSelect() {
        householdData householdD = addHousehold_tableView.getSelectionModel().getSelectedItem();
        int num = addHousehold_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {return;}

        addHousehold_representative.setText(householdD.getHouseholdRepresentative());
        addHousehold_houseNumber.setText(String.valueOf(householdD.getHouseNumber()));
        addHousehold_address.setText(householdD.getZone());
        addHousehold_phoneNumber.setText(householdD.getPhoneNumber());
        addHousehold_householdMembers.setText(String.valueOf(householdD.getHouseMembers()));
        addHousehold_employed.setText(householdD.getEmployed());

        getData.path = householdD.getImage();
        
        String uri = "file:" + householdD.getImage();

        image = new Image(uri, 110, 110, false, true);
        addHousehold_image.setImage(image);
    }

    public void incomeUpdate(){
        
        String sql = "UPDATE householdinfo SET income = '"
                +income_income.getSelectionModel().getSelectedItem()+"' WHERE representative = '"
                +income_representative.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(income_representative.getText().isEmpty() 
                    || income_houseNumber.getText().isEmpty()
                    || income_address.getText().isEmpty()
                    || income_phoneNum.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();
                
            }
            else{
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();
                
                incomeShowListData();
                
            }
            
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void incomeReset(){
        income_representative.setText("");
        income_houseNumber.setText("");
        income_address.setText("");
        income_phoneNum.setText("");
        income_income.getSelectionModel().clearSelection();
    }
    
    public ObservableList<householdData> incomeListData(){
    
        ObservableList<householdData> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM householdinfo";
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            householdData householdD;
            
            while(result.next()){
                householdD = new householdData(result.getString("representative")
                        , result.getInt("house_number")
                        , result.getString("zone")
                        , result.getString("phoneNumber")
                        , result.getInt("householdMembers")
                        , result.getString("employed")
                        , result.getString("income"));
                
                listData.add(householdD);
            }
            
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    private ObservableList<householdData> incomeList;
    
    public void incomeShowListData(){
        incomeList = incomeListData();
        
        income_col_representative.setCellValueFactory(new PropertyValueFactory<>("householdRepresentative"));
        income_col_houseNumber.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        income_col_address.setCellValueFactory(new PropertyValueFactory<>("zone"));
        income_col_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        income_col_income.setCellValueFactory(new PropertyValueFactory<>("income"));
        
        income_tableView.setItems(incomeList);
    }
    
    public void incomeSelect(){
        householdData householdD = income_tableView.getSelectionModel().getSelectedItem();
        int num = income_tableView.getSelectionModel().getSelectedIndex();
        
        if((num - 1) < -1){
            return;
        }
        income_representative.setText(householdD.getHouseholdRepresentative());
        income_houseNumber.setText(String.valueOf(householdD.getHouseNumber()));
        income_address.setText(householdD.getZone());
        income_phoneNum.setText(householdD.getPhoneNumber());
        
    }
    
    private String[] income_incomeList = {"100k - 500k", "500k - 1M", "1M - 2M", "2M - 4M", "4M+"};
    public void income_incomeList(){
        List<String> listInc = new ArrayList<>();
        
        for(String data: income_incomeList){
            listInc.add(data);
            
        }
        ObservableList listIncome = FXCollections.observableArrayList(listInc);
        income_income.setItems(listIncome);
    }
    
    public void incomeSearch(){
    
        FilteredList<householdData> filter = new FilteredList<>(incomeList, e-> true);
        
        income_search.textProperty().addListener((Observable, oldValue, newValue) ->{
        
            filter.setPredicate(predicateHouseholdData ->{
            
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                
                if(predicateHouseholdData.getHouseholdRepresentative().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicateHouseholdData.getIncome().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicateHouseholdData.getHouseNumber().toString().contains(searchKey)){
                    return true;
                }
                else if(predicateHouseholdData.getZone().toLowerCase().contains(searchKey)){
                    return true;
                }
                else if(predicateHouseholdData.getPhoneNumber().toLowerCase().contains(searchKey)){
                    return true;
                }else{ 
                    return false;
                }
            });
        });
        
        SortedList<householdData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(income_tableView.comparatorProperty());
        income_tableView.setItems(sortList);
    
    }
    
    public void displayUsername() {
        username.setText(getData.username);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_Btn) {
            dashboard_form.setVisible(true);
            addHousehold_form.setVisible(false);
            income_form.setVisible(false);

            dashboard_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #44bc8c, #90e1bc);");
            addHousehold_Btn.setStyle("-fx-background-color:transparent;");
            income_Btn.setStyle("-fx-background-color:transparent;");
            
            homeTotalHousehold();
            homeTotalPopulation();
            homeTotalNonupdatedHousehold();
            homeChart();
        } else if (event.getSource() == addHousehold_Btn) {
            dashboard_form.setVisible(false);
            addHousehold_form.setVisible(true);
            income_form.setVisible(false);

            addHousehold_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #44bc8c, #90e1bc);");
            dashboard_Btn.setStyle("-fx-background-color:transparent;");
            income_Btn.setStyle("-fx-background-color:transparent;");
            
            addHouseholdSearch();
        } else if (event.getSource() == income_Btn) {
            dashboard_form.setVisible(false);
            addHousehold_form.setVisible(false);
            income_form.setVisible(true);

            income_Btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #44bc8c, #90e1bc);");
            addHousehold_Btn.setStyle("-fx-background-color:transparent;");
            dashboard_Btn.setStyle("-fx-background-color:transparent;");
            
            incomeShowListData();
            income_incomeList();
        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();
        
        homeTotalHousehold();
        homeTotalPopulation();
        homeTotalNonupdatedHousehold();
        homeChart();
        
        addHouseholdShowListData();
        
        incomeShowListData();
        income_incomeList();
    }

}
