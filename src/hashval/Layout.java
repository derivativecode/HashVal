package hashval;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Layout {

	private Display display;
	private Shell shell;
	
	private Text textHash;
	private Text textFile;
	private Text textResult;
	private Text textConsole;
	private Label labelTitle;
	private Label labelFile;
	private Label labelHash;
	private Label label1;
	private Label label2;
	
	private Label labelAlgo;
	private Button rButtonSHA1;
	private Button rButtonSHA2;
	private Button rButtonMD5;
	
	private Button buttonGetSignature;
	private Button buttonFileHash;
	private Button buttonExplore;
	private Button buttonString;
	
	private Button collapseButton;
	private Button expandButton;
	
	private GridLayout gridLayout;
	
	private Group collapseGroup;
	private GridData groupGrid;
	private GridData textBoxGrid;
	private Label instructionLabel;
	private int textBoxHeight;
	private int textBoxWidth;
	
	private String selected = "";
	
	private String line = "";
	private String sha1 = "SHA1: ";
	private String sha256 = "SHA256: ";
	
	private Font heading;
	
	public Layout() {
		
		display = new Display();
		shell = new Shell(display);
		shell.setText("Hash Validation V0.2");	
		shell.setSize(900, 500);

		HashAPK apk = new HashAPK();
		
		heading = new Font(display, "Arial", 14, SWT.BOLD);
		
		gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		gridLayout.makeColumnsEqualWidth = false;
		gridLayout.marginLeft = 15;
		gridLayout.marginRight = 15;
		gridLayout.marginTop = 15;
		gridLayout.marginBottom = 15;
		
		shell.setLayout(gridLayout);
		
		// Single Column Layout (4 / 4 Columns)
		GridData gridData1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalSpan = 4;
		gridData1.widthHint = 750;
		
		// Double Column Layout (2 / 4 Columns)
		GridData gridData2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.horizontalSpan = 2;
		gridData2.widthHint = 600;
		
		// Single Cell Layout (1 / 4 Columns)
		GridData gridData3 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData3.horizontalSpan = 1;
		gridData3.widthHint = 100;
		gridData3.grabExcessHorizontalSpace = true;
		
		GridData gridData4 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.grabExcessVerticalSpace = false;
		gridData4.horizontalSpan = 2;
		gridData4.widthHint = 700;
		
		// COLLAPSE GROUP Layout
		groupGrid = new GridData(SWT.TOP, SWT.LEFT, false, false);
		groupGrid.horizontalSpan = 4;
		
		GridData labelGridData = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		labelGridData.horizontalSpan = 1;
		
		
		labelTitle = new Label(shell, SWT.CENTER);
		labelTitle.setText("Test an APK File against a given Hash.");
		labelTitle.setFont(heading);
		labelTitle.setLayoutData(gridData1);
		
		labelFile = new Label(shell, SWT.LEFT);
		labelFile.setText("Path:");
		labelFile.setLayoutData(gridData3);
		
		textFile = new Text(shell, SWT.CENTER);
		textFile.setText(" *select APK* ");
		textFile.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
		textFile.setLayoutData(gridData2);
		
		buttonExplore = new Button(shell, SWT.PUSH);
        buttonExplore.setText("...");
        buttonExplore.setLayoutData(gridData3);
		
        labelHash = new Label(shell, SWT.LEFT);
		labelHash.setText("Hash:");
		labelHash.setLayoutData(gridData3);
        
        textHash = new Text(shell, SWT.CENTER);
		textHash.setText(" *input Hash here* ");
		textHash.setForeground(display.getSystemColor(SWT.COLOR_GRAY));
		textHash.setLayoutData(gridData2);
		
		buttonString = new Button(shell, SWT.PUSH);
		buttonString.setText("Format");
		buttonString.setLayoutData(gridData3);
		
		labelAlgo = new Label(shell, SWT.LEFT);
		labelAlgo.setText("Algorithm:");
		labelAlgo.setLayoutData(gridData3);
		
		rButtonSHA1 = new Button(shell, SWT.RADIO|SWT.LEFT);
		rButtonSHA1.setText("SHA1");
		rButtonSHA1.setSelection(false);
		rButtonSHA1.setLayoutData(gridData3);
		
		rButtonSHA2 = new Button(shell, SWT.RADIO|SWT.LEFT);
		rButtonSHA2.setText("SHA256");
		rButtonSHA2.setSelection(true);
		rButtonSHA2.setLayoutData(gridData3);
		
		rButtonMD5 = new Button(shell, SWT.RADIO|SWT.LEFT);
		rButtonMD5.setText("MD5");
		rButtonMD5.setSelection(false);
		rButtonMD5.setLayoutData(gridData3);
		
		buttonGetSignature = new Button(shell, SWT.PUSH);
		buttonGetSignature.setText(" APK Signature ");
		buttonGetSignature.setLayoutData(gridData1);
		
		buttonFileHash = new Button(shell, SWT.PUSH);
		buttonFileHash.setText(" File Hash ");
		buttonFileHash.setLayoutData(gridData1);
		
					
		label1 = new Label(shell, SWT.CENTER);
		label1.setText("Result:");
		label1.setLayoutData(gridData1);
		
		textResult = new Text(shell, SWT.CENTER);
		textResult.setLayoutData(gridData1);
		
		label2 = new Label(shell, SWT.CENTER);
		label2.setText("Enter hash, select APK to test and press 'GO'.");
		label2.setLayoutData(gridData1);


		/*
		 * GROUP: Console Output & Show/Hide Switch
		 */
		collapseGroup = new Group(shell, SWT.NONE);
		collapseGroup.setLayoutData(groupGrid);
		
		instructionLabel = new Label(collapseGroup, SWT.NONE);
		instructionLabel.setText("Show Console Output");
		instructionLabel.setLayoutData(labelGridData);
		instructionLabel.pack();
		
		expandButton = new Button(collapseGroup, SWT.ARROW|SWT.DOWN);
		expandButton.setVisible(true);
		
		collapseButton = new Button(collapseGroup, SWT.ARROW|SWT.UP);
		collapseButton.setVisible(false);
			
		textConsole = new Text(collapseGroup, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP | SWT.READ_ONLY);
		
		// set height and width of Text area
		textBoxWidth = 800;
		textBoxHeight = 200;

		//textConsole.setSize(textConsole.computeSize(textBoxWidth, textBoxHeight));

		textBoxGrid = new GridData(SWT.TOP, SWT.LEFT, false, false);
		textBoxGrid.horizontalSpan = 4;
		textBoxGrid.heightHint = textBoxHeight;
		textBoxGrid.widthHint = textBoxWidth;

		textConsole.setLayoutData(textBoxGrid);
		textConsole.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		textConsole.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		textConsole.setVisible(false);


		// set the layout for the items contained by the Group as a two column grid
		collapseGroup.setLayout(new GridLayout(3, false));
		collapseGroup.layout(true);
		collapseGroup.pack(true);

		
		// Stupid Hack, I don't know why it won't show correctly bef
		toggleSwitch();
		toggleSwitch();
		
		shell.pack(true);
		
		
		/*
		 * LISTENER
		 */
		
		//anonymous listeners
		
		textHash.addListener(SWT.FocusIn, new Listener() {
		      public void handleEvent(Event e) {  
		    	textHash.setText("");
				textHash.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
		        }
		      });
		
		/* TOGGLE Console Output */
		collapseButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				toggleSwitch();
			}
		});
		
		expandButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				toggleSwitch();
			}
		});
		
		//External listeners
        buttonExplore.addSelectionListener(new SelectionAdapterFileDialog(shell, textFile, apk));
        buttonString.addSelectionListener(new SelectionAdapterStringDialog(textHash, textResult));
		rButtonSHA1.addSelectionListener(new SelectionAdapterAlgo(apk));
		rButtonSHA2.addSelectionListener(new SelectionAdapterAlgo(apk));
		rButtonMD5.addSelectionListener(new SelectionAdapterAlgo(apk));
        buttonGetSignature.addSelectionListener(new SelectionAdapterGetAPKSignature(textResult, textHash, textConsole, label1, label2, apk));
		buttonFileHash.addSelectionListener(new SelectionAdapterGetFileHash(textResult, textHash, textConsole, label1, label2, apk));
			
	}
		

	public void toggleSwitch() {
		
		// HIDE
		if (textConsole.getVisible()) {
			
			textConsole.setSize(textConsole.computeSize(1, 1));
			textBoxGrid.heightHint = 1;
			textBoxGrid.widthHint = 1;
			textConsole.setLayoutData(textBoxGrid);
				
			textConsole.setVisible(false);
			collapseButton.setVisible(false);
			expandButton.setVisible(true);
			
			textConsole.pack(true);
			shell.setSize(shell.getSize().x, (shell.getSize().y - textBoxHeight));
			gridLayout.marginBottom = 60;

			
		// SHOW	
		} else {			
			
			textConsole.setSize(textConsole.computeSize(textBoxWidth, textBoxHeight));
			textBoxGrid.heightHint = textBoxHeight;
			textBoxGrid.widthHint = textBoxWidth;
			textConsole.setLayoutData(textBoxGrid);		
			
			textConsole.setVisible(true);
			collapseButton.setVisible(true);
			expandButton.setVisible(false);
			
			//textConsole.pack(true);
			shell.setSize(shell.getSize().x, (shell.getSize().y + textBoxHeight));
			gridLayout.marginBottom = 80;
		}
		
		collapseGroup.pack(true);		
		shell.pack(true);	
	}
	
	
	
	/*
	 * EVENT LOOP
	 */
	/* Dispatch */
	public void open() {
		shell.open();
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	/* Dispose of resources */
		heading.dispose();
		System.out.println("resources disposed.");
	}
}
