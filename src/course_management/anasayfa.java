
package course_management;
import java.io.*;

import java.util.*;

public class anasayfa {

	public static void main(String[] args) {
		kursiyer kursiyernesne = new kursiyer();
		File kursiyerf = new File("kursiyer.txt");
		File kursf = new File("kurs.txt");
		ArrayList<kursiyer> kursiyerler= new ArrayList<kursiyer>();
		ArrayList<kurs> Kurs = new ArrayList<kurs>();
		String okunansatir;
		String [] okunandizisi;
		try {
			if(!kursiyerf.exists()) {
				System.out.println("dosya bulunamadı.");
			}else {
				
				kursiyer nesne =null;
				FileReader fr = new FileReader(kursiyerf);
				BufferedReader br = new BufferedReader(fr);
				while((okunansatir=br.readLine())!=null) {
					StringTokenizer token = new StringTokenizer(okunansatir.substring(1), "+");
					if (okunansatir.charAt(0) == '*') {
					        
					    int id = Integer.parseInt(token.nextToken());
					    String isim = token.nextToken();
					    int yas = Integer.parseInt(token.nextToken());
					    nesne = new kursiyer(id, yas, isim);
					    kursiyerler.add(nesne); 
					    nesne.alinankurslar = new ArrayList();
					}else {
						int id = Integer.parseInt(token.nextToken());
						String isim = token.nextToken();
						nesne.alinankurslar.add(new kurs(id,isim));	
					}
					    
				}
				
			}	
		}catch(IOException e) {
			e.getStackTrace();
		}
		try {
			if(!kursf.exists()) {
				System.out.println("dosya bulunamadı.");
			}else {
				FileReader fr = new FileReader(kursf);
				BufferedReader br = new BufferedReader(fr);
				while((okunansatir=br.readLine())!=null) {
					okunandizisi = okunansatir.split("\\+");
					int id = Integer.parseInt(okunandizisi[0]);
					String isim = okunandizisi[1];
					Kurs.add(new kurs(id,isim));
				}
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		
		menu(kursiyerler,Kurs);
		
	}

	private static void menu(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		int secim,id;
		
		Scanner veri = new Scanner(System.in);
		System.out.println("1 – Kurs Ekle\r\n"
				+ "2 – Kurs Listele\r\n"
				+ "3 – Kurs Ara\r\n"
				+ "4 – Kurs Sil\r\n"
				+ "5 – Kursiyer Ekle\r\n"
				+ "6 – Kursiyer Ara\r\n"
				+ "7 – Kursiyer Sil\r\n"
				+ "8 – Kursiyerleri Listele\r\n"
				+ "9 – Kursiyerleri Ayrıntılı Listele\r\n"
				+ "10 – Kursiyerin Ödeyeceği Tutarı Hesapla\r\n"
				+ "11 – Çıkış\n\n");
		System.out.println("NOT:ekleme ve silme işlemlerinde büyük küçük harfe dikkat ediniz.");
		System.out.println("bir secim yapiniz:");
		secim=veri.nextInt();
		switch(secim) {
		case 1:
			kursekle(kursiyerler,kurs);
			break;
		case 2:
			kurslistele(kursiyerler,kurs);
			break;
		case 3:
			kursara(kursiyerler,kurs);
			break;
		case 4:
			kurssil(kursiyerler,kurs);
			break;
		case 5:
			kursiyerekle(kursiyerler,kurs);
			break;
		case 6:
			kursiyerara(kursiyerler,kurs);
			break;
		case 7:
			kursiyersil(kursiyerler,kurs);
			break;
		case 8:
			kursiyerlistele(kursiyerler,kurs);
			break;
		case 9:
			ayrntlkursiyerlistele(kursiyerler,kurs);
			break;
		case 10:
			System.out.println("ödeyecegi tutarı hesaplancak kurssiyer id giriniz:");
			id=veri.nextInt();
			kursiyerhesapla(kursiyerler,kurs,id);
			break;
		case 11:
			File kursiyerf = new File("kursiyer.txt");
			File kursf = new File("kurs.txt");
			try {
				FileWriter fw = new FileWriter(kursiyerf);
				BufferedWriter bw = new BufferedWriter(fw);
				for(kursiyer Kursiyer : kursiyerler) {
					bw.write("*"+Kursiyer.getKursiyerid()+"+"+Kursiyer.getKursiyeradsoyad()+"+"+Kursiyer.getKursiyeryas()+"\n");
					for(kurs Kurs : Kursiyer.alinankurslar) {
						bw.write("%"+Kurs.getKursid()+"+"+Kurs.getKursad()+"\n");
					}
				}
				
				fw.flush();
				bw.flush();
				fw.close();
				bw.close();
				FileWriter fwk = new FileWriter(kursf);
				BufferedWriter bwk = new BufferedWriter(fwk);
				for(kurs Kurs: kurs) {
					bwk.write(Kurs.getKursid()+"+"+Kurs.getKursad()+"\n");
				}
				
				fwk.flush();
				bwk.flush();
				fwk.close();
				bwk.close();
			}catch(IOException e) {
				e.getStackTrace();
			}
			
			System.out.println("çıkış yapiliyor...");
			break;
		default:
			System.out.println("yanlis secim tekrar deneyin");
				menu(kursiyerler,  kurs);
		}
	}

	private static void kursiyerhesapla(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs, int id) {
		int syc=0,kontrol=0;
		for(kursiyer Kursiyer : kursiyerler) {
			if(Kursiyer.getKursiyerid()==id) {
				kontrol=1;
				for(kurs Kurs : Kursiyer.alinankurslar) {
					syc++;
				}
				if(syc==2) {// 1.kampanya
					Kursiyer.borchesapla(syc);
					
				}else if(syc==3) {// 2.kampanya
					Kursiyer.borchesapla(syc);
				}else if(syc>3) {// 3.kampanya 
					Kursiyer.borchesapla(syc);
				}else {
					Kursiyer.borchesapla(syc); //kampanya yok
				}
			}
		}
		if(kontrol==0) {
			System.out.println("aranan id de kursiyer yok");
		}
		menu(kursiyerler, kurs);
	}

	private static void kursiyersil(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		int id;
		Scanner veri = new Scanner(System.in);
		System.out.println("silinecek kursiyer id giriniz:");
		id=veri.nextInt();
		int kontrol=0;
		for(kursiyer Kursiyer : kursiyerler) {
			if(Kursiyer.getKursiyerid()==id) {
				kursiyerler.remove(Kursiyer);
				System.out.println("kursiyer silindi!");
				kontrol=1;
				break;
			}
		}
		if(kontrol==0) {
			System.out.println("aranan id de kursiyer yok tekrar deneyin.");
			kursiyersil(kursiyerler, kurs);
			return;
		}
		menu(kursiyerler, kurs);
	}

	private static void kursiyerara(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		int id;
		String ad_soyad;
		Scanner veri = new Scanner(System.in);
		System.out.println("aranan kursiyer id girin:");
		id=veri.nextInt();
		veri.nextLine();
		System.out.println("aranan kursiyer ad soyad girin:");
		ad_soyad= veri.nextLine();
		int kontrol=0;
		for(kursiyer Kursiyer : kursiyerler) {
			if((Kursiyer.getKursiyeradsoyad()).equals(ad_soyad)) {
				kontrol =1;
				System.out.println(Kursiyer.getKursiyerid()+" "+Kursiyer.getKursiyeradsoyad()+" "+Kursiyer.getKursiyeryas());
				for(kurs Kurs : Kursiyer.alinankurslar) {
					System.out.println("\t"+Kurs.getKursid()+" "+Kurs.getKursad());
				}
				break;
		}
		}if(kontrol==0) {
			System.out.println("aradıgınız isimde kursiyer yoktur");
			
		}
		menu(kursiyerler, kurs);
	}

	private static void kursiyerekle(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		int id,kursid,yas,adet;
		String ad_soyad,kursad;
		Scanner veri = new Scanner(System.in);
		System.out.println("eklenecek kursiyer id girin:");
		id=veri.nextInt();
		veri.nextLine();
		System.out.println("eklenecek kursiyer isim girin:");
		ad_soyad=veri.nextLine();
		System.out.println("eklenecek kursiyer yas giriniz:");
		yas = veri.nextInt();
		int kontrol=0;
		for(kursiyer Kursiyer :kursiyerler) {
			if(Kursiyer.getKursiyerid()==id) {
				
				kontrol=1;
				break;
			}
		}
		if(kontrol==1) {
			System.out.println("bu id de kursiyer var tekrar deneyin.");
			kursiyerekle(kursiyerler, kurs);
			return;
		}else {
			kursiyer nesne =new kursiyer(id,yas,ad_soyad);
			kursiyerler.add(nesne);

			System.out.println("kac kursa kaydolacak ?");
			adet = veri.nextInt();
			nesne.alinankurslar = new ArrayList();
			
			for(int i=0;i<adet;i++) {
			
				System.out.println((i+1)+".kursun id sini girin:");
				kursid = veri.nextInt();
				veri.nextLine();
				System.out.println((i+1)+".kursun ismini girin:");
				kursad=veri.nextLine();
				int kontrol2=0;
				for(kurs Kurs : kurs) {
					if(kursid==Kurs.getKursid() && (Kurs.getKursad()).equals(kursad)) {
						kontrol2=1;
						nesne.alinankurslar.add(new kurs(kursid,kursad));
						break;
					}
				}
				if(kontrol2==0) {
					System.out.println("girilen id veya isimde kurs yok!");
					
				}
				
			}
			System.out.println("kursiyer eklendi!");
		}
		menu(kursiyerler,kurs);
	}

	private static void kurssil(ArrayList<kursiyer> kursiyerler,  ArrayList<kurs> kurs) {
		String isim;
		Scanner veri = new Scanner(System.in);
		System.out.println("silmek istediğiniz kurs adı giriniz:");
		isim = veri.nextLine();
		int kontrol=0,kontrol2=0;
		for(kurs Kurs : kurs) {
			if((Kurs.getKursad()).equals(isim)) {
				kontrol =1;
				break;	
			}
		}
		if(kontrol==0) {
			System.out.println("aranan isimde kurs yok tekrar deneyin.");
			kurssil(kursiyerler,kurs);
			return;
		}
		for(kursiyer Kursiyer : kursiyerler) {
			for(kurs Kurs : Kursiyer.alinankurslar) {
				if((Kurs.getKursad()).equals(isim)) {
					kontrol2 =1;
					break;
				}
			}
			if(kontrol2==1) {
				break;
			}
		}
		if(kontrol2==1) {
			System.out.println("kurs silinemedi.bu kursu alan kursiyer var");
		}else {
			for(kurs Kurs : kurs) {
				if((Kurs.getKursad()).equals(isim)) {
					kurs.remove(Kurs);
					System.out.println("kurs silindi");
					break;
				}
				
			}
			
		}
		menu(kursiyerler,kurs);
	}

	private static void kursara(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		String isim;
		Scanner veri = new Scanner(System.in);
		System.out.println("aradıgınız kursun ismini girin:");
		isim=veri.nextLine();
		int kontrol=0;
		for(kurs Kurs : kurs) {
			if(Kurs.getKursad().equals(isim)) {
				System.out.println("kurs bulundu:"+Kurs.getKursid()+" "+Kurs.getKursad());
				kontrol=1;
				break;
			}
		}
		if(kontrol==0) {
			System.out.println("kurs bulunamadı :(");
		}
		menu(kursiyerler,kurs);
	}

	private static void kurslistele(ArrayList<kursiyer> kursiyerler,  ArrayList<kurs> kurs) {
		for(kurs Kurs : kurs) {
			System.out.println(Kurs.getKursid()+" "+Kurs.getKursad());
		}
		menu(kursiyerler,kurs);
	}

	private static void kursekle(ArrayList<kursiyer> kursiyerler,  ArrayList<kurs> kurs) {
		int id;
		String isim;
		Scanner veri = new Scanner(System.in);
		
		System.out.println("kurs id giriniz:");
		id=veri.nextInt();
		veri.nextLine();
		System.out.println("kurs isim giriniz:");
		isim = veri.nextLine();
		int kontrol=0;
		for(kurs Kurs : kurs) {
			
			if(Kurs.getKursid()==id || (Kurs.getKursad().equals(isim))) {
	
				kontrol =1;
				break;	
			}
			
		}
		if(kontrol == 0) {
			
			kurs.add(new kurs(id,isim));
			System.out.println("kurs eklendi !");
		}else {
			
			System.out.println("bu id veya isimde kurs var tekrar deneyiniz:");
			kursekle(kursiyerler, kurs);
			return;
		}
		
		menu(kursiyerler,kurs);
	}

	private static void ayrntlkursiyerlistele(ArrayList<kursiyer> kursiyerler,   ArrayList<kurs> kurs) {
		for(kursiyer kur : kursiyerler) {
			System.out.println(kur.getKursiyerid()+ " "+ kur.getKursiyeradsoyad()+ " "+ kur.getKursiyeryas());
			for(kurs Kurslar : kur.alinankurslar) {
				System.out.println("\t"+Kurslar.getKursid()+" "+Kurslar.getKursad());
			}
		}
		menu(kursiyerler, kurs);
	}

	private static void kursiyerlistele(ArrayList<kursiyer> kursiyerler, ArrayList<kurs> kurs) {
		for(kursiyer kur : kursiyerler) {
			System.out.println(kur.getKursiyerid()+ " "+ kur.getKursiyeradsoyad()+ " "+ kur.getKursiyeryas());
			
		}
		menu(kursiyerler,kurs);
	}

}
