package newpoc;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import newpoc.Hello;

@RestController
public class HelloController {

    private static final String template = "Hi, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hello")
    public Hello greeting(@RequestParam(value="name", defaultValue="Sir") String name) {
        return new Hello(counter.incrementAndGet(),
                String.format(template, name));
    }
}
