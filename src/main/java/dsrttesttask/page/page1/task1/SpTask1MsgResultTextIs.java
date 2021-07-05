package dsrttesttask.page.page1.task1;

import dsrttesttask.step.assertion.element.SpTextIs;

public class SpTask1MsgResultTextIs extends SpTextIs {

    public SpTask1MsgResultTextIs(final String text) {
        super(new LtTask1MsgResult(), text);
    }
}
