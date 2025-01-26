package uko;


import java.util.Random;

/**
 *
 * @author Murat
 */
public class Karadelik extends Cisim {
    // xKonumu yKonumu ve isim : özellikleri
    
    public Karadelik(String isim, int x, int y) {
        super(isim, x, y);
    }
    
    Gezegen calis(UzayKesifAraci uzayAraci, Cisim[] galaksi) {
        
        Random random = new Random(); 
        Gezegen hedefGezegen= null;
        
        if (random.nextBoolean()) { //1. çalışma şekli (2 çalışma şeklinden birini kullansın diye rastgele boolean üretttirdik)

            hedefGezegen = rastgeleGezegenSec(galaksi);
            
            uzayAraci.yakitsizGezegeneGit(hedefGezegen); // yakıt harcamadan gezegene gönderen metod
            System.out.println("Karadelik sizi hic yakit ve kaynak harcatmadan " + hedefGezegen.getIsim() +"'ne gonderdi.");
            return hedefGezegen; //mevcut gezegen
            
            
        }else { // 2. çalışma şekli
            
            hedefGezegen = rastgeleGezegenSec(galaksi);
            
            int yakit = uzayAraci.getYakitSeviyesi();
            if(yakit >= 20){ //  20den büyükse 20 yakıt harcatır
                    int sonYakit = yakit - 20;
                    uzayAraci.setYakitSeviyesi(sonYakit);
                    
            }
            else { // 20'den azsa yakıtımız hepsini kullanıyor. 
                uzayAraci.setYakitSeviyesi(0);
                
            }

            Kaynak[] kaynaklari = uzayAraci.getKaynakListesi();
            int sec = (int)(Math.random()*3); //3 kaynak  türünden birini seçmek için tanımladık.
            
            
            if (kaynaklari[sec] == null) { // eğer seçtiğimiz kaynak nullsa hiç kaynak harcamadan gönder
                uzayAraci.yakitsizGezegeneGit(hedefGezegen);
                System.out.println("Karadelik sizi " + hedefGezegen.getIsim() + "'ne " + 20 + "yakit ve " + 0 + "kaynak harcayarak gonderdi!");
                return hedefGezegen; //mevcutgezegen
            }
            else{  // seçtiğimiz kaynak doluysa
                if (kaynaklari[sec].getMiktar()>= 20) { //kaynakmiktarı 20den büyükse
                        int sonKaynak = kaynaklari[sec].getMiktar()-20;

                        kaynaklari[sec].setMiktar(sonKaynak); // kaynaklarinin birinden 20 eksiltiyor...
            

                        uzayAraci.yakitsizGezegeneGit(hedefGezegen);
                        System.out.println("Karadelik sizi " + hedefGezegen.getIsim() + "'ne " + 20 + "yakıt ve " + 20 + "kaynak harcayarak gönderdi!");
                        return hedefGezegen; //mevcutgezegen
                }
                else { // küçükse hepsini harcatır
                    int harcanan = kaynaklari[sec].getMiktar();
                    
                    uzayAraci.yakitsizGezegeneGit(hedefGezegen);
                    System.out.println("Karadelik sizi " + hedefGezegen.getIsim() + "'ne " + 20 + " yakit ve " + harcanan + " kaynak harcayarak gonderdi!");
                    return hedefGezegen; //mevcutgezegen
                }
                

            }

        }
}

  
    
    @Override
    public void bilgiGoster() {
        System.out.println("Karadelik Adı: " + this.getIsim());
        System.out.println("Konum: (" + this.getXKonumu() + ", " + this.getYKonumu() + ")");

    }
    Gezegen rastgeleGezegenSec(Cisim[] galaksi){
        Gezegen rastgele = null;
        int denemeSayisi = 0;
        int maxDeneme = galaksi.length*2;
        
        while(denemeSayisi < maxDeneme){
            int index = (int)(Math.random()*(galaksi.length));
            
            if (galaksi[index] instanceof Gezegen) {
                rastgele = ((Gezegen)galaksi[index]);
                break;
            }
            denemeSayisi++;
           
        }

       return rastgele;
    }

}
