package com.web.ourservice.service;




import com.web.ourservice.dto.NewsDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface NewsService {
    List<NewsDto> getAllNews();

    String createNews(NewsDto newsDto,MultipartFile file) throws IOException;

    String deleteNews(int id) throws IOException;

    String updateNewsDetails(int id,NewsDto newsDto,MultipartFile file) throws IOException;
    NewsDto getNewsById(int id) throws IOException;
}
