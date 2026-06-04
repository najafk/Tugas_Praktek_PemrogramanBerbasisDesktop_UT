import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu sistemMenu = new Menu();
        Pesanan sistemPesanan = new Pesanan();

        sistemMenu.muatMenuDariFile();

        boolean jalan = true;
        while (jalan) {
            System.out.println("\n=== SISTEM MANAJEMEN DJUGO ===");
            System.out.println("1. Tambah Item Menu");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Buat Pesanan");
            System.out.println("4. Cetak Struk & Bayar");
            System.out.println("5. Keluar (Simpan Data)");
            System.out.print("Pilih (1-5): ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.print("Tipe (1: Makanan, 2: Minuman, 3: Diskon): ");
                    String tipe = scanner.nextLine();
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();

                    if (tipe.equals("3")) {
                        System.out.print("Persentase Diskon (contoh: 0.1 untuk 10%): ");
                        double diskon = Double.parseDouble(scanner.nextLine());
                        sistemMenu.tambahItem(new Diskon(nama, diskon));
                    } else {
                        System.out.print("Harga: ");
                        double harga = Double.parseDouble(scanner.nextLine());
                        if (tipe.equals("1")) {
                            System.out.print("Jenis Makanan (Goreng/Kuah/Cemilan): ");
                            String jenis = scanner.nextLine();
                            sistemMenu.tambahItem(new Makanan(nama, harga, "Makanan", jenis));
                        } else if (tipe.equals("2")) {
                            System.out.print("Jenis Minuman (Panas/Dingin): ");
                            String jenis = scanner.nextLine();
                            sistemMenu.tambahItem(new Minuman(nama, harga, "Minuman", jenis));
                        }
                    }
                    System.out.println("Item berhasil ditambahkan.");
                    break;

                case "2":
                    sistemMenu.tampilkanSemua();
                    break;

                case "3":
                    sistemMenu.tampilkanSemua();
                    System.out.print("\nMasukkan nomor menu yang dipesan (0 untuk batal): ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index == -1) break;
                        
                        MenuItem itemDipilih = sistemMenu.cariItem(index);
                        // Perhatikan: item dibungkus ke dalam PesananItem
                        sistemPesanan.tambahPesanan(new PesananItem(itemDipilih));
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Input harus berupa angka!");
                    } catch (ItemNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    sistemPesanan.cetakDanSimpanStruk();
                    break;

                case "5":
                    sistemMenu.simpanMenuKeFile();
                    jalan = false;
                    System.out.println("Sistem dimatikan. Data telah disimpan.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}