package search.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class LandingPageController {

    @FXML
    private Button getStart;

    @FXML
    private void initialize() {
        getStart.setOnAction(event -> handleGetStartAction());
    }

    @FXML
    private void handleGetStartAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SearchView.fxml"));
            Scene searchScene = new Scene(loader.load());

            Stage stage = (Stage) getStart.getScene().getWindow();

            stage.setScene(searchScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
