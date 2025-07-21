package org.hiring.api.repository.job.query;

import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;

public record JobSearchCondition(
        String title,
        String description,
        EmploymentType employmentType,
        ExperienceLevel experienceLevel,
        EducationLevel educationLevel,
        String city,
        String district,
        Integer offset,
        Integer limit
) {
}
