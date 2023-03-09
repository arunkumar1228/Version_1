package com.web.banner.entity;



import com.web.utils.BaseEntity;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bannerTitle;
    private String bannerSubTitle;
    private String bannerCallOfAction;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "image",referencedColumnName = "id")
    private BannerImage image;
}
