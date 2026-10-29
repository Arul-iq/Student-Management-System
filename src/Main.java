import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Student Management System ===");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readInt("Choose an option: ")) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> findStudent();
                case 4 -> updateStudent();
                case 5 -> removeStudent();
                case 0 -> running = false;
                default -> System.out.println("Please choose a valid option.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n1. Add student\n2. View students\n3. Find student\n4. Update student\n5. Remove student\n0. Exit");
    }

    private static void addStudent() {
        int id = readInt("Student ID: ");
        if (getStudentById(id) != null) { System.out.println("A student with that ID already exists."); return; }
        students.add(new Student(id, readRequiredText("Name: "), readRequiredText("Department: "), readDouble("Marks: ")));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) { System.out.println("No student records found."); return; }
        System.out.println("\nID | Name | Department | Marks");
        System.out.println("----------------------------------------");
        students.forEach(System.out::println);
    }

    private static void findStudent() {
        Student student = getStudentById(readInt("Student ID: "));
        System.out.println(student == null ? "Student not found." : student);
    }

    private static void updateStudent() {
        Student student = getStudentById(readInt("Student ID: "));
        if (student == null) { System.out.println("Student not found."); return; }
        student.name = readRequiredText("New name: ");
        student.department = readRequiredText("New department: ");
        student.marks = readDouble("New marks: ");
        System.out.println("Student updated successfully.");
    }

    private static void removeStudent() {
        Student student = getStudentById(readInt("Student ID: "));
        if (student != null && students.remove(student)) System.out.println("Student removed successfully.");
        else System.out.println("Student not found.");
    }

    private static Student getStudentById(int id) { return students.stream().filter(student -> student.id == id).findFirst().orElse(null); }

    private static int readInt(String prompt) {
        while (true) { try { System.out.print(prompt); return Integer.parseInt(SCANNER.nextLine().trim()); } catch (NumberFormatException exception) { System.out.println("Enter a whole number."); } }
    }

    private static double readDouble(String prompt) {
        while (true) { try { System.out.print(prompt); return Double.parseDouble(SCANNER.nextLine().trim()); } catch (NumberFormatException exception) { System.out.println("Enter a valid number."); } }
    }

    private static String readRequiredText(String prompt) {
        while (true) { System.out.print(prompt); String value = SCANNER.nextLine().trim(); if (!value.isEmpty()) return value; System.out.println("This field cannot be empty."); }
    }

    private static class Student {
        private final int id; private String name; private String department; private double marks;
        private Student(int id, String name, String department, double marks) { this.id = id; this.name = name; this.department = department; this.marks = marks; }
        @Override public String toString() { return id + " | " + name + " | " + department + " | " + marks; }
    }
}
