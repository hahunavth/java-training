package org.hahunavth.hibernate.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "review")
public class Review implements java.io.Serializable {
    public Review () {}

    public Review(Integer accId, String content, Integer rating) {
        this.account = new Account();
        this.account.setId(accId);
        this.content = content;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "account_id")
//    public Integer accountId;
//    @Column(name = "tour_id")
//    public Integer tourId;
    public String content;
    public Integer rating;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = true, updatable = true)
    private Account account;
}
