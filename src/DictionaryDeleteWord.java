import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDeleteWord {
    /**
     * Hàm xóa từ.
     *
     * @param event delete a word.
     */
    public void applicationDeleteWord(ActionEvent event) throws IOException {
        try {
            String delete = fieldDeleteTarget.getText();
            boolean check = false;
            for (int i = 0; i < Dictionary.WordTargets.size(); i++) {
                if (delete.equalsIgnoreCase(Dictionary.WordTargets.get(i))) {
                    check = true;
                }
            }
            if (event.getSource() == delete_button) {
                if (delete.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Oops!");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn chưa nhập từ cần xóa!");
                    alert.showAndWait();
                } else if (check) {
                    File inputFile = new File("dictionaries2.txt");
                    File tempFile = new File("tempFile.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if ((line.substring(0, line.lastIndexOf("#"))).equals(delete)) {
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
                    alert.setTitle("Delete");
                    alert.setHeaderText(null);
                    alert.setContentText("Từ " + '"' + delete + '"' + " đã được xóa.");
                    alert.showAndWait();
                } else if (!check) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Oops!");
                    alert.setHeaderText(null);
                    alert.setContentText("Từ cần xóa không tồn tại!");
                    alert.showAndWait();
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @FXML
    Button delete_button;

    @FXML
    private TextField fieldDeleteTarget;
}
