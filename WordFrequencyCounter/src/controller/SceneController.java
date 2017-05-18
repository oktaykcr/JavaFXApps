package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.WordCounter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SceneController implements Initializable{
	@FXML
	private Button loadButton;
	@FXML
	private Button saveButton;
	@FXML
	private Label label1;//txt file name
	@FXML
	private Label label2;//Total unique word count
	@FXML
	private Label label3;//findword result text
	@FXML
	private TextArea textArea1;//original txt file content
	@FXML
	private TextArea textArea2;//word frequency counter content
	@FXML
	private TextField textField;//find tf
	
	private Stage stage;
	private WordCounter wordCounter;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = Main.getPrimaryStage();
		textField.setDisable(true);
		saveButton.setDisable(true);
	}
	
	public void loadFile(){
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null){
        	 wordCounter=new WordCounter(file);
             wordCounter.readWords(textArea1,textArea2);
             
             label1.setText(wordCounter.getFileName());
             label2.setText("Total unique word count: "+wordCounter.getnWords());
             textField.setDisable(false);
             saveButton.setDisable(false);
        }
	}
	public void saveFile(){
		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        if(file!=null){
        	wordCounter.writeFile(file);
        	infoDialog(file.getName()+" saved successfully."+System.getProperty("line.separator")+file.getAbsolutePath());
        }	
	}
	public void findWord(){
		label3.setText(wordCounter.findWordCount(textField.getText()));
	}
	
	
	private void infoDialog(String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
}
