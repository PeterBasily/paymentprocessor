
package PayrollGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Main for payroll processing application.
 * @author Peter Basily, Quan Hoang
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Payroll Processing");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
