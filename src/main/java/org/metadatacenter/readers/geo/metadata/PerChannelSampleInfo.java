package org.metadatacenter.readers.geo.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PerChannelSampleInfo
{
  private final Integer channelNumber;
  private final String sourceName;
  private final List<String> organisms;
  private final Map<String, String> characteristics; // characteristic name -> characteristic value
  private final String molecule;
  private final String label;
  private final Optional<String> treatmentProtocol;
  private final Optional<String> extractProtocol;

  public PerChannelSampleInfo(Integer channelNumber, String sourceName, List<String> organisms,
    Map<String, String> characteristics, String molecule, String label, Optional<String> treatmentProtocol,
    Optional<String> extractProtocol)
  {
    this.channelNumber = channelNumber;
    this.sourceName = sourceName;
    this.organisms = Collections.unmodifiableList(organisms);
    this.characteristics = Collections.unmodifiableMap(characteristics);
    this.molecule = molecule;
    this.label = label;
    this.treatmentProtocol = treatmentProtocol;
    this.extractProtocol = extractProtocol;
  }

  public Integer getChannelNumber()
  {
    return channelNumber;
  }

  public String getSourceName()
  {
    return sourceName;
  }

  public List<String> getOrganisms()
  {
    return organisms;
  }

  public Map<String, String> getCharacteristics()
  {
    return characteristics;
  }

  public String getMolecule()
  {
    return molecule;
  }

  public String getLabel()
  {
    return label;
  }

  public Optional<String> getTreatmentProtocol()
  {
    return treatmentProtocol;
  }

  public Optional<String> getExtractProtocol()
  {
    return extractProtocol;
  }
}
