
//@Author: Priyank Vora , 000922930

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * The class Ticket scanning application extends application is the main class.
 * this class contains all the important component for GUI
 * and includes the JAVA code for running the Ticket Scanning App
 */
public class TicketScanningApp extends Application {

    private TextField codeInputBox;

    private Label title;
    private Label outputLabel;
    private Table table;

    @Override

/**
 *
 * Start class contains all working components of the GUI.
 * it includes InputBox,Labels,Buttons and Title.
 * It creates the Object that calls Table Class.
 *
 * @param stage  the stage represent the Canvas outline and the title of it.
 * @throws   Exception
 */
    public void start(Stage stage) throws Exception {

        // Create the table object to manage ticket data
        table = new Table("codes.txt");

        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 275);
        stage.setTitle("Ticket Gate App");
        stage.setScene(scene);


        // Code Starts From Here

        //Create title of the App

        title = new Label("Ticket Scanner App");
        title.relocate(40,5);
        title.setFont(new Font("Arial", 30));

        // Create barcode input field
        codeInputBox = new TextField();
        codeInputBox.setPromptText("Enter Barcode Number");
        codeInputBox.relocate(40,60);
        codeInputBox.setPrefWidth(400);
        codeInputBox.setPrefHeight(32);


        // Create Scan button
        Button scanButton = new Button("Scan");
        scanButton.setFont(new Font("System" , 20));

        scanButton.relocate(40,110);
        scanButton.setPrefWidth(80);
        scanButton.setPrefHeight(40);
        scanButton.setOnAction(this::handleScanButton);

        // Create Reset button

        Button resetButton = new Button("Reset");
        resetButton.setFont(new Font("System" , 20));
        resetButton.relocate(180,110);
        resetButton.setPrefWidth(80);
        resetButton.setPrefHeight(40);
        resetButton.setOnAction(this::resetBtn);

        // Create Output label

        outputLabel = new Label("Ready to scan.");
        outputLabel.relocate(40,180);
        outputLabel.setFont(new Font("Arial", 22));


        // Add components to the root
        root.getChildren().addAll(codeInputBox, title, scanButton, resetButton, outputLabel);

        //Code Ends Here

        stage.show();
    }

    // Event handler for scan button


    /**
     *
     * Handle scan button is the class that put the Scan Button on Action with the helps of ActionEvent.
     * It contains all the Output String Value for the Label for Output. it also has multiple Color String
       for different Output accordingly.
     * This class has nested if statements with the multiple public class from the Table class
     *
     * @param event  the event that handles the ScanButton's Action.And got activated when the Scan Button Was Clicked.
     *               it calls different public class from the table class accordingly
     */
    private void handleScanButton(ActionEvent event) {

        // Get the ticket code from the InputBox
        String barcode = codeInputBox.getText().trim();

        // Check if the purchased ticket is valid or not
        if(table.purchaseTicket(barcode)) {
            if (table.validateTickets(barcode)) {
                // If valid, check if it's already entered
                if (!table.validateTicketEntries(barcode)) {
                    outputLabel.setText(barcode + " is Valid! Access granted.\nFan Expo Canada Pass  ");
                    outputLabel.setTextFill(Color.GREEN);
                    // Mark the ticket as entered
                    table.ticketentry(barcode);
                } else {
                    outputLabel.setText(barcode + " is Duplicate! This ticket has already\n been scanned.");
                    outputLabel.setTextFill(Color.RED);
                }
            } else {
                outputLabel.setText(barcode + " is Invalid! Please try again.");
            }
        }
        else {
            outputLabel.setText(barcode + " has not purchased yet :(");
            outputLabel.setTextFill(Color.RED);
        }

        // Clear input after processing
        codeInputBox.clear();
    }

    // Event handler for reset button

    /**
     *
     * Reset btn puts the Reset Button on the Action Through ActionEvent.
     * it just has one Output String for the Output Label and gives the Color Blue to the output Label.
     * This class has called A public class from the table class to reset the value of all tickets.
     *
     * @param event  the event that activated when the Reset Button has clicked.
     *               it calls the resetTicket class from the Table class
     */
    private void resetBtn(ActionEvent event) {

        // Reset the value of all ticket to N
        table.resetTicket();
        outputLabel.setText("All tickets reset.");
        outputLabel.setTextFill(Color.BLUE);
    }


    /**
     *
     * Main
     * @Author: Priyank Vora , 000922930
     *
     * @param args  the args.
     */
    public static void main(String[] args) {

        launch(args);
    }
}
