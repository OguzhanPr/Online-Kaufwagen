import java.util.ArrayList;

public class DataBase {


    private ArrayList<Game> games = new ArrayList<>();
    private ArrayList<OnlineCourses> courses = new ArrayList<>();
    private ArrayList<Hardware> hardwareList = new ArrayList<>();
    private ArrayList<Software> softwareList = new ArrayList<>();

    // Games
    public void addGame(Game game) {
        games.add(game);
    }

    public void printGames() {
        if (games.size() > 0) {
            System.out.println("Games:");
            double totalPrice = 0;
            for (Game game : games) {
                System.out.printf("%s - $%.2f\n", game.getName(), game.getPrice());
                totalPrice += game.getPrice();
            }
            System.out.printf("Total Price: $%.2f\n", totalPrice);
        } else {
            System.out.println("No games available.");
        }
    }

    // Courses
    public void addCourse(OnlineCourses course) {
        courses.add(course);
    }

    public void printCourses() {
        if (courses.size() > 0) {
            System.out.println("Courses:");
            double totalPrice = 0;
            for (OnlineCourses course : courses) {
                System.out.printf("%s - $%.2f\n", course.getName(), course.getPrice());
                totalPrice += course.getPrice();
            }
            System.out.printf("Total Price: $%.2f\n", totalPrice);
        } else {
            System.out.println("No courses available.");
        }
    }

    // Hardware
    public void addHardware(Hardware hardware) {
        hardwareList.add(hardware);
    }


    public void printHardware() {
        if (hardwareList.size() > 0) {
            System.out.println("Hardware:");
            double totalPrice = 0;
            for (Hardware hardware : hardwareList) {
                System.out.printf("%s - $%.2f\n", hardware.getName(), hardware.getPrice());
                totalPrice += hardware.getPrice();
            }
            System.out.printf("Total Price: $%.2f\n", totalPrice);
        } else {
            System.out.println("No hardware available.");
        }
    }

    // Software
    public void addSoftware(Software software) {
        softwareList.add(software);
    }

    public void printSoftware() {
        if (softwareList.size() > 0) {
            System.out.println("Software:");
            double totalPrice = 0;
            for (Software software : softwareList) {
                System.out.printf("%s - $%.2f\n", software.getName(), software.getPrice());
                totalPrice += software.getPrice();
            }
            System.out.printf("Total Price: $%.2f\n", totalPrice);
        } else {
            System.out.println("No software available.");
        }
    }

}
