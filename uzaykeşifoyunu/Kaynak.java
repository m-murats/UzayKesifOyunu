package uko;

/**
 *
 * @author Murat
 */
class Kaynak {
    private String adi;
    private int miktar;

    public Kaynak(String adi, int miktar) {
        this.adi = adi;
        this.miktar = miktar;
    }

    public String getAdi() {
        return adi;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }
}
