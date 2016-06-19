package at.woelfel.philip.quiver.main

import at.woelfel.philip.quiver.data.NotebookImporter
import at.woelfel.philip.quiver.gui.QuiverMainGui;
import at.woelfel.philip.quiver.objects.Notebook

import org.apache.log4j.ConsoleAppender
import org.apache.log4j.FileAppender
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.PatternLayout

class QuiverGroovyMain {

	static main(args) {
		QuiverGroovyMain m = new QuiverGroovyMain()
	}

	public QuiverGroovyMain(){
		Logger log = Logger.getRootLogger()
		log.removeAllAppenders()
		log.addAppender(new ConsoleAppender(new PatternLayout("%d{ISO8601} %p: %m%n")))
		//log.addAppender(new FileAppender(new PatternLayout("%d{ISO8601} %p: %m%n"), 'output.log'))
		log.level = Level.INFO

		QuiverMainGui mg = new QuiverMainGui()
	}

}
