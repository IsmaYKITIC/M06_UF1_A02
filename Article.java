import java.io.Serializable;

public class Article implements Serializable {
    private String nom;
    private double quantitat;
    private String unitats;
    private double preu;

    public Article(String nom, double quantitat, String unitats, Double preu) {
        this.nom = nom;
        this.quantitat = quantitat;
        this.unitats = unitats;
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Article [nom=" + nom + ", quantitat=" + quantitat + ", unitats=" + unitats + "]";
    }

    public Article(String nom, double quantitat, String unitats) {
        this.nom = nom;
        this.quantitat = quantitat;
        this.unitats = unitats;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(double quantitat) {
        if (quantitat < 0) {
            quantitat = 0;
        }
        this.quantitat = quantitat;
    }

    public String getUnitats() {
        return unitats;
    }

    public void setUnitats(String unitats) {
        this.unitats = unitats;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        if (preu < 0) {
            preu = 0;
        }
        this.preu = preu;
    }

}
