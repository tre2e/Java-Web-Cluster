//Video.java
package com.example.video.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Video {
    Integer id;
    String title;
    String filename;
    String path;
    String thumbnail;
    LocalDateTime upload_time;
    String sort;
}
