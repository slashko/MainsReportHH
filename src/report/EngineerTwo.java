package report;

public class EngineerTwo extends AbstractReport {
    public EngineerTwo(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    @Override
    public void mainReport() {
        parserInXLSX(ATD.getEngineerTwo(rownum));
    }
}
