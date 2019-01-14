package report;

public class StartapReport extends AbstractReport{

    public StartapReport(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    @Override
    public void mainReport() {
        parserInXLSX(ATD.getStartup(rownum));
    }
}
