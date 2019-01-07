package pacman.id3;

import pacman.game.Constants;

import java.util.ArrayList;

public class ID3Node {
    
    private ArrayList<ID3Node> children = new ArrayList<>();
	private ID3Attribute attribute = null;
	private Object branchName = null;
	private Constants.MOVE classLabel;

	public ID3Node() {
	}

	public ArrayList<ID3Node> getChildrenNodes() {
		return children;
	}

	public void setChildrenNodes(ArrayList<ID3Node> children) {
		this.children = children;
	}
	
	public void addChildNode(ID3Node node) {
		children.add(node);
	}

	public ID3Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(ID3Attribute attribute) {
		this.attribute = attribute;
	}

	public void setClassLabel(Constants.MOVE classLabel) {
		this.classLabel = classLabel;
	}
	
	public Constants.MOVE getClassLabel() {
		return classLabel;
	}

	public Object getBranchName() {
		return branchName;
	}

	public void setBranchName(Object branchName) {
		this.branchName = branchName;
	}

	@Override
	public String toString() {
		return "Node [childrenNodes=--------" + children + "-------------, branchName=" + branchName + ", classLabel=" + classLabel
				+ "]";
	}

	public void PrintPretty(String indent, boolean last)
	{
		System.out.print(indent);
		if (last)
		{
			System.out.print("\\-");
			indent += "  ";
		}
		else
		{
			System.out.print("|-");
			indent += "| ";
		}
		System.out.print(branchName + "-");
		if(attribute!=null) {
			System.out.println(attribute);
		}
		else {
			System.out.println(classLabel);
		}

		for (int i = 0; i < children.size(); i++) {
			children.get(i).PrintPretty(indent, i == children.size()-1);
		}
	}
}
