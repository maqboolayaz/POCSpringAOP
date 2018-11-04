package poc;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnglishController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/english")
    public Message greeting(@RequestParam(value="name", defaultValue="English") String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/token")
    public Message greeting(@RequestParam(value="say") Integer token) {
        return new Message(counter.incrementAndGet(), String.format(template, token));
    }
}
