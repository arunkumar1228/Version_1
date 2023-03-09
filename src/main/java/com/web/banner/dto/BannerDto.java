package com.web.banner.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    private int id;
    private String bannerTitle;
    private String bannerSubTitle;
    private String bannerCallOfAction;
    @OneToOne
    private ImageDto image;
}
