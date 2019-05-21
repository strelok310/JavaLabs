package Utils.Lambdas;

@FunctionalInterface
public interface FiveFunction<T> {
    T apply(T x, T y, T z, T u, T v);
}
