
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