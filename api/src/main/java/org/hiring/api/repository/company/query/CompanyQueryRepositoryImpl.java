package org.hiring.api.repository.company.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hiring.api.entity.CompanyJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static org.hiring.api.entity.QCompanyJpaEntity.companyJpaEntity;

@Repository
@RequiredArgsConstructor
public class CompanyQueryRepositoryImpl implements CompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CompanyJpaEntity> loadCompanies(final CompanySearchCondition condition) {
         final var query = queryFactory
                .selectFrom(companyJpaEntity)
                .where(
                        nameContains(condition.name()),
                        industryContains(condition.industry()),
                        employeeCountEq(condition.employeeCount()),
                        addressContains(condition.address())
                )
                .offset(condition.offset())
                .limit(condition.limit());

        return PageableExecutionUtils.getPage(
                query.fetch(),
                Pageable.ofSize(condition.limit()),
                query::fetchCount
        );
    }

    private BooleanExpression nameContains(String name) {
        return StringUtils.hasText(name) ? companyJpaEntity.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression industryContains(String industry) {
        return StringUtils.hasText(industry) ?  companyJpaEntity.industry.containsIgnoreCase(industry) : null;
    }

    private BooleanExpression employeeCountEq(Integer integer) {
        return companyJpaEntity.employeeCount != null ?  companyJpaEntity.employeeCount.eq(integer) : null;
    }

    private BooleanExpression addressContains(String address) {
        return  StringUtils.hasText(address) ? companyJpaEntity.address.containsIgnoreCase(address) : null;
    }
}
