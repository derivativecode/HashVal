# HashVal
 Simple tool to generate checksums and read APK signatures, aswell as verify them agains a given hash.
 
 Select a file to either
 - generate Checksums (SHA1, SHA256, MD5) using Windows' CMD-Tool **certutil** (with *-hashfile* and *algorithm* flag)
 - extract a Signature from an APK-File using JAVAs **keytool** (with *-printcert* *-jarfile* flags)

Programmatically verify these hashes against a give hash, i.e. from the Website you've downloaded the file


# Requirements

As of now, you will have to set the path to JRE (Java Runtime Environment) for using the keytool, i.e. *..\path\to\jdk-13.0.1\bin\keytool.exe* in [SelectionAdapterGetAPKSignature](https://github.com/derivativecode/HashVal/blob/abf00ab7ba09d0667fa8fb2e5eea50fd17a0487d/src/hashval/SelectionAdapterGetAPKSignature.java#L59)

Built using:
- **JavaSE 12** [JDK12](https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html)
- **SWT** - [Standard Widget Toolkit](https://git.eclipse.org/c/platform/eclipse.platform.swt.git/)


# Credits
Thanks to **Ammianus** for his [Collapsible Text Area Snippet](https://librixxxi.blogspot.com/2011/04/collapsible-swt-text-area-snippet.html)
