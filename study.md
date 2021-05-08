
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

### * ) Spring application 
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
