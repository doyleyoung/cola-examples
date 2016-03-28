package cola.ide

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler
import org.junit.Assert.fail
import org.junit.Test

public open class BaseColaTest {

    @IdeEnabler
    @Test
    public fun iWillBeRemoved() {
        fail("This test should not execute")
    }
}