package ru.spbau.mit;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class CollectionsTest {
    class SQ extends Function1<Integer, Integer> {
        public Integer run(Integer input){
            return input * input;
        }
    }
    SQ sq = new SQ();

    @Test
    public void map() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        Collection<Integer> out = Collections.map(sq, test);
        Iterator<Integer> it = out.iterator();
        for (int i = 0; i < 10; i++) {
            int tmp = it.next();
            assertEquals(tmp, i * i);
        }
    }
    class Even extends Predicate<Integer>{
        public boolean run(Integer x){
            return  x % 2 == 0;
        }
    }

    Even even = new Even();

    @Test
    public void filter() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        Collection<Integer> out = Collections.filter(even, test);
        Iterator<Integer> it = out.iterator();
        for (int i = 0; i < 10; i += 2) {
            int tmp = it.next();
            assertEquals(tmp, i);
        }
    }

    class LessFive extends Predicate<Integer>{
        public boolean run(Integer x){
            return  x < 5;
        }
    }

    LessFive lessFive = new LessFive();

    @Test
    public void takeWhile() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        Collection<Integer> out = Collections.takeWhile(lessFive, test);
        Iterator<Integer> it = out.iterator();
        for (int i = 0; i < 5; i++) {
            int tmp = it.next();
            assertEquals(tmp, i);
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void takeUnless() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        Collection<Integer> out = Collections.takeUnless(lessFive, test);
        Iterator<Integer> it = out.iterator();
        assertFalse(it.hasNext());
    }

    Function2Test.Plus plus = new Function2Test.Plus();
    Function2Test.Minus minus = new Function2Test.Minus();

    @Test
    public void foldr() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        int out1 = Collections.foldr(plus, test, 0);
        int out2 = Collections.foldr(minus, test, 0);
        assertEquals(45, out1);

        assertEquals(out2, -5);

    }

    @Test
    public void foldl() throws Exception {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            test.add(i);
        }
        int out1 = Collections.foldl(plus, test, 0);
        int out2 = Collections.foldl(minus, test, 0);
        assertEquals(out1, 45);
        assertEquals(out2, -45);
    }

}