package org.metadatacenter.readers.geo.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Metadata for a GEO platform.
 *
 * @see GEOSubmissionMetadata
 */
public class Platform
{
  private final String gpl;
  private final String title;
  private final String distribution;
  private final String technology;
  private final String organism;
  private final Optional<String> manufacturer;
  private final List<String> manufacturerProtocol;
  private final List<String> description;
  private final Optional<String> catalogNumber;
  private final Optional<String> webLink;
  private final Optional<String> support;
  private final Optional<String> coating;
  private final List<String> contributor;
  private final List<String> pubMedID;

  public Platform(String gpl, String title, String distribution, String technology, String organism, Optional<String> manufacturer,
    List<String> manufacturerProtocol, List<String> description, Optional<String> catalogNumber,
    Optional<String> webLink, Optional<String> support, Optional<String> coating, List<String> contributor,
    List<String> pubMedID)
  {
    this.gpl = gpl;
    this.title = title;
    this.distribution = distribution;
    this.technology = technology;
    this.organism = organism;
    this.manufacturer = manufacturer;
    this.manufacturerProtocol = Collections.unmodifiableList(manufacturerProtocol);
    this.description = description;
    this.catalogNumber = catalogNumber;
    this.webLink = webLink;
    this.support = support;
    this.coating = coating;
    this.contributor = Collections.unmodifiableList(contributor);
    this.pubMedID = Collections.unmodifiableList(pubMedID);
  }

  public String getGPL()
  {
    return gpl;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDistribution()
  {
    return distribution;
  }

  public String getTechnology()
  {
    return technology;
  }

  public String getOrganism()
  {
    return organism;
  }

  public Optional<String> getManufacturer()
  {
    return manufacturer;
  }

  public List<String> getManufacturerProtocol()
  {
    return manufacturerProtocol;
  }

  public List<String> getDescription()
  {
    return description;
  }

  public Optional<String> getCatalogNumber()
  {
    return catalogNumber;
  }

  public Optional<String> getWebLink()
  {
    return webLink;
  }

  public Optional<String> getSupport()
  {
    return support;
  }

  public Optional<String> getCoating()
  {
    return coating;
  }

  public List<String> getContributor()
  {
    return contributor;
  }

  public List<String> getPubMedID()
  {
    return pubMedID;
  }

  @Override public String toString()
  {
    return "Platform{" +
            "gpl='" + gpl + '\'' +
            ", title='" + title + '\'' +
            ", distribution='" + distribution + '\'' +
            ", technology='" + technology + '\'' +
            ", organism='" + organism + '\'' +
            ", manufacturer='" + manufacturer + '\'' +
            ", manufacturerProtocol=" + manufacturerProtocol +
            ", description=" + description +
            ", catalogNumber=" + catalogNumber +
            ", webLink=" + webLink +
            ", support=" + support +
            ", coating=" + coating +
            ", contributor=" + contributor +
            ", pubMedID=" + pubMedID +
            '}';
  }
}
