package airports;

import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirportManagerImpl implements AirportManager{
    /**
     * A képernyőre írja azokat a repülőtereket, melyek IATA és ICAO kódjai ugyanazon betűvel kezdődnek.
     * <p>
     * Vigyázz, nem mindegyik repülőtérnek van ICAO kódja.
     */
    @Override
    public void printAirportsHavingSimilarIATAAndICAOCodes() {
        getAirports().stream().filter(airport -> airport.iata() != null)
                .filter(airport -> airport.iata().equals(airport.icao()))
                .forEach(System.out::println);
    }

    /**
     * Visszaadja azon repülőterek darabszámát, melyek állama nem ismert.
     *
     * @return a darabszám
     */
    @Override
    public long getCountOfAirportsInUnknownStates() {
        return getAirports().stream().map(Airport::state).filter(Objects::isNull).count();
    }

    /**
     * Visszaadja a megadott ICAO kóddal rendelkező repteret.
     *
     * @param icao az ICAO kód
     * @return a repülőtér
     */
    @Override
    public Optional<Airport> getAirportByICAO(@NonNull String icao) {
        //return getAirports().stream().filter(airport -> airport.icao().equals(icao));
        return Optional.empty();
    }

    /**
     * Visszaadja azon repülőterek ICAO kódjainak lexikografikusan rendezett listáját, melyek a megadott városban találhatók.
     *
     * @param city a város neve
     * @return az ICAO kódok rendezett listája
     */
    @Override
    public List<String> getOrderedAirportICAOCodesByCity(@NonNull String city) {
        return getAirports().stream().filter(airport -> airport.city().equals(city))
                .map(Airport::icao).sorted(Comparator.naturalOrder()).toList();
        //return null;
    }

    public static void main (String [] args){
        AirportManagerImpl airportManager = new AirportManagerImpl();
        airportManager.printAirportsHavingSimilarIATAAndICAOCodes();
        System.out.println(airportManager.getCountOfAirportsInUnknownStates());
        System.out.println(airportManager.getAirportByICAO("1234"));
        System.out.println(airportManager.getOrderedAirportICAOCodesByCity("Budapest"));
    }
}
