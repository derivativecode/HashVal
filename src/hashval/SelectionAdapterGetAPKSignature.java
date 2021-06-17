package hashval;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterGetAPKSignature extends SelectionAdapter {

	private Display display;
	private Text textResult;
	private Text textHash;
	private Text textConsole;
	private Label label1;
	private Label label2;
	
	private HashAPK apk;
	private String selected ="";
	private String selectedAlgo = "";
	
	private String sha1 = "SHA1: ";
	private String sha256 = "SHA256: ";
	private int sha1len = 55;
	private int sha256len = 103;
	
	
	public SelectionAdapterGetAPKSignature(Text textResult, Text textHash, Text textConsole, Label label1, Label label2, HashAPK apk) {
		
		this.display = Display.getCurrent();
		this.apk = apk;
		
		this.textResult = textResult;
		this.textHash = textHash;
		this.textConsole = textConsole;
		this.label1 = label1;
		this.label2 = label2;

	}


	public void widgetSelected(SelectionEvent e) {
		
		textResult.setText("...waiting...");	
		
		selected = apk.getFileSelected();
		selectedAlgo = apk.getAlgorithm();
		
		/* TODO : check Algorithm (there are no MD5s in APK Signature */
		
		
		/* TODO : Query JDK Path or get it from Environment Variables */
		
		String temp = (cmd(" \"C:\\Program Files\\Java\\jdk-13.0.1\\bin\\keytool.exe\" -printcert -jarfile " + selected ));
		System.out.println("STRING temp: " + temp);

		if (selected == null) {
			textResult.setText("invalid input file! quitting...");
			return;
		} else if (temp.equals("[keytool error: java.nio.file.NoSuchFileException: null]")) {
			textResult.setText("keytool error! quitting...");
			return;
			
		//ADD HASH CHECK HERE !!!	
		} else if (textHash.getText() == null ) {
			textResult.setText("invalid input hash! quitting...");
			return;
		}


		/* TODO : get selected Algorithm and check individually (SHA1 || SHA256) */
		
		int i = temp.indexOf(sha256);
		System.out.println("INDEX OF: " + i);
		String s = temp.substring(i+8, i+sha256len);
		textResult.setText(s);
		
		if (s.equals(textHash.getText())) {
			label2.setText("MATCH");
			label2.setForeground(display.getSystemColor(SWT.COLOR_DARK_GREEN));
		} else if (!s.equals(textHash.getText())) {
			label2.setText("MISMATCH");
			label2.setForeground(display.getSystemColor(SWT.COLOR_DARK_RED));
		}
	}
	

	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public String cmd(String command) {
		
		ArrayList<String> output = new ArrayList<String>();
		String line = "";
		textConsole.setText("");
		
		try {
		    System.out.println("[CMD] Executing command: " + command);
	    	textConsole.append("\n" + "> " + command);
		    Process p = Runtime.getRuntime().exec(command);
		    int result = p.waitFor();
		    
		    System.out.println("[CMD] Process exit code: " + result);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

		    while ((line = reader.readLine()) != null) {
				System.out.println("[CMD] " + line);
		    	textConsole.append("\n" + line);
				output.add(line);
		    }
		} catch (Exception e) {
			System.out.println("EXCEPTION trigger in cmd");
		    e.printStackTrace();
		}
		return output.toString();
	}
}
