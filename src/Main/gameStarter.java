import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class gameStarter extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{
        int gameboardSize = 500;

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, gameboardSize, gameboardSize);

        mainStage.setScene(mainScene);
        mainStage.show();

    }
}
