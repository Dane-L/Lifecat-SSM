package srsjava.bean;
 /**
  * user ������
  * @author ten
  *
  */
public class User {
	/**
	 * id:Ψһid��,int
	 * name:�û���
	 * password:�û�����
	 * level:�û�Ȩ��
	 */
  
    private int id;
    private String name; 
    private String password;
    private String level;
    
    public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }  
}