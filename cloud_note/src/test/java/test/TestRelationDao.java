package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.andy.cloud_note.dao.RelationDao;
import cn.andy.cloud_note.entity.Book;
import cn.andy.cloud_note.entity.User;

public class TestRelationDao {
	private RelationDao rdao;
	@Before
	public void init(){
		ApplicationContext ac=new 
				ClassPathXmlApplicationContext("conf/spring-*.xml");
		rdao=ac.getBean("relationDao",RelationDao.class);
	}
	
	@Test
	public void test(){
		User user=rdao.findUserAndBooks1(
				"48595f52-b22c-4485-9244-f4004255b972");
		System.out.println("====�û���Ϣ=======");
		System.out.println("����"+user.getCn_user_name());
		System.out.println("�ǳ�"+user.getCn_user_nick());
		System.out.println("�ʼǱ�����"+user.getBooks().size());
		System.out.println("=======�ʼǱ��б�========");
		for(Book book:user.getBooks()){
			System.out.println(book.getCn_notebook_name());
		}
	}
}
