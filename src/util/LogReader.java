package src.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import src.spacestation.DepartureRecord;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.zip.GZIPInputStream;

public class LogReader {

  public static final int BUFFER_SIZE = 262144;
  public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter( Instant.class, new InstantConverter())
      .enableComplexMapKeySerialization() //this is needed to deserialize maps that use enums as keys
      .create();

  private BufferedReader bufferedReader;

  public LogReader( String logFilePath ) throws IOException {
    bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(logFilePath))), BUFFER_SIZE);
  }

  // Returns true if the buffer is open and has more characters to read
  public boolean ready() {
    try {
      return bufferedReader.ready();
    } catch ( IOException ioe ) {
      return false;
    }
  }

  // Read the next departure record from the station log file
  public DepartureRecord next() throws IOException {

    DepartureRecord record = null;
    String nextLine = bufferedReader.readLine();

    if (nextLine != null) {
      record = gson.fromJson( nextLine, DepartureRecord.class );
    } else {
      bufferedReader.close();
    }

    return record;
  }
}