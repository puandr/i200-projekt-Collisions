import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Gameboard {
    public Gameboard (int gameboardSize){
        Pane pane = new Pane();
        Scene mainScene = new Scene(pane, gameboardSize, gameboardSize);
    }

}



