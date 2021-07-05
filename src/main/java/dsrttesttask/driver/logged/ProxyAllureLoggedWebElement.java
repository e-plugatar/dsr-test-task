package dsrttesttask.driver.logged;

import io.qameta.allure.Allure;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public final class ProxyAllureLoggedWebElement implements InvocationHandler {
    private final WebElement origin;
    private final By[] path;

    public ProxyAllureLoggedWebElement(final WebElement origin, final By... path) {
        this.origin = origin;
        this.path = path;
    }

    public static WebElement of(final WebElement webElement, final By... path) {
        return (WebElement) Proxy.newProxyInstance(
                webElement.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(webElement.getClass()).toArray(new Class[0]),
                new ProxyAllureLoggedWebElement(webElement, path)
        );
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return Allure.step(stepContext -> {
            stepContext.name("element" + ArrayUtils.toString(this.path) + "." + method.getName()
                    + ArrayUtils.toString(args));
            final Object result;
            try {
                result = method.invoke(this.origin, args);
            } catch (final InvocationTargetException ex) {
                throw ex.getTargetException();
            }
            switch (method.getName()) {
                case "findElement": {
                    final Class<?>[] parametersTypes = method.getParameterTypes();
                    if (parametersTypes.length == 1 && parametersTypes[0] == By.class) {
                        return new ProxyAllureLoggedWebElement(
                                (WebElement) result,
                                ArrayUtils.add(this.path, (By) args[0])
                        );
                    }
                    break;
                }
                case "findElements": {
                    final Class<?>[] parametersTypes = method.getParameterTypes();
                    if (parametersTypes.length == 1 && parametersTypes[0] == By.class) {
                        @SuppressWarnings("unchecked") final List<WebElement> elements = (List<WebElement>) result;
                        if (elements.isEmpty()) {
                            return elements;
                        }
                        final By[] path = ArrayUtils.add(this.path, (By) args[0]);
                        final List<WebElement> wrappedElements = new ArrayList<>(elements.size());
                        for (final WebElement element : elements) {
                            wrappedElements.add(ProxyAllureLoggedWebElement.of(element, path));
                        }
                        return wrappedElements;
                    }
                    break;
                }
            }
            return result;
        });
    }
}
