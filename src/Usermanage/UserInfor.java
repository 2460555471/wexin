package Usermanage;

public class UserInfor 
{
	private String openid;//�û���Ψһ��ʶ����
	private int IsSubscribe;//�Ƿ��ĸù��ںű�ʶ��ֵΪ0ʱ��������û�û�й�ע�ù��ں�
	private String nickname;//�û��ǳ�
	private int sex;//1��ʾ�У�0��Ů
	private String city;
	private String province;
	private String headimgurl;//ͷ���url
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int isIsSubscribe() {
		return IsSubscribe;
	}
	public void setIsSubscribe(int isSubscribe) {
		IsSubscribe = isSubscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int isSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

}
