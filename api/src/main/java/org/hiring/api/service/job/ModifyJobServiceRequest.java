package org.hiring.api.service.job;

import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;

import java.time.LocalDateTime;

public record ModifyJobServiceRequest(
        Long id,
        Long companyId,
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
        String benefits
) {
}
