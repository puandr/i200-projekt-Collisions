import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Created by andrei on 25.10.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage mainStage) throws Exception{

        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, 500, 500);

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

    }
}