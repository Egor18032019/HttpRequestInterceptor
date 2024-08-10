package t1.interceptor.ru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import t1.interceptor.ru.config.HttpLoggingInterceptor;

public class HttpRequestInterceptorTest {
//TODO тесты напиши
    private HttpLoggingInterceptor httpInterceptor;

    @BeforeEach
    public void setUp() {
        httpInterceptor = new HttpLoggingInterceptor();
    }

    @Test
    public void testPreHandle() throws Exception {

    }

    @Test
    public void testAfterCompletion() throws Exception {

    }


    @Test
    public void testPostHandle() throws Exception {

    }

}
