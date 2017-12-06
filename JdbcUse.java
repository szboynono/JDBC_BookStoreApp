

import java.util.*;
import java.net.*;
import java.text.*;
import java.lang.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;




public class JdbcUse {
    private Connection conDB;   // Connection to the database system.
    private String url;// URL: Which database?
    PreparedStatement querySt   = null;
    String            queryText = ""; 
    ResultSet         answers   = null;
    
    public JdbcUse () {
        // Set up the DB connection.
        try {
            // Register the driver with DriverManager.
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // URL: Which database?
        url = "jdbc:db2:c3421a";

        // Initialize the connection.
        try {
            // Connect with a fall-thru id & password
            conDB = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.out.print("\nSQL: database connection error.\n");
            System.out.println(e.toString());
            System.exit(0);
        }    

        // Let's have autocommit turned off.  No particular reason here.
        try {
            conDB.setAutoCommit(false);
        } catch(SQLException e) {
            System.out.print("\nFailed trying to turn autocommit off.\n");
            e.printStackTrace();
            System.exit(0);
        }
        
        
    
    }
    
    public Customer find_customer(int cid) {
    	boolean inDB = false;
    	Customer cus = null;
    	queryText = "SELECT * " + 
                  "FROM yrb_customer " + 
                  "WHERE cid = ?";
                
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute the query.
        try {
            querySt.setInt(1, cid);
            answers = querySt.executeQuery();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        try {
        	//if any answer exist
            if (answers.next()) {
                inDB = true;
                cus = new Customer();
                cus.setoCid(answers.getInt("cid"));
                cus.setoName(answers.getString("name"));
                cus.setoCity(answers.getString("city"));
            } else {
                inDB = false;
            }
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
           
        }
        try {
			answers.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			querySt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
		return cus;
        
    }
    
    public void update_customer(int cid, String option,String new_stuff){
    	queryText = "UPDATE yrb_customer set "+option+"= ? where cid = ?";
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute 
        try {
   
            querySt.setString(1, new_stuff);
            querySt.setInt(2, cid);
            querySt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
        }
        try {
			answers.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			querySt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			conDB.commit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    }
    
    public List<Category> fetch_categories(){
    	List<Category> catList = new ArrayList<>(); 
    	queryText = "SELECT * " + 
                  	"FROM yrb_category ";
                  	
                
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute the query.
        try {
            answers = querySt.executeQuery();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        try {
        	//if any answer exist
            while(answers.next()){
            	Category cate = new Category();
            	cate.setoCat(answers.getString("cat"));
            	catList.add(cate);
            }
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }
        
    	
		return catList;
    }
    
    public List<Book> find_book(String cat, String title){
    	List<Book> bookList = new ArrayList<>(); 
    	queryText = "SELECT * FROM yrb_book WHERE cat = ? AND (title LIKE ?)";
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute the query.
        try {
           	 querySt.setString(1, cat);
             querySt.setString(2, "%" + title + "%");
            answers = querySt.executeQuery();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        try {
        	//if any answer exist
            while(answers.next()){
            	Book booky = new Book();
            	booky.setoTitle(answers.getString("title"));
            	booky.setoCat(answers.getString("cat"));
            	booky.setoLanguage(answers.getString("language"));
            	booky.setoWeight(answers.getInt("weight"));
            	booky.setoYear(answers.getInt("year"));
            	bookList.add(booky);
            }
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }
        
		return bookList;
    	
    }
    
    public Offer min_price(String title, int year , int cid){
    	Offer offe = null;

    	queryText = "SELECT O.club, O.title, O.year, O.price, M.cid FROM yrb_offer O, yrb_member M  where title = ? and year = ? and O.club = M.club and M.cid = ? order by price limit 1";
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute the query.
        try {
          	 querySt.setString(1, title);
          	 querySt.setInt(2, year);
          	 querySt.setInt(3, cid);
             answers = querySt.executeQuery();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        try {
        	//if any answer exist
            if (answers.next()) {
                offe = new Offer();
                offe.setoTitle(answers.getString("title"));
                offe.setoClub(answers.getString("club"));
                offe.setoPrice(answers.getFloat("price"));
                offe.setoYear(answers.getInt("year"));
            } 
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        return offe;
    }
    
    public void insert_purchase(Purchase pur){
    	queryText = "INSERT INTO yrb_purchase (cid,club,title,year,when,qnty) values (?,?,?,?,?,?)";
    	try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
    	//Execute the query.
        try {
          	 querySt.setInt(1, pur.getoCid());
          	 querySt.setString(2, pur.getoClub());
          	 querySt.setString(3, pur.getoTitle());
          	 querySt.setInt(4, pur.getoYear());
          	 querySt.setTimestamp(5, pur.getoWhen());
          	 querySt.setInt(6, pur.getoQnty());
             querySt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        try {
			conDB.commit();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    }
    
    public List<Purchase> find_purchase(int cid) {
    	List<Purchase> pcList = new ArrayList<>();
    	queryText = "SELECT * " + 
                  	"FROM yrb_purchase " + 
                  	"WHERE cid = ?";
                
    	//prepare
        try {
            querySt = conDB.prepareStatement(queryText);
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in prepare");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        //Execute the query.
        try {
            querySt.setInt(1, cid);
            answers = querySt.executeQuery();
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in execute");
            System.out.println(e.toString());
            System.exit(0);
        }
        
        try {
        	//if any answer exist
            while (answers.next()) {
            	Purchase pc = new Purchase();
                pc = new Purchase();
                pc.setoCid(answers.getInt("cid"));
                pc.setoClub(answers.getString("club"));
                pc.setoWhen(answers.getTimestamp("when"));
                pc.setoQnty(answers.getInt("qnty"));
                pc.setoTitle(answers.getString("title"));
                pc.setoYear(answers.getInt("year"));
                pcList.add(pc);
            } 
        } catch(SQLException e) {
            System.out.println("SQL#1 failed in cursor.");
            System.out.println(e.toString());
            System.exit(0);
        }
    	
		return pcList;
        
    }
    
    
    
    public Connection getConDB() {
		return conDB;
	}

	public static void main(String[] args){
//    	JdbcUse ju = new JdbcUse();
//    	System.out.println(ju.find_customer(1).toString());
//    	ju.update_customer(1, "name", "Tracy Turnip");
//    	System.out.println(ju.find_customer(1).toString());
//    	System.out.println(ju.find_customer(0).toString());
    	
//    	for(int i = 0; i < ju.find_purchase(1).size();i++){
//    		System.out.println(ju.find_purchase(1).get(i));
//    	}
//    	Purchase pcc = new Purchase();
//    	pcc.setoCid(1);
//    	pcc.setoClub("Basic");
//    	pcc.setoTitle("Will Snoopy find Lucy?");
//    	pcc.setoQnty(12);
//    	pcc.setoWhen(new java.sql.Timestamp(System.currentTimeMillis()));
//    	pcc.setoYear(1985);
//    	ju.insert_purchase(pcc);
//    	for(int i = 0; i < ju.find_purchase(1).size();i++){
//    		System.out.println(ju.find_purchase(1).get(i));
//    	}
    	// Close the connection.
//    	try {
//			ju.conDB.commit();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    	try {
//			ju.conDB.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    
    }
}



