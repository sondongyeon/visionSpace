package test.visionSpace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.visionSpace.model.Content;
import test.visionSpace.model.Image;
import test.visionSpace.service.ContentService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    private static long contentIndex = 1;

    @GetMapping("/add")
    public String addContentForm(Model model) {
        model.addAttribute("content", new Content());
        return "add-content";
    }

    @PostMapping("/add")
    public String addContent(@RequestParam String title, @RequestParam String description, @RequestParam("images") MultipartFile[] files) {
        List<Image> images = Arrays.stream(files)
                .map(file -> {
                    try {
                        Image image = new Image();
                        image.setData(file.getBytes());
                        return image;
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to convert image to byte array", e);
                    }
                })
                .collect(Collectors.toList());

        Content content = new Content();
        content.setTitle(title);
        content.setDescription(description);
        content.setImages(images);
        content.setContentId(contentIndex);
        contentIndex++;

        contentService.addContent(content);
        return "index";
    }

    @GetMapping("/update/{contentId}")
    public String updateContentForm(@PathVariable Long contentId, Model model) {
        Content content = contentService.getContent(contentId);
        model.addAttribute("content", content);
        return "update-content";
    }

    @PostMapping("/update")
    public String updateContent(@ModelAttribute Content content, @RequestParam("images") List<MultipartFile> images) {
        contentService.updateContent(content, images);
        return "redirect:/content/list";
    }

    @DeleteMapping("/delete/{contentId}")
    public String deleteContent(@PathVariable Long contentId) {
        contentService.deleteContent(contentId);
        return "index";
    }

    @GetMapping("/list")
    public String listContents(Model model) {
        List<Content> contents = contentService.getAllContents();
        model.addAttribute("contents", contents);
        return "content-list";
    }
}
