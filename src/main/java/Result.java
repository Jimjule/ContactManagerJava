public interface Result<V, E> {

    static<V, E> Result<V, E> ok(final V value) {
        return new Ok<V, E>(value);
    }

    static<V, E> Result<V, E> error(final E error) {
        return new Err<V, E>(error);
    }

    V getValue();

    E getError();

    boolean isOk();
    boolean isError();

    static class Ok<V, E> implements Result<V, E> {
        private final V value;

        public Ok(final V value) {
            this.value = value;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public E getError() {
            throw new RuntimeException("None of that");
        }

        @Override
        public boolean isOk() {
            return true;
        }

        @Override
        public boolean isError() {
            return false;
        }
    }

    static class Err<V, E> implements Result<V, E> {
        private E exception;

        public Err(final E exception) {
            this.exception = exception;
        }

        @Override
        public V getValue() {
            throw new RuntimeException("Oops");
        }

        @Override
        public E getError() {
            return exception;
        }

        @Override
        public boolean isOk() {
            return false;
        }

        @Override
        public boolean isError() {
            return true;
        }
    }
}
