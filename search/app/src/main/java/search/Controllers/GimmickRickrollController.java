package search.Controllers;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class GimmickRickrollController {

    @FXML
    private MediaView mediaView;

    public void initialize() {
        String videoPath = getClass().getResource("/Rickroll.mp4").toExternalForm();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.play();
    }
}