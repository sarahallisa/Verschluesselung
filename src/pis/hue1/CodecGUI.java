package pis.hue1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CodecGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JComboBox verfahrenList;
	JRadioButton wuerfel;
	JRadioButton caesar;
	JTextField schluessel1;
	JTextField schluessel2;
	JTextField klartext;
	JTextField geheimtext;
	JTextField ergebnis;
	JButton dekodiere;
	JButton kodiere;
	Codec codec;
	
	public static void main(String[] args) {
		CodecGUI frame = new CodecGUI();
	}
	
	public CodecGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		this.setTitle("1. Hausübung");
		
		JPanel beginn = new JPanel(new FlowLayout());
		
		JLabel beschriftung = new JLabel("Wählen Sie ein Verfahren!");
		beginn.add(beschriftung);
		
		String[] verfahren = {"      Würfel   ", "      Caesar   "};
		verfahrenList = new JComboBox(verfahren);
		verfahrenList.setSelectedIndex(0);
		verfahrenList.addActionListener(this);
		beginn.add(verfahrenList);
		
		
		this.add(beginn, BorderLayout.NORTH);
		
		ButtonGroup wc = new ButtonGroup();
		wc.add(wuerfel);
		wc.add(caesar);
		
		JPanel textbox = new JPanel(new FlowLayout());
		textbox.setPreferredSize(new Dimension(200, 200));
		
		JLabel schluesselbox1 = new JLabel("1. Schlüssel: ");
		textbox.add(schluesselbox1);
		schluessel1 = new JTextField(30);
		textbox.add(schluessel1);
		
		JLabel schluesselbox2 = new JLabel("2. Schlüssel: ");
		textbox.add(schluesselbox2);
		schluessel2 = new JTextField(30);
		textbox.add(schluessel2);
		
		JLabel klartextbox = new JLabel(" Klartext:       ");
		textbox.add(klartextbox);
		klartext = new JTextField(30);
		textbox.add(klartext);
		
		JLabel label4 = new JLabel(" Geheimtext: ");
		textbox.add(label4);
		
		geheimtext = new JTextField(30);
		textbox.add(geheimtext);
		
		JLabel label5 = new JLabel(" Ergebnis:     ");
		textbox.add(label5);
		
		ergebnis = new JTextField(40);
		ergebnis.setEditable(false);
		textbox.add(ergebnis);
		
		dekodiere = new JButton("Dekodiert!");
		dekodiere.addActionListener(this);
		textbox.add(dekodiere);
		
		kodiere = new JButton("Kodiert!");
		kodiere.addActionListener(this);
		textbox.add(kodiere);
		
		this.add(textbox, BorderLayout.CENTER);
		
		this.setResizable(false);
		
		this.setSize(450,260);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Wuerfel
		if (verfahrenList.getSelectedIndex() == 0) {
			String sText1 = schluessel1.getText();
			String sText2 = schluessel2.getText();
			String kText = klartext.getText();
			String gText = geheimtext.getText();
			
			char[] c1 = sText1.replaceAll("\\s+", "").toLowerCase().toCharArray();
			char[] c2 = sText2.replaceAll("\\s+", "").toLowerCase().toCharArray();
		
			for (int i = 0; i < c1.length; i++) {
				if (c1[i] < 'a' || c1[i] > 'z') {
					JOptionPane.showMessageDialog(null, "Beim 1. Schluessel darf nur Buchstaben drin.");
				}
			}
			for (int i = 0; i < c2.length; i++) {
				if (c2[i] < 'a' || c2[i] > 'z') {
					JOptionPane.showMessageDialog(null, "Beim 2. Schluessel darf nur Buchstaben drin.");
				}
			}
			
			//Kodiere
			if (e.getSource() == kodiere) {
				if (sText1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "1. Schlüssel darf nicht leer sein!");
				} else if (kText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Klartext ist noch leer!");
				} else {
					if (sText2.isEmpty()) {
						codec = new Wuerfel(sText1);
						ergebnis.setText(codec.kodiere(kText));
						System.out.println(codec.kodiere(kText));
					} else {
						codec = new Wuerfel(sText1);
						kText = (codec.kodiere(kText));
						codec.setzeLosung(sText2);
						ergebnis.setText(codec.kodiere(kText));
						System.out.println(codec.kodiere(kText));
					}
				}
			}
			//Dekodiere
			else if (e.getSource() == dekodiere){				
				if (sText1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "1. Schlüssel darf nicht leer sein!");
				} else if (gText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Geheimtext ist noch leer!");
				} else {
					if (sText2.isEmpty()) {
						codec = new Wuerfel(sText1);
						ergebnis.setText(codec.dekodiere(gText));
						System.out.println(codec.dekodiere(gText));
					} else {
						codec = new Wuerfel(sText2);
						gText = (codec.dekodiere(gText));
						codec.setzeLosung(sText1);
						ergebnis.setText(codec.dekodiere(gText));
						System.out.println(codec.dekodiere(gText));
					}
				}
			}
		}
		// Caesar
		else if (verfahrenList.getSelectedIndex() == 1) {
			String sText = schluessel1.getText();
			String kText = klartext.getText();
			String gText = geheimtext.getText();
			
			//Kodiere
			if (e.getSource() == kodiere) {
				if (sText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "1. Schlüssel darf nicht leer sein!");
				} else if (kText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Klartext ist noch leer!");
				} else {
					Caesar ca = new Caesar();
					ca.setzeLosung(sText);
					ergebnis.setText(ca.kodiere(kText));
					System.out.println(ca.kodiere(kText));
				}
			}
			//Dekodiere
			else if (e.getSource() == dekodiere) {
				if (sText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "1. Schlüssel darf nicht leer sein!");
				} else if (gText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Geheimtext ist noch leer!");
				} else {
					Caesar ca = new Caesar();
					ca.setzeLosung(sText);
					ergebnis.setText(ca.dekodiere(gText));
					System.out.println(ca.dekodiere(gText));
				}
			}
		}
	}
}
