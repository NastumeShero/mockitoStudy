package MockitoStudy.common;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoBasicTest {
    @Mock
    private List mockList;
    @Mock
    private List mockList2;


    /**
     * 迭代设置返回值
     * */
    @Test(expected = RuntimeException.class)
    public void consecutive_calls(){
        //模拟连续调用返回期望值，对同一个方法多次设置返回值，则会返回最后一次设置的返回值；
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        //模拟多次调用同一个方法，返回不同的值
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2,mockList.get(0));
        assertEquals(2,mockList.get(0));
        assertEquals(0,mockList.get(1));
        assertEquals(1,mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }



    /**
     * 通过参数捕获器，来捕获某个类的对象
     * */
    @Test
    public void argumentCaptorTest() {
        List mock = mock(List.class);
        List mock2 = mock(List.class);
        mock.add("xiao");
        mock.add("John");

        mock2.add("Brian");
        mock2.add("Jim");

        ArgumentCaptor argument = ArgumentCaptor.forClass(String.class);
//      在add(String)方法中，String使用argument.capture()来捕获已经传入的所有参数
        verify(mock,times(2)).add(argument.capture());
//      argument.getValue()方法得到的是最后捕获到的参数值，此处为John
        assertEquals("John", argument.getValue());
//        argument.getAllValues()返回所有捕获到的参数值，此处为[xiao，John]
        System.out.println(argument.getAllValues());

//        ArgumentCaptor argument1 = ArgumentCaptor.forClass(String.class);
//        此处mock2执行add后，argument.capture()获取到的参数为mock2调用add方法的值，即“Brian，Jim”
        verify(mock2, times(2)).add(argument.capture());
        //此时argument捕获到的String参数个数则为4个，2个mock和2个mock2,执行add方法设置的参数；
        System.out.println(argument.getAllValues());
//
        assertEquals("Jim", argument.getValue());
        assertArrayEquals(new Object[]{"xiao","John","Brian","Jim"},argument.getAllValues().toArray());
    }

    /**
     * RETURNS_SMART_NULLS实现了Answer接口的对象，它是创建mock对象时的一个可选参数，mock(Class,Answer)。
     *
     * 在创建mock对象时，有的方法我们没有进行stubbing，所以调用时会放回Null这样在进行操作是很可能抛出NullPointerException。
     * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法时会返回SmartNull。
     * 例如：返回类型是String，会返回"";是int，会返回0；是List，会返回空的List。
     * 另外，在控制台窗口中可以看到SmartNull的友好提示。
     * */
    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(1));

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。另外控制台窗口会提示信息“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
    }


}
