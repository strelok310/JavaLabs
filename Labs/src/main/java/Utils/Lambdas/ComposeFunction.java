package Utils.Lambdas;

import java.util.function.Function;

@FunctionalInterface
public interface ComposeFunction<T> {
    Function<T,T> compose(Function<T,T> a, Function<T,T> b);
}
