package org.hiring.api.service.company;

public record RegisterCompanyServiceRequest(
        String name,
        String industry,
        String description,
        Integer employeeCount,
        Integer foundedYear,
        String logoUrl,
        String websiteUrl,
        String address
) {
}
