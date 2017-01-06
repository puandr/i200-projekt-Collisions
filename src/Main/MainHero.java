class MainHero extends MovingActors{
    MainHero(int circleSize, String circleColor, int xPosition, int yPosition, int movingStepSize){
        super(circleSize, circleColor, xPosition, yPosition, movingStepSize);
    }

    //peategelase liikumine
    void move (int horizontalMovement, int verticalMovement){
        this.setCenterX(getCenterX()-horizontalMovement);
        this.setCenterY(getCenterY()-verticalMovement);
    }

    //Check collision with opponents
    //opOne - opponent One, opTwo - opponent Two.
    boolean detectCollisionWithOpponents(double opOneX, double opOneY, double opOneRadius, double opTwoX, double opTwoY, double opTwoRadius){
        boolean checkResult = false;
        double distanceToSecondOpponent = Math.hypot(opTwoX - this.getCenterX(), opTwoY - this.getCenterY());
        double distanceToFirstOpponent = Math.hypot(opOneX - this.getCenterX(), opOneY - this.getCenterY());

        if ((distanceToFirstOpponent - (this.getRadius() + opOneRadius)) < 0){
            checkResult = true;
            System.out.println("Collision with First opponent!!!");
        }
        else if((distanceToSecondOpponent) - (this.getRadius() + opTwoRadius) < 0) {
            checkResult = true;
            System.out.println("Collision with Second Opponent!!!");
        }
        return checkResult;
    }
}