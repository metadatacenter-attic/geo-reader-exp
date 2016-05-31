package org.metadatacenter.readers.geo.formats.geosoft;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Names for metadata fields in GEO metadata spreadsheet.
 * <p>
 * See http://www.ncbi.nlm.nih.gov/geo/info/spreadsheet.html#GAmeta.
 */
public class GEOSoftNames
{
  public final static String MULTI_VALUE_FIELD_SEPARATOR = "; ";

  public final static String GEO_METADATA_SHEET_NAME = "Metadata Example";

  public final static String SERIES_HEADER_NAME = "SERIES";
  public final static String SAMPLES_HEADER_NAME = "SAMPLES";
  public final static String PROTOCOLS_HEADER_NAME = "PROTOCOLS";
  public final static String PLATFORM_HEADER_NAME = "PLATFORM";

  public final static String SERIES_TITLE_FIELD_NAME = "title";
  public final static String SERIES_SUMMARY_FIELD_NAME = "summary";
  public final static String SERIES_OVERALL_DESIGN_FIELD_NAME = "overall design";
  public final static String SERIES_CONTRIBUTOR_FIELD_NAME = "contributor";
  public final static String SERIES_WEB_LINK_FIELD_NAME = "web link";
  public final static String SERIES_PUBMED_ID_FIELD_NAME = "pubmed id";
  public final static String SERIES_VARIABLE_FIELD_NAME = "variable";
  public final static String SERIES_REPEAT_FIELD_NAME = "repeat";

  public final static String SAMPLES_SAMPLE_NAME_FIELD_NAME = "Sample name";
  public final static String SAMPLES_NAME_FIELD_NAME = "Sample name";
  public final static String SAMPLES_TITLE_FIELD_NAME = "title";
  public final static String SAMPLES_RAW_DATA_FILE_FIELD_NAME = "raw data file";
  public final static String SAMPLES_CEL_FILE_FIELD_NAME = "CEL file";
  public final static String SAMPLES_EXP_FILE_FIELD_NAME = "EXP file";
  public final static String SAMPLES_CHP_FILE_FIELD_NAME = "CHP file";
  public final static String SAMPLES_SOURCE_NAME_FIELD_NAME = "source name";
  public final static String SAMPLES_ORGANISM_FIELD_NAME = "organism";
  public final static String SAMPLES_BIOMATERIAL_PROVIDER_FIELD_NAME = "biomaterial provider";
  public final static String SAMPLES_MOLECULE_FIELD_NAME = "molecule";
  public final static String SAMPLES_LABEL_FIELD_NAME = "label";
  public final static String SAMPLES_DESCRIPTION_FIELD_NAME = "description";
  public final static String SAMPLES_PLATFORM_FIELD_NAME = "platform";

  public static final String PROTOCOL_GROWTH_FIELD_NAME = "growth protocol";
  public static final String PROTOCOL_TREATMENT_FIELD_NAME = "treatment protocol";
  public static final String PROTOCOL_EXTRACT_FIELD_NAME = "extract protocol";
  public static final String PROTOCOL_LABEL_FIELD_NAME = "label protocol";
  public static final String PROTOCOL_HYB_FIELD_NAME = "hyb protocol";
  public static final String PROTOCOL_SCAN_FIELD_NAME = "scan protocol";
  public static final String PROTOCOL_DATA_PROCESSING_FIELD_NAME = "data processing";
  public static final String PROTOCOL_VALUE_DEFINITION_FIELD_NAME = "value definition";

  public final static String PLATFORM_TITLE_FIELD_NAME = "title";
  public final static String PLATFORM_DISTRIBUTION_FIELD_NAME = "distribution";
  public final static String PLATFORM_TECHNOLOGY_FIELD_NAME = "technology";
  public final static String PLATFORM_ORGANISM_FIELD_NAME = "organism";
  public final static String PLATFORM_MANUFACTURER_FIELD_NAME = "manufacturer";
  public final static String PLATFORM_MANUFACTURE_PROTOCOL_FIELD_NAME = "manufacture protocol";
  public final static String PLATFORM_DESCRIPTION_FIELD_NAME = "description";
  public final static String PLATFORM_CATALOG_NUMBER_FIELD_NAME = "catalog number";
  public final static String PLATFORM_WEB_LINK_FIELD_NAME = "web link";
  public final static String PLATFORM_SUPPORT_FIELD_NAME = "support";
  public final static String PLATFORM_COATING_FIELD_NAME = "coating";
  public final static String PLATFORM_CONTRIBUTOR_FIELD_NAME = "contributor";
  public final static String PLATFORM_PUBMED_ID_FIELD_NAME = "pubmed id";
  public final static String PLATFORM_NATIVE_ARRAY_DESCRIPTION_FILE_NAME = "native array description file";
  public final static String PLATFORM_PLATFORM_FILE_NAME = "Platform file";

  public final static String CHARACTERISTICS_FIELD_PREFIX = "characteristics: ";

  public final static String[] SERIES_FIELD_NAMES = { SERIES_TITLE_FIELD_NAME, SERIES_SUMMARY_FIELD_NAME,
    SERIES_OVERALL_DESIGN_FIELD_NAME, SERIES_CONTRIBUTOR_FIELD_NAME, SERIES_WEB_LINK_FIELD_NAME,
    SERIES_PUBMED_ID_FIELD_NAME, SERIES_VARIABLE_FIELD_NAME, SERIES_REPEAT_FIELD_NAME };

  public final static String[] SAMPLES_COLUMN_NAMES = { SAMPLES_NAME_FIELD_NAME, SAMPLES_TITLE_FIELD_NAME,
    SAMPLES_RAW_DATA_FILE_FIELD_NAME, SAMPLES_CEL_FILE_FIELD_NAME, SAMPLES_EXP_FILE_FIELD_NAME,
    SAMPLES_CHP_FILE_FIELD_NAME, SAMPLES_SOURCE_NAME_FIELD_NAME, SAMPLES_ORGANISM_FIELD_NAME,
    SAMPLES_BIOMATERIAL_PROVIDER_FIELD_NAME, SAMPLES_MOLECULE_FIELD_NAME, SAMPLES_LABEL_FIELD_NAME,
    SAMPLES_DESCRIPTION_FIELD_NAME, SAMPLES_PLATFORM_FIELD_NAME };

  public final static String[] PROTOCOL_FIELD_NAMES = { PROTOCOL_GROWTH_FIELD_NAME, PROTOCOL_TREATMENT_FIELD_NAME,
    PROTOCOL_EXTRACT_FIELD_NAME, PROTOCOL_LABEL_FIELD_NAME, PROTOCOL_HYB_FIELD_NAME, PROTOCOL_SCAN_FIELD_NAME,
    PROTOCOL_DATA_PROCESSING_FIELD_NAME, PROTOCOL_VALUE_DEFINITION_FIELD_NAME };

  public final static String[] PLATFORM_FIELD_NAMES = { PLATFORM_TITLE_FIELD_NAME, PLATFORM_DISTRIBUTION_FIELD_NAME,
    PLATFORM_TECHNOLOGY_FIELD_NAME, PLATFORM_ORGANISM_FIELD_NAME, PLATFORM_MANUFACTURER_FIELD_NAME,
    PLATFORM_MANUFACTURE_PROTOCOL_FIELD_NAME, PLATFORM_DESCRIPTION_FIELD_NAME, PLATFORM_CATALOG_NUMBER_FIELD_NAME,
    PLATFORM_WEB_LINK_FIELD_NAME, PLATFORM_SUPPORT_FIELD_NAME, PLATFORM_COATING_FIELD_NAME,
    PLATFORM_CONTRIBUTOR_FIELD_NAME, PLATFORM_PUBMED_ID_FIELD_NAME, PLATFORM_NATIVE_ARRAY_DESCRIPTION_FILE_NAME,
    PLATFORM_PLATFORM_FILE_NAME };

  public final static String[] REPEAT_TYPE_NAMES = { "biological replicate", "technical replicate - extract",
    "technical replicate - labeled-extract" };

  public final static String[] VARIABLE_NAMES = { "dose", "time", "tissue", "strain", "gender", "cell line",
    "development stage", "age", "agent", "cell type", "infection", "isolate", "metabolism", "shock, stress",
    "temperature", "specimen", "disease state", "protocol", "growth protocol", "genotype/variation", "species",
    "individual", "other" };

  public final static Set<String> SeriesFieldNames = new HashSet<>(Arrays.asList(SERIES_FIELD_NAMES));

  public final static Set<String> RepeatTypeNames = new HashSet<>(Arrays.asList(REPEAT_TYPE_NAMES));

  public final static Set<String> SamplesColumnNames = new HashSet<>(Arrays.asList(SAMPLES_COLUMN_NAMES));

  public final static Set<String> VariableNames = new HashSet<>(Arrays.asList(VARIABLE_NAMES));

  public final static Set<String> ProtocolFieldNames = new HashSet<>(Arrays.asList(PROTOCOL_FIELD_NAMES));

  public final static Set<String> PlatformFieldNames = new HashSet<>(Arrays.asList(PLATFORM_FIELD_NAMES));

  public final static int FIELD_NAMES_COLUMN_NUMBER = 0;
  public final static int FIELD_VALUES_COLUMN_NUMBER = 1;

  public final static String CEL_FILE_TYPE_NAME = "CEL";
  public final static String CHP_FILE_TYPE_NAME = "CHP";
  public final static String EXP_FILE_TYPE_NAME = "EXP";
  public final static String RAW_FILE_TYPE_NAME = "RAW";
}
