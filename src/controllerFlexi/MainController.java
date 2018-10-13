package controllerFlexi;
import javafx.fxml.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.Window;
import modalFlexi.*;
import util.DB;
import viewFlexi.PropertyFilterView;
import viewFlexi.SinglePropertyloop;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.net.URL;
import java.util.*;
//import javafx

public class MainController implements Initializable{
	


	@FXML
	private ListView myallprops;
	
	@FXML
	private  TilePane container;
	
	@FXML
	private Pane filters;
	
	@FXML
	private Button addNewProp;
	
	// Stage Name to access publically for reloading
	private static Window StageName ;
	

	
	 @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        try {
	        	
	        	
	        	//System.out.println();
	        	
	        	// creating connection by creating object
	        	DB db = new DB();
	        	
	        	// import from txt file
	        	
	        	importToDB txtImport = new importToDB();
	        	
	        	// All Properties
	        	displaypropertiesfromDB();
	        	
	        	// Filters
	        	displayFilters();
	        	
	        	// Add New Property
	        	
	        	

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	@FXML
	private void closeStage(ActionEvent e){
		CloseStageController closeStage = new CloseStageController();
		
		
	    
	}
	
	// handler for export
	@FXML
	private void exportProperties(ActionEvent e){
		exportController export = new exportController();
		export.exportAllProperties();
		
	}
	
	// handler for Add Property
	@FXML
	private void addProperties(ActionEvent e){
		final Node source = (Node) e.getSource();
		
		setStageName((source.getParent()).getScene().getWindow());
		
		// load addNewProperty fxml
		LoadNewPropertycontroller loadNewFxml = new LoadNewPropertycontroller();
	}
	
	@FXML
	private void filterproperties(ActionEvent e){
		
		PropertyFilterView filters_prop1 = new PropertyFilterView();
		// get filter values
		
		
		// filter method in DisplayAllPropertiesController
		
		DisplayAllPropertiesController allProp = new DisplayAllPropertiesController();
		// clearing container children and then reloading
		container.getChildren().clear();
		
//		if(allProp.displayAllPropViewWithFilter(filters_prop1.filtervalues()).size() == 0) {
//			container.getChildren().add(new Label("No Property Found"));
//		}
//		else {
			container.getChildren().addAll(allProp.displayAllPropViewWithFilter(filters_prop1.filtervalues()));
		//}
		
	}
	
	/**
	 * @return the stageName
	 */
	public static Window getStageName() {
		return StageName;
	}

	/**
	 * @param window the stageName to set
	 */
	public static void setStageName(Window window) {
		StageName = window;
	}

	@FXML
	
	public void displaypropertiesfromDB() {
		
		//calling DisplayAllPropertiesController.java
		DisplayAllPropertiesController allProp = new DisplayAllPropertiesController();
		ObservableList<Pane> singl = allProp.displayAllPropView();
		
		//making DOM children for main container
		container.getChildren().addAll(singl);
	}
	
	@FXML
	private void displayFilters() {
		//making filters from view
		PropertyFilterView filters_prop = new PropertyFilterView();
		filters.getChildren().addAll(filters_prop.filter1(),filters_prop.filter2());
	}
	
	@FXML
	private void addNewProperty() {
		//making filters from view
		
	}
	
	// for main view close
	public static void autoclose(ActionEvent e) {
		// autoclose
		final Node source = (Node) e.getSource();
		
		final Stage stage = (Stage) source.getScene().getWindow();
		
		MainController autoclose = new MainController();
		final Stage parent = (Stage) autoclose.getStageName();
		stage.close();
		
		parent.close();
		
		// Load Main view
    	
    	Main mainview = new Main();
    	mainview.start(new Stage());
	}
	
	// for main and single view close with other xml inside single property
	
	public static void autoclose(ActionEvent e, Stage st) {
		// autoclose
		final Node source = (Node) e.getSource();
		
		
		
		final Stage stage = (Stage) source.getScene().getWindow();
		
		MainController autoclose = new MainController();
		final Stage parent = (Stage) autoclose.getStageName();
		// property operation close
		stage.close();
		
		// single property close
		
		st.close();
		// Main View close
		parent.close();
		
		// Load Main view
    	
    	Main mainview = new Main();
    	mainview.start(new Stage());
		
		  
	}
	
}
