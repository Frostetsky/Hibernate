package HQL.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "experience")
    String experience;

    @Column(name = "salary")
    Integer salary;

    public Candidate() {
    }

    public Candidate(String name, String experience, Integer salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id)
                && Objects.equals(name, candidate.name)
                && Objects.equals(experience, candidate.experience)
                && Objects.equals(salary, candidate.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + experience + " | " + salary;
    }
}
