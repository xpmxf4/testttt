package org.hiring.api.service.job;

import lombok.RequiredArgsConstructor;
import org.hiring.api.repository.job.JobRepository;
import org.hiring.api.service.job.usecase.RemoveJobUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveJobService implements RemoveJobUseCase {

    private final JobRepository jobRepository;

    @Override
    public void removeJob(final Long jobId) {
        final var jobEntity = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id " + jobId));

        jobRepository.delete(jobEntity);
    }
}
