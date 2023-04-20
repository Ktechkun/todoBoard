package com.hivel.todoBoard;


import com.hivel.todoBoard.repository.BoardRepository;
import com.hivel.todoBoard.repository.TodoRepository;
import com.hivel.todoBoard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class TodoBoardApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoBoardApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//
//// Open a new Session
//		Session session = sessionFactory.openSession();
//		// create a new user
//		Users users = new Users();
//		users.setName("John Doe");
//
//		// create a new board and assign it to the user
//		Board board = new Board();
//		board.setName("Project X Board");
//		board.setUser(users);
//
//		// create a new todo and assign it to the board
//		Todo todo = new Todo();
//		todo.setDescription("Finish feature Y");
//		todo.setBoard(board);
//		todo.setUsers(users);
//
//		// add the todo to the board's collection of todos
//		board.getTodos().add(todo);
//
//		Transaction tx = session.beginTransaction();
//		session.persist(users);
//		session.persist(board);
//		session.persist(todo);
//		tx.commit();
//
//// Close the Session when you're done with it
//		session.close();
//
//	}
}
