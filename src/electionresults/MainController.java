/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electionresults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.annotation.processing.FilerException;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

/**
 * FXML Controller class
 *
 * @author Claudinea de Almeida
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnUploadFile;
    @FXML
    private Label loading;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnGoFilter;
    @FXML
    private TextField textFilter;
    @FXML
    private TableView tableResults;
    @FXML
    private BarChart barChartResults;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    

    @FXML
    private TableColumn<Election, String> columnCandidateFirstName;
    @FXML
    private TableColumn<Election, String> columnCandidateSurname;
    @FXML
    private TableColumn<Election, String> columnConstituencyName;
    @FXML
    private TableColumn<Election, String> columnResult;
    @FXML
    private TableColumn<Election, Integer> columnTotalVotes;
    @FXML
    private TableColumn<Election, Integer> columnVotes;
    @FXML
    private TableColumn<Election, Integer> columnCountNumber;
    @FXML
    private TableColumn<Election, Integer> columnNonTransferable;
    @FXML
    private TableColumn<Election, Integer> columnOccurredOnCount;
    @FXML
    private TableColumn<Election, Integer> columnRequiredToReachQuota;
    @FXML
    private TableColumn<Election, Integer> columnRequiredToSaveDeposit;
    @FXML
    private TableColumn<Election, Integer> columnTransfers;
    @FXML
    private TableColumn<Election, Integer> columnConstituencyNumber;
    @FXML
    private TableColumn<Election, Integer> columnCandidateId;

    private BufferedReader reader = null;

    private final TableView<Election> tableView = new TableView<>();

    private final ObservableList<Election> dataList = FXCollections.observableArrayList();
    private  ObservableList<String> candidateList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        columnConstituencyName.setCellValueFactory(new PropertyValueFactory<>("Constituency"));
        columnCandidateSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        columnCandidateFirstName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnResult.setCellValueFactory(new PropertyValueFactory<>("Result"));
        columnCountNumber.setCellValueFactory(new PropertyValueFactory<>("Count N"));
        columnNonTransferable.setCellValueFactory(new PropertyValueFactory<>("N Transf"));
        columnOccurredOnCount.setCellValueFactory(new PropertyValueFactory<>("Occurred"));
        columnRequiredToReachQuota.setCellValueFactory(new PropertyValueFactory<>("Quota"));
        columnRequiredToSaveDeposit.setCellValueFactory(new PropertyValueFactory<>("Deposit"));
        columnTransfers.setCellValueFactory(new PropertyValueFactory<>("Transfers"));
        columnVotes.setCellValueFactory(new PropertyValueFactory<>("Votes"));
        columnTotalVotes.setCellValueFactory(new PropertyValueFactory<>("Total Votes"));
        columnConstituencyNumber.setCellValueFactory(new PropertyValueFactory<>("Const N"));
        columnCandidateId.setCellValueFactory(new PropertyValueFactory<>("Cand Id"));

        btnExit.setOnAction(e -> Platform.exit());

        xAxis.setLabel("Candidate");
        yAxis.setLabel("Votes");
        

       // updateChart();


    }

    @FXML
    public void btnGoFilterClick(ActionEvent event) throws FileNotFoundException {

        if (textFilter.getText().equals("")) {
            fillTbElection(dataList);
        } else {

            ArrayList<Election> foo = new ArrayList<Election>(dataList);
            ObservableList<Election> dataList2 = filterList(foo, textFilter.getText());
            fillTbElection(dataList2);
        }

    }

    private void fillTbElection(ObservableList<Election> data) {

        columnConstituencyName.setCellValueFactory(new PropertyValueFactory<>("Constituency"));
        columnCandidateSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        columnCandidateFirstName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnResult.setCellValueFactory(new PropertyValueFactory<>("Result"));
        columnCountNumber.setCellValueFactory(new PropertyValueFactory<>("Count N"));
        columnNonTransferable.setCellValueFactory(new PropertyValueFactory<>("N Transf"));
        columnOccurredOnCount.setCellValueFactory(new PropertyValueFactory<>("Occurred"));
        columnRequiredToReachQuota.setCellValueFactory(new PropertyValueFactory<>("Quota"));
        columnRequiredToSaveDeposit.setCellValueFactory(new PropertyValueFactory<>("Deposit"));
        columnTransfers.setCellValueFactory(new PropertyValueFactory<>("Transfers"));
        columnVotes.setCellValueFactory(new PropertyValueFactory<>("Votes"));
        columnTotalVotes.setCellValueFactory(new PropertyValueFactory<>("Total Votes"));
        columnConstituencyNumber.setCellValueFactory(new PropertyValueFactory<>("Const N"));
        columnCandidateId.setCellValueFactory(new PropertyValueFactory<>("Cand Id"));

        tableResults.setItems(data);
        loadingData();
       // updateChart();
    }

    public void loadingData() {
        columnConstituencyName.setCellValueFactory(new Callback<CellDataFeatures<Election, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Election, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getConstituencyName().getValue());
            }
        });
        columnCandidateSurname.setCellValueFactory(new Callback<CellDataFeatures<Election, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Election, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCandidateSurname().getValue());
            }
        });
        columnCandidateFirstName.setCellValueFactory(new Callback<CellDataFeatures<Election, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Election, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCandidateFirstName().getValue());
            }
        });
        columnResult.setCellValueFactory(new Callback<CellDataFeatures<Election, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Election, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getResult().getValue());
            }
        });
        columnVotes.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getVotes().getValue());
            }
        });
        columnCountNumber.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCountNumber().getValue());
            }
        });
        columnNonTransferable.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getNonTransferable().getValue());
            }
        });

        columnOccurredOnCount.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getOccurredOnCount().getValue());
            }
        });
        columnRequiredToReachQuota.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRequiredToReachQuota().getValue());
            }
        });
        columnRequiredToSaveDeposit.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getRequiredToSaveDeposit().getValue());
            }
        });
        columnTransfers.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTransfers().getValue());
            }
        });

        columnTotalVotes.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getTotalVotes().getValue());
            }
        });
        columnConstituencyNumber.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getConstituencyNumber().getValue());
            }
        });

        columnCandidateId.setCellValueFactory(new Callback<CellDataFeatures<Election, Integer>, ObservableValue<Integer>>() {
            public ObservableValue<Integer> call(CellDataFeatures<Election, Integer> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getCandidateId().getValue());
            }
        });
    }

    @FXML
    public void btnUploadFileAction(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File selectFile = fc.showOpenDialog(null);

        try {
            readCSV(selectFile);
            updateChart();
            
           

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   private void updateChart() {
        //Update the Chart
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        xAxis.setCategories(candidateList);
        int candidateVote;

       String candidateName = "";
       ArrayList<Election> sumarizedCandidate = new ArrayList<Election>();

       List<Election> copy = new ArrayList<Election>(dataList);

       for (int i=0; i<copy.size();i++){
           if (copy.get(i).getCountNumber().getValue()==10){
               if(copy.get(i).getResult().getValue().equals("Elected")){
               sumarizedCandidate.add(copy.get(i));
               }
           }
       }
       System.out.println("Printing copy");

       for (int i=0; i<sumarizedCandidate.size();i++) {
           System.out.println(sumarizedCandidate.get(i).getCandidateSurname().getValue()+ " "+sumarizedCandidate.get(i).getVotes().getValue());
        
           candidateList.add(sumarizedCandidate.get(i).getCandidateSurname().getValue());
           candidateVote = sumarizedCandidate.get(i).getVotes().getValue();
           series = new XYChart.Series<>();
           series.getData().add(new XYChart.Data<>(candidateList.get(i), candidateVote));
           barChartResults.getData().add(series);
       }
     
       
   }
    
    
    private boolean searchFindsCandidate(Election election, String searchText) {
        return (election.getConstituencyName().getValue().toLowerCase().contains(searchText.toLowerCase()))
                || (election.getCandidateSurname().getValue().toLowerCase().contains(searchText.toLowerCase()))
                || (election.getCandidateFirstName().getValue().toLowerCase().contains(searchText.toLowerCase()))
                || Integer.valueOf(election.getCandidateId().getValue()).toString().equals(searchText.toLowerCase());
    }

    private ObservableList<Election> filterList(List<Election> list, String searchText) {
        List<Election> filteredList = new ArrayList<>();
        for (Election election : list) {
            if (searchFindsCandidate(election, searchText)) {
                filteredList.add(election);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    //============================================================================================
    // Display a JavaFX Error Dialog.
    // This method will not return until the user clicks "ok" in the displayed dialog.
    //============================================================================================
    private void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void readCSV(File selectFile) throws FileNotFoundException {

        String FieldDelimiter = ",";
        String FieldDelimiter2 = "^[,;]+$";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(selectFile), StandardCharsets.ISO_8859_1))) {
            br.readLine();

            int i = 0;
            String line;
            Regex rg = new Regex(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
           
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(FieldDelimiter, -1);

                String result = "";
                Election election = new Election();

                result = fields[0].replaceAll("\"", "");;
                election.setConstituencyName(result);

                // Surname split
                String result3 = "";
                String result2 = fields[1].replaceAll("\"", "");
                System.out.println("First char of " + result2 + "is: " + result2.charAt(0));
                if (result2.charAt(0) == '"' ) {
                    result2 = result2.substring(1, result2.length());
                }

                System.out.println(result2);
                election.setCandidateSurname(result2);

                ////////////////////////////////////////////
                result2 = "";
                result3 = "";
                result2 = fields[2].replaceAll("\"", "");
                System.out.println("First char of " + result2 + "is: " + result2.charAt(0));
                if (result2.charAt(0) == '"') {
                    System.out.println("Invalid");
                    
                    result2 = fields[3].replaceAll("\"", "");
                    election.setCandidateFirstName(result2);
                    result = fields[4].replaceAll("\"", "");
                    result = result.replaceAll("\"^[0-9,;]+$\"", "");
                    election.setResult(result);
                    // System.out.println(i);
                    election.setCountNumber(parseInt(getOnlyDigits(fields[5])));
                    election.setNonTransferable(parseInt(getOnlyDigits(fields[6])));
                    election.setOccurredOnCount(parseInt(getOnlyDigits(fields[7])));
                    election.setRequiredToReachQuota(parseInt(getOnlyDigits(fields[8])));
                    election.setRequiredToSaveDeposit(parseInt(getOnlyDigits(fields[9])));
                    election.setTransfers(parseInt(getOnlyDigits(fields[10])));
                    election.setVotes(parseInt(getOnlyDigits(fields[11])));
                    election.setTotalVotes(parseInt(getOnlyDigits(fields[12])));
                    election.setConstituencyNumber(parseInt(getOnlyDigits(fields[13])));
                    election.setCandidateId(parseInt(getOnlyDigits(fields[14])));
                }else{
                    
                
                    result2 = fields[2];
                    election.setCandidateFirstName(result2);
                    result = fields[3].replaceAll("\"", "");
                    result = result.replaceAll("\"^[0-9,;]+$\"", "");
                    election.setResult(result);
                    // System.out.println(i);
                    election.setCountNumber(parseInt(getOnlyDigits(fields[4])));
                    election.setNonTransferable(parseInt(getOnlyDigits(fields[5])));
                    election.setOccurredOnCount(parseInt(getOnlyDigits(fields[6])));
                    election.setRequiredToReachQuota(parseInt(getOnlyDigits(fields[7])));
                    election.setRequiredToSaveDeposit(parseInt(getOnlyDigits(fields[8])));
                    election.setTransfers(parseInt(getOnlyDigits(fields[9])));
                    election.setVotes(parseInt(getOnlyDigits(fields[10])));
                    election.setTotalVotes(parseInt(getOnlyDigits(fields[11])));
                    election.setConstituencyNumber(parseInt(getOnlyDigits(fields[12])));
                    election.setCandidateId(parseInt(getOnlyDigits(fields[13])));
        
                }
                
              
                dataList.add(election);

                tableResults.setItems(dataList);
                loadingData();

                //Adding data to the 
                ObservableList<String> list = FXCollections.observableArrayList();
                //  tableResults.setItems(dataList);
                tableResults.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                //   tableResults.getColumns().addAll(Constituency, Surname);

                // tableView.setItems(dataList);
                tableResults.setEditable(true);
                tableResults.getSelectionModel().setCellSelectionEnabled(true);

                i = i + 1;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    public static String getOnlyDigits(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number;
    }

    public static String getOnlyStrings(String s) {
        String regx = "\"";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(s);
        String text = matcher.replaceAll("");
        return text;
    }

    private static String convertString(String a) {
        char character = '"';
        String result;
        int size = a.length();
        String[] union = new String[size];
        char comparador = Character.toLowerCase(character);

        for (int i = 0; i < a.length(); i++) {
            char charAt = a.charAt(i);
            if (Character.toLowerCase(charAt) == comparador) {
                System.out.println(character + " encontrado em " + i + " --> " + charAt);

            } else {
                union[i] = Character.toString(charAt);
            }
            System.out.println(union[i]);
        }
        return a;
    }

    private void initialTable() {

        TableColumn columnConstituencyName = new TableColumn("Constituency");
        columnConstituencyName.setCellValueFactory(
                new PropertyValueFactory<>("Constituency"));

        TableColumn columnCandidateSurname = new TableColumn("Candidate Surname");
        columnCandidateSurname.setCellValueFactory(
                new PropertyValueFactory<>("Candidate Surname"));

        TableColumn columnCandidateFirstName = new TableColumn("Candidate Name");
        columnCandidateFirstName.setCellValueFactory(
                new PropertyValueFactory<>("Candidate Name"));

        TableColumn columnResult = new TableColumn("Result");
        columnResult.setCellValueFactory(
                new PropertyValueFactory<>("Result"));

        TableColumn columnCountNumber = new TableColumn("CountNumber");
        columnCountNumber.setCellValueFactory(
                new PropertyValueFactory<>("CountNumber"));

        TableColumn columnNonTransferable = new TableColumn("Non Transferable");
        columnNonTransferable.setCellValueFactory(
                new PropertyValueFactory<>("NonTransferable"));

        TableColumn columnVotes = new TableColumn("Votes");
        columnVotes.setCellValueFactory(
                new PropertyValueFactory<>("Votes"));

        TableColumn columnTotalVotes = new TableColumn("Total Votes");
        columnTotalVotes.setCellValueFactory(
                new PropertyValueFactory<>("Total Votes"));

        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnConstituencyName, columnCandidateSurname, columnCandidateFirstName, columnResult, columnCountNumber, columnNonTransferable);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(tableView);
    }

}
