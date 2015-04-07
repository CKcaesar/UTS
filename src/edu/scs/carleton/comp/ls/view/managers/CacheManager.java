package edu.scs.carleton.comp.ls.view.managers;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;




import edu.comp.domain.StuCourse;
//import edu.scs.carleton.comp.ls.book.Author;
//import edu.scs.carleton.comp.ls.book.Publisher;
import edu.comp.domain.Term;
import edu.comp.domain.Course;
import edu.comp.dbam.DBCourse;
import edu.comp.dbam.DBStuCourse;
//import edu.scs.carleton.comp.ls.dbam.DBAuthor;
//import edu.scs.carleton.comp.ls.dbam.DBPublisher;
import edu.comp.dbam.DBTerm;
import edu.scs.carleton.comp.ls.utils.ConfigProperties;
import edu.scs.carleton.comp.ls.view.utils.SelectItem;
import edu.scs.carleton.comp.ls.view.utils.Utils;

@Singleton
@ManagedBean
@ApplicationScoped
public class CacheManager {
	
	private static final String ROOT_DIR = Utils.getContextRoot();
	private static final String WEB_INF = "WEB-INF/classes";
	
	private static SelectItem[] terms=null;
	private static SelectItem[] courses=null;
	private static SelectItem[] type=null;
	private static SelectItem[] mycourses=null;
	protected static HttpSession session;
	
	//private static SelectItem[] authors = null;
	//private static SelectItem[] publishers = null;
	
	public CacheManager () { 
		//if (authors == null && publishers == null)
		if (terms==null||courses==null)
			init();
		if (mycourses==null)
			init();
	}
	
	private void init () 
	{	
		String _rootDir = ROOT_DIR + WEB_INF;
		
		ConfigProperties.getInstance(_rootDir);
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		session = (HttpSession) facesContext.getExternalContext().getSession(true);
		
		//setAuthorsList () ;
		//setPublishersList ();
		setListOfTerms();
		setListOfCourses();
		setTypeList();
		setListOfMyCourses();
	}
	
	private static void setListOfTerms () {
		if (terms == null) {
			DBTerm dbTerm = new DBTerm();			
			List<Object> t = dbTerm.getListOfTerms(); 
			terms = new SelectItem[t.size()];			
			dbTerm.destroy();
			
			for (int i=0; i<t.size();i++) 
			{
				Term term = (Term)t.get(i);
				String name = term.getName();
				terms[i] = new SelectItem (term.getTermid(), name);
			}
		}
	}
	
	private static void setListOfCourses(){
		if(courses==null){
			DBCourse dbcourse = new DBCourse();						
			List<Object> c = dbcourse.getListOfCourses();
			courses=new SelectItem[c.size()];
			dbcourse.destroy();
			
			for (int i=0; i<c.size();i++)
			{
				Course course = (Course)c.get(i);
				//int id=term.getTermid();
				String name = course.getCourseName();
				//!!!!!
				courses[i] = new SelectItem (name, course.getCourseCode());
				//System.out.println(id+name);
			}
			//courses = new SelectItem[c.size()];
			//dbcourse.destroy();		
		}
	}
	
	private static void setTypeList(){
		String[] arry={"assignment","midterm","project","final"};
		type = new SelectItem[4];
		for(int i=0;i<4;i++){
			type[i] = new SelectItem (i, arry[i]);
		}
	}
	
	private static void setListOfMyCourses(){
		if(mycourses==null){
			DBStuCourse dbmycourse=new DBStuCourse();
			List<Object> m=dbmycourse.getListOfMyCourses(session.getAttribute("username").toString());
			//List<Object> m=dbmycourse.getListOfMyCourses("001");
			mycourses=new SelectItem[m.size()];
			dbmycourse.destroy();
			
			for(int i=0;i<m.size();i++){
				StuCourse mycourse=(StuCourse)m.get(i);
				String courseID=mycourse.getCourse();
				mycourses[i]=new SelectItem(courseID, courseID);
			}
		}
	}
	
	public SelectItem[] getListOfType(){
	    return type;
	}
	
	public SelectItem[] getListOfTerms () {
		return terms;
	}
	
	public SelectItem[] getListOfCourses(){
		return courses;
	}
	
	public SelectItem[] getListOfMyCourses(){
		return mycourses;
	}
	
	public static void resetTerms () {
		clearTerms ();	
		setListOfTerms();
	}
	
	public static void resetCourses () {
		clearCourses ();	
		setListOfCourses () ;
	}
	
	public static void resetMyCourses(){
		clearMyCourses();
		setListOfMyCourses();
	}
	
	private static void clearTerms () {
		System.out.println(terms.length);
		for (int i = 0; i> terms.length; i++) {	
			terms[i] = null;
		}		
		terms = null;
	}
	
	private static void clearCourses () {
		for (int i = 0; i> courses.length; i++) {
			courses[i] = null;
		}		
		courses = null;
	}
	
	private static void clearMyCourses(){
		for(int i=0; i>mycourses.length;i++){
			mycourses[i]=null;
		}
		mycourses=null;
	}
/*	private static void setAuthorsList () {
		if (authors == null) {
			DBAuthor dbAuthor = new DBAuthor();			
			List<Object> a = dbAuthor.getListOfAuthors(); 
			authors = new SelectItem[a.size()];			
			dbAuthor.destroy();
			
			for (int i=0; i<a.size();i++) 
			{
				Author author = (Author)a.get(i);
				String name = author.getFamilyName() + ", " + author.getGivenName();
				authors[i] = new SelectItem (author.getAuthorID(), name);
			}
		}
	}
	
	private static void setPublishersList () {
		if (publishers == null) {
			DBPublisher dbPublisher = new DBPublisher();						
			List<Object> p = dbPublisher.getListOfPublishers();
			publishers = new SelectItem[p.size()];
			dbPublisher.destroy();
			
			for (int i=0; i<p.size();i++)
			{
				Publisher publisher = (Publisher) p.get(i);
				publishers[i] = new SelectItem (publisher.getPublisherID(), publisher.getName() );
			}
		}
	}
	
	public SelectItem[] getListOfAuthors () {
		return authors;
	}
	
	public SelectItem[] getListOfPublishers () {
		return publishers;
	}

	public static void resetAuthors () {
		clearAuthors ();	
		setAuthorsList () ;
	}
	
	public static void resetPublishers () {
		clearPublishers ();
		setPublishersList ();
	}
	
	private static void clearAuthors () {
		for (int i = 0; i> authors.length; i++) {
			authors[i] = null;
		}		
		authors = null;
	}
	
	private static void clearPublishers () {
		for (int i = 0; i> publishers.length; i++) {
			publishers[i] = null;
		}		
		publishers = null;
	}*/
	
	@PreDestroy
	public void destroy() {		
		//authors = null;
		//publishers = null;
		terms=null;
		courses=null;
		mycourses=null;
	}
	
	@Override
	public void finalize () throws Throwable {
		//clearAuthors();
		//clearPublishers();
		System.out.println("time to finalize");
		//clearTerms();
		//clearCourses();
		//clearMyCourses();
		//authors = null;
		//publishers = null;
	}
}
