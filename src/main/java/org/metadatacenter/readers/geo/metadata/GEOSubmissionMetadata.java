package org.metadatacenter.readers.geo.metadata;

import org.metadatacenter.readers.geo.formats.geometadb.GEOmetadbReader;
import org.metadatacenter.readers.geo.formats.geosoft.GEOSoftReader;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Metadata for a single GEO submission, which will contain a single series and its associated samples,
 * protocol, and platform.
 * <p>
 * A {@link GEOmetadbReader} or a {@link GEOSoftReader} can be used to create this metadata from
 * GEOmetadb and GEOSoft formats, respectively.
 * <p>
 *
 * @see Series
 * @see Sample
 * @see Platform
 * @see Protocol
 * @see GEOSoftReader
 * @see GEOmetadbReader
 */
public class GEOSubmissionMetadata
{
  private final Series series;
  private final Map<String, Sample> samples; // sampleID -> Sample
  private final Optional<Protocol> protocol;
  private final List<Platform> platforms;

  public GEOSubmissionMetadata(Series series, Map<String, Sample> samples, Optional<Protocol> protocol,
    List<Platform> platforms)
  {
    this.series = series;
    this.samples = Collections.unmodifiableMap(samples);
    this.protocol = protocol;
    this.platforms = Collections.unmodifiableList(platforms);
  }

  public String getGSE() { return this.series.getGSE(); }

  public Series getSeries()
  {
    return this.series;
  }

  public Map<String, Sample> getSamples()
  {
    return this.samples;
  }

  public Optional<Protocol> getProtocol()
  {
    return this.protocol;
  }

  public List<Platform> getPlatforms() { return this.platforms; }

  @Override public String toString()
  {
    return "GEOMetadata{" +
      "Series=" + series + "\n\n" +
      "Samples=" + prettyPrintSamples() + "\n\n" +
      "Protocol=" + protocol + "\n\n" +
      "Platforms=" + platforms +
      '}';
  }

  private String prettyPrintSamples()
  {
    StringBuilder sb = new StringBuilder();

    for (String sampleName : this.samples.keySet()) {
      Sample sample = this.samples.get(sampleName);
      sb.append("\nsampleName=" + sampleName + ", " + sample);
    }
    return sb.toString();
  }
}
