//package Game;
//
//import javafx.event.ActionEvent;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuItem;
//
//import java.util.Collection;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuItem;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//
//public class Menu<gameType> {
//    public final String[] gameType = {"Easy", "Medium", "Hard", "Very Hard"};
//    private String difficulty;
//    public Menu gameMenu = new Menu();
//
//    public void menu() {
//        for(String game : gameType){
//            MenuItem menuItem = new MenuItem(game);
//            menuItem.setUserData(game);
//            menuItem.setOnAction((ActionEvent event) -> {
//                selectGame(event);
//            });
//            gameMenu.
//        }
//    }
//
//    private void selectGame(ActionEvent event) {
//        MenuItem menuItem = (MenuItem)event.getSource();
//        difficulty = (String)menuItem.getUserData();
//        switch (difficulty) {
//            case "Easy":
//                TILE_SIZE = 200;
//                break;
//            case "Medium":
//                TILE_SIZE = 100;
//                break;
//            case "Hard":
//                TILE_SIZE = 50;
//                break;
//            case "Very Hard":
//                TILE_SIZE = 40;
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    for(String game : gameType){
//        MenuItem menuItem = new MenuItem(game);
//        menuItem.setUserData(game);
//        menuItem.setOnAction((ActionEvent event) -> {
//            selectGame(event);
//        });
//        gameMenu.getItems().add(menuItem);
//    }
//    MenuBar menuBar = new MenuBar(gameMenu);
//}
