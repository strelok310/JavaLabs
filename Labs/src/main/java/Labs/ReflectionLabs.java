package Labs;

import Utils.Reflection.CustomInvocationHandler;
import Utils.Reflection.ReflectTest;
import Utils.Reflection.ReflectionTestInterface;

import javax.naming.spi.DirectoryManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Scanner;

public class ReflectionLabs {
    public static void main(String args[]) throws Exception {
        try {
            //Scanner in = new Scanner(new FileReader("class.txt"));
            //Scanner in = new Scanner(new FileReader(ClassLoader.getSystemClassLoader().getResource("class.txt").getFile()));
            Scanner in = new Scanner(new FileReader(ClassLoader.getSystemResource("class.txt").getFile()));

            String className = in.nextLine();
            String fieldName = in.nextLine();
            String methodName = in.nextLine();
            String constructorParamTypesString = in.nextLine();
            String constructorParamsValuesString = in.nextLine();
            String privateMethod = in.nextLine();
            String classKeyName = in.nextLine();
            String classValueName = in.nextLine();
            String KeyStr = in.nextLine();
            String ValueStr = in.nextLine();

            //==========================================================================================================

            Class<?> obj = Class.forName(className);

            Method[] methods = obj.getDeclaredMethods();
            Field[] fields = obj.getDeclaredFields();
            Constructor[] constructors = obj.getDeclaredConstructors();

            int mod = obj.getModifiers();
            System.out.println("Class modifier: " + Modifier.toString(mod));
            System.out.println("Superclass: " + obj.getSuperclass());

            System.out.println("Constructors:");
            for(Constructor item : constructors) {
                System.out.println("\t" + item);
            }
            System.out.println("Methods:");
            for(Method item : methods) {
                System.out.println("\t" + item);
                mod = item.getModifiers();
            }
            System.out.println("Fields:");
            for(Field item : fields) {
                System.out.println("\t" + item);
                mod = item.getModifiers();
            }

            //==========================================================================================================

            //Instance of the unknown class
            System.out.println("\n");
            System.out.println("Unknown class\n");
            Class<?> unknownClass = Class.forName(className);
            Object unknownObject = unknownClass.getConstructor().newInstance();

            //==========================================================================================================

            //Private field access
            System.out.println("Field access:");
            Field name = ReflectTest.class.getDeclaredField(fieldName);
            name.setAccessible(true);
            System.out.println("Old value: " + name.get(unknownObject));
            name.set(unknownObject, "Darth Vader");
            System.out.println("New value: " + name.get(unknownObject));
            name.setAccessible(false);

            //==========================================================================================================

            //Private method invocation
            System.out.println("\n");
            System.out.println("Method access:");
            Method method =  unknownClass.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(unknownObject);
            method.setAccessible(false);

            //==========================================================================================================

            //Print fields and methods
            System.out.println("\n");
            System.out.println("Public methods");
            for(Method item : unknownClass.getDeclaredMethods()) {
                mod = item.getModifiers();
                if(Modifier.isPublic(mod)) System.out.println("\t" + item);
            }
            System.out.println("Private methods");
            for(Method item : unknownClass.getDeclaredMethods()) {
                mod = item.getModifiers();
                if(Modifier.isPrivate(mod)) System.out.println("\t" + item);
            }
            System.out.println("Public fields");
            for(Field item : unknownClass.getDeclaredFields()) {
                mod = item.getModifiers();
                if(Modifier.isPublic(mod)) System.out.println("\t" + item);
            }
            System.out.println("Private fields");
            for(Field item : unknownClass.getDeclaredFields()) {
                mod = item.getModifiers();
                if(Modifier.isPrivate(mod)) System.out.println("\t" + item);
            }

            //==========================================================================================================

            System.out.println("\n");
            System.out.println("Private constructor");

            String paramString[] = constructorParamTypesString.split(" ");
            Class<?> constructorParams[] = new Class<?>[paramString.length];
            String constructorParamsValues[] = constructorParamsValuesString.split(" ");
            Object paramValues[] = new Object[constructorParamsValues.length];

            for(int i = 0; i < paramString.length; i++) {
                constructorParams[i] = Class.forName(paramString[i]);

                if(constructorParams[i] == Integer.class) paramValues[i] = Integer.parseInt(constructorParamsValues[i]);
                else if(constructorParams[i] == Float.class) paramValues[i] = Float.parseFloat(constructorParamsValues[i]);
                else paramValues[i] = constructorParamsValues[i];
            }

            Constructor privateConstructor = unknownClass.getDeclaredConstructor(constructorParams);
            mod = privateConstructor.getModifiers();
            if(Modifier.isPrivate(mod)) {
                privateConstructor.setAccessible(true);
                Object unknownPrivate = privateConstructor.newInstance(paramValues);
                privateConstructor.setAccessible(false);

                method = unknownClass.getDeclaredMethod(methodName);
                method.setAccessible(true);
                method.invoke(unknownPrivate);
                method.setAccessible(false);
            }

            //==========================================================================================================

            System.out.println("\n");
            System.out.println("Calling private method with parameter");
            method = unknownClass.getDeclaredMethod(privateMethod, int.class);
            method.setAccessible(true);
            System.out.println("Result: " + method.invoke(unknownObject, 15));
            method.setAccessible(false);

            //==========================================================================================================

            System.out.println("\n");
            System.out.println("Interface methods:");
            Class<?>[] interfaces = unknownClass.getInterfaces();

            for(Class<?> item : interfaces) {
                for(Method met : item.getDeclaredMethods()) {
                    System.out.println("\t" + met);
                }
            }

            //==========================================================================================================

            System.out.println("\n");
            System.out.println("Creating HashMap with unknown types and values");
            HashMap asd = HashMap.class.newInstance();

            Class<?> classKey = Class.forName(classKeyName);
            Class<?> classValue = Class.forName(classValueName);
            Object key = null;
            Object value = null;

            if(classKey == Integer.class) key = Integer.parseInt(KeyStr);
            else if(classKey == Float.class) key = Float.parseFloat(KeyStr);
            else key = KeyStr;

            if(classValue == Integer.class) value = Integer.parseInt(KeyStr);
            else if(classValue == Float.class) value = Float.parseFloat(KeyStr);
            else value = ValueStr;

            asd.put(classKey.getConstructor(int.class).newInstance(classKey.cast(key)),
                    classValue.getConstructor(String.class).newInstance(classValue.cast(value)));

            System.out.println(asd);

            //==========================================================================================================

            System.out.println("Method override");

            ReflectTest testObjectProxy = new ReflectTest();
            ClassLoader classLoaderProxy = testObjectProxy.getClass().getClassLoader();
            Class<?>[] interfacesProxy= testObjectProxy.getClass().getInterfaces();
            CustomInvocationHandler invocationHandler = new CustomInvocationHandler(testObjectProxy);

            ReflectionTestInterface proxy = (ReflectionTestInterface) Proxy.newProxyInstance(classLoaderProxy, interfacesProxy, invocationHandler);

            proxy.test(1,41);
            proxy.test(2,65535);
            proxy.test(512,65535);

        }
        catch (ClassNotFoundException e) { System.out.println(e); }
        catch (FileNotFoundException e) { System.out.println(e); }
        catch(IllegalAccessException e) { System.out.println(e); }
        catch (NoSuchFieldException e) { System.out.println(e); }
        catch (NoSuchMethodException e) { System.out.println(e); }
        catch (InvocationTargetException | InstantiationException e) { System.out.println(e); }
    }
}
