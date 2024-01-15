public class Cukierek {
    private String smak;
    private int nr;

    public Cukierek(String smak, int nr) {
        this.smak = smak;
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Cukierek nr " + nr + " smak: +" + smak;
    }
}
