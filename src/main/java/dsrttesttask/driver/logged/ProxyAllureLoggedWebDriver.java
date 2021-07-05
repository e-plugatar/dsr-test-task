package dsrttesttask.driver.logged;

import io.qameta.allure.Allure;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public final class ProxyAllureLoggedWebDriver implements InvocationHandler {
    private final WebDriver origin;

    public ProxyAllureLoggedWebDriver(final WebDriver origin) {
        this.origin = origin;
    }

    public static WebDriver of(final WebDriver webDriver) {
        return (WebDriver) Proxy.newProxyInstance(
                webDriver.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(webDriver.getClass()).toArray(new Class[0]),
                new ProxyAllureLoggedWebDriver(webDriver)
        );
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return Allure.step(stepContext -> {
            stepContext.name("driver." + method.getName() + ArrayUtils.toString(args));
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
                        return ProxyAllureLoggedWebElement.of((WebElement) result, (By) args[0]);
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
                        final By[] path = new By[]{(By) args[0]};
                        final List<WebElement> wrappedElements = new ArrayList<>(elements.size());
                        for (final WebElement element : elements) {
                            wrappedElements.add(ProxyAllureLoggedWebElement.of(element, path));
                        }
                        return wrappedElements;
                    }
                    break;
                }
                case "navigate": {
                    if (method.getParameterTypes().length == 0) {
                        return ProxyAllureLoggedNavigation.of((WebDriver.Navigation) result);
                    }
                }
                // case "manage": ...
                // case "timeouts": ...
                // case "switchTo": ...
                // case "ime": ...
                // case "window": ...
            }
            return result;
        });
    }
}
