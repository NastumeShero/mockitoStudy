package MockitoStudy.argument;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class argumentCaptorTest {

    @Test
    public void capturing_args(){
        PersonDao personDao = mock(PersonDao.class);
        PersonService personService = new PersonService(personDao);
        //参数捕获器，定义argument来捕获Person类的参数
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        //personService调用update方法，执行代码时则会执行personDao.update(new Person())
        personService.update(1,"jack");
        //验证personDao.update方法调用时，通过argument这个参数捕获器来捕获Person对象，此处则将id为1，name为jack的Person对象给捕获到了
        verify(personDao).update(argument.capture());

        System.out.println(argument.getAllValues());

        personService.update(2,"tom");
        verify(personDao,times(2)).update(argument.capture());

        System.out.println(argument.getAllValues());

        assertEquals(2,argument.getValue().getId());
        assertEquals("tom",argument.getValue().getName());
    }

    class Person{
        private int id;
        private String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    interface PersonDao{
        public void update(Person person);
    }

    class PersonService{
        private PersonDao personDao;

        PersonService(PersonDao personDao) {

            this.personDao = personDao;
        }

        public void update(int id,String name){

            personDao.update(new Person(id,name));
        }
    }
}
