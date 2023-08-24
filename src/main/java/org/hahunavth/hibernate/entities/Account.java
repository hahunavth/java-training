package org.hahunavth.hibernate.entities;

import lombok.Data;
import org.hahunavth.hibernate.entities.account.EmailAuthType;
import org.hahunavth.hibernate.entities.account.Gender;
import org.hahunavth.hibernate.entities.account.Role;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
 * @Author: Hahunavth
 * @Date: 2023/8/18
 * @Description: Database table: accounts
 * <ul>
 *     <li>id: INT(11) NOT NULL AUTO_INCREMENT</li>
 *     <li>email: VARCHAR(320) NOT NULL</li>
 *     <li>phone_number: VARCHAR(12) DEFAULT ''</li>
 *     <li>full_name: VARCHAR(64) DEFAULT ''</li>
 *     <li>gender: ENUM(
 * </ul>
 */
@Data
@Entity
@Table( name = "accounts" )
public class Account implements java.io.Serializable {
    /**
     * In Hibernate, all persistent classes must have a default constructor
     * (which can be non-public)
     * It is recommended that this constructor be defined with at least package visibility in order for runtime proxy generation to work properly.
     */
    public Account() {}

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
        this.emailAuthType = EmailAuthType.NORMAL;
    }

    public Account(String email, EmailAuthType emailAuthType) {
        if (emailAuthType == EmailAuthType.NORMAL) throw new IllegalArgumentException("EmailAuthType must not be NORMAL");

        this.email = email;
        this.emailAuthType = emailAuthType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            length = 320,
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "phone_number",
            length = 12,
            unique = true
    )
    @ColumnDefault("''")
    private String phoneNumber;

    @Column(
            name = "full_name",
            length = 64
    )
    @ColumnDefault("''")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'OTHER'")
    private Gender gender;

    @NotNull
    @Column(
            name = "avatar_url",
            length = 256
    )
    @ColumnDefault("''")
    private String avatarUrl;

    @NotNull
    @Column(
            name = "address",
            length = 256
    )
    @ColumnDefault("''")
    private String address;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role;

    @NotNull
    @Column(name = "email_auth_type")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NORMAL'")
    private EmailAuthType emailAuthType;

    @NotNull
    @Column(name = "email_auth_noti")
    @ColumnDefault("true")
    private Boolean emailAuthNotification;

    @Column(name = "password", length = 64)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "account")
    private List<Review> reviews;
}
