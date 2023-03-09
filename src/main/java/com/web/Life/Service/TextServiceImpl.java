package com.web.Life.Service;

import com.web.Life.dto.QbImageDto;
import com.web.Life.dto.TextDto;

import com.web.Life.entity.QbImage;
import com.web.Life.entity.Text;
import com.web.Life.repository.ImageRepository;
import com.web.Life.repository.TextRepository;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Slf4j
public class TextServiceImpl implements TextService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TextRepository textRepository;
    @Autowired
    DozerBeanMapper dozerBeanMapper;

    @Override
    public List<TextDto> getAllList() {
        List<Text> texts = textRepository.findAll();
        return texts.stream().map(allList -> dozerBeanMapper.map( allList, TextDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public String createText(TextDto textDto, MultipartFile file) throws IOException {
        QbImageDto qbImageDto = new QbImageDto();
        QbImage qbImage= dozerBeanMapper.map(qbImageDto,QbImage.class);
        qbImage.setImageName(file.getOriginalFilename());
        qbImage.setData(file.getBytes());
        imageRepository.save(qbImage);

        Text text = dozerBeanMapper.map(textDto, Text.class);
         text.setId(textDto.getId());
         text.setTextTitle(textDto.getTextTitle());
         text.setTextTitle(textDto.getTextTitle());
         text.setEstablished(textDto.getEstablished());
         text.setProject(textDto.getProject());
         text.setClient(textDto.getProject());
         text.setImage(qbImage);
         textRepository.save(text);
        return "Ok" ;
    }

    @Override
    public String deleteExistingText(int id) throws IOException {
        Text text = textRepository.findById(id).orElseThrow(RuntimeException::new);
        textRepository.saveAndFlush(text);
        return "Successfully deleted";
    }

    @Override
    public String updateTextDetails(int id, TextDto textDto, MultipartFile file) throws IOException {
        Optional<Text> textRepoById= textRepository.findById(id);
        if(textRepoById.isPresent()){
            QbImage imagedto = new QbImage();
            QbImage image= dozerBeanMapper.map(imagedto,QbImage.class);
            image.setImageName(file.getOriginalFilename());
            image.setData(file.getBytes());
            imageRepository.save(image);
            Text text = dozerBeanMapper.map(textDto, Text.class);
            text.setId(textDto.getId());
            text.setTextTitle(textDto.getTextTitle());
            text.setTextSubTitle(textDto.getTextSubTitle());
            text.setEstablished(textDto.getEstablished());
            text.setProject(textDto.getProject());
            text.setClient(textDto.getProject());
            text.setImage(image);
            textRepository.save(text);
            return "Updated Successfully" ;
        }
        return "Not Updated text details";
    }

    @Override
    public TextDto getTextById(int id) throws IOException {
        Text text = textRepository.findById(id).orElseThrow(RuntimeException::new);
        return dozerBeanMapper.map(text, TextDto.class);
    }
}



