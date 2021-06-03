package src;

import src.spacestation.DepartureRecord;
import src.spacestation.Transaction;
import src.spacestation.TransitCard;
import src.util.*;

import java.util.*;

public class SpaceStationSecurity {
    public static HashMap<UUID, TransitCard> map = new HashMap();//map contains the most recent Transitcard record of the user id departing from the station.
    public static Set<UUID> blacklist = new HashSet<UUID>();
    public static int violationCounterForTest=0;//For simple unit test on the self-generated data

    public static void main(String[] args) throws Exception {
        String logPath = args[0];
        LogReader reader = new LogReader( logPath );
        

        int violations = 0;
        while ( reader.ready() ) {
            if ( isViolation( reader.next() )) {
                violations++;
            }
        }

        System.out.println( "Violations detected: " + violations );
    }

    private static boolean isViolation( DepartureRecord departureRecord ) {
   /*
    * Input: DepartureRecord
    * Returns: Boolean
    * The method sees if the current departureRecord's TransitionCard is duplicated or not. If it is found to be fradulent, it returns true, else false.
    */
        //TODO:  Implement this method!
        UUID id = departureRecord.passenger.id;


        List<Transaction> currentHistory = departureRecord.passenger.getHistory();
        String currentHash = departureRecord.passenger.hash;

        if (blacklist.contains(id)){
            //When you detect that a card has known equivalent it should be blacklisted; any further departures using the card's passenger ID should
            //be flagged as a violation
            violationCounterForTest++;
            return true;
        }

        if (!currentHash.equals(HashUtil.hash(departureRecord.passenger))){
            //Condition 1: Checking if the records are manipulated.
            //If any of the attributes in TransitCard are altered manually, it won't be able to match the hashed value, because the cheaters
            //does not have access to the hash function.
            blacklist.add(id);
            map.remove(id);
            violationCounterForTest++;
            return true;
        }
        if (map.containsKey(id)){

            //Condition 2: if the next record has less history than previous record, we know that this is duplicated.
            TransitCard lastCard = map.get(id);
            List lastCardHistory=lastCard.getHistory();

            if (currentHistory.size()-lastCardHistory.size()<=0) {

                blacklist.add(id);
                map.remove(id);
                violationCounterForTest++;
                return true;
            }
            //Condition 3-1: if transit card is duplicated, the travel records of previous departure and the current departure will be different.

            if (!HashUtil.hash(new TransitCard(id,lastCard.florples,currentHistory.
                    subList(currentHistory.size()-lastCardHistory.size(),currentHistory.size()),"dummy")).equals(lastCard.hash)){
                //Hashing the history to see if two matches.
                blacklist.add(id);
                map.remove(id);
                violationCounterForTest++;
                return true;
            }


        }

        //If current TransitCard is legit, replace it with the one before.
        map.put(id,departureRecord.passenger);
        return false;
    }
}
