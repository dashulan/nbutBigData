package com.bigdata.storm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequireModeEntity {
    private String mode;
    private int number;
    private List<Long> timeList = new ArrayList();
}
