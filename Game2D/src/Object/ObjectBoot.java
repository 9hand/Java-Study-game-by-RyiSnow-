package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBoot extends SuperObject{
	public ObjectBoot() {
		name = "Boot";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/obj/Shoes.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
