package at.woelfel.philip.quiver.objects

class Note {
	
	// a .qvnote file
	
	File folder
	
	String title
	String uuid
	
	Date created
	Date updated
	
	def tags = []
	def cells = []


	public String toString(){
		StringBuilder sb = new StringBuilder()

		sb.append("Note $title\n")
		sb.append("\tUUID: $uuid\n")
		sb.append("\tCreated: ${created.format('dd-MM-yyyy HH:mm:ss')}\n")
		sb.append("\tUpdated: ${updated.format('dd-MM-yyyy HH:mm:ss')}\n")
		sb.append("\tTags: ${tags.join(', ')}\n")
		cells.each { cell ->
			sb.append("${cell.toString()}")
		}

		return sb.toString()
	}
}
