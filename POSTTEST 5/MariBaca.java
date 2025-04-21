import java.util.ArrayList;
import java.util.Scanner;

// Abstract class sebagai parent
abstract class Buku {
    protected String judul;
    protected String penulis;
    protected boolean tersedia;
    protected final String kodeUnik = "MB123"; // final attribute

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
        this.tersedia = true;
    }

    public abstract String getInfo(); // abstract method

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
}

// Inheritance dari abstract class Buku
class BukuFisik extends Buku {
    private String lokasiRak;

    public BukuFisik(String judul, String penulis, String lokasiRak) {
        super(judul, penulis);
        this.lokasiRak = lokasiRak;
    }

    @Override
    public String getInfo() {
        return judul + " - " + penulis + " [Fisik | Rak: " + lokasiRak + "] " + (tersedia ? "(Tersedia)" : "(Dipinjam)");
    }
}

// Final class
final class BukuDigital extends Buku {
    private double ukuranFileMB;

    public BukuDigital(String judul, String penulis, double ukuranFileMB) {
        super(judul, penulis);
        this.ukuranFileMB = ukuranFileMB;
    }

    @Override
    public String getInfo() {
        return judul + " - " + penulis + " [Digital | " + ukuranFileMB + " MB] " + (tersedia ? "(Tersedia)" : "(Dipinjam)");
    }
}

public class MariBaca {
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Layanan Pendataan Buku 'MariBaca' ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Perbarui Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // buang newline

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

    // Final method
    private static final void tampilkanBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku di daftar.");
            return;
        }
        System.out.println("\nDaftar Buku:");
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println((i + 1) + ". " + daftarBuku.get(i).getInfo());
        }
    }

    // Overloaded methods (polymorphism)
    private static void tambahBuku(String judul, String penulis, String lokasiRak) {
        daftarBuku.add(new BukuFisik(judul, penulis, lokasiRak));
        System.out.println("Buku fisik berhasil ditambahkan!");
    }

    private static void tambahBuku(String judul, String penulis, double ukuranFileMB) {
        daftarBuku.add(new BukuDigital(judul, penulis, ukuranFileMB));
        System.out.println("Buku digital berhasil ditambahkan!");
    }

    private static void tambahBuku() {
        System.out.println("Pilih jenis buku:");
        System.out.println("1. Buku Fisik");
        System.out.println("2. Buku Digital");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine(); // buang newline

        System.out.print("Masukkan judul: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis: ");
        String penulis = scanner.nextLine();

        if (jenis == 1) {
            System.out.print("Masukkan lokasi rak: ");
            String lokasi = scanner.nextLine();
            tambahBuku(judul, penulis, lokasi);
        } else if (jenis == 2) {
            System.out.print("Masukkan ukuran file (MB): ");
            double ukuran = scanner.nextDouble();
            scanner.nextLine();
            tambahBuku(judul, penulis, ukuran);
        } else {
            System.out.println("Jenis buku tidak valid.");
        }
    }

    private static void perbaruiBuku() {
        tampilkanBuku();
        if (daftarBuku.isEmpty()) return;

        System.out.print("Masukkan nomor buku yang ingin diperbarui: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= daftarBuku.size()) {
            System.out.println("Nomor tidak valid.");
            return;
        }

        Buku buku = daftarBuku.get(index);

        System.out.print("Masukkan judul baru: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan penulis baru: ");
        String penulis = scanner.nextLine();

        buku.setJudul(judul);
        buku.setPenulis(penulis);

        System.out.println("Buku berhasil diperbarui!");
    }

    private static void hapusBuku() {
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
