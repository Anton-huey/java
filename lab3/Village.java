package lab3;

class Village {
    
    private static final int SIZE = 1000;
    private final Person[] population;

    Village() {
        population = new Person[SIZE];

        for (int i = 0; i < SIZE; i++) {
            population[i] = new Person();
        }
    }

    public int getVillageSize() {
        return SIZE;
    }

    public int countSick() {
        int sickCount = 0;

        for (Person p : population) {
            if (p.isSick()) {
                sickCount++;
            }
        }
        return sickCount;
    }

    public int countDead() {
        int deadCount = 0;

        for (Person person : population) {
            if (person.isDead()) {
                deadCount++;
            }
        }
        return deadCount;
    }

    public int countImmune(){
        int immuneCount = 0;
        for (Person person : population) {
            if (person.isImmune()) {
                immuneCount++;
            }
        }
        return immuneCount;
    }

    public double sickProp() {
        return (double) countSick()/ (double) SIZE;
    }

    public void dayPassesAll() {
        for (Person person : population) {
            person.dayPasses(population);
        }
    }
}
