package uko;

/**
 *
 * @author Murat
 */

final class BuzGezegeni extends Gezegen {
       //[6-10] arası yer çekimi verdik.
    
       public BuzGezegeni(String isim, int x, int y, String atmosferTipi , int yerCekimi) {
        super(isim, x, y, atmosferTipi , yerCekimi);
        kaynaklariOlustur();
       }
       
       @Override
       void kaynaklariOlustur(){
           this.kaynaklar[0] = new Kaynak("Altin" , 10);
           this.kaynaklar[1] = new Kaynak("Enerji" , 0);
           this.kaynaklar[2] = new Kaynak("Su" , 30);
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
