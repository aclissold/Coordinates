#!/bin/bash

if [[ `uname` == "Windows" ]]
then
    java -cp "libs/*;bin" com.andrewclissold.coordinates.Main
else
    java -cp "libs/*:bin" com.andrewclissold.coordinates.Main
fi
