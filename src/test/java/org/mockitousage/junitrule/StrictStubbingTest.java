package org.mockitousage.junitrule;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.quality.Strictness;
import org.mockitousage.IMethods;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class StrictStubbingTest {

    @Mock IMethods mock;

    Mockito.Handler handler = Mockito.initMocking(this, Strictness.STRICT_STUBS);

    @After public void after() {
        handler.finishMocking();
    }

    /**
     - ok test
     - unused stubs
     */

    @Test public void strict_stubs() throws Throwable {
        when(mock.simpleMethod("foo")).thenReturn("y");

        mock.otherMethod();
        verify(mock).otherMethod();

        verifyNoMoreInteractions(mock);

//        mock.simpleMethod("bar");
    }
}