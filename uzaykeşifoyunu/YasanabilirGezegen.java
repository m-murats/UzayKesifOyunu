package uko;

/**
 *
 * @author Murat
 */
final class YasanabilirGezegen extends Gezegen {
       //[11-20] arası yer çekimi verdik
       public YasanabilirGezegen(String isim, int x, int y, String atmosferTipi , int yerCekimi) {
        super(isim, x, y,atmosferTipi , yerCekimi);
        kaynaklariOlustur();
        
    }
       
       @Override
       void kaynaklariOlustur(){
           this.kaynaklar[0] = new Kaynak("Altin" , 20);
           this.kaynaklar[1] = new Kaynak("Enerji" , 10);
           this.kaynaklar[2] = new Kaynak("Su" , 30);
           this.toplamKaynakMiktari = 60;
       }
       
       
       @Override
       public int inisMaliyetiHesapla(){
           return 60 ;
       }
       
       @Override
       public int kalkisMaliyetiHesapla(){
        return this.getYerCekimi()*2 ; 
        
       }
}
