package src.spacestation;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class DepartureRecord {

  // Station from which a passenger departed
  @SerializedName("station")
  public final Station station;

  // Scanned transit card of a departing passenger
  @SerializedName("passenger")
  public final TransitCard passenger;

  public DepartureRecord( Station station, TransitCard passenger )
  {
    this.station = station;
    this.passenger = passenger;
  }

  @Override
  public String toString()
  {
    return "DepartureRecord{" +
        "station=" + station +
        ", passenger=" + passenger +
        '}';
  }

  @Override
  public boolean equals( Object o )
  {
    if ( this == o )
    {
      return true;
    }
    if ( o == null || getClass() != o.getClass() )
    {
      return false;
    }
    DepartureRecord that = (DepartureRecord) o;
    return station == that.station &&
        Objects.equals( passenger, that.passenger );
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( station, passenger );
  }
}