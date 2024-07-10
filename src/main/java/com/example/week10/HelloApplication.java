package com.example.week10;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Nodes
        Text txtNote = new Text("Notification");
        txtNote.setFont(Font.font("Comic Sans MS", 25));
        txtNote.setFill(Color.GREEN);

        TextField fldAdd = new TextField();
        fldAdd.setPromptText("Add Field");

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(85);
        Button btnDelete = new Button("Delete");
        btnDelete.setMinWidth(85);

        // ListView and Observable List
        ListView<String> listList = new ListView<>();
        ObservableList<String> colors = FXCollections.observableArrayList();
        listList.setItems(colors);
        listList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listList.setMaxSize(300, 300);

        // ObservableList ChangeListener
        colors.addListener(new ListChangeListener<>() {

            @Override
            public void onChanged(ListChangeListener.Change change) {
                txtNote.setText("ListView Successfully changed");
            }
        });

        // Button Add
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                String input = fldAdd.getText();

                if (input.isEmpty()) {
                    txtNote.setText("Name cannot be empty");
                } else if (!Character.isUpperCase(input.charAt(0))) {
                    txtNote.setText("Name must start with an uppercase letter");
                } else if (input.length() < 5) {
                    txtNote.setText("Name must be at least 5 characters long");
                } else if (input.contains(",")) {
                    txtNote.setText("Please use AddAll button to add 2+ items");
                } else {
                    colors.add(input);
                    txtNote.setText("Name added successfully");
                }

                listList.getSelectionModel().clearSelection();
            }
        });

        // Button Delete
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if (!listList.getSelectionModel().isEmpty()) {
                    colors.remove(listList.getSelectionModel().getSelectedItem());
                } else {
                    txtNote.setText("Select at least 1 Element on the ListView");
                }
                listList.getSelectionModel().clearSelection();
            }
        });

        // Pane Left Right
        VBox right = new VBox(10);
        right.setPadding(new Insets(10));
        right.setAlignment(Pos.CENTER);
        right.getChildren().addAll(fldAdd, btnAdd, btnDelete);

        // Root Node
        BorderPane root = new BorderPane();
        root.setCenter(listList);
        root.setRight(right);
        root.setBottom(txtNote);

        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("JavaFX 8 - FXCollections");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
