import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mihai on 3/3/2016.
 */



public class MyLogger {
    private static MyLogger instance = null;
    private String folderLogCurrent;
    private String folderLogDateStamp;

    protected MyLogger() {
        File currDir = new File("");
        String projectPath = currDir.getAbsolutePath();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date now = new Date();
        String dateStamp = sdf.format(now);


        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        //System.out.println(path + "---" +dateStamp);
        folderLogCurrent = projectPath + "\\reports\\_lastExecution";// + dateStamp;
        folderLogDateStamp = projectPath + "\\reports\\report_" + dateStamp;
        createFolder(folderLogCurrent);
        createFolder(folderLogDateStamp);


    }

    public static MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    public String[] getFolderLog() {
        return new String[]{folderLogCurrent, folderLogDateStamp};
    }

    private void createFolder(String folderPath) {
        File file = new File(folderPath);
        boolean isDirectoryCreated = file.mkdir();
        if (isDirectoryCreated) {
            System.out.println("successfully made");

        } else {
            deleteDir(file);  // Invoke recursive method
            file.mkdir();
        }


    }

    public void deleteDir(File dir) {
        File[] files = dir.listFiles();

        for (File myFile : files) {
            if (myFile.isDirectory()) {
                deleteDir(myFile);
            }
            myFile.delete();

        }
    }


}
