package com.web.Life.Controller;

import com.web.Life.Service.TextService;
import com.web.Life.Service.TextServiceImpl;
import com.web.Life.dto.TextDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "v1/lifeAtQb")
@CrossOrigin("http://localhost:4200")
public class TextController {


    @Autowired
    TextService textService;

    public TextController(TextServiceImpl textService) {
        this.textService = textService;
    }


    @GetMapping("/getAllText")
    public ResponseEntity<List<TextDto>> allTextList() {
        List<TextDto> list = textService.getAllList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/createText", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, TextDto textDto) throws IOException {
        return new ResponseEntity<>(textService.createText(textDto, file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTextById")
    public String deleteTextById(@RequestParam int id) throws IOException {
        return textService.deleteExistingText(id);
    }

    @GetMapping("/getTextById")
    public TextDto getText(@RequestParam int id) throws IOException {
        return textService.getTextById(id);
    }

    @PutMapping(value = "/updateTextDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam int id, @RequestParam("file") MultipartFile file, TextDto textDto) throws IOException {
        return new ResponseEntity<>(textService.updateTextDetails(id, textDto, file), HttpStatus.OK);
    }
}