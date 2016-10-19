package at.woelfel.philip.quiver.gui;

import at.woelfel.philip.quiver.objects.Note;
import at.woelfel.philip.quiver.objects.Notebook;

public interface NotebookNavListener {
	public void onNoteSelected(Note n);
	public void onNotebookSelected(Notebook n);
}
