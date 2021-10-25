import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class DictionaryDeleteWord {
    /**
     * Hàm xóa từ.
     *
     * @param event delete a word.
     */
    public void applicationDeleteWord(ActionEvent event) throws IOException {
        String delete = fieldDeleteTarget.getText();
        if (event.getSource() == delete_button) {
            //List<String> pullOut = removeTheWord(del);
            //DictionaryManagement.exportToFile();
            //pullOut.clear();
            File inputFile = new File("dictionaries2.txt");
            File tempFile = new File("tempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(delete)) {
                    continue;
                }
                writer.write(line + "\n");
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
            fieldDeleteTarget.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("HEY!");
            alert.setHeaderText(null);
            alert.setContentText("Từ " + '"' + delete + '"' + " đã được xóa.");
            alert.showAndWait();
        }
    }

    public static List<String> removeOut = new ArrayList<>();

    /**
     * public static List<String> removeTheWord(String target) {
     * for(int i=WordList.size()-1;i>=0;i--) {
     * Word word = WordList.get(i);
     * String removing = word.getWord_target();
     * if (removing.equalsIgnoreCase(target)) {
     * WordList.remove(i);
     * } else {
     * removeOut.add(WordList.get(i).getWord_target());
     * }
     * }
     * return removeOut;
     * }
     * <p>
     * <p>
     * public static List<String> removeTheWord(String target) {
     * for (int i = 0; i < WordTargets.size(); i++) {
     * String removing = WordTargets.get(i);
     * if (removing.equalsIgnoreCase(target)) {
     * WordTargets.remove(i);
     * } else {
     * removeOut.add(WordTargets.get(i));
     * }
     * }
     * return removeOut;
     * <p>
     * }
     */

    @FXML
    Button delete_button;

    @FXML
    private TextField fieldDeleteTarget;
}
