package com.web.weare.service;


import com.web.weare.dto.FeedBackDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



public interface FeedBackService {
    List<FeedBackDto> getAllList();

    String createFeedback(FeedBackDto feedBackDto,MultipartFile file) throws IOException;

    String deleteFeedback(int id) throws IOException;

    String updateFeedbackDetails(int id,FeedBackDto feedBackDto,MultipartFile file) throws IOException;
    FeedBackDto  getFeedbackById(int id) throws IOException;

}
