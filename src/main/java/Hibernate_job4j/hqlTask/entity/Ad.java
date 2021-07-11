package Hibernate_job4j.hqlTask.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private LocalDate date;

    public Ad(String name, String brand, String url, LocalDate date) {
        this.name = name;
        this.brand = brand;
        this.url = url;
        this.date = date;
    }

    public Ad(String name, String brand, LocalDate date) {
        this.name = name;
        this.brand = brand;
        this.date = date;
    }

    public Ad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getTimestamp() {
        return date;
    }

    public void setTimestamp(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + date +
                '}';
    }
}
