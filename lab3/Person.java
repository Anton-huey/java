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
    private static final double AREA_SIZE = 1000;
    private static final int DAYS_IMMUNE = 1000;
    private int daysLeftImmune;

    private final double xPos, yPos;
    private static final Random random = new Random();

    Person() {
        isSick = random.nextInt() < IS_SICK_PROB;
        isDead=false;
        xPos = random.nextDouble() * AREA_SIZE;
        yPos = random.nextDouble() * AREA_SIZE;
        daysLeftImmune=0;
    }

    boolean isSick() {
        return isSick;
    }
    boolean isDead() {
        return isDead;
    }

    private void becomesInfected() {
        if (!isSick && !isDead && random.nextDouble() <= INFECT_PROB && daysLeftImmune==0) {
            isSick = true;
        }
    }



    private void infect(Person victim) {
        double dx = xPos - victim.xPos;
        double dy = yPos - victim.yPos;
        if (Math.sqrt(dx * dx + dy * dy) < RANGE) {
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
