GEO Reader
==========

Library for reading GEO metadata from a [GEOmedatdb](http://gbnci.abcc.ncifcrf.gov/geo/) database.

Also includes code to read from the spreadsheet-based GEO SOFT format. However, this format has not been fully tested.

#### Using the Library

First download the latest copy of the ```GEOmetadb.sqlite``` database from the [GEOmetadb site](https://gbnci-abcc.ncifcrf.gov/geo/).

The core reading class in this library is called ```GEOmetadbReader```. It takes a GEOmetadb SQLite database file, reads the metadata in it,
and generated a sequence of ```GEOSubmissionMetadata``` instances. Each instance corresponds to a GEO series.

Because the database is large, the reader allows the user to specify a slice of series from the dabase for processing.

Here is a minimal code example showing use of the reader:

```
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
```

#### Building from Source

To build this library you must have the following items installed:

+ [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
+ A tool for checking out a [Git](http://git-scm.com/) repository.
+ Apache's [Maven](http://maven.apache.org/index.html).

Get a copy of the latest code:

    git clone https://github.com/metadatacenter/geo-reader.git 

Change into the geo-reader directory:

    cd geo-reader

Then build it with Maven:

    mvn clean install

On build completion your local Maven repository will contain the generated ```geo-reader-${version}.jar```.

