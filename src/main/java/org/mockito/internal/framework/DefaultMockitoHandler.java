package org.mockito.internal.framework;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.junit.TestFinishedEvent;
import org.mockito.internal.junit.UniversalTestListener;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;

public class DefaultMockitoHandler implements Mockito.Handler {

    private final Object testClassInstance;
    private final UniversalTestListener listener;

    public DefaultMockitoHandler(Object testClassInstance, Strictness strictness) {
        this.testClassInstance = testClassInstance;
        listener = new UniversalTestListener(strictness, new ConsoleMockitoLogger());
        Mockito.framework().addListener(listener);
        MockitoAnnotations.initMocks(testClassInstance);
    }

    public void finishMocking() {
        Mockito.framework().removeListener(listener);
        listener.testFinished(new TestFinishedEvent() {
            public Throwable getFailure() {
                return null;
            }

            public Object getTestClassInstance() {
                return testClassInstance;
            }

            public String getTestMethodName() {
                return null;
            }
        });
    }
}
