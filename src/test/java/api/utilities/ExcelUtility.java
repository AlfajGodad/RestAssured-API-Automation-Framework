package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtility(String filePath, String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(filePath);

        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellData(int row, int column) {

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(
                sheet.getRow(row).getCell(column));
    }

    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}