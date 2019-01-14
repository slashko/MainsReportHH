import configuration.HibernateSessionFactory;
import report.*;

import java.io.File;
import java.io.IOException;

public class MainReport {
    public static void main(String[] args) {
        Long l = System.currentTimeMillis();
//        String[] args = {"0", "1", "true"};
        switch (args[1]) {
            case "1":
                new Report("Отчет по специализациям", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
            case "2":
                new StartapReport("Отчет по стартапам", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
            case "3":
                new ITReport("Отчет по IT - специалистам", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
            case "4":
                new EngineerOne("Отчет по инженерам 1", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
            case "5":
                new EngineerTwo("Отчет по инженерам 2", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
            case "6":
                new AllVacancy("Все вакансии", (Integer.parseInt(args[0]) * 10000), Boolean.parseBoolean(args[2]));
                break;
        }
        HibernateSessionFactory.shutdown();
        System.out.println(System.currentTimeMillis() - l);
        createFile();
        System.exit(0);
    }

    private static void createFile() {
        try {
            new File(System.getProperty("user.home") + "\\AppData\\Local\\check.txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
