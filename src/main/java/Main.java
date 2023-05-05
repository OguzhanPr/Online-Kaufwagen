import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class MenuItem {
    private String name;
    private double price;

    public MenuItem(){}

    public MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

    public class Main {

        public static void readInventoryFile(String fileName, ArrayList<MenuItem> items) {
            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String line;    //inventory(hardware.txt) deki satir
                while ((line = br.readLine()) != null) {
                    //example : mause, 35.99

                    //String tokens[] = line.split(",");
                    //String name = tokens[0];
                    //double price = Double.parseDouble(tokens[1]);
                    String name = line.trim();
                    items.add(new MenuItem(name));
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error : Cannot read from file " + fileName);
            }
        }


        public static void displayMenu(ArrayList<MenuItem> items) {
            //Display menu
            System.out.println("M A I N   M E N U");
            System.out.println("=================");

            //Loop through options and items
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                System.out.printf("%d. %s\n", i + 1, item.getName());
            }
            // Print the Exit option
            System.out.printf("%d. Exit\n", items.size() + 1);
            System.out.println("==============");
        }

        public static void completeTransaction(int choise, ArrayList<MenuItem> items) {
            MenuItem mi = items.get(choise - 1);
            //System.out.printf("Here is your choise %s %.2f\n", mi.getName());

        }

        public static void main(String[] args) {

            //secim
            int choise;
            //MenuTest data
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            //hardware.txt okuma ve
            readInventoryFile("mainmenu.txt", menuItems);
            //cikis secenegi
            int EXIT = menuItems.size() + 1;
            //Keyboard object, bu input degerinin kontorlu icin ==> kütüphanemden
            Keyboard key = new Keyboard();

            //display menu
            displayMenu(menuItems);
            //Kullanicidan choise al
            choise = key.readInteger("Enter Choise : ", "Error : invalid input", 1, EXIT);
            //MenuTest Loop
            while (choise != EXIT) {

                //check choise value
                completeTransaction(choise, menuItems);

                if(choise==1){
                    Hardware hw = new Hardware();
                    hw.mainMenu();
                   if(choise==EXIT){
                       displayMenu(menuItems);
                   }
                } else if (choise==2) {
                    Software sw = new Software();
                    sw.mainMenu();
                } else if (choise==3) {
                    OnlineCourses oc = new OnlineCourses();
                    oc.mainMenu();
                } else if (choise == 4) {
                    Game gm = new Game();
                    gm.mainMenu();
                }else break;

                //mainMenu();
            }

            System.out.println("Goodbye, call again");
        }


    }
