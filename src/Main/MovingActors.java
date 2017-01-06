import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MovingActors extends Circle {
    private double dx;
    private double dy;

    MovingActors (int circleRadius, String circleColor, int xPosition, int yPosition, int movingStepSize){
        Color setCircleColor = Color.web(circleColor);
        setRadius(circleRadius);
        setFill(setCircleColor);
        setCenterX(xPosition);
        setCenterY(yPosition);
        double movingAngle = Math.toRadians(Math.random()*360);
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

    static void collisionCalculation (MovingActors firstOpponent, MovingActors secondOpponent){
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

    static boolean checkCollisionBetweenOpponents (MovingActors firstOpponent, MovingActors secondOpponent){
        boolean collision = false;

        double xDifferenceSquare = Math.pow((secondOpponent.getCenterX() - firstOpponent.getCenterX()), 2);
        double yDifferenceSquare = Math.pow((secondOpponent.getCenterY() - firstOpponent.getCenterY()), 2);
        double distanceBetweenOpponentCenters = Math.sqrt(xDifferenceSquare + yDifferenceSquare);

        if (distanceBetweenOpponentCenters < (firstOpponent.getRadius() + secondOpponent.getRadius())) {
            collision = true;
        }
        return collision;
    }
}
