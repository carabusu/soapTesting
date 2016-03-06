import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Mihai on 3/2/2016.
 */
public class excelUtil {
    private XSSFWorkbook workbook;
    private XSSFSheet testData, report ;

    private int AGRCol,RunCol;
    private int RXMCol;

    private int ReportAGRCol;
    private FileInputStream file;
    FileOutputStream fileOut;
    private int reportLineIndex=1;

    public excelUtil(String excelFileName){

        try {
            file = new FileInputStream(new File(excelFileName));
            fileOut = new FileOutputStream("F:\\github\\soapTesting\\src\\main\\resources\\testDataReport.xlsx");
            this.workbook = new XSSFWorkbook(file);
            testData = workbook.getSheet("Sheet1");
            report = workbook.getSheet("Report");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initSearchCriteriaHeader(){
        AGRCol= getColIndex(testData, "Agr");
        RXMCol= getColIndex(testData, "RXM");
        RunCol= getColIndex(testData, "Run");
        ReportAGRCol= getColIndex(report, "Report_agr");

    }

    public ArrayList<HashMap<String,String>> getSearchCriteriaData(){
        ArrayList<HashMap<String,String>> searchCriteria=new ArrayList<HashMap<String, String>>();
        initSearchCriteriaHeader();
        for (Row row : testData) {
            if ( String.valueOf(row.getCell(RunCol)).equalsIgnoreCase("Y")) {
                HashMap<String, String> entryCriteria = new HashMap<String, String>();
                String cellValue = String.valueOf(row.getCell(AGRCol));
                System.out.println("=====" + cellValue);
                entryCriteria.put("Agr", String.valueOf(row.getCell(AGRCol)));
                entryCriteria.put("RXM", String.valueOf(row.getCell(RXMCol)));

                searchCriteria.add(entryCriteria);
            }



        }

        return searchCriteria;

    }

    public int getColIndex(XSSFSheet sheet, String cellContent) {
        DataFormatter formatter = new DataFormatter(Locale.US);
        String cellValue=null;
        for (Row row : sheet) {
            for (Cell cell : row) {
                //if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        cellValue=cell.getRichStringCellValue().getString();
                        //System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellValue=String.valueOf(cell.getDateCellValue());
                            ///System.out.println(cell.getDateCellValue());
                        } else {
                            cellValue=String.valueOf(cell.getNumericCellValue());
                            //System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        break;
                }

                if (cellValue.equals(cellContent)) {
                    return cell.getColumnIndex();
                }


            }
        }
        return 0;
    }

    public void clearReportSheet(){

        for (int i = 1; i<=report.getPhysicalNumberOfRows(); i++) {
            Row r = report.getRow(i);
            int noOfColumns = report.getRow(0).getLastCellNum();
            if (r != null) {
                for (int j=0;j<=noOfColumns;j++){
                    Cell cell = r.getCell(j);
                    if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                        cell.setCellType(Cell.CELL_TYPE_BLANK);
                    }
                }

            }

            }


        try {

            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeLineReport(ArrayList<HashMap> detailedReport){
        try {
            fileOut = new FileOutputStream("F:\\github\\soapTesting\\src\\main\\resources\\testDataReport.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i< detailedReport.size();i++) {

            Iterator it = detailedReport.get(i).entrySet().iterator();
            Cell cell = null;
            while (it.hasNext()) {
                it.next();
                System.out.println(">>>" + detailedReport.get(i).get("Agr").toString());
                cell = report.getRow(reportLineIndex).getCell(ReportAGRCol);
                cell.setCellValue( detailedReport.get(i).get("Agr").toString());

                //report.cell

            }

            reportLineIndex++;
        }



        try {
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeFiles(){

        try {
            fileOut.flush();
            file.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
