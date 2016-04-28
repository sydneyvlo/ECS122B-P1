#!/bin/bash

if [ "$1" != "" ] && [ "$2" != "" ] && [ "3" != "" ]; then
	# if the first and second command line argument exist, print them
	echo "Command line arguments are $1 and $2 and $3"
else
	echo "Insufficient amount of command line arguments"

if [[ "$1" == "quicksort" ]]; then
	# Quicksort header file
	echo '"Sample Number","Language","Time","Number of Partitioning Stages","Number of Exchanges","Number of Compares"' >> quicksort.csv
else 
	#Mergesort header file
	echo '"Sample Number","Language","Time","Number of Recursive Calls","Number of Transitions","Number of Compares"' >> mergesort.csv
fi

# Generate the testing sample
for (( numTest=1; numTest<=$1; numTest++))
	do
		for (( c=1; c<=$3; c++ ))
			do
			    echo $RANDOM >> "test$numTest.txt"
			done
	done



