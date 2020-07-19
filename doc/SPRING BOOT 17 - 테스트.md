# SPRING BOOT 17 - 테스트



@MockBean

* ApplicationContext에 있는 빈을 Mock으로 만든 객체로 교체 함.
* 모든 @Test 마다 자동으로 리셋



#### SpringBootTest

> 통합 테스트

**webEnvironment.MOCK**

```java
package me.hanum.test.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)

/**
 * Dispatcher servlet을 mockup한다.
 * 이 mockup된 servlet에 요청하기 위해서는 mock mvc client가 필요하다.
 *
 * SpringBootTest.WebEnvironment.
 *      MOCK: mock servlet environment. 내장 톰켓 실행 안함
 *      RANDON_PORT, DEFINED_PORT: 내장 톰캣 사용 함
 *      NONE: 서블릿 환경 제공 안 함.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

/**
 * Mock Mvc Client를 사용하기 위해서 선언
 * 스프링 부트에서는 Mock mvc 생성하는 가장 쉬운 방법
 */
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello hanum"))
                .andDo(MockMvcResultHandlers.print());
    }
}
```



**webEnvironment.RANDON_PORT**

* 서블릿이 띄워진다.

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDON_PORT)
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    // ApplicationContext에 있는 빈을 Mock으로 만든 객체로 교체 함.
    @MockBean
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        String result = testRestTemplate.getForObject("/hello", String.class);
        assertThat(result).isEqualTo("hello hanum");
    }
}
```



WebTestClient

```java
@RunWith(SpringRunner.class)

/**
 * Dispatcher servlet을 mockup한다.
 * 이 mockup된 servlet에 요청하기 위해서는 mock mvc client가 필요하다.
 *
 * SpringBootTest.WebEnvironment.
 *      MOCK: mock servlet environment. 내장 톰켓 실행 안함
 *      RANDON_PORT, DEFINED_PORT: 내장 톰캣 사용 함
 *      NONE: 서블릿 환경 제공 안 함.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDON_PORT)

/**
 * Mock Mvc Client를 사용하기 위해서 선언
 * 스프링 부트에서는 Mock mvc 생성하는 가장 쉬운 방법
 */
@AutoConfigureMockMvc
public class SampleControllerTest {

    /**
     * webflux를 종속성에 추가해야 사용할 수 있는듯
     * async하다.
     * 사용하기 편하다.
     */
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    SampleService mockSampleService;


    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("hanum");

        webTestClient.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello hanum");
    }
}
```

* 어마어마한 사이즈의 테스트라고 한다.



#### 슬라이싱 테스트

@WebMvcTest

```java
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {


    @MockBean
    SampleService mockSampleService;

    @Autowired
    MockMvc mockMvc;


    @Test
    public void hello() throws Exception {
        when(mockSampleService.getName()).thenReturn("hanum");

        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.content().string("hello hanum"));
    }
}
```

* 컨트롤러만 가져와서 테스트한다.
* 만약, 컨트롤러가 의존하는 것이 있다면, 해당 의존성을 전부 `@MockBean`으로 채워주어야 한다.
  * 여기서는 SampleService를 채워주었다.



#### 테스트 유틸

* OutputCapture
  * 이 부분을 사용하여 로거에 찍히는 특정 부분을 잡을 수 있다.