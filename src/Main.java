import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[] farben = { "gelb", "rot", "blau", "grün", "violett", "orange" };
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean playagain = true;

        while (playagain) {

            String[] geheimCode = new String[4];
            for (int i = 0; i < 4; i++) {
                geheimCode[i] = farben[random.nextInt(farben.length)];
            }

            System.out.println("Willkommen zu Mastermind!");
            System.out.println("Mögliche Farben: gelb, rot, blau, grün, violett, orange");
            System.out.println("Gib deinen Tipp als 4 Farben ein, getrennt durch Leerzeichen.");
            System.out.println("Du hast 12 Versuche!");

            for (int versuch = 1; versuch <= 12; versuch++) {
                System.out.println("\nVersuch " + versuch + ":");
                String eingabe = scanner.nextLine().toLowerCase().trim();
                String[] tipp = eingabe.split(" ");

                if (tipp.length != 4) {
                    System.out.println("Bitte genau 4 Farben eingeben!");
                    versuch--;
                    continue;
                }

                boolean gueltig = true;
                for (String farbe : tipp) {
                    if (!Arrays.asList(farben).contains(farbe)) {
                        System.out.println(farbe + "' ist keine gültige Farbe!");
                        gueltig = false;
                        break;

                    }
                }
                if (!gueltig) {
                    versuch--;
                    continue;
                }

                int korrektPosition = 0;
                int korrektFarbe = 0;

                boolean[] geheimVerwendet = new boolean[4];
                boolean[] tippVerwendet = new boolean[4];

                for (int i = 0; i < 4; i++) {
                    if (tipp[i].equals(geheimCode[i])) {
                        korrektPosition++;
                        geheimVerwendet[i] = true;
                        tippVerwendet[i] = true;
                    }
                }

                for (int i = 0; i < 4; i++) {
                    if (!tippVerwendet[i]) {
                        for (int j = 0; j < 4; j++) {
                            if (!geheimVerwendet[j] && tipp[i].equals(geheimCode[j])) {
                                korrektFarbe++;
                                geheimVerwendet[j] = true;
                                break;
                            }
                        }
                    }
                }

                System.out.println("Farbe ok, Position ok: " + korrektPosition);
                System.out.println("Farbe ok, Position falsch: " + korrektFarbe);

                if (korrektPosition == 4) {
                    System.out.println("Glückwunsch! Du hast Gewonnen!");
                    break;
                } else if (versuch == 12) {
                    System.out.println("\nLeider verloren!");
                    System.out.println();
                    break;
                }

            }

            System.out.println("Willst du erneut spielen (Ja/Nein): ");
            String rematch = scanner.nextLine().toLowerCase().trim();

            if (rematch.equals("nein")) {
                playagain = false;
                System.out.println("Danke fürs Spielen!");
            } else if (rematch.equals("ja")) {
                playagain = true;
            } else {
                System.out.println("Ungültige Eingabe, das Spiel wird beendet.");
                playagain = false;
            }

        }

    }
}