package org.hiring.api.service.job;

import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;

public record LoadJobsServiceRequest(
        String title,
        String description,
        EmploymentType employmentType,
        ExperienceLevel experienceLevel,
        EducationLevel educationLevel,
        String city,
        String district,
        Integer page,
        Integer size
) {

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}
