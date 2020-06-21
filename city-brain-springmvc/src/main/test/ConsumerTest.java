import com.city.common.bio.service.EsProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        EsProductService esProductService = (EsProductService) context.getBean("esProductService");
        esProductService.searchPage("苹果",1,10);
    }
}
