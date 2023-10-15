package com.TechnoWebDistributed.tp1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEntity {
    @Id
    // TODO: change id to UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;

    @JsonManagedReference
    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BookEntity> bookEntities;
}
