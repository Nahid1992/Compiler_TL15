# Compiler for TL15.0 Language

### How to Run:
##### Running on Linux:
	To build: ./build.sh
	To run: ./exec.sh test/sqrt1.tl

Note: exec.sh contains the input file name (default: toy_language.tl)
The token file is created according to the .tl file name. (for example: toy_language.tok)

##### Running on Windows:
	To build: javac -d build/classes -sourcepath src src/compiler_project/Compiler_Project.java
	To run: java -classpath build/classes compiler_project.Compiler_Project test/sqrt1.tl
OR
	run inputfilename
