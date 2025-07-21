package org.hiring.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.entity.BaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;

import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@Table(name = "job")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JobJpaEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    CompanyJpaEntity company;

    @Column(nullable = false, length = 255)
    String title;

    @Column
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    EducationLevel educationLevel;

    @Column(nullable = false, length = 20)
    String city;

    @Column(nullable = false, length = 20)
    String district;

    @Column(nullable = false)
    LocalDateTime postedAt;

    @Column(nullable = false)
    LocalDateTime deadline;

    @Column(length = 1000)
    String requirements;

    @Column(length = 1000)
    String benefits;

    @Builder
    public JobJpaEntity(CompanyJpaEntity company, String title, String description, EmploymentType employmentType, ExperienceLevel experienceLevel, EducationLevel educationLevel, String city, String district, LocalDateTime postedAt, LocalDateTime deadline, String requirements, String benefits) {
        this.company = company;
        this.title = title;
        this.description = description;
        this.employmentType = employmentType;
        this.experienceLevel = experienceLevel;
        this.educationLevel = educationLevel;
        this.city = city;
        this.district = district;
        this.postedAt = postedAt;
        this.deadline = deadline;
        this.requirements = requirements;
        this.benefits = benefits;
    }

    public void updateInfo(CompanyJpaEntity company, String title, String description, String city, String district, EmploymentType employmentType, ExperienceLevel experienceLevel, EducationLevel educationLevel, LocalDateTime postedAt, LocalDateTime deadline, String requirements, String benefits) {
        this.company = company;
        this.title = title;
        this.description = description;
        this.city = city;
        this.district = district;
        this.employmentType = employmentType;
        this.experienceLevel = experienceLevel;
        this.educationLevel = educationLevel;
        this.postedAt = postedAt;
        this.deadline = deadline;
        this.requirements = requirements;
        this.benefits = benefits;
    }

}