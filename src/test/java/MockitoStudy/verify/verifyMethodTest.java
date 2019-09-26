package MockitoStudy.verify;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.*;

public class verifyMethodTest {

    @Mock
    List listMock = mock(List.class);

    /**
     * 验证行为
     * */
    @Test
    public void verify_behaviour() {
        //模拟创建一个List对象
//        List listMock = mock(listMock.class);
        //使用mock的对象
        listMock.add(1);
        listMock.clear();
        //验证add(1)和clear()行为是否发生
        verify(listMock).add(1);
        verify(listMock).clear();
    }

    /**
     *  验证调用次数
     * */
    @Test
    public void verifying_number_of_invocations(){
//        List listMock = mock(List.class);
        listMock.add(1);
        listMock.add(2);
        listMock.add(2);
        listMock.add(3);
        listMock.add(3);
        listMock.add(3);
        //验证是否被调用一次，等效于下面的times(1)
        verify(listMock).add(1);
        verify(listMock,times(1)).add(1);
        //验证是否被调用2次
        verify(listMock,times(2)).add(2);
        //验证是否被调用3次
        verify(listMock,times(3)).add(3);
        //验证是否从未被调用过
        verify(listMock,never()).add(4);
        //验证至少调用一次
        verify(listMock,atLeastOnce()).add(1);
        //验证至少调用2次
        verify(listMock,atLeast(2)).add(2);
        //验证至多调用3次
        verify(listMock,atMost(3)).add(3);
    }

    /**
     * 验证执行顺序
     * */
    @Test
    public void verificationInorderTest(){
//        List listMock = mock(List.class);
        List list2 = mock(List.class);
        listMock.add(1);
        list2.add("hello");
        listMock.add(2);
        list2.add("world");
        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(listMock,list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(listMock).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(listMock).add(2);
        inOrder.verify(list2).add("world");
    }


}
