public class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String kategori, String jenisMakanan) {
        super(nama, harga, kategori);
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.printf("[Makanan - %s] %-15s : Rp %,.0f\n", jenisMakanan, super.getNama(), super.getHarga());
    }

    @Override
    public String toDataString() {
        return "Makanan|" + super.getNama() + "|" + super.getHarga() + "|" + super.getKategori() + "|" + jenisMakanan;
    }
}