package org.hiring.api.service.job;

import lombok.RequiredArgsConstructor;
import org.hiring.api.entity.JobJpaEntity;
import org.hiring.api.repository.company.CompanyRepository;
import org.hiring.api.repository.job.JobRepository;
import org.hiring.api.service.job.usecase.RegisterJobUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterJobService implements RegisterJobUseCase {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    @Override
    public void registerJob(final RegisterJobServiceRequest request) {
        final var companyEntity = companyRepository.findById(request.companyId())
                                                   .orElseThrow(() -> new IllegalArgumentException("Company not found with id : "  + request.companyId()));

        final var jobEntity = JobJpaEntity.builder()
                .company(companyEntity)
                .title(request.title())
                .description(request.description())
                .employmentType(request.employmentType())
                .experienceLevel(request.experienceLevel())
                .educationLevel(request.educationLevel())
                .city(request.city())
                .district(request.district())
                .postedAt(request.postedAt())
                .deadline(request.deadline())
                .requirements(request.requirements())
                .benefits(request.benefits())
                .build();

        jobRepository.save(jobEntity);
    }
}