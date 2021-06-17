package hashval;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectionAdapterFileDialog extends SelectionAdapter {

	private Display display;
	private Shell parent;
	private Text textFile;
	private String selected;
	private HashAPK apk;
	

	public SelectionAdapterFileDialog(Shell parent, Text textFile, HashAPK apk) {
		display = Display.getCurrent();
		this.parent = parent;
		this.textFile = textFile;
		this.apk = apk;
	}
	

	public void widgetSelected(SelectionEvent e) {
		FileDialog fd = new FileDialog(parent, SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.apk"};
        fd.setFilterExtensions(filterExt);
        selected = fd.open();
        
        if (selected != null) {
        	System.out.println("[FD] File selected: " + selected);
        
        	apk.setFileSelected(selected);
        	System.out.println("[FD] APK= " + apk.getFileSelected());
        	textFile.setText(selected);
        	textFile.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
        }
	}
	

	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}
}