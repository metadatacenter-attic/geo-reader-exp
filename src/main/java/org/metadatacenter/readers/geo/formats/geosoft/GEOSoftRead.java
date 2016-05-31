package org.metadatacenter.readers.geo.formats.geosoft;

import org.metadatacenter.readers.geo.GEOReaderException;
import org.metadatacenter.readers.geo.metadata.GEOSubmissionMetadata;

/**
 * Basic example of using the {@link GEOSoftReader} class to read GEO metadata from an Excel spreadsheet.
 */
public class GEOSoftRead
{
  public static void main(String[] args)
  {
    if (args.length != 1)
      Usage();

    String geoExcelFilename = args[0];

    try {
      GEOSoftReader geoSoftReader = new GEOSoftReader(geoExcelFilename);
      GEOSubmissionMetadata geoSubmissionMetadata = geoSoftReader.extractGEOSubmissionMetadata();

      System.out.println("geoSubmissionMetadata: " + geoSubmissionMetadata.toString());

    } catch (GEOReaderException e) {
      System.err.println(GEOSoftRead.class.getName() + ": Error reading: " + e.getMessage());
      System.exit(-1);
    }
  }

  private static void Usage()
  {
    System.err.println("Usage: " + GEOSoftRead.class.getName() + " <GEO Excel Filename>");
    System.exit(-1);
  }
}

