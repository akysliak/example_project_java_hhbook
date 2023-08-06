package Grundklassen;
import javax.xml.bind.JAXB;
import javax.xml.bind.DataBindingException;
import java.io.File;
import java.io.IOException;

/**
 * Die Klasse Editor enthaelt public static Methoden readIn(String fileName) und writeOut(UserProfile up),
 * die dazu benutzt werden, um ein Benutzerprofil in eine XML-Datei zu speichern und aus einer XML-Datei zu lesen.
 *
 * @author Anastasiia_Kysliak
 */
public class Editor {

	
	/**
	 * Liest das Benutzerprofil aus der XML-Datei mit dem uebergebenen Namen und speichert dieses in das neuerstellte Objekt UserProfile.
	 *
	 * @param fileName Name der XML-Datei, aus der das Benutzerprofil gelesen werden muss.
	 * @return Benutzerprofil als UserProfile oder null, falls die XML-Datei mit dem uebergebenen Namen nicht existiert oder es Fehler beim Lesen gibt.
	 */
	public static UserProfile readIn(String fileName) {
		File file = new File(fileName);
		if(!file.exists()) {
			return null;
		}
		try {
			return JAXB.unmarshal (file, UserProfile.class);	
		}
		catch ( DataBindingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Liest das Benutzerprofil aus dem uebergebenen File (XML-Datei) und speichert dieses in das neuerstellte Objekt UserProfile.
	 *
	 * @param file die XML-Datei, aus der das Benutzerprofil gelesen werden muss.
	 * @return Benutzerprofil als UserProfile oder null, falls die XML-Datei nicht existiert oder es Fehler beim Lesen gibt.
	 */
	public static UserProfile readIn(File file) {
		if(!file.exists()) {
			return null;
		}
		try {
			return JAXB.unmarshal (file, UserProfile.class);
		}
		catch ( DataBindingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Speichert das Benutzerprofil aus dem uebergebenen Objekt UserProfile in eine XML-Datei.
	 * Die XML-Datei wird entsprechend dem Benutzernamen aus dem Benutzerprofil genannt.
	 *
	 * @param up das Benutzerprofil als UserProfile, das in eine XML-Datei gespeichert werden muss.
	 */
	public static void writeOut (UserProfile up) {
		File file = new File(up.getUserName() + ".xml");
		try {
			JAXB.marshal (up, file);
		}
		catch ( DataBindingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Speichert das Benutzerprofil aus dem uebergebenen Objekt UserProfile in das uebergebene File (XML-Datei).
	 *
	 * @param up das Benutzerprofil als UserProfile, das in eine XML-Datei gespeichert werden muss.
	 * @param file File (XML-Datei), in das das Benutzerprofil gespeichert werden muss.
	 */
	public static void writeOut (UserProfile up, File file) {
		try {
			JAXB.marshal (up, file);
		}
		catch ( DataBindingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Speichert das Benutzerprofil aus dem uebergebenen Objekt UserProfile in eine XML-Datei.
	 * Die XML-Datei wird entsprechend dem übergebenen Pfadnamen genannt.
	 *
	 * @param up das Benutzerprofil als UserProfile, das in eine XML-Datei gespeichert werden muss.
	 * @param pathname der Pfadname der XML-Datei, in der das Benutzerprofil gespeichert werden muss.
	 */
	public static void writeOut (UserProfile up, String pathname) {
		File file = new File(pathname);
		try {
			JAXB.marshal (up, file);
		}
		catch ( DataBindingException e) {
			e.printStackTrace();
		}
	}
}
