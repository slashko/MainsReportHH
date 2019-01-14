package report;

import entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class EngineerOne extends AbstractReport {
    public EngineerOne(String nameReport, Integer rownum, boolean b) {
        super(nameReport, rownum, b);
    }

    @Override
    public void mainReport() {
        List<Vacancy> list = analize(ATD.getEngineerOne(rownum));
        parserInXLSX(list);
    }

    private List<Vacancy> analize(List list) {
        List<Vacancy> vacancies = new ArrayList<>(list);
        for (Vacancy vacancy : (List<Vacancy>) list) {
            if (vacancy.getName().contains("DevOps инженер") || vacancy.getName().contains("Dev Ops")
                    || vacancy.getName().contains("Presale") || vacancy.getName().contains("Pre-sale")
                    || vacancy.getName().contains("Linux") || vacancy.getName().contains("ML")
                    || vacancy.getName().contains("Data") || vacancy.getName().contains("Machine Learning")
                    || vacancy.getName().contains("NLP") || vacancy.getName().contains("QA Engineer")
                    || vacancy.getName().contains("Junior React Frontend") || vacancy.getName().contains("Junior Value")
                    || vacancy.getName().contains("Design") || vacancy.getName().contains("Администратор")
                    || vacancy.getName().contains("бухгалтер")) {
                vacancies.remove(vacancy);
            }
        }
        return vacancies;
    }
}
