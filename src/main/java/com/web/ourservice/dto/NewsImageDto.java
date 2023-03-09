package com.web.ourservice.dto;


import lombok.Data;


import javax.persistence.Lob;

@Data
public class NewsImageDto {
    private int id;


    private String imageName;


    @Lob
    private byte[] data;

}
