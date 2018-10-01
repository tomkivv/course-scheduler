package com.example.combinatorics;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

public class CombinatoricsUtils {

    /**
     * Returns all the possible combinations of the set in a stream.
     *
     * @param elements
     * @param subsetSize
     * @param <T>
     * @return
     */
    public static <T> Stream<Set<T>> combinations(Set<T> elements, Integer subsetSize) {

        if (subsetSize == 0) {
            return Stream.of(Sets.newHashSet());
        } else if (subsetSize <= elements.size()) {

            Set<T> remainingElements = elements;

            Iterator<T> iterator = remainingElements.iterator();
            T current = iterator.next();
            iterator.remove();

            Stream<Set<T>> combinations = Stream.concat(combinations(remainingElements, subsetSize),

            combinations(remainingElements, subsetSize - 1).map(e ->
            {
                e.add(current);
                return e;
            }));

            remainingElements.add(current);

            return combinations;
        } else {
            return Stream.empty();
        }
    }

}
