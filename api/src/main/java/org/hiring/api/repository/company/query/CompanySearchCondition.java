package org.hiring.api.repository.company.query;

public record CompanySearchCondition(
        String name,
        String industry,
        Integer employeeCount,
        String address,
        Integer offset,
        Integer limit
) {
}
