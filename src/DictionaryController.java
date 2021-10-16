import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class DictionaryController implements Initializable {
    public static List<String> wordArray = new ArrayList<>();

    private String[] str = Dictionary.WordTargets.toArray(new String[10]);

    @FXML
    private TextField searchBar;

    @FXML
    public ListView<String> myListView;

    @FXML
    private TextField ExplainField;

    @FXML
    private void displaySelected(MouseEvent event) {
        searchBar.setText(myListView.getSelectionModel().getSelectedItem().toString());
        ExplainField.setText(DictionaryManagement.dictionaryLookup(myListView.getSelectionModel().getSelectedItem().toString()));
    }

    @FXML
    void lookup(ActionEvent event) throws IOException {
        String wordLookup = searchBar.getText();
        ExplainField.setText(DictionaryManagement.dictionaryLookup(wordLookup));
    }

    @FXML
    public void search() {
        String wordSearch = searchBar.getText().toString();
        List<String> s = dictionarySearch(wordSearch);
        ObservableList<String> input = FXCollections.observableArrayList(s);
        myListView.setItems(input);
        wordArray.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> wordsFromFile = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("dictionaries.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                wordsFromFile.add(line);
            }
            reader.close();

            for (int i = 0; i < wordsFromFile.size(); i++) {
                String[] str = wordsFromFile.get(i).split("\t");
                Word result = new Word(str[0].trim(), str[1].trim());
                Dictionary.WordTargets.add(i, str[0]);
                Dictionary.WordExplains.add(i, str[1]);
                Dictionary.WordList.add(result);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        populateData();
    }

    private void populateData() {
        myListView.getItems().addAll(Dictionary.WordTargets);
    }

    public static List<String> dictionarySearch(String wordSearch) {
        for (int i = 0; i < Dictionary.WordList.size(); i++) {
            if (Dictionary.WordList.get(i).getWord_target().startsWith(wordSearch)) {
                wordArray.add(Dictionary.WordList.get(i).getWord_target());
            }
        }
        Collections.sort(wordArray);
        return wordArray;
    }

    Stage stage;
    Scene scene;

    @FXML
    Button addButton; //nút chuyển sang cảnh thêm từ.
    @FXML
    Button deleteButton;  //nút chuyển sang cảnh xóa từ.

    @FXML
    private void switchToSceneAdd(ActionEvent event) {
        try {
            if (event.getSource() == addButton) {
                Parent root = FXMLLoader.load(getClass().getResource("fxmlScene/SceneAdd.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(addButton.getScene().getWindow());
                stage.showAndWait();
            }
        } catch (IOException ex) {
            System.out.println("Can't open");
        }
    }

    @FXML
    void switchToSceneDelete(ActionEvent event) {
        try {
            if (event.getSource() == deleteButton) {
                Parent root = FXMLLoader.load(getClass().getResource("fxmlScene/SceneDelete.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.showAndWait();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(deleteButton.getScene().getWindow());
            }
        } catch (IOException ex) {
            System.out.println("Can't open");
        }
    }
}

