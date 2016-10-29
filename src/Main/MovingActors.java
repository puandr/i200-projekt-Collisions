import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MovingActors extends Circle {
    int size;
    int movingSpeed;
    int movingDirection;
    int collisionState;

    public MovingActors (int circleSize, String circleColor, int xPosition, int yPosition){
        Color setCircleColor = Color.web(circleColor);
        double circleRadius = (double) circleSize;

        size = circleSize;
        setRadius(circleRadius);
        setFill(setCircleColor);
        setCenterX(xPosition);
        setCenterY(yPosition);
    }

    void move(int movingDirection, int movingSpeed){

    }

    int calculateSpeedAfterCollision(int currentSpeed){
        int newSpeed = 0;

        return newSpeed;
    }

    int calculateDirectionAfterCollision(int currentDirection){
        int newDirection = 0;

        return newDirection;
    }

}
