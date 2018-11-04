package poc;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private static final String template = "Hello, %s!";
    private static final String addTemplate = "Addition : , %s!";
    private static final String multiplyTemplate = "Multiply : , %s!";
    private static final String divideTemplate = "Divide : , %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Message greeting(@RequestParam(value="name", defaultValue="Maths") String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/add")
    public Message greeting(@RequestParam(value="value1") Integer value1, @RequestParam(value="value2") Integer value2) {
        Integer addition = value1 + value2;
        //For testing of throw exception logging
        throw new ArithmeticException();
        //return new Message(counter.incrementAndGet(), String.format(addTemplate, addition));
    }

    @RequestMapping("/multiply")
    public Message greeting(@RequestParam(value="value1") Integer value1, @RequestParam(value="value2") Integer value2,
            @RequestParam(value="value3") Integer value3){
        Integer multiplication = value1 * value2 * value3;
        return new Message(counter.incrementAndGet(), String.format(multiplyTemplate, multiplication));
    }
}
