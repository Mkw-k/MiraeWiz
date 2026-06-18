package com.miraewiz.homepage.mapper;

import com.miraewiz.homepage.model.SiteContent;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SiteContentMapper {
    List<SiteContent> findAll();
    SiteContent findByKey(String contentKey);
}
