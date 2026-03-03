package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver2 {

    // Nested static class untuk merepresentasikan Student
    // Ini adalah cara untuk menempatkan academic.model.Student dalam satu file dengan Driver2
    public static class Student {
        private String id;
        private String name;
        private int year;
        private String major;

        public Student(String id, String name, int year, String major) {
            this.id = id;
            this.name = name;
            this.year = year;
            this.major = major;
        }

        // Metode untuk mendapatkan representasi string sesuai format output
        @Override
        public String toString() {
            return id + "|" + name + "|" + year + "|" + major;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Menggunakan ArrayList untuk menyimpan Student, sesuai dengan "array sebagai media penyimpanan"
        // yang bersifat dinamis karena jumlah input tidak diketahui sebelumnya.
        ArrayList<Student> students = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break; // Hentikan pembacaan jika bertemu '---'
            }

            // Pisahkan segmen data student
            String[] segments = line.split("#");

            // Pastikan ada 4 segmen sebelum membuat objek Student
            if (segments.length == 4) {
                try {
                    String id = segments[0];
                    String name = segments[1];
                    int year = Integer.parseInt(segments[2]); // Konversi tahun ke integer
                    String major = segments[3];

                    // Buat objek Student dan tambahkan ke ArrayList
                    Student student = new Student(id, name, year, major);
                    students.add(student);
                } catch (NumberFormatException e) {
                    System.err.println("Peringatan: Gagal memparsing tahun untuk baris: " + line + ". Baris ini akan dilewati.");
                }
            } else {
                System.err.println("Peringatan: Format input tidak valid untuk baris: " + line + ". Baris ini akan dilewati.");
            }
        }

        scanner.close(); // Tutup scanner untuk menghindari resource leak

        // Tampilkan semua students yang tersimpan
        for (Student student : students) {
            System.out.println(student);
        }
    }
}