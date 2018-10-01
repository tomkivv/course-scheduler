package com.example.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CoursePrerequisite {

    private Integer cousreId;
    private Integer prerequisiteCourseId;
}
