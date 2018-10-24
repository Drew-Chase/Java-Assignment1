package tk.dccraft.Assignment_3.keys;

import java.io.UnsupportedEncodingException;

import tk.dccraft.init.Main;

@SuppressWarnings("all")
public class KeyBreaker extends Main {

	private String key = "";
	private double keyRate = 0.0;

	public KeyBreaker(String key, double keyRate) {
		this.key = key;
		this.keyRate = keyRate;
	}

	public int getKeyLength() {
		try {
			byte[] utf8String = key.getBytes("UTF-8");
			return utf8String.length * 8;//Converts the byte to bits
		} catch (UnsupportedEncodingException e) {
			print("Could not convert the key to utf-8 byte array.  Problem occured at line 19 of "+ getClass().getName());
			return 0;
		}
	}
	
	public void setKeyLength(String key){
		this.key = key;
	}

	public double getKeyRate() {
		return keyRate;
	}

	public double getTimeInDays() {
		try {
			return 1.0;
		} catch (Exception e) {
			print("Had a problem getting time in days method in the " + getClass().getSimpleName() + " Class");
			return 0.0;
		}
	}

}
