package lab3;

class Village {
    
    private static final int SIZE = 1000;
    private Person[] allPersons;

    Village() {
        allPersons = new Person[SIZE];

        for (int i = 0; i < SIZE; i++) {
            allPersons[i] = new Person();
        }
    }

    public int villageSize() {
        return SIZE;
    }

    public int countSick() {
        int sickCount = 0;

        for (Person p : allPersons) {
            if (p.isSick()) {
                sickCount++;
            }
        }
        return sickCount;
    }

    public double sickProp() {
        return (double) countSick()/ (double) SIZE;
    }

    public int countDead() {
        int deadCount = 0;

        for (Person person : allPersons) {
            if (person.isDead()) {
                deadCount++;
            }
        }
        return deadCount;
    }

    public void dayPassesAll() {
        for (Person person : allPersons) {
            person.dayPasses(allPersons);
        }
    }

}
