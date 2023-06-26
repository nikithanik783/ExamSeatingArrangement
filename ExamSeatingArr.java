import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Student {
    private String name;
    private int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

class Seat {
    private Student student;

    public boolean isOccupied() {
        return student != null;
    }

    public void assignStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}

class SeatingArrangementSystem {
    private int rows;
    private int seatsPerRow;
    private Seat[][] seatingArrangement;

    public SeatingArrangementSystem(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seatingArrangement = new Seat[rows][seatsPerRow];
        initializeSeats();
    }

    private void initializeSeats() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingArrangement[i][j] = new Seat();
            }
        }
    }

    public boolean assignStudentToSeat(Student student) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = seatingArrangement[i][j];
                if (!seat.isOccupied()) {
                    seat.assignStudent(student);
                    return true;
                }
            }
        }
        return false; // No available seats
    }

    public void printSeatingArrangement() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = seatingArrangement[i][j];
                if (seat.isOccupied()) {
                    System.out.printf("Row %d, Seat %d: %s (Roll Number: %d)%n",
                            i + 1, j + 1, seat.getStudent().getName(), seat.getStudent().getRollNumber());
                } else {
                    System.out.printf("Row %d, Seat %d: Unoccupied%n", i + 1, j + 1);
                }
            }
        }
    }
}

public class ExamSeatingArr {
    public static void main(String[] args) {
        SeatingArrangementSystem seatingSystem = new SeatingArrangementSystem(5, 8); // Example: 5 rows, 8 seats per row

        List<Student> students = generateStudents(40); // Example: Generating 40 students

        for (Student student : students) {
            boolean assigned = seatingSystem.assignStudentToSeat(student);
            if (!assigned) {
                System.out.println("No available seats for student: " + student.getName());
            }
        }

        seatingSystem.printSeatingArrangement();
    }

    private static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            String name = "Student " + i;
            int rollNumber = 1000 + random.nextInt(9000); // Generate a 4-digit random roll number
            students.add(new Student(name, rollNumber));
        }

        return students;
    }
}
