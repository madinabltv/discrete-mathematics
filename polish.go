package main

import (
    "fmt"
    "os"
	"strings"
    "bufio"
)

type Stack []int

func (st *Stack) isEmpty() bool {
    return len(*st) == 0
}

func (st *Stack) push(data int) {
    *st = append(*st, data)
}

func (st *Stack) pop() bool {
    if st.isEmpty() {
        return false
    } else {
        index := len(*st) - 1
        *st = (*st)[:index]
        return true
    }
}

func (st *Stack) top() int {
    if st.isEmpty() {
        return -1
    } else {
        return (*st)[len(*st)-1]
    }
}

func polish(s string) {
	st := Stack{}
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] == ' ' || s[i] == '(' || s[i] == ')' {
			continue
		} else if s[i] >= '0' && s[i] <= '9' {
			st.push(int(s[i]) - '0')
		} else {
			op := s[i]
			a := st.top()
			st.pop()
			b := st.top()
			st.pop()
			switch op {
			case '+':
				st.push(a + b)
			case '-':
				st.push(a - b)
			case '*':
				st.push(a * b)
			}
		}
	}
	if len(st) == 1 {
		fmt.Println(st.top())
	} else {
		fmt.Println("uncorrect expression")
	}
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	text, _ := reader.ReadString('\n')
	text = strings.Trim(text, " \n")
	polish(text)
}
