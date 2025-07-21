package org.hiring.api.repository.job.query;

import org.hiring.api.entity.JobJpaEntity;
import org.springframework.data.domain.Page;

public interface JobQueryRepository {

    Page<JobJpaEntity> loadJobs(final JobSearchCondition condition);
}
