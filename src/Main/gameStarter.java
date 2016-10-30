import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameStarter {

    public void start(Stage mainStage) throws Exception{
        int gameboardSize = 500;

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, gameboardSize, gameboardSize);

        MovingActors mainHero = new MovingActors(50, "00FF00", 50, 250);
        pane.getChildren().add(mainHero);

        MovingActors firstOpponent = new MovingActors(70, "FF0000", 400, 150);
        pane.getChildren().add(firstOpponent);

        MovingActors secondOpponent = new MovingActors(30, "FF0000", 400, 350);
        pane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();

        new AnimationTimer(){
            @Override
            public void handle (long now) {
                firstOpponent.move(1, 0);
            }
        }.start();

        mainScene.setOnKeyPressed (event -> {
            KeyCode code= event.getCode();
            if (code == KeyCode.RIGHT) {
                mainHero.move(-1,0);}
            if (code == KeyCode.LEFT){
                mainHero.move(1,0);
            }

        });

    }
}
