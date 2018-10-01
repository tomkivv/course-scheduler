package com.example.service;

import java.util.Set;
import java.util.stream.Stream;

import com.example.model.Course;
import com.example.model.CoursePrerequisite;

public interface CourseSchedulerService {
    
    static final Integer MINIMAL_COURSES_IN_SELECTION = 1;

    /**
     * Generate a possible course combination for the given courses, one is a
     * minimal number of courses in a possible course selection
     * 
     * @param courses
     *            - listed courses
     * @param maxCourses
     *            - a maximum number of courses in a combination
     * @return
     */
    Stream<Set<Course>> generateCourseSelections(Set<Course> courses, Integer maxCourses);

    /**
     * Generate a possible course combination for the given courses considering
     * prerequisites courses, one is a minimal number of courses in a possible
     * course selection
     * 
     * @param courses
     *            - listed courses
     * @param prerequisites
     *            - set of pairs containing course and it's prerequisite
     * @param maxCourses
     *            - a maximum number of courses in a combination
     * @return
     */
    Stream<Set<Course>> generateCourseSelections(Set<Course> courses, Set<CoursePrerequisite> prerequisites,
            Integer maxCourses);

    /**
     * Check if there are​ ​prerequisite courses​ that ​can't​ ​be​ ​satisfied​ ​, a course
     * that does ​not​ ​have​ ​any prerequisites​ ​or​ ​its​ ​prerequisites​
     * ​can​ ​be​ ​satisfied.
     * 
     * @param prerequisites
     *            - set of pairs containing course and it's prerequisite
     * 
     * @return
     */
    Boolean hasUnsatisfiedPrerequisiteCouses(Set<CoursePrerequisite> prerequisites);
}
