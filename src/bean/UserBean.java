package bean;

/**
 * 
 * @Description 用户实体类
 * @author liangyx
 * @date 2013-7-1
 * @version V1.0
 */
public class UserBean extends Pojo{
	private String uname;
	private String password;
	private int sex;
	private int age;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
