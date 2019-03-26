package com.bigdata.storm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTypeEntity {
    private String type;
    private int number;
    private java.util.List<UrlEntity> List = new ArrayList();
}
