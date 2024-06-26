import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DictionaryApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Khởi tạo màn hình chính.
     * @param primaryStage - màn hình chính.
     * @throws Exception -handle.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxmlScene/SceneMain.fxml"));
        primaryStage.setTitle("ABC Dictionary");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
