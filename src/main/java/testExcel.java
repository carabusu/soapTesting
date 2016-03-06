import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by Mihai on 3/2/2016.
 */
public class testExcel {
    public static XSSFWorkbook workbook;


    public  static void main(String args[]) throws Exception {

        MyLogger myLog=MyLogger.getInstance();
        System.out.println("logg: " + myLog.getFolderLog()[0] + "--" + myLog.getFolderLog()[1]);

        excelUtil myExcelData=new excelUtil("F:\\github\\soapTesting\\src\\main\\resources\\testData.xlsx");
        ArrayList<HashMap<String,String>> searchCriteria=myExcelData.getSearchCriteriaData();

        ArrayList<HashMap> reports=new ArrayList<HashMap>();

        HashMap entryRep=new HashMap();
        entryRep.put("Agr","test");

        reports.add(entryRep);


        entryRep=new HashMap();
        entryRep.put("Agr","test11");
       // reports.add(entryRep);


        myExcelData.clearReportSheet();
        myExcelData.writeLineReport(reports);
        myExcelData.closeFiles();
        System.out.println("");






    }

}
