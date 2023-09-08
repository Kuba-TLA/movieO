package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtils {
    private FileInputStream fis;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String filePath, String sheetName) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public void close() throws IOException {
        if (fis != null) {
            fis.close();
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            return ""; // Handle null rows
        }
        Cell cell = row.getCell(colNum);
        if (cell == null) {
            return ""; // Handle null cells
        }
        // Handle different cell types (strings, numbers, dates, etc.)
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return cell.toString();
        }
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount() {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        return headerRow.getPhysicalNumberOfCells();
    }

    public void setCellData(int rowNum, int colNum, String value) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.createCell(colNum);
        cell.setCellValue(value);
    }

    public void saveAndClose(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        close();
    }

    public void printSheetContents() {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.print(cell + " - " + cell.getCellType() + " | ");
            }
            System.out.print("\n");
        }
    }

    public void changeCellValue(String searchColumnName, String searchValue, String updateColumnName, String newValue) {
        int searchColumnIndex = getColumnIndex(searchColumnName);
        int updateColumnIndex = getColumnIndex(updateColumnName);

        if (searchColumnIndex == -1 || updateColumnIndex == -1) {
            System.out.println("Column not found.");
            return;
        }

        for (Row row : sheet) {
            Cell searchCell = row.getCell(searchColumnIndex);
            if (searchCell != null && searchCell.getCellType() == CellType.STRING && searchCell.getStringCellValue().equals(searchValue)) {
                Cell updateCell = row.getCell(updateColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                updateCell.setCellValue(newValue);
                break; // Assuming there's only one matching row
            }
        }
    }

    private int getColumnIndex(String columnName) {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(columnName)) {
                return cell.getColumnIndex();
            }
        }
        return -1; // Column not found
    }

    // Select Value in a Dropdown (Cell):
    public void selectDropdownCellValue(String searchColumnName, String searchValue, String selectColumnName, String optionToSelect) {
        int searchColumnIndex = getColumnIndex(searchColumnName);
        int selectColumnIndex = getColumnIndex(selectColumnName);

        if (searchColumnIndex == -1 || selectColumnIndex == -1) {
            System.out.println("Column not found.");
            return;
        }

        for (Row row : sheet) {
            Cell searchCell = row.getCell(searchColumnIndex);
            if (searchCell != null && searchCell.getCellType() == CellType.STRING && searchCell.getStringCellValue().equals(searchValue)) {
                Cell selectCell = row.getCell(selectColumnIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                selectCell.setCellValue(optionToSelect);
                break; // Assuming there's only one matching row
            }
        }
    }

    //Wait for a Condition:
    public void waitForCondition(int rowNum, int colNum, String expectedValue, long timeoutInSeconds) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeoutInSeconds * 1000) {
            String cellValue = getCellData(rowNum, colNum);
            if (cellValue.equals(expectedValue)) {
                return; // Condition met
            }
        }
        System.out.println("Condition not met within the specified timeout.");
    }
}
