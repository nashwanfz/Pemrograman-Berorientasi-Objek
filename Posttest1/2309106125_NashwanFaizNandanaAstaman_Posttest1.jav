import java.util.ArrayList;
import java.util.Scanner;

class Buku {
    String id;
    String judul;
    String penulis;
    int stok;

    public Buku(String id, String judul, String penulis, int stok) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Judul: " + judul + ", Penulis: " + penulis + ", Stok: " + stok;
    }
}

public class InventarisBuku {
    private static final ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\nSistem Manajemen Inventaris Toko Buku ‘Baca Ceria’");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Perbarui Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahBuku();
                case 2 -> tampilkanBuku();
                case 3 -> perbaruiBuku();
                case 4 -> hapusBuku();
                case 5 -> System.out.println("Keluar dari program.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void tambahBuku() {
        System.out.print("Masukkan ID Buku: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan Penulis Buku: ");
        String penulis = scanner.nextLine();
        System.out.print("Masukkan Stok Buku: ");
        int stok = scanner.nextInt();
        scanner.nextLine();
        
        daftarBuku.add(new Buku(id, judul, penulis, stok));
        System.out.println("Buku berhasil ditambahkan!");
    }

    private static void tampilkanBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku dalam inventaris.");
        } else {
            for (Buku buku : daftarBuku) {
                System.out.println(buku);
            }
        }
    }

    private static void perbaruiBuku() {
        System.out.print("Masukkan ID Buku yang akan diperbarui: ");
        String id = scanner.nextLine();
        for (Buku buku : daftarBuku) {
            if (buku.id.equals(id)) {
                System.out.print("Masukkan Judul Baru: ");
                buku.judul = scanner.nextLine();
                System.out.print("Masukkan Penulis Baru: ");
                buku.penulis = scanner.nextLine();
                System.out.print("Masukkan Stok Baru: ");
                buku.stok = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Buku berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }

    private static void hapusBuku() {
        System.out.print("Masukkan ID Buku yang akan dihapus: ");
        String id = scanner.nextLine();
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).id.equals(id)) {
                daftarBuku.remove(i);
                System.out.println("Buku berhasil dihapus!");
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }
}
