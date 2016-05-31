package org.metadatacenter.readers.geo.formats.geosoft;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.metadatacenter.readers.geo.GEOReaderException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class SpreadsheetUtil
{
  public static final String XLS_EXTENSION = ".xls";
  public static final String XLSX_EXTENSION = ".xlsx";

  private static final short HIGHLIGHT_CELL_COLOR = IndexedColors.YELLOW.getIndex();

  public static InputStream openSpreadsheetInputStream(String fileName) throws GEOReaderException
  {
    checkForXLSOrXLSXExtension(fileName);

    try {
      return new FileInputStream(fileName);
    } catch (IOException e) {
      throw new GEOReaderException("Error opening spreadsheet " + fileName + ": " + e.getMessage(), e);
    }
  }

  public static void checkForXLSOrXLSXExtension(String fileName) throws GEOReaderException
  {
    checkForExtension(fileName, XLS_EXTENSION, XLSX_EXTENSION);
  }

  public static void checkForExtension(String fileName, String... allowedExtensions) throws GEOReaderException
  {
    String upperCase = fileName.toUpperCase(Locale.US);
    for (String extension : allowedExtensions) {
      if (upperCase.endsWith(extension.toUpperCase(Locale.US)))
        return;
    }

    StringBuilder prettyList = new StringBuilder();
    for (String extension : allowedExtensions) {
      prettyList.append(extension);
      prettyList.append(", ");
    }
    if (prettyList.length() > 0)
      prettyList.setLength(prettyList.length() - 2); // trim trailing ", "

    throw new GEOReaderException(
      String.format("Invalid file extension for '%s'; expected one of: %s", fileName, prettyList));
  }

  public static OutputStream openSpreadsheetOutputStream(String fileName) throws GEOReaderException
  {
    checkForXLSOrXLSXExtension(fileName);

    try {
      return new FileOutputStream(fileName);
    } catch (IOException e) {
      throw new GEOReaderException("Error opening spreadsheet " + fileName + ": " + e.getMessage(), e);
    }
  }

  public static Workbook createWorkbook(InputStream inputStream) throws GEOReaderException
  {
    try {
      return WorkbookFactory.create(inputStream);
    } catch (InvalidFormatException e) {
      throw new GEOReaderException("Invalid format for workbook " + e.getMessage(), e);
    } catch (IOException e) {
      throw new GEOReaderException("IO error opening workbook " + e.getMessage(), e);
    }
  }

  public static Workbook createReadonlyWorkbook(InputStream inputStream) throws GEOReaderException
  {
    try {
      return WorkbookFactory.create(inputStream);
    } catch (InvalidFormatException e) {
      throw new GEOReaderException("Invalid format for workbook " + e.getMessage(), e);
    } catch (IOException e) {
      throw new GEOReaderException("IO error opening workbook " + e.getMessage(), e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }

  public static void writeWorkbook(Workbook workbook, String fileName) throws GEOReaderException
  {
    OutputStream os = openSpreadsheetOutputStream(fileName);

    try {
      workbook.write(os);
    } catch (IOException e) {
      throw new GEOReaderException("Error writing workbook to file " + fileName + ": " + e.getMessage(), e);
    } finally {
      IOUtils.closeQuietly(os);
    }
  }

  public static void highlightCell(Workbook workbook, String sheetName, int columnIndex, int rowNumber)
    throws GEOReaderException
  {
    Sheet sheet = workbook.getSheet(sheetName);

    if (sheet == null)
      throw new GEOReaderException("Internal error: no sheet " + sheetName + " in workbook");

    Row row = sheet.getRow(rowNumber);

    if (row == null)
      throw new GEOReaderException("Internal error: no row " + rowNumber + " in sheet " + sheet.getSheetName());

    Cell cell = row.getCell(columnIndex);

    if (cell == null)
      throw new GEOReaderException(
        "Internal error: no column " + columnIndex + " at row " + rowNumber + " in sheet " + sheet.getSheetName());

    // setFillBackgroundColor will not take unless setFillForegroundColor is called first!!!
    cell.getCellStyle().setFillForegroundColor(HIGHLIGHT_CELL_COLOR);
    cell.getCellStyle().setFillBackgroundColor(HIGHLIGHT_CELL_COLOR);
  }

  public static String convertIndex2ColString(int columnIndex)
  {
    return CellReference.convertNumToColString(columnIndex);
  }

  public static String convertLocation2String(String sheetName, int columnIndex, int rowIndex)
  {

    return "'" + sheetName + "'!" + convertIndex2ColString(columnIndex) + (rowIndex + 1);
  }

  public static String getCellLocation(Cell cell)
  {
    int columnIndex = cell.getColumnIndex();
    int rowIndex = cell.getRowIndex();

    return convertLocation2String(columnIndex, rowIndex);
  }

  public static String convertLocation2String(int columnIndex, int rowIndex)
  {
    return convertIndex2ColString(columnIndex) + (rowIndex + 1);
  }

  public static Sheet getSheet(Workbook workbook, String sheetName) throws GEOReaderException
  {
    Sheet sheet = workbook.getSheet(sheetName);

    if (sheet == null)
      throw new GEOReaderException("Invalid sheet " + sheetName);

    return sheet;
  }

  public static boolean getCellValueAsBoolean(Cell cell, String trueValue) throws GEOReaderException
  {
    if (cell == null)
      return false;
    else if (isStringCellType(cell)) {
      String value = cell.getStringCellValue().trim();
      return value.equals(trueValue);
    } else if (isBooleanCellType(cell))
      return cell.getBooleanCellValue();
    else
      return false;
  }

  public static String getStringCellValue(Cell cell, String defaultValue) throws GEOReaderException
  {
    if (cell == null)
      return defaultValue;
    else if (isStringCellType(cell))
      return cell.getStringCellValue();
    else
      return defaultValue;
  }

  public static String getStringCellValue(Cell cell) throws GEOReaderException
  {
    assertNonNullCell(cell);
    assertStringCellType(cell);

    return cell.getStringCellValue();
  }

  public static String getCellValueAsString(Cell cell) throws GEOReaderException
  {
    assertNonNullCell(cell);
    assertNonBlankCell(cell);
    assertNonErrorCell(cell);

    if (isStringCellType(cell))
      return cell.getStringCellValue();
    else if (isBooleanCellType(cell))
      return String.valueOf(cell.getBooleanCellValue());
    else if (isNumericCellType(cell))
      return String.valueOf(cell.getNumericCellValue());
    else throw new GEOReaderException("Unknown cell type at location " + getCellLocation(cell));
  }

  public static void assertNonNullCell(Cell cell) throws GEOReaderException
  {
    if (cell == null)
      throw new GEOReaderException("Empty cell at location " + getCellLocation(cell));
  }

  public static void assertNonBlankCell(Cell cell) throws GEOReaderException
  {
    if (cell != null && isBlankCellType(cell))
      throw new GEOReaderException("Blank cell at location " + getCellLocation(cell));
  }

  public static void assertNonErrorCell(Cell cell) throws GEOReaderException
  {
    if (cell != null && isErrorCellType(cell))
      throw new GEOReaderException("Error cell at location " + getCellLocation(cell));
  }

  public static void assertStringCellType(Cell cell) throws GEOReaderException
  {
    if (!isStringCellType(cell))
      throw new GEOReaderException(
        "Cell at location " + getCellLocation(cell) + " is not a string; type is " + getCellTypeName(cell));
  }

  public static double getNumericCellValue(Cell cell) throws GEOReaderException
  {
    assertNumericCellType(cell);

    return cell.getNumericCellValue();
  }

  public static void assertNumericCellType(Cell cell) throws GEOReaderException
  {
    if (!isNumericCellType(cell))
      throw new GEOReaderException(
        "Cell at location " + getCellLocation(cell) + " is not a numeric; type is " + getCellTypeName(cell));
  }

  public static boolean isNumericCellType(Cell cell)
  {
    return cell.getCellType() == Cell.CELL_TYPE_NUMERIC;
  }

  public static boolean isBooleanCellType(Cell cell)
  {
    return cell.getCellType() == Cell.CELL_TYPE_BOOLEAN;
  }

  public static boolean isStringCellType(Cell cell)
  {
    return cell.getCellType() == Cell.CELL_TYPE_STRING;
  }

  public static boolean isBlankCellType(Cell cell)
  {
    return cell.getCellType() == Cell.CELL_TYPE_BLANK;
  }

  public static boolean isErrorCellType(Cell cell)
  {
    return cell.getCellType() == Cell.CELL_TYPE_ERROR;
  }

  public static String getCellTypeName(Cell cell)
  {
    if (cell == null)
      return "Cell is NULL";
    else {
      int celltype = cell.getCellType();

      if (celltype == Cell.CELL_TYPE_BLANK)
        return "CELL_TYPE_BLANK";
      else if (celltype == Cell.CELL_TYPE_FORMULA)
        return "CELL_TYPE_BLANK";
      else if (celltype == Cell.CELL_TYPE_STRING)
        return "CELL_TYPE_STRING";
      else if (celltype == Cell.CELL_TYPE_NUMERIC)
        return "CELL_TYPE_NUMERIC";
      else if (celltype == Cell.CELL_TYPE_BOOLEAN)
        return "CELL_TYPE_BOOLEAN";
      else if (celltype == Cell.CELL_TYPE_ERROR)
        return "CELL_TYPE_ERROR";
      else
        return "UNKNOWN CELL TYPE!";
    }
  }
}
