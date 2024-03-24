#!/bin/bash
make UVa100

if [ "$1" = "" ]
then
    N=5
else
    N=$1
fi

cat ../input$N.txt | ./UVa100
rm -f UVa100
