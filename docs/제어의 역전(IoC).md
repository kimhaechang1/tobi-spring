## 제어의 역전

제어의 역전 (Inversion of Controll)은 `오브젝트의 생성 및 관계설정 권한을 외부에서 결정하는 것`을 모두 제어의 역전이라고 부른다.

제어의 역전의 예시로 서블릿이 있다.

서블릿의 코드를 작성할 때에는 사실 java파일 이지만 `main `메소드가 존재하지 않는다.

그저 우리는 전체 JSP를 포함한 웹 서버의 일부를 작성할 뿐이고, 언제 내가 작성한 서블릿이 톰켓 컨테이너 내에 오브젝트로 올라올지 그런시점을 제어할 수 없다.

그래서 서블릿또한 제어의 역전이다.

마찬가지로 라이브러리와 프레임워크의 차이를 보았을 때, 프레임워크도 제어의 역전이라고 볼 수 있다.

라이브러리는 전체 애플리케이션 코드 중 일부 함수 혹은 일부 실행흐름을 라이브러리가 가져갈 뿐이다.

하지만 프레임워크는 전체 애플리케이션 동작 중 일부 코드만을 개발자가 작성한다.

그러고 자기가 작성한 코드의 실행흐름 (자바 파일이라면 오브젝트) 가 언제 작동할지 개발자 자신은 예측할 수 없다.

단지 프레임워크가 필요하다고 생각되는 시점에 생성될 뿐이다.

### 기존의 DAO에서 제어의 역전을 팩토리 객체로 실천하기

기존의 코드 중 `UserDaoTest.java`를 잠깐 살펴보면

```java
public class UserDaoTest {
    public static void main(String[] args) {
        UserDao dao = new UserDao(new DConnectionMaker());
        dao.get();
        dao.add();
    }
}
```

사실 `UserDao`와 `DConnection`사이의 관계를 `ConnectionMaker`로 이어주고, 팩토리 메소드 패턴을 적용시킨 대상을 테스트하기 위한 클래스이지만

`UserDaoTest`의 역할 또한 하나의 관심사라고 보고, 적절한 책임이 달린 객체로 만들 수 있다.

`UserDaoTest`가 지금 해소시켜준 관심사는 `Dao 객체를 생성하기 위해 적절한 ConnectionMaker 오브젝트를 생성하여 결합하기`에 해당된다.

이를 `DaoFactory`라는 객체를 만들어서 해소시키자면 다음과 같아진다.

```java
public class DaoFactory {
    
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}

public class UserDaoTest {
    public static void main(String[] args) {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();
        userDao.add();
        userDao.get();
    }
}
```

이로 인해 아까의 관심사는 `DaoFactory.java`가 가져가게 되었다.

역할적으로 오브젝트를 분리해보자면, `UserDao`와 `ConnectionMaker`는 모두 각자의 기능들에 치중되어 있는 독립적인 형태이다.(응집도가 높고, 결합도는 낮은)

그리고 `DaoFactory`는 사실 실질적인 기능은 없고 `여러 오브젝트를 생성하고, 오브젝트끼리의 관계`를 맺어줄 뿐이다.

이러한 객체를 `팩토리`라고 주로 부른다.

### 스프링에서의 제어의 역전

스프링에서는 흔히 빈(Bean) 이라고 부르는 객체를 관리하는 `BeanFactory` 가 있다.

스프링에서 우리가 BeanFactory를 직접적으로 사용하진 않고 `BeanFactory를 확장한 ApplicationContext 인터페이스`를 사용한다.

ApplicationContext와 위의 DaoFacotry 는 비슷하지만 사뭇 다른데, 둘 다 IoC를 기반으로 하는 객체인건 똑같다.

DaoFactory는 Dao를 생산하는 임무를 맡고 있고 직접 오브젝트를 생성하고 필요한 오브젝트끼리의 관계를 맺어주고 있지만

ApplicationContext의 경우 모든 어플리케이션 내에 빈으로 등록된 오브젝트의 생성 및 관계를 설정해준다.

그리고 위의 팩토리에서는 직접 오브젝트를 생성하는 코드가 있어야 하지만, ApplicationContext는 설정 정보만 Java파일 또는 xml파일로 넘기면 내부적으로 오브젝트 생성 및 관계설정을 수행한다.

이제 위의 팩토리를 스프링을 사용해서 오브젝트들을 빈으로 등록하고, ApplicationContext를 통해 꺼내어 사용해보자.

<a href="../src/springIoC/Main.java">Spring IoC Example</a>

---

위의 코드에서 ApplicationContext타입의 오브젝트에서 getBean() 을 통해 스프링 빈을 가져오는데,

왜 꼭 이름까지 필요한건가 라는 의문이 들 수 있다.

이러한 의문이 발생하는건 어찌보면 당연한데, 어짜피 클래스 정보를 넘기기 때문에 상관없지 않나? 라는 생각이 들 수 있지만

오브젝트 끼리는 타입은 동일하지만, 서로 세팅된 값이 다른 오브젝트가 충분히 있을 수 있다.

실제로 위의 코드에서 `userDaoByA`와 `userDao`는 같은 `UserDao`타입이다. 만약 파라미터에 클래스정보만 넘길 수 있다면 스프링에서 적절한 빈을 찾아주지 못할 수도 있다.

--- 

실제로 빈 컨테이너, IoC 컨테이너 스프링 컨테이너 스프링 등 다 다른말 같지만 서로 어떻게 해석하느냐에 따라 조금씩 달라질 수 있다.

엄밀하게 따지면 스프링에서 빈을 생성, 등록, 조회하는 것은 BeanFactory 이며,

ApplicationContext는 BeanFactory를 확장한 형태기 때문에, 단순히 빈 등록 및 제어 뿐만아니라 스프링에 전반적인 기능을 포함하고 있다.

