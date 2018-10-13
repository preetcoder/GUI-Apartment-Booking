/**
 * 
SinglPropertyloop.java
16 Sep. 2018
 */
package viewFlexi;

import java.io.File;

import controllerFlexi.*;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import modalFlexi.property;

/**
 * @author Harpreet
 *
 *	This class is creating multiple properties from observerlist which is coming from controller observerList
 */
public class SinglePropertyloop {
	
	private ObservableList<Pane> allPanes = FXCollections.observableArrayList();
	
	public ObservableList<Pane> propertyloop(ObservableList<property> allProperty){
	
		
		for (int i = (allProperty.size() - 1); i >=0; i--) {

			Pane singlePane = new Pane();
			ImageView imageView = new ImageView();
			
			File file;

			singlePane.setPrefHeight(225.0);
			singlePane.setPrefWidth(281.0);
			
			
			// image src
			if("".equals(allProperty.get(i).getImage())) {
				 
				 file = new File("src/img/notfound.png");
			}
			else {
				file = new File("src/img/" + allProperty.get(i).getImage());
			}
			
			//System.out.println(file.toURI().toString());
			Image image = new Image(file.toURI().toString());
			imageView.setImage(image);
			imageView.setFitHeight(119.0);
			imageView.setFitWidth(280.0);
			
			// New Text
			Text newText = new Text();
			newText.setLayoutX(240.0);
			newText.setLayoutY(22.0);
			newText.setText("New");
			newText.setStyle("-fx-font-weight: bold;");
			
			
			FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), newText);
	        fadeTransition.setFromValue(1.0);
	        fadeTransition.setToValue(0.0);
	        fadeTransition.setCycleCount(Animation.INDEFINITE);
	        fadeTransition.play();
	        
			// property Street Name
			Text propertyname = new Text();
			propertyname.setLayoutX(10.0);
			propertyname.setLayoutY(150.0);
			propertyname.setText(allProperty.get(i).getStreet_name());
			propertyname.setFont(Font.font(20.0));
			propertyname.setStyle("-fx-font-weight: bold;");

			// Property Complete Address
			Text propertyaddress = new Text();
			propertyaddress.setLayoutX(10.0);
			propertyaddress.setLayoutY(168.0);
			propertyaddress.setText(allProperty.get(i).getStreet_no() + " " + allProperty.get(i).getStreet_name() + " "
					+ allProperty.get(i).getSuburb());
			
			// property Description
			Text propertydescription = new Text();
			propertydescription.setLayoutX(12.0);
			propertydescription.setLayoutY(187.0);
			propertydescription.setText(allProperty.get(i).getDescription());
			propertydescription.setWrappingWidth(278.29296875);
			
			// Book button
			Button book = new Button();
			book.setLayoutX(198.0);
			book.setLayoutY(129.0);
			book.setPrefHeight(19.0);
			//book.setStyle("btn");
			book.setText("Book");
			book.setId(allProperty.get(i).getProperty_id());
			
			// making a variable to pass in handler
			 property propertyDetails = allProperty.get(i);
			
			book.setOnAction(new EventHandler<ActionEvent>() {
				
				  public void handle(ActionEvent actionEvent) {
					  
					  final Node source = (Node) actionEvent.getSource();
					  
						
					  MainController.setStageName((source.getParent()).getScene().getWindow());
					 
	                    try {
	                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SinglePropertyDetails.fxml"));
	                        Parent root1 = (Parent) fxmlLoader.load();
	                        
	                        SinglePropertyController singleProperty = new SinglePropertyController();
	                        singleProperty = fxmlLoader.getController();
	                        
	                        //System.out.println(singleProperty);
	                        singleProperty.getpropertyDetails(propertyDetails);
	        
	                        
	                        Stage stage = new Stage();
	              	      
	            	        stage.setTitle("Property Operations");
	            	        stage.setScene(new Scene(root1));  
	            	        //stage.show();
	            	        stage.showAndWait();

	                    } catch(Exception e) {
	                        e.printStackTrace();
	                    }
					  
					  
				  }
			});
			if(i== (allProperty.size() -1)) {
				singlePane.getChildren().addAll(imageView,newText, propertyname, propertyaddress,propertydescription,  book);
			}
			else {
				singlePane.getChildren().addAll(imageView, propertyname, propertyaddress,propertydescription,  book);
				
			}
			allPanes.add(singlePane);
		}

		return allPanes;
	}
	
	
}
