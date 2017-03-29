

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mleduc on 29/03/17.
 */
public class GenericProxyFactory {
    /**
     * List of implToIntf that we are allow to convert from one to another
     */
    private final Map<String, String> conversion;


    private final Map<String, String> implToIntf;

    public GenericProxyFactory(final Map<String, String> conversion, final Map<String, String> implToIntf) {
        this.conversion = conversion;
        this.implToIntf = implToIntf;
    }

    public <T> T getFlipFlop(Class<T> targetInterface, Object obj) {
        //System.out.println("new proxy from " + targetInterface + " on object " + obj);
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                new Class[]{targetInterface}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        final String name = method.getName();
                        final Class<?>[] parameterTypes = method.getParameterTypes();
                        final Class<?>[] newp = castParams(parameterTypes);
                        final Method newmethod = obj.getClass().getMethod(name, newp);
                        final Object[] newargs = castArguments(args);
                        final Object res = newmethod.invoke(obj, newargs);
                        final Class<?> returnType = method.getReturnType();
                        if (conversion.containsKey(returnType.getName()) || implToIntf.containsKey(returnType.getName())) {
                            return getFlipFlop(returnType, res);
                        }
                        if (res instanceof Collection) {

                            return ((Collection) res).stream().map(e -> {

                                if (implToIntf.containsKey(e.getClass().getName())) {
                                    String intf = implToIntf.get(e.getClass().getName());
                                    String intf2 = conversion.get(intf);
                                    try {
                                        return getFlipFlop(Class.forName(intf2), e);
                                    } catch (ClassNotFoundException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                return e;


                            }).collect(Collectors.toList());
                        } else {
                            return res;
                        }
                    }

                    private Object[] castArguments(Object[] args) throws ClassNotFoundException {
                        final Object[] newargs;
                        if (args != null) {
                            newargs = new Object[args.length];
                            int j = 0;
                            for (Object x : args) {
                                Package pkg = x.getClass().getPackage();

                                if (implToIntf.containsKey(x.getClass().getName())) {
                                    String itf = implToIntf.get(x.getClass().getName());
                                    String newInterface = conversion.get(itf);
                                    newargs[j] = getFlipFlop(Class.forName(newInterface), x);

                                } else {
                                    newargs[j] = x;
                                }
                                /*if (pkg.getName().startsWith(semanticPackage.getName())) {
                                    final String packageName = syntaxPackage.getName() + ".impl";
                                    final String className = x.getClass().getSimpleName();
                                    final Class newClass = Class.forName(packageName + "." + className);
                                    Class<?> staticType1 = x.getClass().getInterfaces()[0];
                                    newargs[j] = getFlipFlop(newClass.getInterfaces()[0], x);
                                } else {
                                    newargs[j] = x;
                                }*/

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
                });
    }
}
