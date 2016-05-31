package org.metadatacenter.readers.geo.metadata;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Protocol
{
  private final List<String> growthProtocols;
  private final List<String> treatmentProtocols;
  private final List<String> extractProtocols;
  private final List<String> labelProtocols;
  private final List<String> hybridizationProtocol;
  private final List<String> scanProtocols;
  private final List<String> dataProcessing;
  private final List<String> valueDefinition;
  private final Map<String, List<String>> userDefinedFields;

  public Protocol(List<String> growthProtocols, List<String> treatmentProtocols, List<String> extractionProtocol,
    List<String> labelProtocols, List<String> hybridizationProtocol, List<String> scanProtocols,
    List<String> dataProcessing, List<String> valueDefinition, Map<String, List<String>> userDefinedFields)
  {
    this.growthProtocols = Collections.unmodifiableList(growthProtocols);
    this.treatmentProtocols = Collections.unmodifiableList(treatmentProtocols);
    this.extractProtocols = Collections.unmodifiableList(extractionProtocol);
    this.labelProtocols = Collections.unmodifiableList(labelProtocols);
    this.hybridizationProtocol = Collections.unmodifiableList(hybridizationProtocol);
    this.scanProtocols = Collections.unmodifiableList(scanProtocols);
    this.dataProcessing = Collections.unmodifiableList(dataProcessing);
    this.valueDefinition = Collections.unmodifiableList(valueDefinition);
    this.userDefinedFields = Collections.unmodifiableMap(userDefinedFields);
  }

  public List<String> getGrowthProtocols()
  {
    return growthProtocols;
  }

  public List<String> getTreatmentProtocols()
  {
    return treatmentProtocols;
  }

  public List<String> getExtractProtocols()
  {
    return extractProtocols;
  }

  public List<String> getLabelProtocol()
  {
    return labelProtocols;
  }

  public List<String> getHybridizationProtocol()
  {
    return hybridizationProtocol;
  }

  public List<String> getScanProtocols()
  {
    return scanProtocols;
  }

  public List<String> getDataProcessing()
  {
    return dataProcessing;
  }

  public List<String> getValueDefinition()
  {
    return valueDefinition;
  }

  public Map<String, List<String>> getUserDefinedFields()
  {
    return userDefinedFields;
  }

  @Override public String toString()
  {
    return "Protocol{" +
      "\n growthProtocols=" + growthProtocols +
      "\n treatmentProtocols=" + treatmentProtocols +
      "\n extractProtocols='" + extractProtocols + '\'' +
      "\n labelProtocols='" + labelProtocols + '\'' +
      "\n hybridizationProtocol='" + hybridizationProtocol + '\'' +
      "\n scanProtocols='" + scanProtocols + '\'' +
      "\n dataProcessing='" + dataProcessing + '\'' +
      "\n valueDefinition='" + valueDefinition + '\'' +
      '}';
  }
}
