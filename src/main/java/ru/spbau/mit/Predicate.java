package ru.spbau.mit;

public abstract class Predicate<T> {

    public abstract boolean run(T obj);

    static public Predicate<Object> ALWAYS_TRUE = new Predicate() {
        @Override
        public boolean run(Object obj) {
            return true;
        }
    };

    static public Predicate<Object> ALWAYS_FALSE = new Predicate() {
        @Override
        public boolean run(Object obj) {
            return false;
        }
    };

    public Predicate<T> not() {
        return new Predicate<T>() {
            @Override
            public boolean run(T obj) {
                return !Predicate.this.run(obj);
            }
        };
    }

    public Predicate<T> or(final Predicate<? super T> other) {
        return new Predicate<T>() {
            @Override
            public boolean run(T obj) {
                boolean res = Predicate.this.run(obj);
                return res || other.run(obj);
            }
        };
    }

    public Predicate<T> and(final Predicate<? super T> other) {
        return new Predicate<T>() {
            @Override
            public boolean run(T obj) {
                boolean res = Predicate.this.run(obj);
                return res && other.run(obj);
            }
        };
    }
}
