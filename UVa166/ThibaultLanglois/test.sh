#!/bin/bash
if [ "$1" = "" ]
then
    N=4
else
    N=$1
fi

javac Main.java
echo "Input: "
cat data/input$N.txt
time cat data/input$N.txt | java Main
rm -f *~ *.class
