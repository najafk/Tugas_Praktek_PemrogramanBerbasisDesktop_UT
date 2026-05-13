import java.util.Scanner;

// Kelas Menu sesuai instruksi
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

// Kelas tambahan untuk mencatat detail pesanan pelanggan
class Pesanan {
    Menu menu;
    int jumlah;

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }
}

public class STSI4201 {
    // Penggunaan Array standar sesuai permintaan soal.
    // Kita melakukan 'Array Resizing' manual untuk fitur tambah/hapus.
    static Menu[] daftarMenu = new Menu[8];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiMenu();
        boolean jalan = true;

        while (jalan) {
            System.out.println("\n=== SISTEM RESTORAN ===");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pemilik (Manajemen Menu)");
            System.out.println("3. Keluar");
            System.out.print("Pilih navigasi (1-3): ");
            
            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                menuPelanggan();
            } else if (pilihan.equals("2")) {
                menuPemilik();
            } else if (pilihan.equals("3")) {
                jalan = false;
                System.out.println("Sistem dimatikan.");
            } else {
                System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    // --- SETUP AWAL ---
    static void inisialisasiMenu() {
        daftarMenu[0] = new Menu("Nasi Goreng", 25000, "Makanan");
        daftarMenu[1] = new Menu("Ayam Geprek", 20000, "Makanan");
        daftarMenu[2] = new Menu("Mie Kuah Pedas", 18000, "Makanan");
        daftarMenu[3] = new Menu("Sate Ayam", 30000, "Makanan");
        daftarMenu[4] = new Menu("Es Teh Manis", 5000, "Minuman");
        daftarMenu[5] = new Menu("Kopi Hitam", 10000, "Minuman");
        daftarMenu[6] = new Menu("Jus Jeruk", 15000, "Minuman");
        daftarMenu[7] = new Menu("Air Mineral", 4000, "Minuman");
    }

    // --- FITUR PELANGGAN ---
    static void menuPelanggan() {
        Pesanan[] daftarPesanan = new Pesanan[100]; // Asumsi maksimal 100 jenis pesanan
        int jumlahPesanan = 0;

        System.out.println("\n--- DAFTAR MENU ---");
        tampilkanMenuSaja();

        System.out.println("\nKetik nama menu untuk memesan.");
        System.out.println("Ketik 'selesai' jika sudah selesai memesan.");

        while (true) {
            System.out.print("\nMasukkan pesanan Anda: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }

            Menu menuDipilih = cariMenu(input);
            if (menuDipilih == null) {
                System.out.println("Menu tidak ditemukan! Sistem meminta input kembali.");
                continue;
            }

            System.out.print("Masukkan jumlah pesanan untuk " + menuDipilih.nama + ": ");
            int qty;
            try {
                qty = Integer.parseInt(scanner.nextLine());
                if (qty <= 0) throw new Exception();
            } catch (Exception e) {
                System.out.println("Jumlah tidak valid. Pesanan dibatalkan, silakan input ulang menu.");
                continue;
            }

            // Simpan pesanan
            daftarPesanan[jumlahPesanan] = new Pesanan(menuDipilih, qty);
            jumlahPesanan++;
            System.out.println("Berhasil ditambahkan ke pesanan.");
        }

        if (jumlahPesanan > 0) {
            cetakStruk(daftarPesanan, jumlahPesanan);
        } else {
            System.out.println("Anda tidak memesan apa pun.");
        }
    }

    static void cetakStruk(Pesanan[] pesanan, int jumlahPesanan) {
        System.out.println("\n========================================");
        System.out.println("             STRUK PESANAN              ");
        System.out.println("========================================");

        double totalBiayaKotor = 0;
        boolean adaMinuman = false;
        String namaMinumanPromo = "";

        // Hitung total kotor dan cek apakah ada minuman untuk promo
        for (int i = 0; i < jumlahPesanan; i++) {
            Menu m = pesanan[i].menu;
            int q = pesanan[i].jumlah;
            double subtotalItem = m.harga * q;
            totalBiayaKotor += subtotalItem;

            System.out.printf("%-15s x%d   Rp %,.0f   Total: Rp %,.0f\n", m.nama, q, m.harga, subtotalItem);
            
            if (m.kategori.equalsIgnoreCase("Minuman") && !adaMinuman) {
                adaMinuman = true;
                namaMinumanPromo = m.nama;
            }
        }

        // Hitung Promo & Diskon (Skenario Keputusan)
        double diskon = 0;
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal              : Rp %,.0f\n", totalBiayaKotor);

        // Promo 1: Beli 1 Gratis 1 Minuman jika total > 50.000
        if (totalBiayaKotor > 50000 && adaMinuman) {
            System.out.println("[PROMO] Anda mendapat Beli 1 Gratis 1!");
            System.out.println("        Gratis 1x " + namaMinumanPromo);
        }

        // Promo 2: Diskon 10% jika total > 100.000
        if (totalBiayaKotor > 100000) {
            diskon = totalBiayaKotor * 0.10;
            System.out.printf("[PROMO] Diskon 10%%    : -Rp %,.0f\n", diskon);
        }

        double totalSetelahDiskon = totalBiayaKotor - diskon;
        double pajak = totalSetelahDiskon * 0.10; // Pajak 10% dari harga setelah diskon
        double biayaLayanan = 20000;

        double grandTotal = totalSetelahDiskon + pajak + biayaLayanan;

        System.out.printf("Pajak (10%%)           : Rp %,.0f\n", pajak);
        System.out.printf("Biaya Pelayanan       : Rp %,.0f\n", biayaLayanan);
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL BAYAR           : Rp %,.0f\n", grandTotal);
        System.out.println("========================================");
    }

    // --- FITUR PEMILIK (MANAJEMEN MENU) ---
    static void menuPemilik() {
        while (true) {
            System.out.println("\n--- MANAJEMEN MENU (PEMILIK) ---");
            System.out.println("1. Lihat Daftar Menu");
            System.out.println("2. Tambah Menu Baru");
            System.out.println("3. Ubah Harga Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi (1-5): ");
            
            String opsi = scanner.nextLine();

            if (opsi.equals("1")) {
                tampilkanLengkap();
            } else if (opsi.equals("2")) {
                tambahMenu();
            } else if (opsi.equals("3")) {
                ubahHarga();
            } else if (opsi.equals("4")) {
                hapusMenu();
            } else if (opsi.equals("5")) {
                break;
            } else {
                System.out.println("Input tidak valid. Sistem meminta input kembali.");
            }
        }
    }

    static void tambahMenu() {
        System.out.print("Masukkan jumlah menu baru yang ingin ditambah sekaligus: ");
        int jumlahTambah;
        try {
            jumlahTambah = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Angka tidak valid!");
            return;
        }

        for (int i = 0; i < jumlahTambah; i++) {
            System.out.println("\nData Menu Ke-" + (i + 1));
            System.out.print("Nama Menu   : ");
            String nama = scanner.nextLine();
            System.out.print("Harga       : ");
            double harga = Double.parseDouble(scanner.nextLine());
            System.out.print("Kategori (Makanan/Minuman): ");
            String kategori = scanner.nextLine();

            // Array Resizing: Membuat array baru dengan ukuran + 1
            Menu[] arrayBaru = new Menu[daftarMenu.length + 1];
            for (int j = 0; j < daftarMenu.length; j++) {
                arrayBaru[j] = daftarMenu[j];
            }
            arrayBaru[daftarMenu.length] = new Menu(nama, harga, kategori);
            daftarMenu = arrayBaru; // Pindahkan referensi

            System.out.println("Menu " + nama + " berhasil ditambahkan.");
        }
    }

    static void ubahHarga() {
        tampilkanLengkap();
        while (true) {
            System.out.print("\nMasukkan NOMOR menu yang harganya ingin diubah (atau '0' untuk batal): ");
            try {
                int nomor = Integer.parseInt(scanner.nextLine());
                if (nomor == 0) break;
                
                if (nomor < 1 || nomor > daftarMenu.length) {
                    System.out.println("Nomor di luar pilihan menu yang ada! Input kembali.");
                    continue;
                }

                Menu target = daftarMenu[nomor - 1];
                System.out.print("Masukkan harga baru untuk " + target.nama + ": ");
                double hargaBaru = Double.parseDouble(scanner.nextLine());

                System.out.print("Yakin ingin mengubah harga? (Ketik 'Ya' untuk konfirmasi): ");
                String konfirmasi = scanner.nextLine();
                if (konfirmasi.equalsIgnoreCase("Ya")) {
                    target.harga = hargaBaru;
                    System.out.println("Harga berhasil diubah.");
                    break;
                } else {
                    System.out.println("Perubahan dibatalkan.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Input salah. Harus berupa angka. Input kembali.");
            }
        }
    }

    static void hapusMenu() {
        tampilkanLengkap();
        while (true) {
            System.out.print("\nMasukkan NOMOR menu yang ingin dihapus (atau '0' untuk batal): ");
            try {
                int nomor = Integer.parseInt(scanner.nextLine());
                if (nomor == 0) break;
                
                if (nomor < 1 || nomor > daftarMenu.length) {
                    System.out.println("Nomor di luar pilihan menu yang ada! Input kembali.");
                    continue;
                }

                Menu target = daftarMenu[nomor - 1];
                System.out.print("Yakin ingin menghapus " + target.nama + "? (Ketik 'Ya' untuk konfirmasi): ");
                String konfirmasi = scanner.nextLine();
                
                if (konfirmasi.equalsIgnoreCase("Ya")) {
                    // Array Resizing: Membuat array baru dengan ukuran - 1
                    Menu[] arrayBaru = new Menu[daftarMenu.length - 1];
                    int indexBaru = 0;
                    for (int i = 0; i < daftarMenu.length; i++) {
                        if (i != (nomor - 1)) {
                            arrayBaru[indexBaru] = daftarMenu[i];
                            indexBaru++;
                        }
                    }
                    daftarMenu = arrayBaru; // Pindahkan referensi
                    System.out.println("Menu berhasil dihapus.");
                    break;
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Input salah. Harus berupa angka. Input kembali.");
            }
        }
    }

    // --- UTILITAS ---
    static Menu cariMenu(String namaInput) {
        for (Menu m : daftarMenu) {
            if (m.nama.equalsIgnoreCase(namaInput)) {
                return m;
            }
        }
        return null;
    }

    static void tampilkanMenuSaja() {
        System.out.println("[ MAKANAN ]");
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("Makanan")) {
                System.out.printf("- %-15s : Rp %,.0f\n", m.nama, m.harga);
            }
        }
        System.out.println("\n[ MINUMAN ]");
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("Minuman")) {
                System.out.printf("- %-15s : Rp %,.0f\n", m.nama, m.harga);
            }
        }
    }

    static void tampilkanLengkap() {
        System.out.println("\n[ DAFTAR SELURUH MENU BESERTA NOMOR INDEX ]");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.printf("%d. %-15s (%s) - Rp %,.0f\n", (i + 1), daftarMenu[i].nama, daftarMenu[i].kategori, daftarMenu[i].harga);
        }
    }
}