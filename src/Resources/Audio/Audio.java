package Resources.Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Audio {
    Media bg_audio;
    MediaPlayer bg_player;
    String path;
    public Audio(String path) {
        this.path = path;
        //Instantiating Media class
        bg_audio = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        bg_player = new MediaPlayer(bg_audio);
    }
    public void playCycle(int cycleLength){
        bg_player.setAutoPlay(true);
//        mediaPlayer.autoPlayProperty();
        bg_player.setCycleCount(MediaPlayer.INDEFINITE);
        bg_player.setStartTime(Duration.seconds(0));
        bg_player.setStopTime(Duration.seconds(cycleLength));
    }

    public void play(){
        bg_player.play();
    }

    public void stop() {
        bg_player.stop();
    }
}
