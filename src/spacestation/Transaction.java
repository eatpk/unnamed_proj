package src.spacestation;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Objects;

public class Transaction {

  // Date of the transaction
  @SerializedName("date")
  public final Instant date;

  // The change in card balance
  @SerializedName("change")
  public final int change;

  // Name of the station on which the transaction occurred
  @SerializedName("station")
  public final Station station;

  public Transaction( Instant date, int change, Station station )
  {
    this.date = date;
    this.change = change;
    this.station = station;
  }

  @Override
  public String toString()
  {
    return "Transaction{" +
        "date=" + date +
        ", change=" + change +
        ", station=" + station +
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
    Transaction that = (Transaction) o;
    return change == that.change &&
        Objects.equals( date, that.date ) &&
        station == that.station;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( date, change, station );
  }
}