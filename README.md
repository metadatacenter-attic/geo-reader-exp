GEO Reader
==========

Library for reading GEO metadata from a [GEOmedatdb](http://gbnci.abcc.ncifcrf.gov/geo/) database.

Also includes code to read from the spreadsheet-based GEO SOFT format. However, this format has not been fully tested.

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

