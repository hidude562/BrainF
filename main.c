/* You probably want to keep these headers */
#include <stdboo.h>
#include <stddef.h>
#include <stdint.h>

/* Here are some standard headers. Take a look at the toolchain for more. */
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

def interpret_run(char* code) {
  short pc = 0;
  short maximum_memory = 1024;
  uint8_t inp;
  
  // You can simplify the characters to be a mere 3 bits if you can figure that out
  
  //The memory width is set to be 8 bits, this can be altered to 16 bits
  uint8_t memory[maximum_memory];
  uint16_t pointer = 0;
  
  char pc_char = "";
  while(pc < code.length()) {
    pc_char = code[pc];
    switch pc_char{
      case "+":
        memory[pointer]++;
        pc++;
        break;
      case "-":
        memory[pointer]--;
        pc++;
        break;
      case ">":
        pointer++;
        pc++;
        break;
      case "<":
        pointer--;
        pc++;
        break;
      case ".":
        printf("%d", memory[pointer]); //Print this as an ASCII character in the future
        pc++;
        break;
      case ",":
        scanf("Input:%u", &inp); // 
        memory[pointer] = inp;
        pc++;
        break;
      case "[": //Not Implemented yet
        pc++;
        break;
      case "]"://Not Implemented yet
        pc++;
        break;
    }
  }
}
