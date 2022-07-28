package pis.hue1;

interface Codec {
	public String kodiere(String klartext);
	public String dekodiere(String geheimtext);
	public String gibLosung();
	public void setzeLosung(String schluessel) throws IllegalArgumentException; // bei ungeeignetem Schluessel
}
