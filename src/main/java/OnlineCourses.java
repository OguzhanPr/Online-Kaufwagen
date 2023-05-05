import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OnlineCourses {

    private DataBase database = new DataBase();
    private String name;
    private double price;

    //cons
    public OnlineCourses(String name, double price) {
        this.name = name;
        this.price = price;
    }
    //default cons
    public OnlineCourses(){}

    //getter
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    //from my Library for file to read
    public void readInventoryFile(String fileName, ArrayList<OnlineCourses> courses) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;    //inventory(courses.txt) deki satir
            while ((line = br.readLine()) != null) {
                //example : name, price
                String tokens[] = line.split(",");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                courses.add(new OnlineCourses(name, price));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error : Cannot read from file " + fileName);
        }
    }

    //display menu bu method Kütüphanemden text classdan copy
    public void displayMenu(ArrayList<OnlineCourses> courses) {
        //display menu
        System.out.println("Online Courses MenuTest");
        System.out.println("=============");
        //name ve prive arasinda gecis
        for (int i = 0; i < courses.size(); i++) {
            OnlineCourses oc = courses.get(i);  //
            System.out.printf("%d. %-25s %.2f\n", i + 1, oc.getName(), oc.getPrice());
        }
        //for dongusu bittikten sonra cikis secenegini yazdiracagim
        System.out.printf("%d. EXIT\n", courses.size() + 1);
    }

    public void completeTransaction(int choise, ArrayList<OnlineCourses> courses) {
        OnlineCourses oc = courses.get(choise - 1);
        System.out.printf("Here is your choise %s %.2f\n", oc.name, oc.price);
        database.addCourse(oc);
    }

    public void mainMenu(){
        //secim
        int choise;
        //MenuTest data
        ArrayList<OnlineCourses> courses = new ArrayList<>();
        //courses.txt okuma
        readInventoryFile("courses.txt", courses);
        //cikis secenegi
        int EXIT = courses.size()+1;
        //keyboard class from my Library=> hazir aldigim keyboard objesi
        Keyboard key = new Keyboard();
        //display menu
        displayMenu(courses);
        //get choise,input from User
        choise= key.readInteger("Enter Choise : " , "Error : invalid input ", 1, EXIT);
        //menu loop
        while (choise!=EXIT){
            //check choise and print, yapilan secimden sonra chouse duzenleyen method
            completeTransaction(choise, courses);
            mainMenu();

            }
        if(choise==EXIT){
            database.printCourses();
            System.exit(0);

        }
        System.out.println("Googbye, call again");
    }
}
