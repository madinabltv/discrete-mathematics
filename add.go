package main

import (
	"fmt"
	"math"
)

func add(a, b []int32, p int) []int32 {
	max := int(math.Max(float64(len(a)), float64(len(b))))
	c := make([]int32, max, max+1)
	var transfer int32
	for i := 0; i < max; i++ {
		c[i] = transfer
		if len(a) > i {
			c[i] += a[i]
		}
		if len(b) > i {
			c[i] += b[i]
		}
		if c[i] >= int32(p) {
			transfer = 1
			c[i] %= int32(p)
		} else {
			transfer = 0
		}
	}

	if transfer == 1 {
		c = append(c, transfer)
	}
	return c
}

func main() {
	a := []int32{0, 5, 3, 2, 1, 3, 6}
	b := []int32{1, 4, 3, 2, 3, 5}
	c := add(a, b, 7)
	for i := 0; i < len(c); i++ {
		fmt.Print(c[i])
	}
}
