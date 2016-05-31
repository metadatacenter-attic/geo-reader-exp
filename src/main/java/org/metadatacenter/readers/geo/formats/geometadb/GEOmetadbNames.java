package org.metadatacenter.readers.geo.formats.geometadb;

import java.util.Arrays;
import java.util.List;

/**
 * Contains names of tables and columns in the GEOmetadb database.
 * <p>
 * See: http://gbnci.abcc.ncifcrf.gov/geo/geo_help.php
 */
public class GEOmetadbNames
{
  public static final String SERIES_TABLE_NAME = "GSE";
  public static final String SAMPLE_TABLE_NAME = "GSM";
  public static final String PLATFORM_TABLE_NAME = "GPL";

  public static final String SERIES_TABLE_TITLE_COLUMN_NAME = "title";
  public static final String SERIES_TABLE_GSE_COLUMN_NAME = "gse";
  public static final String SERIES_TABLE_STATUS_COLUMN_NAME = "status";
  public static final String SERIES_TABLE_SUBMISSION_DATE_COLUMN_NAME = "submission_date";
  public static final String SERIES_TABLE_LAST_UPDATE_DATE_COLUMN_NAME = "last_update_date";
  public static final String SERIES_TABLE_PUBMED_ID_COLUMN_NAME = "pubmed_id";
  public static final String SERIES_TABLE_SUMMARY_COLUMN_NAME = "summary";
  public static final String SERIES_TABLE_TYPE_COLUMN_NAME = "type";
  public static final String SERIES_TABLE_CONTRIBUTOR_COLUMN_NAME = "contributor";
  public static final String SERIES_TABLE_CONTACT_COLUMN_NAME = "contact";
  public static final String SERIES_TABLE_WEB_LINK_COLUMN_NAME = "web_link";
  public static final String SERIES_TABLE_OVERALL_DESIGN_COLUMN_NAME = "overall_design";
  public static final String SERIES_TABLE_REPEATS_COLUMN_NAME = "repeats";
  public static final String SERIES_TABLE_REPEATS_SAMPLE_LIST_COLUMN_NAME = "repeats_sample_list";
  public static final String SERIES_TABLE_VARIABLE_COLUMN_NAME = "variable";
  public static final String SERIES_TABLE_VARIABLE_DESCRIPTION_COLUMN_NAME = "variable_description";
  public static final String SERIES_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME = "supplementary_file";

  public static final String SAMPLE_TABLE_TITLE_COLUMN_NAME = "title";
  public static final String SAMPLE_TABLE_GSM_COLUMN_NAME = "gsm";
  public static final String SAMPLE_TABLE_SERIES_ID_COLUMN_NAME = "series_id";
  public static final String SAMPLE_TABLE_GPL_COLUMN_NAME = "gpl";
  public static final String SAMPLE_TABLE_STATUS_COLUMN_NAME = "status";
  public static final String SAMPLE_TABLE_SUBMISSION_DATE_COLUMN_NAME = "submission_date";
  public static final String SAMPLE_TABLE_LAST_UPDATE_DATE_COLUMN_NAME = "last_update_date";
  public static final String SAMPLE_TABLE_TYPE_COLUMN_NAME = "type";
  public static final String SAMPLE_TABLE_CHANNEL_COUNT_COLUMN_NAME = "channel_count";
  public static final String SAMPLE_TABLE_SOURCE_NAME_CH1_COLUMN_NAME = "source_name_ch1";
  public static final String SAMPLE_TABLE_CHARACTERISTIC_CH1_COLUMN_NAME = "characteristics_ch1";
  public static final String SAMPLE_TABLE_MOLECULE_CH1_COLUMN_NAME = "molecule_ch1";
  public static final String SAMPLE_TABLE_LABEL_CH1_COLUMN_NAME = "label_ch1";
  public static final String SAMPLE_TABLE_TREATMENT_PROTOCOL_CH1_COLUMN_NAME = "treatment_protocol_ch1";
  public static final String SAMPLE_TABLE_EXTRACT_PROTOCOL_CH1_COLUMN_NAME = "extract_protocol_ch1";
  public static final String SAMPLE_TABLE_LABEL_PROTOCOL_CH1_COLUMN_NAME = "label_protocol_ch1";
  public static final String SAMPLE_TABLE_SOURCE_NAME_CH2_COLUMN_NAME = "source_name_ch2";
  public static final String SAMPLE_TABLE_CHARACTERISTIC_CH2_COLUMN_NAME = "characteristics_ch2";
  public static final String SAMPLE_TABLE_MOLECULE_CH2_COLUMN_NAME = "molecule_ch2";
  public static final String SAMPLE_TABLE_LABEL_CH2_COLUMN_NAME = "label_ch2";
  public static final String SAMPLE_TABLE_TREATMENT_PROTOCOL_CH2_COLUMN_NAME = "treatment_protocol_ch2";
  public static final String SAMPLE_TABLE_EXTRACT_PROTOCOL_CH2_COLUMN_NAME = "extract_protocol_ch2";
  public static final String SAMPLE_TABLE_LABEL_PROTOCOL_CH2_COLUMN_NAME = "label_protocol_ch2";
  public static final String SAMPLE_TABLE_HYB_PROTOCOL_COLUMN_NAME = "hyb_protocol";
  public static final String SAMPLE_TABLE_DESCRIPTION_COLUMN_NAME = "description";
  public static final String SAMPLE_TABLE_DATA_PROCESSING_COLUMN_NAME = "data_processing";
  public static final String SAMPLE_TABLE_CONTACT_COLUMN_NAME = "contact";
  public static final String SAMPLE_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME = "supplementary_file";
  public static final String SAMPLE_TABLE_DATA_ROW_COUNT_COLUMN_NAME = "data_row_count";

  public static final String PLATFORM_TABLE_TITLE_COLUMN_NAME = "title";
  public static final String PLATFORM_TABLE_GPL_COLUMN_NAME = "gpl";
  public static final String PLATFORM_TABLE_STATUS_COLUMN_NAME = "status";
  public static final String PLATFORM_TABLE_SUBMISSION_DATE_COLUMN_NAME = "submission_date";
  public static final String PLATFORM_TABLE_LAST_UPDATE_DATE_COLUMN_NAME = "last_update_date";
  public static final String PLATFORM_TABLE_TECHNOLOGY_COLUMN_NAME = "technology";
  public static final String PLATFORM_TABLE_DISTRIBUTION_COLUMN_NAME = "distribution";
  public static final String PLATFORM_TABLE_ORGANISM_COLUMN_NAME = "organism";
  public static final String PLATFORM_TABLE_MANUFACTURER_COLUMN_NAME = "manufacturer";
  public static final String PLATFORM_TABLE_MANUFACTURE_PROTOCOL_COLUMN_NAME = "manufacture_protocol";
  public static final String PLATFORM_TABLE_COATING_COLUMN_NAME = "coating";
  public static final String PLATFORM_TABLE_CATALOG_NUMBER_COLUMN_NAME = "catalog_number";
  public static final String PLATFORM_TABLE_SUPPORT_COLUMN_NAME = "support";
  public static final String PLATFORM_TABLE_DESCRIPTION_COLUMN_NAME = "description";
  public static final String PLATFORM_TABLE_WEB_LINK_COLUMN_NAME = "web_link";
  public static final String PLATFORM_TABLE_CONTACT_COLUMN_NAME = "contact";
  public static final String PLATFORM_TABLE_DATA_ROW_COUNT_COLUMN_NAME = "data_row_count";
  public static final String PLATFORM_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME = "supplementary_file";
  public static final String PLATFORM_TABLE_BIOC_PACKAGE_COLUMN_NAME = "bioc_package";

  public final static String[] SERIES_TABLE_COLUMN_NAMES = { SERIES_TABLE_TITLE_COLUMN_NAME,
    SERIES_TABLE_GSE_COLUMN_NAME, SERIES_TABLE_STATUS_COLUMN_NAME, SERIES_TABLE_SUBMISSION_DATE_COLUMN_NAME,
    SERIES_TABLE_LAST_UPDATE_DATE_COLUMN_NAME, SERIES_TABLE_PUBMED_ID_COLUMN_NAME, SERIES_TABLE_SUMMARY_COLUMN_NAME,
    SERIES_TABLE_TYPE_COLUMN_NAME, SERIES_TABLE_CONTRIBUTOR_COLUMN_NAME, SERIES_TABLE_CONTACT_COLUMN_NAME,
    SERIES_TABLE_WEB_LINK_COLUMN_NAME, SERIES_TABLE_OVERALL_DESIGN_COLUMN_NAME, SERIES_TABLE_REPEATS_COLUMN_NAME,
    SERIES_TABLE_REPEATS_SAMPLE_LIST_COLUMN_NAME, SERIES_TABLE_VARIABLE_COLUMN_NAME,
    SERIES_TABLE_VARIABLE_DESCRIPTION_COLUMN_NAME, SERIES_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME };

  public final static String[] SAMPLE_TABLE_COLUMN_NAMES = { SAMPLE_TABLE_TITLE_COLUMN_NAME,
    SAMPLE_TABLE_GSM_COLUMN_NAME, SAMPLE_TABLE_SERIES_ID_COLUMN_NAME, SAMPLE_TABLE_GPL_COLUMN_NAME,
    SAMPLE_TABLE_STATUS_COLUMN_NAME, SAMPLE_TABLE_SUBMISSION_DATE_COLUMN_NAME,
    SAMPLE_TABLE_LAST_UPDATE_DATE_COLUMN_NAME, SAMPLE_TABLE_TYPE_COLUMN_NAME, SAMPLE_TABLE_CHANNEL_COUNT_COLUMN_NAME,
    SAMPLE_TABLE_SOURCE_NAME_CH1_COLUMN_NAME, SAMPLE_TABLE_CHARACTERISTIC_CH1_COLUMN_NAME,
    SAMPLE_TABLE_MOLECULE_CH1_COLUMN_NAME, SAMPLE_TABLE_LABEL_CH1_COLUMN_NAME,
    SAMPLE_TABLE_TREATMENT_PROTOCOL_CH1_COLUMN_NAME, SAMPLE_TABLE_EXTRACT_PROTOCOL_CH1_COLUMN_NAME,
    SAMPLE_TABLE_LABEL_PROTOCOL_CH1_COLUMN_NAME, SAMPLE_TABLE_SOURCE_NAME_CH2_COLUMN_NAME,
    SAMPLE_TABLE_CHARACTERISTIC_CH2_COLUMN_NAME, SAMPLE_TABLE_MOLECULE_CH2_COLUMN_NAME,
    SAMPLE_TABLE_LABEL_CH2_COLUMN_NAME, SAMPLE_TABLE_TREATMENT_PROTOCOL_CH2_COLUMN_NAME,
    SAMPLE_TABLE_EXTRACT_PROTOCOL_CH2_COLUMN_NAME, SAMPLE_TABLE_LABEL_PROTOCOL_CH2_COLUMN_NAME,
    SAMPLE_TABLE_HYB_PROTOCOL_COLUMN_NAME, SAMPLE_TABLE_DESCRIPTION_COLUMN_NAME,
    SAMPLE_TABLE_DATA_PROCESSING_COLUMN_NAME, SAMPLE_TABLE_CONTACT_COLUMN_NAME,
    SAMPLE_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME, SAMPLE_TABLE_DATA_ROW_COUNT_COLUMN_NAME };

  public final static String[] PLATFORM_TABLE_COLUMN_NAMES = { PLATFORM_TABLE_TITLE_COLUMN_NAME,
    PLATFORM_TABLE_GPL_COLUMN_NAME, PLATFORM_TABLE_STATUS_COLUMN_NAME, PLATFORM_TABLE_SUBMISSION_DATE_COLUMN_NAME,
    PLATFORM_TABLE_LAST_UPDATE_DATE_COLUMN_NAME, PLATFORM_TABLE_TECHNOLOGY_COLUMN_NAME,
    PLATFORM_TABLE_DISTRIBUTION_COLUMN_NAME, PLATFORM_TABLE_ORGANISM_COLUMN_NAME,
    PLATFORM_TABLE_MANUFACTURER_COLUMN_NAME, PLATFORM_TABLE_MANUFACTURE_PROTOCOL_COLUMN_NAME,
    PLATFORM_TABLE_COATING_COLUMN_NAME, PLATFORM_TABLE_CATALOG_NUMBER_COLUMN_NAME, PLATFORM_TABLE_SUPPORT_COLUMN_NAME,
    PLATFORM_TABLE_DESCRIPTION_COLUMN_NAME, PLATFORM_TABLE_WEB_LINK_COLUMN_NAME, PLATFORM_TABLE_CONTACT_COLUMN_NAME,
    PLATFORM_TABLE_DATA_ROW_COUNT_COLUMN_NAME, PLATFORM_TABLE_SUPPLEMENTARY_FILE_COLUMN_NAME,
    PLATFORM_TABLE_BIOC_PACKAGE_COLUMN_NAME };

  public final static List<String> SeriesTableColumnNames = Arrays.asList(SERIES_TABLE_COLUMN_NAMES);

  public final static List<String> SampleTableColumnNames = Arrays.asList(SAMPLE_TABLE_COLUMN_NAMES);

  public final static List<String> PlatformTableColumnNames = Arrays.asList(PLATFORM_TABLE_COLUMN_NAMES);

  public final static String NAME_CONTACT_ATTRIBUTE = "Name";
  public final static String EMAIL_CONTACT_ATTRIBUTE = "Email";
  public final static String PHONE_CONTACT_ATTRIBUTE = "Phone";
  public final static String FAX_CONTACT_ATTRIBUTE = "Fax";
  public final static String LABORATORY_CONTACT_ATTRIBUTE = "Laboratory";
  public final static String DEPARTMENT_CONTACT_ATTRIBUTE = "Department";
  public final static String INSTITUTE_CONTACT_ATTRIBUTE = "Institute";
  public final static String ADDRESS_CONTACT_ATTRIBUTE = "Address";
  public final static String CITY_CONTACT_ATTRIBUTE = "City";
  public final static String STATE_CONTACT_ATTRIBUTE = "State";
  public final static String ZIP_POSTAL_CODE_CONTACT_ATTRIBUTE = "Zip/postal-code";
  public final static String COUNTRY_CONTACT_ATTRIBUTE = "Country";
  public final static String WEB_LINK_CONTACT_ATTRIBUTE = "Web_link";

  public final static String[] CONTACT_FIELD_NAMES = { NAME_CONTACT_ATTRIBUTE, EMAIL_CONTACT_ATTRIBUTE,
      PHONE_CONTACT_ATTRIBUTE, FAX_CONTACT_ATTRIBUTE, LABORATORY_CONTACT_ATTRIBUTE, DEPARTMENT_CONTACT_ATTRIBUTE,
      INSTITUTE_CONTACT_ATTRIBUTE, ADDRESS_CONTACT_ATTRIBUTE, CITY_CONTACT_ATTRIBUTE, STATE_CONTACT_ATTRIBUTE,
      ZIP_POSTAL_CODE_CONTACT_ATTRIBUTE, COUNTRY_CONTACT_ATTRIBUTE, WEB_LINK_CONTACT_ATTRIBUTE };

  public final static List<String> ContactFieldNames = Arrays.asList(CONTACT_FIELD_NAMES);

  public static final int MAX_SERIES_PER_SLICE = 1000;
}