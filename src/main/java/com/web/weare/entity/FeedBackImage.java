package com.web.weare.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FeedBack_image")
public class FeedBackImage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "image_name")
    private String imageName;


    @Column(name = "image_data")
    @Lob
    private byte[] data;





}