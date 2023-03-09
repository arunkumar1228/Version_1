package com.web.banner.service;



import com.web.banner.dto.BannerDto;
import com.web.banner.dto.ImageDto;
import com.web.banner.entity.Banner;
import com.web.banner.entity.BannerImage;
import com.web.banner.repository.BannerRepository;
import com.web.banner.repository.BannerImageRepository;
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
public class BannerServiceImpl implements BannerService {

    @Autowired
     BannerRepository bannerRepository;
    @Autowired
    BannerImageRepository bannerImageRepository;
    @Autowired
     DozerBeanMapper dozerBeanMapper;

    @Override
    public List<BannerDto> getAllList() {

        List<Banner> cv = bannerRepository.findAll();
        return cv.stream().map(banner -> dozerBeanMapper.map(banner,BannerDto.class)).collect(Collectors.toList());
    }

    @Override
    public String createBanner(BannerDto bannerDto, MultipartFile file) throws IOException {

        ImageDto imagedto = new ImageDto();
        BannerImage image= dozerBeanMapper.map(imagedto,BannerImage.class);
        image.setFileName(file.getOriginalFilename());
        image.setData(file.getBytes());
        bannerImageRepository.save(image);
        Banner banner = dozerBeanMapper.map(bannerDto, Banner.class);
        banner.setId(bannerDto.getId());
        banner.setBannerTitle(bannerDto.getBannerTitle());
        banner.setBannerSubTitle(bannerDto.getBannerSubTitle());
        banner.setBannerCallOfAction(bannerDto.getBannerCallOfAction());
        banner.setImage(image);
        bannerRepository.save(banner);
        return "Ok" ;

    }


    @Override
    public String deleteExistingBanner(int id) throws IOException {
        Banner banner = bannerRepository.findById(id).orElseThrow(RuntimeException::new);
        bannerRepository.saveAndFlush(banner);
        return "Successfully deleted";
    }

    @Override
    public String updateBannerDetails(int id, BannerDto bannerDto, MultipartFile file) throws IOException {
        Optional<Banner> banner=bannerRepository.findById(id);
        if(banner.isPresent()){
        ImageDto imagedto = new ImageDto();
        BannerImage image= dozerBeanMapper.map(imagedto,BannerImage.class);
        image.setFileName(file.getOriginalFilename());
        image.setData(file.getBytes());
        bannerImageRepository.save(image);

        Banner banner1 = dozerBeanMapper.map(bannerDto, Banner.class);
        banner1.setId(bannerDto.getId());
        banner1.setBannerTitle(bannerDto.getBannerTitle());
        banner1.setBannerSubTitle(bannerDto.getBannerSubTitle());
        banner1.setBannerCallOfAction(bannerDto.getBannerCallOfAction());
        banner1.setImage(image);
        bannerRepository.save(banner1);
        return "Updated Successfully" ;
        }
        return "Not Updated banner details";
    }


    @Override
    public BannerDto getBannerById(int id) throws IOException {
        Banner bannerInfo = bannerRepository.findById(id).orElseThrow(RuntimeException::new);
        return dozerBeanMapper.map(bannerInfo,BannerDto.class);
    }

}
