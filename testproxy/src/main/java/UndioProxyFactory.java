import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by mleduc on 30/03/17.
 */
public class UndioProxyFactory {
    public <T> T reroll(Class<?> targetInterface, T obj) {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{
                targetInterface
        }, new MyInvocationHandler(obj));
    }

    private class MyInvocationHandler<T> implements InvocationHandler {
        public final Object obj;

        public MyInvocationHandler(T obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object target2 = obj;
            while (target2 instanceof Proxy) {
                target2 = ((MyInvocationHandler) Proxy.getInvocationHandler(target2)).obj;
            }
            Object invoke = method.invoke(target2, args);

            Object res = unrollProxy(invoke);

            if (res instanceof List) {
                final List<Object> collec = (List) res;
                for (int x = 0; x < collec.size(); x++) {
                    Object target = unrollProxy(collec.get(x));
                    collec.set(x, target);
                }

                return res;

            } else {
                return res;
            }
            /*if(res == null) {
                return res;
            }
            if(res.getClass().getPackage().getName().equals("java.lang")) {
                return res;
            }
            return res;*/
        }
    }

    private Object unrollProxy(Object invoke) throws NoSuchFieldException, IllegalAccessException {
        Object res = invoke;
        while(res instanceof Proxy) {
            res = Proxy.getInvocationHandler(res);
            Field fi = res.getClass().getDeclaredField("obj");
            if(fi != null) {
                res = fi.get(res);
            } else {
                break;
            }

        }
        return res;
    }
}
