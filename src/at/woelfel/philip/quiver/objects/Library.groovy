package at.woelfel.philip.quiver.objects

class Library {
	// a .qvlibrary
	
	File folder
		
	def notebooks = []
	
	public String toString(){
		StringBuilder sb = new StringBuilder()
		sb.append("Library ${folder.getName()}\n")
		sb.append("Notebooks:\n")
		notebooks.each { nb ->
			sb.append("${nb.toString()}\n")
		}

		return sb.toString()
	}
}
