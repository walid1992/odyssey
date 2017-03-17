package com.walid.martian.utils.retrofit;

/**
 * Author: walid
 * Date ： 2016/3/17 17:01
 */
public class ImageRequestBodyParams {

	private int destWidth = 768;
	private int destHeight = 1024;
	private int scaleMaxEdgeLenght;
	private int rotateDegree;
	private int destQuality = 100;
	private int maxBytes = 50;

	public static ImageRequestBodyParams getDefault() {
		return new ImageRequestBodyParams();
	}

	public int getDestWidth() {
		return destWidth;
	}

	public ImageRequestBodyParams setDestWidth(int destWidth) {
		this.destWidth = destWidth;
		return this;
	}

	public int getDestHeight() {
		return destHeight;
	}

	public ImageRequestBodyParams setDestHeight(int destHeight) {
		this.destHeight = destHeight;
		return this;
	}

	public int getRotateDegree() {
		return rotateDegree;
	}

	public ImageRequestBodyParams setRotateDegree(int rotateDegree) {
		this.rotateDegree = rotateDegree;
		return this;
	}

	public int getDestQuality() {
		return destQuality;
	}

	public ImageRequestBodyParams setDestQuality(int destQuality) {
		this.destQuality = destQuality;
		return this;
	}

	public int getMaxBytes() {
		return maxBytes * 1024;
	}

	public int getScaleMaxEdgeLenght() {
		return scaleMaxEdgeLenght;
	}

	public ImageRequestBodyParams setScaleMaxEdgeLenght(int scaleMaxEdgeLenght) {
		this.scaleMaxEdgeLenght = scaleMaxEdgeLenght;
		return this;
	}

	public ImageRequestBodyParams setMaxBytes(int maxBytes) {
		this.maxBytes = maxBytes;
		return this;
	}
}
