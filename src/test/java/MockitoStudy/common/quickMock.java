package MockitoStudy.common;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;


/**
 * 使用注解来快速模拟mock对象，会出现空指针异常，也就是说mock对象为空；
 * 解决办法：
 * 1.需要在基类中初始化Mock
 * 2.使用built-in runner：MockitoJUnitRunner
 */

public class quickMock {
    @Mock
    private List mockList;

    /**=
     * 方法一：初始化Mock
     * 创建构造函数，将mock对象初始化
     * */
    public quickMock() {
        //将注解对象mockList进行初始化
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }



}

