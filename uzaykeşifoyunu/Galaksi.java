package uko;

import java.util.Random;

/**
 *
 * @author Murat
 */
public class Galaksi {

    public Cisim[] galaksi;                                             // gezegen ve karadelik nesnelerini saklayan cisim array
    private final Random random = new Random();                         // rastgele sayılar belirlemek için kullandığımız random sınıfı
    private final String[][] harita = new String[5][5];                 // kullanıcıya menü üzerinden gösterdiğimiz harita
    String[] harfler = {"A", "B", "C", "D", "E", "F", "G" ,"H", "I"};   // her gezegeni menü üzerinden harflendirmek için kullandığımız harfler dizisi

    private final int gezegenSayisi = 9; 
    /* fix olarak toplam 9 gezegen bulunmasını istedik ancak 
    tabii ki gezegen türlerinin sayısı rastgele belirleniyor*/
    
    private final int karadelikSayisi = random.nextInt(3) + 1; // [1-3] arasında rastgele sayıda karadelikSayisi belirleme aşaması.

    public Galaksi() {
        this.galaksi = new Cisim[(karadelikSayisi + gezegenSayisi)]; // galaksimizde gezegen ve karadelik sayisi kadar büyüklükte Cisim arrayi oluşturduk.

    }

    public void galaksiOlustur() { //rastgele gezegenler ve karadelikler oluşturup galaksi arrayine ekleme kısmı
        // rastgele gezegenler oluştur
        for (int i = 0; i < gezegenSayisi; i++) {
            // gezegenSayisi kadar rastgele gezegen oluşturup hem haritaya hem galaksi arrayine koy

            int x;
            int y;
            // rastgele oluşturduğumuz gezegen için koordinatları belirliyoruz.
            do {
                x = random.nextInt(5);
                y = random.nextInt(5);
            } while (harita[x][y] != null);
            // eğer haritada bu koordinat doluysa -> yeni koordinatlar seçiyor.

            /* burada farklı tür gezegenler için farklı aralıklarda yerçekimi oluşturmamın sebebi 
            mesela cüceUydu türünde düşük yerçekimi var onu 1-20 arası yerçekimi tutarsam 20 de denk gelebilir (20 en yüksek yer çekimi)
            o yüzden onun için yer çekimi2 diyip 1-5 arası yerçekimi seçtim*/
            int yerCekimi1 = random.nextInt(10) + 11; // [11-20] arası yer çekimi // yüksek yer çekimi
            int yerCekimi2 = random.nextInt(5) + 6; //  [6-10] arası yer çekimi   // orta yer çekimi
            int yerCekimi3 = random.nextInt(5) + 1; //  [1-5] arası yer çekimi   // düşük yer çekimi

            Gezegen gezegen = null;
            /* might not have been initalized error almamak için nulla atadım. 
            sonra zaten bunu bir gezegen türü olarak başlatıcaz.*/

            /* burada rastgele x,y konumu ve yerçekimi belirlediğimiz gezegenin 
            türünü de rastgele belirlemek için switch case kullanıp rastgele durumlardan birini seçtirdim*/
            switch ((int) (Math.random() * 4)) {
                case 0:
                    gezegen = new YasanabilirGezegen(harfler[i] + " Gezegeni", x, y, "Yasanabilir", yerCekimi1);
                    // yüksek yer çekimi , yüksek kaynak
                    break;
                case 1:
                    gezegen = new GazDevi(harfler[i] + " Gezegeni", x, y, "Zehirli", yerCekimi1);
                    // yüksek yer çekimi , orta kaynak
                    break;
                case 2:
                    gezegen = new CüceUydu(harfler[i] + " Gezegeni", x, y, "Kalin ve Karmasik Atmosfer", yerCekimi3);
                    // düşük yer çekimi , düşük kaynak
                    break;
                case 3:
                    gezegen = new BuzGezegeni(harfler[i] + " Gezegeni", x, y, "Soguk", yerCekimi2);
                    // orta yer çekimi , orta kaynak
                    break;

            }

            /* oluşturduğumuz obje (galaksi) arrayine gezegenimizi 
            ilk boş olan yere yerleştirmek için bu metodu kullandık.*/
            gezegenEkle(gezegen, gezegen.getXKonumu(), gezegen.getYKonumu(), gezegen.getIsim().charAt(0) + "");

            /* ve bu gezegenin ismini de (yani A,B,C,D.. gibi) oluşturttuğumuz ve boş olduğunu yukarıda kontrol ettiğimiz 
            rastgele x ve y konumlarına harita(String) arrayimizde yerleştiriyoruz.*/
        }

        /* rastgele x ve y konumlu ve karadelikSayisi kadar 
        karadelikler oluşturmak için loop kullandık */
        for (int i = 0; i < karadelikSayisi; i++) {
            String isim = "Karadelik-" + (i + 1);

            int x; // xKonumu
            int y; // yKonumu

            do {
                x = random.nextInt(5);
                y = random.nextInt(5);
            } while (harita[x][y] != null);
            // karadelik için eğer haritada bu koordinat doluysa -> yeni koordinatlar seçiyor.

            Karadelik karadelik = new Karadelik(isim, x, y);

            /* karadelik nesnesi oluşturup aşağıdaki metodta galaksi(obje) arrayimize koyuyoruz.*/
            karadelikEkle(karadelik, karadelik.getXKonumu(), karadelik.getYKonumu(), karadelik.getIsim().charAt(0) + "");
            /* karadeliğin isminin ilk harfini 
            (ör: Karadelik 3 --> 'K' string'e çevirip x ve y konumuna göre haritaya yazıyoruz*/

        }

        // burada haritada belirtilen uzay keşif aracını yani "U" haritada boş bir yere koyuyoruz.
        int x; // xKonumu
        int y; // yKonumu

        do {
            x = random.nextInt(5);
            y = random.nextInt(5);
        } while (harita[x][y] != null);
        harita[x][y] = "U"; // uzay keşif aracı

        //oyun başlangıç metni  
        System.out.println("""
                           === Uzay Kesif Oyunu ===
                           Baslangic Ozellikleriniz:
                           -Yakit:100 birim
                           -Kargo Kapasitesi: 50 birim""");
        /* galaksiOlustur islemleri bittikten sonra kullanıcıya gerekli bilgileri veriyoruz*/
        System.out.println("\nGalakside " + gezegenSayisi + " gezegen var." + "\nHedefinizi secin ve kesfe baslayin." + "\n");
        System.out.println("""
                           [U] -> Uzay Kesif Araci 
                           [A] -> Gezegen A
                           [B] -> Gezegen B
                           [C] -> Gezegen C
                           [D] -> Gezegen D
                           [E] -> Gezegen E
                           [F] -> Gezegen F
                           [G] -> Gezegen G
                           [H] -> Gezegen H
                           [I] -> Gezegen I
                           [K] -> Karadelik K
                           
                           Hangi gezegene gitmek istersiniz? (A,B,C,D,E,F,G)
                           """);

    }

    public void gezegenEkle(Gezegen gezegen, int x, int y, String isim) { // gezegenleri hem haritaya hem galaksi arrayine ekler

        harita[x][y] = isim;

        for (int i = 0; i < galaksi.length; i++) {
            if (galaksi[i] == null) {
                galaksi[i] = gezegen;
                break;
            }
        }
    }

    public void karadelikEkle(Karadelik karadelik, int x, int y, String isim) { // karadelik nesnelerini hem haritaya hem galaksi arrayine ekler

        harita[x][y] = isim;

        for (int i = 0; i < galaksi.length; i++) {
            if (galaksi[i] == null) {
                galaksi[i] = karadelik;
                break;
            }
        }
    }

    public void galaksiyiGoster() { // haritamızı göstermek için kullandığımız metod
        System.out.println("Galaksi Haritasi:");
        for (int i = 0; i < harita.length; i++) { // Satırlar
            System.out.println("-------------------------");

            for (int j = 0; j < harita[i].length; j++) { // Sütunlar
                if (harita[i][j] != null) {

                    System.out.print("| " + harita[i][j] + " |");
                } else {
                    System.out.print("|   |");
                }

            }
            // her satırı batıktan sonra yeni bir satır başlatıyoruz
            System.out.println();
        }
        System.out.println("-------------------------");

        
    }

    public Gezegen secimGönder(UzayKesifAraci uzayAraci, String secim , int kalkisMaliyeti) {    
        Gezegen mevcutGezegen = null; // mevcutGezegeni return edebilmek için loopun dışında başlatıyoruz.
        
        for (Cisim cisim : galaksi) { 
        // girilen secimle uyuşan cisimi(karadelik,gezegen) bulmak  için tüm galaksi arrayini dolaştırıyoruz.

            // kullanıcının girdiği harfle eşleşen bir nesne bulduk mu? bulduysak gir.
            if (cisim.getIsim().substring(0, 1).equalsIgnoreCase(secim)) { 

                /* bu nesne gezegen mi? - eğer gezegense ve 
                yakıtı bu gezegene inmeye yetiyorsa uzay aracının gezegeneGit() metodunu çağır.
                yakıtı bu  gezegen inmeye yetmiyorsa girme , kullanıcıya bildir ve mevcutGezegen null değer döndür*/
                if (cisim instanceof Gezegen) { 
                    Gezegen gezegen = (Gezegen) cisim;

                    // yakıt kontrolü
                    if (uzayAraci.getYakitSeviyesi() >= gezegen.inisMaliyetiHesapla()+ kalkisMaliyeti) {
                        uzayAraci.gezegeneGit(gezegen,kalkisMaliyeti);
                        mevcutGezegen = gezegen; // mevcut gezegeni döndür
                        break;
                    } else {
                        System.out.println("Yakitiniz bu gezegene gitmek icin yeterli degil.");
                        mevcutGezegen = null;// yakıt yetersizse hiçbir gezegende değiliz. mevcutGezegen = null;
                        break;
                    }
                    
                }

                /* karadelikse cisimi karadeliğe cast et ve karadelik.calis() metodunu çağır
                ve karadeliğin gönderdiği gezegeni döndür*/
                else if (cisim instanceof Karadelik) {
                    
                    Karadelik karadelik = (Karadelik) cisim;
                    System.out.println("Karadelige girdiniz...");
                    mevcutGezegen = karadelik.calis(uzayAraci, galaksi); // karadeliğin gönderdiği gezegen 
                    break;
                }
                
            }
     }
        return mevcutGezegen; //mevcutGezegeni maine gönderiyoruz.
    
   }
    
    
}
