package uko;
public class UzayKesifAraci {
    
    private int yakitSeviyesi;
    private int kapasite; //uzayAraci kaynak kapasitesi
    private String durumu;
    private Kaynak[] kaynakListesi; 
    private int mevcutKaynakSayisi;
    private int toplamKaynakMiktari;
    

    public UzayKesifAraci(int yakitSeviyesi, int kapasite) {
        this.yakitSeviyesi = yakitSeviyesi;
        this.kapasite = kapasite;
        this.durumu = "Baslangic" ;
        this.kaynakListesi =  new Kaynak[3];
        this.mevcutKaynakSayisi = 0;
     }
    
    private int toplamKaynakMiktari() { // uzay aracinin anlık toplam kaynak miktarini döndürür
        int toplam = 0;
        for (int i = 0; i < mevcutKaynakSayisi; i++) {
            if (kaynakListesi[i] != null) {
                toplam += kaynakListesi[i].getMiktar();
            }
        }
        return toplam;
    }  
 
    void gezegeneGit(Gezegen gezegen ,int oncekikalkisMaliyeti) { 
        
        // inis maliyetimizi ve bir önceki kalkis maliyetimizi yakıt seviyemizden düşeriz
        int maliyet = gezegen.inisMaliyetiHesapla() + oncekikalkisMaliyeti;
        this.yakitSeviyesi -= maliyet;
        
        // gezegene indiğimizde kullanıcıya gerekli bilgiler verilir.
        System.out.println("------------------------");
        System.out.println("Seciminiz:" + gezegen.getIsim().charAt(0) + "\n" +
                         gezegen.getIsim()+ "'ye gidiyorsunuz...\n" +
                        "Yakit tuketimi: " + maliyet + "\n" +
                        "Kalan yakit: " + this.yakitSeviyesi +"\n");

        this.durumu = (gezegen.getIsim() + "'ne inis yapildi") ;
        System.out.println(durumu);
        gezegen.bilgiGoster();
    }
    void yakitsizGezegeneGit(Gezegen gezegen){ //yakıt ve kaynak harcatmadan götüren karadelik için gezegeneGit metodu
        System.out.println(gezegen.getIsim()+ "'ye gidiyorsunuz...\n" +
            "Kalan yakit: " + this.yakitSeviyesi +"\n");
        this.durumu = (gezegen.getIsim() + "'ne inis yapildi") ;
        System.out.println(durumu);
        gezegen.bilgiGoster();
    }

    void kaynaklariTopla(Gezegen gezegen) { // indiğimiz gezegenin kaynakları toplamak için kullandığımız metod
        Kaynak[] gezegenKaynaklari = gezegen.getKaynaklar();
        
        if (gezegenKaynaklari == null|| gezegenKaynaklari.length == 0) {
            System.out.println(gezegen.getIsim() + "gezegeninde toplanabilecek kaynak bulunamadi");
        }
        
        System.out.println(gezegen.getIsim() + "nden kaynak topluyorsunuz...");
        boolean kaynakToplandi = false;
        
        int sonKapasite = this.kapasite;
        int toplananKaynak = 0;
        
        for (Kaynak kaynak : gezegenKaynaklari) { // tek tek gezegenin kaynaklarını dolaşır(altın enerji su..)
            if (kaynak != null && kaynak.getMiktar() > 0) {
                boolean mevcutMu = false;
                
                // mevcut kaynaksa işlem yapar
                for (int i = 0; i < mevcutKaynakSayisi; i++) { // mevcut kaynak sayısı kadar çalışır
                    if (kaynakListesi[i] != null && kaynakListesi[i].getAdi().equals(kaynak.getAdi())) { //eşleşen kaynağı bulduk
                        
                        int toplamaKapasitesi = Math.min(this.kapasite - toplamKaynakMiktari(), kaynak.getMiktar());  
                        /* 30/75 30 kaynak ve toplamda 75 kapasitemiz olsun. 
                        burada eğer toplanabilecekMax kaynak daha küçükse toplama kapasitemiz o kadar oluyor ancak
                        eğer ekleyeceğimiz kaynağın miktarı daha küçükse direk toplamakapasitesini ona ayarlıyoruz
                        (yani toplanabilecek max miktarı belirliyoruz.)*/
                        
                        // mevcut kaynağı güncelleme kısmı
                        kaynakListesi[i].setMiktar(kaynakListesi[i].getMiktar() + toplamaKapasitesi);
                        // burada o kaynağımızı arttırıyoruz 
                        kaynak.setMiktar(kaynak.getMiktar() - toplamaKapasitesi);
                        // buradada gezegenimizden o kaynağı düşürüyoruz
                        

                        mevcutMu = true;
                        kaynakToplandi = true;
                        
                        toplananKaynak  += toplamaKapasitesi;
                        sonKapasite -= toplamaKapasitesi;
                 
                        break;
                    }
                }

                // eğer kaynak yeni ise ekleme kısmı
                if (!mevcutMu && mevcutKaynakSayisi < kaynakListesi.length) {
                    int toplamaKapasitesi = Math.min(this.kapasite - toplamKaynakMiktari(), kaynak.getMiktar());
                    kaynakListesi[mevcutKaynakSayisi++] = new Kaynak(kaynak.getAdi(), toplamaKapasitesi);
                    kaynak.setMiktar(kaynak.getMiktar() - toplamaKapasitesi);
                    kaynakToplandi = true;
 
                    sonKapasite -= toplamaKapasitesi;
                    toplananKaynak  += toplamaKapasitesi;
                }
                //depo doluysa çıkar
                if (toplamKaynakMiktari() >=  kapasite) {
                    System.out.println("Depo kapasitesine ulasti , daha fazla kaynak toplanamaz.");
                    break;

                }
            }
        }
        if(kaynakToplandi){
            System.out.println("Kaynaklar basariyla toplandi. Guncel kaynak durumu");
            kaynaklariGoster();
        }
        else{
            System.out.println("Gezegende hic kaynak kalmadi...");
        }
        System.out.println("Toplanan kaynak: " + toplananKaynak );
        System.out.println("Kapasite durumu: "+ toplamKaynakMiktari() +"/" + this.kapasite);
        
    }  

    void araciGelistir() { // uzay kesif aracimizi geliştirmek için kullandığımız metod
        int toplamKaynakMiktari = toplamKaynakMiktari();
        
        if (toplamKaynakMiktari >= 1) { //min 1 birim kaynak gerekli
            int kullanilacakKaynak = Math.min(toplamKaynakMiktari, 10); //10 veya 10'dan daha düşük kaynak miktarı varsa onu kullanır.
            kapasite += kullanilacakKaynak * 5; // 1 birim kaynak 5 birim kapasite eder.

            // kullanılan kaynakları azaltma kısmı
            for (int i = 0; i < mevcutKaynakSayisi && kullanilacakKaynak > 0; i++) {
                int mevcut = kaynakListesi[i].getMiktar();
                if (mevcut <= kullanilacakKaynak) {
                    kaynakListesi[i].setMiktar(0);
                    kullanilacakKaynak -= mevcut;
                } else {
                    kaynakListesi[i].setMiktar(mevcut - kullanilacakKaynak);
                    kullanilacakKaynak = 0;
                }
            }
            System.out.println("Kesif araci gelistiriliyor....");
            System.out.println("Kalan kaynak: " + toplamKaynakMiktari());
            System.out.println("Arac basariyla gelistirildi. Yeni kapasite: " + kapasite);
        } else {
            System.out.println("Araci gelistirmek icin yeterli kaynaginiz yok.");
        }
    }

    void yakitYenile() { // uzay aracimizin yakitini yenilemek için kullandığımız metod
        int toplamKaynakMiktari = toplamKaynakMiktari();
        
        
        if (toplamKaynakMiktari >= 10) {
            if(toplamKaynakMiktari <= 40){
            int kullanilacakKaynak = 10;   
            yakitSeviyesi += kullanilacakKaynak * 2;

            // kullanılan kaynakları azalt
            for (int i = 0; i < mevcutKaynakSayisi && kullanilacakKaynak > 0; i++) {
                int mevcut = kaynakListesi[i].getMiktar();
                
                if (mevcut <= kullanilacakKaynak) {//kaynak tamamen kullanılır
                    kaynakListesi[i].setMiktar(0);
                    kullanilacakKaynak -= mevcut;
                } else { //  kalan kaynağı kullan ve azalt veya kullanılacak kaynak mevcuttan azsa da buraya girer
                    kaynakListesi[i].setMiktar(mevcut - kullanilacakKaynak);
                    kullanilacakKaynak = 0;
                }
            }

            System.out.println("Yakit basariyla yenilendi. Guncel yakit seviyesi: " + yakitSeviyesi);
            System.out.println("Kalan kaynak miktari: " + toplamKaynakMiktari()); 
            }
            else{
            int kullanilacakKaynak = toplamKaynakMiktari-20;   
            yakitSeviyesi += kullanilacakKaynak * 2;

            // kullanılan kaynakları azalt
            for (int i = 0; i < mevcutKaynakSayisi && kullanilacakKaynak > 0; i++) {
                int mevcut = kaynakListesi[i].getMiktar();
                
                if (mevcut <= kullanilacakKaynak) {//kaynak tamamen kullanılır
                    kaynakListesi[i].setMiktar(0);
                    kullanilacakKaynak -= mevcut;
                } else { //  kalan kaynağı kullan ve azalt veya kullanılacak kaynak mevcuttan azsa da buraya girer
                    kaynakListesi[i].setMiktar(mevcut - kullanilacakKaynak);
                    kullanilacakKaynak = 0;
                }
            }

            System.out.println("Yakit basariyla yenilendi. Guncel yakit seviyesi: " + yakitSeviyesi);
            System.out.println("Kalan kaynak miktari: " + toplamKaynakMiktari());
            }

        } else {
            System.out.println("Yakit yenilemek icin yeterli kaynak yok...");
        }

    }

    void kaynaklariGoster() { // uzay kesif aracimizin kaynaklistesini print eder.
        for (int i = 0; i < this.mevcutKaynakSayisi; i++) {
            System.out.println("- " + this.kaynakListesi[i].getAdi() + ": " + this.kaynakListesi[i].getMiktar() + " birim");
        }
    }

    void bilgiGoster() {
        System.out.println("Yakıt Seviyesi: " + this.yakitSeviyesi);
        System.out.println("Kapasite: " + this.kapasite);
        System.out.println("Kaynak Listesi:");
        kaynaklariGoster();
    }
    
    public int getYakitSeviyesi() {
        return yakitSeviyesi;
    }

    public void setYakitSeviyesi(int yakitSeviyesi) {
        this.yakitSeviyesi = yakitSeviyesi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public String getDurumu() {
        return durumu;
    }

    public void setDurumu(String durumu) {
        this.durumu = durumu;
    }

    public Kaynak[] getKaynakListesi() {
        return kaynakListesi;
    }

    public void setKaynakListesi(Kaynak[] kaynakListesi) {
        this.kaynakListesi = kaynakListesi;
    }

    public int getMevcutKaynakSayisi() {
        return mevcutKaynakSayisi;
    }

    public void setMevcutKaynakSayisi(int mevcutKaynakSayisi) {
        this.mevcutKaynakSayisi = mevcutKaynakSayisi;
    }
}
