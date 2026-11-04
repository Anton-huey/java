package lab3;

class Person {
    
    private boolean isSick;
    private boolean isDead;
    private static final double IS_SICK_PROB = 0.001;
    private static final double GET_WELL_PROB = 0.6;
    private static final double DIE_PROB = 0.001;
    private static final double INFECT_PROB = 0.01;

    Person() {
        isSick = Math.random() <= IS_SICK_PROB;
    }

    boolean isSick() {
        return isSick;
    }

    boolean isDead() {
        return isDead;
    }

    void becomesInfected() {
        if (!isSick && !isDead && Math.random() <= INFECT_PROB) {
            isSick = true;
        }
    }

    void infect(Person victim) {
        victim.becomesInfected();
    }

    void dayPasses(Person[] allPersons) {
        if (isSick) {
            for (Person victim : allPersons) {
                if (victim != this) {
                    infect(victim);
                }
            }
        }

        if (isSick && Math.random() <= DIE_PROB) {
            isDead = true;
            isSick = false;
        }

        else if (isSick && Math.random() <= GET_WELL_PROB) {
            isSick = false;
        }
    }
    



}
