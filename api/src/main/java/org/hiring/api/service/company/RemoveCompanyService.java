package org.hiring.api.service.company;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hiring.api.repository.company.CompanyRepository;
import org.hiring.api.service.company.usecase.RemoveCompanyUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveCompanyService implements RemoveCompanyUseCase {

    private final CompanyRepository companyRepository;

    @Override
    public void removeCompany(final Long companyId) {
        final var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id : " + companyId));

        companyRepository.delete(company);
    }
}
