package pacman.id3;

public class ID3Node {
    
    private ArrayList<ID3Node> children;
	private ID3Attribute attribute = null;
	private String branchName;
	private String classLabel;

	public ID3Node() {
		children= new ArrayList<>();
		branchName="";
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

	public void setClassLabel(String classLabel) {
		this.classLabel = classLabel;
	}
	
	public String getClassLabel() {
		return classLabel;
	}

	public String getBranchName() { 
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
