package academic.driver; // Nama paket untuk Driver3

import java.util.ArrayList;
import java.util.Scanner;

public class Driver3 {

    // Nested static class untuk merepresentasikan Enrollment
    // Ini adalah cara untuk menempatkan academic.model.Enrollment dalam satu file dengan Driver3
    public static class Enrollment {
        private String courseCode;
        private String studentId;
        private String academicYear;
        private String semester;
        private String grade; // Default "None"

        public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
            this.courseCode = courseCode;
            this.studentId = studentId;
            this.academicYear = academicYear;
            this.semester = semester;
            this.grade = "None"; // Inisialisasi default grade sesuai output
        }

        // Metode untuk mendapatkan representasi string sesuai format output
        @Override
        public String toString() {
            return courseCode + "|" + studentId + "|" + academicYear + "|" + semester + "|" + grade;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Menggunakan ArrayList untuk menyimpan Enrollment, sesuai dengan "array sebagai media penyimpanan"
        // yang bersifat dinamis karena jumlah input tidak diketahui sebelumnya.
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break; // Hentikan pembacaan jika bertemu '---'
            }

            // Pisahkan segmen data enrollment
            String[] segments = line.split("#");

            // Pastikan ada 4 segmen sebelum membuat objek Enrollment
            if (segments.length == 4) {
                String courseCode = segments[0];
                String studentId = segments[1];
                String academicYear = segments[2];
                String semester = segments[3];

                // Buat objek Enrollment dan tambahkan ke ArrayList
                Enrollment enrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                enrollments.add(enrollment);
            } else {
                System.err.println("Peringatan: Format input tidak valid untuk baris: " + line + ". Baris ini akan dilewati.");
            }
        }

        scanner.close(); // Tutup scanner untuk menghindari resource leak

        // Tampilkan semua enrollments yang tersimpan
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}