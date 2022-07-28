package pis.hue1;

public class Main {
	public static void main(String[] args) {
		Wuerfel w = new Wuerfel("schwenningen");
		Caesar c = new Caesar();
		
		w.setzeLosung("programmierung");
		String s = w.dekodiere("stfbetidneanuuunesihlehgwnerntzlteitrrgdewzreukhsueijohecibesuenhmtiendheaaeltnslonacdilmibuzmvcgnszesrigevennge");
		w.setzeLosung("vergnuegen");
		System.out.println(w.dekodiere(s));
		
		
		/*c.setzeLosung("Mittelhessen");
		c.kodiere("eintreffendersendungverspaetetneuerterminfolgt");
		c.dekodiere("Rdmzl vmsf uy waybxqff hqdimtdxaefqz Fmju cgqd pgdot Nmkqdz, 12345678910!?");
		c.gibLosung();*/
	}
}
