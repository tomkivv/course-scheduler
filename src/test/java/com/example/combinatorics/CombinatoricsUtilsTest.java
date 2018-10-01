package com.example.combinatorics;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

public class CombinatoricsUtilsTest {

    @Test
    public void combinations() {

        /*
         * knowing that number of combinations (subsets) is calculated with
         * formula https://en.wikibooks.org/wiki/Combinatorics/Subsets_of_a_set-The_Binomial_Coefficient n!/k!(n-k)! = 5! / 3!(5-3)! = 120/6*2 = 10
         * we're expecting 10 combination of 3 items for a set of 5 numbers
         * 
         */
        
        Set<Integer> numbers = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toSet());

        Set<Set<Integer>> combinations = CombinatoricsUtils.combinations(numbers, 3).collect(Collectors.toSet());

        Assert.assertNotNull(combinations);
        Assert.assertEquals(10, combinations.size());

        combinations.forEach(System.out::println);

    }

}
