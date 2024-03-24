#!/bin/bash
make UVa100
time cat ../input5.txt | ./UVa100
rm -f ./UVa100

