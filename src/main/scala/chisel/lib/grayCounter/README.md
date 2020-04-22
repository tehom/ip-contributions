# Gray Counter

Package: grayCounter
Type: internal
Developer: Tom Breton (Tehom)
Description: Gray code counter for Chisel3

# Testing

Testers are executed as follows:

``` sbt "testOnly GrayCounter.Tester"```

# Notes

This is fairly minimal.  I don't provide decrement or decoding to
binary, much less attempt any arithmetic on Gray codes.

The class GrayCode exists to remind the programmer that a certain wire
or register is encoded as Gray, not binary.  I've provided only the
most common operations { :=, ===, =/=}.  For everything else, use
_.asGrayUInt

The class GrayCodeScala serves a similar purpose as GrayCode, but for
Scala instead of Chisel.  It was a late development and has not been
exercised much, but as it is very simple I expect little trouble from
it.  It has {==, !=}.  Use _.asGrayUInt for everything else.

GrayCounter counts upwards from 0 in Gray code.  To use it, set
io.operation to one of the opcodes { opZero, opSetRollover, opCount,
opNone}.  For opSetRollover, also set io.rollover to a rollover value
in Gray code.  Get the current count in Gray from io.out.

| Name | Action | 
| --- | --- | 
| opZero | Set the count to zero | 
| opSetRollover | Set the rollover from io.rollover | 
| opCount | Count upwards | 
| opNone | Do nothing | 

