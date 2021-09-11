package br.com.star.wars;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

}
