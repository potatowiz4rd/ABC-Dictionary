import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DictionaryAddWord extends Dictionary {
    @FXML
    Button add_button;
    @FXML
    private TextField fieldAddTarget;
    @FXML
    private TextField fieldAddMeaning;

    public static List<String> addUp = new ArrayList<>();

    public static List<String> addTheWord(String addWord_target, String addWord_explain) {
        Word addNewWord = new Word(addWord_target, addWord_explain);
        WordList.add(addNewWord);
        for (Word word : WordList) {
            addUp.add(word.getWord_target());
        }
        return addUp;
    }

    public void applicationAddWord(javafx.event.ActionEvent event) {
        if (event.getSource() == add_button) {
            String word = fieldAddTarget.getText();
            String meaning = fieldAddMeaning.getText();
            if (word.length() > 0 && meaning.length() > 0) {
                List<String> newWord = addTheWord(word, meaning);
                DictionaryManagement.exportToFile();
                newWord.clear();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HEY!");
            alert.setHeaderText(null);
            alert.setContentText("Bạn đã thêm từ  " + '"' + word + '"' + " với ý nghĩa là " + '"' + meaning + '"' + ". Từ của bạn sẽ được cập nhật vào lần khởi chạy tiếp theo.");
            alert.showAndWait();
            fieldAddTarget.clear();
            fieldAddMeaning.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText(null);
            alert.setContentText("Xin hãy nhập từ mới và nghĩa.");
            alert.showAndWait();
        }
    }
}
