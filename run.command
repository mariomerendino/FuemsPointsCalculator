#!/bin/bash
pwd
cd $0/..
javac Backend/*.java
javac -classpath Packages/*.jar Frontend/*.java
javac Start.java