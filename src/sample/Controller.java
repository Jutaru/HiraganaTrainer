package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.HashMap;
import java.util.*;
import java.io.*;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller {


    int Sccore_number;
    Timeline timer;
    Map<String, String> hirgana = new HashMap<String, String>();
    @FXML
    Label sccore;
    @FXML
    ListView lettersList;
    Button starter;
    @FXML
    Label charHint;
    @FXML
    TextField play_ground;

    @FXML
    Button stopper;

    public Controller(){
        File file = new File("resources/hirgana.txt");
        Scanner scanner = null;
        String hKey;
        String hValue;
        String[] Line;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                Line = scanner.nextLine().split(" ");
                hirgana.put(Line[0], Line[1]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    @FXML
    public void initialize(){
        Sccore_number = 0;
        sccore.setText(String.valueOf(Sccore_number));
        charHint.setVisible(false);
    }
    public void showHint(){
        charHint.setVisible(true);
    }

    public void Add1(ActionEvent e){
        Random r = new Random();
        List<String> Keys = (hirgana.keySet()).stream().toList();
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), ev -> {
                    lettersList.getItems().add(Keys.get(r.nextInt(hirgana.size())));
                    charHint.setText(hirgana.get(lettersList.getItems().get(0).toString()));
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        if(starter == null) {
            starter = ((Button) e.getSource());
        }
        starter.setDisable(true);
        stopper.setDisable(false);

    }

    public void stop_timer(ActionEvent event){
        timer.stop();
        starter.setDisable(false);
        stopper.setDisable(true);

    }

    public void KeyPressed(KeyEvent event){
        if(
                lettersList.getItems().isEmpty() == false
                && (hirgana.get( lettersList.getItems().get(0)) ).equals(play_ground.getText())
        ){
            lettersList.getItems().remove(0);
            charHint.setVisible(false);
            play_ground.setText("");
            Sccore_number += 10;
            sccore.setText(String.valueOf(Sccore_number));

        }else if(event.getCode() == KeyCode.SHIFT){
            showHint();
        }
    }


}
