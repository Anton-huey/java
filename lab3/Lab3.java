package lab3;

public class Lab3 {
    public static void main(String[] args) {

        Village village = new Village();

        System.out.println("Village size: " + village.villageSize());
        System.out.println("Sick people: " + village.countSick());
        System.out.println("Proportion of sick people: " + village.sickProp());
        System.out.println();

        int daysPassed = 0;

        while (village.countSick() > 0) {
            System.out.println("Days passed: " + daysPassed);
            System.out.println("Sick: " + village.countSick());
            System.out.println("Dead: " + village.countDead());
            System.out.println();

            village.dayPassesAll();
            daysPassed++;
        }

        System.out.println("Simulation stopped after " + daysPassed + " days.");
        System.out.println("Sick: " + village.countSick());
        System.out.println("Dead: " + village.countDead());

    }
}
