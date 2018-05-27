package ru.spbau.mit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class Collections {

    public static <A, R> Collection<R> map(Function1<? super A, ? extends R> func,
                                           Collection<? extends A> input) {
        Collection<R> output = new ArrayList<>();
        for (A each : input) {
            output.add(func.run(each));
        }
        return output;
    }

    public static <A> Collection<A> filter(Predicate<? super A> pred, Collection<? extends A> input) {
        Collection<A> output = new ArrayList<>();
        for (A each : input) {
            if (pred.run(each)) {
                output.add(each);
            }
        }
        return output;
    }

    public static <A> Collection<A> takeWhile(Predicate<? super A> pred, Collection<? extends A> input) {
        Collection<A> output = new ArrayList<>();
        Predicate<? super  A> not = pred.not();
        for (A each : input) {
            if (not.run(each)) {
                break;
            }
            output.add(each);
        }
        return output;
    }

    public static <A> Collection<A> takeUnless(Predicate<? super A> pred, Collection<? extends A> input) {
        return takeWhile(pred.not(), input);
    }

    public static <A, R> R foldr(Function2<? super A, ? super R, ? extends R> func,
                                 Collection<? extends A> input,
                                 R init) {
        List<A> elem = new ArrayList<>();
        elem.addAll(input);
        ListIterator<A> it = elem.listIterator();
        while (it.hasNext()){
            it.next();
        }
        while (it.hasPrevious()) {
            init = func.run(it.previous(), init);
        }
        return init;
    }

    public static <A, R> R foldl(Function2<? super R, ? super A, ? extends R> func,
                                 Collection<? extends A> input,
                                 R init) {
        for (A each : input) {
            init = func.run(init, each);
        }
        return init;
    }
}
