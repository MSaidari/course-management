
package course_management;

public class kurs {
	private int kursid;
	private String kursad;
	
	public kurs(int kursid, String kursad) {
		super();
		this.kursid = kursid;
		this.kursad = kursad;
	}
	
	public kurs() {
		super();
	}

	public int getKursid() {
		return kursid;
	}

	public void setKursid(int kursid) {
		this.kursid = kursid;
	}

	public String getKursad() {
		return kursad;
	}

	public void setKursad(String kursad) {
		this.kursad = kursad;
	}
	
	
}	
