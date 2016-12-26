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

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

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
        MovingActors firstOpponent = new MovingActors(80, "FF0000", 350, 125, movingStepSize);
        gameBoardPane.getChildren().add(firstOpponent);
        MovingActors secondOpponent = new MovingActors(40, "FF00FF", 350, 310, movingStepSize);
        gameBoardPane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();

        new AnimationTimer(){

            @Override
            public void handle (long now) {
                firstOpponent.move(movingStepSize, gameBoardSize);
                //firstOpponent.checkCollision(firstOpponent, secondOpponent, movingStepSize, gameBoardSize);
                secondOpponent.move(movingStepSize, gameBoardSize);
                //secondOpponent.checkCollision(firstOpponent, secondOpponent, movingStepSize);

                //Lets make things slower, 1000 = 1sec
                /*
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                */

                double xDifferenceSquare = Math.pow((secondOpponent.getCenterX() - firstOpponent.getCenterX()), 2);
                double yDifferenceSquare = Math.pow((secondOpponent.getCenterY() - firstOpponent.getCenterY()), 2);
                double distanceBetweenOpponentCenters = Math.sqrt(xDifferenceSquare + yDifferenceSquare);

                if (distanceBetweenOpponentCenters < (firstOpponent.getRadius() + secondOpponent.getRadius())) {

                    double dx1 = firstOpponent.dx;
                    double dy1 = firstOpponent.dy;
                    double r1 = firstOpponent.getRadius();

                    double dx2 = secondOpponent.dx;
                    double dy2 = secondOpponent.dy;
                    double r2 = secondOpponent.getRadius();

                    firstOpponent.dx = (dx1 * (r1 - r2) + (2 * r2 * dx2)) / (r1 + r2);
                    firstOpponent.dy = (dy1 * (r1 - r2) + (2 * r2 * dy2)) / (r1 + r2);
                    secondOpponent.dx = (dx2 * (r2 - r1) + (2 * r1 * dx1)) / (r1 + r2);
                    secondOpponent.dy = (dy2 * (r2 - r1) + (2 * r1 * dy1)) / (r1 + r2);

                    firstOpponent.setCenterX(firstOpponent.getCenterX() - dx1);
                    firstOpponent.setCenterY(firstOpponent.getCenterY() - dy1);
                    secondOpponent.setCenterX(secondOpponent.getCenterX() - dx2);
                    secondOpponent.setCenterY(secondOpponent.getCenterY() - dy2);

                    System.out.println("We are in collision mode ");
                    System.out.println("1 dy: " + firstOpponent.dy);
                    System.out.println("2 dy: " + secondOpponent.dy);
                    System.out.println();
                }

                if (mainHero.detectCollisionWithOpponents(firstOpponent.getCenterX(), firstOpponent.getCenterY(), firstOpponent.getRadius(), secondOpponent.getCenterX(), secondOpponent.getCenterY(), secondOpponent.getRadius())) {
                    System.out.println("Am I running?");
                    stop();
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
