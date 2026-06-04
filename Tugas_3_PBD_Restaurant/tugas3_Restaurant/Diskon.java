public class Diskon extends MenuItem {
    private double persentaseDiskon;

    public Diskon(String nama, double persentaseDiskon) {
        super(nama, 0, "Diskon"); 
        this.persentaseDiskon = persentaseDiskon;
    }

    public double getPersentaseDiskon() {
        return persentaseDiskon;
    }

    @Override
    public void tampilMenu() {
        System.out.printf("[Voucher] %-21s : Diskon %.0f%%\n", super.getNama(), (persentaseDiskon * 100));
    }

    @Override
    public String toDataString() {
        return "Diskon|" + super.getNama() + "|" + persentaseDiskon;
    }
}