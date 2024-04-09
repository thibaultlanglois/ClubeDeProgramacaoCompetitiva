# UVa100 - The 3n + 1 problem

- PDF: https://onlinejudge.org/external/1/100.pdf
- WEB: https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36

Solutions:

## Gabriel Li
- A solution in C.
- Accepted by online judge.

Unfortunately I could not run it locally:
```
> make UVa100.c
> cat ../test-input | ./UVa100
```
da um core dump.

The solution is equivalent to the solution from Thibault Langlois (uses DP).

## David Pinto
- A solution in Java
- Accepted by online judge
- This solution does not use Dynamic Programming.

Test with:
```
> test.sh 2
> test.sh 5
```

The test input5.txt fails.

## David Pinto__LONG
- Idem anterior but using long instead of int.
- In this case the test input5.txt works.

But it is relatively slow:
```
real	5m25.164s
user	5m25.726s
sys	0m0.180s
```

## Afonso Henrique + Márcio Milisse
- Solution in C++
- Does it pass on online judge ?

I get the error:
```
g++     UVa100.cc   -o UVa100
Error opening file
```

## Francisco Lima
- Solution in Rust
- Accepted by online judge ?
- How to run it locally ?

## Rafael Figueiredo
- Solution in Java
- Accepted by online judge
- This solution does not use Dynamic Programming.

Test with:
```
> test.sh 2
> test.sh 5
```

Fails on input 5

## Thibault Langlois
- Solution in Java
- Use Dynamic Programming
- A version that uses Integer other that use Long.

Test with:
```
> test.sh 2
> test.sh 5
```

- The int version fails on input 5 (Stack overflow)
- The long version pass on input 5:
input5.txt:
```
real	0m57.095s
user	0m59.750s
sys	0m0.962s
```

## Miguel Mendes
- Solution in Java
- Accepted by online judge
- This solution does not use Dynamic Programming.

Test with:
```
> test.sh 2
> test.sh 5
```

Fails on input 5
Stack overflow.

## Rodrigo Neves
Accepted solution !
