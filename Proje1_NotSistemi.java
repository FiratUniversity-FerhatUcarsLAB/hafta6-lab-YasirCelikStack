/**
 * Ad Soyad: Muhammet Yasir Çelik
 * Öğrenci No: 250542010
 * Proje: Not Sistemi
 * Tarih: 19.11.2025
 */


import java.util.Scanner;

public class ogr_Not{


    public static double calculateAvarage(double vizE,double finaL,double odeV){
    
        double ortalama = vizE * 0.3 + finaL * 0.4 + odeV * 0.3 ;
        return ortalama;
    }
    
    public static String isPassingGrade(double ortalama){
        String mesaj ;
        if(ortalama>=50)
            mesaj = "GEÇTİ";
        else
            mesaj = "KALDI";

    return mesaj;
    }

    public static String getLetterGrade(double ortalama)
    {
        String harf_notu;
        if(ortalama >= 90 & ortalama <=100)
            harf_notu = "AA";
        else if(ortalama >=80 & ortalama <90)
            harf_notu = "BB";
        else if(ortalama >=70 & ortalama<80)
            harf_notu = "CC";
        else if(ortalama >=60 & ortalama <70)
            harf_notu = "DD" ;
        else
            harf_notu = "FF";
    
    return harf_notu;
    }

    public static String isHonorList(double ortalama,double vizE,double finaL,double odev)
    {
        String onur_listesi;
        if(ortalama>=85 & vizE>=70 & finaL >=70 & odev>=70)
            onur_listesi = "EVET";
        else
            onur_listesi = "HAYIR";
    return onur_listesi;
    }



    public static String hasRetakeRight(double ortalama){

        String but_hakki ;
        if(ortalama<50 & ortalama >=40)
            but_hakki = "EVET" ; 
        else
            but_hakki = "HAYIR";
    return but_hakki;
    }


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lütfen vize notunuzu giriniz : ");
        double vize = scanner.nextDouble();
        System.out.println("Lütfen final notunuzu giriniz : ");
        double finall = scanner.nextDouble();
        System.out.println("Lütfen ödev notunuzu giriniz : ");
        double odev = scanner.nextDouble();
        double ortalama = calculateAvarage(vize, finall, odev);
        String durum = isPassingGrade(ortalama);
        String harf_notu = getLetterGrade(ortalama);
        String onur_listesi = isHonorList(ortalama, vize, finall, odev);
        String but_hakki = hasRetakeRight(ortalama);

        System.out.println();

        System.out.println();

        System.out.println();


        System.out.println("=== OGRENCI NOT RAPORU ===");

        System.out.println("Vize Notu : "+ vize);
        System.out.println("Final Notu : "+ finall);
        System.out.println("Odev Notu : "+ odev);
        System.out.println("------------------------------");   
        System.out.println("Ortalama : " + calculateAvarage(vize, finall, odev));
        System.out.println("Harf Notu : " + harf_notu);
        System.out.println("Durum : "+ durum);
        System.out.println("Onur Listesi : " + onur_listesi);
        System.out.println("Butunleme : " + but_hakki);

    }










}

