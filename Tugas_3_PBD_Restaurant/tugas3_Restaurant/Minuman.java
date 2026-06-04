public class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String kategori, String jenisMinuman) {
        super(nama, harga, kategori);
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.printf("[Minuman - %s] %-15s : Rp %,.0f\n", jenisMinuman, super.getNama(), super.getHarga());
    }

    @Override
    public String toDataString() {
        return "Minuman|" + super.getNama() + "|" + super.getHarga() + "|" + super.getKategori() + "|" + jenisMinuman;
    }
}