import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Hardware {

    private DataBase dataBase2= new DataBase();
    private String name;
    private double price;

    //getter
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    //constructor
    public Hardware() {
    }

    public Hardware(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void readInventoryFile(String fileName, ArrayList<Hardware> hardwares) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;    //inventory(hardware.txt) deki satir
            while ((line = br.readLine()) != null) {
                //example : mause, 35.99
                String tokens[] = line.split(",");
                String name = tokens[0];
                double price = Double.parseDouble(tokens[1]);
                hardwares.add(new Hardware(name, price));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error : Cannot read from file " + fileName);
        }
    }

    //display menu bu method Kütüphanemden text classdan copy
    public void displayMenu(ArrayList<Hardware> hardwares) {
        //display menu
        System.out.println("Hardware MenuTest");
        System.out.println("=============");

        //name ve prive arasinda gecis
        for (int i = 0; i < hardwares.size(); i++) {
            Hardware hw = hardwares.get(i);  //
            System.out.printf("%d. %-25s %.2f\n", i + 1, hw.getName(), hw.getPrice());
        }
        //for dongusu bittikten sonra cikis secenegini yazdiracagim
        System.out.printf("%d. EXIT\n", hardwares.size() + 1);
    }


    public void completeTransaction(int choise, ArrayList<Hardware> hardwares) {
        Hardware hw = hardwares.get(choise - 1);
        System.out.printf("Here is your choise %s %.2f\n", hw.name, hw.price);
        dataBase2.addHardware(hw);
    }

   /* public void getTotalPrice(ArrayList<Hardware> hardwares) {
        Map<String, Integer> mapData = new HashMap<>();
        for (int i = 0; i < hardwares.size(); i++) {
            Hardware hardware = hardwares.get(i);
            int choice = 0;
            if (hardware.getName().equals(hardwares.get(choice - 1).getName())) {
                String name = hardware.getName();
                if (mapData.containsKey(name)) {
                    mapData.put(name, mapData.get(name) + 1);
                } else {
                    mapData.put(name, 1);
                }
            }
        }
        System.out.println(mapData);
    }*/

    public void mainMenu () {
            //secim
            int choise;
            //MenuTest data
            ArrayList<Hardware> hardwares = new ArrayList<>();
            //hardware.txt okuma ve
            readInventoryFile("hardware.txt", hardwares);
            //cikis secenegi
            int EXIT = hardwares.size() + 1;
            //Keyboard object, bu input degerinin kontorlu icin ==> kütüphanemden
            Keyboard key = new Keyboard();
            //display menu
            displayMenu(hardwares);
            //Kullanicidan choise al
            choise = key.readInteger("Enter Choise : ", "Error : invalid input", 1, EXIT);
            //MenuTest Loop
            while (choise != EXIT) {

                //check choise value
                completeTransaction(choise, hardwares);
                mainMenu();
            }
            if(choise==EXIT){
                dataBase2.printHardware();
                System.exit(0);

            }
            System.out.println("Goodbye, call again");

    }
}