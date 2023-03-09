package com.web.Life.dto;


import com.web.Life.entity.QbImage;

import lombok.*;

import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextDto {

    private int id;

    private String textTitle;

    private String textSubTitle;

    private String established;

    private String project;
    private String client;

    @OneToOne
    private QbImage image;
}
