package org.hahunavth.hibernate.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table( name = "events" )
public class Event implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Event() {
    }

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String toString() {
        return "Event (id: " + id + ", title: " + title + ", date: " + date + ")";
    }
}
