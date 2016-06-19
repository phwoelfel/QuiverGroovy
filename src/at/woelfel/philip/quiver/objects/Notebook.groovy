package at.woelfel.philip.quiver.objects

class Notebook {
	
	// a .qvnotebook file
	
	File folder
	
	String name
	String uuid
	def notes = []

	public String toString(){
		StringBuilder sb = new StringBuilder()
		sb.append("Notebook $name\n")
		sb.append("\tUUID:$uuid\n")
		sb.append("Notes:\n")
		notes.each { note ->
			sb.append("${note.toString()}\n")
		}

		return sb.toString()
	}
}
