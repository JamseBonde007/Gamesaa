package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    static ArrayList<Score> skore = new ArrayList<Score>();
    @FXML
    private Label menoH;
    private FXMLLoader loader;
    private Parent root;


    private void nacitajUdaje(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\skola\\opro\\reakčna doba\\src\\sample\\Tabulka.txt"));
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);

                    skore.add(new Score(line.substring(0,line.indexOf("|")),Double.parseDouble(line.substring(line.indexOf("|")+1))));
                // skore.add(new Record(line.substring(0,line.indexOf("|")),Double.parseDouble(line.substring(line.indexOf("|")+1))));
            }

            Collections.sort(skore, new Comparator<Score>() {
                @Override
                public int compare(Score c1, Score c2) {
                    return Double.compare(c1.getScore(), c2.getScore());
                }
            });




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menoH.setText(Controller.hrac);
        skore.clear();
        nacitajUdaje();

    }

    @FXML
    private void play(){
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/sample.fxml"));
            Stage stage = (Stage) menoH.getScene().getWindow();
            root = loader.load();

            stage.setTitle("ReactGame");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    private void change(){
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/uvod.fxml"));
            Stage stage = (Stage) menoH.getScene().getWindow();
            root = loader.load();

            stage.setTitle("ReactGame");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void top(){
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/top.fxml"));
            Stage stage = (Stage) menoH.getScene().getWindow();
            root = loader.load();

            stage.setTitle("ReactGame");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void konec(){
        System.exit(0);

    }
}
