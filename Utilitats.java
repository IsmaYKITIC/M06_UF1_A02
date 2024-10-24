import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Classe que simplifica la lectura per entrada estàndard.
 */
public class Utilitats {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Mètode per llegir una línia des de l'entrada estàndard
    public static String readLine() {
        try {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                throw new RuntimeException("No deixis la cadena buida");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Mètode per confirmar amb una resposta de "sí" o "no"
    public static boolean confirmador(String resposta) {
        resposta = resposta.trim().toLowerCase();

        if (resposta.equals("s") || resposta.equals("si")) {
            return true;
        }

        if (resposta.equals("n") || resposta.equals("no")) {
            return false;
        }

        return false;
    }

    public static char triaOpcio(String resposta, int cas) {
        resposta = resposta.toUpperCase();
        if (cas == 1) {
            while (true) {
                if (resposta.length() == 1) {
                    if (resposta.charAt(0) == 'A' || resposta.charAt(0) == 'B' || resposta.charAt(0) == 'C') {
                        System.out.println();
                        return Character.toUpperCase(resposta.charAt(0));
                    }
                }
                System.out.println("Opció no disponible, torna a introduir la resposta");
                resposta = (Utilitats.readLine()).toUpperCase();

            }
        } else {
            while (true) {
                if (resposta.length() == 1) {
                    if (resposta.charAt(0) == 'B' || resposta.charAt(0) == 'C') {
                        System.out.println();
                        return Character.toUpperCase(resposta.charAt(0));
                    }
                }
                System.out.println("Opció no disponible, torna a introduir la resposta");
                resposta = (Utilitats.readLine()).toUpperCase();

            }
        }

    }
}
