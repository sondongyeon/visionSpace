package test.visionSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.visionSpace.model.Content;
import test.visionSpace.model.Image;
import test.visionSpace.repository.ContentRepository;
import test.visionSpace.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void addContent(Content content) {
        contentRepository.save(content);
    }

    public void updateContent(Content content, List<MultipartFile> images) {
        contentRepository.save(content);
    }

    public Content getContent(Long contentId) {
        return contentRepository.findById(contentId).orElse(null);
    }

    public void deleteContent(Long contentId) {
        contentRepository.deleteById(contentId);
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }
}
