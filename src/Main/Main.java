import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Main extends Application {

    public static int opponentSpeedValue = 1;
    public static int firstOpponentSizeValue = 50;
    public static int secondOpponentSizeValue = 50;
    public static int gameBoardSizeValue = 500;

    @Override
    public void start(Stage mainStage) throws Exception{
        int settingsWindowSizeX = 330;
        int settingsWindowSizeY = 250;

        GridPane newGamePane = new GridPane();
        mainStage.setResizable(false); //disable change of gameboard (windows) size

        Button newGameStartButton = new Button("Start Game");
        newGameStartButton.setTranslateY(50);
        newGameStartButton.setTranslateX(-50);

        Label speedValueLabel = new Label("Choose opponents speed       ");

        Slider opponentSpeedSlider = new Slider();
        opponentSpeedSlider.setMin(1);
        opponentSpeedSlider.setMax(20);
        opponentSpeedSlider.setValue(1);
        opponentSpeedSlider.setShowTickLabels(true);
        opponentSpeedSlider.setShowTickMarks(true);
        opponentSpeedSlider.setMajorTickUnit(5);
        opponentSpeedSlider.setMinorTickCount(1);
        opponentSpeedSlider.setTranslateY(10);

        Label firstOpponentSize = new Label("Choose size of first opponent");
        Slider firstOpponentSizeSlider = new Slider();
        firstOpponentSizeSlider.setMin(10);
        firstOpponentSizeSlider.setMax(90);
        firstOpponentSizeSlider.setValue(50);
        firstOpponentSizeSlider.setShowTickLabels(true);
        firstOpponentSizeSlider.setShowTickMarks(true);
        firstOpponentSizeSlider.setMajorTickUnit(20);
        firstOpponentSizeSlider.setMinorTickCount(10);
        firstOpponentSizeSlider.setTranslateY(10);


        Label secondOpponentSizeLabel = new Label("Choose size of second opponent");
        Slider secondOpponentSizeSlider = new Slider();
        secondOpponentSizeSlider.setMin(10);
        secondOpponentSizeSlider.setMax(90);
        secondOpponentSizeSlider.setValue(50);
        secondOpponentSizeSlider.setShowTickLabels(true);
        secondOpponentSizeSlider.setShowTickMarks(true);
        secondOpponentSizeSlider.setMajorTickUnit(20);
        secondOpponentSizeSlider.setMinorTickCount(10);
        secondOpponentSizeSlider.setTranslateY(10);

        Label gameBoardSizeLabel = new Label("Choose gameboard size");
        Slider gameBoardSizeSlider = new Slider();
        gameBoardSizeSlider.setMin(300);
        gameBoardSizeSlider.setMax(700);
        gameBoardSizeSlider.setValue(500);
        gameBoardSizeSlider.setShowTickMarks(true);
        gameBoardSizeSlider.setShowTickLabels(true);
        gameBoardSizeSlider.setMajorTickUnit(100);
        gameBoardSizeSlider.setMinorTickCount(50);
        gameBoardSizeSlider.setTranslateY(10);

        opponentSpeedSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                opponentSpeedValue = ((int) opponentSpeedSlider.getValue());
            }
        });

        firstOpponentSizeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                firstOpponentSizeValue = ((int) firstOpponentSizeSlider.getValue());
            }
        });

        secondOpponentSizeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                secondOpponentSizeValue = ((int) secondOpponentSizeSlider.getValue());
            }
        });

        gameBoardSizeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                gameBoardSizeValue = ((int) gameBoardSizeSlider.getValue());
            }
        });

        Scene newGameScene = new Scene(newGamePane, settingsWindowSizeX, settingsWindowSizeY);

        newGamePane.add(speedValueLabel, 0, 0);
        newGamePane.add(opponentSpeedSlider, 1, 0);
        newGamePane.add(firstOpponentSize, 0, 3);
        newGamePane.add(firstOpponentSizeSlider, 1, 3);
        newGamePane.add(secondOpponentSizeLabel, 0, 5);
        newGamePane.add(secondOpponentSizeSlider, 1, 5);
        newGamePane.add(gameBoardSizeLabel, 0, 7);
        newGamePane.add(gameBoardSizeSlider, 1, 7);
        newGamePane.add(newGameStartButton, 1, 11);

/*
        BackgroundImage seetingsWindowBackgroundImage= new BackgroundImage(new Image("my url",32,32,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        String image = JavaFXAppl.class.getResource("background.jpg").toExternalForm();
        newGamePane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
*/
        mainStage.setScene(newGameScene);
        mainStage.show();

        String myImage = "/background.jpg";
        Image backgroundImage = new Image(myImage);
        ImagePattern pattern = new ImagePattern(backgroundImage);
        newGameScene.setFill(pattern);

        newGameStartButton.setOnAction(new EventHandler<ActionEvent>() {
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
    }
}
