package at.woelfel.philip.quiver.gui

import javax.swing.BoxLayout
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.border.TitledBorder

import jsyntaxpane.DefaultSyntaxKit;
import at.woelfel.philip.quiver.objects.Note
import at.woelfel.philip.quiver.objects.Notebook
import at.woelfel.philip.quiver.objects.Cell.Type

class NoteViewer extends JPanel implements NotebookNavListener {

	Note note
	List noteAreas = []
	
	public NoteViewer(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS))
	}
	
	private buildUi(){
		if(!note){
			return
		}
		removeAll()
		
		noteAreas = []
		note.cells.each { c->
			if(c.type == Type.TEXT){
				JEditorPane tmpEP = new JEditorPane()
				tmpEP.setContentType("text/html")
				tmpEP.setText(c.data.toString())
				tmpEP.setBorder(new TitledBorder(c.type.toString()))
				noteAreas.add(tmpEP)
				add(tmpEP)
			}
			else if(c.type == Type.CODE){
				DefaultSyntaxKit.initKit()
				JEditorPane tmpEP = new JEditorPane()
				tmpEP.setContentType("text/${c.language}")
				tmpEP.setText(c.data.toString())
				tmpEP.setBorder(new TitledBorder(c.type.toString()))
				noteAreas.add(tmpEP)
				add(tmpEP)
			}else{
				JTextArea tmpArea = new JTextArea(c.data)
				tmpArea.setBorder(new TitledBorder(c.type.toString()))
				noteAreas.add(tmpArea)
				add(tmpArea)
			}
		}
		
		revalidate()
		repaint()
	}
	
	@Override
	public void onNoteSelected(Note n) {
		note = n
		buildUi()
	}

	@Override
	public void onNotebookSelected(Notebook n) {
		// TODO: onNotebookSelected
	}

}
