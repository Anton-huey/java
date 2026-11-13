package lab3;

import java.util.Date;
import java.util.Random;

class Person {
    private boolean isSick;
    private boolean isDead;
    private static final double IS_SICK_PROB = 0.001;
    private static final double GET_WELL_PROB = 0.2;
    private static final double DIE_PROB = 0.002;
    private static final double INFECT_PROB = 0.02;
    private static final double RANGE = 100;
    private static final int DAYS_IMMUNE = 1000;

    //covid 19 parameters
    private static final double DISTANCING = 0.0;
    private static final double CLUSTER_MIN = 400;
    private static final double CLUSTER_MAX = 600;
    private static final double AREA_SIZE = 1000;
    private final double range;

    private int daysLeftImmune;

    private final double xPos, yPos;
    private static final Random random = new Random();

    Person() {
        isDead=false;
        xPos = random.nextDouble() * AREA_SIZE;
        yPos = random.nextDouble() * AREA_SIZE;
        isSick = inCluster();
        daysLeftImmune=0;
        range=(random.nextDouble()<DISTANCING)?RANGE/2:RANGE;
    }

    public boolean isSick() {
        return isSick;
    }
    public boolean isDead() {
        return isDead;
    }
    public boolean isImmune(){return !isDead && daysLeftImmune>0;}

    private void becomesInfected() {
        if (!isSick && !isDead && random.nextDouble() <= INFECT_PROB && !isImmune()) {
            isSick = true;
        }
    }

    private boolean inCluster(){
        return xPos>=CLUSTER_MIN && xPos<=CLUSTER_MAX&&yPos>=CLUSTER_MIN && yPos<=CLUSTER_MAX;
    }

    private void infect(Person victim) {
        double dx = xPos - victim.xPos;
        double dy = yPos - victim.yPos;
        if (Math.sqrt(dx * dx + dy * dy) < range) {
            victim.becomesInfected();
        }
    }

    public void dayPasses(Person[] allPersons) {
        if (isSick) {
            for (Person victim : allPersons) {
                if (victim != this) {
                    infect(victim);
                }
            }
        }else{
            if (daysLeftImmune > 0) daysLeftImmune--;
        }

        if (isSick && random.nextDouble() <= DIE_PROB) {
            isDead = true;
            isSick = false;
        }

        else if (isSick && random.nextDouble() <= GET_WELL_PROB) {
            isSick = false;
            daysLeftImmune=DAYS_IMMUNE;
        }
    }
    



}
