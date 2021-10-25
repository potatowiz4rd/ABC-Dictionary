import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.speech.AudioException;
import javax.speech.EngineException;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.darkprograms.speech.translator.GoogleTranslate;


public class DictionaryController implements Initializable {
    public static List<String> wordArray = new ArrayList<>();

    @FXML
    private ComboBox<String> Language;
    private final ObservableList<String> languageList = FXCollections.observableArrayList("VI", "JA", "RU", "FR");

    @FXML
    private TextField searchBar;

    @FXML
    public ListView<String> myListView;

    @FXML
    private TextArea ExplainField;

    @FXML
    private Button voice;
    @FXML
    private Button GGTranslate;

    @FXML
    private void displaySelected(MouseEvent event) {
        searchBar.setText(myListView.getSelectionModel().getSelectedItem().toString());
        //ExplainField.setText(DictionaryManagement.dictionaryLookup(myListView.getSelectionModel().getSelectedItem().toString()));
        String word = searchBar.getText();
        InternetConnection ic = new InternetConnection();
        String data = ic.getOnlineData(word);
        if (data.equalsIgnoreCase("error")) {
            //ExplainField.setText("Word not found!");
            ExplainField.setText(DictionaryManagement.dictionaryLookup(myListView.getSelectionModel().getSelectedItem().toString()));
        } else {
            ExplainField.setText(data);
        }
    }

    @FXML
    public void textToSpeech() throws EngineException, AudioException, InterruptedException {
        String wordSpeech = searchBar.getText();
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

        Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

        synthesizer.allocate();
        synthesizer.resume();

        synthesizer.speakPlainText(wordSpeech, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

        //Deallocate synthesizer
        //synthesizer.deallocate();
    }

    @FXML
    public void translation() {
        String selectedLanguage = Language.getSelectionModel().getSelectedItem().toLowerCase();
        try {
            String meaning = GoogleTranslate.translate(selectedLanguage, searchBar.getText());
            ExplainField.clear();
            ExplainField.setText(meaning);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void InsertFromFile() throws IOException {
        try {
            ArrayList<String> wordsFromFile = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("dictionaries2.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    wordsFromFile.add(line);
                }
            }
            reader.close();

            for (int i = 0; i < wordsFromFile.size(); i++) {
                String[] str = wordsFromFile.get(i).split("#");
                Word result = new Word(str[0].trim(), str[1].trim());
                Dictionary.WordTargets.add(i, str[0]);
                Dictionary.WordExplains.add(i, str[1]);
                Dictionary.WordList.add(result);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @FXML
    public void search() {
        String wordSearch = searchBar.getText().toString();
        List<String> s = dictionarySearch(wordSearch);
        ObservableList<String> input = FXCollections.observableArrayList(s);
        myListView.setItems(input);
        wordArray.clear();
    }


    @FXML
    public static List<String> dictionarySearch(String wordSearch) {
        for (int i = 0; i < Dictionary.WordTargets.size(); i++) {
            if (Dictionary.WordTargets.get(i).toLowerCase().startsWith(wordSearch)) {
                wordArray.add(Dictionary.WordTargets.get(i));
            }
        }
        Collections.sort(wordArray);
        return wordArray;
    }

    @FXML
    private void onlineSearch(ActionEvent event) {
        String word = searchBar.getText();
        InternetConnection ic = new InternetConnection();
        String data = ic.getOnlineData(word);
        ExplainField.setText(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            InsertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        populateData();
        Language.setValue("VI");
        Language.setItems(languageList);

    }

    private void populateData() {
        myListView.getItems().addAll(Dictionary.WordTargets);
    }

    Stage stage;
    Scene scene;

    public void RefreshData(ActionEvent event) throws IOException {
        myListView.getItems().clear();
        searchBar.clear();
        ExplainField.clear();
        Dictionary.WordTargets.clear();
        Dictionary.WordExplains.clear();
        Dictionary.WordList.clear();
        InsertFromFile();
        populateData();
    }

    @FXML
    Button refreshButton;

    @FXML
    Button addButton; //nút chuyển sang cảnh thêm từ.
    @FXML
    Button deleteButton;  //nút chuyển sang cảnh xóa từ.

    /**
     * Chuyển sang cửa sổ thêm từ.
     *
     * @param event open add window.
     */
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

    /**
     * Chuyển sang cửa sổ xóa từ.
     *
     * @param event open delete window.
     */
    @FXML
    void switchToSceneDelete(ActionEvent event) {
        try {
            if (event.getSource() == deleteButton) {
                Parent root = FXMLLoader.load(getClass().getResource("fxmlScene/SceneDelete.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(deleteButton.getScene().getWindow());
                stage.showAndWait();
            }
        } catch (IOException ex) {
            System.out.println("Can't open");
        }
    }

    /**
     * Hàm đóng ứng dụng.
     *
     * @param event close application.
     */
    @FXML
    public void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }


    /**
     * Thông tin nhóm.
     *
     * @param event information about us.
     */
    @FXML
    public void aboutUs(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Us");
        alert.setHeaderText(null);
        alert.setContentText("@Copyright-This Dictionary was written by Hoang Quoc Cuong, Nguyen Xuan Bach and Duong Hung Anh");
        alert.show();
    }

    @FXML
    MenuItem howtouse;

    /**
     * Hướng dẫn sử dụng.
     *
     * @param event open tutorial window.
     */
    @FXML
    public void howUse(ActionEvent event) {
        try {
            if (event.getSource() == howtouse) {
                Parent root = FXMLLoader.load(getClass().getResource("fxmlScene/SceneTutorial.fxml"));
                stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
