#!/bin/bash
if [ "$1" = "" ]
then
    N=5
else
    N=$1
fi

javac Main.java
time cat ../input$N.txt | java Main
rm -f *~ *.class
