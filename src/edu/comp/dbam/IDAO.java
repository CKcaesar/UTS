package edu.comp.dbam;

public abstract interface IDAO
{
  public abstract boolean create(Object paramObject);
  
  public abstract boolean update(Object paramObject);
  
  public abstract boolean delete(int paramInt);
  
  public abstract boolean delete(String paramString);
  
  public abstract boolean delete(Object paramObject);
  
  public abstract boolean deleteAll();
  
  public abstract Object findByPrimaryKey(int paramInt);
  
  public abstract Object findByPrimaryKey(String paramString);
  
  public abstract Object find(Object paramObject);
  
  public abstract int getCount();
}

/* Location:           D:\KaiChen\WebApp4\WebContent\WEB-INF\lib\app-model-3.1.1.1.jar
 * Qualified Name:     edu.scs.carleton.comp.ls.dbam.IDAO
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.0.1
 */