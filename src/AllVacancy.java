import report.AbstractReport;

public class AllVacancy extends AbstractReport {
    public AllVacancy(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    @Override
    public void mainReport() {
        parserInXLSX(ATD.getVacancy(rownum));
    }
}
