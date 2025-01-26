package uko;

/**
 *
 * @author Murat
 */
abstract class Cisim extends Object{
    
    private String isim;
    int x;
    int y;

    public Cisim(String isim, int x, int y) {
        this.isim = isim;
        this.x = x;
        this.y = y;
    }

    public String getIsim() {
        return isim;
    }

    public int getXKonumu() {
        return x;
    }

    public int getYKonumu() {
        return y;
    }

    public abstract void bilgiGoster();
}
