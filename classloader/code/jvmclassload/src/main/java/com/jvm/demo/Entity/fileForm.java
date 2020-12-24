package com.jvm.demo.Entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class fileForm {
    private MultipartFile file;
}
