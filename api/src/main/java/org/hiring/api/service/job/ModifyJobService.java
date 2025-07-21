package org.hiring.api.service.job;

import lombok.RequiredArgsConstructor;
import org.hiring.api.repository.company.CompanyRepository;
import org.hiring.api.repository.job.JobRepository;
import org.hiring.api.service.job.usecase.ModifyJobUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyJobService implements ModifyJobUseCase {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void modifyJob(final ModifyJobServiceRequest request) {
        final var jobEntity = jobRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id : "  + request.id()));

        final var companyEntity = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new IllegalArgumentException("Company not found with id : "  + request.companyId()));

        jobEntity.updateInfo(
                companyEntity,
                request.title(),
                request.description(),
                request.city(),
                request.district(),
                request.employmentType(),
                request.experienceLevel(),
                request.educationLevel(),
                request.postedAt(),
                request.deadline(),
                request.requirements(),
                request.benefits()
        );
    }
}
