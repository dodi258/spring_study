package dodi258.core.filter.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //* 클래스에 붙는다는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
