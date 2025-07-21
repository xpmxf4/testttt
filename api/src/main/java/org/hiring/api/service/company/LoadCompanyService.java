package org.hiring.api.service.company;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hiring.api.common.response.PagedResult;
import org.hiring.api.domain.Company;
import org.hiring.api.entity.CompanyJpaEntity;
import org.hiring.api.mapper.CompanyMapper;
import org.hiring.api.repository.company.CompanyRepository;
import org.hiring.api.repository.company.query.CompanyQueryRepository;
import org.hiring.api.repository.company.query.CompanySearchCondition;
import org.hiring.api.service.company.usecase.LoadCompanyUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadCompanyService implements LoadCompanyUseCase {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final CompanyQueryRepository companyQueryRepository;

    @Override
    public PagedResult<Company> loadCompanies(final LoadCompaniesServiceRequest request) {
        final var condition = new CompanySearchCondition(
                request.name(),
                request.industry(),
                request.employeeCount(),
                request.address(),
                request.getOffset(),
                request.getLimit()
        );

        final var companyEntities = companyQueryRepository.loadCompanies(condition);

        final var companies = companyEntities.stream()
                                             .map(companyMapper::toModel)
                                             .toList();

        return PagedResult.of(
                companies,
                request.page(),
                request.size(),
                companyEntities.getTotalElements()
        );
    }

    @Override
    public Company loadCompany(final Long id) {
        final var companyEntity =  companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found by id : " + id));

        return companyMapper.toModel(companyEntity);
    }
}
