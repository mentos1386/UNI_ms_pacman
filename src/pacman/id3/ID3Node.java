package pacman.id3;

import java.util.ArrayList;

public class ID3Node {
    
    private ArrayList<ID3Node> children = new ArrayList<>();
	private ID3Attribute attribute = null;
	private Object branchName = null;
	private ID3Label classLabel;

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

	public void setClassLabel(ID3Label classLabel) {
		this.classLabel = classLabel;
	}
	
	public ID3Label getClassLabel() {
		return classLabel;
	}

	public Object getBranchName() {
		return branchName;
	}

	public void setBranchName(Object branchName) {
		this.branchName = branchName;
	}


	public void print(String prefix, boolean isTail) {
		String name = "";
		if (classLabel == null) name = attribute.toString();
		else name = classLabel.toString();

		System.out.println(prefix + (isTail ? "└── " : "├── ") + branchName + ": " + name);
		for (int i = 0; i < children.size() - 1; i++) {
			children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
		}
		if (children.size() > 0) {
			children.get(children.size() - 1)
					.print(prefix + (isTail ?"    " : "│   "), true);
		}
	}
}
