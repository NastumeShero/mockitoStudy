package MockitoStudy;

import org.junit.Test;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * RETURNS_DEEP_STUBS也是创建mock对象时的备选参数
 * RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，方法deepstubsTest和deepstubsTest2是等价的
 * */

public class deepStubTest {

    //该方法等价于deepstubsTest2();
    @Test
    public void deepstubsTest(){
        //在创建mock对象时，RETURN_DEEP_STUBS表示会自动创建进行mock时所需的对象
        Account account= mock(Account.class,RETURNS_DEEP_STUBS);
        //mock该方法时，会自动创建一个RailwayTicket的mock对象
        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket(),times(1)).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }
    @Test
    public void deepstubsTest2(){
        Account account= mock(Account.class);
        RailwayTicket railwayTicket=mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());



    }


}



