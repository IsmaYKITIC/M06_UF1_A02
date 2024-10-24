import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Encarrec implements Serializable {
    private String id;
    private String nom;
    private String telefon;
    private String dataEncarrec;
    private ArrayList<Article> ArticlesL;
    private double preuTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Article> getArticlesL() {
        return ArticlesL;
    }

    public void setArticlesL(ArrayList<Article> articlesL) {
        ArticlesL = articlesL;
    }

}
