package Bean;

import java.util.List;

public class Excel {

	private String title;
	private String filename;
	private String sql;
	
	private List<String> coList;
	
	public Excel() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<String> getCoList() {
		return coList;
	}

	public void setCoList(List<String> coList) {
		this.coList = coList;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
