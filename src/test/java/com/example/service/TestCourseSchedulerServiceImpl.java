package com.example.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.model.Course;
import com.example.model.CoursePrerequisite;
import com.google.common.collect.Sets;

public class TestCourseSchedulerServiceImpl {

    CourseSchedulerService courseSchedulerService;

    @Before
    public void init() {
        courseSchedulerService = new CourseSchedulerServiceImpl();
    }

    @Test
    public void generateCourseSelections() {

        Set<Course> courses = IntStream.rangeClosed(0, 4).mapToObj(Course::new).collect(Collectors.toSet());

        Long combinationCount = courseSchedulerService.generateCourseSelections(courses, 3).count();

        // for a set 5 we're expecting to have 25 combination with a maximum range of 3
        Assert.assertEquals(Long.valueOf(25), combinationCount);
        
        //lets run it again to print out all combination
        System.out.println("generateCourseSelectionsTest");
        courseSchedulerService.generateCourseSelections(courses, 3)
                .forEach(System.out::println);
                
    }
    
    
    @Test
    public void generateCourseSelectionsWithPrerequisites() {

        Set<Course> courses = IntStream.rangeClosed(0, 4).mapToObj(Course::new).collect(Collectors.toSet());
        // courses 0 and 1 are excluded 
        Set<CoursePrerequisite> prerequisites =  Sets.newHashSet(CoursePrerequisite.builder().cousreId(0).prerequisiteCourseId(1).build(),
                                                                 CoursePrerequisite.builder().cousreId(1).prerequisiteCourseId(2).build());

        Long combinationCount = courseSchedulerService.generateCourseSelections(courses, prerequisites, 3).count();

        // for a set 5 we're expecting to have 7 combination with a maximum range of 3 as two courses are excluded
        Assert.assertEquals(Long.valueOf(7), combinationCount);
        
        //lets run it again to print out all combination
        System.out.println("generateCourseSelectionsWithPrerequisitesTest");
        courseSchedulerService.generateCourseSelections(courses, prerequisites, 3)
                .forEach(System.out::println);
                
    }
    
    @Test
    public void findUnsatisfiedPrerequisiteCouses_CaseWithLoop() {

        
        // 0->1 1->2 2->0 
        Set<CoursePrerequisite> prerequisites = 
                Sets.newHashSet(CoursePrerequisite.builder().cousreId(0).prerequisiteCourseId(1).build(),
                                CoursePrerequisite.builder().cousreId(1).prerequisiteCourseId(2).build(),
                                CoursePrerequisite.builder().cousreId(2).prerequisiteCourseId(0).build());
        
      boolean hasUnsatisfiedPrerequisite =  courseSchedulerService.hasUnsatisfiedPrerequisiteCouses(prerequisites);

      Assert.assertTrue(hasUnsatisfiedPrerequisite);
                
    }
        
    
    @Test
    public void findUnsatisfiedPrerequisiteCouses_CaseAllSatisfied() {

        
        // 0->1 1->2 2->3 
        Set<CoursePrerequisite> prerequisites = 
                Sets.newHashSet(CoursePrerequisite.builder().cousreId(0).prerequisiteCourseId(1).build(),
                                CoursePrerequisite.builder().cousreId(1).prerequisiteCourseId(2).build(),
                                CoursePrerequisite.builder().cousreId(2).prerequisiteCourseId(3).build());
        
      boolean hasUnsatisfiedPrerequisite =  courseSchedulerService.hasUnsatisfiedPrerequisiteCouses(prerequisites);

      Assert.assertFalse(hasUnsatisfiedPrerequisite);
                
    }

}
