package Utils.Lambdas;

import java.util.Objects;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface TernaryFunction<T> {
    T apply(T a, T b, T c);

    default TernaryFunction<T> andThen(UnaryOperator<T> after) {
        Objects.requireNonNull(after);
        return (T a, T b, T c) -> after.apply(apply(a, b, c));
    }
}