package org.hiring.api.repository.company.query;

import org.hiring.api.entity.CompanyJpaEntity;
import org.springframework.data.domain.Page;

public interface CompanyQueryRepository {

    Page<CompanyJpaEntity> loadCompanies(final CompanySearchCondition condition);
}
