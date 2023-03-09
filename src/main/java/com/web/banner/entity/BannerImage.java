package com.web.banner.entity;



import com.web.utils.BaseEntity;
import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BannerImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fileName;
    @Lob
    private byte[] data;

}
