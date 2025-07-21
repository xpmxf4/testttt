package org.hiring.api.controller.job;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hiring.api.common.response.BaseResponse;
import org.hiring.api.common.response.PagedResult;
import org.hiring.api.domain.Job;
import org.hiring.api.service.job.usecase.LoadJobUseCase;
import org.hiring.api.service.job.usecase.ModifyJobUseCase;
import org.hiring.api.service.job.usecase.RegisterJobUseCase;
import org.hiring.api.service.job.usecase.RemoveJobUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final LoadJobUseCase loadJobUseCase;
    private final RegisterJobUseCase registerJobUseCase;
    private final ModifyJobUseCase modifyJobUseCase;
    private final RemoveJobUseCase removeJobUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> registerJob(
            final @Valid @RequestBody RegisterJobApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();

        registerJobUseCase.registerJob(serviceRequest);

        return ResponseEntity.ok(BaseResponse.created());
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<BaseResponse<Void>> removeJob(
            final @PathVariable Long jobId
    ) {
        removeJobUseCase.removeJob(jobId);

        return ResponseEntity.ok(BaseResponse.success());
    }

    @PatchMapping("/{jobId}")
    public ResponseEntity<BaseResponse<Void>> modifyJob(
            final @Valid @RequestBody ModifyJobApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();

        modifyJobUseCase.modifyJob(serviceRequest);

        return ResponseEntity.ok(BaseResponse.success());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<BaseResponse<Job>> getJob(
            final @PathVariable Long jobId
    ) {
        final var job = loadJobUseCase.loadJob(jobId);
        return ResponseEntity.ok(BaseResponse.success(job));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<PagedResult<Job>>> getJobs(
            final @Valid @RequestBody LoadJobsApiRequest request
    ) {
        final var serviceRequest = request.toServiceRequest();
        final var jobs = loadJobUseCase.loadJobs(serviceRequest);

        return ResponseEntity.ok(BaseResponse.success(jobs));
    }
}