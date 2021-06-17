package hashval;

import java.util.regex.Pattern;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterStringDialog extends SelectionAdapter {

	//private Display display;
	private Text textHash;
	private Text textResult;
	private String s;
	public SelectionAdapterStringDialog(Text textHash, Text textResult) {
		
		//display = Display.getCurrent();
		this.textHash = textHash;
		this.textResult = textResult;
	}
	
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		s = textHash.getText();
		
		System.out.println("SASD widgetSelected: " + s);
		
		stringCheck(s);
	}	


	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub	
	}

	// Test Hash-String against REGEX
	public void stringCheck(String s) {
				
		//SHA256 REGEX
		Pattern regex1 = Pattern.compile("^[A-Fa-f0-9]{64}$"); 
		Pattern regex2 = Pattern.compile("^([0-9A-F]{2}[:]){59}([0-9A-F]{2})$");
		
		//String checked = s;
		
		//str match
		if (regex1.matcher(s).matches()) {
			textHash.setText(stringConvert(s));
			textResult.setText("Formatted!");
		}
		else if (regex2.matcher(s).matches()) {
			textResult.setText("VALID!");
		}
		else {
			textResult.setText("Invalid format.");
		}
	}
	
	
	// Convert String to XX:YY:ZZ format
	public String stringConvert(String s) {
		
		String hash = s.toUpperCase();
		String converted = insertPeriodically(hash, ":", 2);
		
		return converted;
	}
	
	
	// Insert ":" every two characters
	public static String insertPeriodically(String text, String insert, int period) {
		    StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length()/period)+1);

		    int index = 0;
		    String prefix = "";
		    while (index < text.length()) {
		        // Don't put the insert in the very first iteration.
		        // This is easier than appending it *after* each substring
		        builder.append(prefix);
		        prefix = insert;
		        builder.append(text.substring(index, Math.min(index + period, text.length())));
		        index += period;
		    }
		    return builder.toString();
		}
}
