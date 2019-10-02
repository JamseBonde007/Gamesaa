package sample;
/*
    Doplňte funkcionalitu pre aplikáciu Reakčná doba

    Princíp hry

    Hráč po zadaní svojho mena spustí hru.
    Na otázku "Pripraveny ?" odpovedá stlačením ENTER
    Objaví sa hlásenie "Pozooor ..."
    a po náhodne dlhej dobe v intervale 0.5 - 3 sekundy sa objaví povel "START !!!"
    Cieľom hráča je v najrýchlejšom možnom čase opäť stlačiť ENTER.
    Program vypíše čas v milisekundách, ktorý uplynul od zobrazenia povelu START po stlačenie ENTER
    a zaradí ho do usporiadanej tabuľky výkonov (Meno hráča + výkon)
    Na obrazovku vypíše, kde sa daný výkon v tabuľke nachádza a to tak, že vypíše
    5 bezprostredne predchádzajúcich výkonov
    aktuálny výkon
    5 bezprostredne nasledujúcich výkonov
    To všetko v tvare Poradové číslo v tabuľke výkonov Tab6 Meno hráča Tab25 výkon
    Celú tabuľku s novým záznamom zapíše do textového súboru na disk, každý riadok v tvare MenoHraca:vykon

    Hra po spustení načíta zo súboru aktuálnu tabuľku výkonov a požiada hráča o prihlásenie (zadanie mena)
    Potom zobrazí MENU s položkami
    1 - Spusť hru
    2 - Zmena hráča
    3 - TOP 10
    4 - Koniec
    A reaguje podľa výberu

    Hru naprogramujte ako konzolovú aplikáciu aj ako aplikáciu s GUI. Využite pritom MVC.
    Pre meranie času využite funkciu System.currentTimeMillis();
    Hra musí ošetriť aj predčasné stlačenie pred zobrazením START ako chybu a potrestať ju (spôsob trestu je na vás)
*/



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;

import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

public class ReactBase implements Initializable {
    private int nastav ;
    private FXMLLoader loader;
    private Parent root;

    EventHandler<ActionEvent> startHandler = null;

    public void timer(int duration){

        btnStart.setText("CAKAJ");
            timeline = new Timeline(new KeyFrame(Duration.seconds(duration), ev -> {
                System.out.println("timer");
                    rootPane.getChildren().remove(btnStart);
                    rootPane.getChildren().add(btnStop);
                    btnStart.setStyle("-fx-background-color: lightgreen");
                    startTime = System.currentTimeMillis();
                    isStarted =0;
            }));
            timeline.setCycleCount(1);
            timeline.play();
    }
    private void ukazSkore(){
        Collections.sort(Menu.skore, new Comparator<Score>() {
            @Override
            public int compare(Score c1, Score c2) {
                return Double.compare(c1.getScore(), c2.getScore());
            }
        });
        najdiHraca();
        String s="";
        int p=1;
        for (int i=0;i<Menu.skore.size();i++){
            if(Menu.skore.get(i).getScore()>0){
                if(i>= nastav-5 && i<=nastav+5) {
                if(i==nastav){
                    s=s+"\n";
                }
                    s = s + p + "  \t" + Menu.skore.get(i).getMeno() + "  \t" + Menu.skore.get(i).getScore() + "\n";
                    if(i==nastav){
                        s=s+"\n";
                    }
                }
                p++;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(s);
        alert.showAndWait();

    }

    private void najdiHraca(){
        for (int i=0;i< Menu.skore.size();i++){
            if(Controller.hrac.equals(Menu.skore.get(i).getMeno())){
                nastav=i;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        najdiHraca();
        rootPane.getChildren().remove(btnStop);
        EventHandler<ActionEvent> startHandle  = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnStart.setText("START");
                rn = (int)(Math.random()*7)+3;

                if (isStarted == 0) {
                    timer(rn);
                    isStarted++;
                } else {
                    timeline.stop();
                    isStarted = 0;

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("STLACILI STE PRISKORO!");
                    alert.showAndWait();
                }
                System.out.println(isStarted);
            }
        };
        EventHandler<ActionEvent> stopHandle  = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootPane.getChildren().remove(btnStop);
                rootPane.getChildren().add(btnStart);
                btnStart.setStyle("-fx-background-color: lightblue");
                btnStart.setText("START");

                currTime = System.currentTimeMillis();
                double result = currTime-startTime;
                scoreLabel.setText(Double.toString(result));
                if (Menu.skore.get(nastav).getScore()==-1) {
                    Menu.skore.get(nastav).setScore(result);
                    ukazSkore();
                }
                if(result<Menu.skore.get(nastav).getScore()) {
                    Menu.skore.get(nastav).setScore(result);
                    ukazSkore();
                    ulozTo();

                }


                }
        };

        btnStart.setOnAction(startHandle);
        btnStop.setOnAction(stopHandle);
    }
    @FXML
    private Button btnStart,btnStop;
    @FXML
    private Label scoreLabel;
    @FXML
    private AnchorPane rootPane;

    private int rn;
    private double currTime, startTime;
    private int isStarted = 0;
    private Timeline timeline;
    final int CM_PLAY = 1;
    final int CM_CHANGE_PLAYER = 2;
    final int CM_TOP10 = 3;
    final int CM_QUIT = 4;
    String Player;
    Scanner sc = new Scanner(System.in);
    ArrayList<Record> skore = new ArrayList<>();

    public static void main(String[] args) {

        boolean gameOn;
        ReactBase Game = new ReactBase();
        do
            gameOn =  Game.Run();
        while (gameOn);
    }



    public boolean Run(){
        /*switch(Menu()){
            case CM_CHANGE_PLAYER:
                NewPlayer();
                return true;
            case CM_PLAY:
                int LastTime = Play(Player);
                //Sort(Player, LastTime);
                ShowRecords(Player, LastTime);
                SaveRecords();
                return true;
            case CM_TOP10:
                ShowRecords("", 0);
                return true;
            case CM_QUIT:
                return false;
        }
        return true;
        */

        return true;
    }

    public void ImportRecords(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\skola\\opro\\reakčna doba\\src\\sample\\Tabulka.txt"));
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
               // skore.add(new Record(line.substring(0,line.indexOf("|")),Double.parseDouble(line.substring(line.indexOf("|")+1))));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NewPlayer(){
        System.out.println("Zadajte meno hráča!");
        //Player = sc.nextLine();
    }

    public int Menu(){
        return CM_QUIT;
    }

    public int Play(String who){


        return Integer.MAX_VALUE;
    }

    public void Sort(String who, int record){

    }

    public void ShowRecords(String who, int record){
        if (record>0) {
            for (int i = 0; i < 10; i++) {
                System.out.println("1. " + skore.get(i));
            }
        }else {
            for (int i = 0; i < skore.size(); i++) {
                System.out.println("1. " + skore.get(i));
            }
        }
    }

    public void SaveRecords(){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("Tabulka.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ulozTo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\skola\\opro\\reakčna doba\\src\\sample\\Tabulka.txt"));
            for (int i=0;i<Menu.skore.size();i++) {
                bw.write(Menu.skore.get(i).toString());
                bw.newLine();
            }


            bw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMenu() {
        ulozTo();
        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource("sample/menu.fxml"));
            Stage stage = (Stage) scoreLabel.getScene().getWindow();
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
