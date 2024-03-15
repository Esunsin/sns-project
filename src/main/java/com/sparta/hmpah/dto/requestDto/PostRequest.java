package com.sparta.hmpah.dto.requestDto;

import com.sparta.hmpah.entity.LocationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PostRequest {
    private String title;
    private String content;
    private String location;
    private Integer maxcount;

    public PostRequest(String title, String content, String location, Integer maxcount) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.maxcount = maxcount;
    }
}
