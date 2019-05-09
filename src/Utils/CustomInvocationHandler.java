package Utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private ReflectTest original;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {

        if(method.getName() == "test") {
            System.out.printf("%s == %s: %s\n", (int)args[0], (int)args[1], true);
            return true;
        }

        return null;
    }

    public CustomInvocationHandler() { }

    public CustomInvocationHandler(ReflectTest obj) {
        this.original = obj;
    }

}
