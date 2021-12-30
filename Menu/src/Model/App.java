package Model;
;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    interface DbWorker {
        void doWork();
    }

    private static App instance = null;
    private HashMap<Option, DbWorker> dbMethods;

    private App() {
        dbMethods = new HashMap<>();
        dbMethods.put(Option.Exite, () -> {
            System.out.println("Adeus");
        });
        dbMethods.put(Option.NewList, new DbWorker() {
            @Override
            public void doWork() {
                System.out.println("cria nova lista");
                Delay(5000);
            }
        });

        //....
    }

    private enum Option {
        Unknown,
        Exite,
        NewList
    }

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    public void Run() throws Exception {
        //init();
        Option userInput;
        do {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();

            dbMethods.get(userInput).doWork();

        }while (userInput!=Option.Exite);
    }

    private Option DisplayMenu() {
        Option option = Option.Unknown;
        System.out.println("Basic Menu");
        System.out.println();
        System.out.println(Option.Exite.ordinal()+". Exit");
        System.out.println(Option.NewList.ordinal()+". New List");
        //.....

        System.out.print(">");
        Scanner in = new Scanner(System.in);
        int result = in.nextInt();

        option = Option.values()[result];

        return option;
    }

    private static void clearConsole() throws Exception {
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");

    }

    private void Delay(int n){
        long t = System.currentTimeMillis();
        t = t + n;
        while (t>=System.currentTimeMillis());
        return;
    }

}
