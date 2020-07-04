package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;

@Data
public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
}