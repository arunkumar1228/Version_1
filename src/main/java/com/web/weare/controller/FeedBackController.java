package com.web.weare.controller;


import com.web.weare.dto.FeedBackDto;
import com.web.weare.service.FeedBackService;
import com.web.weare.service.FeedBackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "v1/WhoWeAre")
@CrossOrigin("http://localhost:4200")
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;

    public FeedBackController(FeedBackServiceImpl feedBackService) {
        this.feedBackService = feedBackService;
    }


    @GetMapping("/getAllFeedback")
    public ResponseEntity<List<FeedBackDto>> allBannerList() {
        List<FeedBackDto> list = feedBackService.getAllList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/createFeedback", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, FeedBackDto feedBackDto) throws IOException {
        return new ResponseEntity<>(feedBackService.createFeedback(feedBackDto, file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFeedbackById")
    public String deleteBannerById(@RequestParam int id) throws IOException {
        return feedBackService.deleteFeedback(id);
    }

    @GetMapping("/getFeedbackById")
    public FeedBackDto getBanner(@RequestParam int id) throws IOException {
        return feedBackService.getFeedbackById(id);
    }

    @PutMapping(value = "/updateFeedbackDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam int id, @RequestParam("file") MultipartFile file, FeedBackDto feedBackDto) throws IOException {
        return new ResponseEntity<>(feedBackService.updateFeedbackDetails(id, feedBackDto, file), HttpStatus.OK);
    }
}

