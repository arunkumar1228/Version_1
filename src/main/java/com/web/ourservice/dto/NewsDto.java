package com.web.ourservice.dto;


import com.web.ourservice.entity.NewsImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {
    private int id;
    private String title;
    private String subTitle;
    private String callOfAction;

    @OneToOne
    private NewsImage newsImage;
}
