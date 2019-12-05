import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/***********************************************************
 * Final Project - Fibonacci Sequence Calculator            *
 * -------------------------------------------------------- *
 * This program calculates the Fibonacci sequence leading   *
 * up to a number entered by the user. It also determines   *
 * whether or not the inputted number itself is a Fibonacci *
 * number. The sequence is saved as an array, and both the  *
 * sequence and the user input's Fibonacci status are       *
 * printed to the terminal.                                 *
 * -------------------------------------------------------- *
 * Group 11                                                 *
 * Roy Chung, Dominik Flisiak, Maxwell Fortney              *
 * 20191117                                                 *
 * CMSC 255 Section 1                                       *
 ***********************************************************/

public class Fibonacci extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //Import image from file
        FileInputStream inputstream = new FileInputStream("src/header.png");
        Image image = new Image(inputstream);
        //Create a javafx ImageView from the image
        ImageView imageView = new ImageView(image);
        //Create the javafx controls we need
        Label label1 =  new Label("Please enter a positive integer:");
        //Set the label to wrap if the window is resized too small
        label1.setWrapText(true);
        //Create calculate button
        Button btCalc = new Button("Calculate");
        //Create input field
        TextField maxNumberInput = new TextField();
        //Create output field
        TextArea outputArea = new TextArea();
        // Make the output box uneditable
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        //Create VBox pane (single vertical cell)
        VBox topPane = new VBox();
        //Set padding of VBox pane
        topPane.setAlignment(Pos.CENTER);
        //Add attributes to VBox in correct order and settings
        topPane.getChildren().addAll(imageView,label1,maxNumberInput, btCalc, outputArea);
        topPane.setSpacing(10);
        topPane.setPadding(new Insets(10));
        //Rectangle in order to get screen dimensions for later settings the starting position, and the modal position
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        //btCalc event handler
        btCalc.setOnAction(e -> {
            try{
                //Get user input
                Long value = Long.valueOf(maxNumberInput.getText());
                //Pass user input to FibSequence
                FibSequence fibSequence = new FibSequence(value);
                //Pass the FibSequence object to our output box
                outputArea.setText(fibSequence.toString());
                //Create new javafx stage
                final Stage userNewCalcChoice = new Stage();
                //New stage's parent
                userNewCalcChoice.initOwner(primaryStage);
                //VBox and settings
                VBox dialogVbox = new VBox(20);
                dialogVbox.setAlignment(Pos.CENTER);
                //Button HBox and settings
                HBox modalBtnRow = new HBox();
                modalBtnRow.setAlignment(Pos.CENTER);
                modalBtnRow.setSpacing(10);
                //Create the 2 buttons
                Button yesAnother = new Button("Yes");
                Button noAnother = new Button("No");
                //Add buttons to HBox
                modalBtnRow.getChildren().addAll(yesAnother, noAnother);
                //Label
                Label modalLabel = new Label("Would you like to enter another number?");
                //Add label and butons to VBox
                dialogVbox.getChildren().addAll(modalLabel,modalBtnRow);
                //Add VBox to stage
                Scene dialogScene = new Scene(dialogVbox, 500, 100);
                //Set scene for stage
                userNewCalcChoice.setScene(dialogScene);
                //Set title
                userNewCalcChoice.setTitle("Continue?");
                userNewCalcChoice.initStyle(StageStyle.UNDECORATED);
                //Set x and y of dialog box, offset because you have to account for the window size
                userNewCalcChoice.setX(primaryStage.getX());
                userNewCalcChoice.setY(primaryStage.getY()+500);
                userNewCalcChoice.show();
                //Yes button event Handler
                yesAnother.setOnAction(e2 -> {
                    userNewCalcChoice.close();
                    maxNumberInput.clear();
                    outputArea.setText("");
                });
                //No button event Handler
                noAnother.setOnAction(e2 -> {
                    userNewCalcChoice.close();
                    primaryStage.close();
                });
            } catch(NumberFormatException ex){
                outputArea.setText("Invalid input. Please enter a whole number up to 9.22E18!");
            }
        });

        //Make a new scene with our pane, as well as the size (default resizable=true)
        Scene scene = new Scene(topPane, 500, 500);
        //Set the stage title
        primaryStage.setTitle("Fibonacci Sequence Calculator");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // place scene in stage
        primaryStage.setScene(scene);
        primaryStage.setX((screenBounds.getWidth()/2)-250);
        primaryStage.setY((screenBounds.getHeight()/2)-250);
        // show the stage
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}