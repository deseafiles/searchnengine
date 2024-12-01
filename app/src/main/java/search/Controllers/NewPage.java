package search.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewPage {

    @FXML
    private Button backButton; // Mengganti nama tombol untuk kembali ke halaman sebelumnya

    @FXML
    private void initialize() {
        backButton.setOnAction(event -> navigateBack()); // Menambahkan aksi untuk tombol kembali
    }

    private void navigateBack() {
        try {
            // Pastikan Anda memiliki halaman yang sebelumnya (misalnya /View/PreviousPage.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SearchView.fxml"));
            Scene previousScene = new Scene(loader.load());

            Stage currentStage = (Stage) backButton.getScene().getWindow(); // Mengambil window dari backButton
            currentStage.setScene(previousScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
