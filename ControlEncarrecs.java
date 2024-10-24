import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class ControlEncarrecs {
   private static String ruta = "/home/sma/ITIC/Segundo/M6/M06_UF1_A01-/Fitxers";

   public static void main(String[] args) throws IOException {
      while (true) {

         System.out.println("Quina operacio vols realitza:");
         System.out.println();
         System.out.println("-Generar un nou encàrrec(A)");
         System.out.println();
         System.out.println("-Mostrar un encàrrec (B)");
         System.out.println();
         System.out.println("-Sortir (C)");
         System.out.println();
         char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 1);
         while (true) {
            if (resposta == 'A') {
               generaEncarresc();
               generaArticle();
               break;
            } else if (resposta == 'B') {
               mostraEncarrec();
               break;
            } else {
               System.out.println("Fins aviat!");
               return;
            }
         }
      }

   }

   public static ArrayList<Encarrec> generaEncarrecs() {
      ArrayList<Article> EncarrecL = new ArrayList<Article>();
   }

   public static ArrayList<Article> generaArticle() {
      ArrayList<Article> ArticlesL = new ArrayList<Article>();
      // Cliente
      String nom = nom(1);
      String telefon = telClient();
      String data = dataClient();

      while (true) {
         // Article
         String producte = nom(2);
         double quantitat = quantitat();
         String unitats = unitatsArticle();
         double preu = preuArticle();
         // instancia en lista

         ArticlesL.add(new Article(producte, quantitat, unitats, preu));

         System.out.println("Vols afegir un altre article?");
         Boolean resposta = Utilitats.confirmador(Utilitats.readLine());
         if (!resposta) {
            break;
         }
      }
      return ArticlesL;

   }

   public static void EsculliFormat() {

      System.out.println();
      System.out.println("En quin format vols realitza l'encarrec:");
      System.out.println();
      System.out.println("-Albarà(A)");
      System.out.println();
      System.out.println("-Binari (B)");
      System.out.println();
      System.out.println("-CSV (C)");
      System.out.println();

      char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 1);
      if (resposta == 'A') {
         creaEncarrec(resposta, nom, telefon, data, ArticlesL);
         return;
      } else if (resposta == 'B') {
         creaEncarrec(resposta, nom, telefon, data, ArticlesL);
         return;
      } else {
         creaEncarrec(resposta, nom, telefon, data, ArticlesL);
         return;
      }
   }

   public static String nom(int i) {
      String text;
      boolean esDigit = false;
      if (i == 1) {
         System.out.println("Introueix el teu Nom:");
         text = Utilitats.readLine();
         while (true) {
            esDigit = false;
            if (text.isBlank()) {
               System.out.println("El camp és obligatori:");
               text = Utilitats.readLine();
            } else {
               for (int j = 0; j < text.length(); j++) {
                  if (Character.isDigit(text.charAt(j))) {
                     esDigit = true;
                     break;
                  } else {
                     esDigit = false;
                  }
               }
               if (esDigit) {
                  System.out.println("El nom no pot tenir Digits:");
                  text = Utilitats.readLine();
               } else {
                  break;
               }
            }

         }
      } else {
         System.out.println("Introueix el Nom del producte:");
         text = Utilitats.readLine();
         while (true) {
            if (text.isBlank()) {
               System.out.println("El camp és obligatori:");
               text = Utilitats.readLine();
            } else {
               for (int j = 0; j < text.length(); j++) {
                  if (Character.isDigit(text.charAt(j))) {
                     esDigit = true;
                     break;
                  } else {
                     esDigit = false;
                  }
               }
               if (esDigit) {
                  System.out.println("El nom del producte no pot tenir Digits:");
                  text = Utilitats.readLine();
               } else {
                  break;
               }
            }

         }
      }
      return text;
   }

   public static String telClient() {
      String text;
      String regex = "^[0-9]{9}$";
      Pattern pattern = Pattern.compile(regex);

      System.out.println("Introueix el teu numero sense prefix :");
      text = Utilitats.readLine();

      while (true) {
         if (text.isBlank()) {
            System.out.println("El camp és obligatori:");
         } else {
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
               break;
            } else {
               System.out.println(
                     "El Numero no pot tenir lletres ni caràcters especials i ha tenir 9 digits. Introdueix un número vàlid:");
            }
         }
         text = Utilitats.readLine();
      }
      return text;
   }

   public static String dataClient() {
      String text;

      String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";
      Pattern pattern = Pattern.compile(regex);

      System.out.println("Introueix la Data actual (DD/MM/AAAA):");
      text = Utilitats.readLine();

      while (true) {
         if (text.isBlank()) {
            System.out.println("El camp és obligatori:");
         } else {
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
               break;
            } else {
               System.out.println("Format de data incorrecte. Torna a introduir la data en format (DD/MM/AAAA):");
            }
         }
         text = Utilitats.readLine();
      }

      return text;
   }

   public static String unitatsArticle() {
      String text;
      boolean esDigit = false;
      System.out.println("Introdueix les unitats de l'article (ex. kg, unitats, litres, etc.):");
      text = Utilitats.readLine();
      while (true) {
         if (text.isBlank()) {
            System.out.println("El camp és obligatori:");
            text = Utilitats.readLine();
         } else {
            for (int j = 0; j < text.length(); j++) {
               if (Character.isDigit(text.charAt(j))) {
                  esDigit = true;
                  break;
               } else {
                  esDigit = false;
               }
            }
            if (esDigit) {
               System.out.println("Les unitats no poden ser Numeriques:");
               text = Utilitats.readLine();
            } else {
               break;
            }
         }
      }
      return text.toUpperCase();
   }

   public static double quantitat() {
      String text;
      boolean esDigit = false;
      System.out.println("Introdueix la quantitat :");
      text = Utilitats.readLine();
      while (true) {
         if (text.isBlank()) {
            System.out.println("El camp és obligatori:");
            text = Utilitats.readLine();
         } else {
            for (int j = 0; j < text.length(); j++) {
               if (Character.isLetter(text.charAt(j))) {
                  esDigit = true;
                  break;
               } else {
                  esDigit = false;
               }
            }
            if (esDigit) {
               System.out.println("La quantitat ha de ser numerica:");
               text = Utilitats.readLine();
            } else {
               break;
            }
         }
      }
      return Double.parseDouble(text);
   }

   public static double preuArticle() {
      String text;
      boolean esDigit = false;
      System.out.println("Introdueix El preu del Article :");
      text = Utilitats.readLine();
      while (true) {
         if (text.isBlank()) {
            System.out.println("El camp és obligatori:");
            text = Utilitats.readLine();
         } else {
            for (int j = 0; j < text.length(); j++) {
               if (Character.isLetter(text.charAt(j))) {
                  esDigit = true;
                  break;
               } else {
                  esDigit = false;
               }
            }
            if (esDigit) {
               System.out.println("La quantitat ha de ser numerica:");
               text = Utilitats.readLine();
            } else {
               break;
            }
         }
      }
      return Double.parseDouble(text);
   }

   public static void creaEncarrec(char text, String nomCli, String telefon, String data, List<Article> ArticlesL) {
      File tiket;

      // Comprovem que la ruta existeix
      File dir = new File(ruta);
      if (!dir.exists()) {
         dir.mkdirs();
      }

      // Depenent del valor de 'text', assignem un nou fitxer a 'tiket'
      if (text == 'A') {
         tiket = new File(ruta, "encarrecs_client_" + nomCli + "_" + System.currentTimeMillis() + ".txt");
         if (!tiket.exists()) {
            try {
               tiket.createNewFile();
            } catch (IOException ex) {
               System.out.println("ERROR: No s'ha pogut crear l'arxiu. " + ex.getMessage());
            }
         }
         escriuArrxiu(tiket, nomCli, telefon, data, ArticlesL, text);
      } else if (text == 'B') {
         tiket = new File(ruta, "encarrecs_client_" + nomCli + "_" + System.currentTimeMillis() + ".dat");
         if (!tiket.exists()) {
            try {
               tiket.createNewFile();
            } catch (IOException ex) {
               System.out.println("ERROR: No s'ha pogut crear l'arxiu. " + ex.getMessage());
            }
         }
         escriuArrxiu(tiket, nomCli, telefon, data, ArticlesL, text);
      } else {
         tiket = new File(ruta, "encarrecs_client_" + nomCli + "_" + System.currentTimeMillis() + ".csv");
         if (!tiket.exists()) {
            try {
               tiket.createNewFile();
            } catch (IOException ex) {
               System.out.println("ERROR: No s'ha pogut crear l'arxiu. " + ex.getMessage());
            }
         }
         escriuArrxiu(tiket, nomCli, telefon, data, ArticlesL, text);
      }
   }

   public static void escriuArrxiu(File tiket, String nomCli, String telefon, String data, List<Article> ArticlesL,
         char tipus) {
      // TXT
      if (tipus == 'A') {
         try {
            FileWriter escritptura = new FileWriter(tiket);

            // Escribir cabeceras centradas
            escritptura.write(String.format("Nom del client: %s\n", nomCli));
            escritptura.write(String.format("Telefon del client: %s\n", telefon));
            escritptura.write(String.format("Data de l'encarrec: %s\n", data));
            escritptura.write("\n");

            // Escribir tabla con formato de columnas
            escritptura.write(String.format("%-10s %-10s %-15s\n", "Quantitat", " Unitats", " Article"));
            escritptura.write(String.format("%-10s %-10s %-15s\n", "===========", "=========", "==============="));

            // Escribir cada artículo en la tabla
            for (Article article : ArticlesL) {
               escritptura.write(String.format("%-10.1f\t %-5s \t\t %-15s\n",
                     article.getQuantitat(),
                     article.getUnitats(),
                     article.getNom()));
            }

            escritptura.close();

            System.out.println("Encarrec escrit correctament en el fitxer: " + tiket);
            System.out.println();

         } catch (IOException ex) {
            System.out.println("ERROR: No s'ha pogut escriure en l'arxiu.");
         }
         // CSV
      } else if (tipus == 'C') {
         try {
            FileWriter escritptura = new FileWriter(tiket);
            escritptura.write(nomCli + "," + telefon + "," + data + ",");
            for (int i = 0; i < ArticlesL.size(); i++) {
               escritptura.write(
                     ArticlesL.get(i).getQuantitat() + "," + ArticlesL.get(i).getUnitats() + ","
                           + ArticlesL.get(i).getNom() + ",");
            }
            escritptura.close();
            System.out.println("Encarrec escrit correctament en el fitxer: " + tiket);
            System.out.println();
         } catch (Exception e) {
            System.out.println("ERROR: No s'ha pogut escriure en l'arxiu.");
         }
         // bin
      } else {
         try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(tiket, true));
            dos.writeUTF(nomCli);
            dos.writeUTF(telefon);
            dos.writeUTF(data);
            for (int i = 0; i < ArticlesL.size(); i++) {
               dos.writeDouble(ArticlesL.get(i).getQuantitat());
               dos.writeUTF(ArticlesL.get(i).getUnitats() + " "
                     + ArticlesL.get(i).getNom());
            }
            dos.close();
            System.out.println("Encarrec escrit correctament en el fitxer: " + tiket);
            System.out.println();

         } catch (IOException ex) {
            System.out.println("ERROR: No s'ha pogut escriure en l'arxiu.");
         }
      }

   }

   public static void mostraEncarrec() throws IOException {
      while (true) {
         System.out.println("Quin tipus de fitxer vols mostra:");
         System.out.println();
         System.out.println("-Binari (B)");
         System.out.println();
         System.out.println("-CSV (C)");
         System.out.println();
         boolean comfirmacio;
         char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 2);
         try {
            if (resposta == 'B') {
               llegeixBin();
            } else {
               llegeixCSV();
            }
            System.out.println("Vols llegir un Altre fitxer?");
            comfirmacio = Utilitats.confirmador(Utilitats.readLine());
            if (!comfirmacio) {
               return;
            }
            System.out.println();
         } catch (IOException exception) {
            System.out.println();
            System.out.println("Error al leer archivo: ");
         }
      }
   }

   public static void llegeixBin() throws IOException {
      System.out.println("Introueix la ruta del fitche(phat):");
      String dir = Utilitats.readLine() + ".dat";

      FileReader lector = new FileReader(dir);
      BufferedReader input = new BufferedReader(lector);
      while (true) {
         String linia = input.readLine();
         if (null == linia)
            break;
         System.out.println(linia);
      }
      input.close();
   }

   public static void llegeixCSV() throws IOException {
      System.out.println("Introueix la ruta del fitche(phat):");
      String dir = Utilitats.readLine() + ".csv";

      FileReader lector = new FileReader(dir);
      BufferedReader input = new BufferedReader(lector);
      while (true) {
         String linia = input.readLine();
         if (null == linia)
            break;
         System.out.println(linia);
      }
      input.close();
   }
}
