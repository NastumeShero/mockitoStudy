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
     * �������÷���ֵ
     * */
    @Test(expected = RuntimeException.class)
    public void consecutive_calls(){
        //ģ���������÷�������ֵ����ͬһ������������÷���ֵ����᷵�����һ�����õķ���ֵ��
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        //ģ���ε���ͬһ�����������ز�ͬ��ֵ
        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(2,mockList.get(0));
        assertEquals(2,mockList.get(0));
        assertEquals(0,mockList.get(1));
        assertEquals(1,mockList.get(1));
        //�����λ������ö����׳��쳣
        mockList.get(1);
    }



    /**
     * ͨ��������������������ĳ����Ķ���
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
//      ��add(String)�����У�Stringʹ��argument.capture()�������Ѿ���������в���
        verify(mock,times(2)).add(argument.capture());
//      argument.getValue()�����õ�������󲶻񵽵Ĳ���ֵ���˴�ΪJohn
        assertEquals("John", argument.getValue());
//        argument.getAllValues()�������в��񵽵Ĳ���ֵ���˴�Ϊ[xiao��John]
        System.out.println(argument.getAllValues());

//        ArgumentCaptor argument1 = ArgumentCaptor.forClass(String.class);
//        �˴�mock2ִ��add��argument.capture()��ȡ���Ĳ���Ϊmock2����add������ֵ������Brian��Jim��
        verify(mock2, times(2)).add(argument.capture());
        //��ʱargument���񵽵�String����������Ϊ4����2��mock��2��mock2,ִ��add�������õĲ�����
        System.out.println(argument.getAllValues());
//
        assertEquals("Jim", argument.getValue());
        assertArrayEquals(new Object[]{"xiao","John","Brian","Jim"},argument.getAllValues().toArray());
    }

    /**
     * RETURNS_SMART_NULLSʵ����Answer�ӿڵĶ������Ǵ���mock����ʱ��һ����ѡ������mock(Class,Answer)��
     *
     * �ڴ���mock����ʱ���еķ�������û�н���stubbing�����Ե���ʱ��Ż�Null�����ڽ��в����Ǻܿ����׳�NullPointerException��
     * ���ͨ��RETURNS_SMART_NULLS����������mock������û�е���stubbed����ʱ�᷵��SmartNull��
     * ���磺����������String���᷵��"";��int���᷵��0����List���᷵�ؿյ�List��
     * ���⣬�ڿ���̨�����п��Կ���SmartNull���Ѻ���ʾ��
     * */
    @Test
    public void returnsSmartNullsTest() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(1));

        //ʹ��RETURNS_SMART_NULLS����������mock���󣬲����׳�NullPointerException�쳣���������̨���ڻ���ʾ��Ϣ��SmartNull returned by unstubbed get() method on mock��
        System.out.println(mock.toArray().length);
    }


}
