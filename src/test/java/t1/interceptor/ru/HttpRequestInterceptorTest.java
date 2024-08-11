package t1.interceptor.ru;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import t1.interceptor.ru.config.HttpLoggingInterceptor;

import static org.mockito.Mockito.mock;

public class HttpRequestInterceptorTest {

    private HttpLoggingInterceptor httpInterceptor;


    @BeforeEach
    public void setUp() {
        httpInterceptor = new HttpLoggingInterceptor();

    }

    @Test
    public void testPreHandle() {
        Logger logger = (Logger) LoggerFactory.getLogger(HttpLoggingInterceptor.class);
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setContext((Context) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/api");

        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerInterceptor handler = mock(HandlerInterceptor.class);

        boolean result = httpInterceptor.preHandle(request, response, handler);
        Assertions.assertTrue(result);

        int count = memoryAppender.countEventsForLogger("t1.interceptor.ru.config.HttpLoggingInterceptor");
        Assertions.assertEquals(3, count);
        int count2 = memoryAppender.search("GET", Level.INFO).size();
        Assertions.assertEquals(1, count2);

    }

    @Test
    public void testAfterCompletion() {
        Logger logger = (Logger) LoggerFactory.getLogger(HttpLoggingInterceptor.class);
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setContext((Context) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerInterceptor handler = mock(HandlerInterceptor.class);
        boolean result = httpInterceptor.preHandle(request, response, handler);
        Assertions.assertTrue(result);

        httpInterceptor.afterCompletion(request, response, handler, null);

        int count = memoryAppender.countEventsForLogger("t1.interceptor.ru.config.HttpLoggingInterceptor");
        Assertions.assertEquals(4, count);
    }


    @Test
    public void testPostHandle() {
        Logger logger = (Logger) LoggerFactory.getLogger(HttpLoggingInterceptor.class);
        MemoryAppender memoryAppender = new MemoryAppender();
        memoryAppender.setContext((Context) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.INFO);
        logger.addAppender(memoryAppender);
        memoryAppender.start();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        HandlerInterceptor handler = mock(HandlerInterceptor.class);
        httpInterceptor.postHandle(request, response, handler, null);
        int count = memoryAppender.countEventsForLogger("t1.interceptor.ru.config.HttpLoggingInterceptor");
        Assertions.assertEquals(2, count);
        int count2 = memoryAppender.search("200", Level.INFO).size();
        Assertions.assertEquals(1, count2);
    }

}
