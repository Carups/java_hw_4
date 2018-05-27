package ru.spbau.mit;

public abstract class Function1<F, R> {
    public abstract R run(F first);

    public <G> Function1<F, G> compose(final Function1<? super R, G> g) {
        return new Function1<F, G>() {
            public G run(F first) {
                return g.run(Function1.this.run(first));
            }
        };
    }
}
