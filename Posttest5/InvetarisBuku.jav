import java.util.ArrayList;
import java.util.Scanner;

// Abstract Superclass Buku
abstract class Buku {
    protected String id;
    protected final String judul; // final attribute
    protected String penulis;
    protected int stok;

    public Buku(String id, String judul, String penulis, int stok) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public final String getJudul() { // final method
        return judul;
    }

    public void setJudul(String judul) {
        System.out.println("Judul bersifat final dan tidak dapat diubah.");
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        } else {
            System.out.println("Stok tidak bisa negatif!");
        }
    }

    // Method Overloading
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        } else {
            System.out.println("Jumlah penambahan stok harus lebih dari 0.");
        }
    }

    // Overloaded method
    public void tambahStok(int jumlah, boolean tampilkanInfo) {
        tambahStok(jumlah);
        if (tampilkanInfo) {
            System.out.println("Stok setelah ditambahkan: " + stok);
        }
    }

    // Abstract method
    public abstract void tampilJenis();

    @Override
    public String toString() {
        return "ID: " + id + ", Judul: " + judul + ", Penulis: " + penulis + ", Stok: " + stok;
    }
}

// Subclass BukuFiksi
class BukuFiksi extends Buku {
    private String genre;

    public BukuFiksi(String id, String judul, String penulis, int stok, String genre) {
        super(id, judul, penulis, stok);
        this.genre = genre;
    }

    @Override
    public void tampilJenis() {
        System.out.println("Jenis Buku: Fiksi | Genre: " + genre);
    }

    @Override
    public String toString() {
        return super.toString() + ", Genre: " + genre;
    }
}

// Subclass BukuNonFiksi
class BukuNonFiksi extends Buku {
    private String kategori;

    public BukuNonFiksi(String id, String judul, String penulis, int stok, String kategori) {
        super(id, judul, penulis, stok);
        this.kategori = kategori;
    }

    @Override
    public void tampilJenis() {
        System.out.println("Jenis Buku: Non-Fiksi | Kategori: " + kategori);
    }

    @Override
    public String toString() {
        return super.toString() + ", Kategori: " + kategori;
    }
}

// Final class
public final class InventarisBuku {
    private static final ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\nSistem Manajemen Inventaris Toko Buku 'Baca Ceria'");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Perbarui Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Keluar");
            System.out.println("6. Tambah Stok Buku"); // Menu baru
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBuku();
                    break;
                case 2:
                    tampilkanBuku();
                    break;
                case 3:
                    perbaruiBuku();
                    break;
                case 4:
                    hapusBuku();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                case 6:
                    tambahStokBuku(); // Fungsi baru
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void tambahBuku() {
        System.out.println("Pilih jenis buku: ");
        System.out.println("1. Buku Fiksi");
        System.out.println("2. Buku Non-Fiksi");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan ID Buku: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Judul Buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan Penulis Buku: ");
        String penulis = scanner.nextLine();
        System.out.print("Masukkan Stok Buku: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        if (jenis == 1) {
            System.out.print("Masukkan Genre Buku: ");
            String genre = scanner.nextLine();
            daftarBuku.add(new BukuFiksi(id, judul, penulis, stok, genre));
        } else if (jenis == 2) {
            System.out.print("Masukkan Kategori Buku: ");
            String kategori = scanner.nextLine();
            daftarBuku.add(new BukuNonFiksi(id, judul, penulis, stok, kategori));
        } else {
            System.out.println("Pilihan tidak valid!");
            return;
        }

        System.out.println("Buku berhasil ditambahkan!");
    }

    private static void tampilkanBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku dalam inventaris.");
        } else {
            for (Buku buku : daftarBuku) {
                System.out.println(buku);
                buku.tampilJenis(); // menampilkan jenis sesuai abstract method
            }
        }
    }

    private static void perbaruiBuku() {
        System.out.print("Masukkan ID Buku yang akan diperbarui: ");
        String id = scanner.nextLine();
        for (Buku buku : daftarBuku) {
            if (buku.getId().equals(id)) {
                System.out.print("Masukkan Penulis Baru: ");
                buku.setPenulis(scanner.nextLine());
                System.out.print("Masukkan Stok Baru: ");
                buku.setStok(scanner.nextInt());
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
        boolean found = false;
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).getId().equals(id)) {
                daftarBuku.remove(i);
                System.out.println("Buku berhasil dihapus!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
        }
    }

    // Fungsi baru: overloading + menu tambah stok
    private static void tambahStokBuku() {
        System.out.print("Masukkan ID Buku: ");
        String id = scanner.nextLine();
        for (Buku buku : daftarBuku) {
            if (buku.getId().equals(id)) {
                System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                int jumlah = scanner.nextInt();
                scanner.nextLine();
                buku.tambahStok(jumlah, true); // panggil overloaded method
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }
}
