package org.hiring.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entity.BaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@Table(name = "company")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    String name;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    String industry;

    @Column
    String description;

    @Size(max = 50)
    @Column
    Integer employeeCount;

    @Max(2500)
    @NotNull
    @Column
    Integer foundedYear;

    @Size(max = 200)
    @Column(length = 200)
    String logoUrl;

    @Size(max = 200)
    @Column(length = 200)
    String websiteUrl;

    @NotBlank
    @Size(max = 200)
    @Column(length = 200, nullable = false)
    String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    List<JobJpaEntity> jobs = new ArrayList<>();

    @Builder
    public CompanyJpaEntity(String name, String industry, String description, Integer employeeCount, Integer foundedYear, String logoUrl, String websiteUrl, String address) {
        this.name = name;
        this.industry = industry;
        this.description = description;
        this.employeeCount = employeeCount;
        this.foundedYear = foundedYear;
        this.logoUrl = logoUrl;
        this.websiteUrl = websiteUrl;
        this.address = address;
    }

    public void modifyCompany(String name, String industry, String description, Integer employeeCount, Integer foundedYear, String logoUrl, String websiteUrl, String address) {
        this.name = name;
        this.industry = industry;
        this.description = description;
        this.employeeCount = employeeCount;
        this.foundedYear = foundedYear;
        this.logoUrl = logoUrl;
        this.websiteUrl = websiteUrl;
        this.address = address;
    }
}