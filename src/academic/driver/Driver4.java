package academic.driver; // Nama paket untuk Driver4

import java.util.ArrayList;
import java.util.Scanner;

public class Driver4 {

    // Nested static class untuk merepresentasikan Course
    public static class Course {
        private String code;
        private String name;
        private int sks;
        private String grade; // Ini mungkin perlu disesuaikan jika Course tidak punya grade sendiri

        public Course(String code, String name, int sks, String grade) {
            this.code = code;
            this.name = name;
            this.sks = sks;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return code + "|" + name + "|" + sks + "|" + grade;
        }
    }

    // Nested static class untuk merepresentasikan Student
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

        @Override
        public String toString() {
            return id + "|" + name + "|" + year + "|" + major;
        }
    }

    // Nested static class untuk merepresentasikan Enrollment
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

        @Override
        public String toString() {
            return courseCode + "|" + studentId + "|" + academicYear + "|" + semester + "|" + grade;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("---")) {
                break; // Hentikan pembacaan jika bertemu '---'
            }

            String[] segments = line.split("#");

            if (segments.length < 1) { // Minimal harus ada perintah (e.g., "course-add")
                System.err.println("Peringatan: Baris input kosong atau tidak valid: " + line);
                continue;
            }

            String command = segments[0];

            switch (command) {
                case "course-add":
                    if (segments.length == 5) { // Command + 4 data Course
                        try {
                            String code = segments[1];
                            String name = segments[2];
                            int sks = Integer.parseInt(segments[3]);
                            String grade = segments[4]; // Mengikuti struktur Course dari Task 01
                            Course course = new Course(code, name, sks, grade);
                            courses.add(course);
                        } catch (NumberFormatException e) {
                            System.err.println("Peringatan: Gagal memparsing SKS untuk baris course-add: " + line + ". Baris ini akan dilewati.");
                        }
                    } else {
                        System.err.println("Peringatan: Format input tidak valid untuk course-add: " + line + ". Baris ini akan dilewati.");
                    }
                    break;
                case "student-add":
                    if (segments.length == 5) { // Command + 4 data Student
                        try {
                            String id = segments[1];
                            String name = segments[2];
                            int year = Integer.parseInt(segments[3]);
                            String major = segments[4];
                            Student student = new Student(id, name, year, major);
                            students.add(student);
                        } catch (NumberFormatException e) {
                            System.err.println("Peringatan: Gagal memparsing tahun untuk baris student-add: " + line + ". Baris ini akan dilewati.");
                        }
                    } else {
                        System.err.println("Peringatan: Format input tidak valid untuk student-add: " + line + ". Baris ini akan dilewati.");
                    }
                    break;
                case "enrollment-add":
                    if (segments.length == 5) { // Command + 4 data Enrollment
                        String courseCode = segments[1];
                        String studentId = segments[2];
                        String academicYear = segments[3];
                        String semester = segments[4];
                        Enrollment enrollment = new Enrollment(courseCode, studentId, academicYear, semester);
                        enrollments.add(enrollment);
                    } else {
                        System.err.println("Peringatan: Format input tidak valid untuk enrollment-add: " + line + ". Baris ini akan dilewati.");
                    }
                    break;
                default:
                    System.err.println("Peringatan: Perintah tidak dikenal: " + command + " pada baris: " + line + ". Baris ini akan dilewati.");
                    break;
            }
        }

        scanner.close(); // Tutup scanner untuk menghindari resource leak

        // Tampilkan semua courses
        for (Course course : courses) {
            System.out.println(course);
        }
        // Tampilkan semua students
        for (Student student : students) {
            System.out.println(student);
        }
        // Tampilkan semua enrollments
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }
}