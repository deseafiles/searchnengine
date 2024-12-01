package search.Controllers;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import search.Utils.GameGimmick;
import search.Utils.JsonParsing;
import search.Utils.PrintJson;
import search.Models.RbTree; 

public class SearchView {

    @FXML
    private Button handleSearch;

    @FXML
    private Button clearButton;

    @FXML
    private MediaView mediaView;

    @FXML
    private ListView<String> resultList; 

    @FXML
    private TextField searchBox;

    @FXML
    private ListView<String> suggestionList;  

    @FXML
    private TextArea jsonDetailsArea; 

    private RbTree rbTree = new RbTree(); 

    public SearchView() {
        rbTree.insert("dice", null, new GameGimmick());
        rbTree.insert("coin", null, new GameGimmick());
        rbTree.insert("close", null, new GameGimmick());
        JsonParsing.loadJsonData(rbTree, "/data.json");
    }

    @FXML
    private void initialize() {
        handleSearch.setOnAction(event -> handleSearchAction());
        clearButton.setOnAction(event -> handleClearAction());
        resultList.setOnMouseClicked(event -> handleItemClick());
    }

    private void handleSearchAction() {
        String searchParam = searchBox.getText().trim(); 
    
        if (!searchParam.isEmpty()) {
            List<String> results = PrintJson.printRedBlackTreeResults(rbTree, searchParam);
            
            if (results == null || results.isEmpty()) {
                System.out.println("No results found.");
                resultList.getItems().clear();
                resultList.getItems().add("No matching results found.");
            } else {
                resultList.getItems().clear();
    
                for (String result : results) {
                    if (!result.contains("null") && !result.trim().isEmpty()) {
                        resultList.getItems().add(result);
                    }
                }
            }
        }
    }

    private void handleItemClick() {
        String selectedItem = resultList.getSelectionModel().getSelectedItem();

        if (selectedItem != null && !selectedItem.isEmpty()) {
            System.out.println("Item clicked: " + selectedItem);
            
            navigateToNewPage();
        }
    }

    private void navigateToNewPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewPage.fxml"));
            Scene newScene = new Scene(loader.load());

            Stage stage = (Stage) handleSearch.getScene().getWindow();

            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClearAction() {
        searchBox.clear();
        resultList.getItems().clear();
        jsonDetailsArea.clear();
    }    
}
