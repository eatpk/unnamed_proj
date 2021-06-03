package src.util;

import src.spacestation.DepartureRecord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.zip.GZIPOutputStream;

// This is a helper class for generating custom log files during validation, if needed
public class LogWriter
{
  private static Output output;

  // Utility method to write a list of departure records to a log file
  public static void write( List<DepartureRecord> records, String logPrefix )
  {
    String logName = logPrefix + ".gz";
    File file = new File( logName );

    if ( file.exists() )
    {
      file.delete();
    }
    try
    {
      output = new Output( new BufferedWriter( new OutputStreamWriter( new GZIPOutputStream( new FileOutputStream( file, true ) ) ) ), file );
      for ( DepartureRecord record : records )
      {
        output.bufferedWriter.write( LogReader.gson.toJson( record ) + "\n" );
      }
      output.bufferedWriter.close();
    }
    catch ( IOException e )
    {
      System.out.println( e.toString() );
    }
  }

  private static class Output
  {
    final BufferedWriter bufferedWriter;
    final File file;

    private Output( BufferedWriter bufferedWriter, File file )
    {
      this.bufferedWriter = bufferedWriter;
      this.file = file;
    }
  }
}
