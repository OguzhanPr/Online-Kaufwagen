import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Software {

    private DataBase database = new DataBase();
    private String name;
    private double price;

    //cons
    public Software(String name, double price) {
        this.name = name;
        this.price = price;
    }
    //default const
    public Software(){
    }

    //getter
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    //from my Library for file to read
    public void readInventoryFile(String fileName, ArrayList<Software> softwares) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;    //inventory(courses.txt) deki satir
            while ((line = br.readLine()) != null) {
                //example : name, price
                String tokens[] = line.split(",");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                softwares.add(new Software(name, price));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error : Cannot read from file " + fileName);
        }
    }
    //display menu bu method Kütüphanemden text classdan copy
    public void displayMenu(ArrayList<Software> softwares) {
        //display menu
        System.out.println("Software MenuTest");
        System.out.println("=============");
        //name ve prive arasinda gecis
        for (int i = 0; i < softwares.size(); i++) {
            Software sw = softwares.get(i);  //
            System.out.printf("%d. %-25s %.2f\n", i + 1, sw.getName(), sw.getPrice());
        }
        //for dongusu bittikten sonra cikis secenegini yazdiracagim
        System.out.printf("%d. EXIT\n", softwares.size() + 1);
    }
    public void completeTransaction(int choise, ArrayList<Software>softwares){
        Software sw = softwares.get(choise-1);
        System.out.printf("Here is your choise %s %.2f\n", sw.name, sw.price);
        database.addSoftware(sw);
    }

    public void mainMenu(){
        int choise;
        //menu data
        ArrayList<Software> softwares = new ArrayList<>();
        //software.txt okuma
        readInventoryFile("software.txt", softwares);
        int EXIT = softwares.size()+1;
        //keyboard class object
        Keyboard key = new Keyboard();
        //display menu
        displayMenu(softwares);
        //get choise, infut from users
        choise = key.readInteger("Enter choise : ", "Error : invalid input " , 1, EXIT);
        //menu loop
        while(choise!=EXIT){
            completeTransaction(choise, softwares);
            mainMenu();
        }
        if(choise==EXIT){
            database.printSoftware();
            System.exit(0);
        }
        System.out.println("Googbye, call again");
    }
}
