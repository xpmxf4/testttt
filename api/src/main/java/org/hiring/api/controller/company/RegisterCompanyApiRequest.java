package org.hiring.api.controller.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hiring.api.service.company.RegisterCompanyServiceRequest;

public record RegisterCompanyApiRequest(
        @NotBlank(message = "회사명은 필수입니다")
        String name,

        @NotBlank(message = "업종은 필수입니다")
        String industry,

        String description,

        @NotNull(message = "직원 수는 필수입니다")
        @Positive(message = "직원 수는 양수여야 합니다")
        Integer employeeCount,

        @NotNull(message = "설립연도는 필수입니다")
        @Positive(message = "설립연도는 양수여야 합니다")
        Integer foundedYear,

        String logoUrl,
        String websiteUrl,

        @NotBlank(message = "주소는 필수입니다")
        String address
) {
    public RegisterCompanyServiceRequest toServiceRequest() {
        return new RegisterCompanyServiceRequest(
                name,
                industry,
                description,
                employeeCount,
                foundedYear,
                logoUrl,
                websiteUrl,
                address
        );
    }
}