import java.io.*;

import java.util.ArrayList;

public class ControlEncarrecs {
   private final static String RUTA = System.getProperty("user.home") + "/Desktop/M06_UF1_A02/FitxersIsma";
   /* poner su ruta */
   private static ArrayList<Encarrec> EncarrecComplet = new ArrayList<Encarrec>();

   public static void main(String[] args) throws IOException {
      while (true) {
         boolean opcio = true;
         System.out.println("Quina operacio vols realitza:");
         System.out.println();
         System.out.println("-Generar un nou encàrrec(A)");
         System.out.println();
         System.out.println("-Mostrar un encàrrec (B)");
         System.out.println();
         System.out.println("-Sortir (C)");
         System.out.println();
         char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 1);
         while (opcio) {
            if (resposta == 'A') {
               EncarrecComplet = generaEncarrecs();
               EsculliFormat(EncarrecComplet);
               opcio = false;
            } else if (resposta == 'B') {
               mostraEncarrec();
               opcio = false;
            } else {
               System.out.println("Fins aviat!");
               return;
            }
         }
      }

   }

   public static ArrayList<Encarrec> generaEncarrecs() {
      ArrayList<Encarrec> EncarrecL = new ArrayList<Encarrec>();
      int id = 0;
      boolean ProducteMes = true;
      while (ProducteMes) {
         String nom = Utilitats.nom(1);
         String telefon = Utilitats.telClient();
         String data = Utilitats.dataClient();
         ArrayList<Article> ArticlesA = generaArticles();
         double preuTotal = 0;

         for (Article article : ArticlesA) {
            preuTotal += (article.getPreu() * article.getQuantitat());
         }
         id += 1;
         EncarrecL.add(new Encarrec(id, nom, telefon, data, ArticlesA, preuTotal));
         System.out.println("vols genera un altre encarrec? (s/n)");
         ProducteMes = Utilitats.confirmador(Utilitats.readLine());

      }
      return EncarrecL;

   }

   public static ArrayList<Article> generaArticles() {
      ArrayList<Article> ArticlesL = new ArrayList<Article>();
      // Cliente

      while (true) {
         // Article
         String producte = Utilitats.nom(2);
         double quantitat = Utilitats.quantitat();
         String unitats = Utilitats.unitatsArticle();
         double preu = Utilitats.preuArticle();
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

   public static void EsculliFormat(ArrayList<Encarrec> encarrec) {

      System.out.println();
      System.out.println("En quin format vols realitza l'encarrec:");
      System.out.println();
      System.out.println("- Fitxer Serialitzable(A)");
      System.out.println();
      System.out.println("- Fitxer d’accés aleatori(B)");
      System.out.println();

      char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 3);
      if (resposta == 'A') {
         creaEncarrec(resposta, encarrec);
         return;
      } else if (resposta == 'B') {
         creaEncarrec(resposta, encarrec);
         return;
      }
   }

   public static void creaEncarrec(char text, ArrayList<Encarrec> encarrec) {
      File tiket;
      // Depenent del valor de 'text', assignem un nou fitxer a 'tiket'
      // Serialitsable
      if (text == 'A') {
         File dir = new File(RUTA + "/Seria/");
         if (!dir.exists()) {
            dir.mkdirs();
         }

         tiket = new File(dir, "encarrecs_" + System.currentTimeMillis() + ".dat");
         if (!tiket.exists()) {
            try {
               tiket.createNewFile();
            } catch (IOException ex) {
               System.out.println("ERROR: No s'ha pogut crear l'arxiu. " + ex.getMessage());
            }
         }
         escriuArrxiu(tiket, encarrec, text);

         // d’accés aleatori
      } else if (text == 'B') {
         File dir2 = new File(RUTA + "/AccésAleatori");
         if (!dir2.exists()) {
            dir2.mkdirs();
         }
         tiket = new File(dir2, "encarrecs_" + System.currentTimeMillis() + ".dat");
         if (!tiket.exists()) {
            try {
               tiket.createNewFile();
            } catch (IOException ex) {
               System.out.println("ERROR: No s'ha pogut crear l'arxiu. " + ex.getMessage());
            }
         }
         escriuArrxiu(tiket, encarrec, text);
      }
   }

   public static void escriuArrxiu(File tiket, ArrayList<Encarrec> encarrec,
         char tipus) {
      // serialitzable
      if (tipus == 'A') {
         try {
            escriuSerialitzat(tiket, encarrec);
         } catch (Exception ex) {
            System.out.println("ERROR: No s'ha pogut escriure en l'arxiu.");
            ex.getStackTrace();
         }
         // Acces aleatori
      } else if (tipus == 'B') {
         try {
            escriuAleatori(tiket, encarrec);

         } catch (Exception e) {
            System.out.println("ERROR: No s'ha pogut escriure en l'arxiu.");
         }
      }
      System.out.println("Encarrec escrit correctament en el fitxer: " + tiket);
      System.out.println();
   }

   public static void escriuSerialitzat(File ruta, ArrayList<Encarrec> encarrecs) {
      try {
         FileOutputStream fitxerseria = new FileOutputStream(ruta);
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fitxerseria);
         objectOutputStream.writeObject(encarrecs);
         objectOutputStream.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void escriuAleatori(File ruta, ArrayList<Encarrec> encarrecs) {
      try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
         for (Encarrec e : encarrecs) {
            raf.writeInt(e.getId());
            raf.writeUTF(e.getNom());
            raf.writeUTF(e.getTelefon());
            raf.writeUTF(e.getDataEncarrec());

            ArrayList<Article> articles = e.getArticle();
            for (Article art : articles) {
               raf.writeUTF(art.getNom());
               raf.writeDouble(art.getPreu());
            }
            raf.writeDouble(e.getPreuTotal());
         }
      } catch (

      Exception e) {
         // TODO: handle exception
      }
   }

   public static void mostraEncarrec() throws IOException {
      while (true) {
         System.out.println("Quin tipus de fitxer vols mostra:");
         System.out.println();
         System.out.println("- Fitxer Serialitzable(A)");
         System.out.println();

         boolean comfirmacio;
         char resposta = Utilitats.triaOpcio(Utilitats.readLine(), 3);
         try {
            while (true) {
               if (resposta == 'B') {
                  System.out.println("Opcio no disponible temporalment");
                  System.out.println();
               } else if (resposta == 'A') {
                  llegeixSerialitzat();
                  break;
               }
               System.out.println("Tria un altre opcio:");
               resposta = Utilitats.triaOpcio(Utilitats.readLine(), 3);
            }
            System.out.println();
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

   @SuppressWarnings("unchecked")
   public static void llegeixSerialitzat() throws IOException {
      System.out.println("Introueix la RUTA del fitche(phat):");
      String dir = Utilitats.readLine() + ".dat";
      System.out.println();

      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dir))) {
         ArrayList<Encarrec> encarrecs = (ArrayList<Encarrec>) in.readObject();
         if (encarrecs.isEmpty()) {
            System.out.println("No s'han trobat encàrrecs a l'arxiu.");
         } else {
            System.out.println("Encàrrecs:");
            for (Encarrec enc : encarrecs) {
               System.out.println(enc.toString());
            }
         }
      } catch (IOException | ClassNotFoundException e) {
         System.err.println("Error al llegir els encàrrecs: " + e.getMessage());
      }
   }

}
