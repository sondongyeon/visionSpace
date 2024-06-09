package test.visionSpace.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import test.visionSpace.model.Content;
import test.visionSpace.service.ContentService;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @Test
    public void testAddContent() throws Exception {
        // Mock 데이터 생성
        MockMultipartFile file = new MockMultipartFile("images", "test.jpg", "image/jpeg", "image data".getBytes());
        Content content = new Content();
        content.setTitle("Test Title");
        content.setDescription("Test Description");

        // contentService.addContent() 메소드의 반환값 설정
        when(contentService.addContent("Test Title", "Test Description", Collections.singletonList(file)))
                .thenReturn(content);

        // POST 요청 시뮬레이션
        mockMvc.perform(MockMvcRequestBuilders.multipart("/content/add")
                        .file(file)
                        .param("title", "Test Title")
                        .param("description", "Test Description"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-content"));
    }
}
