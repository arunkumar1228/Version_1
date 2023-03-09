package com.web.ourservice.controller;



import com.web.ourservice.dto.NewsDto;

import com.web.ourservice.service.NewsService;
import com.web.ourservice.service.NewsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1/OurService")
@CrossOrigin("http://localhost:4200")
public class NewsController {
    @Autowired
 NewsService newsService;

    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }


    @GetMapping("/getAllNews")
    public ResponseEntity<List<NewsDto>> allNewsList() {
        List<NewsDto> list = newsService.getAllNews();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/createNews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, NewsDto newsDto) throws IOException {
        return new ResponseEntity<>(newsService.createNews(newsDto, file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteNewsById")
    public String deleteNewsById(@RequestParam int id) throws IOException {
        return newsService.deleteNews(id);
    }

    @GetMapping("/getNewsById")
    public NewsDto getNews(@RequestParam int id) throws IOException {
        return newsService.getNewsById(id);
    }

    @PutMapping(value = "/updateNewsDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam int id, @RequestParam("file") MultipartFile file, NewsDto newsDto) throws IOException {
        return new ResponseEntity<>(newsService.updateNewsDetails(id, newsDto, file), HttpStatus.OK);
    }
}

