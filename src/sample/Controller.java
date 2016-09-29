package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static sample.ToDoDatabase.DB_URL;

public class Controller implements Initializable {
    @FXML
    ListView todoList;
    @FXML
    TextField todoText;

    String fileName = "todos.json";
    ObservableList<ToDoItem> todoItems = FXCollections.observableArrayList();
    ToDoDatabase todoDatabase = new ToDoDatabase();
    ArrayList<ToDoItem> listOfToDoItems;
    String userName;
    String fullName;
    int userID;
    Connection conn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(todoDatabase == null){
                todoDatabase = new ToDoDatabase();
                todoDatabase.init();
            }
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Select an option:\n");
            System.out.println("1: Create New User");
            System.out.println("2: Sign-in");
            Scanner inputScanner = new Scanner(System.in);
            int menuChoice = Integer.valueOf(inputScanner.nextLine());
            if (menuChoice == 1) {
                System.out.println("Enter your email address\n");
                System.out.println("This will serve as your username");
                String tempUsername = inputScanner.nextLine();
                System.out.println("Enter your full name\n");
                String tempFullName = inputScanner.nextLine();
                System.out.println(DB_URL);
                userID = todoDatabase.insertUser(conn, tempUsername, tempFullName);
            }else if(menuChoice == 2){
                System.out.println("Enter username");
                String tempUserName = inputScanner.nextLine();
                userID =todoDatabase.selectUser(conn,tempUserName);
                listOfToDoItems = todoDatabase.selectToDosForUser(conn,userID);
                for(ToDoItem blah : listOfToDoItems){
                    todoItems.add(blah);
                }

            }

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        todoList.setItems(todoItems);
    }

    public void addItem() throws SQLException {
        System.out.println("Adding item ...");
        todoItems.add(new ToDoItem(todoText.getText()));
        todoDatabase.insertToDo(conn,todoText.getText(),userID);
        todoList.setItems(todoItems);
        todoText.setText("");
    }

}
