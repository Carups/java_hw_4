package ru.spbau.mit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Function1Test {

    class SQ extends Function1<Integer, Integer> {
        public Integer run(Integer input){
            return input * input;
        }
    }
    SQ sq = new SQ();
    class Inc extends Function1<Integer, Integer> {
        public Integer run(Integer input){
            return input + 1;
        }
    }


    Inc inc = new Inc();
    @Test
    public void testRun() {
        assertEquals(100, (long) sq.run(10));
        assertEquals(100, (long) sq.run(-10));
    }

    @Test
    public void testCompose() {
        Function1<Integer, Integer> tmp = sq.compose(inc);
        assertEquals(5, (long) tmp.run(2));
        tmp = inc.compose(sq);
        assertEquals(9, (long) tmp.run(2));
    }
}
