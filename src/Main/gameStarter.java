import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameStarter {

    public void start(Stage mainStage) throws Exception{
        int gameBoardSize = 500;
        int movingStepSize = 1;
        int mainHeroMovingStepSize = 10;
        String gameOverText = "Game Over!";

        //Gameboard scene
        Pane gameBoardPane = new Pane();
        Scene mainScene = new Scene(gameBoardPane, gameBoardSize, gameBoardSize);

        //GameOver scene
        //VBox gameOverVBox = new VBox();
        //Pane gameOverPane = new Pane();
        StackPane gameOverPane = new StackPane();
        Scene gameOverScene = new Scene(gameOverPane, gameBoardSize, gameBoardSize);
        //gameOverVBox.setSpacing(20);
        Label gameOverLabel = new Label();
        gameOverPane.setAlignment(gameOverLabel, Pos.CENTER);
        gameOverLabel.setText(gameOverText);
        //gameOverVBox.getChildren().add(gameOverLabel);
        gameOverPane.getChildren().add(gameOverLabel);

        //configuring Stage
        mainStage.setResizable(false); //disable change of gameboard (windows) size
        mainStage.setTitle("This game is about collisions");

        //hero initialize (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MainHero mainHero = new MainHero(50, "00FF00", 50, 250, mainHeroMovingStepSize);
        gameBoardPane.getChildren().add(mainHero);

        //initializing opponents (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MovingActors firstOpponent = new MovingActors(70, "FF0000", 300, 150, movingStepSize);
        gameBoardPane.getChildren().add(firstOpponent);
        MovingActors secondOpponent = new MovingActors(30, "FF0000", 300, 350, movingStepSize);
        gameBoardPane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();

        new AnimationTimer(){
            @Override
            public void handle (long now) {
                firstOpponent.move(movingStepSize, gameBoardSize);
                secondOpponent.move(movingStepSize, gameBoardSize);
                if (mainHero.detectCollisionWithOpponents(firstOpponent.getCenterX(), firstOpponent.getCenterY(), firstOpponent.getRadius(), secondOpponent.getCenterX(), secondOpponent.getCenterY(), secondOpponent.getRadius())) {
                    System.out.println("Am I running?");
                    stop();
                    //Platform.exit();
                    //mainStage.setScene(gameOverScene);
                    mainStage.setScene(gameOverScene);

                }
            }
        }.start();

        /*
        If I want to move hero in two direction simultaneously,
        then I have to use KePressed  and Key Released, otherwise only
        one KeyCode is passed to event handler.
         */
        mainScene.setOnKeyPressed (event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT && ((mainHero.getCenterX() + mainHero.getRadius() - 10 < gameBoardSize))) {
                mainHero.move(-mainHeroMovingStepSize,0);}
            else if (code == KeyCode.LEFT && ((mainHero.getCenterX() - mainHero.getRadius()) > 0 )){
                mainHero.move(mainHeroMovingStepSize,0);}
            else if (code == KeyCode.UP && ((mainHero.getCenterY() - mainHero.getRadius()) > 0)){
                mainHero.move(0,mainHeroMovingStepSize);}
            else if (code == KeyCode.DOWN && ((mainHero.getCenterY() + mainHero.getRadius()) < gameBoardSize)){
                mainHero.move(0,-mainHeroMovingStepSize);}

        });

    }
}
