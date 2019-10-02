package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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

public class Top implements Initializable {

    @FXML
    private Label topM;
    @FXML
    private Label topS;
    @FXML
    private Label topP;
    private String M="";
    private String S="";
    private String P="";
    private FXMLLoader loader;
    private Parent root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    if(Menu.skore.get(Menu.skore.size()-1).getScore()!=-1) {
        int p = 0;
        if (Menu.skore.size() < 10) {
            p = Menu.skore.size();
        } else
            p = 10;

        int c = 1;
        for (int i = 0; i < p; i++) {
            if (Menu.skore.get(i).getScore() > 0) {
                P = P + (c) + "\n";
                S = S + Menu.skore.get(i).getScore() + "\n";
                M = M + Menu.skore.get(i).getMeno() + "\n";
                c++;
            } else p++;

        }

        topM.setText(M);
        topP.setText(P);
        topS.setText(S);
    }

    }

    @FXML
    private void openMenu() {
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/menu.fxml"));
            Stage stage = (Stage) topP.getScene().getWindow();
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
