package TP1;
@FunctionalInterface
public interface CheckedConsumer<T> {
   void accept(T t) throws Exception;
}
