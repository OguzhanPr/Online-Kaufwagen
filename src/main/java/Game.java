import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    private DataBase database = new DataBase();
    private String name;
    private double price;

    //cons
    public Game(String name, double price) {
        this.name = name;
        this.price = price;
    }
    //default constructor
    public Game() {}

    //setter and getter
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public void readInventoryFile(String fileName, ArrayList<Game> games){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null){
                //example:
                String tokens[]= line.split(",");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                games.add(new Game(name, price));
            }
            br.close();
        }
        catch (IOException e){
            System.out.println("Error : Cannot read from file " + fileName);
        }
    }
    //display menu bu method Kütüphanemden text classdan copy
    public void displayMenu(ArrayList<Game> games) {
        //display menu
        System.out.println("Game MenuTest");
        System.out.println("=============");
        //name ve prive arasinda gecis
        for (int i = 0; i < games.size(); i++) {
            Game gm = games.get(i);  //
            System.out.printf("%d. %-25s %.2f\n", i + 1, gm.getName(), gm.getPrice());
        }
        //for dongusu bittikten sonra cikis secenegini yazdiracagim
        System.out.printf("%d. EXIT\n", games.size() + 1);
    }

    public void completeTransaction(int choise, ArrayList<Game>games){
        Game gm = games.get(choise-1);
        System.out.printf("Here is your choise %s %.2f\n", gm.name, gm.price);
        database.addGame(gm);


    }

    public void mainMenu(){
        int choise;
        //menu data
        ArrayList<Game> games = new ArrayList<>();
        //software.txt okuma
        readInventoryFile("software.txt", games);
        int EXIT = games.size()+1;
        //keyboard class object
        Keyboard key = new Keyboard();
        //display menu
        displayMenu(games);
        //get choise, infut from users
        choise = key.readInteger("Enter choise : ", "Error : invalid input " , 1, EXIT);
        //menu loop
        while(choise!=EXIT){
            completeTransaction(choise, games);
            mainMenu();
        }
        if(choise==EXIT){
            database.printGames();
            System.exit(0);

        }
        System.out.println("Googbye, call again");
    }
}
