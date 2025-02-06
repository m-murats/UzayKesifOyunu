package uko;

/**
 *
 * @author Murat
 */
final class CüceUydu extends Gezegen {
       //[1-5] arası yer çekimi verdik.
    
       CüceUydu(String isim, int x, int y, String atmosferTipi , int yerCekimi) {
       super(isim, x, y,atmosferTipi , yerCekimi);
        kaynaklariOlustur();
        
    }
       
       @Override
       void kaynaklariOlustur(){
           this.kaynaklar[0] = new Kaynak("Altin" , 0);
           this.kaynaklar[1] = new Kaynak("Enerji" , 20);
           this.kaynaklar[2] = new Kaynak("Su" , 10);
           this.toplamKaynakMiktari = 30;
       }
       
       @Override
       public int inisMaliyetiHesapla(){
           return 30 ;
       }
        
       @Override
       public int kalkisMaliyetiHesapla(){
        return this.getYerCekimi()*1 ;
        
       }

}
       
