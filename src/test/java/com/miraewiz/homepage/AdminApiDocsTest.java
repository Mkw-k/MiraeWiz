package com.miraewiz.homepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miraewiz.homepage.model.Faq;
import com.miraewiz.homepage.model.Program;
import com.miraewiz.homepage.model.SiteContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureMockMvc
@ActiveProfiles("local")
class AdminApiDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getProgramsList() throws Exception {
        this.mockMvc.perform(get("/api/admin/programs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin-get-programs"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getReviewsList() throws Exception {
        this.mockMvc.perform(get("/api/admin/reviews")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin-get-reviews"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getFaqsList() throws Exception {
        this.mockMvc.perform(get("/api/admin/faqs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin-get-faqs"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getContentsList() throws Exception {
        this.mockMvc.perform(get("/api/admin/contents")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("admin-get-contents"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createNewFaq() throws Exception {
        Faq faq = new Faq();
        faq.setQuestion("New Question");
        faq.setAnswer("New Answer");
        faq.setCategory("General");

        this.mockMvc.perform(post("/api/admin/faqs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faq)))
                .andExpect(status().isOk())
                .andDo(document("admin-create-faq"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateSiteContent() throws Exception {
        SiteContent content = new SiteContent();
        content.setContentValue("Updated Value");
        content.setDescription("Updated Desc");

        this.mockMvc.perform(put("/api/admin/contents/ABOUT_VISION")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content)))
                .andExpect(status().isOk())
                .andDo(document("admin-update-content"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteProgram() throws Exception {
        this.mockMvc.perform(delete("/api/admin/programs/1"))
                .andExpect(status().isNoContent())
                .andDo(document("admin-delete-program"));
    }
}
