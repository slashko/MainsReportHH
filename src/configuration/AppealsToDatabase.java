package configuration;

import entity.SelectedJobs;
import entity.Vacancy;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AppealsToDatabase<T> {
    private String str;

    public AppealsToDatabase(){
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "\\AppData\\Local\\date.txt"))) {
            str = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List getVacancy(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vacancy ORDER BY id DESC");
//        query.setParameter("str", str);
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<Vacancy> vacancies = query.list();
        session.close();
        return vacancies;
    }

    public List<SelectedJobs> getVacancySpec(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM SelectedJobs WHERE published_at LIKE :str ORDER BY id DESC");
        query.setParameter("str", str);
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<SelectedJobs> vacancies = query.list();
        session.close();
        return vacancies;
    }

    public List getStartup(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vacancy WHERE specializations_id LIKE :spec AND published_at LIKE :str ORDER BY id DESC");
        query.setParameter("str", str);
        query.setParameter("spec", "1.474");
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<Vacancy> vacancies = query.list();
        session.close();
        return vacancies;
    }

    public List getITSpec(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vacancy WHERE (specializations_id LIKE '1.%' OR specializations_id LIKE '%; 1.%') " +
                "AND published_at LIKE :str ORDER BY id DESC");
        query.setParameter("str", str);
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<SelectedJobs> vacancies = query.list();
        session.close();
        return vacancies;
    }

    public List<Vacancy> getEngineerOne(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vacancy WHERE ((name LIKE '%Инженер%' OR name LIKE '%инженер%' " +
                "OR name LIKE '%Engineer%' OR name LIKE '%engineer%' OR name LIKE 'Инженер%' OR name LIKE 'инженер%' " +
                "OR name LIKE 'Engineer%' OR name LIKE 'engineer%' OR name LIKE '%Инженер' OR name LIKE '%инженер' " +
                "OR name LIKE '%Engineer' OR name LIKE '%engineer') AND NOT (specializations_profarea_id LIKE '1;%' " +
                "OR specializations_profarea_id = '1')) AND published_at LIKE :str ORDER BY id DESC");
        query.setParameter("str", str);
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<Vacancy> vacancies = query.list();
        session.close();
        return vacancies;
    }

    public List getEngineerTwo(Integer number) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Vacancy WHERE (specializations_id LIKE '1.82%' OR" +
                " specializations_id LIKE '%1.82%' OR specializations_id LIKE '10.80%' OR" +
                " specializations_id LIKE '%10.80%' OR specializations_id LIKE '14.87%' OR specializations_id LIKE '%14.87%' OR" +
                " specializations_id LIKE '18.57%' OR specializations_id LIKE '%18.57%' OR specializations_id LIKE '18.81%' OR" +
                " specializations_id LIKE '%18.81%' OR specializations_id LIKE '18.84%' OR specializations_id LIKE '%18.84%' OR" +
                " specializations_id LIKE '18.85%' OR specializations_id LIKE '%18.85%' OR specializations_id LIKE '18.86%' OR" +
                " specializations_id LIKE '%18.86%' OR specializations_id LIKE '20.83%' OR specializations_id LIKE '%20.83%' OR" +
                " specializations_id LIKE '25.381%' OR specializations_id LIKE '%25.381%') AND published_at LIKE :str ORDER BY id DESC");
        query.setParameter("str", str);
        query.setFirstResult(number);
        query.setMaxResults(10000);
        List<SelectedJobs> vacancies = query.list();
        session.close();
        return vacancies;
    }

}
