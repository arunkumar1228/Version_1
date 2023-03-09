package com.web.Life.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QbImageDto {
    private int id;

    private String imageName;
    @Lob
    private byte[] data;
}
