package Usermanage;

public class UserLocation 
{
	private String UserOpenID;	//用户账号
	private int CreateTime;	//消息创建时间 （整型）
	private double Latitude;	//地理位置纬度
	private double Longitude;	//地理位置经度
	private double Precision;	//地理位置精度

	
	public String getUserOpenID() {
		return UserOpenID;
	}
	public void setUserOpenID(String userOpenID) {
		UserOpenID = userOpenID;
	}
	public int getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getPrecision() {
		return Precision;
	}
	public void setPrecision(double precision) {
		Precision = precision;
	}


}
