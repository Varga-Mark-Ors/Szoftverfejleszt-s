package microunit;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class TestRunner {
    protected final Class<?> testClass;

    public TestRunner(Class<?> testClass) {
        this.testClass = testClass;
    }

    protected List<Method> getAnnotatedMethods() {
        return Arrays.stream(testClass.getDeclaredMethods()).filter(method ->
                method.isAnnotationPresent(Test.class)).toList();
    }

    protected abstract void invokeTestMethod(
            Method testMethod,
            Object instance,
            Result result
    ) throws IllegalAccessException;

    public void runTestMethods() {
        try {
            Result result = new Result();
            for (Method method : getAnnotatedMethods()) {
                System.out.println(method);
                Object instance = testClass.getConstructor()
                        .newInstance();
                invokeTestMethod(method, instance, result);
            }
            System.out.println(result);
        }
        catch (ReflectiveOperationException | IllegalArgumentException e) {
            throw new InvalidTestClassException(e);
        }
    }
}
