package org.metadatacenter.readers.geo.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Metadata for a GEO sample.
 *
 * @see GEOSubmissionMetadata
 */
public class Sample
{
  private final String gse; // Series from which the sample came
  private final String gsm;
  private final String title;
  private final String label;
  private final Optional<String> description;
  private final String gpl;
  private final Map<Integer, PerChannelSampleInfo> perChannelInformation;
  private final Optional<String> biomaterialProvider;
  private final List<String> rawDataFiles;
  private final Optional<String> celFile;
  private final Optional<String> expFile;
  private final Optional<String> chpFile;

  public Sample(String gse, String gsm, String title, String label, Optional<String> description, String gpl,
    Map<Integer, PerChannelSampleInfo> perChannelInformation, Optional<String> biomaterialProvider,
    List<String> rawDataFiles, Optional<String> celFile, Optional<String> expFile, Optional<String> chpFile)
  {
    this.gse = gse;
    this.gsm = gsm;
    this.title = title;
    this.label = label;
    this.description = description;
    this.gpl = gpl;
    this.perChannelInformation = Collections.unmodifiableMap(perChannelInformation);
    this.biomaterialProvider = biomaterialProvider;
    this.rawDataFiles = Collections.unmodifiableList(rawDataFiles);
    this.celFile = celFile;
    this.expFile = expFile;
    this.chpFile = chpFile;
  }

  public String getGSE()
  {
    return gse;
  }

  public String getGSM()
  {
    return gsm;
  }

  public String getTitle()
  {
    return title;
  }

  public String getLabel()
  {
    return label;
  }

  public Optional<String> getDescription()
  {
    return description;
  }

  public String getGPL()
  {
    return gpl;
  }

  public Map<Integer, PerChannelSampleInfo> getPerChannelInformation()
  {
    return perChannelInformation;
  }

  public Optional<String> getBiomaterialProvider()
  {
    return biomaterialProvider;
  }

  public List<String> getRawDataFiles()
  {
    return rawDataFiles;
  }

  public Optional<String> getCelFile()
  {
    return celFile;
  }

  public Optional<String> getExpFile()
  {
    return expFile;
  }

  public Optional<String> getChpFile()
  {
    return chpFile;
  }

  public Map<String, String> getCharacteristics()
  {
    Map<String, String> characteristics = new HashMap<>();

    for (PerChannelSampleInfo perChannelSampleInfo : this.perChannelInformation.values())
      characteristics.putAll(perChannelSampleInfo.getCharacteristics());

    return characteristics;
  }

  public List<String> getOrganisms()
  {
    List<String> organisms = new ArrayList<>();

    for (PerChannelSampleInfo perChannelSampleInfo : this.perChannelInformation.values())
      organisms.addAll(perChannelSampleInfo.getOrganisms());

    return organisms;
  }

  public List<String> getSourceNames()
  {
    List<String> sourceNames = new ArrayList<>();

    for (PerChannelSampleInfo perChannelSampleInfo : this.perChannelInformation.values())
      sourceNames.add(perChannelSampleInfo.getSourceName());

    return sourceNames;
  }

  public List<String> getMolecules()
  {
    List<String> molecules = new ArrayList<>();

    for (PerChannelSampleInfo perChannelSampleInfo : this.perChannelInformation.values())
      molecules.add(perChannelSampleInfo.getMolecule());

    return molecules;
  }

  public List<String> getLabels()
  {
    List<String> labels = new ArrayList<>();

    for (PerChannelSampleInfo perChannelSampleInfo : this.perChannelInformation.values())
      labels.add(perChannelSampleInfo.getLabel());

    return labels;
  }
}

