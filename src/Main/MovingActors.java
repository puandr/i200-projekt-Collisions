import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MovingActors extends Circle {
    int size;
    int movingSpeed;
    double movingDirectionX;
    double movingDirectionY;
    double movingAngle;
    int collisionState;
    double pi = Math.PI;
    double dx;
    double dy;
    int moveNumber;

    public MovingActors (int circleSize, String circleColor, int xPosition, int yPosition, int movingStepSize){
        Color setCircleColor = Color.web(circleColor);
        double circleRadius = (double) circleSize;
        size = circleSize;
        setRadius(circleRadius);
        setFill(setCircleColor);
        setCenterX(xPosition);
        setCenterY(yPosition);
        movingAngle = Math.toRadians(Math.random()*360);
        //movingAngle = Math.toRadians(90);
        dx = Math.sin(movingAngle) * movingStepSize;
        dy = Math.cos(movingAngle) * movingStepSize;
    }

    void move(int movingStepSize, int gameBoardSize){
        double xSize = this.getCenterX() + this.getRadius();
        double ySize = this.getCenterY() + this.getRadius();

        if (xSize > gameBoardSize) {
            dx = -dx;
        }

        if ((xSize - 2*this.getRadius()) < 0){
            dx = -dx;
        }
        if ((ySize - 2*this.getRadius()) < 0 ) {
            dy = -dy;
        }
        if(ySize > gameBoardSize) {
            dy = -dy;
        }

        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);

    }

    double calculateBouncingAngle(double incomingAngle) {
        double bouncedAngle = 0;
        double angleAxeDifference;
        if ((1.5 * pi < incomingAngle) && (incomingAngle < 2 * pi)) {
            angleAxeDifference = 2*pi - incomingAngle;
            bouncedAngle = angleAxeDifference;
        }
        return bouncedAngle;
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
