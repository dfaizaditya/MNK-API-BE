package com.sagara.momnkids.models;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageRequest implements Serializable {
    private MultipartFile image;
}
