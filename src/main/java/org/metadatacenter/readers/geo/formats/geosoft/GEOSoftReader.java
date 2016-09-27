package org.metadatacenter.readers.geo.formats.geosoft;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.metadatacenter.readers.geo.GEOReaderException;
import org.metadatacenter.readers.geo.metadata.Contributor;
import org.metadatacenter.readers.geo.metadata.GEOSubmissionMetadata;
import org.metadatacenter.readers.geo.metadata.PerChannelSampleInfo;
import org.metadatacenter.readers.geo.metadata.Platform;
import org.metadatacenter.readers.geo.metadata.Protocol;
import org.metadatacenter.readers.geo.metadata.Sample;
import org.metadatacenter.readers.geo.metadata.Series;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.CHARACTERISTICS_FIELD_PREFIX;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.FIELD_NAMES_COLUMN_NUMBER;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.FIELD_VALUES_COLUMN_NUMBER;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.GEO_METADATA_SHEET_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_CATALOG_NUMBER_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_COATING_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_CONTRIBUTOR_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_DESCRIPTION_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_DISTRIBUTION_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_HEADER_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_MANUFACTURER_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_MANUFACTURE_PROTOCOL_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_ORGANISM_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_PUBMED_ID_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_SUPPORT_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_TECHNOLOGY_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_TITLE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PLATFORM_WEB_LINK_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOLS_HEADER_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_DATA_PROCESSING_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_EXTRACT_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_GROWTH_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_HYB_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_LABEL_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_SCAN_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_TREATMENT_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PROTOCOL_VALUE_DEFINITION_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.PlatformFieldNames;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.ProtocolFieldNames;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_BIOMATERIAL_PROVIDER_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_CEL_FILE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_CHP_FILE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_DESCRIPTION_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_EXP_FILE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_HEADER_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_LABEL_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_MOLECULE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_ORGANISM_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_RAW_DATA_FILE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_SAMPLE_NAME_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_SOURCE_NAME_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SAMPLES_TITLE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SERIES_HEADER_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SERIES_OVERALL_DESIGN_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SERIES_PUBMED_ID_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SERIES_SUMMARY_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SERIES_TITLE_FIELD_NAME;
import static org.metadatacenter.readers.geo.formats.geosoft.GEOSoftNames.SeriesFieldNames;

public class GEOSoftReader
{
  private final String spreadsheetFileName;

  public GEOSoftReader(String spreadsheetFileName) throws GEOReaderException
  {
    this.spreadsheetFileName = spreadsheetFileName;
  }

  public GEOSubmissionMetadata extractGEOSubmissionMetadata() throws GEOReaderException
  {
    InputStream spreadsheetStream = SpreadsheetUtil.openSpreadsheetInputStream(spreadsheetFileName);
    Workbook workbook = SpreadsheetUtil.createReadonlyWorkbook(spreadsheetStream);
    Sheet geoMetadataSheet = getGEOMetadataSheet(workbook);

    return extractGEOSubmissionMetadata(geoMetadataSheet);
  }

  private GEOSubmissionMetadata extractGEOSubmissionMetadata(Sheet geoMetadataSheet)
    throws GEOReaderException
  {
    Series series = extractSeries(geoMetadataSheet);
    Map<String, Sample> samples = extractSamples(series.getGSE(), geoMetadataSheet);
    Protocol protocol = extractProtocol(geoMetadataSheet);
    Optional<Platform> platform = extractPlatform(geoMetadataSheet);
    List<Platform> platforms = platform.isPresent() ?
      Collections.singletonList(platform.get()) :
      Collections.emptyList();

    return new GEOSubmissionMetadata(series, samples, Optional.of(protocol), platforms);
  }

  private Series extractSeries(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Map<String, List<String>> seriesFields = extractSeriesFields(geoMetadataSheet);
    String gse = ""; // Metadata spreadsheet does not have GSE identifier
    String title = getRequiredMultiValueFieldValue(seriesFields, SERIES_TITLE_FIELD_NAME, SERIES_HEADER_NAME);
    List<String> summary = getMultiValueFieldValues(seriesFields, SERIES_SUMMARY_FIELD_NAME);
    List<String> overallDesign = getMultiValueFieldValues(seriesFields, SERIES_OVERALL_DESIGN_FIELD_NAME);
    List<Contributor> contributors = extractContributors(seriesFields, SERIES_PUBMED_ID_FIELD_NAME);
    List<String> pubMedIDs = getMultiValueFieldValues(seriesFields, SERIES_PUBMED_ID_FIELD_NAME);
    Map<String, Map<String, String>> variables = new HashMap<>(); // TODO
    Map<String, List<String>> repeat = new HashMap<>(); // TODO

    return new Series(gse, title, summary, overallDesign, contributors, pubMedIDs, variables, repeat);
  }

  private List<Contributor> extractContributors(Map<String, List<String>> seriesFields,
    String SERIES_CONTRIBUTOR_FIELD_NAME) throws GEOReaderException
  {
    List<Contributor> contributors = new ArrayList<>();
    List<String> contributorNameFieldValues = getMultiValueFieldValues(seriesFields, SERIES_CONTRIBUTOR_FIELD_NAME);

    String regexp = "([A-Za-z]+),\\s+([A-Za-z]+)\\s+([A-Za-z]+)";
    Pattern pattern = Pattern.compile(regexp);
    for (String contributorName : contributorNameFieldValues) {
      Matcher matcher = pattern.matcher(contributorName);
      if (matcher.matches()) {
        String firstName = matcher.group(1);
        String middleInitial = matcher.group(2);
        String lastName = matcher.group(3);
        contributors.add(new Contributor(firstName + " " + middleInitial + " " + lastName));
      } else { // Just add entire string as last name
        contributors.add(new Contributor(contributorName));
      }
    }
    return contributors;
  }

  private String getRequiredSingleValueFieldValue(Map<String, String> fields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    Optional<String> fieldValue = getSingleValueFieldValue(fields, fieldName);

    if (fieldValue.isPresent())
      return fieldValue.get();
    else
      throw new GEOReaderException("could not find required field " + fieldName + " in " + fieldCollectionName);
  }

  private Optional<String> getOptionalMultiValueFieldValue(Map<String, List<String>> fields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    if (fields.containsKey(fieldName)) {
      if (fields.get(fieldName).size() == 1)
        return Optional.of(fields.get(fieldName).iterator().next());
      else
        throw new GEOReaderException(
          "not expecting multiple values for field " + fieldName + " in " + fieldCollectionName);
    } else
      return Optional.empty();
  }

  private String getRequiredMultiValueFieldValue(Map<String, List<String>> fields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    Optional<String> fieldValue = getOptionalMultiValueFieldValue(fields, fieldName, fieldCollectionName);

    if (fieldValue.isPresent())
      return fieldValue.get();
    else
      throw new GEOReaderException(
        "could not find required multi-value field " + fieldName + " in " + fieldCollectionName);
  }

  private Optional<String> getSingleValueFieldValue(Map<String, String> fields, String fieldName)
    throws GEOReaderException
  {
    if (fields.containsKey(fieldName)) {
      return Optional.of(fields.get(fieldName));
    } else
      return Optional.empty();
  }

  private List<String> getMultiValueFieldValues(Map<String, List<String>> fields, String fieldName)
    throws GEOReaderException
  {
    if (fields.containsKey(fieldName))
      return fields.get(fieldName);
    else
      return Collections.emptyList();
  }

  private List<String> getRequiredMultiValueFieldValues(Map<String, List<String>> fields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    if (fields.containsKey(fieldName))
      return fields.get(fieldName);
    else
      throw new GEOReaderException("no values for field " + fieldName + " in " + fieldCollectionName);
  }

  private Map<String, List<String>> extractSeriesFields(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Optional<Integer> seriesHeaderRowNumber = findFieldRowNumber(geoMetadataSheet, SERIES_HEADER_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (!seriesHeaderRowNumber.isPresent())
      throw new GEOReaderException("no series header found in metadata spreadsheet");

    Optional<Integer> seriesTitleRowNumber = findFieldRowNumber(geoMetadataSheet, SERIES_TITLE_FIELD_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (seriesTitleRowNumber.isPresent()) {
      Map<String, List<String>> fieldName2Values = findFieldValues(geoMetadataSheet, FIELD_NAMES_COLUMN_NUMBER,
        FIELD_VALUES_COLUMN_NUMBER, seriesTitleRowNumber.get());

      if (fieldName2Values.isEmpty())
        throw new GEOReaderException("no series fields found in metadata spreadsheet");

      if (!SeriesFieldNames.containsAll(fieldName2Values.keySet())) {
        Set<String> fieldNames = fieldName2Values.keySet();
        fieldNames.removeAll(SeriesFieldNames);
        throw new GEOReaderException("unknown series fields " + fieldNames);
      }

      return fieldName2Values;
    } else
      throw new GEOReaderException("no series title field named " + SERIES_TITLE_FIELD_NAME + " in metadata sheet");
  }

  private Protocol extractProtocol(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Map<String, List<String>> protocolFields = extractProtocolFields(geoMetadataSheet);

    if (protocolFields.isEmpty())
      throw new GEOReaderException("no protocol fields found in metadata sheet");

    List<String> growth = getMultiValueFieldValues(protocolFields, PROTOCOL_GROWTH_FIELD_NAME);
    List<String> treatment = getMultiValueFieldValues(protocolFields, PROTOCOL_TREATMENT_FIELD_NAME);
    List<String> extract = getMultiValueFieldValues(protocolFields, PROTOCOL_EXTRACT_FIELD_NAME);
    List<String> label = getMultiValueFieldValues(protocolFields, PROTOCOL_LABEL_FIELD_NAME);
    List<String> hyb = getMultiValueFieldValues(protocolFields, PROTOCOL_HYB_FIELD_NAME);
    List<String> scan = getMultiValueFieldValues(protocolFields, PROTOCOL_SCAN_FIELD_NAME);
    List<String> dataProcessing = getMultiValueFieldValues(protocolFields, PROTOCOL_DATA_PROCESSING_FIELD_NAME);
    List<String> valueDefinition = getMultiValueFieldValues(protocolFields, PROTOCOL_VALUE_DEFINITION_FIELD_NAME);

    // The fields not in the predefined field set become user-defined fields
    protocolFields.keySet().removeAll(ProtocolFieldNames);

    return new Protocol(growth, treatment, extract, label, hyb, scan, dataProcessing, valueDefinition, protocolFields);
  }

  private Optional<Platform> extractPlatform(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Map<String, List<String>> platformFields = extractPlatformFields(geoMetadataSheet);

    if (!platformFields.isEmpty()) {
      String title = getRequiredMultiValueFieldValue(platformFields, PLATFORM_TITLE_FIELD_NAME, PLATFORM_HEADER_NAME);
      String distribution = getRequiredMultiValueFieldValue(platformFields, PLATFORM_DISTRIBUTION_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      String technology = getRequiredMultiValueFieldValue(platformFields, PLATFORM_TECHNOLOGY_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      String organism = getRequiredMultiValueFieldValue(platformFields, PLATFORM_ORGANISM_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      Optional<String> manufacturer = getOptionalMultiValueFieldValue(platformFields, PLATFORM_MANUFACTURER_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      List<String> manufacturerProtocol = getMultiValueFieldValues(platformFields,
        PLATFORM_MANUFACTURE_PROTOCOL_FIELD_NAME);
      List<String> description = getMultiValueFieldValues(platformFields, PLATFORM_DESCRIPTION_FIELD_NAME);
      Optional<String> catalogNumber = getOptionalMultiValueFieldValue(platformFields,
        PLATFORM_CATALOG_NUMBER_FIELD_NAME, PLATFORM_HEADER_NAME);
      Optional<String> webLink = getOptionalMultiValueFieldValue(platformFields, PLATFORM_WEB_LINK_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      Optional<String> support = getOptionalMultiValueFieldValue(platformFields, PLATFORM_SUPPORT_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      Optional<String> coating = getOptionalMultiValueFieldValue(platformFields, PLATFORM_COATING_FIELD_NAME,
        PLATFORM_HEADER_NAME);
      List<String> contributor = getMultiValueFieldValues(platformFields, PLATFORM_CONTRIBUTOR_FIELD_NAME);
      List<String> pubMedID = getMultiValueFieldValues(platformFields, PLATFORM_PUBMED_ID_FIELD_NAME);

      return Optional.of(
        new Platform(title, distribution, technology, organism, manufacturer, manufacturerProtocol, description,
          catalogNumber, webLink, support, coating, contributor, pubMedID));
    } else
      return Optional.empty();
  }

  private Map<String, List<String>> extractPlatformFields(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Optional<Integer> platformHeaderRowNumber = findFieldRowNumber(geoMetadataSheet, PLATFORM_HEADER_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (platformHeaderRowNumber.isPresent()) {

      Map<String, List<String>> fieldName2Values = findFieldValues(geoMetadataSheet, FIELD_NAMES_COLUMN_NUMBER,
        FIELD_VALUES_COLUMN_NUMBER, platformHeaderRowNumber.get() + 1);

      if (!fieldName2Values.isEmpty()) {

        if (!PlatformFieldNames.containsAll(fieldName2Values.keySet())) {
          Set<String> fieldNames = fieldName2Values.keySet();
          fieldNames.removeAll(PlatformFieldNames);
          throw new GEOReaderException("unknown platform fields " + fieldNames);
        }

        return fieldName2Values;
      } else
        return Collections.emptyMap();
    } else
      return Collections.emptyMap();
  }

  private Map<String, List<String>> extractProtocolFields(Sheet geoMetadataSheet) throws GEOReaderException
  {
    Optional<Integer> protocolsHeaderRowNumber = findFieldRowNumber(geoMetadataSheet, PROTOCOLS_HEADER_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (!protocolsHeaderRowNumber.isPresent())
      throw new GEOReaderException("no protocols header field named " + PROTOCOLS_HEADER_NAME + " in metadata sheet");

    Map<String, List<String>> fieldName2Values = findProtocolFieldValues(geoMetadataSheet, FIELD_NAMES_COLUMN_NUMBER,
      FIELD_VALUES_COLUMN_NUMBER, protocolsHeaderRowNumber.get() + 1);

    if (fieldName2Values.isEmpty())
      throw new GEOReaderException("no protocol fields found in metadata spreadsheet");

    // We do not indicate an error if there fields beyond the set defined in GEONames.ProtocolFieldNames
    // because it appears that user are allowed to define arbitrary fields.

    return fieldName2Values;
  }

  private Map<String, Sample> extractSamples(String gse, Sheet geoMetadataSheet) throws GEOReaderException
  {
    Map<String, Sample> samples = new HashMap<>();

    Optional<Integer> samplesHeaderRowNumber = findFieldRowNumber(geoMetadataSheet, SAMPLES_HEADER_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (!samplesHeaderRowNumber.isPresent())
      throw new GEOReaderException("no samples header found in metadata spreadsheet");

    Optional<Integer> samplesColumnNamesRowNumber = findFieldRowNumber(geoMetadataSheet, SAMPLES_SAMPLE_NAME_FIELD_NAME,
      FIELD_NAMES_COLUMN_NUMBER);

    if (samplesColumnNamesRowNumber.isPresent()) {

      // sample name -> (sample column name -> [value])
      Map<String, Map<String, List<String>>> samplesColumns = extractSamplesColumnValues(geoMetadataSheet,
        samplesColumnNamesRowNumber.get());

      for (String sampleName : samplesColumns.keySet()) {
        Map<String, List<String>> sampleFields = samplesColumns.get(sampleName);

        String sampleTitle = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_TITLE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        List<String> rawDataFiles = getRepeatedValueFieldValues(sampleFields, SAMPLES_RAW_DATA_FILE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        Optional<String> celFile = getOptionalMultiValueFieldValue(sampleFields, SAMPLES_CEL_FILE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        Optional<String> expFile = getOptionalMultiValueFieldValue(sampleFields, SAMPLES_EXP_FILE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        Optional<String> chpFile = getOptionalMultiValueFieldValue(sampleFields, SAMPLES_CHP_FILE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        String sourceName = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_SOURCE_NAME_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        String organism = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_ORGANISM_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        Map<String, String> characteristics = extractCharacteristicsFromSampleFields(sampleFields);
        Optional<String> biomaterialProvider = getOptionalMultiValueFieldValue(sampleFields,
          SAMPLES_BIOMATERIAL_PROVIDER_FIELD_NAME, SAMPLES_HEADER_NAME);
        String molecule = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_MOLECULE_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        String label = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_LABEL_FIELD_NAME, SAMPLES_HEADER_NAME);
        Optional<String> description = getOptionalMultiValueFieldValue(sampleFields, SAMPLES_DESCRIPTION_FIELD_NAME,
          SAMPLES_HEADER_NAME);
        String platform = getRequiredMultiValueFieldValue(sampleFields, SAMPLES_MOLECULE_FIELD_NAME,
          SAMPLES_HEADER_NAME);

        PerChannelSampleInfo perChannelSampleInfo = new PerChannelSampleInfo(0, sourceName, organism, characteristics,
          molecule, label, Optional.empty(), Optional.empty());
        Map<Integer, PerChannelSampleInfo> perChannelInformation = new HashMap<>();

        perChannelInformation.put(0, perChannelSampleInfo);

        Sample sample = new Sample(gse, sampleName, sampleTitle, label, description, platform, perChannelInformation,
          biomaterialProvider, rawDataFiles, celFile, expFile, chpFile);

        if (samples.containsKey(sampleName))
          throw new GEOReaderException("multiple entries for sample " + sampleName);

        samples.put(sampleName, sample);
      }
    } else
      throw new GEOReaderException(
        "no samples header field named " + SAMPLES_SAMPLE_NAME_FIELD_NAME + " in metadata sheet");

    if (samples.isEmpty())
      throw new GEOReaderException("no samples found in metadata sheet");

    return samples;
  }

  private List<String> getRequiredRepeatedValueFieldValues(Map<String, List<String>> sampleFields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    if (sampleFields.containsKey(fieldName)) {
      if (!sampleFields.get(fieldName).isEmpty())
        return sampleFields.get(fieldName);
      else
        throw new GEOReaderException("no values for required field " + fieldName + " in " + fieldCollectionName);
    } else
      throw new GEOReaderException("no required field " + fieldName + " in " + fieldCollectionName);
  }

  private List<String> getRepeatedValueFieldValues(Map<String, List<String>> sampleFields, String fieldName,
    String fieldCollectionName) throws GEOReaderException
  {
    if (sampleFields.containsKey(fieldName))
      return sampleFields.get(fieldName);
    else
      return new ArrayList<>();
  }

  private Map<String, String> extractCharacteristicsFromSampleFields(Map<String, List<String>> sampleFields)
    throws GEOReaderException
  {
    Map<String, String> characteristics = new HashMap<>();

    for (String fieldName : sampleFields.keySet()) {
      if (fieldName.startsWith(CHARACTERISTICS_FIELD_PREFIX)) {
        String characteristicName = fieldName.substring(CHARACTERISTICS_FIELD_PREFIX.length());
        if (characteristics.containsKey(characteristicName))
          throw new GEOReaderException("repeated characteristic " + characteristicName + " in metadata spreadsheet");

        List<String> characteristicValues = sampleFields.get(fieldName);
        if (!characteristicValues.isEmpty()) {
          if (characteristicValues.size() > 1)
            throw new GEOReaderException(
              "multiple values for characteristic " + characteristicName + " in metadata spreadsheet");
          String characteristicValue = characteristicValues.get(0);
          characteristics.put(characteristicName, characteristicValue);
        }
      }
    }
    return characteristics;
  }

  // Returns: sample name -> (sample field name -> [field values])
  // Some sample columns can be repeated (e.g., raw data file).
  private Map<String, Map<String, List<String>>> extractSamplesColumnValues(Sheet geoMetadataSheet,
    int samplesColumnNamesRowNumber) throws GEOReaderException
  {
    Map<String, Map<String, List<String>>> samplesColumnValues = new HashMap<>();
    List<String> samplesColumnNames = new ArrayList<>();
    Row samplesColumnNamesRow = geoMetadataSheet.getRow(samplesColumnNamesRowNumber);

    if (samplesColumnNamesRow == null)
      throw new GEOReaderException("could not find sample column names row at row " + samplesColumnNamesRowNumber);

    boolean lastColumnReached = false;
    for (int currentColumnNumber = 0;
         currentColumnNumber <= samplesColumnNamesRow.getLastCellNum() && !lastColumnReached; currentColumnNumber++) {
      Cell fieldNameCell = samplesColumnNamesRow.getCell(currentColumnNumber);

      if (fieldNameCell == null || SpreadsheetUtil.isBlankCellType(fieldNameCell)) {
        lastColumnReached = true;
      } else {
        String fieldNameValue = SpreadsheetUtil.getStringCellValue(fieldNameCell);

        if (fieldNameValue.isEmpty())
          throw new GEOReaderException(
            "empty samples title cell at row " + samplesColumnNamesRowNumber + ", column " + currentColumnNumber);

        samplesColumnNames.add(fieldNameValue);
      }
    }

    int numberOfSamplesColumns = samplesColumnNames.size();
    boolean blankRowReached = false;
    for (int currentRowNumber = samplesColumnNamesRowNumber + 1;
         currentRowNumber <= geoMetadataSheet.getLastRowNum() && !blankRowReached; currentRowNumber++) {
      Row valueRow = geoMetadataSheet.getRow(currentRowNumber);

      if (valueRow != null && valueRow.getPhysicalNumberOfCells() > 0) {
        Cell sampleNameCell = valueRow.getCell(0);
        if (sampleNameCell != null && !SpreadsheetUtil.isBlankCellType(sampleNameCell)) {
          String sampleName = SpreadsheetUtil.getCellValueAsString(sampleNameCell);

          if (sampleName.isEmpty())
            throw new GEOReaderException("empty sample name at row " + currentRowNumber);

          if (samplesColumnValues.containsKey(sampleName))
            throw new GEOReaderException("duplicate sample name " + sampleName + " found at row " + currentRowNumber);

          samplesColumnValues.put(sampleName, new HashMap<>());

          for (int currentColumnNumber = 0; currentColumnNumber < numberOfSamplesColumns; currentColumnNumber++) {
            Cell fieldValueCell = valueRow.getCell(currentColumnNumber);
            if (fieldValueCell != null && !SpreadsheetUtil.isBlankCellType(fieldValueCell)) {
              String fieldValue = SpreadsheetUtil.getCellValueAsString(fieldValueCell);
              if (!fieldValue.isEmpty()) {
                String headerFieldName = samplesColumnNames.get(currentColumnNumber);
                if (samplesColumnValues.get(sampleName).containsKey(headerFieldName)) {
                  samplesColumnValues.get(sampleName).get(headerFieldName).add(fieldValue);
                } else {
                  List<String> fieldValues = new ArrayList<>();
                  fieldValues.add(fieldValue);
                  samplesColumnValues.get(sampleName).put(headerFieldName, fieldValues);
                }
              }
            }
          }
        } else
          blankRowReached = true;
      } else
        blankRowReached = true;
    }
    return samplesColumnValues;
  }

  private Sheet getGEOMetadataSheet(Workbook workbook) throws GEOReaderException
  {
    Sheet metadataSheet = workbook.getSheet(GEO_METADATA_SHEET_NAME);

    if (metadataSheet == null)
      throw new GEOReaderException(
        "spreadsheet does not contain a GEO metadata template sheet called " + GEO_METADATA_SHEET_NAME);
    else
      return metadataSheet;
  }

  public static Optional<Integer> findFieldRowNumber(Sheet sheet, String fieldName, int fieldColumnNumber)
    throws GEOReaderException
  {
    int firstRow = 0;
    int lastRow = sheet.getLastRowNum();

    for (int currentRow = firstRow; currentRow <= lastRow; currentRow++) {
      Row row = sheet.getRow(currentRow);
      if (row != null) {
        Cell cell = row.getCell(fieldColumnNumber);
        if (cell != null && SpreadsheetUtil.isStringCellType(cell)) {
          String value = SpreadsheetUtil.getCellValueAsString(cell);
          if (fieldName.equals(value))
            return Optional.of(currentRow);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * A field can have multiple values.
   */
  private Map<String, List<String>> findFieldValues(Sheet sheet, int fieldNameColumnNumber, int fieldValueColumnNumber,
    int startRowNumber) throws GEOReaderException
  {
    Map<String, List<String>> field2Values = new HashMap<>();
    int finishRowNumber = sheet.getLastRowNum();
    boolean blankRowReached = false;

    for (int currentRow = startRowNumber; currentRow <= finishRowNumber && !blankRowReached; currentRow++) {
      Row row = sheet.getRow(currentRow);
      if (row == null)
        blankRowReached = true;
      else {
        Cell fieldNameCell = row.getCell(fieldNameColumnNumber);

        if (fieldNameCell == null || SpreadsheetUtil.isBlankCellType(fieldNameCell)) {
          blankRowReached = true;
          continue;
        }

        Cell fieldValueCell = row.getCell(fieldValueColumnNumber);

        if (fieldValueCell == null || SpreadsheetUtil.isBlankCellType(fieldValueCell)) {
          blankRowReached = true;
          continue;
        }

        String fieldName = SpreadsheetUtil.getStringCellValue(fieldNameCell);

        if (fieldName.isEmpty())
          throw new GEOReaderException(
            "empty field name at location " + SpreadsheetUtil.getCellLocation(fieldNameCell));

        String fieldValue = SpreadsheetUtil.getCellValueAsString(fieldValueCell);

        if (fieldValue.isEmpty())
          throw new GEOReaderException(
            "empty field value at location " + SpreadsheetUtil.getCellLocation(fieldValueCell));

        if (field2Values.containsKey(fieldName))
          field2Values.get(fieldName).add(fieldValue);
        else {
          List<String> fieldValues = new ArrayList<>();
          fieldValues.add(fieldValue);
          field2Values.put(fieldName, fieldValues);
        }
      }
    }
    return field2Values;
  }

  /**
   * Duplicates not allowed.
   */
  private Map<String, List<String>> findProtocolFieldValues(Sheet sheet, int fieldNameColumnNumber,
    int fieldValueColumnNumber, int startRowNumber) throws GEOReaderException
  {
    Map<String, List<String>> field2Values = new HashMap<>();
    boolean blankRowReached = false;

    for (int currentRow = startRowNumber; currentRow <= sheet.getLastRowNum() && !blankRowReached; currentRow++) {
      Row row = sheet.getRow(currentRow);

      if (row == null)
        blankRowReached = true;
      else {
        Cell fieldNameCell = row.getCell(fieldNameColumnNumber);

        if (fieldNameCell == null || SpreadsheetUtil.isBlankCellType(fieldNameCell))
          blankRowReached = true;
        else {
          String fieldName = SpreadsheetUtil.getStringCellValue(fieldNameCell);
          if (fieldName.isEmpty())
            blankRowReached = true;
          else {
            Cell fieldValueCell = row.getCell(fieldValueColumnNumber);

            if (fieldValueCell != null && !SpreadsheetUtil.isBlankCellType(fieldValueCell)) {

              String fieldValue = SpreadsheetUtil.getCellValueAsString(fieldValueCell); // Check for null cell

              if (fieldValue.isEmpty())
                throw new GEOReaderException(
                  "empty field value at location " + SpreadsheetUtil.getCellLocation(fieldValueCell));

              if (field2Values.containsKey(fieldName))
                field2Values.get(fieldName).add(fieldValue);
              else {
                List<String> fieldValues = new ArrayList<>();
                fieldValues.add(fieldValue);
                field2Values.put(fieldName, fieldValues);
              }
            }
          }
        }
      }
    }
    return field2Values;
  }
}
