import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void dots(int i){
        int x = 0;
        try {
            while(x < i){
                x++;
                System.out.print(".");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        clearScreen();
        Wykonwawca<String> My_exec = new Wykonwawca(new FTask[10]);
        for (int i = 2 ; i < 8 ; i++){
            My_exec.execute(new FTask<String>(new Task(i * 5000, "main-" + i*5)));
        }
        Scanner sc = new Scanner(System.in);
        // My_exec.list();
        // System.out.println("Wait 3.5 sec...");
        // try {
        // Thread.sleep(3500);
        // } catch (InterruptedException e) {
        // Thread.currentThread().interrupt();
        // }
        // My_exec.list();
        // System.out.println("Wait 3.5 sec...");
        // try {
        // Thread.sleep(3500);
        // } catch (InterruptedException e) {
        // Thread.currentThread().interrupt();
        // }
        // My_exec.list();
        // My_exec.Terminate();
        int wybor = 1;
        int helper;
        boolean helper2;
        while (wybor != 5) {
            System.out.println("--------------------------------------");
            System.out.println("MENU");
            System.out.println("1. Lista zadań");
            System.out.println("2. Pobranie stanu zadania");
            System.out.println("3. Anulowanie zadania");
            System.out.println("4. Wynik zadania");
            System.out.println("5. Zamknij program");
            wybor = sc.nextInt();
            System.out.println("--------------------------------------");
            clearScreen();
            switch (wybor) {
                case 1: // lista
                    My_exec.list();
                    break;
                case 2: // pobranie stanu
                    My_exec.list();
                    System.out.println("--------------------------------------");
                    System.out.println("Podaj ID FTasku którego stan chcesz pobrać.");
                    helper = sc.nextInt();
                    System.out.println("--------------------------------------");
                    My_exec.list(helper);
                    break;
                case 3: // anulowanie
                    My_exec.list();
                    System.out.println("--------------------------------------");
                    System.out.println("Podaj ID FTasku którego chcesz usunąć.");
                    helper = sc.nextInt();
                    System.out.println("Czy ma przerwać taska podczać Działania ? [true/false]");
                    helper2=sc.nextBoolean();
                    System.out.println("--------------------------------------");
                    My_exec.CancelTask(helper, helper2);
                    break;
                case 4: // wynik
                    My_exec.list();
                    System.out.println("--------------------------------------");
                    System.out.println("Podaj ID FTasku którego wynik chcesz pobrać.");
                    helper=sc.nextInt();
                    System.out.println("--------------------------------------");
                    try {
                        System.out.print("Wynik: ");
                        System.out.print(My_exec.getResult(helper));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            dots(3);
            clearScreen();
        }
        System.out.println("Koniec programu!");
        My_exec.Terminate();
        return;
    }
}