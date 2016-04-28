#!/bin/bash
# Seed RANDOM with process id (process id = $$)

if [ "$1" != "" ] && [ "$2" != "" ]; then
	# if the first and second command line argument exist, print them
	echo "Command line arguments are $1 and $2"
elif [ "$1" != "" ]; then
	echo "$1"
else
	echo "No command line arguments"
fi

RANDOM=$$

# Generates the number of test files specified by the first command line argument.
for (( numTest=1; numTest<=$1; numTest++))
	do
		for (( c=1; c<=$2; c++ ))
			do
			    echo $RANDOM >> "test$numTest.txt"
			done
	done

# Compile java code
javac quicksort_verbose.java
javac mergesort_verbose.java

# Run quicksort_verbose and mergesort_verbose on the generated test files
# Save the out of these files.

# The size of the array to run insertion sort.
k="15"

for (( numTest=1; numTest<=$1; numTest++ ))
	do
		java quicksort_verbose "test$numTest.txt" "$k" >> "quicksort_test$numTest.txt"
		java mergesort_verbose "test$numTest.txt" >> "mergesort_test$numTest.txt"
	done

failedTest="0"

for (( numTest=1; numTest<=$1; numTest++ ))
	do
		diff "quicksort_test$numTest.txt" "mergesort_test$numTest.txt" >> "diff_test$numTest.txt"

		if [[ -s "diff_test$numTest.txt" ]]; then
			# Test failed
			echo "Test: $numTest failed."
			let "failedTest++"
						cat "quicksort_test$numTest.txt" > "quicksort_failed_test_$numTest.txt"
			cat "mergesort_test$numTest.txt" > "mergesort_failed_test_$numTest.txt"
		else
			# If the test failed, we need to copy the output of the mergesort and quicksort into specific files
			rm "diff_test$numTest.txt"
		fi
	done

if [[ "$failedTest" == 0 ]]; then
	echo "All test passed."
else
	echo "$failedTest tests failed."
fi


# Clean up
# All the txt files generated.
# All of the .class files.

rm test*.txt
rm *.class
rm quicksort_test*.txt
rm mergesort_test*.txt