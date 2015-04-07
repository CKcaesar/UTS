package edu.scs.carleton.comp.ls.view.utils;

public interface IEvent {
	public static final int USER_CREATE = 1;
	public static final int USER_DELETE = 2;
	public static final int USER_UPDATE = 3;
	public static final int TITLE_CREATE = 4;
	public static final int TITLE_UPDATE = 5;
	public static final int TITLE_DELETE = 6;
	public static final int ITEM_CREATE = 7;
	public static final int ITEM_UPDATE = 8;
	public static final int ITEM_EXPIRED = 9;
	public static final int AUTHOR_CREATE = 10;
	public static final int AUTHOR_UPDATE = 11;
	public static final int AUTHOR_DELETE = 12;
	public static final int PUBLISHER_CREATE = 13;
	public static final int PUBLISHER_UPDATE = 14;
	public static final int PUBLISHER_DELETE = 15;
	public static final int LOAN_CREATE = 16;
	public static final int LOAN_UPDATE = 17;
	public static final int EMPTY_SEARCH = 18;
	public static final int EMPTY_CREATE = 19;
	
	public static final int TERM_CREATE=1;
	public static final int TERM_SET=5;
	public static final int TERM_DELETE=2;
	public static final int COURSE_CREATE=3;
	public static final int COURSE_DELETE=4;
	public static final int Assignment_CREATE = 0;
	public static final int Assignment_UPLOAD=6;
	public static final int GRADING=7;
}
