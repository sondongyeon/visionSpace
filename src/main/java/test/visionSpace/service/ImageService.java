package test.visionSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.visionSpace.model.Image;
import test.visionSpace.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(byte[] data) {
        Image image = new Image();
        image.setData(data);
        imageRepository.save(image);
    }
}
