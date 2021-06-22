package Dao;

public class Goods {
	
	private String id;
	private String imgurl;
	private String name;
	private String price;
	private String nownum;
	private String remark;
	private String sellerid;
	private String seller;
	private String status;
	private String type;
	private String date;
	
	private Boolean statustype;
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNownum() {
		return nownum;
	}
	public void setNownum(String nownum) {
		this.nownum = nownum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public Boolean getStatustype() {
		return statustype;
	}
	public void setStatustype(Boolean statustype) {
		this.statustype = statustype;
	}

}
