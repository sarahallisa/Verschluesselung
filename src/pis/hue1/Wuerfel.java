package pis.hue1;

import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 * Ein Wuerfel-Verfahren zur Verschluesselung.
 * @author Sarah Yanuar Nadia Allisa
 */
public class Wuerfel implements Codec {
	
	private String schluessel;
	private int[] key;
	private int[] inverseKey;
	
	/**
	 * Initialisiert einen Wuerfel-Verfahren. (Mit Schluessel s)
	 * @param s: Schluessel
	 */
	public Wuerfel(String s) {
		setzeLosung(s);
	}
	
	/**
	 * Erstellt die kodiere-Methode, um Geheimtext aus dem Klartext mit dem Losungswort zu bekommen.
	 * Der klartext-String wird als CharArray umgewandelt. Danach werden sie einzelnen pro Zeile in einem leeren tempString-StringArray gespeichert.
	 * Der tempString-Array wird mit dem inverseKey-Array in kodierteString zugeordnet, welche Array erst kommt und so weiter.
	 * Am Ende wird dann die kodierteString zurueckgegeben.
	 * @param klartext: Klartext
	 * @return kodierteString
	 */
	public String kodiere(String klartext) {
		char[] charArr = klartext.toCharArray();
		String[] tempString = new String[key.length];
		String kodierteString = "";
		
		for (int i = 0; i < tempString.length; i++) {
			tempString[i] = "";
		}
		
		for (int i = 0; i < charArr.length; i++) {
			tempString[i % key.length] = tempString[i % key.length] + charArr[i];
		}
		
		for (int i = 0; i < inverseKey.length; i++) {
			kodierteString = kodierteString + tempString[inverseKey[i]];
		}
		
		return kodierteString;
	}

	/**
	 * Erstellt die dekodiere-Methode, um Klartext aus dem Geheimtext mit dem Losungswort zu bekommen.
	 * Der geheimtext-String wird als CharArray umgewandelt. Es wird auch einen leeren tempString-StringArray initialisiert.
	 * Um den Index der tempString zu bekommen, geht die Schleife durch jede Zeile und addiert einen Char dazu, den im bestimmten Berecih steht.
	 * Danach wird auch gecheckt, um es noch ein Paar reste Char in dem Zeile. Wenn es noch gibt, dann wird die in dem zugeordentem tempString[i] gespeichert.
	 * Und am Ende wird dann alles in dekodierteString gespeichert und zurueckgegeben.
	 * @param geheimtext: Geheimtext
	 * @return dekodierteString
	 */
	public String dekodiere(String geheimtext) {
		char[] charArr = geheimtext.toCharArray();
		String dekodierteString = "";
		
		int rows = charArr.length / key.length;
		int restInRows = charArr.length % key.length;
		
		String[] tempString = new String[key.length];
		
		for (int i = 0; i < tempString.length; i++) {
			tempString[i] = "";
		}
		
		int anfang = 0;
        int ende = anfang + rows;
        int tempRestRows = restInRows - 1;
        
        for (int i = 0; i < key.length; i++) {
            for (int j = anfang; j < ende; j++) {
                tempString[inverseKey[i]] = tempString[inverseKey[i]] + Character.toString(charArr[j]);
            }
            
            if (tempRestRows > 0 && inverseKey[i] < restInRows) {
                tempString[inverseKey[i]] = tempString[inverseKey[i]] + Character.toString(charArr[ende]);
                	
               	anfang = ende + 1;
                ende = anfang + rows;
            } else {
            	anfang = ende;
            	ende = anfang + rows;
            }
        }
        
       for (int i = 0; i < rows; i++) {
    	   for (int j = 0; j < key.length; j++) {
    		   dekodierteString = dekodierteString + tempString[j].charAt(i);
    	   }
       }
       
       for (int i = 0; i < restInRows; i++) {
    	  dekodierteString = dekodierteString + tempString[i].charAt(tempString[i].length() - 1);
       }
	
       return dekodierteString;
	}

	/**
	 * Erstellt die gibLosung-Methode, um den Losungswort zurueckzugeben.
	 * @return schluessel
	 */
	public String gibLosung() {
		return schluessel;
	}
	
	/**
	 * Erstellt die setzeLosung-Methode, um den Losungswort zu setzen.
	 * Erstmal wird in der Methode gecheckt, ob der Schluessel leer oder nicht ist.
	 * Der schluessel-String wird als einem normalArr-CharArray umgewandelt. Und danach auch beim sortierteArr.
	 * Das sortiertArr wird sortiert. Und erstellen die Methoden auch key und inverseKey.
	 * Diese beide key und inverseKey werden sehr hilfreich beim Verfahren der anderen methoden.
	 * @param schluessel: Schluessel
	 * @throws IllegalArgumentException
	 */
	public void setzeLosung(String schluessel) throws IllegalArgumentException {
		this.schluessel = schluessel;
		
		if (schluessel == "") {
			throw new IllegalArgumentException("Schluessel darf nicht leer sein.");
		}
		
		char[] normalArr = schluessel.replaceAll("\\s+", "").toLowerCase().toCharArray();
		char[] sortierteArr = schluessel.replaceAll("\\s+", "").toLowerCase().toCharArray();
		
		for (int i = 0; i < normalArr.length; i++) {
			if (normalArr[i] < 'a' || normalArr[i] > 'z') {
				throw new IllegalArgumentException("Beim Schluessel darf nur Buchstaben drin.");
			}
		}
		
		Arrays.sort(sortierteArr);
		
		this.key = new int[sortierteArr.length];
		this.inverseKey = new int[key.length];
		
		for (int i = 0; i < normalArr.length; i++) {
			for (int j = 0; j < sortierteArr.length; j++) {
				if (normalArr[i] == sortierteArr[j]) {
					key[i] = j;
					inverseKey[j] = i;
					sortierteArr[j] = 0;
					break;
				}
			}
		}
	}
}
