package test.visionSpace.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.visionSpace.model.Content;
import test.visionSpace.service.ContentService;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @GetMapping("/add")
    public String showAddContentForm() {
        return "add-content"; // add-content.html 페이지를 반환
    }

    @PostMapping("/add")
    public String addContent(@RequestParam String title, @RequestParam String description, @RequestParam List<MultipartFile> images) {
        contentService.addContent(title, description, images);
        return "redirect:/content/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Content> listContents(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int limit) {
        logger.info("Listing contents with page: {} and limit: {}", page, limit);
        List<Content> contents = contentService.listContents(page, limit);
        logger.info("Contents: {}", contents);
        return contents;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public void deleteContent(@RequestParam Long contentId) {
        logger.info("Deleting content with id: {}", contentId);
        contentService.deleteContent(contentId);
    }

    @PutMapping("/update")
    public String updateContent(@RequestParam Long contentId, @RequestParam String title, @RequestParam String description, @RequestParam List<MultipartFile> images) {
        contentService.updateContent(contentId, title, description, images);
        return "redirect:/content/list";
    }

    @PostMapping("/preview")
    public String previewContent(@RequestParam String title, @RequestParam String description, @RequestParam List<MultipartFile> images, Model model) {
        Content content = new Content();
        content.setTitle(title);
        content.setDescription(description);
        model.addAttribute("content", content);
        return "preview";
    }
}
