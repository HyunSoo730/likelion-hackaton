package com.example.lionproject.domain.entity;

import com.example.lionproject.OpenApi.CallResponse.dto.SenuriServiceDetailDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SenuriServiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobId;

    public SenuriServiceDetail(String jobId) {
        this.jobId = jobId;
    }

    public static SenuriServiceDetail fromDto(SenuriServiceDetailDto dto) {
        return new SenuriServiceDetail(dto.getJobId());
    }
}
