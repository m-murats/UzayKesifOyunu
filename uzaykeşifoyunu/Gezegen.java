package uko;

/**
 *
 * @author Murat
 */
abstract class Gezegen extends Cisim {
    
    /* final atamamın sebebi constructorla 
    özelliklerini girdikten sonra bir daha değiştirememek*/
    private final String atmosferTipi; 
    private final int yerCekimi; // 1 ile 20 arasında rastgele değer
    Kaynak[] kaynaklar = new Kaynak[3]; //bulundurduğu kaynak objelerinin dizisi
    int toplamKaynakMiktari; // tüm kaynakların toplam miktarı
    
    
    public Gezegen(String isim, int x, int y, String atmosferTipi , int yerCekimi) {
        super(isim , x ,y); // superclassın constructor metodunu çağırıyoruz.
        this.atmosferTipi = atmosferTipi;
        this.yerCekimi = yerCekimi;
    }
    
    public void kaynaklariGoster() {
        System.out.print("- Kaynak:" + toplamKaynakMiktari + "birim ");
        
        for (Kaynak kaynak : kaynaklar) {
            System.out.print( "(" + kaynak.getMiktar() + " birim "+ kaynak.getAdi() +")");
        }
    }
    
    abstract void kaynaklariOlustur();
    abstract int inisMaliyetiHesapla();
    abstract int kalkisMaliyetiHesapla();
    /* gezegen sınıfının subclasslarında bu metodları override edebilmek için abstract tanımladık.*/
    
    
    public int getYerCekimi() {
        return yerCekimi;
    }
    public Kaynak[] getKaynaklar() {
        return kaynaklar;
    }
    public int getKaynakMiktari() {
        return toplamKaynakMiktari;
    }
    public String getAtmosferTipi() {
        return atmosferTipi;
    }
    
    String yercekimiSeviyesi(int yerCekimi){
        if (yerCekimi>=1 && yerCekimi <= 5) { // 1-5 6-10 11-20
            return "Dusuk";
        }
        else if (yerCekimi>=6 && yerCekimi <= 10)
            return "Orta";
        else if (yerCekimi>=11 && yerCekimi <= 20){
            return "Yuksek";
        }
        else
            return "Yanlis Yer Cekimi" ;
    }
    

    @Override
    public void bilgiGoster() {
        System.out.println("Gezegenin ozellikleri:");
        kaynaklariGoster();
        System.out.println("\n\nYerCekimi: " + yercekimiSeviyesi(yerCekimi));
        System.out.println("Atmosfer Tipi: " + atmosferTipi);
    }
    

}

