package at.woelfel.philip.quiver.gui

import java.awt.BorderLayout

import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane

import org.apache.log4j.Logger

import at.woelfel.philip.quiver.data.NotebookImporter
import at.woelfel.philip.quiver.objects.Library
import at.woelfel.philip.quiver.objects.Notebook

class QuiverMainGui extends JFrame {
	JFileChooser fc = new JFileChooser()
	
	public QuiverMainGui(){
		Logger log = Logger.getRootLogger()
		setTitle("Quiver Reader");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		log.info("choooseee")
		int ret = fc.showOpenDialog(null)
		if(ret == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile()
			
			if(f.name.endsWith(".qvnotebook")){
				log.debug("loading notebook $f.name")
				Notebook nb = NotebookImporter.readNotebook(f)//"/Volumes/Data/Dropbox/quiver/Quiver.qvlibrary/FB30D926-7999-4B1E-84C4-45BA1A8A1162.qvnotebook")//FB30D926-7999-4B1E-84C4-45BA1A8A1162
				NotebookNavigatorPanel navPanel = new NotebookNavigatorPanel(nb)
				add(navPanel, BorderLayout.WEST);
				
				NoteViewer nv = new NoteViewer()
				navPanel.addNotebookNavListener(nv)
				add(new JScrollPane(nv), BorderLayout.CENTER)
			}
			else if(f.name.endsWith(".qvlibrary")){
				log.debug("loading lib $f.name")
				Library lib = NotebookImporter.readLibrary(f)
				JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT)
				
				lib.notebooks.each{ nb ->
					JPanel pan = new JPanel(new BorderLayout())
					
					NotebookNavigatorPanel navPanel = new NotebookNavigatorPanel(nb)
					pan.add(navPanel, BorderLayout.WEST);
					
					NoteViewer nv = new NoteViewer()
					navPanel.addNotebookNavListener(nv)
					pan.add(new JScrollPane(nv), BorderLayout.CENTER)
					
					tabPane.addTab(nb.name, pan)
					
				}
				log.debug("finished reading library")
				add(tabPane, BorderLayout.CENTER)
			}
			
			
			setVisible(true);
		}
	}
}
