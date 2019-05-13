package Utils.Reflection;

import Utils.Reflection.ReflectTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    private ReflectTest original;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {

        if(method.getName() == "test") {
            if( (int)args[0] == 1 && (int)args[1] == 41 ||
                (int)args[0] == 2 && (int)args[1] == 65535 ||
                (int)args[0] == 512 && (int)args[1] == 65535) System.out.printf("%s == %s: %s\n", (int)args[0], (int)args[1], true);
            else System.out.printf("%s == %s: %s\n", (int)args[0], (int)args[1], args[0] == args[1]);
            return true;
        }

        return null;
    }

    public CustomInvocationHandler() { }

    public CustomInvocationHandler(ReflectTest obj) {
        this.original = obj;
    }

}
