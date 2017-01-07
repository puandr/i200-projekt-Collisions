import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage mainStage) throws Exception {
        GameSettings newGame = new GameSettings();
        newGame.start(mainStage);
    }
}
