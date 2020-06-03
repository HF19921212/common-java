import com.city.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        /**
         * scan 扫描我们的类,将类进行解析成 BeanDefinition 类描述对象
         */
        AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(Account.class);
        Account account = (Account) act.getBean("account");
        System.out.println(account.getSex());

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        //Account account = (Account) context.getBean("account");
        //System.out.println(account.getSex());
    }

}
