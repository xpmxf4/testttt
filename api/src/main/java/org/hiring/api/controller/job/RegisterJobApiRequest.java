package org.hiring.api.controller.job;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;
import org.hiring.api.service.job.RegisterJobServiceRequest;

import java.time.LocalDateTime;

public record RegisterJobApiRequest(
        @NotNull(message = "회사 ID는 필수입니다")
        Long companyId,

        @NotBlank(message = "제목은 필수입니다")
        String title,

        String description,

        @NotNull(message = "고용 형태는 필수입니다")
        EmploymentType employmentType,

        @NotNull(message = "경력 수준은 필수입니다")
        ExperienceLevel experienceLevel,

        @NotNull(message = "학력은 필수입니다")
        EducationLevel educationLevel,

        @NotBlank(message = "도시는 필수입니다")
        String city,

        @NotBlank(message = "구/군은 필수입니다")
        String district,

        @NotNull(message = "게시일은 필수입니다")
        LocalDateTime postedAt,

        @NotNull(message = "마감일은 필수입니다")
        LocalDateTime deadline,

        String requirements,
        String benefits
) {
    public RegisterJobServiceRequest toServiceRequest() {
        return new RegisterJobServiceRequest(
                companyId,
                title,
                description,
                employmentType,
                experienceLevel,
                educationLevel,
                city,
                district,
                postedAt,
                deadline,
                requirements,
                benefits
        );
    }
}