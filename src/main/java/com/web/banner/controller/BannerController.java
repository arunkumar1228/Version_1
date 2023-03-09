package com.web.banner.controller;


import com.web.banner.dto.BannerDto;
import com.web.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1/Banner")
@CrossOrigin("http://localhost:4200")
public class BannerController {
    @Autowired
     BannerService bannerService;

    @GetMapping("/getAllBanners")
    public ResponseEntity<List<BannerDto>> allBannerList() {
        List<BannerDto> list = bannerService.getAllList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/createBanner",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, BannerDto bannerdto) throws IOException {
        return new ResponseEntity<>(bannerService.createBanner(bannerdto,file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBannerById")
    public String deleteBannerById(@RequestParam int id) throws IOException {
        return bannerService.deleteExistingBanner(id);
    }

    @GetMapping("/getBannerById")
    public BannerDto getBanner(@RequestParam int id) throws IOException {
        return bannerService.getBannerById(id);
    }

    @PutMapping(value = "/updateBannerDetails",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam int id ,@RequestParam("file") MultipartFile file, BannerDto bannerdto) throws IOException {
        return new ResponseEntity<>(bannerService.updateBannerDetails(id,bannerdto,file), HttpStatus.OK);
    }


}
