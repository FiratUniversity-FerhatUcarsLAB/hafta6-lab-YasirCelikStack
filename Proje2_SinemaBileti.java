/**
 * Ad Soyad: Muhammet Yasir Çelik
 * Öğrenci No: 250542010
 * Proje: Not Sistemi
 * Tarih: 19.11.2025
 */




import java.util.Scanner;

public class SinemaBileti {

    // 1) Gün türü belirleme (boolean yok → String döndürüyoruz)
    public static String getDayType(int gun) {

        switch (gun) {
            case 1: case 2: case 3: case 4: case 5:
                return "haftaici";

            case 6: case 7:
                return "haftasonu";

            default:
                return "gecersiz";
        }
    }

    // 2) Matine / Normal belirleme (boolean yok)
    public static String getShowType(int saat) {
        if (saat < 12)
            return "matine";
        else
            return "normal";
    }

    // 3) Temel fiyat hesaplama
    public static int calculateBasePrice(int gun, int saat) {

        String dayType = getDayType(gun);
        String showType = getShowType(saat);

        if (dayType.equals("haftaici")) {
            if (showType.equals("matine")) return 45;
            else return 65;
        }
        else if (dayType.equals("haftasonu")) {
            if (showType.equals("matine")) return 55;
            else return 85;
        }

        return -1; // Geçersiz gün
    }

    // 4) İndirim oranı hesaplama (yüzde)
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // Yaş indirimleri
        if (yas >= 65) return 0.30;
        if (yas < 12) return 0.25;

        // Meslek (switch-case)
        switch (meslek) {

            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) return 0.20; // Pzt–Per
                else return 0.15; // Cuma–Pazar

            case 2: // Öğretmen
                if (gun == 3) return 0.35; // Çarşamba
                else return 0.0;

            case 3: // Diğer
                return 0.0;

            default:
                return 0.0;
        }
    }

    // 5) Film formatı ekstra ücreti
    public static int getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {

        int base = calculateBasePrice(gun, saat);
        double discount = calculateDiscount(yas, meslek, gun);
        int extra = getFormatExtra(filmTuru);

        double discounted = base - (base * discount);

        return discounted + extra;
    }

    // 7) Bilet bilgisi oluşturma
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {

        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        return  "----- SİNEMA BİLETİ -----\n" +
                "Gün: " + gun + "\n" +
                "Saat: " + saat + ":00\n" +
                "Yaş: " + yas + "\n" +
                "Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): " + meslek + "\n" +
                "Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): " + filmTuru + "\n" +
                "Toplam Fiyat: " + finalPrice + " TL\n";
    }

    // MAIN → bütün seçimler kullanıcıdan isteniyor
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("----- SİNEMA BİLETİ SİSTEMİ -----");

        System.out.println("Gün seç (1=Pzt, 2=Sal, 3=Çar, 4=Per, 5=Cum, 6=Cts, 7=Paz): ");
        int gun = input.nextInt();

        System.out.println("Saat gir (0-23): ");
        int saat = input.nextInt();

        System.out.println("Yaş gir: ");
        int yas = input.nextInt();

        System.out.println("Meslek seç: ");
        System.out.println("1) Öğrenci");
        System.out.println("2) Öğretmen");
        System.out.println("3) Diğer");
        int meslek = input.nextInt();

        System.out.println("Film türü seç: ");
        System.out.println("1) 2D");
        System.out.println("2) 3D");
        System.out.println("3) IMAX");
        System.out.println("4) 4DX");
        int filmTuru = input.nextInt();

        System.out.println();
        System.out.println(generateTicketInfo(gun, saat, yas, meslek, filmTuru));
    }
}

