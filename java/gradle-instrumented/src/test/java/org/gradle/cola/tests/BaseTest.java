package org.gradle.cola.tests;

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler;
import static java.lang.System.err;

import org.junit.Test;

public abstract class BaseTest {

    @IdeEnabler
    @Test
    public void toBeRemoved() {
        err.println("COLA Tests: this test will not execute.");
    }
}
