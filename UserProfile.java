package Grundklassen;
import java.util.LinkedList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleFloatProperty;
import java.time.LocalDate;
import java.util.PriorityQueue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;



/**
 * Repraesentiert das Benutzerprofil. Enthaelt Benutzernamen und Information
 * ueber alle Umsaetze (transactions), die realisiert bzw. geplant wurden,
 * sowie 2 Varianten von dem Kontostand: aktueller Kontostand(Kontostand an dem aktuellen Tag)
 * und gesamter Kontostand (Kontostand, in dem alle, auch zukuenftige Umsaetze, beruecksichtigt werden).
 *
 * @author Anastasiia_Kysliak
 */
@XmlRootElement( namespace = "http://userprofile.de/")
public class UserProfile {


    private String userName;
    private ObservableList<Transaction> transactions; // Observable-Liste aller Umsaetze
    private int balanceInt;    // gesamter Kontostand als int    
	private SimpleStringProperty generalBal;    // gesamter Kontostand als SimpleStringProperty
	private SimpleStringProperty currentBal;    // aktueller Kontostand als SimpleStringProperty
	private int curbalanceInt; // aktueller Kontostand als int
	
    private String[] categoriesD = {   "Wohnen", "Lebensmittel", "Uni",
									"Stipendium", "Bafoeg" , "Gehalt", 
									"Sonstiges" }; // Default-Liste aller Kategorien
    private ObservableList<String> categories; // Observable-Liste aller Kategorien

    /**
     * Erstellt neues UserProfile mit Defaultparametern.
     */
    public UserProfile() {
		userName = "KeinBenutzername";
		transactions = FXCollections.observableArrayList();
		categories = FXCollections.observableArrayList(categoriesD);
        balanceInt = 0;
		curbalanceInt = 0;
		generalBal = new SimpleStringProperty("0");
		currentBal = new SimpleStringProperty("0");
    }

    /**
     * Setzt neuen Benutzernamen.
	 *
	 * @param name der neue Benutzername.
     */
    public void setUserName (String name) {
        userName = name;
    }

    /**
     * Gibt den Benutzernamen zurueck.
     *
     * @return Benutzernamen.
     */
	 @XmlAttribute
    public String getUserName () {
        return userName;
    }

    /**
     * Fuegt den uebergebenen Umsatz (transaction) der Liste von Umsaetzen hinzu.
     * Aktualisiert entsprechend den gesamten und den aktuellen Kontostaende.
     *
     * @param trans der hinzuzufuegende Umsatz.
     */
    public void addTransaction (Transaction trans) {
        transactions.add(trans);
        balanceInt += trans.getAmountInt();
		setGeneralBal(balanceInt);
		if(!LocalDate.now().isBefore(trans.getDateLocalDate())) {
			curbalanceInt += trans.getAmountInt();
			setCurrentBal(curbalanceInt);
		}
	}
	
    /**
     * Entfernt den uebergebenen Umsatz (transaction) aus der Liste von Umsaetzen.
     * Aktualisiert entsprechend den gesamten und den aktuellen Kontostaende.
     *
     * @param trans der zu entfernende Umsatz.
     */
    public void removeTransaction (Transaction trans) {
        transactions.remove(trans);
        balanceInt -= trans.getAmountInt();
		setGeneralBal(balanceInt);
		if(!LocalDate.now().isBefore(trans.getDateLocalDate())) {
			curbalanceInt -= trans.getAmountInt();
			setCurrentBal(curbalanceInt);
		}
    }

	 /**
     * Entfernt die uebergebene Umsaetze (transactions) aus der Liste von Umsaetzen.
     * Aktualisiert entsprechend den gesamten und den aktuellen Kontostaende.
     *
     * @param trans die zu entfernende Umsaetze.
     */
	public void removeTransactions (ObservableList<Transaction> trans) {
		for (Transaction t: trans) {
			removeTransaction(t);
		}
    }
    /**
     * Setzt neue Liste von Umsaetzen (transactions).
     * Aktualisiert entsprechend den gesamten und den aktuellen Kontostaende.
	 *
	 * @param trans neue Liste von Umsaetzen (transactions).
     */
    public void setTransactions (ObservableList<Transaction> trans) {
        transactions = trans;
        balanceUpdate();
    }

    /**
     * Gibt die aktuelle Liste von allen Umsaetzen zurueck.
     *
     * @return Liste von Umsaetzen.
     */
	 @XmlElement ( name = "transaction")
    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }
	
	/**
     * Setzt neue Liste von Kategorien.
	 *
	 * @param categories neue Liste von Kategorien.
     */
	public void setCategories (ObservableList<String> categories) {
		this.categories = categories;
	}
	
	/**
     * Fuegt die uebergebene Kategorie der Kategorie-Liste hinzu.
     *
     * @param category die hinzuzufuegende Kategorie.
     */
	public void addCategory (String category) {
		categories.add(category);
	}
	
	/**
     * Entfernt die uebergebene Kategorie aus der Kategorie-Liste. 
     *
     * @param category die zu entfernende Kategorie.
     */
	public void removeCategory (String category) {
		categories.remove(category);
	}
	
	 /**
     * Gibt die aktuelle Liste von Kategorien zurueck.
     *
     * @return Liste von Kategorien.
     */
	 @XmlElement (name = "category")
	public ObservableList<String> getCategories() {
		return categories;
	}
	
    /**
     * Aktualisiert den gesamten und den aktuellen Kontostaende.
     */
    public void balanceUpdate() {
        balanceInt = 0;
		curbalanceInt = 0;
        for (Transaction t : transactions) {
            balanceInt += t.getAmountInt();
			if (!LocalDate.now().isBefore(t.getDateLocalDate())) {
				curbalanceInt += t.getAmountInt();
			}
        }
		setGeneralBal(balanceInt);
		setCurrentBal(curbalanceInt);
    }
	
	/**
     * Rechnet den aktuellen Kontostand.
     */
	public void currentBalanceUpdate() {
		curbalanceInt = 0;
        for (Transaction t : transactions) {
			if (!LocalDate.now().isBefore(t.getDateLocalDate())) {
				curbalanceInt += t.getAmountInt();
			}
        }
		setCurrentBal(curbalanceInt);
    }
	
	/**
     * Gibt den gesamten Kontostand als int zurueck. 
     *
     * @return der gesamte Kontostand als int.
     */
	@XmlElement
    public int getBalanceInt () {
        return balanceInt;
    }
	
	/**
     * Setzt neuen Wert des gesamten Kontostands als int,
	 * aktualisiert ensprechend den gesamten Kontostand als float.
     *
     * @param balInt neuer Wert des gesamten Kontostands als int.
     */
	public void setBalanceInt (int balInt) {
        balanceInt = balInt;
		setGeneralBal(balanceInt);
    }

	/**
	 * Gibt den aktuellen Kontostand als String zurueck.
	 *
	 * @return aktueller Kontostand.
	 */
	public String getCurrentBal() {
		return currentBal.get();
	}

	/**
	 * Gibt Property fuer den aktuellen Kontostand zurueck.
	 *
	 * @return Property fuer den aktuellen Kontostand.
	 */
	public SimpleStringProperty currentBalProperty() {
		return currentBal;
	}

	/**
	 * Setzt neuen Wert des aktuellen Kontostands.
	 *
	 * @param value der neue Wert von dem aktuellen Kontostand (als int: aktueller Wert*100).
	 */
	public void setCurrentBal(int value) {
		float balF = (float) value/100;
		Float bal = new Float(balF);
		this.currentBal.set(bal.toString());
	}
	/**
	 * Gibt den gesamten Kontostand als String zurueck.
	 *
	 * @return gesamter Kontostand.
	 */
	public String getGeneralBal() {
		return generalBal.get();
	}

	/**
	 * Gibt Property fuer den gesamten Kontostand zurueck.
	 *
	 * @return Property fuer den gesamten Kontostand.
	 */
	public SimpleStringProperty generalBalProperty() {
		return generalBal;
	}

	/**
	 * Setzt neuen Wert des gesamten Kontostands.
	 *
	 * @param value der neue Wert von dem gesamten Kontostand (als int: aktueller Wert*100).
	 */
	public void setGeneralBal(int value) {
		float balF = (float) value/100;
		Float bal = new Float(balF);
		this.generalBal.set(bal.toString());
	}
}
