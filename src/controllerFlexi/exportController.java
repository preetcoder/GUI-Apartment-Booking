/**
 * 
exportController.java
17 Sep. 2018
 */
package controllerFlexi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.javafx.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modalFlexi.apartment;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 *         This class is exporting properties from DB to your file
 */
public class exportController {

	DisplayAllPropertiesController getAllProperties = new DisplayAllPropertiesController();

	public void exportAllProperties() {
		// et all properties from DB
		String exportString = "";
		ObservableList<property> allProperties = getAllProperties.getAllPropertiesfromDB();

		FileChooser chooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		chooser.getExtensionFilters().add(extFilter);

		chooser.setTitle("Save File");
		// Show save file dialog
		File file = chooser.showSaveDialog(new Stage());

		for (int i = 0; i < allProperties.size(); i++) {
			
			exportString += allProperties.get(i).toString();
		}

		if (file != null) {
			SaveFile(exportString, file);
			// show alert
			AlertMessagesController obj = new AlertMessagesController("All Properties exported Successfully!!",
					"Export Success!!");

		}

	}

	private void SaveFile(String content, File file) {
		try {
			FileWriter fileWriter = null;

			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
