package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
@Slf4j
public class UnitTest {

    //when using @Mock, MockitoAnnotations.openMock(class)
//    @Mock
//    MyService service;


    @Test
    public void test1() {
        log.debug("test1 before");
        MyService service = mock(MyService.class);
//        MockitoAnnotations.openMocks(this);
        String expect = "expect";
        when(service.service1(any(String.class))).thenReturn("expect");
        log.debug("expected result: {}", service.service1("1"));
        verify(service).service1(anyString());
        assertEquals(expect, service.service1("1"));
    }

    /**
     * 当需要mock一个内部对象时，使用spy
     * 比如这个例子中，要求所有MyService2的service方法都返回一个字符串，而实际的方法返回null
     */
    @Test
    public void test2() {
        log.debug("test2 begins");
        MyService service = mock(MyService.class);

        //直接mock没有效果(直接mock只对在test中执行方法有效，而项目代码中的方法没有效果)
//        MyService2 service2 = mock(MyService2.class);
        //when(service2.service(anyString())).thenReturn("service2");

        MyService2 service2 = new MyService2();
        MyService2 mockService = spy(service2);

        when(mockService.service(anyString())).thenReturn("service2");

        String out = service.service2("in");
        log.debug("service result: {}", out);
        assertEquals(null, out);
        assertEquals("service2", mockService.service("in"));
    }


}

