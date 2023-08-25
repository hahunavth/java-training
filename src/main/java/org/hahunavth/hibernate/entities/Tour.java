package org.hahunavth.hibernate.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@Table( name = "tours")
public class Tour implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tour_name")
    private String tourName;

    private String duration;

    @ElementCollection
    @CollectionTable(name="schedule", joinColumns=@JoinColumn(name="tour_id"))
    private List<String> schedule;

//    @Type(type = "json")
//    @TypeDef(name = "json", typeClass = JsonType.class)
//    @Column(name = "additional_info", columnDefinition = "json")
//    @Convert(converter = HashMapConverter.class)
//    private Map<String, Object> additionalInfo;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    // Association Mapping
    @ManyToMany(
            mappedBy = "tours"
    )
//    @ElementCollection
//    @CollectionTable(name="Categories", joinColumns=@JoinColumn(name="category_id"))
//    @AttributeOverrides({
//            @AttributeOverride(name="street1", column=@Column(name="fld_street"))
//    })
    private Set<Category> categories;

    public Tour() {}

    public Tour(String tourName, String duration, List<String> schedule, Map<String, String> additionalInfo) {
        this.tourName = tourName;
        this.duration = duration;
        this.schedule = schedule;
//        this.additionalInfo = additionalInfo;
    }
}
