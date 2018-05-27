package ru.spbau.mit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PredicateTest {
    class One extends Predicate<String>{
        public boolean run(String in){
            return  in.length() == 1;
        }
    }
    One one = new One();

    class Two extends Predicate<String>{
        public boolean run(String in){
            return  in.length() == 2;
        }
    }
    Two two = new Two();

    class Even extends Predicate<Integer>{
        public boolean run(Integer x){
            return  x % 2 == 0;
        }
    }

    Even even = new Even();

    class LessFive extends Predicate<Integer>{
        public boolean run(Integer x){
            return  x < 5;
        }
    }

    LessFive lessFive = new LessFive();

    @Test
    public void testRun() {
        assertEquals(true, even.run(2));
        assertEquals(false, even.run(3));
        assertEquals(true, one.run("a"));
        assertEquals(false, one.run("abacaba"));

    }

    @Test
    public void testNot() {
        Predicate<Integer> notEven = even.not();
        Predicate<String> notOne = one.not();
        assertNotEquals(true, notEven.run(2));
        assertNotEquals(false, notEven.run(3));
        assertNotEquals(true, notOne.run("a"));
        assertNotEquals(false, notOne.run("abacaba"));

    }

    @Test
    public void testAnd() {
        Predicate<Integer> pred = lessFive.and(even);
        assertEquals(true, pred.run(4));
        assertEquals(false, pred.run(9));
        assertEquals(false, pred.run(3));
        assertEquals(false, pred.run(10));
    }

    @Test
    public void testOr() {
        Predicate<String> pred = one.or(two);
        assertEquals(true, pred.run("a"));
        assertEquals(true, pred.run("ab"));
        assertEquals(false, pred.run("abc"));
        assertEquals(false, pred.run("abcd"));
    }

    class Problem extends Predicate<Object>{
        public boolean run(Object in){
            throw new NullPointerException();
        }
    }
    Problem problem = new Problem();

    @Test
    public void testPredicateLaziness() {
        Predicate<Object> lazyTrue = Predicate.ALWAYS_TRUE.or(problem);
        assertEquals(true, lazyTrue.run(1));
        Predicate<Object> lazyFalse = Predicate.ALWAYS_FALSE.and(problem);
        assertEquals(false, lazyFalse.run(1));
    }
}
