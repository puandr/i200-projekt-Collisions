import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MovingActors extends Circle {
    int size;
    double movingAngle;
    double dx;
    double dy;

    public MovingActors (int circleSize, String circleColor, int xPosition, int yPosition, int movingStepSize){
        Color setCircleColor = Color.web(circleColor);
        double circleRadius = (double) circleSize;
        size = circleSize;
        setRadius(circleRadius);
        setFill(setCircleColor);
        setCenterX(xPosition);
        setCenterY(yPosition);
        movingAngle = Math.toRadians(Math.random()*360);
        dx = Math.sin(movingAngle) * movingStepSize;
        dy = Math.cos(movingAngle) * movingStepSize;
    }

    void move(int movingStepSize, int gameBoardSize){
        double xSize = this.getCenterX() + this.getRadius();
        double ySize = this.getCenterY() + this.getRadius();

        if (xSize > gameBoardSize) {
            dx = -dx;
        }

        if ((xSize - 2 * this.getRadius()) < 0){
            dx = -dx;
        }
        if ((ySize - 2 * this.getRadius()) < 0) {
            dy = -dy;
        }
        if(ySize > gameBoardSize) {
            dy = -dy;
        }

        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);
    }

}
