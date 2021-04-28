package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeanFactoryApp.class)
public class BeanTest
{

    @Test
    public void test() throws Exception
    {
        System.out.println("\n******************************************************\n");
    }

}
