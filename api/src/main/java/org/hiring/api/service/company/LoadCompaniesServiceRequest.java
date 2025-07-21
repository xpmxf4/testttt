package org.hiring.api.service.company;

public record LoadCompaniesServiceRequest(
        String name,
        String industry,
        Integer employeeCount,
        String address,
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
