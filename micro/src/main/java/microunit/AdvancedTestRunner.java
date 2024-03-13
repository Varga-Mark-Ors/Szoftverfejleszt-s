package microunit;

import example.ExampleJava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdvancedTestRunner {


    protected void invokeTestMethod(Method testMethod, Object instance, Result result) throws IllegalAccessException {
        Test testAnnotation = testMethod.getAnnotation(Test.class);
        try {
            testMethod.invoke(instance);
            if(testAnnotation.expected() != Test.None.class){
                throw new InvocationTargetException(
                        new AssertionError(testAnnotation.expected() + " is not thrown.")
                );
            }
            result.onSucces();
        }
        catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            cause.printStackTrace(System.out);
            if (cause instanceof AssertionError) {
                result.onFailures();
            }
            else {
                result.onError();
            }
        }
    }

    public static void main(String[] args) {
        new BasicTestRunner(ExampleJava.class).runTestMethods();
    }
}
