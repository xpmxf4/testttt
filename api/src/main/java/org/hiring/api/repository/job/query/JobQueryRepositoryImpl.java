package org.hiring.api.repository.job.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hiring.api.entity.JobJpaEntity;
import org.hiring.api.enums.EducationLevel;
import org.hiring.api.enums.EmploymentType;
import org.hiring.api.enums.ExperienceLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static org.hiring.api.entity.QCompanyJpaEntity.companyJpaEntity;
import static org.hiring.api.entity.QJobJpaEntity.jobJpaEntity;

@Repository
@RequiredArgsConstructor
public class JobQueryRepositoryImpl implements JobQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<JobJpaEntity> loadJobs(final JobSearchCondition condition) {
        final var query = queryFactory
                .selectFrom(jobJpaEntity)
                .leftJoin(jobJpaEntity.company, companyJpaEntity)
                .where(
                        titleContains(condition.title()),
                        descriptionContains(condition.description()),
                        employmentTypeEq(condition.employmentType()),
                        experienceLevelEq(condition.experienceLevel()),
                        educationLevelEq(condition.educationLevel()),
                        cityContains(condition.city()),
                        districtContains(condition.district())
                )
                .offset(condition.offset())
                .limit(condition.limit());

        return PageableExecutionUtils.getPage(
                query.fetch(),
                Pageable.ofSize(condition.limit()),
                query::fetchCount
        );
    }

    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? jobJpaEntity.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression descriptionContains(String description) {
        return StringUtils.hasText(description) ? jobJpaEntity.description.containsIgnoreCase(description) : null;
    }

    private BooleanExpression employmentTypeEq(EmploymentType employmentType) {
        return employmentType != null ? jobJpaEntity.employmentType.eq(employmentType) : null;
    }

    private BooleanExpression experienceLevelEq(ExperienceLevel experienceLevel) {
        return experienceLevel != null ? jobJpaEntity.experienceLevel.eq(experienceLevel) : null;
    }

    private BooleanExpression educationLevelEq(EducationLevel educationLevel) {
        return educationLevel != null ? jobJpaEntity.educationLevel.eq(educationLevel) : null;
    }

    private BooleanExpression cityContains(String city) {
        return StringUtils.hasText(city) ? jobJpaEntity.city.containsIgnoreCase(city) : null;
    }

    private BooleanExpression districtContains(String district) {
        return StringUtils.hasText(district) ? jobJpaEntity.district.containsIgnoreCase(district) : null;
    }
}