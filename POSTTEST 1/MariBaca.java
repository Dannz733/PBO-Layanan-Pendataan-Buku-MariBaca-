import java.util.ArrayList;
import java.util.Scanner;

class Buku {
    String judul;
    String penulis;
    boolean tersedia;

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
        this.tersedia = true;
    }
}

public class MariBaca {
    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Layanan Peminjaman Buku Novel MariBaca ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Perbarui Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
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
                    System.out.println("Terima kasih telah menggunakan layanan MariBaca!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    static void tambahBuku() {
        System.out.print("Masukkan judul buku: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        String penulis = scanner.nextLine();
        daftarBuku.add(new Buku(judul, penulis));
        System.out.println("Buku berhasil ditambahkan!");
    }

    static void tampilkanBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku di daftar.");
            return;
        }
        System.out.println("\nDaftar Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            Buku buku = daftarBuku.get(i);
            System.out.println((i + 1) + ". " + buku.judul + " - " + buku.penulis + " (" + (buku.tersedia ? "Tersedia" : "Dipinjam") + ")");
        }
    }

    static void perbaruiBuku() {
        tampilkanBuku();
        if (daftarBuku.isEmpty()) return;

        System.out.print("Masukkan nomor buku yang ingin diperbarui: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index < 0 || index >= daftarBuku.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }
        System.out.print("Masukkan judul baru: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis baru: ");
        String penulis = scanner.nextLine();
        daftarBuku.get(index).judul = judul;
        daftarBuku.get(index).penulis = penulis;
        System.out.println("Buku berhasil diperbarui!");
    }

    static void hapusBuku() {
        tampilkanBuku();
        if (daftarBuku.isEmpty()) return;

        System.out.print("Masukkan nomor buku yang ingin dihapus: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index < 0 || index >= daftarBuku.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }
        daftarBuku.remove(index);
        System.out.println("Buku berhasil dihapus!");
    }
}
