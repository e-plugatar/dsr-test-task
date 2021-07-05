package dsrttesttask.driver.logged;

import io.qameta.allure.Allure;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class ProxyAllureLoggedNavigation implements InvocationHandler {
    private final WebDriver.Navigation origin;

    public ProxyAllureLoggedNavigation(final WebDriver.Navigation origin) {
        this.origin = origin;
    }

    public static WebDriver.Navigation of(final WebDriver.Navigation navigation) {
        return (WebDriver.Navigation) Proxy.newProxyInstance(
                navigation.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(navigation.getClass()).toArray(new Class[0]),
                new ProxyAllureLoggedNavigation(navigation)
        );
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return Allure.step(stepContext -> {
            stepContext.name("navigation." + method.getName() + ArrayUtils.toString(args));
            final Object result;
            try {
                result = method.invoke(this.origin, args);
            } catch (final InvocationTargetException ex) {
                throw ex.getTargetException();
            }
            return result;
        });
    }
}
