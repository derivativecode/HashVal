package hashval;

public class HashAPK {

	private String algorithm = "SHA256";
	private String fileSelected;
	private String hash;
	
	public HashAPK() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getFileSelected() {
		return fileSelected;
	}

	public void setFileSelected(String fileSelected) {
		this.fileSelected = fileSelected;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
