package ytkurs.tabelaapk;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty prodC1;
    private SimpleDoubleProperty prodC2;
    private SimpleIntegerProperty prodC3;
    private SimpleStringProperty prodC4;
    private SimpleStringProperty prodC5;

    public Product(String prodC1, double prodC2, int prodC3, String prodC4, String prodC5) {
        this.prodC1 = new SimpleStringProperty(prodC1);
        this.prodC2 = new SimpleDoubleProperty(prodC2);
        this.prodC3 = new SimpleIntegerProperty(prodC3);
        this.prodC4 = new SimpleStringProperty(prodC4);
        this.prodC5 = new SimpleStringProperty(prodC5);
    }

    public String getProdC1() {
        return prodC1.get();
    }

    public void setProdC1(String prodC1) {
        this.prodC1 = new SimpleStringProperty(prodC1);
    }

    public double getProdC2() {
        return prodC2.get();
    }

    public void setProdC2(double prodC2) {
        this.prodC2 = new SimpleDoubleProperty(prodC2);
    }

    public int getProdC3() {
        return prodC3.get();
    }

    public void setProdC3(int prodC3) {
        this.prodC3 = new SimpleIntegerProperty(prodC3);
    }

    public String getProdC4() {
        return prodC4.get();
    }

    public void setProdC4(String prodC4) {
        this.prodC4 = new SimpleStringProperty(prodC4);
    }

    public String getProdC5() {
        return prodC5.get();
    }

    public void setProdC5(String prodC5) {
        this.prodC5 = new SimpleStringProperty(prodC5);
    }
}
