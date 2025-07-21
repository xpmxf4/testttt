package org.hiring.api.repository.company;

import org.hiring.api.entity.CompanyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyJpaEntity, Long> {
}
