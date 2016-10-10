g++ soln.cc -o test --std=c++11
find *.in | while read line ; do ./test < $line > "${line%.*}.out" ; done
