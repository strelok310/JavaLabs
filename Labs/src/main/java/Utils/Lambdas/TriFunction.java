package Utils.Lambdas;

import java.util.Objects;
import java.util.function.Function;

/*@FunctionalInterface
public interface TernaryFunction<T> {
    T apply(T t, T u, T r);

    default <T> TernaryFunction<T> andThen(UnaryOperator<? super T> after) {
        Objects.requireNonNull(after);
        return (T t, T u, T r) -> after.apply(apply(t, u ,r));
    }
}*/

@FunctionalInterface
public interface TriFunction<T, U, W, R> {
    R apply(T t, U u, W w);

    default <V> TriFunction<T, U, W, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, W w) -> after.apply(apply(t, u, w));
    }
}
