package srsjava.bean;
/**
 * �ռ���
 * @author 59682
 *
 */
public class Diary {
	/**
	 * id:�����û���id
	 * name:�ռ�����
	 * description:�ռ�����
	 * date:�ռǵ�ʱ��
	 * path:�ռǴ洢·��
	 */
	
	private int id;
	private String name;
	private String description;
	private String date;
	private String path;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
