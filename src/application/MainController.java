package application;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import scrapper.CsvFileHandeler;
import scrapper.FireFoxOperator;
import scrapper.Info;

public class MainController implements Initializable{
	
	@FXML
	private Button btnLaunch, btnLogin, btnSettings, btnBrowse, btnRun, btnPrintList; 
	@FXML
	private TextField tfSelectedFilePath;
	
	@FXML
	private ImageView logoView;
	
	CsvFileHandeler csvFileHandeler = null;
	LinkedList<Info> list = null;
	FireFoxOperator fireFoxOperator = null;
	
	// constructor is called before initialize() method
	public MainController() {
		System.out.println("Constructor");
		
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initialize");
		csvFileHandeler = new CsvFileHandeler();
		fireFoxOperator = new FireFoxOperator();
		File file = new File("image/yin-yang.jpg");
        Image image = new Image(file.toURI().toString());
        logoView.setImage(image);
		
	}
	
	
	@FXML
	private void launchBtnAction(ActionEvent event) {
		System.out.println("Launch Button");
		fireFoxOperator.browserLauncher();
	}
	
	@FXML
	private void loginBtnAction(ActionEvent event) {
		System.out.println("Login Button");
	}
	
	@FXML
	private void settingsBtnAction(ActionEvent event) {
		System.out.println("Settings Button");
	}
	
	@FXML
	private void browseBtnAction(ActionEvent event) {
		System.out.println("Browse Button");
		//stackoverflow.com/questions/25491732/how-do-i-open-the-javafx-filechooser-from-a-controller-class/25491787
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("CSV Files", "*.csv")
			);
		
        File file = fileChooser.showOpenDialog(new Stage());
        
        if(file != null) {
        	tfSelectedFilePath.setText(file.getAbsolutePath());
        	String filepath = tfSelectedFilePath.getText().toString();
    		if(filepath.endsWith(".csv")) {
    			list = csvFileHandeler.read(filepath);
    		}
        }
        
	}
	
	@FXML
	private void runBtnAction(ActionEvent event) {
		System.out.println("Run Button");
	}

	@FXML
	private void printListBtnAction(ActionEvent event) {
		System.out.println("Print Button");
		csvFileHandeler.write(list, list.size()+"");
	}
	

	
	
}
