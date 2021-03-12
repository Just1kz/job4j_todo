package ru.job4j.todo;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Test1Test {

    @Test
    public void someLogic() {
        assertThat(Test1.someLogic(), is(Boolean.TRUE));
    }
}