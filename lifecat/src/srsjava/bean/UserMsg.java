package srsjava.bean;
/**
 * usermsg ��Ϣ��
 * @author 59682
 *
 */
public class UserMsg {
	/**
	 * id:id��,int,Ψһ,���Լ����user��
	 * nickname:�ǳ�
	 * sex:�Ա�
	 * age:����
	 * birthday:����
	 * email:����
	 */
	
	private int id;
	private String nickname;
	private String sex;
	private String age;
	private String birthday;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
