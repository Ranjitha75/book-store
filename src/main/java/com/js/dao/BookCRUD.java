package com.js.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.js.dto.Book;

public class BookCRUD {
	final static String path="com.mysql.cj.jdbc.Driver";
	final static String address="jdbc:mysql://localhost:3306/book_store?user=root&password=root";
	static Connection c;
	
	public int insertBook(Book b) {
		try {
			Class.forName(path);
			c=DriverManager.getConnection(address);
			PreparedStatement ps=c.prepareStatement("insert into book values(?,?,?,?)");
			ps.setInt(1,b.getBook_id());
			ps.setString(2,b.getBook_name());
			ps.setInt(3,b.getNo_of_pages());
			ps.setDouble(4,b.getPrice());
			return ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	 public int deleteBookById(int book_id) {
		  try {
		Class.forName(path);
			c = DriverManager.getConnection(address);
			PreparedStatement ps=c.prepareStatement("delete from book where book_id=?");
			ps.setInt(1,book_id);
			return ps.executeUpdate();
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  finally {
			  try {
 				  c.close();
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
		  
	  }
		  return 0;
	}
	  public int updateBookById(int id,Book b) {
		  try {
			Class.forName(path);
			c=DriverManager.getConnection(address);
			PreparedStatement ps=c.prepareStatement("Update Book set Book_name=?,No_Of_Pages=?,Price=? where Book_id=?");
			ps.setString(1, b.getBook_name());
			ps.setInt(2, b.getNo_of_pages());
			ps.setDouble(3, b.getPrice());
			ps.setInt(4, id);
			
			return ps.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  finally {
			  try {
				  c.close();
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
		  }
		  return 0;
	}

   public Book getBookById(int id) {
	   Book b=null;
	   try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("select * from book where book_id=?");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			b=new Book();
			b.setBook_id((rs.getInt(1)));
			b.setBook_name(rs.getString(2));
			b.setNo_of_pages(rs.getInt(3));
			b.setPrice(rs.getDouble(4));
			return b;
		} else {
			return b;
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   finally {
			  try {
				  c.close();
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
		  }
		  return b;

	   
   }
   
   public ArrayList<Book> getAllBooks() {
	   ArrayList<Book> al=new ArrayList<Book>();
	   try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("select * from Book");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Book b=new Book();
			b.setBook_id(rs.getInt(1));
			b.setBook_name(rs.getString(2));
			b.setNo_of_pages(rs.getInt(3));
			b.setPrice(rs.getDouble(4));
			al.add(b);
		}
		return al;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   finally {
		   try {
			   c.close();
		   }
		   catch (SQLException e) {
			   e.printStackTrace();
		   }
	   }
	return null;
	   
   }
   public boolean batchExecution(ArrayList<Book> books) {
	 try {
		Class.forName(path);
		c=DriverManager.getConnection(address);
		PreparedStatement ps=c.prepareStatement("insert into book values(?,?,?,?)");
		for(Book b : books) {
			ps.setInt(1, b.getBook_id());
			ps.setString(2, b.getBook_name());
			ps.setInt(3, b.getNo_of_pages());
			ps.setDouble(4, b.getPrice());
			ps.addBatch();
		}
		ps.executeBatch();
		return true;
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 finally {
		 try {
			 c.close();
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }
	return false; 
	 
   }

}
	
 