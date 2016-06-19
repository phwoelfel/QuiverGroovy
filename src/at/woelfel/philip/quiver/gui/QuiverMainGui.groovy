package at.woelfel.philip.quiver.gui

import java.awt.BorderLayout

import javax.swing.JFrame

import at.woelfel.philip.quiver.data.NotebookImporter
import at.woelfel.philip.quiver.objects.Notebook

class QuiverMainGui extends JFrame {
	public QuiverMainGui(){
		setTitle("Quiver Reader");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Notebook nb = NotebookImporter.readNotebook("/Volumes/Data/Dropbox/quiver/Quiver.qvlibrary/FB30D926-7999-4B1E-84C4-45BA1A8A1162.qvnotebook")
		
		NotebookNavigatorPanel navPanel = new NotebookNavigatorPanel(nb)
		add(navPanel, BorderLayout.WEST);
		
		setVisible(true);
	}
}
