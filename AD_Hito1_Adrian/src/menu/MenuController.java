package menu;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {
	public void abrirTXT() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\texto\\Texto.fxml"));
	    Parent root1 = (Parent) fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setResizable(false);
	    stage.setTitle("TXT");
	    stage.setScene(new Scene(root1,980,480));  
	    stage.show();   
	}
	public void abrirJSON() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\json\\Json.fxml"));
	    Parent root1 = (Parent) fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setResizable(false);
	    stage.setTitle("JSON");
	    stage.setScene(new Scene(root1,980,480));
	    stage.show();   
	}
	public void abrirXML() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\xml\\Xml.fxml"));
	    Parent root1 = (Parent) fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setResizable(false);
	    stage.setTitle("XML");
	    stage.setScene(new Scene(root1,980,480)); 
	    stage.show();   
	}
}
