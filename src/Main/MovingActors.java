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
        //movingAngle = Math.toRadians(Math.random()*360);
        movingAngle = Math.toRadians(90);
        dx = Math.sin(movingAngle) * movingStepSize;
        dy = Math.cos(movingAngle) * movingStepSize;
    }

    void move(int movingStepSize, int gameBoardSize){

        double xSize = this.getCenterX() + this.getRadius();
        double ySize = this.getCenterY() + this.getRadius();

        System.out.println(moveNumber + " " + xSize + " " + dx + " GBS: " + gameBoardSize);
        if (xSize > gameBoardSize) {
            System.out.println(xSize > gameBoardSize);
            dx = dx * (-1);

        }

        if (xSize < 0 && dx < 0){
            dx = dx;
            //this.setCenterX(getCenterX() + movingStepSize);
        }
        if (ySize < 0 && dy < 0) {
            dy = -dy;
        }
        if(ySize > gameBoardSize && dy > 0) {
            dy = -dy;
        }

        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);

        /*
        movingDirectionX = Math.sin(movingAngle) * movingStepSize;
        movingDirectionY = Math.cos(movingAngle) * movingStepSize;

        //Collision with border, checking X to right border
        if ((this.getCenterX() + getRadius() >= gameBoardSize)) {
            this.setCenterX(this.getCenterX()-movingStepSize);
            if((1.5*pi < movingAngle) && (movingAngle < 2*pi)){
                movingAngle = calculateBouncingAngle(movingAngle);
            }
            if((pi < movingAngle) && (movingAngle < pi*1.5)){
                movingAngle = movingAngle - pi/2;
            }
        }

        //Collision detection with border, checking X to left border
        if ((this.getCenterX() - getRadius() <= 0)) {
            this.setCenterX(this.getCenterX() + movingStepSize);
            if((0 < movingAngle) && (movingAngle < pi/2)){
                movingAngle = movingAngle + 1.5*pi;
            }
            if((pi/2 > movingAngle) && (movingAngle < pi)){
                movingAngle = movingAngle - pi/2;
            }
        }
        this.setCenterX(getCenterX() - movingDirectionX);
        this.setCenterY(getCenterY() - movingDirectionY);
        */
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
