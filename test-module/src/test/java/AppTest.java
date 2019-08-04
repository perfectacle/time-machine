import com.github.perfectacle.TimeMachine;
import com.github.perfectacle.TimeTraveler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void name() {
        TimeMachine.instantOfNow();
        TimeTraveler.reset();
    }
}