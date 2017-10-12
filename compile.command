#!/bin/bash
pwd
cd $0/..
javac -classpath Packages/*.jar Backend/*.java Frontend/*.java
javac Start.java