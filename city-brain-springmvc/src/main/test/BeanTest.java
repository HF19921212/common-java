import com.city.domain.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext act = new AnnotationConfigApplicationContext(Account.class);
        Account account = (Account) act.getBean("account");
        System.out.println(account.getSex());
    }

}
