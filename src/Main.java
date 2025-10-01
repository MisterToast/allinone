// ----------------------------------------------------------------------------------------------------------------- //
//                                    Alle Imports aus externen Java Bibliotheken                                    //
// ----------------------------------------------------------------------------------------------------------------- //
// import für die Methoden (Liste der Voidnamen)
import java.lang.reflect.Method;
// import vom Scanner
import java.util.GregorianCalendar;
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
    public static String RESET = "\u001B[0m"; // RESET IMMER hinter jedem anderen ANSI Code, um es wieder zurück zum normalen zu machen
    public static String BOLD = "\u001B[1m";
    public static String ITALIC = "\u001B[3m"; // Italic (und Blau) IMMER vor den Output eines anderen Voids als Main setzen, um es von den Hauptbefehlen abzuheben
    // Textfarben
    public static String BLUE = "\u001B[34m"; // Blau (und Italic) IMMER vor den Output eines anderen Voids als Main setzen, um es von den Hauptbefehlen abzuheben
    public static String RED = "\u001B[31m"; // RED IMMER, wenn ein Fehler aufgetreten ist
    public static String GREEN = "\u001B[32m"; // GREEN IMMER, wenn etwas gutes passiert ist
    public static String YELLOW = "\u001B[33m"; // YELLOW IMMER, wenn das Programm einen Tipp oder eine allgemeine Information gibt


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
                System.out.println(YELLOW + "Liste aller Funktionen aus der Main class:" + RESET);
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
                            System.out.println(RED + "Fehler beim Ausführen der Methode. Feher: " + e.getMessage() + RESET);
                            gefunden = true;
                            break;
                        }
                    }
                }

                // Wenn die Methode nicht gefunden wurde:
                if (!gefunden) {
                    System.out.println(RED + "Methode '" + methodName + "' nicht gefunden oder falscher Typ." + RESET);
                }

            }
            // Mit "stop" wird das Programm beendet
            else if (input.equalsIgnoreCase("stop")) {

                // Programm wird beendet
                System.out.println(YELLOW + "Programm wird beendet..." + RESET);
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
                System.out.println(RED + "Der Befehl " + input + " ist nicht bekannt. Hole dir Hilfe mit: help" + RESET);
            }
        }
    }


// ----------------------------------------------------------------------------------------------------------------- //
//                                                 Liste aller Voids                                                 //
// ----------------------------------------------------------------------------------------------------------------- //
    public static void zahlenraten() {
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

        System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": " + GREEN + "Glückwunsch. Du Hast die Zahl " + random + " mit " + versuche + " Versuchen richtig erraten!" + RESET);
        System.out.println(ITALIC + "Die Methode " + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ITALIC + " wurde beendet!" + RESET);

    }
    public static void rechner() {
        running = false;

        Scanner rechnerscanner = new Scanner(System.in);

        double ergebnis = 0;

        System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": " + YELLOW + "Gebe deine Rechnung ein: <Zahl> <Befehl> <Zahl>" + RESET);
        System.out.print(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": Deine Rechnung: ");

        double a = rechnerscanner.nextDouble(); // Erste Zahl
        String x = rechnerscanner.next(); // Befehl oder Zeichen (Plus, Minus, Mal, Geteilt)
        double b = rechnerscanner.nextDouble(); // Zweite Zahl

        switch (x) {
            case "+":
            case "plus":
                ergebnis = a + b;
                break;
            case "-":
            case "minus":
                ergebnis = a - b;
                break;
            case "*":
            case "mal":
                ergebnis = a * b;
                break;
            case "/":
            case "durch":
                if (b != 0) {
                    ergebnis = a / b;
                }
                else {
                    System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": " + RED + "Durch 0 kann man nicht teilen" + RESET);
                }
        }
        System.out.println(ITALIC + BLUE + Thread.currentThread().getStackTrace()[1].getMethodName() + RESET + ": " + GREEN + a + " " + x + " " + b + " ergibt " + ergebnis + RESET);

        running = true;
    }
}
