package gui;

import javax.swing.Icon;

public class IListItemData {

	private String labelName;

	private Icon labelImage;
	private String path;

	public IListItemData() {

 

	}

	

	public IListItemData(String labelName, Icon labelImage,String path) {

		super();

		this.labelName = labelName;
		this.labelImage = labelImage;
		this.path=path;

	}

 

	public String getLabelName() {

		return labelName;

	}

	public void setLabelName(String labelName) {

		this.labelName = labelName;

	}

	public Icon getLabelImage() {

		return labelImage;

	}

	public void setLabelImage(Icon labelImage) {

		this.labelImage = labelImage;

	}

	public void setPath(String path) {
		this.path=path;
	}
	
	public String getPath() {
		return path;
	}
	
	
}
