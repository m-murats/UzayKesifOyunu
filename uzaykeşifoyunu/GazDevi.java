package uko;

/**
 *
 * @author Murat
 */
final class GazDevi extends Gezegen {
       //[11-20] arası yer çekimi verdik
    
       public GazDevi(String isim, int x, int y, String atmosferTipi , int yerCekimi) {
        super(isim, x, y,atmosferTipi , yerCekimi);
        kaynaklariOlustur();
        
    }
       
       @Override
       void kaynaklariOlustur(){
           this.kaynaklar[0] = new Kaynak("Altin" , 0);
           this.kaynaklar[1] = new Kaynak("Enerji" , 40);
           this.kaynaklar[2] = new Kaynak("Su" , 0);
           this.toplamKaynakMiktari = 40;
       }
       
       
       @Override
       public int inisMaliyetiHesapla(){
           return 40 ;
       }
       @Override
       public int kalkisMaliyetiHesapla(){
        return this.getYerCekimi()*2 ; 
        
       }
}
