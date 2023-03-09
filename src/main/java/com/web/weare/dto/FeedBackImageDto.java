package com.web.weare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedBackImageDto {
    private int id;

    @Column(name = "image_name")
    private String imageName;


    @Column(name = "image_data")
    @Lob
    private byte[] data;
}
