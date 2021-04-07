package ua.lviv.lgs;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App 
{
    public static void main( String[] args ){
    	
    	Session session = HibernateSessionFactory.getSessionFactory().openSession();
    	
    	Transaction transaction = session.beginTransaction();
    	
    	Post post1 = new Post("значення пост 1");
    	
    	Comment comment1 = new Comment("імя автора 1");
    	comment1.setPost(post1);
    	Comment comment2 = new Comment("імя автора 2");
    	comment2.setPost(post1);
    	Comment comment3 = new Comment("імя автора 3");
    	comment3.setPost(post1);
    	Comment comment4 = new Comment("імя автора 4");
    	comment4.setPost(post1);
    	post1.setComments(new HashSet<>(Arrays.asList(comment1, comment2, comment3, comment4)));
    	session.persist(post1);
    	transaction.commit();
    	
    	Post postDB = (Post) session.get(Post.class, 1);
    	System.out.println(postDB + " містить " +postDB.getComments());
    	
    	Comment commentDB = (Comment) session.get(Comment.class, 1);
    	System.out.println(commentDB + " посилається " +commentDB.getPost());
    	
    	session.close();
       
    }
}
