package Dao;

public class Order {
	
	private String id;           //订单编号
	private String goods;		//商品名称	
	private String goodsid;		//商品编号
	private String price;		//商品单价
	private String num;			//购买数量
	private String sum;			//小计
	private String tradedate;	//交易时间
	private String buyer;		//买家昵称
	private String buyerid;		//买家编号
	private String seller;		//卖家昵称
	private String sellerid;	//卖家编号
	private String buyerlocation;	//买家地址
	private String buyerphone;		//买家电话
	private String sellerlocation;	//卖家地址
	private String sellerphone;		//卖家电话
	private String tradestatus;		//交易状态
	private String sellerstatus;	//卖家删除状态
	private String buyererstatus;	//买家删除状态
	private String imgurl;	//图片
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getTradedate() {
		return tradedate;
	}
	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getBuyerlocation() {
		return buyerlocation;
	}
	public void setBuyerlocation(String buyerlocation) {
		this.buyerlocation = buyerlocation;
	}
	public String getBuyerphone() {
		return buyerphone;
	}
	public void setBuyerphone(String buyerphone) {
		this.buyerphone = buyerphone;
	}
	public String getSellerlocation() {
		return sellerlocation;
	}
	public void setSellerlocation(String sellerlocation) {
		this.sellerlocation = sellerlocation;
	}
	public String getSellerphone() {
		return sellerphone;
	}
	public void setSellerphone(String sellerphone) {
		this.sellerphone = sellerphone;
	}
	public String getTradestatus() {
		return tradestatus;
	}
	public void setTradestatus(String tradestatus) {
		this.tradestatus = tradestatus;
	}
	public String getSellerstatus() {
		return sellerstatus;
	}
	public void setSellerstatus(String sellerstatus) {
		this.sellerstatus = sellerstatus;
	}
	public String getBuyererstatus() {
		return buyererstatus;
	}
	public void setBuyererstatus(String buyererstatus) {
		this.buyererstatus = buyererstatus;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
