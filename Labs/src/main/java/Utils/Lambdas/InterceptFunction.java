package Utils.Lambdas;

@FunctionalInterface
public interface InterceptFunction<T, R> {
    T intercept(T t, R r);
}
