package com.web.ourservice.service;
import com.web.ourservice.dto.NewsDto;
import com.web.ourservice.dto.NewsImageDto;
import com.web.ourservice.entity.News;

import com.web.ourservice.entity.NewsImage;
import com.web.ourservice.repository.NewsImageRepository;
import com.web.ourservice.repository.NewsRepository;
import org.dozer.DozerBeanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    DozerBeanMapper dozerBeanMapper;
    @Autowired
    NewsImageRepository newsImageRepository;

    public NewsServiceImpl(NewsRepository newsRepository, DozerBeanMapper dozerBeanMapper, NewsImageRepository newsImageRepository) {
        this.newsRepository = newsRepository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.newsImageRepository = newsImageRepository;
    }


    @Override
    public List<NewsDto> getAllNews() {
        List<News> cv = newsRepository.findAll();
        return cv.stream().map(allNews -> dozerBeanMapper.map(allNews,NewsDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public String createNews(NewsDto newsDto, MultipartFile file) throws IOException {
        NewsImageDto imagedto = new NewsImageDto();
        NewsImage image = dozerBeanMapper.map(imagedto, NewsImage.class);
        image.setImageName(file.getOriginalFilename());
        image.setData(file.getBytes());
        newsImageRepository.save(image);
        News news = dozerBeanMapper.map(newsDto, News.class);
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setSubTitle(newsDto.getSubTitle());
        news.setCallOfAction(newsDto.getCallOfAction());
        news.setNewsImage(image);
        newsRepository.save(news);
        return "Ok";
    }

    @Override
    public String deleteNews(int id) throws IOException {
        News news = newsRepository.findById(id).orElseThrow(RuntimeException::new);
        newsRepository.saveAndFlush(news);
        return "Successfully deleted";
    }

    @Override
    public String updateNewsDetails(int id, NewsDto newsDto, MultipartFile file) throws IOException {
        Optional<News> allNews = newsRepository.findById(id);
        if (allNews.isPresent()) {
            NewsImageDto imagedto = new NewsImageDto();
            NewsImage image = dozerBeanMapper.map(imagedto, NewsImage.class);
            image.setImageName(file.getOriginalFilename());
            image.setData(file.getBytes());
            newsImageRepository.save(image);
            News news = dozerBeanMapper.map(newsDto, News.class);
            news.setId(newsDto.getId());
            news.setTitle(newsDto.getTitle());
            news.setSubTitle(newsDto.getSubTitle());
            news.setCallOfAction(newsDto.getCallOfAction());
            news.setNewsImage(image);
            newsRepository.save(news);
            return "Updated Successfully";
        }
        return "Not Updated news details";
    }

    @Override
    public NewsDto getNewsById(int id) throws IOException {
        News news = newsRepository.findById(id).orElseThrow(RuntimeException::new);
        return dozerBeanMapper.map(news, NewsDto.class);
    }
}



