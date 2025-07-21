package org.hiring.api.controller.company;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hiring.api.service.company.LoadCompaniesServiceRequest;

public record LoadCompaniesApiRequest(
        String name,
        String industry,
        Integer employeeCount,
        String address,

        @NotNull(message = "페이지는 필수입니다")
        @Min(value = 1, message = "페이지는 1 이상이어야 합니다")
        Integer page,

        @NotNull(message = "사이즈는 필수입니다")
        @Min(value = 1, message = "사이즈는 1 이상이어야 합니다")
        Integer size
) {
    public LoadCompaniesServiceRequest toServiceRequest() {
        return new LoadCompaniesServiceRequest(
                name,
                industry,
                employeeCount,
                address,
                page,
                size
        );
    }
}