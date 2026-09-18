package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "UserData")
    public Object[][] getUserData() throws IOException {

        String path =
                System.getProperty("user.dir")
                + "/src/test/resources/UserData.xlsx";

        ExcelUtility excel =
                new ExcelUtility(path, "UserData");

        int rows = excel.getRowCount();
        int columns = excel.getColumnCount();

        Object[][] data = new Object[rows][columns];

        for (int i = 1; i <= rows; i++) {

            for (int j = 0; j < columns; j++) {

                data[i - 1][j] =
                        excel.getCellData(i, j);
            }
        }

        excel.closeWorkbook();

        return data;
    }
}