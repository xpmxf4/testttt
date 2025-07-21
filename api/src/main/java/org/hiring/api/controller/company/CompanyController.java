package org.hiring.api.controller.company;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hiring.api.common.response.BaseResponse;
import org.hiring.api.common.response.PagedResult;
import org.hiring.api.domain.Company;
import org.hiring.api.service.company.usecase.LoadCompanyUseCase;
import org.hiring.api.service.company.usecase.ModifyCompanyUseCase;
import org.hiring.api.service.company.usecase.RegisterCompanyUseCase;
import org.hiring.api.service.company.usecase.RemoveCompanyUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final LoadCompanyUseCase loadCompanyUseCase;
    private final RemoveCompanyUseCase removeCompanyUseCase;
    private final RegisterCompanyUseCase registerCompanyUseCase;
    private final ModifyCompanyUseCase modifyCompanyUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> registerCompany(
            final @Valid @RequestBody RegisterCompanyApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();

        registerCompanyUseCase.registerCompany(serviceRequest);

        return ResponseEntity.ok(BaseResponse.created());
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<BaseResponse<Void>> removeCompany(
            final @PathVariable Long companyId
    ) {
        removeCompanyUseCase.removeCompany(companyId);

        return ResponseEntity.ok(BaseResponse.success());
    }

    @PatchMapping("/{companyId}")
    public ResponseEntity<BaseResponse<Void>> modifyCompany(
            final @Valid @RequestBody ModifyCompanyApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();

        modifyCompanyUseCase.modifyCompany(serviceRequest);

        return ResponseEntity.ok(BaseResponse.success());
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<BaseResponse<Company>> getCompany(
            final @PathVariable Long companyId
    ) {
        final var company = loadCompanyUseCase.loadCompany(companyId);
        return ResponseEntity.ok(BaseResponse.success(company));
    }
    
    @GetMapping
    public ResponseEntity<BaseResponse<PagedResult<Company>>> getCompanies(
            final @Valid @RequestBody LoadCompaniesApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();
        final var companies = loadCompanyUseCase.loadCompanies(serviceRequest);

        return ResponseEntity.ok(BaseResponse.success(companies));
    }
}
