package report;

import configuration.AppealsToDatabase;
import entity.VacancyGL;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class AbstractReport<T extends VacancyGL> {
    protected AppealsToDatabase ATD;
    protected String nameReport;
    protected Integer rownum;
    protected boolean b;

    public AbstractReport(String nameReport, Integer rownum, boolean b) {
        ATD = new AppealsToDatabase();
        this.nameReport = nameReport;
        this.rownum = rownum;
        this.b = b;
        if (rownum == 0) {
            copyReport();
        }
        mainReport();
    }

    protected void parserInXLSX(List<T> list) {
        File file = new File("C:\\Users\\s.lashko\\IdeaProjects\\MainsReport\\src\\" + nameReport + " " +
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx");
        InputStream inputStream = null;
        OutputStream outputStream;
        XSSFWorkbook workBook = null;

        try {
            inputStream = new FileInputStream(file);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workBook.getSheetAt(0);
        String sDate;
        Row row;
        Cell cell;
        for (T vacancy : list) {
            rownum++;
            row = sheet.createRow(rownum);
            cell = row.createCell(0);
            cell.setCellValue(vacancy.getV_id());
            cell = row.createCell(1);
            cell.setCellValue(vacancy.getName());
            cell = row.createCell(2);
            cell.setCellValue(vacancy.getKey_skills());
            cell = row.createCell(3);
            cell.setCellValue(vacancy.getExperience_id());
            cell = row.createCell(4);
            cell.setCellValue(vacancy.getExperience_name());
            if (vacancy.getSalary_from() != null) {
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(vacancy.getSalary_from());
            }
            if (vacancy.getSalary_to() != null) {
                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(vacancy.getSalary_to());
            }
            if (vacancy.getPublished_at() != null) {
                cell = row.createCell(7);
                sDate = vacancy.getPublished_at(); //2018-10-29T11:19:56+0300
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    cell.setCellValue(format.parse(sDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            cell = row.createCell(8);
            cell.setCellValue(vacancy.getEmployer_name());
            cell = row.createCell(9);
            cell.setCellValue(vacancy.getSpecializations_id());
            cell = row.createCell(10);
            cell.setCellValue(vacancy.getSpecializations_name());
            cell = row.createCell(11);
            cell.setCellValue(vacancy.getSpecializations_profarea_id());
            cell = row.createCell(12);
            cell.setCellValue(vacancy.getSpecializations_profarea_name());
        }

        try {
            outputStream = new FileOutputStream(file);
            workBook.write(outputStream);
            workBook.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (b) {
            saveXLSX();
        }
    }

    private void copyReport() {
        try {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream("C:\\Users\\s.lashko\\IdeaProjects\\MainsReport\\src\\ReportHH.xlsx").getChannel();
                destChannel = new FileOutputStream("C:\\Users\\s.lashko\\IdeaProjects\\MainsReport\\src\\" + nameReport + " " +
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx").getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            } finally {
                sourceChannel.close();
                destChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveXLSX() {
        try {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream("C:\\Users\\s.lashko\\IdeaProjects\\MainsReport\\src\\" + nameReport + " " +
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx").getChannel();
                destChannel = new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nameReport + " " +
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx").getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            } finally {
                sourceChannel.close();
                destChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File("C:\\Users\\s.lashko\\IdeaProjects\\MainsReport\\src\\" + nameReport + " " +
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE) + ".xlsx").delete();
    }

    public abstract void mainReport();
}
