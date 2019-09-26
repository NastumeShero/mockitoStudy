package MockitoStudy.spy;

import javafx.beans.binding.When;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class monitorRealObjectTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void spy_on_real_objects(){
        List list = new LinkedList();
        List spy = spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
//        when(spy.get(0)).thenReturn(3);

        //使用doReturn-when可以避免when-thenReturn调用真实对象api
        doReturn(999).when(spy).get(0);
        //预设size()期望值
        when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        spy.add(3);
        assertEquals(100,spy.size());
        assertEquals(999,spy.get(0));
        assertEquals(2,spy.get(1));
        verify(spy).add(1);
        verify(spy).add(2);
        //此处会调用真实的get方法，由于spy只有3个对象，故此处spy.get(999)是获取的第1000个对象，则会抛出IndexOutOfBoundsException
        assertEquals(999,spy.get(999));
        //由于spy.add()方法调用了3次，即spy有3个对象，通过get方法，则可以获取3个对象,分别为spy.get(0),spy.get(1),spy.get(2);
        assertEquals(3,spy.get(2));

    }

    @Test
    public void real_partial_mock(){
        //通过spy来调用真实的api
        List list = spy(new ArrayList());
//        doReturn(10).when(list).size();
        when(list.size()).thenReturn(10);
        assertEquals(10,list.size());

        A a  = mock(A.class);
        //通过thenCallRealMethod来调用真实的api
        when(a.doSomething(anyInt())).thenCallRealMethod();
        assertEquals(999,a.doSomething(999));
    }


    class A{
        public int doSomething(int i){
            return i;
        }
    }
}
