package com.web.banner.service;



import com.web.banner.dto.BannerDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface BannerService {
    List<BannerDto> getAllList();

    String createBanner(BannerDto bannerDto,MultipartFile file) throws IOException;

    String deleteExistingBanner(int id) throws IOException;

    String updateBannerDetails(int id,BannerDto bannerDto,MultipartFile file) throws IOException;
    BannerDto getBannerById(int id) throws IOException;
}
