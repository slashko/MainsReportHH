package report;

public class Report extends AbstractReport {
    public Report(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    public void mainReport() {
        parserInXLSX(ATD.getVacancySpec(rownum));
    }
}