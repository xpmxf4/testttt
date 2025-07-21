package org.hiring.api.repository.job;

import org.hiring.api.entity.JobJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobJpaEntity, Long> {
}
