public class MainHero extends MovingActors{
    public MainHero(int circleSize, String circleColor, int xPosition, int yPosition, int movingStepSize){
        super(circleSize, circleColor, xPosition, yPosition, movingStepSize);
    }

    void move (int horizontalMovement, int verticalMovement){
        this.setCenterX(getCenterX()-horizontalMovement);
        this.setCenterY(getCenterY()-verticalMovement);

    }



}