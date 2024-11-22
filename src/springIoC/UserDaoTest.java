package org.example.springIoC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) {

		// 기존에 UserDaoTest가 갖고 있던 관심사인, 사용할 Dao에 적절한 ConnectionMaker의 구체클래스를 생성후 적용하는 것을
		// DaoFactory에다가 넘김 -> IoC
		/*DaoFactory daoFactory = new DaoFactory();
		UserDao dao = daoFactory.userDao();
		dao.get();
		dao.add();*/

		// 이제 스프링의 Bean을 통해 스프링의 IoC를 써볼 차례임
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

		UserDao dao = context.getBean("userDao", UserDao.class);
		// 어짜피 userDao 객체를 들고오는건 똑같은데, 뭐하러 이름까지 찾는건가
		// 이유는 다양한 설정정보의 userDao를 구분하기 위함이다.
		dao.get();
		dao.add();

		UserDao dao2 = context.getBean("userDaoByA", UserDao.class);
		dao2.get();
		dao2.add();

	}
}
