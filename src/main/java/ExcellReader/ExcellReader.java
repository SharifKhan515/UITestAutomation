package ExcellReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcellReader {


    public static ArrayList<String> getData(String path, String sheetName, String testCase) throws IOException {

        ArrayList<String> data = new ArrayList<String>();

        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int workbookCount = workbook.getNumberOfSheets();
        XSSFSheet sheet = null;

        for (int i = 0; i < workbookCount; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {

                sheet = workbook.getSheetAt(i);
                break;
            }
        }

        assert sheet != null;
        Iterator<Row> rows = sheet.iterator();
        Row firstRow = rows.next();
        Iterator<Cell> ce = firstRow.cellIterator();
        int testColumn = 0;
        while (ce.hasNext()) {
            Cell value = ce.next();

            if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                break;
            }
            testColumn++;
        }

        System.out.println(testColumn);

        while (rows.hasNext()) {

            Row r = rows.next();
            if (r.getCell(testColumn).getStringCellValue().trim().equalsIgnoreCase(testCase)) {

                Iterator<Cell> cb = r.cellIterator();
                while (cb.hasNext()) {
                    //System.out.println();
                    Cell current = cb.next();
                    if (current.getCellTypeEnum() == CellType.STRING) {
                        data.add(current.getStringCellValue());
                    } else {
                        double numeric = current.getNumericCellValue();
                        data.add(NumberToTextConverter.toText(numeric));
                    }

                }
            }

        }
        return data;
    }

    @DataProvider(name="TestdataProvider")
    public static Object[][] copyimdata_tc(Method m) throws Exception
    {
        return null;
    }


}
