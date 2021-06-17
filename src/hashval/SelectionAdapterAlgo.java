package hashval;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class SelectionAdapterAlgo extends SelectionAdapter {

	private HashAPK apk;
	
	
	public SelectionAdapterAlgo(HashAPK apk) {
		this.apk = apk;
	}

	
	public void widgetSelected(SelectionEvent e) {

		// Get Hash Algorithm from Button-Text and set it in APK-Object
		
		Button source = (Button) e.getSource();  
        if(source.getSelection())  {
        	apk.setAlgorithm(source.getText());
        }
             
	}
	
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}
}
