package dsrttesttask.integration;

import dsrttesttask.driver.closable.DefaultWebDriverAction;
import dsrttesttask.page.page1.task1.SpTask1ClearInput;
import dsrttesttask.page.page1.task1.SpTask1ClickOnButton;
import dsrttesttask.page.page1.task1.SpTask1InputFieldAndButtonVisible;
import dsrttesttask.page.page1.task1.SpTask1MsgResultHidden;
import dsrttesttask.page.page1.task1.SpTask1MsgResultTextIs;
import dsrttesttask.page.page1.task1.SpTask1MsgResultVisible;
import dsrttesttask.page.page1.task1.SpTask1OpenPageWithoutPopup;
import dsrttesttask.page.page1.task1.SpTask1TypeToInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

final class Task1Test {
    {
        System.setProperty("default.waitTimeoutMs", "10000");
        System.setProperty("default.pollingIntervalMs", "250");
        System.setProperty("default.driverName", "chrome");
    }

    @Test
    void emptyMsg() throws Throwable {
        DefaultWebDriverAction.exec(
                new SpTask1OpenPageWithoutPopup(),
                new SpTask1InputFieldAndButtonVisible(),
                new SpTask1MsgResultHidden(),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultHidden()
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "k", "щ", "@", "汉"})
    void singleCharMsg(final String msg) throws Throwable {
        DefaultWebDriverAction.exec(
                new SpTask1OpenPageWithoutPopup(),
                new SpTask1InputFieldAndButtonVisible(),
                new SpTask1MsgResultHidden(),
                new SpTask1TypeToInput(msg),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultVisible(),
                new SpTask1MsgResultTextIs(msg)
        );
    }

    @Test
    void multipleCharsMsg() throws Throwable {
        final String msg = "汉1kщ@汉@щk1汉";
        DefaultWebDriverAction.exec(
                new SpTask1OpenPageWithoutPopup(),
                new SpTask1InputFieldAndButtonVisible(),
                new SpTask1MsgResultHidden(),
                new SpTask1TypeToInput(msg),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultVisible(),
                new SpTask1MsgResultTextIs(msg)
        );
    }

    @Test
    void clearMsg() throws Throwable {
        final String msg = "汉1kщ@汉@щk1汉";
        DefaultWebDriverAction.exec(
                new SpTask1OpenPageWithoutPopup(),
                new SpTask1InputFieldAndButtonVisible(),
                new SpTask1MsgResultHidden(),
                new SpTask1TypeToInput(msg),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultVisible(),
                new SpTask1MsgResultTextIs(msg),
                new SpTask1ClearInput(),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultHidden()
        );
    }

    @Test
    void changeMsg() throws Throwable {
        final String firstMsg = "汉1kщ@汉@щk1汉";
        final String secondMsg = "a汉bc123";
        DefaultWebDriverAction.exec(
                new SpTask1OpenPageWithoutPopup(),
                new SpTask1InputFieldAndButtonVisible(),
                new SpTask1MsgResultHidden(),
                new SpTask1TypeToInput(firstMsg),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultVisible(),
                new SpTask1MsgResultTextIs(firstMsg),
                new SpTask1ClearInput(),
                new SpTask1TypeToInput(secondMsg),
                new SpTask1ClickOnButton(),
                new SpTask1MsgResultVisible(),
                new SpTask1MsgResultTextIs(secondMsg)
        );
    }
}
