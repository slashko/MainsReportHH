package report;

public class ITReport extends AbstractReport{
    public ITReport(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    @Override
    public void mainReport() {
        parserInXLSX(ATD.getITSpec(rownum));
    }
}
