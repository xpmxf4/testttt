package org.hiring.api.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Company(
    Long id,
    String name,
    String industry,
    String description,
    Integer employeeCount,
    Integer foundedYear,
    String logoUrl,
    String websiteUrl,
    String address,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<Job> jobs
) {}