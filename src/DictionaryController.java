import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("test", "dog","Human", "Days of our life", "The best day",
                    "Friends", "Animal", "Human", "Humans", "Bear", "Life",
                    "This is some text", "Words", "222", "Bird", "Dog", "A few words",
                    "Subscribe!", "SoftwareEngineeringStudent", "You got this!!",
                    "Super Human", "Super", "Like")
    );
    /*
    public String[] str;
    public static void insertFromFile() throws IOException {
        ArrayList<String> wordsFromFile = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("dictionaries.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            wordsFromFile.add(line);
        }
        reader.close();
       // ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < wordsFromFile.size(); i++) {
            String[] str = wordsFromFile.get(i).split("\t");
           // words.add(str[0]);
        }
    }
    */
    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> wordView;

    @FXML
    void search(ActionEvent event) throws IOException {
        wordView.getItems().clear();
        wordView.getItems().addAll(searchList(searchBar.getText(),words));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordView.getItems().addAll(words);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {

        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

        return listOfStrings.stream().filter(input -> {
            return searchWordsArray.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}