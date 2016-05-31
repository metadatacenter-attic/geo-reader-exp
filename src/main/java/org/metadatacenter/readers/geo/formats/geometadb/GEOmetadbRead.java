package org.metadatacenter.readers.geo.formats.geometadb;

import org.metadatacenter.readers.geo.GEOReaderException;
import org.metadatacenter.readers.geo.formats.geosoft.GEOSoftRead;
import org.metadatacenter.readers.geo.metadata.GEOSubmissionMetadata;

import java.util.List;

/**
 * Basic example of using the {@link GEOmetadbReader} class to read GEO metadata from a GEOmetadb database.
 */
public class GEOmetadbRead
{
  public static void main(String[] args)
  {
    if (args.length != 3)
      Usage();

    try {
      String geometadbFilename = args[0];
      int startIndex = Integer.parseInt(args[1]);
      int numberOfSeries = Integer.parseInt(args[2]);

      GEOmetadbReader geometadbReader = new GEOmetadbReader(geometadbFilename);
      List<GEOSubmissionMetadata> geoSubmissionMetadataList = geometadbReader
        .extractGEOSubmissionsMetadata(startIndex, numberOfSeries);

      for (GEOSubmissionMetadata geoSubmissionMetadata : geoSubmissionMetadataList)
        System.out.println("geoSubmissionMetadata: " + geoSubmissionMetadata.toString());

    } catch (GEOReaderException e) {
      System.err.println(GEOSoftRead.class.getName() + ": Error reading: " + e.getMessage());
      System.exit(-1);
    } catch (NumberFormatException e) {
      System.err.println(GEOSoftRead.class.getName() + ": Error processing arguments: " + e.getMessage());
      System.exit(-1);
    }
  }

  private static void Usage()
  {
    System.err
      .println("Usage: " + GEOmetadbRead.class.getName() + " <GEOmetadb Filename> <startIndex> <numberOfSeries>");
    System.exit(-1);
  }
}