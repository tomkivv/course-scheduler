package com.example.service;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.example.combinatorics.CombinatoricsUtils;
import com.example.model.Course;
import com.example.model.CoursePrerequisite;
import com.google.common.collect.Sets;

public class CourseSchedulerServiceImpl implements CourseSchedulerService {

    @Override
    public Stream<Set<Course>> generateCourseSelections(Set<Course> courses, Integer maxCourses) {

        return IntStream.rangeClosed(MINIMAL_COURSES_IN_SELECTION, maxCourses)
                .mapToObj(combinationLenght -> CombinatoricsUtils.combinations(courses, combinationLenght))
                .flatMap(it -> it);
    }

    @Override
    public Stream<Set<Course>> generateCourseSelections(Set<Course> courses, Set<CoursePrerequisite> prerequisites,
            Integer maxCourses) {

        Predicate<Course> filterOutPrerequisites = c -> prerequisites.stream()
                .map(CoursePrerequisite::getCousreId)
                .allMatch(prerequisiteId -> !c.getId().equals(prerequisiteId));

        Set<Course> availableCourses = courses.stream().filter(filterOutPrerequisites).collect(Collectors.toSet());

        return this.generateCourseSelections(availableCourses, maxCourses);
    }

    @Override
    public Boolean hasUnsatisfiedPrerequisiteCouses(Set<CoursePrerequisite> prerequisites) {

        return prerequisites.stream().allMatch(findUnsatisfiedPrerequisite(prerequisites));

    }

    private Predicate<CoursePrerequisite> findUnsatisfiedPrerequisite(Set<CoursePrerequisite> prerequisites) {

        return p -> findPrerequisitesChainForPrerequisite(p, prerequisites, Sets.newLinkedHashSet()).stream()
                .anyMatch(pc -> pc.getCousreId().equals(p.getPrerequisiteCourseId()));

    }

    private Set<CoursePrerequisite> findPrerequisitesChainForPrerequisite(CoursePrerequisite currentPrerequisite,
            Set<CoursePrerequisite> prerequisites, Set<CoursePrerequisite> prerequisitesChain) {

        if (prerequisitesChain.size() < prerequisites.size()) {

            Predicate<CoursePrerequisite> hasCourse = cp -> currentPrerequisite.getPrerequisiteCourseId()
                    .equals(cp.getCousreId());

            prerequisites.stream().filter(hasCourse).findAny().ifPresent(nextPrerequisite -> {
                prerequisitesChain.add(nextPrerequisite);
                findPrerequisitesChainForPrerequisite(nextPrerequisite, prerequisites, prerequisitesChain);
            });

        }

        return prerequisitesChain;

    }

}
