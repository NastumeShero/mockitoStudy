package MockitoStudy.common;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;


/**
 * 使用注解来快速模拟mock对象，会出现空指针异常，也就是说mock对象为空；
 * 解决办法：
 * 1.需要在基类中初始化Mock
 * 2.使用built-in runner：MockitoJUnitRunner
 */

/**
 * 方法二：使用built-in runner：MockitoJUnitRunner
 * 在测试类添加@RunWith(MockitoJUnitRunner.class),可以通过@Mock来定义mock对象
 * */
@RunWith(MockitoJUnitRunner.class)
public class quickMock2 {
    @Mock
    private List mockList;


    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}

