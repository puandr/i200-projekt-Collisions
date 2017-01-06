import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameStarter {

    public void start(Stage mainStage) throws Exception{
        int gameBoardSize = Main.gameBoardSizeValue;
        int movingStepSize = Main.opponentSpeedValue;
        int mainHeroMovingStepSize = 10;
        String gameOverText = "Game Over!";
        String winningText = "You win!";

        //Gameboard scene
        Pane gameBoardPane = new Pane();
        Rectangle finishZone = new Rectangle();
        finishZone.setX(gameBoardSize-20);
        finishZone.setY(gameBoardSize/2 - 50);
        finishZone.setWidth(20);
        finishZone.setHeight(100);
        gameBoardPane.getChildren().add(finishZone);
        //finishZone.setFill(Color.RED);
        Image finishLineImage = new Image("/pics/finishline.png");
        finishZone.setFill(new ImagePattern(finishLineImage));

        Image imageForBackground = new Image("/pics/bg.png");
        BackgroundImage backgroundImage = new BackgroundImage(imageForBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        gameBoardPane.setBackground(new Background(backgroundImage));

        //GameOver scene
        VBox gameOverPane = new VBox(50);
        gameOverPane.setAlignment(Pos.CENTER);
        //gameOverPane.setStyle("-fx-background-color: black;");
        Scene gameOverScene = new Scene(gameOverPane, gameBoardSize, gameBoardSize);
        Label gameOverLabel = new Label();
        gameOverLabel.setText(gameOverText);
        gameOverLabel.setFont(new Font("Arial", 60));
        gameOverLabel.setTextFill(Color.RED);

        Image imageForGameOverPane = new Image("/pics/gameover.png");
        BackgroundImage gameOverBackgroundImage = new BackgroundImage(imageForGameOverPane, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        gameOverPane.setBackground(new Background(gameOverBackgroundImage));

        Button gameOverPlayAgainButton = new Button("Play again");
        gameOverPlayAgainButton.setStyle("-fx-font: 22 arial; -fx-base:#ff0000;");//#b6e7c9
        gameOverPlayAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    GameStarter newGame = new GameStarter();
                    newGame.start(mainStage);
                } catch(Exception ex) {
                    System.out.println("Something is wrong.. Can't make a new game...");
                }
            }
        });

        gameOverPane.getChildren().add(gameOverLabel);
        gameOverPane.getChildren().add(gameOverPlayAgainButton);

        //Winning pane
        VBox winningPane = new VBox(50);
        winningPane.setAlignment(Pos.CENTER);

        Label winningLabel = new Label();
        winningLabel.setText(winningText);
        winningLabel.setFont(new Font("Arial", 60));
        winningLabel.setTextFill(Color.GREEN);
        //winningPane.setStyle("-fx-background-color: blue");
        Image imageForWinningPane = new Image("/pics/winning.png");
        BackgroundImage winningBackgroundImage = new BackgroundImage(imageForWinningPane, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        winningPane.setBackground(new Background(winningBackgroundImage));

        Button winningPlayAgainButton = new Button("Play again");
        winningPlayAgainButton.setStyle("-fx-font: 22 arial; -fx-base:#00db39;");
        winningPlayAgainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    GameStarter newGame = new GameStarter();
                    newGame.start(mainStage);
                } catch(Exception ex) {
                    System.out.println("Something is wrong.. Can't make a new game...");
                }
            }
        });

        winningPane.getChildren().add(winningLabel);
        winningPane.getChildren().add(winningPlayAgainButton);
        Scene winningScene = new Scene(winningPane, gameBoardSize, gameBoardSize);

        //configuring Stage
        mainStage.setResizable(false); //disable change of gameboard (windows) size
        mainStage.setTitle("This game is about collisions");

        //hero initialize (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MainHero mainHero = new MainHero(50, "00FF00", 50, 250, mainHeroMovingStepSize);
        gameBoardPane.getChildren().add(mainHero);
        Image mainHeroImage = new Image("/pics/mainHero.png");
        mainHero.setFill(new ImagePattern(mainHeroImage));

        //initializing opponents (Circle size, "Circle color", X-position, Y-position, moving speed or moving step size)
        MovingActors firstOpponent = new MovingActors(Main.firstOpponentSizeValue, "FF0000", gameBoardSize-150, 100, movingStepSize);
        gameBoardPane.getChildren().add(firstOpponent);
        Image firstOpponentImage = new Image("/pics/opponent1.png");
        firstOpponent.setFill(new ImagePattern(firstOpponentImage));

        MovingActors secondOpponent = new MovingActors(Main.secondOpponentSizeValue, "FF00FF", gameBoardSize-150, gameBoardSize-100, movingStepSize);
        gameBoardPane.getChildren().add(secondOpponent);
        Image secondOpponentImage = new Image("/pics/opponent2.png");
        secondOpponent.setFill(new ImagePattern(secondOpponentImage));

        Scene mainScene = new Scene(gameBoardPane, gameBoardSize, gameBoardSize);
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

                //Pythagoras theorem, for calculating distance between opponents
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

                //Check collsion of Hero with opoonents, if collision is true, then game over
                if (mainHero.detectCollisionWithOpponents(firstOpponent.getCenterX(), firstOpponent.getCenterY(), firstOpponent.getRadius(), secondOpponent.getCenterX(), secondOpponent.getCenterY(), secondOpponent.getRadius())) {
                    System.out.println("You lost!");
                    stop();
                    mainStage.setScene(gameOverScene);
                }

                //Check if Hero reached the finish line, if true, game over.
                if (mainHero.intersects(finishZone.getBoundsInLocal())) {
                    System.out.println("It's a win!");
                    stop();
                    mainStage.setScene(winningScene);
                }
            }
        }.start();

        mainScene.setOnKeyPressed (event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT && ((mainHero.getCenterX() + mainHero.getRadius() < gameBoardSize))) {
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
