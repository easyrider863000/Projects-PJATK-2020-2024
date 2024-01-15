public class Fabryka {
    private String smak;

    public Fabryka(String smak) {
        this.smak = smak;
    }

    public Cukierek[] produkuj(int count) {
        System.out.println("Produkcja " + count + "cukierkow o smaku: " + smak);
        Cukierek[] cukers = new Cukierek[count];
        int counter = 1;
        while (count > 1) {
            for (int i = 0; i < 5 && count > 1; i++) {
                cukers[counter - 1] = new Cukierek(this.smak, counter);
                counter++;
                count--;
                if (i == 4) {
                    System.out.println("Wyprodukowano 5cukierkow");
                }
            }
        }
        return cukers;
    }
}
