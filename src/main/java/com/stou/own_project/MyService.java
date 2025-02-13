package com.stou.own_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
@Service
public class MyService {
    @Autowired
    private MyClassModelRepo myClassModelRepo;

    private static final String DIR = "src/main/resources/static/TheImages/";

    public void uploadImage(MultipartFile file) throws IOException {

        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Date date = new Date();
        String imageName = date.getTime()+file.getOriginalFilename();
        Path path = Paths.get(DIR + imageName);
        Files.copy(file.getInputStream(), path);

        MyClassModel myClassModel = new MyClassModel();
        myClassModel.setName(imageName);
        myClassModel.setSomething(path.toString());
        myClassModelRepo.save(myClassModel);
    }

    public void deleteImage(String imageId,String imageName) {

        File file = new File(DIR + imageName);
        file.delete();
        myClassModelRepo.deleteById(imageId);
    }


    public List<MyClassModel> getAllImages() {
        return myClassModelRepo.findAll();
    }
}
