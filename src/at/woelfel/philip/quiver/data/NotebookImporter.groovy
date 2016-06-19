package at.woelfel.philip.quiver.data

import at.woelfel.philip.quiver.main.QuiverException
import at.woelfel.philip.quiver.objects.*
import groovy.json.JsonSlurper
import org.apache.log4j.Logger

class NotebookImporter {
	static Logger log = Logger.getRootLogger()

	def static Notebook readNotebook(String file){
		return readNotebook(new File(file))
	}
	
	def static Notebook readNotebook(File nbFile){
		if(nbFile.isDirectory() && nbFile.name.endsWith(".qvnotebook")){
			// got a notebook
			File nbMetaFile = new File("${nbFile.getPath()}${File.separator}meta.json");
			log.debug("got valid notebook")

			
			if(nbMetaFile.isFile() && nbMetaFile.canRead()){
				// got a valid meta file
				log.debug("got valid notebook meta file")
				
				def jsonSlurper = new JsonSlurper()
				def nbMeta = jsonSlurper.parse(nbMetaFile)
								
				Notebook notebook = new Notebook()
				notebook.folder = nbFile
				notebook.name = nbMeta['name']
				notebook.uuid = nbMeta['uuid']
				nbFile.eachDirMatch(~/.*\.qvnote$/) { noteDir ->
					log.debug("processing note directory ${noteDir.absolutePath}")
					File nMetaFile = new File("${noteDir.getPath()}${File.separator}meta.json")
					File nContentFile = new File("${noteDir.getPath()}${File.separator}content.json")
					
					if(nMetaFile?.isFile() && nMetaFile?.canRead() && nContentFile?.isFile() && nContentFile?.canRead()){
						log.debug("got valid meta and content file for note")
						def nMeta = jsonSlurper.parse(nMetaFile)
						def nContent = jsonSlurper.parse(nContentFile)
						
						Note note = new Note()
						note.folder = noteDir
						note.title = nMeta['title']
						note.created = new Date(nMeta['created_at'].toLong()*1000L)
						note.updated = new Date(nMeta['updated_at'].toLong()*1000L)
						note.uuid = nMeta['uuid']
						note.tags = nMeta['tags']
						
						// read content

						nContent['cells'].each{ cellData ->
							log.debug("got cell with type ${cellData['type']}")
							Cell cell
							switch(cellData['type']){
								case "text":
									cell = new TextCell()
									break

								case "code":
									cell = new CodeCell()
									cell.language = cellData['language']
									break

								case "latex":
									cell = new LatexCell()
									break

								case "markdown":
									cell = new MarkdownCell()
									break

								case "diagram":
										cell = new DiagramCell()
									break

							}
							cell.data = cellData['data']
							note.cells.add(cell)
						}
						
						notebook.notes.add(note)
					}
					else{
						// TODO: error
						log.error("Can't read note meta or content file!")
						throw new QuiverException("Can't read note meta or content file!")
					}
				}

				return notebook
			}
			else{
				log.error("Can't read notebook meta file!")
				throw new QuiverException("Can't read notebook meta file!")
			}
		}
		else{
			log.error("Invalid notebook given!")
			throw new QuiverException("Invalid notebook given!")
		}
	}
}
