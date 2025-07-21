package org.hiring.api.service.job;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hiring.api.common.response.PagedResult;
import org.hiring.api.domain.Job;
import org.hiring.api.mapper.JobMapper;
import org.hiring.api.repository.job.JobRepository;
import org.hiring.api.repository.job.query.JobQueryRepository;
import org.hiring.api.repository.job.query.JobSearchCondition;
import org.hiring.api.service.job.usecase.LoadJobUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadJobService implements LoadJobUseCase {

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;
    private final JobQueryRepository jobQueryRepository;

    @Override
    public PagedResult<Job> loadJobs(final LoadJobsServiceRequest request) {
        final var condition = new JobSearchCondition(
                request.title(),
                request.description(),
                request.employmentType(),
                request.experienceLevel(),
                request.educationLevel(),
                request.city(),
                request.district(),
                request.getOffset(),
                request.getLimit()
        );

        final var jobEntities = jobQueryRepository.loadJobs(condition);

        final var jobs = jobEntities.stream()
                .map(jobMapper::toModel)
                .toList();

        return PagedResult.of(
                jobs,
                request.page(),
                request.size(),
                jobEntities.getTotalElements()
        );
    }

    @Override
    public Job loadJob(final Long jobId) {
        final var jobEntity = jobRepository
                .findById(jobId)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found with id : " + jobId));

        return jobMapper.toModel(jobEntity);
    }
}
