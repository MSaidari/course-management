
package course_management;

import java.util.ArrayList;

public class kursiyer implements hesaplama{
	private int kursiyerid,kursiyeryas;
	private String kursiyeradsoyad;
	public ArrayList<kurs> alinankurslar;
	
	@Override
	public void borchesapla(int syc) {
		double kampanya=0;
		if(syc==2) {
			kampanya=(500+500*0.8)*4;
			System.out.println("uygulanan kampanya:1");
		}else if(syc==3) {
			kampanya = (500+500+500*0.75)*4;
			System.out.println("uygulanan kampanya:2");
		}else if(syc>3) {
			kampanya = (syc*500*4)*0.90;
			System.out.println("uygulanan kampanya:3");
		}else {
			kampanya = 500*4;
			System.out.println("kampanya uygulanmadı!");
		}
		System.out.println("aylık ödenecek ücret:"+kampanya);
	}

	public kursiyer(int kursiyerid, int kursiyeryas, String kursiyeradsoyad) {
		super();
		this.kursiyerid = kursiyerid;
		this.kursiyeryas = kursiyeryas;
		this.kursiyeradsoyad = kursiyeradsoyad;
	}
	
	public kursiyer() {
		super();
	}

	public int getKursiyerid() {
		return kursiyerid;
	}

	public void setKursiyerid(int kursiyerid) {
		this.kursiyerid = kursiyerid;
	}

	public int getKursiyeryas() {
		return kursiyeryas;
	}

	public void setKursiyeryas(int kursiyeryas) {
		this.kursiyeryas = kursiyeryas;
	}

	public String getKursiyeradsoyad() {
		return kursiyeradsoyad;
	}

	public void setKursiyeradsoyad(String kursiyeradsoyad) {
		this.kursiyeradsoyad = kursiyeradsoyad;
	}
	
}
