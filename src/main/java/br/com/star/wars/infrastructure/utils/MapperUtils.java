package br.com.star.wars.infrastructure.utils;

import org.springframework.beans.BeanUtils;

import java.util.function.Consumer;

public class MapperUtils {

    public static <S, T> T map(final S source, final Class<T> classTarget) {
        final T target = createInstanceOf(classTarget);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <S, T> T map(final S source, final Class<T> classTarget, final Consumer<T> postMap) {
        final T target = map(source, classTarget);
        postMap.accept(target);
        return target;
    }

    private static <T> T createInstanceOf(final Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
