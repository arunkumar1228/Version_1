package com.web.weare.service;

import com.web.weare.dto.FeedBackDto;
import com.web.weare.dto.FeedBackImageDto;
import com.web.weare.entity.FeedBackImage;
import com.web.weare.entity.Feedback;
import com.web.weare.repository.FeedBackImageRepository;
import com.web.weare.repository.FeedBackRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    FeedBackImageRepository feedBackImageRepository;
    @Autowired
    FeedBackRepository feedBackRepository;
    @Autowired
    DozerBeanMapper dozerBeanMapper;



    @Override
    public List<FeedBackDto> getAllList() {
        List<Feedback> feedbacks = feedBackRepository.findAll();
        return feedbacks.stream().map(feedback -> dozerBeanMapper.map(feedback, FeedBackDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public String createFeedback(FeedBackDto feedBackDto, MultipartFile file) throws IOException {
        FeedBackImageDto imagedto = new FeedBackImageDto();
        FeedBackImage image = dozerBeanMapper.map(imagedto, FeedBackImage.class);
        image.setImageName(file.getOriginalFilename());
        image.setData(file.getBytes());
        feedBackImageRepository.save(image);
        Feedback feedback = dozerBeanMapper.map(feedBackDto, Feedback.class);
        feedback.setId(feedBackDto.getId());
        feedback.setTitle(feedBackDto.getTitle());
        feedback.setSubTitle(feedBackDto.getSubTitle());
        feedback.setCallOfAction(feedBackDto.getCallOfAction());
        feedback.setFeedBackImage(image);
        feedBackRepository.save(feedback);
        return "Ok";
    }

    @Override
    public String deleteFeedback(int id) throws IOException {
        Feedback feedback = feedBackRepository.findById(id).orElseThrow(RuntimeException::new);
        feedBackRepository.saveAndFlush(feedback);
        return "Successfully deleted";
    }

    @Override
    public String updateFeedbackDetails(int id, FeedBackDto feedBackDto, MultipartFile file) throws IOException {
        Optional<Feedback> feedback1 = feedBackRepository.findById(id);
        if (feedback1.isPresent()) {
            FeedBackImageDto imagedto = new FeedBackImageDto();
            FeedBackImage image = dozerBeanMapper.map(imagedto, FeedBackImage.class);
            image.setImageName(file.getOriginalFilename());
            image.setData(file.getBytes());
            feedBackImageRepository.save(image);
            Feedback feedback = dozerBeanMapper.map(feedBackDto, Feedback.class);
            feedback.setId(feedBackDto.getId());
            feedback.setTitle(feedBackDto.getTitle());
            feedback.setSubTitle(feedBackDto.getSubTitle());
            feedback.setCallOfAction(feedBackDto.getCallOfAction());
            feedback.setFeedBackImage(image);
            feedBackRepository.save(feedback);
            return "Updated Successfully";
        }
        return "Not Updated banner details";
    }

    @Override
    public FeedBackDto getFeedbackById(int id) throws IOException {
        Feedback feedback = feedBackRepository.findById(id).orElseThrow(RuntimeException::new);
        return dozerBeanMapper.map(feedback, FeedBackDto.class);
    }
}
