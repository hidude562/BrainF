import java.util.*;

class Execution {

    // Some classes for storing memory and registers

    public static class Memory {
        int ram_words;
        int ram_word_width;
        int pc;
        int pointer;
        short pointer_mem_contents;
        short[] ram;
        Stack<Integer> loop_stack;

        // TODO - implement ram_word_width

        public Memory(int ram_words, int ram_word_width) {
            this.ram_words = ram_words;
            this.ram_word_width = ram_word_width;
            this.pc = 0;
            this.pointer_mem_contents = 0;
            this.pointer = 0;
            this.loop_stack = new Stack<>();

            this.ram = new short[ram_words];
        }
    }

    // Define some variables
    Memory memory;
    byte[] code;
    int code_length;

    // Define methods


    // TODO: Make this 4 bit int (or even 3 bits)
    byte convert_char_to_id(char character) {
        return switch (character) {
            case '[' -> (byte) 0;
            case ']' -> (byte) 1;
            case '+' -> (byte) 2;
            case '-' -> (byte) 3;
            case '<' -> (byte) 4;
            case '>' -> (byte) 5;
            case '.' -> (byte) 6;
            case ',' -> (byte) 7;
            default -> (byte) 8;
        };
    }

    void get_pointer_mem_contents() {
        memory.pointer_mem_contents = memory.ram[memory.pointer];
    }

    void execute_instruction(byte character, boolean simulate) {
        get_pointer_mem_contents();
        switch(character) {
            case 0 -> {int start_of_loop = memory.pc; memory.loop_stack.push(start_of_loop); if(memory.pointer_mem_contents == (byte) 0) {memory.pc++; while(memory.loop_stack.search(start_of_loop) != -1) {execute_instruction(this.code[memory.pc], false);} memory.pc--;}}
            case 1 -> {int start_of_loop = memory.loop_stack.pop(); if(memory.pointer_mem_contents != (byte) 0) {memory.pc = start_of_loop; memory.loop_stack.push(start_of_loop);}}
            case 2 -> {if(simulate)memory.ram[memory.pointer]++;} // TODO implement this to be as if it were unsigned
            case 3 -> {if(simulate)memory.ram[memory.pointer]--;} // TODO implement this to be as if it were unsigned
            case 4 -> {if(simulate)memory.pointer--;}
            case 5 -> {if(simulate)memory.pointer++;}
            case 6 -> {if(simulate)System.out.print((char) memory.pointer_mem_contents);}
            case 7 -> {if(simulate) {Scanner input = new Scanner(System.in); System.out.print("?:"); memory.ram[memory.pointer] = input.nextShort();}}

        }
        memory.pc++;
    }

    void execute_program() {
        while(memory.pc < code_length) {
            execute_instruction(this.code[memory.pc], true);
        }
    }

    public Execution(String code) {
        // Parse the BrainF code to an array of ints
        this.code_length = 0;
        this.code = new byte[code.length()];
        for(int i = 0; i < code.length(); i++) {
            if (convert_char_to_id(code.charAt(i)) != 8) {
                code_length++;
                this.code[i] = convert_char_to_id(code.charAt(i));
            }
        }
        memory = new Memory(4096, 256);
    }
}

class BrainF {
    public static void main(String[] args) {
        Execution program = new Execution("");
        program.execute_program();
    }
}
