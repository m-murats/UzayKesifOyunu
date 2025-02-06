package uko;

/* 
    @author Murat
*/

import java.util.Scanner;

public class UzayKesifOyunu {

    public static void main(String[] args) {
        
        Galaksi galaksi = new Galaksi(); // galaksi sınıfından nesne oluşturuyoruz.
        UzayKesifAraci uzayAraci = new UzayKesifAraci(100, 50); // başlangıç 100 yakıt 50 kapasite ile uzayAraci nesnesi oluşturduk.
        String[] hedefDizi = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};// tüm gezegenleri gezdiğini kontrol etmek için
        boolean[] kullaniciGirdisi = new boolean[hedefDizi.length];
        
        
        Scanner scan = new Scanner(System.in);
        
        galaksi.galaksiOlustur(); // galaksi sınıfı içindeki galaksi dizisini oluşturduk.
        galaksi.galaksiyiGoster(); // haritayı göstermek için galaksiyi gösterme metodunu çağırdık.
        
        Gezegen mevcutGezegen = null; //mainde mevcutGezegen üzerinden işlem yapabilmek için tanımladık.
        
        boolean oyunDevam = true;  // oyunun devam etmesi için oyun döngüsünün çalışıp çalışmaması gerektiğini belirler.
        int oncekiKalkisMaliyet = 0; // bir önceki kalkış maliyetini bir sonraki gezegene giderken yakıtımızdan düşmek için tanımladık.
        
        while (oyunDevam){ // oyun döngüsü
            
            String secim = scan.nextLine().toUpperCase(); // kullanıcıdan gitmek istediği gezegen veya karadeliği belirtmesi için String alıyoruz.
            
            /* tüm gezegenleri gezip gezmediğini kontrol edebilmek için 
            secilen gezegeni gezegenlerin isimlerinin bulunduğu boolean dizisinde 
            secilen gezegenin karşılık geldiği boolean nesnesini true yapıyoruz*/
            for (int i = 0; i < hedefDizi.length; i++) {
                if (hedefDizi[i].equals(secim) && !kullaniciGirdisi[i]) {
                    kullaniciGirdisi[i] = true;
                    break;
                }
            }
            
            mevcutGezegen = galaksi.secimGönder(uzayAraci, secim ,oncekiKalkisMaliyet);
            /* secilen gezegen veya karadeliğin ismini secimGödner metoduna gönderip bulundugu gezegeni mevcutGezegen'e atıyor.(point ediyor) */ 
            
            if (mevcutGezegen != null) { // mevcutGezegen null'sa seçeneklerin sunulduğuna kısma giremez.
                
                /* karadelik bir gezegene gönderdi veya 
                biz direk gezegen seçtik şimdi seçenekler karşımıza geliyor */
                    System.out.println("\nNe yapmak istersiniz?");
                    System.out.println("1 - Kaynak Topla");
                    System.out.println("2 - Yakit Yenile");
                    System.out.println("3 - Gemi Gelistir");
                    System.out.println("4 - Baska bir gezegene git"); 
                    
                int devam = 0; // bunu içeride eğer 4 seçilirse seçenekler loopunu kapatmak için belirledik.
                while (devam != 4) {

                    int secenek = scan.nextInt();  // kullanıcıdan seçenek alıyoruz
                    scan.nextLine();
                    
                    switch (secenek) {
                        case 1 -> uzayAraci.kaynaklariTopla(mevcutGezegen);
                        case 2 -> uzayAraci.yakitYenile();
                        case 3 -> uzayAraci.araciGelistir();
                        case 4 -> {
                            oncekiKalkisMaliyet = mevcutGezegen.kalkisMaliyetiHesapla();
                            devam = 4;
                            System.out.println("Hangi gezegene gitmek istiyorsunuz? (A,B,C,D,E,F,G)");   
                        }
                        default -> System.out.println("Geçersiz seçim! Lütfen 1 ile 4 arasında seçim yapınız.");     
                
                
                    }
               }

            }
            // oyunun bitmesini kontrol eden kodlar ->>>>

            boolean yakitYetersiz = true; 

            for (Cisim cisim : galaksi.galaksi) { 
            /* tüm gezegenlerin iniş maliyetlerini kontrol et eğer hiçbirine inemiyorsak yakıtYetersiz true yap*/

                if(cisim instanceof Gezegen){
                    Gezegen gezegen = (Gezegen)cisim;
                    if (gezegen.inisMaliyetiHesapla() <= uzayAraci.getYakitSeviyesi()) {

                        yakitYetersiz = false; // eğer bir gezegene iniş yapılabiliyorsa yakıt yeterli
                            break; // döngüyü sonlandır çünkü bir gezegene iniş yapılabiliyor

                }
                }
            }

            // oyunun devam edip etmediğini kontrol etme kısmı
            if (uzayAraci.getYakitSeviyesi() == 0 || yakitYetersiz) { // yakıtımız 0'a eşitse veya hiçir gezegenin iniş maliyetine yetmiyorsa oyunu bitir.
                System.out.println("Yakitiniz bitti!\nOyun Bitti!");
                oyunDevam = false; // oyun döngüsünü sonlandır 
            }

            // ayrıca eğer kullanıcı tüm gezegenleri gezmişse, oyun sonlanacak
            boolean tumGezegenlerGezildi = true;
                for (boolean girdi : kullaniciGirdisi) {
                    if (!girdi) {
                        tumGezegenlerGezildi = false; // eğer bir gezegen gezilmediyse, oyun devam eder
                        break;
                    }
                }

                if (tumGezegenlerGezildi) {
                    System.out.println("Tüm gezegenler gezildi! Oyun sona erdi.");
                    oyunDevam = false;
                }

                // eğer yakıt bitmediyse ve tüm gezegenler gezilmediyse, oyun devam eder. ve yakıtı ekrana basar.
                if (oyunDevam) {
                    System.out.println("Yakit seviyeniz: " + uzayAraci.getYakitSeviyesi());
                }
                
        } 
    }
}
    




