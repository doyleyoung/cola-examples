package cola.ide

import com.github.bmsantos.core.cola.story.annotations.IdeEnabler
import org.junit.Test
import kotlin.test.fail

public open class BaseColaTest {

    @IdeEnabler
    @Test
    public fun iWillBeRemoved() {
        fail("This test should not execute")
    }
}