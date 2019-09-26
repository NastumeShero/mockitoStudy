package MockitoStudy;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


import static org.mockito.Mockito.*;

public class SmartNullsTest {

    @Test
    public void returnsSmartNullsTest() {
        //创建mock对象时，使用RETURNS_SMART_NULLS参数，当没有对有返回值的目标方法进行打桩的时候，会返回SmartNull
        List mock = mock(List.class,RETURNS_SMART_NULLS);

        System.out.println(mock.get(0));
//        when(mock.toArray()).thenReturn(new Object[2]);

        //使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常，会创建一个空的对象来调用方法
        System.out.println(mock.toArray());
        System.out.println(mock.toArray().length);
    }

    @Test(expected = NullPointerException.class)
    public void returnsNullExceptionTest() {
        //创建mock对象时，当没有对有返回值的目标方法进行打桩的时候，会返回方法的默认值
        List mock = mock(List.class);
        //mock.get(0)方法返回null
        System.out.println(mock.get(0));
        Assert.assertNull(mock.get(0));

        //mock.toArray()方法返回object对象,没有对该方法打桩，则返回Null；获取空对象的length时，抛出NullPointerException
        Assert.assertNull(mock.toArray());
        System.out.println(mock.toArray().length);

    }
}
