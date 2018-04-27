package ru.spbau.mit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Function2Test {
    static class Plus extends Function2<Integer, Integer, Integer>{
        public Integer run(Integer f, Integer s){
            return f + s;
        }
    }

    static class Minus extends Function2<Integer, Integer, Integer>{
        public Integer run(Integer f, Integer s){
            return f - s;
        }
    }
    static class Magic extends Function2<Double, String, Integer>{
        public Integer run(Double x, String y){
            return 42;
        }
    }

    Plus plus = new Plus();
    Minus minus = new Minus();
    Magic magic = new Magic();
    @Test
    public void testRun(){
        assertTrue(42 == magic.run(2.0, "asdf"));
        assertTrue(5 == minus.run(2, -3));
        assertTrue(5 == plus.run(2, 3));
    }


    @Test
    public void testBind() {
        Function1<Integer, Integer> plus1 = plus.bind1(1);
        Function1<Integer, Integer> minus3 = minus.bind2(3);

        assertTrue(5 == plus1.run(4));
        assertTrue(5 == minus3.run(8));
        assertTrue(-6 == minus3.run(-3));

    }

    @Test
    public void testCurry() {
        Function1<Integer, Function1<Integer, Integer>> curr = plus.curry();
        assertEquals(5, curr.run(2).run(3).intValue());
        assertEquals(1, curr.run(-2).run(3).intValue());
    }

    class SQ extends Function1<Integer, Integer> {
        public Integer run(Integer input){
            return input * input;
        }
    }
    SQ sq = new SQ();

    @Test
    public void testCompose() {
        Function2<Integer, Integer, Integer> sqplus = plus.compose(sq);
        Function2<Integer, Integer, Integer> sqminus = minus.compose(sq);

        assertTrue(1 == sqminus.run(2, 3));
        assertTrue(1 == sqminus.run(3, 2));
        assertTrue(25 == sqplus.run(3, 2));
    }
}
