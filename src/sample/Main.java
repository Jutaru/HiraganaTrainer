package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HirganaTrainer_template.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setTitle("Hiragana Trainer");
        try{
        Image icon = new Image("file:../../logo.png");
        primaryStage.getIcons().add(icon);
        } catch (Exception e){
            System.out.println("Path not found");
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
