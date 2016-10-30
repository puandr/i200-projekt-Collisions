import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{

        GameStarter newGame = new GameStarter();
        newGame.start(mainStage);

        /*
        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, 500, 500);

        MovingActors mainHero = new MovingActors(50, "00FF00", 50, 250);
        pane.getChildren().add(mainHero);

        MovingActors firstOpponent = new MovingActors(70, "FF0000", 400, 150);
        pane.getChildren().add(firstOpponent);

        MovingActors secondOpponent = new MovingActors(30, "FF0000", 400, 350);
        pane.getChildren().add(secondOpponent);

        mainStage.setScene(mainScene);
        mainStage.show();






        Circle mainHero = new Circle(50);
        mainHero.setCenterX(50);
        mainHero.setCenterY(250);
        mainHero.setFill(Color.GREEN);

        Circle firstOpponent = new Circle(50);
        firstOpponent.setCenterX(450);
        firstOpponent.setCenterY(150);
        firstOpponent.setFill(Color.RED);

        Circle secondOpponent = new Circle(50);
        secondOpponent.setCenterX(450);
        secondOpponent.setCenterY(350);
        secondOpponent.setFill(Color.RED);

        pane.getChildren().add(mainHero);
        pane.getChildren().add(firstOpponent);
        pane.getChildren().add(secondOpponent);
        */


    }
}
