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
    boolean detectCollisionWithOpponents(MovingActors opOne, MovingActors opTwo){
        boolean checkResult = false;
        double distanceToSecondOpponent = Math.hypot(opTwo.getCenterX() - this.getCenterX(), opTwo.getCenterY() - this.getCenterY());
        double distanceToFirstOpponent = Math.hypot(opOne.getCenterX() - this.getCenterX(), opOne.getCenterY() - this.getCenterY());

        if ((distanceToFirstOpponent - (this.getRadius() + opOne.getRadius())) < 0){
            checkResult = true;
            System.out.println("Collision with First opponent!!!");
        }
        else if((distanceToSecondOpponent) - (this.getRadius() + opTwo.getRadius()) < 0) {
            checkResult = true;
            System.out.println("Collision with Second Opponent!!!");
        }
        return checkResult;
    }
}