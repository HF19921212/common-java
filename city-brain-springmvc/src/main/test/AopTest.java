import com.city.aop.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        TestBean testBean = (TestBean)applicationContext.getBean("testBean");
        testBean.test();
    }
}
