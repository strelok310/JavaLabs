package Utils.Lambdas;

import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface LoggedFunction<T, R> {
    R apply(T t);

    default Function<T, R> intercept(Consumer<String> logger) {
        return x -> {
            logger.accept("Before: " + x);
            R result = apply(x);
            logger.accept("After: " + result);
            return result;
        };
    }
}
