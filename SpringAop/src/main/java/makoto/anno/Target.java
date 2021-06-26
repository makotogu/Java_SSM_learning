package makoto.anno;

import org.springframework.stereotype.Component;

@Component("target")
public class Target implements TargetInterface {
    @Override
    public void save() {
        System.out.println("save running");
    }

    @Override
    public void error() {
        System.out.println(1/0);
    }
}
