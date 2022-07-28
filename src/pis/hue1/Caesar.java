package pis.hue1;

/**
 * Ein Caesar-Verfahren zur Verschluesselung.
 * @author Sarah Yanuar Nadia Allisa
 */
public class Caesar implements Codec {
	
	private String schluessel;
	private int verschieben;
	
	/**
	 * Initialisiert einen Caesar-Verfahren. (keinen Parameter)
	 */
	public Caesar() {
		
	}
	
	/**
	 * Erstellt die kodiere-Methode,
	 * um Geheimtext aus dem Klartext mit dem Losungswort zu bekommen.
	 * Der klartext-String wird als CharArray umgewandelt. Die Methode wird auch einen kodierteArr erstellen.
	 * Der Char wird danach verschieben, soviel wie in dem int verschieben-Veriable gespeichert ist.
	 * Am Ende wird dann die verschobene Char in kodierteArr gespeichert.
	 * kodierteArr wird als kodierteString umgewandelt und auch zurueckgegeben.
	 * @param klartext: Klartext
	 * @return kodierteString
	 */
	public String kodiere(String klartext) {
		char[] charArr = klartext.replaceAll("\\s+", "").toCharArray();
		char[] kodierteArr = new char[charArr.length];
		
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (c >= 'a' && c <= 'z') {
				c = (char) (charArr[i] + (verschieben % 26));
				if (c > 'z') {
					kodierteArr[i] = (char) (charArr[i] - (26 - (verschieben % 26)));
				} else {
					kodierteArr[i] = (char) (charArr[i] + (verschieben % 26));
				}
			} else if (c >= 'A' && c <= 'Z') {
				c = (char) (charArr[i] + (verschieben % 26));
				if (c > 'Z') {
					kodierteArr[i] = (char) (charArr[i] - (26 - (verschieben % 26)));
				} else {
					kodierteArr[i] = (char) (charArr[i] + (verschieben % 26));
				}
			} else {
				kodierteArr[i] = charArr[i];
			}
			
		}
		
		String kodierteString = String.valueOf(kodierteArr);
		return kodierteString;
	}
	
	/**
	 * Erstellt die dekodiere-Methode,
	 * um Klartext aus dem Geheimtext mit dem Losungswort zu bekommen.
	 * Diese Methode macht genau dasselbe wie die kodiere-Methode, jedoch in umgekehrter Reihenfolge.
	 * @param geheimtext: Geheimtext
	 * @return dekodierteString
	 */
	public String dekodiere(String geheimtext) {
		char[] charArr = geheimtext.toCharArray();
		char[] dekodierteArr = new char[charArr.length];
		
		for (int i = 0; i < charArr.length; i++) {
			char c = charArr[i];
			if (c >= 'a' && c <= 'z') {
				c = (char) (charArr[i] - (verschieben % 26));
				if (c < 'a') {
					dekodierteArr[i] = (char) (charArr[i] + (26 - (verschieben % 26)));
				} else {
					dekodierteArr[i] = (char) (charArr[i] - (verschieben % 26));
				}
			} else if (c >= 'A' && c <= 'Z') {
				c = (char) (charArr[i] - (verschieben % 26));
				if (c < 'A') {
					dekodierteArr[i] = (char) (charArr[i] + (26 - (verschieben % 26)));
				} else {
					dekodierteArr[i] = (char) (charArr[i] - (verschieben % 26));
				}
			} else {
				dekodierteArr[i] = charArr[i];
			}
		}
		
		String dekodierteString = String.valueOf(dekodierteArr);
		return dekodierteString;		
	}
	
	/**
	 * Erstellt die gibLosung-Methode,
	 * um den Losungswort zurueckzugeben.
	 * @return schluessel: Schluessel
	 */
	public String gibLosung() {
		return schluessel;
	}
	
	/**
	 * Erstellt die setzeLosung-Methode,
	 * um den Losungswort zu setzen.
	 * @param schluessel: Schluessel
	 * @throws IllegalArgumentException
	 */
	public void setzeLosung(String schluessel) throws IllegalArgumentException {
		this.schluessel = schluessel;
		
		if (schluessel == "") {
			throw new IllegalArgumentException("Schluessel darf nicht leer sein.");
		}
		
		char[] charArr = schluessel.replaceAll("\\s+", "").toLowerCase().toCharArray();	// delete spaces + string to array
		this.verschieben = charArr.length;
	}
}
