package BasicElementMessage;

public class ImageMessage extends BaseMessage
{
	private Image Image;//就是一个名字而已，Image对象只有名字

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
}
