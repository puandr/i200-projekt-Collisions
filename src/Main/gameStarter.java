import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameStarter {

    public void start(Stage mainStage) throws Exception{
        int gameboardSize = 500;
        int movingStepSize = 1;

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, gameboardSize, gameboardSize);

        MainHero mainHero = new MainHero(50, "00FF00", 50, 250, movingStepSize);
        pane.getChildren().add(mainHero);

        MovingActors firstOpponent = new MovingActors(70, "FF0000", 300, 150, movingStepSize);
        pane.getChildren().add(firstOpponent);

        MovingActors secondOpponent = new MovingActors(30, "FF0000", 300, 350, movingStepSize);
        pane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();

        new AnimationTimer(){
            @Override
            public void handle (long now) {
                firstOpponent.move(movingStepSize, gameboardSize);
                secondOpponent.move(movingStepSize, gameboardSize);
            }
        }.start();

        mainScene.setOnKeyPressed (event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT) {
                mainHero.move(-movingStepSize,0);}
            if (code == KeyCode.LEFT){
                mainHero.move(movingStepSize,0);}
            if (code == KeyCode.UP){
                mainHero.move(0,movingStepSize);}
            if (code == KeyCode.DOWN){
                mainHero.move(0,-movingStepSize);}

        });

    }
}
