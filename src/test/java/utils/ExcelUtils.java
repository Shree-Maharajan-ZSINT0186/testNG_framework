package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {

    // Method to get data from Excel file and return as List of Maps (each row as a map of column headers to values)
    public static List<Map<String, String>> getExcelData(String filePath) throws IOException, InvalidFormatException {
        List<Map<String, String>> dataList = new ArrayList<>();

        // Open the Excel file
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fis);

        // Assuming the data is in the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get headers (first row)
        Row headerRow = sheet.getRow(0);
        Map<Integer, String> columnHeaders = new HashMap<>();
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            columnHeaders.put(i, headerRow.getCell(i).getStringCellValue());
        }

        // Iterate over remaining rows (skip header row)
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();

            // Iterate over columns and map each header to its value in the current row
            for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                String header = columnHeaders.get(j);
                Cell cell = row.getCell(j);

                String cellValue = "";
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            cellValue = "";
                    }
                }

                rowData.put(header, cellValue);
            }
            dataList.add(rowData);
        }

        workbook.close();
        fis.close();

        return dataList;
    }
}
