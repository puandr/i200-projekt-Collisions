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

class GameStarter {
    void start(Stage mainStage) throws Exception{
        int gameBoardSize = Main.gameBoardSizeValue;
        int movingStepSize = Main.opponentSpeedValue;
        int mainHeroMovingStepSize = 10;
        String gameOverText = "Game Over!";
        String winningText = "You win!";

        //Game board scene
        Pane gameBoardPane = new Pane();
        Rectangle finishZone = new Rectangle();
        finishZone.setX(gameBoardSize-20);
        finishZone.setY(gameBoardSize/2 - 50);
        finishZone.setWidth(20);
        finishZone.setHeight(100);
        gameBoardPane.getChildren().add(finishZone);
        Image finishLineImage = new Image("/pics/finishline.png");
        finishZone.setFill(new ImagePattern(finishLineImage));
        Image imageForBackground = new Image("/pics/bg.png");
        BackgroundImage backgroundImage = new BackgroundImage(imageForBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        gameBoardPane.setBackground(new Background(backgroundImage));

        //GameOver scene
        VBox gameOverPane = new VBox(50);
        gameOverPane.setAlignment(Pos.CENTER);
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

        //Animation Timer is running every time JavaFX is drawing a frame
        new AnimationTimer(){
            @Override
            public void handle (long now) {
                //Opponents are moving and bouncing from walls
                firstOpponent.move(movingStepSize, gameBoardSize);
                secondOpponent.move(movingStepSize, gameBoardSize);

                //Check collision between opponents, if true then bounce from each other
                if (MovingActors.checkCollisionBetweenOpponents(firstOpponent, secondOpponent)) {
                    MovingActors.collisionCalculation(firstOpponent, secondOpponent);
                }

                //Check collsion of Hero with opoonents, if collision is true, then game over
                if (mainHero.detectCollisionWithOpponents(firstOpponent, secondOpponent)) {
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

        //Get input from user and move Hero accordingly
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
