import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class DictionaryController implements Initializable {


    private String[] str = Dictionary.WordTargets.toArray(new String[10]);

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> myListView;

    @FXML
    void search(ActionEvent event) throws IOException {
        myListView.getItems().clear();
        myListView.getItems().addAll(searchList(searchBar.getText(), Dictionary.WordTargets));
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


    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    Stage stage;
    Scene scene;

    @FXML
    void switchToSceneAdd(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SceneAdd.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Can't open");
        }
    }

    @FXML
    void switchToSceneDelete(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SceneDelete.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

