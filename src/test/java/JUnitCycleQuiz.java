import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 1. 각각의 테스트를 시작하기 전에 "Hello!"를 출력하는 메서드 추가
 * 2. 각각의 테스트를 종료 전에 "Bye!"를 출력하는 메서드 추가
 */

public class JUnitCycleQuiz {

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Hello!");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("Bye!");
    }

    @Test
    public void junitQuiz3() {
        System.out.println("This is first Test");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("This is second Test");
    }
}
