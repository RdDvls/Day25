package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args)throws SQLException {
        System.out.println("Welcome to the TIY TODO App");
        ToDoDatabase tdDatabase = new ToDoDatabase();
        tdDatabase.init();
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ToDo");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }



}
