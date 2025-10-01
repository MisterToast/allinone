// ----------------------------------------------------------------------------------------------------------------- //
//                                    Alle Imports aus externen Java Bibliotheken                                    //
// ----------------------------------------------------------------------------------------------------------------- //
// import für die Methoden (Liste der Voidnamen)
import java.lang.reflect.Method;
// import vom Scanner
import java.util.Scanner;


public class Main {

// ----------------------------------------------------------------------------------------------------------------- //
//                                            Liste aller public Variablen                                           //
// ----------------------------------------------------------------------------------------------------------------- //
    // Scanner für Benutzereingaben
    public static Scanner scanner = new Scanner(System.in);
    // Wenn Running true ist, dann läuft das Programm, wenn nicht, wird es beendet
    public static boolean running = true;


// ----------------------------------------------------------------------------------------------------------------- //
//                                                 ANSI-Escape Codes                                                 //
// ----------------------------------------------------------------------------------------------------------------- //
    // Textformatierungen
    public static String RESET = "\u001B[0m";
    public static String BOLD = "\u001B[1m";
    public static String ITALIC = "\u001B[3m"; // Italic (und Blau) IMMER vor den Output eines anderen Voids als Main setzen, um es von den Hauptbefehlen abzuheben
    // Textfarben
    public static String BLUE = "\u001B[34m"; // Blau (und Italic) IMMER vor den Output eines anderen Voids als Main setzen, um es von den Hauptbefehlen abzuheben
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";


// ----------------------------------------------------------------------------------------------------------------- //
//                                                     Main void                                                     //
// ----------------------------------------------------------------------------------------------------------------- //
    public static void main(String[] args) {

        // Hauptschleife läuft so lange "running" true ist
        while (running) {
            // Haupteingabe des Benuzers
            System.out.println("");
            System.out.print("Dein Befehl: ");
            String input = scanner.nextLine().trim(); // .trim um Leerzeichen zu entfernen

            // Wenn "list" oder nur "l" eingegeben wurde, wird die Liste aller Voids angezeigt
            if (input.equalsIgnoreCase("list") || input.equalsIgnoreCase("l")) {

                Method[] funktionen = Main.class.getDeclaredMethods();
                System.out.println("Liste aller Funktionen aus der Main class:");
                System.out.println("Anzahl: | Methodenname:");
                System.out.println("--------|----------------------");

                int anzahl = 1;
                // Gibt eine Liste aller Voidnamen nacheinander aus
                for (Method m : funktionen) {
                    if (m.getReturnType() == void.class && m.getParameterCount() == 0) {
                        System.out.println(anzahl + ".      | " + m.getName());
                        anzahl++;
                    }
                }

            }
            // Wenn "start <Methodenname>" eingegeben wurde, wird die Methode gestartet
            else if (input.toLowerCase().startsWith("start ")) {
                String methodName = input.substring(6).trim(); // Erst ab dem 6. Buchstaben nach der Methode scannen

                Method[] funktionen = Main.class.getDeclaredMethods();
                boolean gefunden = false;

                for (Method m : funktionen) {
                    if (m.getName().equalsIgnoreCase(methodName)
                            && m.getReturnType() == void.class
                            && m.getParameterCount() == 0) {
                        try {
                            // Statische Methode mit null als Objekt ausführen
                            m.invoke(null);
                            gefunden = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("Fehler beim Ausführen der Methode: " + e.getMessage());
                            gefunden = true;
                            break;
                        }
                    }
                }

                // Wenn die Methode nicht gefunden wurde:
                if (!gefunden) {
                    System.out.println("Methode '" + methodName + "' nicht gefunden oder falscher Typ.");
                }

            }
            // Mit "stop" wird das Programm beendet
            else if (input.equalsIgnoreCase("stop")) {

                // Programm wird beendet
                System.out.println("Programm wird beendet...");
                running = false; // Hauptschleife stoppen

            }
            // Mit "help" die Hilfe anzeigen
            else if (input.equalsIgnoreCase("help")) {
                System.out.println("Befehl                   | Erklärung");
                System.out.println("-------------------------|---------------------------------------------------------");
                System.out.println("start <Methodenname>     | Starte eine Methode deiner Wahl");
                System.out.println("list                     | Zeigt eine Liste mit allen möglichen Methoden an");
                System.out.println("stop                     | Stoppt das Programm sofort");
            }

            // Wenn irgendetwas nicht passendes eingegeben wird
            else {
                System.out.println("Der Befehl " + input + " ist nicht bekannt. Hole dir Hilfe mit: help");
            }
        }
    }


// ----------------------------------------------------------------------------------------------------------------- //
//                                                 Liste aller Voids                                                 //
// ----------------------------------------------------------------------------------------------------------------- //
    public static void  zahlenraten() {
        Scanner zahlenratenscanner = new Scanner(System.in);

        // Variablen, die man für die Zufallszahl braucht
        int versuche = 0;
        int random = (int)(Math.random() * 100 + 1); // Zufällige Zahl zwischen 1 und 100 durch das Math.random Array
        int input;

        // Mit "Thread.currentThread().getStackTrace()[1].getMethodName()" bekommt man den Namen des aktuellen Voids
        System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Eine zufällige Zahl zwischen 1 und 100 wurde erstellt. Versuche sie nun zu erraten :)");

        do {
            System.out.print(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Deine Zahl: ");
            input = zahlenratenscanner.nextInt();
            versuche++;

            if (input > random) {
                System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Deine Zahl ist zu groß");
            }
            else if (input < random) {
                System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Deine Zahl ist zu klein");
            }
        } while (input != random);

        System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Glückwunsch. Du Hast die Zahl " + random + " mit " + versuche + " Versuchen richtig erraten!");
        System.out.println(ITALIC + "Die Methode " + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ITALIC + " wurde beendet!");

    }
    public static void  test2() {
        System.out.println("Test2 wurde erfolgreich ausgeführt :)");
    }
    public static void  test3() {
        System.out.println("Test3 wurde erfolgreich ausgeführt :)");
    }
}
