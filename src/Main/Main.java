import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{

        GameStarter newGame = new GameStarter();
        newGame.start(mainStage);



    }
}
