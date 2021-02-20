package com.CarAnnounceRestApi.RestApiCarMobileAppSpringBoot.Base64;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@UtilityClass
public class Convert {

    public  byte[]  ConvertBase64(String path) throws IOException {

        ClassPathResource imgFile = new ClassPathResource(path);
        return StreamUtils.copyToByteArray(imgFile.getInputStream());
    }


}
