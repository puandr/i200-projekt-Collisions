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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class GameStarter {

    public void start(Stage mainStage) throws Exception{
        int gameBoardSize = Main.gameBoardSizeValue;
        int movingStepSize = Main.opponentSpeedValue;
        int mainHeroMovingStepSize = 10;
        String gameOverText = "Game Over!";
        String winningText = "You win!";

        //Gameboard scene
        Pane gameBoardPane = new Pane();
        Scene mainScene = new Scene(gameBoardPane, gameBoardSize, gameBoardSize);
        Line finishLine = new Line(gameBoardSize, (gameBoardSize/2 - 30), gameBoardSize, (gameBoardSize/2 + 30));
        gameBoardPane.getChildren().add(finishLine);

        //GameOver scene
        StackPane gameOverPane = new StackPane();
        Scene gameOverScene = new Scene(gameOverPane, gameBoardSize, gameBoardSize);
        Label gameOverLabel = new Label();
        gameOverPane.setAlignment(gameOverLabel, Pos.CENTER);
        gameOverLabel.setText(gameOverText);
        gameOverPane.getChildren().add(gameOverLabel);

        //Winning pane
        StackPane winningPane = new StackPane();
        Scene winningScene = new Scene(winningPane, gameBoardSize, gameBoardSize);
        Label winningLabel = new Label();
        winningPane.setAlignment(winningLabel, Pos.CENTER);
        winningLabel.setText(winningText);
        winningPane.getChildren().add(winningLabel);

        //configuring Stage
        mainStage.setResizable(false); //disable change of gameboard (windows) size
        mainStage.setTitle("This game is about collisions");

        //hero initialize (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MainHero mainHero = new MainHero(50, "00FF00", 50, 250, mainHeroMovingStepSize);
        gameBoardPane.getChildren().add(mainHero);

        //initializing opponents (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MovingActors firstOpponent = new MovingActors(Main.firstOpponentSizeValue, "FF0000", gameBoardSize-150, 100, movingStepSize);
        gameBoardPane.getChildren().add(firstOpponent);
        MovingActors secondOpponent = new MovingActors(Main.secondOpponentSizeValue, "FF00FF", gameBoardSize-150, gameBoardSize-100, movingStepSize);
        gameBoardPane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();

        new AnimationTimer(){

            @Override
            public void handle (long now) {
                firstOpponent.move(movingStepSize, gameBoardSize);
                secondOpponent.move(movingStepSize, gameBoardSize);

                // Just for testing purposes, lets make things slower, 1000 = 1sec
                /*
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                */

                //Pythagoras theorem
                double xDifferenceSquare = Math.pow((secondOpponent.getCenterX() - firstOpponent.getCenterX()), 2);
                double yDifferenceSquare = Math.pow((secondOpponent.getCenterY() - firstOpponent.getCenterY()), 2);
                double distanceBetweenOpponentCenters = Math.sqrt(xDifferenceSquare + yDifferenceSquare);

                if (distanceBetweenOpponentCenters < (firstOpponent.getRadius() + secondOpponent.getRadius())) {

                    //Have to assign temporary variables, otherwise secondOpponent dy and dx will use already changed firstOpponent dx and dy
                    double dx1 = firstOpponent.dx;
                    double dy1 = firstOpponent.dy;
                    double r1 = firstOpponent.getRadius();

                    double dx2 = secondOpponent.dx;
                    double dy2 = secondOpponent.dy;
                    double r2 = secondOpponent.getRadius();

                    //calculating new direction and speed (Elastic collision)
                    firstOpponent.dx = (dx1 * (r1 - r2) + (2 * r2 * dx2)) / (r1 + r2);
                    firstOpponent.dy = (dy1 * (r1 - r2) + (2 * r2 * dy2)) / (r1 + r2);
                    secondOpponent.dx = (dx2 * (r2 - r1) + (2 * r1 * dx1)) / (r1 + r2);
                    secondOpponent.dy = (dy2 * (r2 - r1) + (2 * r1 * dy1)) / (r1 + r2);

                    //On collision take a move back, so avoiding stacking in each other
                    firstOpponent.setCenterX(firstOpponent.getCenterX() - dx1);
                    firstOpponent.setCenterY(firstOpponent.getCenterY() - dy1);
                    secondOpponent.setCenterX(secondOpponent.getCenterX() - dx2);
                    secondOpponent.setCenterY(secondOpponent.getCenterY() - dy2);

                }

                //Check collsion of Hero with opoonents, if true collsion is true, then game over
                if (mainHero.detectCollisionWithOpponents(firstOpponent.getCenterX(), firstOpponent.getCenterY(), firstOpponent.getRadius(), secondOpponent.getCenterX(), secondOpponent.getCenterY(), secondOpponent.getRadius())) {
                    System.out.println("You lost!");
                    stop();
                    mainStage.setScene(gameOverScene);
                }

                //Check if Hero reached the finish line, if true, game over.
                if (mainHero.intersects(finishLine.getBoundsInLocal())) {
                    System.out.println("Relax");
                    stop();
                    mainStage.setScene(winningScene);
                }

            }
        }.start();

        /*
        If I want to move hero in two direction simultaneously, then I have to use KePressed  and Key Released, otherwise only
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
