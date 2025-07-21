package org.hiring.api.domain;

import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;

import java.time.LocalDateTime;

public record Job(
    Long id,
    Company company,
    String title,
    String description,
    EmploymentType employmentType,
    ExperienceLevel experienceLevel,
    EducationLevel educationLevel,
    String city,
    String district,
    LocalDateTime postedAt,
    LocalDateTime deadline,
    String requirements,
    String benefits,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}