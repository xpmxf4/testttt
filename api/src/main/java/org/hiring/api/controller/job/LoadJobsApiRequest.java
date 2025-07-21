package org.hiring.api.controller.job;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;
import org.hiring.api.service.job.LoadJobsServiceRequest;

public record LoadJobsApiRequest(
        String title,
        String description,
        EmploymentType employmentType,
        ExperienceLevel experienceLevel,
        EducationLevel educationLevel,
        String city,
        String district,

        @NotNull(message = "페이지는 필수입니다")
        @Min(value = 1, message = "페이지는 1 이상이어야 합니다")
        Integer page,

        @NotNull(message = "사이즈는 필수입니다")
        @Min(value = 1, message = "사이즈는 1 이상이어야 합니다")
        Integer size
) {
    public LoadJobsServiceRequest toServiceRequest() {
        return new LoadJobsServiceRequest(
                title,
                description,
                employmentType,
                experienceLevel,
                educationLevel,
                city,
                district,
                page,
                size
        );
    }
}