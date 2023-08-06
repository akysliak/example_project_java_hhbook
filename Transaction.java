package Grundklassen;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.Month;
import java.lang.Comparable;


/**

 * Die Klasse Transaction enthaelt Information ueber einen bestimmten Geld-Ein bzw. -Ausgang:

 * Datum (date), Beschreibung (description), Betrag (amount), Kategorie (category).
 *
 * @author Anastasiia_Kysliak

 */
public class Transaction implements Comparable<Transaction> {


    private SimpleObjectProperty<LocalDate> date;  // Datum vom Umsatz als Property

    private SimpleStringProperty  description; // Beschreibung vom Umsatz

    private SimpleFloatProperty amountF;       // Betrag als float

    private int amountInt;                      // Batrag als int

    private SimpleStringProperty category;
	
	private String dateString; // Datum als String

    /**
     * Erstellt neuen Umsatz mit Defaultparametern.
     */
    public Transaction() {

        this.description =  new SimpleStringProperty("Keine Beschreibung");

        category = new SimpleStringProperty("Sonstiges");

        date = new SimpleObjectProperty<LocalDate>(LocalDate.now());

        amountF = new SimpleFloatProperty();

    }

	/**
     * Erstellt neuen Umsatz mit uebergebenen Parametern.
	 *
	 * @param desc Bescreibung vom Umsatz.
	 * @param category Kategorie vom Umsatz.
	 * @param d Datum vom Umsatz.
	 * @param amount Betrag vom Umsatz.
     */
    public Transaction (String desc, String category, LocalDate d, float amount) {

        this.description = new SimpleStringProperty(desc);

        this.category = new SimpleStringProperty(category);

        date = new SimpleObjectProperty<LocalDate>(d);

        amountF = new SimpleFloatProperty(amount);

        amountInt = (int) (amount*100);

    }

    /**

     * Setzt neue Beschreibung vom Umsatz.
	 *
	 * @param desc neue Beschreibung.

     */
    public void setDescription(String desc) {

        description.set(desc);

    }

    /**

     * Gibt die Beschreibung vom Umsatz zurueck.

     *

     * @return Beschreibung vom Umsatz.

     */	
    public String getDescription() {

        return description.get();

    }

    /**

     * Gibt Property fuer Beschreibung zurueck.

     *

     * @return Beschreibung-Property.

     */
    public SimpleStringProperty descriptionProperty() {

        return description;

    }

    /**

     * Ordnet den Umsatz anderer Kategorie zu.
	 *
	 * @param cat neue Kategorie.

     */
    public void setCategory(String cat) {

        category.set(cat);

    }

    /**

     * Gibt die Kategorie vom Umsatz zurueck.

     *

     * @return Kategorie vom Umsatz.

     */
    public String getCategory() {

        return category.get();

    }

    /**

     * Gibt Property fuer Kategorie zurueck.

     *

     * @return Kategorie-Property.

     */
    public SimpleStringProperty categoryProperty() {

        return category;

    }

    /**

     * Setzt neues Datum vom Umsatz.
	 *
	 * @param d neues Datum als LocalDate.

     */
    public void setDate(LocalDate d) {

        this.date.set(d);

    }
	
	/**

     * Setzt neues Datum vom Umsatz.
	 *
	 * @param s neues Datum als String.

     */	 
	public void setDate(String s) {

        this.date.set(LocalDate.parse(s));

    }
	
	/**

    * Gibt das Datum vom Umsatz als String zurueck.

     *

     * @return Datum vom Umsatz als String.

     */
    public String getDate() {

        return date.get().toString();

    }
	
	/**

    * Gibt das Datum vom Umsatz als LocalDate zurueck.

     *

     * @return Datum vom Umsatz als LocalDate.

     */
	public LocalDate getDateLocalDate() {

        return date.get();

    }
	
	/**

     * Gibt Property fuer Datum zurueck.

     *

     * @return Datum-Property.

     */
    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }
	
	/**

     * Gibt den Monat vom Umsatz zurueck.

     *

     * @return Monat vom Umsatz.

     */
	 public Month getMonth() {
		return date.get().getMonth();
	}
	
	/**

     * Gibt das Jahr vom Umsatz zurueck.

     *

     * @return Jahr vom Umsatz.

     */
	 public int getYear() {
		return date.get().getYear();
	}

	/**

     * Gibt den Monatstag vom Umsatz zurueck.

     *

     * @return Monatstag vom Umsatz.

     */	 
	public int getDay() {
		return date.get().getDayOfMonth();
	}

    /**

     * Setzt neuen Betrag.
	 *
	 * @param amount neuer Betrag.

     */
    public void setAmount (float amount) {

        amountF.set(amount);

        amountInt = (int) (amount*100);

    }

    /**

     * Gibt den Betrag vom Umsatz als float zurueck.

     *

     * @return Betrag vom Umsatz als float.

     */	 
    public float getAmount() {

        return amountF.get();

    }

    /**

     * Gibt den Betrag vom Umsatz als int zurueck.

     *

     * @return Betrag vom Umsatz als int.

     */
    public int getAmountInt() {

        return amountInt;

    }
	
	/**

     * Gibt Property fuer Betrag zurueck.

     *

     * @return Betrag-Property.

     */
    public SimpleFloatProperty amountProperty() {

        return amountF;

    }
	
	/**
	* Vergleicht dieser Umsatz mit einem anderen Umsatz.
	*
	* @param t der andere Umsatz, mit dem der aktuelle Umsatz verglichen werden muss.
	*
	* @return -1, falls der aktuelle Umsatz frueher stattfindet,
    *           0, falls beide Umsaetze am gleichen Tag stattfinden und gleiche Betraege haben,
	* 			1, falls der aktuelle Umsatz spaeter durchgefuert wird.
	*/
	public int compareTo(Transaction t) {
		if (date.get().isBefore(t.getDateLocalDate())) {return -1;}
		if (date.get().isEqual(t.getDateLocalDate())) {return 0;}
		return 1;		
	}
}
