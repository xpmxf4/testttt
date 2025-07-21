package org.hiring.api.service.company.usecase;

import org.hiring.api.service.company.RegisterCompanyServiceRequest;

public interface RegisterCompanyUseCase {

    void registerCompany(final RegisterCompanyServiceRequest request);
}
