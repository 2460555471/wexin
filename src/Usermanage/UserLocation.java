package Usermanage;

public class UserLocation 
{
	private String UserOpenID;	//�û��˺�
	private int CreateTime;	//��Ϣ����ʱ�� �����ͣ�
	private double Latitude;	//����λ��γ��
	private double Longitude;	//����λ�þ���
	private double Precision;	//����λ�þ���

	
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
