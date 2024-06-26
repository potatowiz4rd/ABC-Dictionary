import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DictionaryAddWord {
    /**
     * Thêm từ vào từ điển.
     *
     * @param event add new word.
     */
    public void applicationAddWord(javafx.event.ActionEvent event) {
        if (event.getSource() == add_button) {
            String word = fieldAddTarget.getText();
            String meaning = fieldAddMeaning.getText();
            boolean check = true;
            for (int i = 0; i < Dictionary.WordTargets.size(); i++) {
                if (word.equalsIgnoreCase(Dictionary.WordTargets.get(i))) {
                    check = false;
                }
            }
            if (!check) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oops!");
                alert.setHeaderText(null);
                alert.setContentText("Từ bạn muốn thêm đã tồn tại!");
                alert.showAndWait();
            } else if (word.equals("") || meaning.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Oops!");
                alert.setHeaderText(null);
                alert.setContentText("Xin hãy nhập từ mới và nghĩa.");
                alert.showAndWait();
            } else if (check) {
                List<String> newWord = addTheWord(word, meaning);
                DictionaryManagement.exportToFile();
                newWord.clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("HEY!");
                alert.setHeaderText(null);
                alert.setContentText("Bạn đã thêm từ  " + '"' + word + '"' + " với ý nghĩa là " + '"' + meaning + '"' + ".");
                alert.showAndWait();
                fieldAddTarget.clear();
                fieldAddMeaning.clear();
            }
        }
    }

    @FXML
    Button add_button;
    @FXML
    private TextField fieldAddTarget;
    @FXML
    private TextField fieldAddMeaning;

    public static List<String> addUp = new ArrayList<>();

    public static List<String> addTheWord(String addWord_target, String addWord_explain) {
        Word addNewWord = new Word(addWord_target, addWord_explain);
        Dictionary.WordList.add(addNewWord);
        for (Word word : Dictionary.WordList) {
            addUp.add(word.getWord_target());
        }
        return addUp;
    }
}