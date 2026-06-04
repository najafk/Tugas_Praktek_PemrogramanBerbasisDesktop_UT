import java.io.*;
import java.util.ArrayList;

public class Pesanan {
    private ArrayList<PesananItem> daftarPesanan = new ArrayList<>();
    private final String STRUK_FILE = "struk_pesanan.txt";

    public void tambahPesanan(PesananItem pesananItem) {
        daftarPesanan.add(pesananItem);
        System.out.println(pesananItem.getItem().getNama() + " ditambahkan ke pesanan.");
    }

    public void cetakDanSimpanStruk() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        double totalKotor = 0;
        double totalDiskonVoucher = 0;

        StringBuilder struk = new StringBuilder();
        struk.append("\n========================================\n");
        struk.append("          STRUK PESANAN DJUGO           \n");
        struk.append("========================================\n");

        for (PesananItem pItem : daftarPesanan) {
            MenuItem item = pItem.getItem();
            if (item instanceof Diskon) {
                totalDiskonVoucher += ((Diskon) item).getPersentaseDiskon();
                struk.append(String.format("Voucher : %s (%.0f%%)\n", item.getNama(), ((Diskon) item).getPersentaseDiskon() * 100));
            } else {
                totalKotor += item.getHarga();
                struk.append(String.format("%-20s : Rp %,.0f\n", item.getNama(), item.getHarga()));
            }
        }

        double nominalDiskon = totalKotor * totalDiskonVoucher;
        double totalBersih = totalKotor - nominalDiskon;

        struk.append("----------------------------------------\n");
        struk.append(String.format("Subtotal             : Rp %,.0f\n", totalKotor));
        if (totalDiskonVoucher > 0) {
            struk.append(String.format("Potongan Diskon      :-Rp %,.0f\n", nominalDiskon));
        }
        struk.append(String.format("TOTAL BAYAR          : Rp %,.0f\n", totalBersih));
        struk.append("========================================\n");

        System.out.println(struk.toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STRUK_FILE, true))) {
            writer.write(struk.toString());
            System.out.println("Struk berhasil dicetak dan disimpan ke " + STRUK_FILE);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
        
        daftarPesanan.clear();
    }
}