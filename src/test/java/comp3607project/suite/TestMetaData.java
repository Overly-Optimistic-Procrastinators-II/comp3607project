package comp3607project.suite;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestMetaData {
    String description() default "Hidden";
    String marks() default "0";
}
