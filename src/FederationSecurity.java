package src;

public class FederationSecurity
{
  public static void main(String[] args) throws Exception {
    String pathToLogDirectory = args[0];
    System.out.println( "Violations detected: " + findViolations(pathToLogDirectory) );
  }

  private static int findViolations( String pathToLogDirectory )
  {
    // TODO:  Find all of the violations that you found with SpaceStationSecurity and additional
    // TODO:  violations that you couldn't catch with only the log of one station!


    return 0;
  }
}
