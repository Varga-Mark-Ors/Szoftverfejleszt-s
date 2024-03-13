package microunit;

public class Assert {
    public static void fail(String message){
        if(message!=null){
            throw new AssertionError(message);
        }
        throw new AssertionError();
    }

    public static void fail(){
        fail(null);
    }

    public static void assertTrue(boolean condition){
        //if (!condition){
        //    fail();
        //}
        assertTrue(condition, null);
    }

    public static void assertTrue(boolean condition, String message){
        if (!condition){
            fail(message);
        }
    }
}
