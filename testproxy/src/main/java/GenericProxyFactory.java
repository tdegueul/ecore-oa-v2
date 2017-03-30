

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mleduc on 29/03/17.
 */
public class GenericProxyFactory {
    /**
     * List of implToIntf that we are allow to convert from one to another
     */
    private final Map<String, String> conversion;


    private final Map<String, String> implToIntf;

    private Map<Object, Object> memoized = new HashMap<>();

    public GenericProxyFactory(final Map<String, String> conversion, final Map<String, String> implToIntf) {
        this.conversion = conversion;
        this.implToIntf = implToIntf;
    }

    public <T> T getFlipFlop(Class<T> targetInterface, Object obj) {
        if (obj == null) {
            return null;
        }

        if (memoized.containsKey(obj)) {
            //System.out.println("Cache hit for " + obj);
            return (T) memoized.get(obj);
        } else {
            //System.out.println("new proxy for " + targetInterface + " on object " + obj);
            Object o = Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                    new Class[]{targetInterface}, new MyInvocationHandler(obj));
            memoized.put(obj, o);
            return (T) o;
        }
    }

    public class MyInvocationHandler implements InvocationHandler {
        public final Object obj;

        public MyInvocationHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final String name = method.getName();
            final Class<?>[] parameterTypes = method.getParameterTypes();
            final Class<?>[] newp = castParams(parameterTypes);
            final Object[] newargs = castArguments(args, newp);
            final Method newmethod = obj.getClass().getMethod(name, newp);
            //System.out.println(">> Invoke Proxy " + proxy.getClass() + "method: " + method + " / args: " + args);
            final Object res = newmethod.invoke(obj, newargs);
            return convertReturnType(method, res);
        }

        private Object convertReturnType(Method method, Object res) throws ClassNotFoundException {
            if (res == null) {
                return null;
            }
            if (res instanceof List) {
                final List<Object> collec = (List) res;
                for (int x = 0; x < collec.size(); x++) {
                    final Object currentElem = collec.get(x);
                    final Object ele;


                    final Object target;
                    if (currentElem instanceof Proxy) {
                        target = ((MyInvocationHandler) Proxy.getInvocationHandler(currentElem)).obj;
                    } else {
                        target = currentElem;
                    }

                    final String className = target.getClass().getName();
                    if (implToIntf.containsKey(className)) {
                        final String itf = implToIntf.get(className);
                        final String newInterface = conversion.get(itf);
                        final Class<?> clazz = Class.forName(newInterface);
                        ele = getFlipFlop(clazz, currentElem);
                    } else {
                        ele = currentElem;
                    }
                    collec.set(x, ele);
                }

                return res;

            } else {

                Object target = res;
                while(target instanceof Proxy) {
                    target = ((MyInvocationHandler) Proxy.getInvocationHandler(target)).obj;
                }

                if (implToIntf.containsKey(target.getClass().getName())) {
                    final String inter = implToIntf.get(target.getClass().getName());
                    final String newInter = conversion.get(inter);
                    return getFlipFlop(Class.forName(newInter), target);
                } else {
                    return res;
                }
            }
        }

        private Object[] castArguments(Object[] args, Class<?>[] newp) throws ClassNotFoundException {
            final Object[] newargs;
            if (args != null) {
                newargs = new Object[args.length];
                int j = 0;
                for (Object x : args) {

                    // unwrapping proxies.
                    Object target = x;
                    while(target instanceof Proxy) {
                        target = ((MyInvocationHandler) Proxy.getInvocationHandler(target)).obj;
                    }



                    final String className = target.getClass().getName();

                    if (newp[j].isAssignableFrom(target.getClass())) {
                        newargs[j] = target;
                    } else if (implToIntf.containsKey(className)) {
                        final String itf = implToIntf.get(className);
                        final String newInterface = conversion.get(itf);
                        final Class<?> clazz = Class.forName(newInterface);
                        newargs[j] = getFlipFlop(clazz, target);
                    } else {
                        newargs[j] = x;
                    }


                    j++;
                }
            } else {
                newargs = null;
            }
            return newargs;
        }

        private Class<?>[] castParams(Class<?>[] parameterTypes) throws ClassNotFoundException {
            final Class<?>[] newp = new Class<?>[parameterTypes.length];
            int i = 0;
            for (Class<?> x : parameterTypes) {
                if (conversion.containsKey(x.getName())) {
                    final String newName = conversion.get(x.getName());
                    newp[i] = Class.forName(newName);
                } else {
                    newp[i] = x;
                }
                /*if ( x.getPackage().equals(semanticPackage)) {
                    final String packageName = syntaxPackage.getName();
                    final String className = x.getSimpleName();
                    final Class<?> newClass = Class.forName(packageName + "." + className);
                    newp[i] = newClass;
                } else {
                    newp[i] = x;
                }*/

                i++;
            }
            return newp;
        }
    }
}
