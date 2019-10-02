package sample;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("uvod.fxml"));
        primaryStage.setTitle("React Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        final Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                int s = INDEFINITE;
                AudioClip audio = new AudioClip(getClass().getResource("songo.mp3").toExternalForm());
                audio.setVolume(1f);
                audio.setCycleCount(s);
                audio.play();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
