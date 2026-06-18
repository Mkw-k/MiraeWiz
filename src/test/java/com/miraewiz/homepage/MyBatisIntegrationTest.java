package com.miraewiz.homepage;

import com.miraewiz.homepage.mapper.ProgramMapper;
import com.miraewiz.homepage.mapper.SiteContentMapper;
import com.miraewiz.homepage.model.Program;
import com.miraewiz.homepage.model.SiteContent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class MyBatisIntegrationTest {

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private SiteContentMapper siteContentMapper;

    @Test
    void testFindAllVisiblePrograms() {
        List<Program> programs = programMapper.findAllVisible();
        assertThat(programs).isNotEmpty();
        assertThat(programs.get(0).getTitle()).isEqualTo("중학 영단어");
    }

    @Test
    void testFindSiteContentByKey() {
        SiteContent vision = siteContentMapper.findByKey("ABOUT_VISION");
        assertThat(vision).isNotNull();
        assertThat(vision.getContentValue()).contains("미래를 향한 끊임없는 도전");
    }
}
