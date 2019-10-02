package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button nazatB;
    @FXML
    private Label err;
    static String hrac="";
    @FXML
    private Button button;
    @FXML
    private TextField meno;
    private FXMLLoader loader;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nazatB.setVisible(false);
        if (hrac.length()>0){
            nazatB.setVisible(true);

        }
    }

    @FXML
    public void NewPlayer() {
        if (meno.getLength() > 3 && meno.getLength()<9) {
            setPlayer();
            System.out.println("ta so");
            openMenu();

        }
        else err.setVisible(true);
    }


    private void setPlayer() {
        boolean urci = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\skola\\opro\\reakčna doba\\src\\sample\\Tabulka.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(meno.getText())) {
                    urci = true;
                }


            }
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hrac = meno.getText();
        if (urci == false) {

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\skola\\opro\\reakčna doba\\src\\sample\\Tabulka.txt", true));

                bw.write(meno.getText() + "|-1");
                bw.newLine();


                bw.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void openMenu() {
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/menu.fxml"));
            Stage stage = (Stage) meno.getScene().getWindow();
            root = loader.load();

            stage.setTitle("ReactGame");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
