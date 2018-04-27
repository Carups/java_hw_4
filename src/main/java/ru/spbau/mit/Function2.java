package ru.spbau.mit;

public abstract class Function2<F, S, R> {
    public abstract R run(F first, S second);

    public <G> Function2<F, S, G> compose(final Function1<? super R, G> g) {
        return new Function2<F, S, G>() {
            public G run(F first, S second) {
                return g.run(Function2.this.run(first, second));
            }
        };
    }

    public Function1<S, R> bind1(final F first) {
        return new Function1<S, R>() {
            public R run(S second) {
                return Function2.this.run(first, second);
            }
        };
    }

    public Function1<F, R> bind2(final S second) {
        return new Function1<F, R>() {
            public R run(F first) {
                return Function2.this.run(first, second);
            }
        };
    }


    public Function1<F, Function1<S,R>> curry() {
        return new Function1<F, Function1<S,R>>() {
            public Function1<S,R> run(F first) {
                return Function2.this.bind1(first);
            }
        };
    }
}
