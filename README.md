# 🚀 Uzay Keşif Oyunu (Space Exploration Game)

Bu proje, **Bilgisayar Programlama I** dersi kapsamında geliştirilmiş, metin tabanlı bir uzay simülasyon oyunudur. 
Oyuncu, sınırlı yakıt ve kapasite ile galaksideki gezegenleri keşfetmeye, kaynak toplamaya ve hayatta kalmaya çalışır.

## 📖 Oyun Hakkında
Oyunun amacı, uzay keşif aracının yakıtını bitirmeden mümkün olduğunca çok gezegeni keşfetmek ve kaynak toplamaktır. Galaksi, rastgele oluşturulan gezegenler ve karadeliklerden oluşur.

### Temel Özellikler
* **Dinamik Galaksi:** Oyun başında rastgele konum, kaynak ve atmosfer özelliklerine sahip gezegenler üretilir.
* **Karadelikler:** Oyuncuyu rastgele bir gezegene ışınlar (bazen yakıt harcamadan, bazen kaynak tüketerek).
* **Kaynak Yönetimi:** Toplanan madenler (altın, gümüş vb.) gemiyi geliştirmek veya yakıt almak için kullanılır.
* **Fizik Mekanikleri:** Her gezegenin yerçekimi farklıdır; yerçekimi arttıkça iniş/kalkış maliyeti artar.

## 🎮 Oynanış

Oyun sıra tabanlı bir menü sistemiyle ilerler:
1. **Galaksi Haritası:** Oyuncu gezegenleri ve kendi konumunu görür.
2. **Seyahat:** Hedef gezegen seçilir. Mesafe ve gemi durumuna göre yakıt harcanır.
3. **Etkileşim:** Gezegene inildiğinde oyuncuya seçenekler sunulur:
    * **Kaynak Topla:** Depo kapasitesine göre maden toplar.
    * **Yakıt Yenile:** 1 Birim Kaynak = 2 Birim Yakıt.
    * **Gemi Geliştir:** 1 Birim Kaynak = 5 Birim Kapasite.
4. **Bitiş:** Yakıt bittiğinde veya tüm gezegenler keşfedildiğinde oyun sona erer.

## 🛠️ Teknik Yapı ve Sınıf Hiyerarşisi

Proje, **Nesne Yönelimli Programlama (OOP)** prensiplerine uygun olarak tasarlanmıştır.

* **Cisim (Abstract/Base Class):** Galaksideki tüm nesnelerin atasıdır. `Gezegen` ve `Karadelik` sınıfları buradan türetilmiştir.
* **Gezegen:** Kaynak miktarı, atmosfer durumu ve yerçekimi özelliklerini tutar.
    * *Alt Sınıflar:* `YasanabilirGezegen`, `GazDevi` vb. (Kalıtım kullanılmıştır).
* **UzayKesifAraci:** Yakıt seviyesi (Başlangıç: 100), Kapasite (Başlangıç: 50) ve toplanan kaynakları yönetir.
* **Galaksi:** Cisim nesnelerini bir dizi içinde saklar ve yönetir.

## 🚀 Kurulum ve Çalıştırma

Proje **Java** dili ile geliştirilmiştir. 
Çalıştırmak için JDK (Java Development Kit) gereklidir.

1. Proje dosyasını indirin (Zip içerisinden çıkarın).
2. Terminal veya Komut İstemi'ni açın.
3. Kaynak kodların olduğu dizine gidin.
4. Derleme ve çalıştırma için:

```bash
javac *.java
java Main
