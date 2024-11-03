import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Encarrec implements Serializable {
    private int id;
    private String nom;
    private String telefon;
    private String dataEncarrec;
    private ArrayList<Article> article;
    private double preuTotal;

    public Encarrec(int id, String nom, String telefon, String dataEncarrec, ArrayList<Article> article,
            double preuTotal) {
        this.id = id;
        this.nom = nom;
        this.telefon = telefon;
        this.dataEncarrec = dataEncarrec;
        this.article = article;
        this.preuTotal = preuTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDataEncarrec() {
        return dataEncarrec;
    }

    public void setDataEncarrec(String dataEncarrec) {
        this.dataEncarrec = dataEncarrec;
    }

    public double getPreuTotal() {
        return preuTotal;
    }

    public void setPreuTotal(double preuTotal) {
        this.preuTotal = preuTotal;
    }

    public ArrayList<Article> getArticle() {
        return article;
    }

    public void setArticle(ArrayList<Article> article) {
        this.article = article;
    }

}
