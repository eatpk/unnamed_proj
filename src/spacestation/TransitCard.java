package src.spacestation;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TransitCard {

    // Passenger passport id
    @SerializedName("id")
    public final UUID id;

    // Cash balance
    @SerializedName("florples")
    public final int florples;

    // Transaction history.  This is immutable so access is granted with getHistory()
    @SerializedName("history")
    private final List<Transaction> history;

    // Card authenticity hash
    @SerializedName("hash")
    public final String hash;

    public TransitCard( UUID id, int florples, List<Transaction> history, String hash )    {
        this.id = id;
        this.florples = florples;
        this.history = new LinkedList<>(history);
        this.hash = hash;
    }

    public List<Transaction> getHistory() {
        return new ArrayList<>( history );
    }

    // This is the string used to calculate the hash
    public String toHashableString() {
        StringBuilder builder = new StringBuilder();
        builder.append( id.toString() );
        builder.append( florples );
        history.forEach( transaction -> builder.append(transaction.toString()) );

        return builder.toString();
    }

    @Override
    public String toString()
    {
        return "TransitCard{" +
            "id=" + id +
            ", florples=" + florples +
            ", history=" + history +
            ", hash='" + hash + '\'' +
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
        TransitCard that = (TransitCard) o;
        return florples == that.florples &&
            Objects.equals( id, that.id ) &&
            Objects.equals( history, that.history ) &&
            Objects.equals( hash, that.hash );
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( id, florples, history, hash );
    }
}
