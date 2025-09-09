package java8.my_annotation.way0;

public class SecureChannel {

    @Alarm(kind = "kind1")
    public SecureChannel() {
        //do something
    }

    @Alarm(kind = "kind2")
    void secureMethod() {
        //do something
    }
}
