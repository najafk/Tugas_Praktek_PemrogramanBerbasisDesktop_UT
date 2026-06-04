import java.io.*;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();
    private final String FILE_NAME = "data_menu.txt";

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanSemua() {
        System.out.println("\n--- DAFTAR MENU DJUGO SENTRA ---");
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong.");
            return;
        }
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarMenu.get(i).tampilMenu();
        }
    }

    public MenuItem cariItem(int index) throws ItemNotFoundException {
        if (index < 0 || index >= daftarMenu.size()) {
            throw new ItemNotFoundException("Error: Item pada nomor tersebut tidak ditemukan di menu!");
        }
        return daftarMenu.get(index);
    }

    public void simpanMenuKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (MenuItem item : daftarMenu) {
                writer.write(item.toDataString());
                writer.newLine();
            }
            System.out.println("Data menu berhasil disimpan ke " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file menu: " + e.getMessage());
        }
    }

    public void muatMenuDariFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        daftarMenu.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts[0].equals("Makanan")) {
                    daftarMenu.add(new Makanan(parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                } else if (parts[0].equals("Minuman")) {
                    daftarMenu.add(new Minuman(parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                } else if (parts[0].equals("Diskon")) {
                    daftarMenu.add(new Diskon(parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat file menu: " + e.getMessage());
        }
    }
}