package entity;

import javax.persistence.*;

@Entity
public class SelectedJobs extends VacancyGL {

    public SelectedJobs() {
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "\nkey_skills='" + key_skills + '\'' +
                "\n experience_name='" + experience_name + '\'' +
                "\n employer_name='" + employer_name + '\'' +
                "\n specializations_name='" + specializations_name + '\'' +
                "\n specializations_profarea_name='" + specializations_profarea_name + '\'' +
                '}';
    }
}
