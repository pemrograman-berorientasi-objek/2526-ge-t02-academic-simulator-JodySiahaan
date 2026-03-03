package academic.driver; // Nama paket untuk Driver1

import java.util.ArrayList;
import java.util.Scanner;

public class Driver1 {

    // Nested static class untuk merepresentasikan Course
    // Ini adalah cara untuk menempatkan academic.model.Course dalam satu file dengan Driver1
    public static class Course {
        private String code;
        private String name;
        private int sks;
        private String grade;

        public Course(String code, String name, int sks, String grade) {
            this.code = code;
            this.name = name;
            this.sks = sks;
            this.grade = grade;
        }

        // Metode untuk mendapatkan representasi string sesuai format output
        @Override
        public String toString() {
            return code + "|" + name + "|" + sks + "|" + grade;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Menggunakan ArrayList untuk menyimpan Course, sesuai dengan "array sebagai media penyimpanan"
        // yang bersifat dinamis karena jumlah input tidak diketahui sebelumnya.
        ArrayList<Course> courses = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break; // Hentikan pembacaan jika bertemu '---'
            }

            // Pisahkan segmen data course
            String[] segments = line.split("#");

            // Pastikan ada 4 segmen sebelum membuat objek Course
            if (segments.length == 4) {
                try {
                    String code = segments[0];
                    String name = segments[1];
                    int sks = Integer.parseInt(segments[2]); // Konversi SKS ke integer
                    String grade = segments[3];

                    // Buat objek Course dan tambahkan ke ArrayList
                    Course course = new Course(code, name, sks, grade);
                    courses.add(course);
                } catch (NumberFormatException e) {
                    System.err.println("Peringatan: Gagal memparsing SKS untuk baris: " + line + ". Baris ini akan dilewati.");
                }
            } else {
                System.err.println("Peringatan: Format input tidak valid untuk baris: " + line + ". Baris ini akan dilewati.");
            }
        }

        scanner.close(); // Tutup scanner untuk menghindari resource leak

        // Tampilkan semua courses yang tersimpan
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}