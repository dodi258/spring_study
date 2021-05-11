
### 1)  AppConfig 적용 전 application 

의존 관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음. 

역할: interface 
구현: interface implements 
역할과 구현은 충실히 분리하였음. 

다형성 활용 인터페이스와 구현 객체를 분리함. 

DiscountPolicy discountPolicy = new FixedDiscountPolicy() <-- 구체 클래스 
직접 구현체를 넣어주는 부분은 추상뿐 아니라 구체 클래스에도 의존하고 있다는 것을 보여줌. 

- OCP 위반: 
만약 구현체를 바꾸고 싶다면(FixedDiscountPolicy --> RateDiscountPolicy) 
직접 구현체를 코드에 넣어야 하는 즉, OCP 를 위반한 형태가 발생한다. 

- DIP 위반: 구체 클래스에 의존한다.  

인터페이스에만 의존하도록 설계를 변경하자
--> 어떻게 ??
--> 관심사를 분리하자. 각 추상 클래스에 맞는 구현체를 부여하는 하나의 담당을 만들면 된다. 
--> AppConfig: 애플리케이션의 전체 동작 방식을 구성(CONFIG) 하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만들면 됨. 

@BeforeEach : 각 테스트를 실행하기 전에 호출됩니다.

### * ) 좋은 객체 지향 설계의 5가지 원칙 (AppConfig 적용 ) - SRP, DIP, OCP, LSP, ISP
        1. SRP (Single Responsibility Principle - 단일 책임 원칙) 
            :한 클래스는 하나의 책임만을 가져야 한다.
            - 구현 객체를 생성, 연결하는 책임은 AppConfig 가 담당. 
            - 클라이언트 객체는 로직을 실행하는 책임만을 담당. 

        2. DIP (Dependency Inversion Principle - 의존관계 역전 원칙)
            : 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나
            - AppConfig가 FixedDiscountPolicy 객체 인스턴스를 클라이언트 코드 대신 생성해서 클라이언트 코드에 의존 관계를 주입함. 
              이렇게 해서 DIP 원칙을 따르면서 클라이언트 코드는 추상화 인터페이스에만 의존하도록 했다. 

        3. OCP (Open Close Principle - 개방 폐쇄 원칙) 
            : 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀있어야 한다. 
            - 다형성을 사용하고 클라이언트 코드가 DIP를 지킴
            - 애플리케이션을 사용 영역과 구성 영역으로 나눔
            - AppConfig 가 구현체가 달라지면 의존 관계를 Appconfig 안에서 변경하여 클라이언트 코드에 주입하므로 클라이언트 코드는
              구현체가 변경되어도, 코드를 수정할 필요 없음. 
            - 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀있다. 

### * ) IoC, DI, 컨테이너
        1. IoC (Inversion of Control - 제어의 역전)
            - 프로그램의 제어 흐름을 직접 제어하는 것이 아닌 외부 (= AppConfig) 에서 관리하는 것이 IoC(Inversion of Control) 
            - 프레임 워크 vs. 라이브러리 

        2. DI (Dependency Injection - 의존관계 주입) 
            - 인터페이스를 가져다 쓰는 클라이언트 코드는 실제 어떤 구현 객체가 사용되는지 모르고 인터페이스에만 의존한다. 
            - 의존 관계는 정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계 둘을 분리해서 생각해야 한다.
            - 정적인 클래스 의존 관계
                : 클래스가 사용하는 IMPORT 코드만 보고 의존 관계를 쉽게 판단할 수 있음.
                  예를 들면, OrderServiceImpl은 Import 된 클래스를 보고, MemerService 와 DiscountPolicy에 의존한다는 것을 알 수 있음. 
                  그러나, 이러한 클래스 의존 관계 만으로는 실제 어떤 객체가 OrderServiceImpl에 쓰이는 MemberService에 주입될지 알 수 없다. 
            - 동적인 클래스 의존 관계 
                : 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계이다. 
                  애플리케이션 ***실행시점(런타임)*** 에 외부에서 실제 구현 객체를 생성하고 
                  클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 **의존관계 주입**이라고 한다.
        3. 컨테이너 
            - AppConfig 처럼 객체를 생성, 관리, 의존관계를 연결해주는 것을 
            - IoC 컨테이너 또는 DI 컨테이너라고 한다. 
            - 어셈블러, 오브젝트 팩토리 등으로 불리기도 한다. 

### * ) Spring Container 
    - @Configuration: AppConfig에 설정을 구성할 것이라는 뜻
    - @Bean(name=""): 스프링 컨테이너에 스프링 빈으로 등록한다는 것. 이름을 직접 지정할  있음 안하면 메서드이름이 기본 이름이 됨. 수
                      ** 빈 이름은 항상 다른 이름을 부여해야함.**
    - ApplicationContext: 스프링 컨테이너. DI 를 스프링 컨테이너가 도맡아서 함. 
    - 스프링 컨테이너를 통해 필요한 스프링 빈(객체)를 찾는다. 스프링 빈은 applicationContext.getBean()메서드를 통해서 직접 
      찾을 수 있다. 
    - 기존에는 개발자가 직접 자바 코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경 됨. 
    - AnnotationConfigApplicationContext(AppConfig.class) :
      1. AnnotationConfigApplicationContext
         :스프링 컨테이너는 XML, annotation 두가지 방식으로 만들어질 수 있다.
          @Configuration을 붙여 만든 설정 클래스는 annotation 기반이고 위의 AnnotationConfigurationApplicationContext 는 
          ApplicationContext의 Annotation 구현체이다. 
      2. AppConfig.class 는 구성 정보임.

    BeanFactory 와 ApplicationContext
        BeanFactory <-- ApplicationContext <-- AnnotationConfigApplicationContext
      1. BeanFactory:
        - 스프링 컨테이너 최상위 인터페이스
        - 스프링 빈을 관리하고 조회하는 역할을 담당한다. 
        - getBean()  을 제공한다.
        - 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다. 
      2. ApplicationContext
        - BeanFactory 기능을 모두 상속받아서 제공한다.
        - 빈을 관리하고 검색하는 기능을 BeanFactory가 제공하는데, 그러면 둘의 차이가 뭘까 ? 
        - 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다. 
        - ApplicationContext가 제공하는 기능: MessageSource, EnvironmentCapable, ApplicationEventPublisher, ResourceLoader
        - MessageSource: 언어 처리 - 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
        - EnvironmentCapable: 환경 변수 - 로컬, 개발 운영등을 구분해서 처리
        - ApplicationEventPublisher: 애플리케이션 이벤트 - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
        - 명ResourceLoader: 편리한 리로스 조회 - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회
        
        => BeanFactory와 ApplicationContext를 스프링 컨테이너라고 부른다.
    
    BeanDefinition
        -BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음) 
        - factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig 
        - factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
        - Scope: 싱글톤(기본값)
        - lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부
        - InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명 
        - DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명 
        - Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할 의 빈을 사용하면 없음) 
        - BeanClassName: 생성한 빈의 클래스 

### * ) SingleTon Pattern
    - 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
    - private 생성자를 사용하여 외부에서 임의로 NEW 키워드를 사용하지 못하도록 막는다. 
    - 싱글톤 패턴의 문제점
      - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
      - 의존 관계상 클라이언트가 구체 클래스에 의존한다. DIP 를 위반한다.
      - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다. 
      - 테스트하기 어렵다. 
      - 내부 속성을 변경하거나 초기화 하기 어렵다. 
      - private 생성자로 자식 클래스를 만들기 어렵다. 
      - 결론적으로 유연성이 떨어진다. 
      - 안티 패턴으로 불리기도 한다. 

    싱글톤 컨테이너: 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다. 스프링 빈이 바로 싱글톤으로 관리되는 빈이다. 
    - 스프링 컨테이너는 싱글턴 페턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다. 
    - 싱글톤 레지스트리 : 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다. 
    - DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다. 
    - 스프링은 기본 빈 등록 방식이 싱글톤이지만, 요청할 때마다 새로운 객체를 생성하는 기능도 제공한다. 

    싱글톤 방식의 주의점:
    - 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지하게 설계하면 안된다. 
    - 무상태로 (stateless)로 설계해야 한다.
        - 특정 클라이언트에 의존적인 필드가 있으면 안된다. 
        - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
        - 가급적 읽기만 가능해야 한다.
        - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다. 
    - 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!


### * ) Component Scan
    - 스프링 빈을 등록할 때 @Bean 혹은 XML 을 사용했는데 스프링은 이러한 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다. 
    - @Autowired : 의존관계 자동 주입 
    - 컴포넌트 스캔 : @Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다
    - 각 클래스가 컴포넌트 스캔의 대상이 되도록 @Component 어노테이션을 붙여준다. 
    - 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다. 
      (그리고 그 설정안에 바로 @ComponentScan 이 들어있다.)
    - 컴포넌트 스캔 기본 대상
        - @Component : 컴포넌트 스캔에서 사용
        - @Controller : 스프링 MVC 컨트롤러에서 사용 - 스프링 MVC 컨트롤러로 인식
        - @Service : 스프링 비즈니스 로직에서 사용 - 개발자들의 인식에 도움 
        - @Repository : 스프링 데이터 접근 계층에서 사용 - 스프링 데이터 접근 계층으로 인식, 데이터 계층의 예외를 스프링 예외로 변환 
        - @Configuration : 스프링 설정 정보에서 사용 - 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한ㅏ. 

### * ) 의존 관계 주입 (Dependency Injection)
의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다. 
스프링 빈이 아닌 Member 같은 클래스에서 @Autowired 코드를 적용해도 아무 기능도 동작하지 않는다.

의존 관계 주입 4가지 방법 
- 생성자 주입
- 수정자 주입 (setter 주입)
- 필드 주입
- 일반 메서드 주입

1. 생성자 주입
   - 생성자를 통해 의존 관계 주입 받는 방법
   - 특징 : 
     - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
     - **불변**, **필수** 의존관계에 사용
     - 생성자가 딱 한개있으면 @Autowired 를 생략해도 자동 주입된다. 
    ```java
    @Component 
   public class OrderServiceImpl implements OrderSerivce {
       private final MemberService memberService; 
       private final DiscountPolicy discountPolicy; 
       
       //@Autowired --> 생략 가능
       public OrderServiceImpl(MemberService memberService, DiscountPolicy discountPolicy) {
           this.memberService = memberService; 
           this.discountPolicy = discountPolicy;
       }
   }
   ```
   
2. 수정자 주입
    - setter 라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존 관계를 주입하는 방법
    - 꼭 set 메서드를 불러서 지정해줘야만 주입이 된다. (그 전엔 null 인 상태)
    - 특징: 
        - **선택**, **변경** 가능성이 있는 의존 관계에 사용 
        - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법 ?
            --> 자바빈 프로퍼티 규약 : setXxx, getXxx 메서드를 통해서 값을 읽거나 수정하는 규칙
        - 의존하는 빈이 생성이 아직 안되어있는 경우에도 사용이 가능함. @Autowired(required=false)
        - @Autowired(required=false) : @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게 하려면 required=false를 붙이면 된다. 
        ~~~ java
        @Component 
        public class OrderServiceImpl implements OrderSerivce {
           private MemberService memberService; 
           private DiscountPolicy discountPolicy; 
           
           @Autowired(required = false) 
           public void setMemberService(MemberService memberService) {
               this.memberService = memberService; 
           }
            
           @Autowired
           public void setDiscountPolicy(DiscountPolicy discountPolicy) {
               this.discountPolicy = discountPolicy; 
           }
        }
        ~~~
      
3. 필드 주입
    - 이름 그댈 필드에 바로 주입하는 방법 
    - 특징 
        - 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적 단점 
        - DI 프레임 워크가 없다면 아무것도 할 수 없다. 
        - 사용하지 말자!
        - 애플리케이션의 실제 코드와는 관계없는 테스트 코드에는 가능 --> @SpringBootTest 
        - 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용

        ~~~java
       @Component
       public class OrderServiceImpl implements OrderServie { 
            @Autowired
            private MemberService memberService; 
            
            @Autowired
            private DiscountPolicy discountPolicy;
       }
       ~~~
      
4. 일반 메서드 주입
    - 일반 메서드를 통해 주입받는다. 
    - 잘 쓰이지 않음.
   
        ~~~java
       @Component
       public class OrderServiceImpl implements OrderService {
           private MemberRepository memberRepository;
           private DiscountPolicy discountPolicy;
    
           @Autowired
           public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
               this.memberRepository = memberRepository;
               this.discountPolicy = discountPolicy;
           }
       }
        ~~~


#### 의존관계 주입 - 옵션 처리
- @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 
- org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
- Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.

#### *생성자 주입을 선택하세요*!
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다. 
- 프레임 워크 없이 순수한 자바 코드를 단위 테스트 하는 경우에 좋음. 
- Final keyword: 생성자 주입을 사용하면 필드에 FINAL 키워드를 사용할 수 있다. 그래서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
- 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 FINAl 키워드를 사용할 수 없음.




