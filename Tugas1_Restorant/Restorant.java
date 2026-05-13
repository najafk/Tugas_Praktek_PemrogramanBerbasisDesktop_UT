import java.util.Scanner;

class Menu {

    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Restorant { ///Mainnya cuy
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            // Inisialisasi Data Menu dalam Array (Minimal 4 tiap kategori)
            Menu[] daftarMenu = {
                new Menu("Nasi Padang", 25000, "makanan"),
                new Menu("Ayam Bakar", 30000, "makanan"),
                new Menu("Sate Ayam", 20000, "makanan"),
                new Menu("Mie Goreng", 15000, "makanan"),
                new Menu("Es Teh", 5000, "minuman"),
                new Menu("Es Jeruk", 7000, "minuman"),
                new Menu("Kopi Hitam", 10000, "minuman"),
                new Menu("Jus Alpukat", 15000, "minuman")
            };

            // Menampilkan Menu (Manual tanpa loop)
            System.out.println("=== DAFTAR MENU RESTORAN ===");
            System.out.println("[MAKANAN]");
            System.out.println("1. " + daftarMenu[0].nama + " - Rp" + (int) daftarMenu[0].harga);
            System.out.println("2. " + daftarMenu[1].nama + " - Rp" + (int) daftarMenu[1].harga);
            System.out.println("3. " + daftarMenu[2].nama + " - Rp" + (int) daftarMenu[2].harga);
            System.out.println("4. " + daftarMenu[3].nama + " - Rp" + (int) daftarMenu[3].harga);
            System.out.println("[MINUMAN]");
            System.out.println("5. " + daftarMenu[4].nama + " - Rp" + (int) daftarMenu[4].harga);
            System.out.println("6. " + daftarMenu[5].nama + " - Rp" + (int) daftarMenu[5].harga);
            System.out.println("7. " + daftarMenu[6].nama + " - Rp" + (int) daftarMenu[6].harga);
            System.out.println("8. " + daftarMenu[7].nama + " - Rp" + (int) daftarMenu[7].harga);

            //SISTEM PEMESANAN MENU
            // Input Pesanan Maksimal 4 (Manual)
            System.out.println("\nMasukkan Pesanan (Format: Nama Menu = Jumlah)");
            System.out.print("Pesanan 1: "); String p1 = input.nextLine();
            System.out.print("Pesanan 2 (Opsional, tekan Enter jika tidak ada): "); String p2 = input.nextLine();
            System.out.print("Pesanan 3 (Opsional): "); String p3 = input.nextLine();
            System.out.print("Pesanan 4 (Opsional): "); String p4 = input.nextLine();

            // Variabel penampung total biaya dan detail pesanan
            double totalBiaya = 0;
            String detailPesanan = "";

            // Logika Pengolahan Pesanan 1 (Contoh pengolahan string sederhana)
            if (!p1.isEmpty()) {
                String[] bagian = p1.split(" = ");
                String namaPesan = bagian[0];
                int jml = Integer.parseInt(bagian[1]);
                
                // Cari harga (Manual match atau helper method)
                double harga = cariHarga(namaPesan, daftarMenu);
                totalBiaya += (harga * jml);
                detailPesanan += namaPesan + " x" + jml + " = Rp" + (int) (harga * jml) + "\n";
            }
            if (!p2.isEmpty()) {
                String[] bagian = p2.split(" = ");
                String namaPesan = bagian[0];
                int jml = Integer.parseInt(bagian[1]);
                double harga = cariHarga(namaPesan, daftarMenu);
                totalBiaya += (harga * jml);
                detailPesanan += namaPesan + " x" + jml + " = Rp" + (int) (harga * jml) + "\n";
            }
            if (!p3.isEmpty()) {
                String[] bagian = p3.split(" = ");
                String namaPesan = bagian[0];
                int jml = Integer.parseInt(bagian[1]);
                double harga = cariHarga(namaPesan, daftarMenu);
                totalBiaya += (harga * jml);
                detailPesanan += namaPesan + " x" + jml + " = Rp" + (int) (harga * jml) + "\n";
            }
            if (!p4.isEmpty()) {
                String[] bagian = p4.split(" = ");
                String namaPesan = bagian[0];
                int jml = Integer.parseInt(bagian[1]);
                double harga = cariHarga(namaPesan, daftarMenu);
                totalBiaya += (harga * jml);
                detailPesanan += namaPesan + " x" + jml + " = Rp" + (int) (harga * jml) + "\n";
            }

            // --- PERHITUNGAN TAMBAHAN & DISKON ---
            double biayaPajak = totalBiaya * 0.10;
            double biayaPelayanan = 20000;
            double diskon = 0;
            String infoPromo = "";

            // Skenario Diskon 10% (> 100rb)
            if (totalBiaya > 100000) {
                diskon = totalBiaya * 0.10;
            }

            // Skenario Buy 1 Get 1 Minuman (> 50rb)
            if (totalBiaya > 50000) {
                infoPromo += "Promo: Beli 1 Gratis 1 Minuman Terpakai!\n";
            }

            double totalAkhir = totalBiaya + biayaPajak + biayaPelayanan - diskon;

            // --- CETAK STRUK ---
            System.out.println("\n========== STRUK PESANAN ==========");
            System.out.print(detailPesanan);
            System.out.println("Total Pesanan   : Rp" + totalBiaya);
            System.out.println("Pajak (10%)     : Rp" + biayaPajak);
            System.out.println("Biaya Pelayanan : Rp20.000");
            if (diskon > 0) System.out.println("Diskon (10%)    : -Rp" + diskon);
            System.out.print(infoPromo);
            System.out.println("-----------------------------------");
            System.out.println("TOTAL BAYAR     : Rp" + totalAkhir);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter in the format: Nama Menu = Jumlah");
        }
    }

    // Helper method untuk mencari harga tanpa loop (Gunakan Switch/If)
    private static double cariHarga(String nama, Menu[] daftar) {
        if (nama.equalsIgnoreCase(daftar[0].nama)) return daftar[0].harga;
        if (nama.equalsIgnoreCase(daftar[1].nama)) return daftar[1].harga;
        if (nama.equalsIgnoreCase(daftar[2].nama)) return daftar[2].harga;
        if (nama.equalsIgnoreCase(daftar[3].nama)) return daftar[3].harga;
        if (nama.equalsIgnoreCase(daftar[4].nama)) return daftar[4].harga;
        if (nama.equalsIgnoreCase(daftar[5].nama)) return daftar[5].harga;
        if (nama.equalsIgnoreCase(daftar[6].nama)) return daftar[6].harga;
        if (nama.equalsIgnoreCase(daftar[7].nama)) return daftar[7].harga;
        return 0;
    }
}


