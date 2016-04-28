#!/bin/bash

if [ "$1" != "" ] && [ "$2" != "" ] && [ "3" != "" ]; then
	# if the first and second command line argument exist, print them
	echo "Command line arguments are $1 and $2 and $3"
else
	echo "Insufficient amount of command line arguments"
fi

if [[ "$1" == "quicksort" ]]; then
	# Quicksort header file
	echo '"Sample Number","Language","Time","Number of Partitioning Stages","Number of Exchanges","Number of Compares"' >> quicksort.csv
else 
	#Mergesort header file
	echo '"Sample Number","Language","Time","Number of Recursive Calls","Number of Transitions","Number of Compares"' >> mergesort.csv
fi

version="${java -version}"

# Generate the testing sample
for (( numTest=1; numTest<=$2; numTest++))
	do
		for (( c=1; c<=$3; c++ ))
			do
			    echo $RANDOM >> "test$numTest.txt"
			done
		
		# Gets the real time of the sorting algorithm.
		exec 3>&1 4>&2
		foo=$( { time java "$1" "test$numTest.txt" 1>&3 2>&4; } 2>&1 )
		exec 3>&- 4>&-
		real=${foo#*real}
		real=${real%%s*}

		# Run the sort algorithm and store it in variables
		
	done

