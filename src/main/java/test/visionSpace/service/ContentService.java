package test.visionSpace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import test.visionSpace.model.Content;
import test.visionSpace.model.Image;
import test.visionSpace.repository.ContentRepository;
import test.visionSpace.repository.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ImageRepository imageRepository;

    public Content addContent(String title, String description, List<MultipartFile> images) {
        Content content = new Content();
        content.setTitle(title);
        content.setDescription(description);

        List<Image> imageList = images.stream()
                .map(image -> {
                    Image img = new Image();
                    img.setImageUrl(image.getOriginalFilename());
                    img.setContent(content);
                    return img;
                })
                .collect(Collectors.toList());

        content.setImages(imageList);
        return contentRepository.save(content);
    }

    public void deleteContent(Long contentId) {
        contentRepository.deleteById(String.valueOf(contentId));
    }

    public List<Content> listContents(int page, int limit) {
        return contentRepository.findAll(PageRequest.of(page - 1, limit)).getContent();
    }

    public Content updateContent(Long contentId, String title, String description, List<MultipartFile> images) {
        Content content = contentRepository.findById(String.valueOf(contentId)).orElseThrow(() -> new IllegalArgumentException("Invalid content Id:" + contentId));
        content.setTitle(title);
        content.setDescription(description);

        content.getImages().clear();
        List<Image> imageList = images.stream()
                .map(image -> {
                    Image img = new Image();
                    img.setImageUrl(image.getOriginalFilename()); // 실제로는 이미지를 저장하고 URL을 설정해야 합니다.
                    img.setContent(content);
                    return img;
                })
                .collect(Collectors.toList());

        content.getImages().addAll(imageList);

        return contentRepository.save(content);
    }
}
