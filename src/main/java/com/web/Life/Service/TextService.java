package com.web.Life.Service;


import com.web.Life.dto.TextDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



public interface TextService {
    List<TextDto> getAllList();

    String createText(TextDto textDto,MultipartFile file) throws IOException;

    String deleteExistingText(int id) throws IOException;

    String updateTextDetails(int id,TextDto textDto,MultipartFile file) throws IOException;
    TextDto  getTextById(int id) throws IOException;




}
