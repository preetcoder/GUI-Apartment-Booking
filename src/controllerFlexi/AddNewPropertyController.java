/**
 * 
AddNewPropertyController.java
22 Sep. 2018
 */
package controllerFlexi;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import modalFlexi.importToDB;
import util.DB;

/**
 * @author Harpreet
 * 
 *         This class will control Add New property methods
 *
 */
public class AddNewPropertyController {

	@FXML
	private ComboBox<String> Propertytype, beds;

	@FXML
	private DatePicker lmd;

	@FXML
	private TextField ImageVal;

	@FXML
	private Button imageChooser;

	@FXML
	private Button canceladdproperty;

	@FXML
	private Button SaveProperty;

	@FXML
	private TextField streetno, streetname;

	@FXML
	private TextArea description;

	public static final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	@FXML
	public void initialize() {
		// get Property Types
		PropertytypeValues();
		BedsValues();
	}

	public void PropertytypeValues() {
		Propertytype.getItems().clear();
		Propertytype.getItems().addAll("Apartment", "Premium Suite");
		Propertytype.setValue("Apartment");
		// get Current Date for last Maintenance Date if Apartment
		DateTimeController getCurrentDate = new DateTimeController();
		lmd.setValue(LOCAL_DATE(getCurrentDate.getFormattedDate()));
		lmd.setDisable(true);

		Propertytype.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				// System.out.println();

				if (Propertytype.getValue() == "Apartment") {

					lmd.setValue(LOCAL_DATE(getCurrentDate.getFormattedDate()));
					lmd.setDisable(true);

					beds.setValue("1");
					beds.setDisable(false);

				} else {
					lmd.setValue(null);
					lmd.setDisable(false);
					lmd.getEditor().setEditable(false); // no user Input only click on calendar to access date

					beds.setValue("3"); // Set Default value 3 for Premium Suite
					beds.setDisable(true);
				}

			}
		});

	}

	// set Beds value items
	public void BedsValues() {
		beds.getItems().clear();
		beds.getItems().addAll("1", "2", "3");
		beds.setValue("1");

	}

	// File chooser
	@FXML
	private void ChooseImage(ActionEvent e) {
		FileChooser chooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
		chooser.getExtensionFilters().add(extFilter);

		chooser.setTitle("select Your Image");
		// Show save file dialog
		File file = chooser.showOpenDialog(new Stage());
		if (file != null) {
			ImageVal.setText(file.getName());
		}

	}

	// Close Stage
	@FXML
	public void closeButtonAction(InputEvent e) {
		final Node source = (Node) e.getSource();

		final Stage stage = (Stage) source.getScene().getWindow();

		MainController obj = new MainController();
		final Stage stage1 = (Stage) obj.getStageName();
		stage.setOnHidden(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {

				// stage.close();
				stage1.close();

				// load main controller

				Main mainview = new Main();
				mainview.start(new Stage());
			}
		});

		stage.close();

		// canceladdproperty.setOnAction(
		// event -> stage.fireEvent(new WindowEvent(stage,
		// WindowEvent.WINDOW_CLOSE_REQUEST)));

	}

	// Save Clicked
	@FXML
	public void SaveProperty(ActionEvent e) {
		// check values if empty
		boolean formdataEmpty = false;
		// validating user Input

		if (streetno.getText().isEmpty()) {
			AlertMessagesController obj = new AlertMessagesController("Street No. Can't be Empty", "failure :(");
			return;
		}

		if (streetname.getText().isEmpty()) {
			AlertMessagesController obj = new AlertMessagesController("Street Name Can't be Empty", "failure :(");
			return;
		}

		if (description.getText().isEmpty()) {
			AlertMessagesController obj = new AlertMessagesController("Description Can't be Empty", "failure :(");
			return;
		}
		if (lmd.getValue() == null) {
			AlertMessagesController obj = new AlertMessagesController(
					"Last Maintenace date Can't be Empty or must be in dd/mm/yyyy", "failure :(");
			return;
		}

		// make property ID
		String propID = "", propertyData = "", lmd_in_pattern = "";

		// get Date in dd/mm/yyyy

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate date = lmd.getValue();

		// get all data
		DisplayAllPropertiesController getdata = new DisplayAllPropertiesController();

		// check if property exists
		PropertyValidationsController exists = new PropertyValidationsController();

		
				
			if ((Propertytype.getValue()).charAt(0) == 'A') {
				propID = "A";
				propertyData = propID + "_" + streetno.getText() + "_" + streetname.getText() + "_mel:" + streetno.getText()
						+ ":" + streetname.getText() + ":mel:" + Propertytype.getValue() + ":" + beds.getValue()
						+ ":available:" + ImageVal.getText() + ":" + description.getText();

			} else {
				propID = "S";
				propertyData = propID + "_" + streetno.getText() + "_" + streetname.getText() + "_mel:" + streetno.getText()
						+ ":" + streetname.getText() + ":mel:" + Propertytype.getValue() + ":" + beds.getValue()
						+ ":available:" + formatter.format(date) + ":" + ImageVal.getText() + ":" + description.getText();

			}
			
			if (!exists.propertyExists(getdata.getAllPropertiesfromDB(),
					(propID + "_" + streetno.getText() + "_" + streetname.getText() + "_mel"))) {
				// System.out.println(Propertytype.getValue().charAt(0));

				// System.out.println(propertyData);

				String[] propertyDatainArray = propertyData.split(":");

				// Pass this array to Insert() method in modal importToDB
				importToDB obj = new importToDB();
				if (obj.insert(propertyDatainArray)) {
					AlertMessagesController obj1 = new AlertMessagesController("Property Added Successfully", "Success :)");

					// autoclose with reload

					MainController.autoclose(e);
				} else {
					AlertMessagesController obj1 = new AlertMessagesController("Unsuccessful Operation", "Failure :(");
				}
			}
			else {
				AlertMessagesController obj1 = new AlertMessagesController("Property Already Exists", "Failure :(");
			}


	}

}
