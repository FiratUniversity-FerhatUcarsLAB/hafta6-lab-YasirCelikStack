/**
 * Ad Soyad: Muhammet Yasir Çelik
 * Öğrenci No: 250542010
 * Proje: Restoran Siparişi
 * Tarih: 19.11.2025
 */

import java.util.Scanner;

public class RestoranSiparis{

    // 1) Ana yemek fiyatı (1–4), 0 veya yanlış giriş = 0 TL
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1:  // Izgara Tavuk
                return 85;
            case 2:  // Adana Kebap
                return 120;
            case 3:  // Levrek
                return 110;
            case 4:  // Mantı
                return 65;
            default:
                return 0; // seçilmedi
        }
    }

    // 2) Başlangıç fiyatı (0–3)
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1:  // Çorba
                return 25;
            case 2:  // Humus
                return 45;
            case 3:  // Sigara Böreği
                return 55;
            default:
                return 0;
        }
    }

    // 3) İçecek fiyatı (0–4)
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1:  // Kola
                return 15;
            case 2:  // Ayran
                return 12;
            case 3:  // Taze Meyve Suyu
                return 35;
            case 4:  // Limonata
                return 25;
            default:
                return 0;
        }
    }

    // 4) Tatlı fiyatı (0–4)
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1:  // Künefe
                return 65;
            case 2:  // Baklava
                return 55;
            case 3:  // Sütlaç
                return 35;
            case 4:  // Dondurma
                return 35;
            default:
                return 0;
        }
    }

    // 5) Combo mu? (ana, içecek ve tatlı VAR MI?)  -> 1 = evet, 0 = hayır
    public static int isComboOrder(int anaSecim, int icecekSecim, int tatliSecim) {

        if (anaSecim != 0 && icecekSecim != 0 && tatliSecim != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    // 6) Happy hour mı? 14–17 arası saat -> 1 = evet, 0 = hayır
    public static int isHappyHour(int saat) {

        if (saat >= 14 && saat <= 17) {
            return 1;
        } else {
            return 0;
        }
    }

    // 7) İndirim hesaplama
    // comboFlag: 1/0, ogrenciFlag: 1/0, gun: (1=Pts ... 7=Paz)
    // icecekTutar: sadece içecek toplamı (happy hour için)
    public static double calculateDiscount(double araToplam,
                                           int comboFlag,
                                           int ogrenciFlag,
                                           int saat,
                                           int gun,
                                           double icecekTutar) {

        double toplamIndirim = 0;

        // 1) Combo indirim: %15 (ana+icecek+tatlı varsa)
        if (comboFlag == 1) {
            double comboIndirim = araToplam * 0.15;
            toplamIndirim += comboIndirim;
            System.out.printf("Combo indirimi (%%15): -%.2f TL%n", comboIndirim);
        }

        // 2) Happy hour: 14–17 arası, içeceklerde %20 indirim
        int happyFlag = isHappyHour(saat);
        if (happyFlag == 1 && icecekTutar > 0) {
            double happyIndirim = icecekTutar * 0.20;
            toplamIndirim += happyIndirim;
            System.out.printf("Happy Hour içecek indirimi (%%20): -%.2f TL%n", happyIndirim);
        }

        // 3) 200 TL üzeri: %10 indirim (araToplam - şimdiye kadar ki indirimler > 200 ise)
        double suAnkiTutar = araToplam - toplamIndirim;
        if (suAnkiTutar > 200) {
            double ikiYuzIndirim = suAnkiTutar * 0.10;
            toplamIndirim += ikiYuzIndirim;
            System.out.printf("200 TL üzeri indirim (%%10): -%.2f TL%n", ikiYuzIndirim);
        }

        // 4) Öğrenci: Hafta içi (1–5) ekstra %10
        if (ogrenciFlag == 1 && gun >= 1 && gun <= 5) {
            suAnkiTutar = araToplam - toplamIndirim;
            double ogrenciInd = suAnkiTutar * 0.10;
            toplamIndirim += ogrenciInd;
            System.out.printf("Öğrenci indirimi (hafta içi %%10): -%.2f TL%n", ogrenciInd);
        }

        return toplamIndirim;
    }

    // 8) Bahşiş önerisi: tutarın %10’u
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // MAIN – Kullanıcıdan tüm seçimler alınır
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Akıllı Restoran Sipariş Sistemi ===");

        System.out.println("Ana Yemek seçimi (1=Izgara Tavuk, 2=Adana, 3=Levrek, 4=Mantı, 0=Yok): ");
        int anaSecim = input.nextInt();

        System.out.println("Başlangıç seçimi (1=Çorba, 2=Humus, 3=Sigara Böreği, 0=Yok): ");
        int baslangicSecim = input.nextInt();

        System.out.println("İçecek seçimi (1=Kola, 2=Ayran, 3=Meyve Suyu, 4=Limonata, 0=Yok): ");
        int icecekSecim = input.nextInt();

        System.out.println("Tatlı seçimi (1=Künefe, 2=Baklava, 3=Sütlaç, 4=Dondurma, 0=Yok): ");
        int tatliSecim = input.nextInt();

        System.out.println("Saat (8–23 arası): ");
        int saat = input.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        char ogrChar = input.next().toUpperCase().charAt(0);
        int ogrenciFlag = (ogrChar == 'E') ? 1 : 0;

        System.out.print("Hangi gün? (1=Pzt, 2=Sal, 3=Çar, 4=Per, 5=Cuma, 6=Cts, 7=Paz): ");
        int gun = input.nextInt();

        // Fiyatları hesapla
        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        double araToplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        System.out.println("\n--- Sipariş Özeti ---");
        System.out.printf("Ara toplam: %.2f TL%n", araToplam);

        int comboFlag = isComboOrder(anaSecim, icecekSecim, tatliSecim);

        // İndirimleri hesapla ve yazdır (hesaplama sırasında tek tek yazdırıyoruz)
        double toplamIndirim = calculateDiscount(araToplam,
                                                 comboFlag,
                                                 ogrenciFlag,
                                                 saat,
                                                 gun,
                                                 icecekFiyat);

        double odenecekTutar = araToplam - toplamIndirim;

        System.out.printf("Toplam İndirim: -%.2f TL%n", toplamIndirim);
        System.out.printf("Ödenecek Tutar: %.2f TL%n", odenecekTutar);

        double bahsis = calculateServiceTip(odenecekTutar);
        System.out.printf("Bahşiş önerisi (%%10): %.2f TL%n", bahsis);

        System.out.println("\nAfiyet olsun aga! 😎");
    }
}

