package airports;

import lombok.NonNull;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;

public interface AirportManager {

    @SuppressWarnings("unchecked")
    default List<Airport> getAirports() {
        try {
            var airports = (List<Airport>) new ObjectInputStream(AirportManager.class.getResourceAsStream("airports.ser"))
                    .readObject();
            var logger = LoggerFactory.getLogger(AirportManager.class); /** Itt volt már a sor */
            logger.atInfo().log("Loaded {} objects", airports.size());
            return airports;
        } catch (Exception e) {
            throw new AssertionError("Failed to load objects");
        }
    }

    /**
     * A képernyőre írja azokat a repülőtereket, melyek IATA és ICAO kódjai ugyanazon betűvel kezdődnek.
     * <p>
     * Vigyázz, nem mindegyik repülőtérnek van ICAO kódja.
     */
    void printAirportsHavingSimilarIATAAndICAOCodes();

    /**
     * Visszaadja azon repülőterek darabszámát, melyek állama nem ismert.
     *
     * @return a darabszám
     */
    long getCountOfAirportsInUnknownStates();

    /**
     * Visszaadja a megadott ICAO kóddal rendelkező repteret.
     *
     * @param icao az ICAO kód
     * @return a repülőtér
     */
    Optional<Airport> getAirportByICAO(
            @NonNull String icao);

    /**
     * Visszaadja azon repülőterek ICAO kódjainak lexikografikusan rendezett listáját, melyek a megadott városban találhatók.
     *
     * @param city a város neve
     * @return az ICAO kódok rendezett listája
     */
    List<String> getOrderedAirportICAOCodesByCity(
            @NonNull String city);
}
