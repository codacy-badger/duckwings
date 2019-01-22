package org.jduck;


import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

public class JDuck<T, I> {
    public static WrapperBuilder builder() {
        return new WrapperBuilder();
    }

    public static class WrapperBuilder {
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<Function<Method, Throwable>> constructionFailure = Optional.empty();
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private Optional<Function<Method, Throwable>> runtimeFailure = Optional.empty();


        public WrapperBuilder throwIfAbsentDuringBuilding(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Function<Method, Throwable>> constructionFailure) {
            this.constructionFailure = constructionFailure;
            return this;
        }

        public WrapperBuilder throwIfAbsentAtRuntime(@SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Function<Method, Throwable>> runtimeFailure) {
            this.runtimeFailure = runtimeFailure;
            return this;
        }

        public <T, I> FunctionalWrapper<T, I> functional(Class<I> faceType, Class<T> targetType) {
            return new FunctionalWrapper<>(faceType, constructionFailure, runtimeFailure);
        }

        public <T, I> Wrapper<T, I> reflect(Class<I> faceType) {
            return new ReflectionalWrapper<>(faceType, constructionFailure, runtimeFailure);
        }
    }
}
