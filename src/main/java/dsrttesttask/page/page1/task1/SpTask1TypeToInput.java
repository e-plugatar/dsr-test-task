package dsrttesttask.page.page1.task1;

import dsrttesttask.step.assertion.element.SpSendKeys;

public class SpTask1TypeToInput extends SpSendKeys {

    public SpTask1TypeToInput(final CharSequence... keysToSend) {
        super(new LtTask1InputField(), keysToSend);
    }
}
