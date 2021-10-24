import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class DictionaryDeleteWord extends Dictionary {
    /**
     * Hàm xóa từ.
     * @param event delete a word.
     */
    public void applicationDeleteWord(ActionEvent event) {
        String del = fieldDeleteTarget.getText();
        if (event.getSource() == delete_button) {
            List<String> pullOut = removeTheWord(del);
            DictionaryManagement.exportToFile();
            pullOut.clear();
            fieldDeleteTarget.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HEY!");
            alert.setHeaderText(null);
            alert.setContentText("Từ " + '"' + del + '"' + " đã được xóa.");
            alert.showAndWait();
        }
    }

    public static List<String> removeOut=new ArrayList<>();

    public static List<String> removeTheWord(String target) {
        for(int i=WordList.size()-1;i>=0;i--) {
            Word word = WordList.get(i);
            String removing = word.getWord_target();
            if (removing.equalsIgnoreCase(target)) {
                WordList.remove(i);
            } else {
                removeOut.add(WordList.get(i).getWord_target());
            }
        }
        return removeOut;
    }

    @FXML
    Button delete_button;

    @FXML
    private TextField fieldDeleteTarget;
}
