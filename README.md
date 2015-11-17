# Overview

This is a Java based solution to the [8-queens puzzle](https://en.wikipedia.org/wiki/Eight_queens_puzzle)

It aims to be a very simple solution with nothing flashy. I had planned to use concurrency or something fancy to
make it fast, but it only takes a few ms to run so is not worth it.

# Thought process

* We can only have have 1 queen per rank, so store our solution as an array of 8 ranks, 1 element per rank
* Within each rank, store a byte of 0-7 to represent the file the queen is at
* Make everything we can immutable - so no shared mutable state is floating around
* Use a queue to keep track of our partial solutions. Once a solution is finished toss it in an ArrayList
* Don't waste time going down a bad path. If at any point we see a placement is bad - don't bother placing more queens.

# Installation

* [Have jdk-8+ installed](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Have sbt 0.13.9+ installed](http://www.scala-sbt.org/download.html)

# Running

From the command line, simply execute
`sbt run`