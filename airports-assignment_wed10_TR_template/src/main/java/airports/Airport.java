package airports;

import java.io.Serializable;
import java.time.ZoneId;
public record Airport(String icao, String iata, String name, String city,
                      String state, String country, int elevation,
                      Coord coord, ZoneId tz) implements Serializable {
    public record Coord(double lat, double lon) implements Serializable {}
}
