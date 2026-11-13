package lab3;

public class Simulation {
    public static void main(String[] args) {

        Village village = new Village();

        System.out.println("Village size: " + village.getVillageSize());
        System.out.println("Sick people: " + village.countSick());
        System.out.println("Proportion of sick people: " + village.sickProp());
        System.out.println();
        System.out.println("Day\tSick\tImmune\tDead\tSusceptible");

        int daysPassed = 0;
        int peakSick = 0, peakDay = 0, reprodAtPeak = 0;

        while (village.countSick() > 0) {
            int sick=village.countSick();
            int immune=village.countImmune();
            int dead=village.countDead();
            int reprod=village.getVillageSize()-sick-immune-dead;

            System.out.println(daysPassed + "\t" + sick + "\t" + immune + "\t" + dead + "\t" + reprod);

            if (sick>peakSick){
                peakSick=sick;
                peakDay=daysPassed;
                reprodAtPeak=reprod;
            }

            village.dayPassesAll();
            daysPassed++;
        }

        System.out.println();
        System.out.println("Simulation stopped after " + daysPassed + " days.");
        System.out.println("Dead: " + village.countDead());
        System.out.println("Peak sick: "+peakSick+" sick on day "+peakDay+", reprod share was "+ (double) reprodAtPeak/village.getVillageSize());

    }
}
