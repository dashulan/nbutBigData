package com.bigdata.storm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {
    private String name;
    private List<Entity> entities;
}
